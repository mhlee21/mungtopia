<template>
	<div class="game-play-pg">
		<div class="game-header game-header2">
			<div class="game-controller">
				<div @click="clickBackButton">{{ `< Back` }}</div>
				<div>
					<b>{{ qNum }}</b>
					{{ ` of ${MBTI.length}` }}
				</div>
				<div @click="clickX">X</div>
			</div>
			<div class="game-status-wrapper">
				<div class="game-status-bar">
					<div
						class="game-status-bar game-status-bar-inner2"
						:style="{ width: (qNum / MBTI.length) * 100 + '%' }"
					></div>
				</div>
			</div>
			<div class="game-question-wrapper2">
				<div class="question2">{{ MBTI[qNum - 1]?.question }}</div>
				<div class="question3"></div>
				<div class="question-icon2">
					<i class="fa-solid" :class="iconList[qNum - 1]"></i>
				</div>
			</div>
		</div>
		<div class="game-body">
			<button
				v-for="(answer, index) in answerList"
				:key="index"
				@click="clickAnswerButton(index)"
				class="game-answer-btn2"
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
import { useRouter } from 'vue-router';
import { ref, computed } from 'vue';
import { useStore } from 'vuex';
export default {
	setup() {
		const qNum = ref(1);
		const store = useStore();
		const router = useRouter();
		const user = computed(() => store.getters['auth/user']);

		// 게임 정답 저장
		const MBTI = computed(() => Qdata?.MBTI);
		const answerList = computed(() => MBTI.value[qNum.value - 1].answer);
		const answer = Array(MBTI.value.length).fill(0);
		// 답 선택
		const questionType = idx => MBTI.value[idx]['question_type'];
		const clickAnswerButton = index => {
			answer[qNum.value - 1] = index;
			// 마지막 문제가 아닐때
			if (qNum.value < MBTI.value.length) {
				qNum.value++;
			} else {
				// 마지막 문제일 때
				// matchNum 결과 저장
				const result = [0, 0, 0, 0];
				for (let idx = 0; idx < MBTI.value.length; idx++) {
					const qIndex = questionType(idx) - 1;
					result[qIndex] += answer[idx];
				}
				const mbtiData = [
					['I', 'E'],
					['S', 'N'],
					['T', 'F'],
					['J', 'P'],
				];
				const MBTIResult = result
					.map((num, index) =>
						num < 2 ? mbtiData[index][0] : mbtiData[index][1],
					)
					.join('');
				if (user.value) {
					const payload = {
						userSeq: store.getters['auth/user']['userSeq'],
						result: 1,
						gameTag: 2,
					};
					store.dispatch('game/sendResult', payload);
				}
				router.push({
					name: 'game2Result',
					params: { mbti: MBTIResult },
				});
			}
		};

		const iconList = [
			'fa-plane',
			'fa-person-walking',
			'fa-face-grin-beam-sweat',
			'fa-lightbulb',
			'fa-dumbbell',
			'fa-mountain',
			'fa-car-burst',
			'fa-dog',
			'fa-person-harassing',
			'fa-suitcase-rolling',
			'fa-house-chimney',
			'fa-paw',
		];
		// 뒤로가기 버튼
		const clickBackButton = () => {
			if (qNum.value === 1) {
				router.push({
					name: 'gameIntro',
					params: { gameTag: 2 },
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
			MBTI,
			clickBackButton,
			clickAnswerButton,
			clickX,
			answerList,
			iconList,
		};
	},
};
</script>

<style lang="scss" scoped></style>
