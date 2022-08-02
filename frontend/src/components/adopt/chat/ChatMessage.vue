<template>
	<div
		style="
			margin-top: 2vh;
			background-color: #ffd8d8;
			border-radius: 2rem;
			padding: 3% 1rem;
			height: 80%;
		"
	>
		{{ message }}
	</div>
</template>

<script>
export default {
	props: { message: String },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
