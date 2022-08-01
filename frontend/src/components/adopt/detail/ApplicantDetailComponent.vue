<template>
	<div
		style="
			display: flex;
			justify-content: center;
			background-color: white;
			border-radius: 1rem;
			width: 80%;
			padding: 7% 10% 2%;
		"
	>
		<div>
			<div style="width: 100px; height: 100px; border: solid black 1px"></div>
			<div style="text-align: center">몽이</div>
		</div>
		<div
			style="
				display: flex;
				flex-direction: column;
				justify-content: space-around;
				align-content: center;
				padding: 0 0 0 10%;
			"
		>
			<button>입양글 보기</button>
			<button>채팅하기</button>
			<button>입양 취소</button>
		</div>
	</div>
</template>

<script>
export default {
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
