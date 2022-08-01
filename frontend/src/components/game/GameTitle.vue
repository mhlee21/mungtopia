<template>
	<div>
		<h2 class="title">{{ gameTitle }}</h2>
	</div>
</template>

<script>
export default {
	props: ['gameTitle'],
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped>
.title {
	text-align: center;
	margin: 20px;
	color: #0d6aba;
	padding: 10px;
}
</style>
