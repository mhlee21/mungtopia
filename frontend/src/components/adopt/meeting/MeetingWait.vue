<template>
	<div>
		<div style="height: 10vh"></div>
		<!-- 이름 -->
		<div style="text-align: center"><h3>황희원</h3></div>
		<!-- 프로필 사진 -->
		<div style="height: 40vh; display: flex; align-content: center">
			<div
				style="border: 1px solid black; width: 40%; height: 40%; margin: auto"
			></div>
		</div>
		<!-- 문구 -->
		<div style="text-align: center">대기 중 입니다.</div>
		<div style="height: 10vh"></div>
		<!-- 버튼 -->
		<div style="display: flex; justify-content: space-around">
			<button>영상</button>
			<button>마이크</button>
			<button>종료</button>
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
