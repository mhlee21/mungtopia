<template>
	<div class="chat-component">
		<div class="chat-message-list" ref="scroll">
			<div v-for="(chat, index) in chatList" :key="index">
				<div class="chat-box" :class="{ 'chat-box-me': !isYou(chat.userSeq) }">
					<small v-if="!isYou(chat.userSeq)" class="chat-time">{{
						difTime(chat.createtime.replace(/-/g, '/'))
					}}</small>
					<div
						class="chat-message"
						:class="{ 'chat-message-me': !isYou(chat.userSeq) }"
					>
						{{ chat.content }}
					</div>
					<small v-if="isYou(chat.userSeq)" class="chat-time">{{
						difTime(chat.createtime.replace(/-/g, '/'))
					}}</small>
				</div>
			</div>
			<div v-for="(chat, index) in newChatList" :key="index">
				<div class="chat-box" :class="{ 'chat-box-me': !isYou(chat.userSeq) }">
					<small v-if="!isYou(chat.userSeq)" class="chat-time">{{
						difTime(chat.createtime.replace(/-/g, '/'))
					}}</small>
					<div
						class="chat-message"
						:class="{ 'chat-message-me': !isYou(chat.userSeq) }"
					>
						{{ chat.content }}
					</div>
					<small v-if="isYou(chat.userSeq)" class="chat-time">{{
						difTime(chat.createtime.replace(/-/g, '/'))
					}}</small>
				</div>
			</div>
		</div>
		<div>
			<form class="chat-form" @submit.prevent="sendMessage()">
				<input type="text" v-model="message" placeholder="메세지 보내기" />
				<button>
					<i class="fa-solid fa-paper-plane icon"></i>
				</button>
			</form>
		</div>
	</div>
</template>

<script>
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
import { computed, onMounted, reactive, ref } from 'vue';
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';
import api from '@/api/api';
import axios from 'axios';

export default {
	setup() {
		const store = useStore();

		//이전 기록 불러오기
		const preChatList = reactive([]);
		const chatList = reactive(store.getters['adopt/chatList']);

		const newChatList = reactive([]);
		const userSeq = computed(() => store.getters['auth/user']?.userSeq);
		const you = computed(() => store.getters['adopt/you']);
		const isYou = userSeq => userSeq === you.value.userSeq;

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
			stompClient.value.connect(
				{},
				() => {
					stompClient.value.connected = true;
					stompClient.value.subscribe('/send', res => {
						newChatList.push(JSON.parse(res.body));
					});
				},
				() => {
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
			if (userSeq.value !== '' && message.value !== '') {
				send();
				message.value = '';
			}
		};
		const send = () => {
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

		// 스크롤 구현
		const page = ref(1);
		const done = ref(false);
		const scroll = ref(null);
		onMounted(() => {
			scroll.value?.addEventListener('scroll', () => {
				const scrollTop = scroll.value.scrollTop;
				if (scrollTop === 0) {
					infiniteHandler();
				}
			});
		});
		const infiniteHandler = () => {
			if (done.value === false) {
				axios({
					url: api.adopt.chats(),
					method: 'get',
					params: {
						page: page.value,
						chatRoomId: chatRoomId,
						userSeq: userSeq.value,
					},
					headers: store.getters['auth/authHeader'],
				})
					.then(res => {
						if (res.data.body.data.chatLogDtoList.length === 0) {
							done.value = true;
						}
						store.commit(
							'adopt/SET_CHAT_LIST',
							res.data.body.data.chatLogDtoList,
						);
						store.commit('adopt/SET_CHAT_ROOM_ID', chatRoomId);

						const you = {
							userSeq: res.data.body.data.userSeq,
							nickname: res.data.body.data.nickname,
							profile: res.data.body.data.profile,
						};
						store.commit('adopt/SET_YOU', you);
						page.value += 1;
					})
					.catch(err => {
						console.error(err.response);
					});
			}
		};

		return {
			you,
			isYou,
			difTime,
			chatRoomId,
			message,
			sendMessage,
			preChatList,
			chatList,
			newChatList,
			page,
			infiniteHandler,
			scroll,
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
	height: 90%;
	overflow: auto;
}
.chat-box {
	display: flex;
	justify-content: flex-start;
}
.chat-box-me {
	justify-content: end;
}
.chat-time {
	font-size: 10px;
	color: gray;
	display: flex;
	align-items: flex-end;
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
.chat-form {
	position: fixed;
	bottom: 20px;
	border: 1px solid #d6d6d6;
	box-shadow: -2px 3px 4px rgba(0, 0, 0, 0.2), 2px 2px 4px rgba(0, 0, 0, 0.2);
	border-radius: 30px;
	background-color: #fff;
	z-index: 10;
	bottom: 80px;
	width: 320px;
	height: 45px;
	padding: 5px 15px;
	box-sizing: border-box;
	left: 50%;
	transform: translateX(-50%);
	display: flex;
	align-items: center;
	// margin: 0px;
	margin-bottom: 20px;
}
.chat-form input {
	width: 200px;
	margin-left: 5px;
	border: none;
	outline: none;
	font-size: 14px;
}
.chat-form button {
	padding: 0;
	border: none;
	background-color: transparent;
	width: calc(100% - 218px);
	text-align: right;
	cursor: pointer;
	font-size: 14px;
}
</style>
