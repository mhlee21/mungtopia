<template>
	<div class="applicant-detail-component">
		<div>
			<div style="width: 100px; height: 100px; border: solid black 1px"></div>
			<div style="text-align: center">{{ dogName }}</div>
		</div>
		<div class="applicant-detail-component-buttons">
			<button @click="goDetail">입양글 보기</button>
			<button @click="goChatRoom">채팅하기</button>
			<button @click="cancelAdoption">입양 취소</button>
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

		const goDetail = () => {};
		const goChatRoom = () => {
			router.push({
				name: 'chat',
				params: {
					chatRoomId: store.getters['adopt/applicantDetail']['chatRoomId'],
				},
			});
		};
		const cancelAdoption = () => {};
		return { dogName, goDetail, goChatRoom, cancelAdoption };
	},
};
</script>

<style>
.applicant-detail-component {
	display: flex;
	justify-content: center;
	background-color: white;
	border-radius: 1rem;
	width: 80%;
	padding: 7% 10% 2%;
}
.applicant-detail-component-buttons {
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	align-content: center;
	padding: 0 0 0 10%;
}
</style>
