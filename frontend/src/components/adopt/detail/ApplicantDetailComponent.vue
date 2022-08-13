<template>
	<div class="applicant-detail-component">
		<div>
			<img :src="dogImg" alt="" class="dog-img" />
			<div class="dog-name">{{ dogName }}</div>
		</div>
		<div class="btn-wrapper">
			<button @click="goDetail" class="btn">입양글 보기</button>
			<button @click="goChatRoom" class="btn">채팅하기</button>
			<button @click="cancelAdoption" class="btn cancel-btn">입양 취소</button>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { computed } from 'vue';
export default {
	setup() {
		const store = useStore();
		const router = useRouter();
		const applicantDetail = computed(
			() => store.getters['adopt/applicantDetail'],
		);
		const dogName = computed(() => applicantDetail.value?.dogName);
		const dogImg = computed(() => applicantDetail.value?.dogImg);
		const boardId = computed(() => applicantDetail.value?.boardId);
		const chatRoomId = computed(() => applicantDetail.value?.chatRoomId);
		const userSeq = computed(() => store.getters['auth/user']?.userSeq);

		const goDetail = () => {
			router.push({
				name: 'boardDetail',
				params: { boardId: boardId.value },
			});
		};
		const goChatRoom = () => {
			router.push({
				name: 'chat',
				params: {
					chatRoomId: chatRoomId.value,
				},
			});
		};
		const cancelAdoption = () => {
			const answer = confirm('입양을 취소하겠습니까?');
			if (answer === true) {
				store.dispatch('applicantCancel');
				router.push({
					name: 'adopt',
					params: { userSeq: userSeq.value },
				});
			}
		};
		return { dogName, dogImg, goDetail, goChatRoom, cancelAdoption };
	},
};
</script>

<style></style>
