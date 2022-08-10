<template>
	<div>
		<div v-if="publisher" class="publisher-wrapper">
			<ov-video :stream-manager="publisher" />
		</div>
		<div v-if="subscriber" class="subscriber-wrapper">
			<ov-video :stream-manager="subscriber" />
		</div>
	</div>
</template>

<script>
import OvVideo from '@/components/adopt/meeting/OvVideo';

export default {
	props: { publisher: Object, subscriber: Object },
	setup() {
		return {};
	},
	components: {
		OvVideo,
	},
};
</script>
