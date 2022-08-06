<template>
	<div>
		<form action="" class="comment-form">
			<!-- lock icon 클래스 active 여부로 아이콘전환 -->
			<p class="active comment-icon">
				<i class="fa-solid fa-lock-open"></i>
			</p>
			<p class="comment-icon"><i class="fa-solid fa-lock"></i></p>
			<input type="text" />
			<button>댓글달기</button>
		</form>
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
