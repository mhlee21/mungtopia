<template>
	<div class="game-play-pg">
		<div class="game-header">
			<div class="game-controller">
				<div @click="clickBackButton">{{ `< Back` }}</div>
				<div>
					<b>{{ qNum }}</b>
					{{ ` of ${MATCH.length}` }}
				</div>
				<div @click="clickX">X</div>
			</div>
			<div class="game-status-wrapper">
				<div class="game-status-bar">
					<div
						class="game-status-bar game-status-bar-inner"
						:style="{ width: (qNum / MATCH.length) * 100 + '%' }"
					></div>
				</div>
			</div>
			<div class="game-question-wrapper">
				<div class="question">{{ MATCH[qNum - 1]?.question }}</div>
				<div class="question-icon">
					<i class="fa-regular fa-file-lines"></i>
				</div>
			</div>
		</div>
		<div class="game-body">
			<button
				class="game-answer-btn"
				v-for="(answer, index) in answerList"
				:key="index"
				@click="clickAnswerButton(index)"
			>
				{{ answer }}
			</button>
			<div class="image-wrapper">
				<img :src="require('@/assets/img/bgle.png')" alt="" class="dog-img2" />
				<img
					:src="require('@/assets/img/masco.png')"
					alt="개 아이콘  제작자: Freepik - Flaticon"
					class="dog-img1"
				/>
				<img :src="require('@/assets/img/siba.png')" alt="" class="dog-img3" />
				<img :src="require('@/assets/img/hava.png')" alt="" class="dog-img2" />
				<img
					:src="require('@/assets/img/Mastiff.png')"
					alt=""
					class="dog-img1"
				/>
			</div>
		</div>
	</div>
</template>

<script>
import { MATCH } from '@/assets/Qdata.json';
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import { useStore } from 'vuex';
export default {
	setup() {
		const qNum = ref(1);
		const store = useStore();
		const route = useRoute();
		const router = useRouter();
		const answerList = [
			'매우 그렇다',
			'그렇다',
			'보통이다',
			'아니다',
			'매우 아니다',
		];
		const answer = Array(MATCH.length);
		const clickAnswerButton = index => {
			answer[qNum] = index;
			if (qNum.value < MATCH.length) {
				qNum.value++;
			} else {
				store.dispatch('game/saveAnswer', answer);
				console.log(answer);
				router.push({
					name: 'gameResult',
					params: { gameType: route.params.gameType },
				});
			}
		};
		const clickBackButton = () => {
			if (qNum.value === 1) {
				router.push({
					name: 'gameIntro',
					params: { gameType: route.params.gameType },
				});
			}
			qNum.value--;
		};
		const clickX = () => {
			router.push({ name: 'game' });
		};
		return {
			qNum,
			MATCH,
			answerList,
			clickBackButton,
			clickAnswerButton,
			clickX,
		};
	},
};
</script>

<style lang="scss" scoped></style>
