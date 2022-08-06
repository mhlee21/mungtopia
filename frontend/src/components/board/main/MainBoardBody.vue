<template>
	<div class="text-wrap">
		<div class="tit-box">
			<p class="dog-name">몽이</p>
			<ul class="icon-box">
				<!-- active 클래스 추가 유무로 색상 전환 가능 -->
				<li class="heart icon active">
					<i class="fa-solid fa-heart"></i>
				</li>
			</ul>
		</div>
		<div class="text-box">
			<p>
				오늘 몽이랑 같이 산책 다녀왔어요! 몽이랑 같이 벚꽃 구경해서 너무
				행복합니닿ㅎ 다들 갱얼쥐랑 꽃구경 다녀오세요^^
			</p>
		</div>
		<div class="brief-box">
			<div class="indicator-box">
				<div class="indicator indicator-likes">
					<p class="tit">좋아요</p>
					<p class="cont"><span>3</span>개</p>
				</div>
				<div class="indicator indicator-comment">
					<p class="tit">댓글</p>
					<p class="cont"><span>5</span>개</p>
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
