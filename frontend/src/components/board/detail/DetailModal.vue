<template>
	<Transition name="detailModal">
		<div v-if="show" class="modal-mask">
			<div class="modal-wrapper">
				<div class="modal-container">
					<div class="modal-header">
						<slot name="header">{{ modalData.title }}</slot>
					</div>

					<div class="modal-body">
						<!-- 댓글 수정 -->
						<div v-if="modalType === 0">
							<textarea
								cols="30"
								rows="4"
								v-model="newComment.contents"
								class="comment-change-box"
							></textarea>
						</div>
						<div v-else-if="modalType === 1">
							<slot name="body">댓글을 삭제하시겠습니까?</slot>
						</div>
						<div v-else-if="modalType === 2">
							<textarea
								cols="30"
								rows="4"
								v-model="newReply.contents"
								class="comment-change-box"
							></textarea>
						</div>
						<div v-else-if="modalType === 3">
							<slot name="body">댓글을 삭제하시겠습니까?</slot>
						</div>
					</div>

					<div class="modal-footer">
						<slot name="footer">
							<button
								class="modal-default-button"
								@click="
									modalData?.buttonFunction(
										modalData?.commentId,
										modalData?.replyId,
									)
								"
							>
								{{ modalData?.buttonValue[0] }}
							</button>
							<button class="modal-default-button" @click="$emit('closeModal')">
								{{ modalData?.buttonValue[1] }}
							</button>
						</slot>
					</div>
				</div>
			</div>
		</div>
	</Transition>
</template>

<script>
import { computed, reactive } from 'vue';
import { useStore } from 'vuex';

export default {
	props: { show: Boolean },
	emits: ['closeModal'],
	setup(props, { emit }) {
		const store = useStore();

		const comment = computed(() => store.getters['board/comment']);
		const newComment = reactive(comment);
		const reply = computed(() => store.getters['board/reply']);
		const newReply = reactive(reply);
		const modalType = computed(() => store.getters['board/modalType']);

		const modalData = computed(() => {
			let data = {};
			if (modalType?.value === 0) {
				data = {
					title: '댓글 수정',
					contents: newComment.value?.contents,
					buttonValue: ['완료', '취소'],
					buttonFunction: updateComment,
					commentId: newComment.value?.commentId,
				};
			} else if (modalType?.value === 1) {
				data = {
					title: '댓글 삭제',
					buttonValue: ['예', '아니오'],
					buttonFunction: deleteComment,
					commentId: newComment.value?.commentId,
				};
			} else if (modalType?.value === 2) {
				data = {
					title: '답글 수정',
					contents: reply.value?.contents,
					buttonValue: ['완료', '취소'],
					buttonFunction: updateReply,
					commentId: newComment.value?.commentId,
					replyId: reply.value?.replyId,
				};
			} else {
				data = {
					title: '답글 삭제',
					buttonValue: ['예', '아니오'],
					buttonFunction: deleteReply,
					commentId: newComment.value?.commentId,
					replyId: reply.value?.replyId,
				};
			}
			return data;
		});

		const userSeq = computed(() => store.getters['auth/user']?.userSeq);

		// 댓글 수정
		const updateComment = commentId => {
			emit('closeModal');
			const payload = {
				userSeq: userSeq.value,
				secret: newComment.value.secret,
				contents: newComment.value.contents,
				userNickname: newComment.value.nickname,
			};
			store.dispatch('board/updateComment', { commentId, payload });
		};
		// 댓글 삭제
		const deleteComment = commentId => {
			emit('closeModal');
			const payload = {
				userSeq: userSeq.value,
			};
			store.dispatch('board/deleteComment', { commentId, payload });
		};

		// 대댓글 수정
		const updateReply = (commentId, replyId) => {
			emit('closeModal');
			const payload = {
				userSeq: userSeq.value,
				secret: newReply.value.secret,
				contents: newReply.value.contents,
				userNickname: newReply.value.author.nickname,
			};
			store.dispatch('board/updateReply', { commentId, replyId, payload });
		};
		// 대댓글 삭제
		const deleteReply = (commentId, replyId) => {
			emit('closeModal');
			const payload = {
				userSeq: userSeq.value,
			};
			store.dispatch('board/deleteReply', { commentId, replyId, payload });
		};

		return { modalData, comment, newComment, reply, newReply, modalType };
	},
};
</script>

<style>
.modal-mask {
	position: fixed;
	z-index: 9998;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	display: table;
	transition: opacity 0.3s ease;
}

.modal-wrapper {
	display: table-cell;
	vertical-align: middle;
}

.modal-container {
	width: 300px;
	margin: 0px auto;
	background-color: #fff;
	border-radius: 2px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
	transition: all 0.3s ease;
	border-radius: 10px;
	height: 40%;
	overflow: hidden;
}

.modal-header {
	background-color: #ff9898;
	height: 20%;
	display: flex;
	align-items: center;
	padding: 0 5%;
	color: white;
}

.modal-body {
	padding: 7% 10%;
	height: 40%;
	display: flex;
	align-items: center;
	justify-content: center;
}

.modal-footer {
	padding: 0 10%;
	display: flex;
	justify-content: space-around;
}

.modal-default-button {
	background-color: #ff9898;
	border-radius: 10px;
	border: none;
	color: white;
	padding: 5px;
	width: 80px;
}

.modal-enter-from {
	opacity: 0;
}

.modal-leave-to {
	opacity: 0;
}

.modal-enter-from .modal-container,
.modal-leave-to .modal-container {
	-webkit-transform: scale(1.1);
	transform: scale(1.1);
}

.comment-change-box {
	border-radius: 10px;
	padding: 2%;
	border: solid lightgray 1px;
	font-family: 'Noto Sans KR', sans-serif;
}
</style>
