<template>
	<div class="chat-component">
		<div id="mydiv" class="chat-message-list">
			<div v-for="(chat, index) in chatList" :key="index">
				<div>
					<small v-if="!isYou(chat.userSeq)" class="chat-time">{{
						difTime(chat.createtime)
					}}</small>
					<div
						class="chat-message"
						:class="{ 'chat-message-me': !isYou(chat.userSeq) }"
					>
						{{ chat.content }}
					</div>
					<small v-if="isYou(chat.userSeq)" class="chat-time">{{
						difTime(chat.createtime)
					}}</small>
				</div>
			</div>
		</div>
		<div>
			<form @submit.prevent="sendMessage()">
				<input type="text" v-model="message" />
				<button>보내기</button>
			</form>
		</div>
	</div>
</template>

<script>
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
import { computed, reactive, ref } from 'vue';
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';
import api from '@/api/api';

export default {
	setup() {
		const store = useStore();

		//이전 기록 불러오기
		// store.dispatch('adopt/fetchChatMain');
		const chatList = computed(() => store.getters['adopt/chatList']);
		console.log('이전 기록 불러오기...', chatList.value);

		const newChatList = reactive([]);
		const userSeq = computed(() => store.getters['auth/user']?.userSeq);
		const you = computed(() => store.getters['adopt/you']);
		const isYou = userSeq => userSeq === you.value.userSeq;
		// console.log('you', you.value);
		// console.log('유저', userSeq.value);

		// 시간 처리
		const difTime = timeValue => {
			const time = new Date(timeValue);
			const today = new Date();
			const betweenTime = Math.floor((today.getTime() - time) / 1000 / 60);
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

		//웹소켓 연결
		const stompClient = reactive({});
		const connect = () => {
			const serverURL = api.adopt.chatStart();
			let socket = new SockJS(serverURL);
			stompClient.value = Stomp.over(socket);
			console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
			stompClient.value.connect(
				{},
				frame => {
					stompClient.value.connected = true;
					console.log('소켓 연결 성공', frame);
					stompClient.value.subscribe('/send', res => {
						console.log('구독으로 받은 메시지 입니다.', res.body);
						newChatList.push(JSON.parse(res.body));
						console.log('newChatList', newChatList);
					});
				},
				error => {
					console.log('소켓 연결 실패', error);
					stompClient.value.connected = false;
				},
			);
		};
		connect();

		// 메시지 전송
		const message = ref('');
		const route = useRoute();
		const chatRoomId = route.params.chatRoomId;
		const sendMessage = () => {
			console.log('userSeq.value:', userSeq.value);
			if (userSeq.value !== '' && message.value !== '') {
				send();
				message.value = '';
			}
		};
		const send = () => {
			console.log('Send message:' + message.value);
			if (stompClient.value && stompClient.value.connected) {
				const date = new Date(Date.now());
				const msg = {
					userSeq: userSeq.value,
					content: message.value,
					createtime: date,
					chatRoomId: chatRoomId,
				};
				stompClient.value.send('/receive', JSON.stringify(msg), {});
			}
		};
		return {
			// you,
			isYou,
			difTime,
			chatRoomId,
			message,
			sendMessage,
			chatList,
			newChatList,
		};
	},
};
</script>

<style lang="scss" scoped>
.chat-component {
	margin: 5% auto;
	background-color: white;
	border-radius: 2rem;
	padding: 10% 5% 0;
	height: 90%;
	width: 90%;
}
.chat-message-list {
	overflow: auto;
	height: 80vh;
}
.chat-time {
	font-size: 10px;
	color: gray;
}
.chat-message {
	margin-top: 2vh;
	background-color: #ffd8d8;
	border-radius: 0px 20px 20px 20px;
	padding: 0 1rem 3%;
	display: inline-block;
	font-size: 14px;
	max-width: 75%;
	padding: 10px;
}
.chat-message-me {
	background-color: #efefef;
	border-radius: 20px 20px 0px 20px;
}
</style>
