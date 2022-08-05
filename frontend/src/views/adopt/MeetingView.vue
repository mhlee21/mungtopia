<template>
	<div class="meeting-view">
		<div v-if="isWait" style="padding: 10% 7%">
			<MeetingWait :isMikeOn="isMikeOn" :isCameraOn="isCameraOn"></MeetingWait>
		</div>
		<div v-else>
			<MeetingCall :isMikeOn="isMikeOn" :isCameraOn="isCameraOn"></MeetingCall>
		</div>
		<div style="display: flex; justify-content: space-around">
			<!-- 영상 -->
			<div @click="clickCamera()">
				<button v-if="isCameraOn">영상끄기</button>
				<button v-else>영상켜기</button>
			</div>
			<!-- 마이크 -->
			<div @click="clickMike()">
				<button v-if="isMikeOn">마이크끄기</button>
				<button v-else>마이크켜기</button>
			</div>
			<!-- 종료 -->
			<div @click="meetingRoomOut()">
				<button>종료</button>
			</div>
		</div>
	</div>
</template>

<script>
import MeetingWait from '@/components/adopt/meeting/MeetingWait';
import MeetingCall from '@/components/adopt/meeting/MeetingCall';
import { ref } from 'vue';
import { computed } from '@vue/reactivity';
import { useRouter } from 'vue-router';
export default {
	components: { MeetingWait, MeetingCall },
	setup() {
		const router = useRouter();

		// 미팅 대기/ 전화중 여부
		const meetingStatus = ref('wait');
		const isWait = computed(() => meetingStatus.value === 'wait');

		// 카메라
		const isCameraOn = ref(false);
		const clickCamera = () => {
			isCameraOn.value = !isCameraOn.value;
		};

		// 마이크
		const isMikeOn = ref(false);
		const clickMike = () => {
			isMikeOn.value = !isMikeOn.value;
		};

		// 종료
		const goBack = () => {
			router.go(-1);
		};
		const meetingRoomOut = () => {
			goBack();
		};
		return {
			isWait,
			isCameraOn,
			clickCamera,
			isMikeOn,
			clickMike,
			meetingRoomOut,
		};
	},
};
</script>

<style scoped>
.meeting-view {
	background: linear-gradient(to bottom, #ff9898, rgba(143, 187, 253, 0.58));
	height: 100%;
}
</style>
