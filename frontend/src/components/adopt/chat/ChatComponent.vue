<template>
	<div class="chat-component">
		<div class="chat-message-list">
			<div v-for="(chat, index) in chatLog" :key="index">
				<div>
					<small v-if="!isYou(chat.userSeq)" class="chat-time">{{
						difTime(chat.createtime)
					}}</small>
					<div class="chat-message" :class="{ 'chat-message-me': isYou }">
						{{ chat.content }}
					</div>
					<small v-if="isYou(chat.userSeq)" class="chat-time">{{
						difTime(chat.createtime)
					}}</small>
				</div>
			</div>
			<div v-for="(chat, index) in chatList" :key="index">
				<div v-if="isYou(chat.userSeq)">
					<div>
						<img
							style="width: 20px; height: 20px"
							src="data:image/png;base64,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"
						/>{{ you.profile }}
						<small>{{ you.nickname }}</small>
					</div>
				</div>
				<div>
					<small v-if="!isYou(chat.userSeq)" class="chat-time">{{
						difTime(chat.createtime)
					}}</small>
					<div class="chat-message" :class="{ 'chat-message-me': isYou }">
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
import { useStore } from 'vuex';
import { computed, reactive, ref } from 'vue';
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';

export default {
	setup() {
		const store = useStore();
		const you = computed(() => store.getters['adopt/you']);

		//이전 기록 불러오기
		store.dispatch('adopt/fetchChatMain');
		const chatLog = computed(() => store.getters['adopt/chatList']);
		console.log('이전 기록 불러오기...', chatLog.value);

		const chatList = reactive([]);
		const isYou = userSeq => userSeq === you.value.userSeq;

		// 시간 처리
		const difTime = timeValue => {
			console.log(timeValue);
			return timeValue;
			// const today = new Date();
			// const betweenTime = Math.floor(
			// 	(today.getTime() - timeValue.getTime()) / 1000 / 60,
			// );
			// if (betweenTime < 1) return '방금전';
			// if (betweenTime < 60) {
			// 	return `${betweenTime}분전`;
			// }
			// const betweenTimeHour = Math.floor(betweenTime / 60);
			// if (betweenTimeHour < 24) {
			// 	return `${betweenTimeHour}시간전`;
			// }
			// const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
			// if (betweenTimeDay < 365) {
			// 	return `${betweenTimeDay}일전`;
			// }
			// return `${Math.floor(betweenTimeDay / 365)}년전`;
		};

		//웹소켓 연결
		const stompClient = reactive({});
		const connect = () => {
			const serverURL = 'http://localhost:8081/api/v1/chat';
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
						chatList.push(JSON.parse(res.body));
						console.log('chatList', chatList);
					});
				},
				error => {
					console.log('소켓 연결 실패', error);
					stompClient.value.connected = false;
				},
			);
		};
		connect();

		const message = ref('');
		// const userSeq = computed(() => store.getters['auth/user']['userSeq']);
		const userSeq = ref();
		userSeq.value = 1;
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
					// userSeq: userSeq.value,
					userSeq: 1,
					content: message.value,
					createtime: date,
					isRequest: false,
					chatRoomId: 4, //chatRoomId?
				};
				stompClient.value.send('/receive', JSON.stringify(msg), {});
			}
		};
		return { you, chatList, isYou, difTime, message, sendMessage, chatLog };
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
	height: 88%;
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
