<template>
	<div class="meeting-pg">
		<div v-if="!session" class="meeting-component">
			<!-- 스피너 구현 -->
			스피너 구현될 예정입니다.
		</div>
		<div v-else class="meeting-component">
			<!-- 비디오를 켜지 않았을 때 -->
			<div v-if="!isVideoOn">
				<MeetingWait></MeetingWait>
			</div>
			<!-- 비디오를 켰을 때 -->
			<div v-else>
				<MeetingCall
					:publisher="publisher"
					:subscriber="subscriber"
				></MeetingCall>
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
import MeetingWait from '@/components/adopt/meeting/MeetingWait';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import MeetingCall from '@/components/adopt/meeting/MeetingCall';

axios.defaults.headers.post['Content-Type'] = 'application/json';

export default {
	setup() {
		const router = useRouter();

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
				console.log('heewon');
			});

			session.value.on('streamDestroyed', () => {
				subscriber.value = undefined;
			});

			session.value.on('exception', ({ exception }) => {
				console.warn(exception);
			});

			getToken(mySessionId.value).then(token => {
				session.value
					.connect(token, { clientData: myUserName.value })
					.then(() => {
						// --- Get your own camera stream with the desired properties ---

						publisher.value = OV.value.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
							publishVideo: true, // Whether you want to start publishing with your video enabled or not
							resolution: '640x480', // The resolution of your video
							frameRate: 30, // The frame rate of your video
							insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
							mirror: false, // Whether to mirror your local video or not
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
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (session.value) session.value.disconnect();

			session.value = undefined;
			publisher.value = undefined;
			subscriber.value = undefined;
			OV.value = undefined;

			window.removeEventListener('beforeunload', leaveSession);
		};

		const getToken = mySessionId => {
			return createSession(mySessionId).then(sessionId =>
				createToken(sessionId),
			);
		};

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
		const createSession = sessionId => {
			return new Promise((resolve, reject) => {
				axios
					.post(
						`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
						JSON.stringify({
							customSessionId: sessionId,
						}),
						{
							auth: {
								username: 'OPENVIDUAPP',
								password: OPENVIDU_SERVER_SECRET,
							},
						},
					)
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId.value);
						} else {
							console.warn(
								`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`,
							);
							if (
								window.confirm(
									`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`,
								)
							) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		};

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
		const createToken = sessionId => {
			return new Promise((resolve, reject) => {
				axios
					.post(
						`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
						{},
						{
							auth: {
								username: 'OPENVIDUAPP',
								password: OPENVIDU_SERVER_SECRET,
							},
						},
					)
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
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
		MeetingCall,
	},
};
</script>
