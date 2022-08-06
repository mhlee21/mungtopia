<template>
	<div>
		<ul class="feed-wrap">
			<li class="feed-item">
				<MainBoardHeader></MainBoardHeader>

				<div class="dog-cont-box">
					<MainBoardImage></MainBoardImage>
					<MainBoardBody></MainBoardBody>
				</div>
			</li>
		</ul>
	</div>
</template>

<script>
import MainBoardHeader from '@/components/board/main/MainBoardHeader.vue';
import MainBoardImage from '@/components/board/main/MainBoardImage.vue';
import MainBoardBody from '@/components/board/main/MainBoardBody.vue';
export default {
	components: { MainBoardHeader, MainBoardImage, MainBoardBody },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
