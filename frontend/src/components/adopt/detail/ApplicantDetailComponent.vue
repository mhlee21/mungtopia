<template>
	<div class="applicant-detail-component">
		<div>
			<img
				src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTH1VJcmjOMZRlaKFeYYQZCK5CAMO9l7LM19A&usqp=CAU"
				alt=""
				class="dog-img"
			/>
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
		const dogName = computed(
			() => store.getters['adopt/applicantDetail'].dogName,
		);

		const goDetail = () => {
			router.push({
				name: 'boardDetail',
				params: { boardId: store.getters['adopt/applicantDetail']['boardId'] },
			});
		};
		const goChatRoom = () => {
			router.push({
				name: 'chat',
				params: {
					chatRoomId: store.getters['adopt/applicantDetail']['chatRoomId'],
				},
			});
		};
		const cancelAdoption = () => {
			// 입양을 취소하겠습니까? 물어보고 처리 입양자가 취소 axios 요청
			console.log('userSeq 없음');
			router.push({
				name: 'adopt',
				params: { userSeq: store.getters['auth/user']?.userSeq },
			});
		};
		return { dogName, goDetail, goChatRoom, cancelAdoption };
	},
};
</script>

<style></style>
