<template>
	<div class="game-result-pg">
		<div class="game-header">반려견 매칭 결과</div>
		<div class="game-body">
			<div class="dog-image-wrapper" @click="clickDogImage">
				<img :src="dogImg" alt="dogImg" class="dog-img" />
			</div>
			<div class="dog-notice">
				{{ dogName }}(이)에 대해 더 자세히 알고 싶다면 터치해주세요!
			</div>
			<div class="dog-matching">
				<div class="dog-matching-title">
					<span class="dog-name">{{ dogName }}</span>
					와(과) 매칭률
				</div>
				<div class="matching-percent">{{ percent }}%</div>
			</div>
			<div class="button-wrapper">
				<a class="menu-btn" @click="clickMenu">Back to Menu</a>
				<button @click="clickShare" class="share-btn">Share</button>
			</div>
		</div>
	</div>
</template>

<script>
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

export default {
	setup() {
		const router = useRouter();
		const clickMenu = () => {
			router.push({ name: 'gameIntro' });
		};
		const store = useStore();
		const matchData = computed(() => store.getters['game/matchData']);
		const boardId = computed(() => matchData.value['boardId']);
		const percent = computed(() => matchData.value['percent']);
		const dogName = computed(() => matchData.value['dogName']);
		const dogImg = computed(() => matchData.value['dogImg']);
		const clickDogImage = () => {
			router.push({ path: '/board/detail/' + boardId.value });
		};
		const clickShare = () => {
			window.Kakao.Link.sendCustom({
				templateId: 81265,
			});
		};
		return {
			clickMenu,
			clickDogImage,
			clickShare,
			matchData,
			percent,
			dogName,
			dogImg,
		};
	},
};
</script>

<style lang="scss" scoped></style>
