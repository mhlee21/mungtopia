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
import Qdata from '@/assets/Qdata.json';
import { useRoute, useRouter } from 'vue-router';
import { ref, computed } from 'vue';
import { useStore } from 'vuex';
export default {
	setup() {
		const qNum = ref(1);
		const store = useStore();
		const route = useRoute();
		const router = useRouter();
		const user = computed(() => store.getters['auth/user']);
		// 게임 정답 저장
		const answerList = [
			'매우 그렇다',
			'그렇다',
			'보통이다',
			'아니다',
			'매우 아니다',
		];
		const MATCH = computed(() => Qdata?.MATCH);
		const answer = Array(MATCH.value.length).fill(0);
		// 답 선택
		const matchNum = Array(6).fill(0);
		const questionType = idx => MATCH.value[idx]['question_type'];
		const clickAnswerButton = index => {
			answer[qNum.value - 1] = 6 - (index + 1);
			// 마지막 문제가 아닐때
			if (qNum.value < MATCH.value.length) {
				qNum.value++;
			} else {
				// 마지막 문제일 때
				// matchNum 결과 저장
				for (let idx = 0; idx < MATCH.value.length; idx++) {
					const qType = questionType(idx);
					matchNum[qType] += answer[idx];
				}
				let payload = {};
				if (user.value) {
					payload = {
						userSeq: user.value?.userSeq,
						matchAnswer: matchNum,
						gameTag: 2,
					};
				} else {
					payload = {
						userSeq: 0,
						matchAnswer: matchNum,
						gameTag: 2,
					};
				}
				store.dispatch('game/sendMatchResult', payload);
				router.push({
					name: 'gameResult',
					params: { gameType: route.params.gameType },
				});
			}
		};

		// 뒤로가기 버튼
		const clickBackButton = () => {
			if (qNum.value === 1) {
				router.push({
					name: 'gameIntro',
					params: { gameType: route.params.gameType },
				});
			}
			qNum.value--;
		};
		// 나가기 버튼
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
