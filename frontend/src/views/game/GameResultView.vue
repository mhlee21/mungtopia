<template>
	<div class="game-result-pg">
		<div class="game-header">반려견 매칭 결과</div>
		<div class="game-body">
			<div class="dog-image-wrapper"><img src="" alt="" /></div>
			<div class="dog-notice">
				{{ dogName }}몽이에 대해 더 자세히 알고 싶다면 터치해주세요!
			</div>
			<div class="dog-matching">
				<div class="dog-matching-title">
					<span class="dog-name">{{ dogName }}몽이</span>
					와(과) 매칭률
				</div>
				<div class="matching-percent">{{ matchingPercent }}87%</div>
			</div>
		</div>
		<div class="button-wrapper">
			<a click="menu-btn">Back to Menu</a>
			<button @click="share" class="share-btn">Share</button>
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
