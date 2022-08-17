<template>
	<div class="chat-view">
		<div style="height: 80vh">
			<div style="display: flex; position: relative; height: 10%">
				<div style="width: 20%">
					<button @click="goBack">back</button>
				</div>
				<div>
					<div style="width: 30%">
						<h3>{{ you.nickname }}</h3>
						<div v-if="!isApplicant">
							<button
								v-if="!isReservated"
								@click="adoptSchedule()"
								style="background-color: white"
							>
								일정 예약
							</button>
							<button
								v-else
								@click="adoptSchedule()"
								style="background-color: white"
							>
								일정 수정
							</button>
						</div>
					</div>
				</div>
			</div>
			<ChatComponent></ChatComponent>
		</div>
	</div>
</template>

<script>
import { useRouter, useRoute } from 'vue-router';
import { useStore } from 'vuex';
import { computed } from 'vue';
import ChatComponent from '@/components/adopt/chat/ChatComponent';

export default {
	components: { ChatComponent },
	setup() {
		const store = useStore();
		const router = useRouter();
		const route = useRoute();
		const isReservated = computed(() => !(store.getters['adopt/date'] === ''));
		const isApplicant = computed(() => store.getters['adopt/isApplicant']);
		store.dispatch('adopt/fetchChatMain', {
			chatRoomId: route.params.chatRoomId,
			page: 0,
		});

		const chatRoomId = computed(() => store.getters['adopt/chatRoomId']);
		const adoptSchedule = () => {
			router.push({ name: 'schedule', params: { chatRoomId } });
		};
		const you = computed(() => store.getters['adopt/you']);
		const goBack = () => {
			router.go(-1);
		};
		return {
			isReservated,
			isApplicant,
			goBack,
			adoptSchedule,
			you,
			chatRoomId,
		};
	},
};
</script>

<style scoped>
.chat-view {
	background-color: #ff9898;
	overflow: hidden;
	height: 100vh;
	padding: 10% 7%;
	box-sizing: border-box;
}
.chat-profile-image {
	height: 100px;
	width: 100px;
	border-radius: 50%;
	border: white 10px solid;
	offset: 10px;
}
</style>
