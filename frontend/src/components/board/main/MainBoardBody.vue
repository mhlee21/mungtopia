<template>
	<div class="text-wrap">
		<div class="tit-box">
			<p class="dog-name">{{ board.dogName }}</p>
			<ul class="icon-box">
				<!-- active 클래스 추가 유무로 색상 전환 가능 -->
				<li
					class="star icon"
					:class="{ active: haveInterest }"
					@click.stop="clickStarEve"
				>
					<i class="fa-solid fa-star"></i>
				</li>
				<li
					class="heart icon"
					:class="{ active: isLike }"
					@click.stop="clickLikeEve"
				>
					<i class="fa-solid fa-heart"></i>
				</li>
			</ul>
		</div>
		<!-- 입양 -->
		<ul v-if="board.boardTag === 1" class="info-box">
			<li class="info">
				<h5>나이</h5>
				<p>{{ board.dogInfo.age }}</p>
			</li>
			<li class="info">
				<h5>몸무게</h5>
				<p>{{ board.dogInfo.weight }}</p>
			</li>
			<li class="info">
				<h5>성별</h5>
				<p>{{ board.dogInfo.gender }}</p>
			</li>
			<li class="info">
				<h5>중성화여부</h5>
				<p>{{ changeBoolean }}</p>
			</li>
		</ul>
		<!-- 후기 잡담 -->
		<div v-else class="text-box">
			<p>
				{{ board.contents }}
			</p>
		</div>
		<div class="brief-box">
			<ol v-if="board.boardTag === 1" class="hashtag-box">
				<li>{{ board.dogInfo.areaSido }}</li>
				<li>{{ board.dogInfo.breed }}</li>
			</ol>
			<div class="indicator-box">
				<div class="indicator indicator-likes">
					<p class="tit">좋아요</p>
					<p class="cont">
						<span>{{ board.likeCount }}</span
						>개
					</p>
				</div>
				<div class="indicator indicator-comment">
					<p class="tit">댓글</p>
					<p class="cont">
						<span>{{ board.commentCount }}</span
						>개
					</p>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
// import board from '@/store/modules/board';
import { computed, ref } from 'vue';
export default {
	props: {
		board: Object,
	},
	emits: ['clickHeart', 'clickStar'],
	setup(props, context) {
		// isLike 변경...!
		const isLike = ref(props.board.like);
		const haveInterest = ref(props.board.star);
		const changeBoolean = computed(() => props.board.dogInfo.neutering)
			? 'Y'
			: 'N';
		const clickStarEve = () => {
			if (haveInterest.value) {
				haveInterest.value = false;
			} else {
				haveInterest.value = true;
			}
			context.emit('clickStar');
		};
		const clickLikeEve = () => {
			if (isLike.value) {
				isLike.value = false;
			} else {
				isLike.value = true;
			}
			context.emit('clickHeart');
		};
		return { isLike, haveInterest, changeBoolean, clickStarEve, clickLikeEve };
	},
};
</script>

<style lang="scss" scoped></style>
