<template>
	<div v-if="streamManager">
		<ov-video :stream-manager="streamManager" />
		<div>
			<p>{{ clientData }}</p>
		</div>
	</div>
</template>

<script>
import OvVideo from './OvVideo';

export default {
	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
	},
	setup() {
		const clientData = () => {
			const { clientData } = getConnectionData();
			return clientData;
		};

		const getConnectionData = () => {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		};
		return { clientData };
	},
};
</script>
