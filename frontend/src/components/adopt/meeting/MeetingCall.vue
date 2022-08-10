<template>
	<div>
		<!-- 영상화면 -->
		<div id="session" v-if="session">
			<div id="session-header">
				<input
					class="btn btn-large btn-danger"
					type="button"
					id="buttonLeaveSession"
					@click="leaveSession"
					value="Leave session"
				/>
			</div>
			<div id="main-video" class="col-md-6">
				<user-video :stream-manager="mainStreamManager" />
			</div>
			<div id="video-container" class="col-md-6">
				<user-video
					:stream-manager="publisher"
					@click.native="updateMainVideoStreamManager(publisher)"
				/>
				<user-video
					v-for="sub in subscribers"
					:key="sub.stream.connection.connectionId"
					:stream-manager="sub"
					@click.native="updateMainVideoStreamManager(sub)"
				/>
			</div>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import axios from 'axios';
import api from '@/api/api';
// import { computed } from 'vue';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/adopt/meeting/UserVideo';

export default {
	components: { UserVideo },
	setup() {
		const store = useStore();
		let OV = undefined;
		let session = undefined;
		let mainStreamManager = undefined;
		let publisher = undefined;
		let subscribers = [];
		let myUserName = 'Participant' + Math.floor(Math.random() * 100);

		// const userSeq = computed(() => store.getters['auth/user']?.userSeq);
		const userSeq = 1;

		const getOpenViduToken = userSeq => {
			return new Promise(resolve => {
				axios({
					url: api.meeting.getOpenViduToken(userSeq),
					method: 'post',
					headers: store.getters['auth/authHeader'],
				})
					.then(res => res.body.data)
					.then(data => {
						resolve(data.token);
						console.log('백엔드에 토큰 요청');
						console.log(data.token);
					})
					.catch(error => {
						console.log(error);
					});
			});
		};

		const joinSession = () => {
			// --- Get an OpenVidu object ---

			OV = new OpenVidu();

			// session 초기화
			session = OV.initSession();

			// session 내부적으로 일어나는 이벤트 처리

			// 새로운 stream 생성 -> session에 해당 stream을 subscribe하고 subscriber에 추가
			session.on('streamCreated', ({ stream }) => {
				const subscriber = session.subscribe(stream);
				subscribers.push(subscriber);
				console.log('streamCreated');
			});

			// stream 삭제(미팅에서 나갔을 때) -> 해당 stream을 subscriber에서 삭제
			session.on('streamDestroyed', ({ stream }) => {
				const index = subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					subscribers.splice(index, 1);
				}
				console.log('streamDestroyed');
			});

			// 모든 비동기 예외 처리..
			session.on('exception', ({ exception }) => {
				console.warn(exception);
				console.log('exception');
			});

			// 토큰 받고 처리
			getOpenViduToken(userSeq).then(token => {
				console.log('getOpenViduToken 토큰 받고 처리');
				session
					.connect(token, { clientData: myUserName })
					.then(() => {
						console.log('session connect');
						// 내 stream setting
						publisher = OV.initPublisher(undefined, {
							audioSource: undefined, // undefined이면 default microphone
							videoSource: undefined, // undefined이면 default webcam
							publishAudio: true, // 시작시 음소거 여부
							publishVideo: true, // 비디오 켜기 여부
							resolution: '640x480', // 비디오 해상도
							frameRate: 30, // 주사율
							insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
							mirror: false, // 거울모드 여부
						});

						mainStreamManager = publisher;

						// session에 stream publish
						session.publish(publisher);
						console.log('session publish');
					}) // 에러처리
					.catch(error => {
						console.log(
							'There was an error connecting to the session:',
							error.code,
							error.message,
						);
					});
			});

			// 처음 들어왔을 때 evernListener 추가
			// beforeuload  이벤트 발생시 leaveSession 처리
			window.addEventListener('beforeunload', leaveSession);
		};

		const leaveSession = () => {
			console.log('leaveSession');
			// session.disconnect 를 이용하여 session 연결 해제
			if (this.session) this.session.disconnect();
			// 현재 data들 모두 초기화
			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			// eventlistener 삭제
			window.removeEventListener('beforeunload', this.leaveSession);
		};

		const updateMainVideoStreamManager = stream => {
			if (mainStreamManager === stream) return;
			mainStreamManager = stream;
			console.log('updateMainVideoStreamManager');
		};

		joinSession(); // 세션 참여
		return { updateMainVideoStreamManager };
	},
};
</script>

<style lang="scss" scoped></style>
