<template>
	<div class="comment-item">
		<!-- 댓글 -->
		<div class="origin-comment">
			<img class="profile" :src="comment.author.profile" />
			<div class="complex-box">
				<div>
					<p class="user-name">{{ comment.author.nickname }}</p>
					<!-- 댓글 내용 -->
					<!-- 비밀댓글이 아니거나, 작성자일 때 댓글 보여주기 -->
					<div v-if="!comment.secret || comment.author?.userSeq === userSeq">
						<p class="content">{{ comment.contents }}</p>
					</div>
					<div v-else>
						<p class="content">비밀 댓글입니다.</p>
					</div>
				</div>
				<!-- 시간, 수정, 삭제 -->
				<div>
					<p class="time">
						{{ difTime(new Date(comment.createtime.replace(/-/g, '/'))) }}
					</p>
					<div class="motion-wrap">
						<!-- 댓글 수정 및 삭제 -->
						<!-- 댓글 작성자일 경우 -->
						<!-- <div v-if="comment.author.userSeq === userSeq"> -->
						<a @click.prevent="clickModalButton(0, comment)">수정</a>
						<a @click.prevent="clickModalButton(1, comment)">삭제</a>
						<!-- </div> -->
						<!-- 답글 달기 -->
						<!-- 공개 댓글이거나, 댓글 작성자이거나, 게시판 글 작성자이거나. -->
						<div
							v-if="
								!comment.secret ||
								comment.author?.userSeq === userSeq ||
								boardAuthor?.userSeq === userSeq
							"
						>
							<a @click.prevent="showReplyForm()">답글달기</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 대댓글 -->
		<div v-if="comment.replyList">
			<!-- 답글 달기 폼 -->
			<form
				@submit.prevent="createReply(comment.commentId)"
				class="reply-form"
				v-if="writeReply"
			>
				<!-- lock icon 클래스 active 여부로 아이콘전환 -->
				<div @click="replySecret()">
					<p class="reply-icon" :class="{ active: !isReplySecret }">
						<i class="fa-solid fa-lock-open"></i>
					</p>
					<p class="reply-icon" :class="{ active: isReplySecret }">
						<i class="fa-solid fa-lock"></i>
					</p>
				</div>
				<input type="text" v-model="newReply" />
				<button>달기</button>
			</form>
			<div
				class="re-comment"
				v-for="reply in comment.replyList"
				:key="reply.replyId"
			>
				<!-- 대댓글 유저 프로필 -->
				<img class="profile" :src="reply.author.profile" />
				<div class="complex-box">
					<div>
						<p class="user-name">{{ reply.author.nickname }}</p>
						<p class="content">{{ reply.contents }}</p>
					</div>
					<div>
						<p class="time">
							{{ difTime(new Date(reply.createtime.replace(/-/g, '/'))) }}
						</p>
						<!-- 대댓글 작성자가 본인	일 때 -->
						<div class="motion-wrap" v-if="reply.author?.userSeq === userSeq">
							<a @click.prevent="clickModalButton(2, comment, reply)">수정</a>
							<a @click.prevent="clickModalButton(3, comment, reply)">삭제</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 모달 구현 -->
		<Teleport to="body">
			<detailModal
				:show="showModal"
				@close-modal="showModal = false"
			></detailModal>
		</Teleport>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { ref, computed } from 'vue';
import DetailModal from '@/components/board/detail/DetailModal.vue';

export default {
	props: { comment: Object },
	components: { DetailModal },
	setup() {
		const store = useStore();

		// 모달 구현
		const showModal = ref(false);
		const selectModalType = modalType => {
			store.dispatch('board/selectModalType', modalType);
		};
		const selectComment = comment => {
			store.dispatch('board/selectComment', comment);
		};
		const selectReply = reply => {
			store.dispatch('board/selectReply', reply);
		};
		const clickModalButton = (modalType, comment, reply) => {
			showModal.value = true;
			selectModalType(modalType);
			selectComment(comment);
			selectReply(reply);
		};

		// boardAuthor
		const boardAuthor = computed(() => store.getters['board/board']['author']);
		// userSeq
		const userSeq = computed(() => store.getters['auth/user']?.userSeq);
		// nickname
		const nickname = computed(() => store.getters['auth/user']?.userNickname);

		// 대댓글 생성
		const writeReply = ref(false);
		const isReplySecret = ref(false);
		const newReply = ref(null);
		const showReplyForm = () => {
			writeReply.value = true;
		};
		const replySecret = () => {
			isReplySecret.value = !isReplySecret.value;
		};
		const createReply = commentId => {
			if (newReply.value) {
				writeReply.value = false;
				const payload = {
					userSeq: userSeq.value,
					content: newReply.value,
					secret: isReplySecret.value,
					userNickname: nickname.value,
				};
				store.dispatch('board/createReply', { commentId, payload });
				newReply.value = null;
				isReplySecret.value = false;
			} else {
				alert('댓글을 작성해주세요');
			}
		};
		// 시간 수정
		const difTime = timeValue => {
			const today = new Date();
			const betweenTime = Math.floor(
				(today.getTime() - timeValue.getTime()) / 1000 / 60,
			);
			if (betweenTime < 1) return '방금전';
			if (betweenTime < 60) {
				return `${betweenTime}분전`;
			}
			const betweenTimeHour = Math.floor(betweenTime / 60);
			if (betweenTimeHour < 24) {
				return `${betweenTimeHour}시간전`;
			}
			const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
			if (betweenTimeDay < 365) {
				return `${betweenTimeDay}일전`;
			}
			return `${Math.floor(betweenTimeDay / 365)}년전`;
		};
		return {
			userSeq,
			boardAuthor,
			difTime,
			writeReply,
			isReplySecret,
			newReply,
			replySecret,
			showReplyForm,
			createReply,
			showModal,
			clickModalButton,
			selectComment,
			selectReply,
		};
	},
};
</script>

<style lang="scss" scoped>
.profile {
	width: 33px;
	height: 33px;
}
</style>
