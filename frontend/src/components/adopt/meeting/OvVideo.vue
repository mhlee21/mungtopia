<template>
	<video autoplay class="my-video" />
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
