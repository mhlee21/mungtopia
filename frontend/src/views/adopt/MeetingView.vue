<template>
	<div>
		<div v-if="isWait()">
			<MeetingWait></MeetingWait>
		</div>
		<div v-else>
			<MeetingCall></MeetingCall>
		</div>
		<MeetingButtonList></MeetingButtonList>
	</div>
</template>

<script>
import MeetingWait from '@/components/adopt/meeting/MeetingWait';
import MeetingCall from '@/components/adopt/meeting/MeetingCall';
import MeetingButtonList from '@/components/adopt/meeting/MeetingButtonList';
import { ref } from 'vue';
export default {
	components: { MeetingWait, MeetingCall, MeetingButtonList },
	setup() {
		const type = ref('wait');
		const isWait = () => {
			return type.value === 'wait';
		};
		return { type, isWait };
	},
};
</script>

<style lang="scss" scoped></style>
