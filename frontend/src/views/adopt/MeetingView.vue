<template>
	<div class="meeting-pg">
		<div v-if="!session" class="meeting-component">
			<div class="spinner-wrapper">
				<!-- 스피너 구현 -->
				<div class="sk-chase">
					<div class="sk-chase-dot"></div>
					<div class="sk-chase-dot"></div>
					<div class="sk-chase-dot"></div>
					<div class="sk-chase-dot"></div>
					<div class="sk-chase-dot"></div>
					<div class="sk-chase-dot"></div>
				</div>
			</div>
		</div>
		<div v-else class="meeting-component">
			<!-- 내화면 -->
			<div v-if="isVideoOn && publisher">
				<div class="publisher-wrapper">
					<ov-video :stream-manager="publisher" />
				</div>
			</div>
			<!-- 상대 화면 -->
			<div v-if="!subscriber">
				<MeetingWait></MeetingWait>
			</div>
			<div v-else class="subscriber-wrapper">
				<ov-video :stream-manager="subscriber" />
			</div>

			<!-- 하단 버튼 -->
			<div class="button-wrapper">
				<!-- 영상 -->
				<div @click="clickVideo()">
					<button v-if="isVideoOn" class="control-btn">
						<i class="fa-solid fa-video"></i>
					</button>
					<button v-else class="control-btn">
						<i class="fa-solid fa-video-slash"></i>
					</button>
				</div>
				<!-- 마이크 -->
				<div @click="clickAudio()">
					<button v-if="isAudioOn" class="control-btn">
						<i class="fa-solid fa-microphone"></i>
					</button>
					<button v-else class="control-btn">
						<i class="fa-solid fa-microphone-slash"></i>
					</button>
				</div>
				<!-- 종료 -->
				<div @click="meetingRoomOut()">
					<button class="control-btn end-btn">
						<i class="fa-solid fa-phone" style="transform: rotate(135deg)"></i>
					</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import { ref, computed } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useStore } from 'vuex';
import MeetingWait from '@/components/adopt/meeting/MeetingWait';
import OvVideo from '@/components/adopt/meeting/OvVideo';
import api from '@/api/api';
axios.defaults.headers.post['Content-Type'] = 'application/json';

export default {
	setup() {
		const router = useRouter();
		const route = useRoute();
		const store = useStore();
		const userSeq = computed(() => store.getters['auth/user']?.userSeq);

		let OV = ref(undefined);
		let session = ref(undefined);
		let publisher = ref(undefined);
		let subscriber = ref(undefined);
		let mySessionId = ref('SessionB' + Math.floor(Math.random() * 100));
		let myUserName = ref('Participant' + Math.floor(Math.random() * 100));
		let isVideoOn = ref(true);
		let isAudioOn = ref(true);

		const clickVideo = () => {
			isVideoOn.value = !isVideoOn.value;
			publisher.value.publishVideo(isVideoOn.value);
		};

		// 마이크
		const clickAudio = () => {
			isAudioOn.value = !isAudioOn.value;
			publisher.value.publishAudio(isAudioOn.value);
		};

		// 종료
		const goBack = () => {
			router.go(-1);
		};
		const meetingRoomOut = () => {
			goBack();
		};

		const joinSession = () => {
			OV.value = new OpenVidu();

			session.value = OV.value.initSession();

			session.value.on('streamCreated', ({ stream }) => {
				subscriber.value = session.value.subscribe(stream);
			});

			session.value.on('streamDestroyed', () => {
				subscriber.value = undefined;
			});

			session.value.on('exception', ({ exception }) => {
				console.warn(exception);
			});

			getToken().then(token => {
				session.value
					.connect(token, { clientData: myUserName.value })
					.then(() => {
						publisher.value = OV.value.initPublisher(undefined, {
							audioSource: undefined,
							videoSource: undefined,
							publishAudio: true,
							publishVideo: true,
							resolution: '1080x1920',
							frameRate: 30,
							insertMode: 'APPEND',
							mirror: false,
						});

						// --- Publish your stream ---

						session.value.publish(publisher.value);
					})
					.catch(error => {
						console.log(
							'There was an error connecting to the session:',
							error.code,
							error.message,
						);
					});
			});

			window.addEventListener('beforeunload', leaveSession);
		};

		const leaveSession = () => {
			if (session.value) session.value.disconnect();

			session.value = undefined;
			publisher.value = undefined;
			subscriber.value = undefined;
			OV.value = undefined;
			axios({
				url: api.meeting.sessionDelete(userSeq.value),
				method: 'delete',
				headers: store.getters['auth/authHeader'],
			}).catch(error => console.log(error.response));
			window.removeEventListener('beforeunload', leaveSession);
		};

		const getToken = () => {
			return createToken();
		};

		const createToken = () => {
			return new Promise((resolve, reject) => {
				axios({
					url: api.meeting.getOpenViduToken(userSeq.value),
					method: 'post',
					headers: {
						'Content-Type': 'application/json',
						Authorization: store.getters['auth/authHeader'],
					},
					data: JSON.stringify({ applicationId: route.params.applicationId }),
				})
					.then(response => {
						resolve(response.data.body.data.token);
					})

					.catch(err => {
						reject(err.response);
					});
			});
		};

		joinSession();

		return {
			joinSession,
			leaveSession,
			OV,
			session,
			publisher,
			subscriber,
			mySessionId,
			myUserName,
			clickVideo,
			clickAudio,
			meetingRoomOut,
			isVideoOn,
			isAudioOn,
		};
	},
	components: {
		MeetingWait,
		OvVideo,
	},
};
</script>
<style scoped>
.sk-chase {
	width: 40px;
	height: 40px;
	position: relative;
	animation: sk-chase 2.5s infinite linear both;
}

.sk-chase-dot {
	width: 100%;
	height: 100%;
	position: absolute;
	left: 0;
	top: 0;
	animation: sk-chase-dot 2s infinite ease-in-out both;
}

.sk-chase-dot:before {
	content: '';
	display: block;
	width: 25%;
	height: 25%;
	background-color: #fff;
	border-radius: 100%;
	animation: sk-chase-dot-before 2s infinite ease-in-out both;
}

.sk-chase-dot:nth-child(1) {
	animation-delay: -1.1s;
}
.sk-chase-dot:nth-child(2) {
	animation-delay: -1s;
}
.sk-chase-dot:nth-child(3) {
	animation-delay: -0.9s;
}
.sk-chase-dot:nth-child(4) {
	animation-delay: -0.8s;
}
.sk-chase-dot:nth-child(5) {
	animation-delay: -0.7s;
}
.sk-chase-dot:nth-child(6) {
	animation-delay: -0.6s;
}
.sk-chase-dot:nth-child(1):before {
	animation-delay: -1.1s;
}
.sk-chase-dot:nth-child(2):before {
	animation-delay: -1s;
}
.sk-chase-dot:nth-child(3):before {
	animation-delay: -0.9s;
}
.sk-chase-dot:nth-child(4):before {
	animation-delay: -0.8s;
}
.sk-chase-dot:nth-child(5):before {
	animation-delay: -0.7s;
}
.sk-chase-dot:nth-child(6):before {
	animation-delay: -0.6s;
}

@keyframes sk-chase {
	100% {
		transform: rotate(360deg);
	}
}

@keyframes sk-chase-dot {
	80%,
	100% {
		transform: rotate(360deg);
	}
}

@keyframes sk-chase-dot-before {
	50% {
		transform: scale(0.4);
	}
	100%,
	0% {
		transform: scale(1);
	}
}
</style>
