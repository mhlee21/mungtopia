<template>
	<div class="comment-item">
		<div class="origin-comment">
			<img
				class="profile"
				src="@/assets/img/example_comment_user_profile.png"
			/>
			<div class="complex-box">
				<div>
					<p class="user-name">황희원</p>
					<p class="content">많이 짖는 편인가요?</p>
				</div>
				<div>
					<p class="time"><span>3</span>시간 전</p>
					<div class="motion-wrap">
						<a href="">수정</a>
						<a href="">삭제</a>
						<a href="">답글달기</a>
					</div>
				</div>
			</div>
		</div>
		<div class="re-comment">
			<img
				class="profile"
				src="@/assets/img/example_comment_user_profile.png"
			/>
			<div class="complex-box">
				<div>
					<p class="user-name">이연정</p>
					<p class="content">아뇨 몽이 거의 안 짖어요! 순둥이입니다</p>
				</div>
				<div>
					<p class="time"><span>4</span>시간 전</p>
					<div class="motion-wrap">
						<a href="">수정</a>
						<a href="">삭제</a>
					</div>
				</div>
			</div>
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
