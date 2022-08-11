<template>
	<v-container>
		<v-expansion-panel>
			<v-expansion-panel-header><h1>채팅</h1></v-expansion-panel-header>
			<v-expansion-panel-content>
				<v-card class="mt-2">
					<v-card-title><h2>채팅 테스트</h2></v-card-title>
					<v-card-text>
						<v-alert dense type="info" color="teal lighten-3"
							>두개 이상의 브라우저를 띄워 간단한 채팅을 확인해 볼 수
							있다.</v-alert
						>
						<v-container>
							uid :
							<input v-model="uid" type="text" />
							message :
							<input v-model="message" type="text" @keyup="sendMessage" />
							<div class="mt-2" v-for="(item, idx) in recvList" :key="idx">
								<v-card
									class="mt-2 mb-2"
									color="teal lighten-3"
									dark
									max-width="400"
								>
									<v-card-text>
										<div>uid : {{ item.user.id }}</div>
										<div>{{ item.message }}</div>
										<div>{{ item.sendAt }}</div>
									</v-card-text>
								</v-card>
							</div>
						</v-container>
					</v-card-text>
				</v-card>
			</v-expansion-panel-content>
		</v-expansion-panel>
	</v-container>
</template>

<script lang="js">
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
    data() {
        return {
            uid: '',
            message: '',
            recvList: []
            }
    },
    created() {
        this.connect() // ChattingView.vue 생성 시 소켓 연결 시도
    },
    methods: {
        sendMessage(e) {
            if(e.keyCode === 13 && this.uid !== '' && this.message !== '') {
                this.send();
                this.message = '';
            }
        },
        send() {
            console.log("Send message:" + this.message);
            if (this.stompClient && this.stompClient.connected) {
                const msg = {
                user: {
                    id: this.uid
                },
                message: this.message,
                sendAt: Date.now(),
                isRequest: false,
                };
                this.stompClient.send("/receive", JSON.stringify(msg), {});
            }
        },
        connect() {
            const serverURL = "http://localhost:8081/api/v1/chat"
            let socket = new SockJS(serverURL);
            this.stompClient = Stomp.over(socket);
            console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
            this.stompClient.connect(
                {},
                frame => {
                this.connected = true;
                console.log('소켓 연결 성공', frame);
                this.stompClient.subscribe("/send", res => {
                    console.log('구독으로 받은 메시지 입니다.', res.body);
                    this.recvList.push(JSON.parse(res.body))
                });
                },
                error => {
                console.log('소켓 연결 실패', error);
                this.connected = false;
                }
            );
        }
    }
}
</script>
<!-- <template>
	<div class="chat-view">
		<div style="height: 80%">
			<div style="display: flex; position: relative; height: 10%">
				<div style="width: 20%">
					<button @click="goBack">back</button>
				</div>
				<div style="width: 30%">
					<h3 style="margin: 0">{{ you.nickname }}</h3>
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
				<div style="width: 50%"></div>
				<div>
					<div style="position: absolute; right: 10%">
						<img
							src="data:image/jpeg;base64,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"
							alt="dog"
							class="chat-profile-image"
						/>
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
		store.dispatch('adopt/fetchChatMain', route.params.chatRoomId);

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
	height: 100%;
	padding: 10% 7%;
}
.chat-profile-image {
	height: 100px;
	width: 100px;
	border-radius: 50%;
	border: white 10px solid;
	offset: 10px;
}
</style> -->
