<template>
	<video autoplay />
</template>

<script>
export default {
	props: {
		streamManager: Object,
	},
	mounted() {
		this.streamManager.addVideoElement(this.$el);
	},
};
</script>
