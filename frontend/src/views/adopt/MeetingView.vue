<template>
	<div class="meeting-pg">
		<div v-if="!session" class="meeting-component">
			<!-- 스피너 구현 -->
			스피너 구현될 예정입니다.
		</div>
		<div v-else class="meeting-component">
			<!-- 내화면 -->
			<div v-if="isVideoOn && publisher">
				<div class="publisher-wrapper">
					<ov-video :stream-manager="publisher" />
				</div>
			</div>
			<div v-if="!subscriber">
				<MeetingWait></MeetingWait>
			</div>
			<!-- <div v-else>
				<div class="publisher-wrapper black-view"></div>
			</div> -->
			<!-- 상대화면 -->
			<!-- 비디오를 켜지 않았을 때 -->
			<!-- 비디오를 켰을 때 -->

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
import { ref } from 'vue';
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
				console.log(subscriber);
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
						console.log(publisher.value);
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
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (session.value) session.value.disconnect();

			session.value = undefined;
			publisher.value = undefined;
			subscriber.value = undefined;
			OV.value = undefined;
			axios
				.delete(api.meeting.sessionDelete(route.params.applicationId))
				.catch(error => console.log(error.response));
			window.removeEventListener('beforeunload', leaveSession);
		};

		const getToken = () => {
			return createToken();
		};

		const createToken = () => {
			return new Promise((resolve, reject) => {
				const userSeq = 1;
				console.log(userSeq);
				axios({
					url: api.meeting.getOpenViduToken(userSeq),
					method: 'post',
					headers: store.getters['auth/authHeader'],
					data: route.params.applicationId,
				})
					.then(response => response.data)
					.then(data => resolve(data.token))
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
