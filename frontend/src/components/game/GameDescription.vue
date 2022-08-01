<template>
	<div>
		<h3 class="description">{{ gameDescription }}</h3>
	</div>
</template>

<script>
export default {
	props: ['gameDescription'],
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped>
.description {
	text-align: center;
	margin: 10px;
	padding: 10px;
	white-space: pre-line;
}
</style>
