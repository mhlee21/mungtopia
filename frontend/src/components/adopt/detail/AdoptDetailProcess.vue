<template>
	<div class="applicant-detail-process">
		<!-- 입양 신청 절차가 진행되고 있을 떄 -->
		<div v-if="applicationStatus < 6">
			<div
				v-for="(step, index) in adoptProcess"
				:key="step.id"
				style="display: flex; height: 10vh"
			>
				<!-- 단계 -->
				<div
					class="applicant-detail-process-step-num"
					:class="{ 'step-num-todo': !step.stepStatus }"
				>
					<h5 style="margin: auto">
						{{ step.step }}
					</h5>
				</div>
				<!-- 절차 -->
				<div
					class="applicant-detail-process-step-title"
					:class="{ 'step-title-todo': !step.stepStatus }"
				>
					<div>{{ adoptProcessTitle[index] }}</div>
					<small v-if="step.date">{{ format(new Date(step.date)) }}</small>
				</div>
				<!-- 아이콘 -->
				<div
					class="applicant-detail-process-step-icon"
					:class="{ 'step-icon-todo': !step.stepStatus }"
				>
					<button
						@click="clickAdoptProcessIcon(step.step)"
						:disabled="!step.stepStatus"
					>
						{{ adoptProcessIcon[index] }}
					</button>
					<!-- 현재까지의 마지막 절차일때, 다음 단계로 넘어가는 버튼 구현 -->
					<button
						v-if="
							step.step === applicationStatus &&
							$route.name === 'protectorDetail'
						"
						@click="adoptNextStep(step.step)"
					>
						다음 단계
					</button>
				</div>
			</div>
		</div>
		<div v-else-if="applicationStatus === 6" style="padding: 15% 5%">
			<div style="padding: 5% 0">
				<div style="text-align: center">
					<h3>입양이 완료되었습니다.</h3>
				</div>
			</div>
		</div>
		<!-- 입양 신청이 반려되었을 때 -->
		<div v-else style="padding: 15% 5%">
			<div style="padding: 5% 0">
				<div style="text-align: center">
					<h3>입양이 반려되었습니다.</h3>
				</div>
				<hr />
				<div>
					이 페이지를 나가면 신청 내역이 삭제됩니다. <strong>확인</strong>을
					눌러주세요.
				</div>
			</div>
			<div style="padding: 20% 0 0; text-align: center">
				<button>확인</button>
			</div>
		</div>
	</div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
export default {
	setup() {
		const store = useStore();
		const router = useRouter();
		const adoptProcess = computed(() => store.getters['adopt/adoptProcess']);
		const applicationStatus = computed(
			() => store.getters['adopt/applicationStatus'],
		);
		const meetingRoomId = computed(() => store.getters['adopt/meetingRoomId']);
		const adoptProcessTitle = [
			'입양신청',
			'화상 면담',
			'1차 방문',
			'2차 방문',
			'입양완료',
		];
		const adoptProcessIcon = ['신청서', '화상 면담', '지도', '지도', '체크'];

		const clickAdoptProcessIcon = step => {
			if (step === 1) {
				// router.push({ name: 'user', params: { username: 'eduardo' }});
				console.log(step);
			} else if (step === 2) {
				router.push({
					name: 'meeting',
					params: { meetingRoomId: meetingRoomId.value },
				});
				console.log(step);
			} else if (step === 3) {
				// router.push({ name: 'user', params: { username: 'eduardo' }});
				console.log(step);
			} else if (step === 4) {
				// router.push({ name: 'user', params: { username: 'eduardo' }});
				console.log(step);
			} else {
				// router.push({ name: 'user', params: { username: 'eduardo' }});
				console.log(step);
			}
		};

		const adoptNextStep = step => {
			const newStep = {
				step: step + 1,
				stepStatus: true,
			};
			store.dispatch('adopt/updateAdoptProcess', {
				step: newStep,
				adoptionProcessId: store.getters['adopt/adoptionProcessId'],
			});
		};

		// 날짜 변환
		const format = date => {
			const year = date.getFullYear();
			const month =
				date.getMonth() + 1 > 9
					? date.getMonth() + 1
					: '0' + (date.getMonth() + 1);
			const day = date.getDate() > 9 ? date.getDate() : '0' + date.getDate();
			const hour =
				date.getHours() > 9 ? date.getHours() : '0' + date.getHours();
			const minute =
				date.getMinutes() > 9 ? date.getMinutes() : '0' + date.getMinutes();
			return `${year}.${month}.${day} ${hour}:${minute}`;
		};

		return {
			format,
			adoptProcess,
			applicationStatus,
			adoptProcessTitle,
			adoptProcessIcon,
			clickAdoptProcessIcon,
			adoptNextStep,
		};
	},
};
</script>

<style>
.applicant-detail-process {
	margin-top: 2vh;
	background-color: white;
	border-radius: 2rem;
	padding: 3% 1rem;
}

.applicant-detail-process-step-num {
	border-radius: 50%;
	width: 1.5rem;
	height: 1.5rem;
	background-color: #ff9898;
	display: flex;
	align-items: center;
	margin: auto 0;
	color: white;
}

.step-num-todo {
	background-color: gray;
}
.step-title-todo {
	color: gray;
}
.step-icon-todo {
	color: gray;
}

.applicant-detail-process-step-title {
	padding: 0 10%;
	width: 50%;
	margin: auto 0;
}

.applicant-detail-process-step-icon {
	margin: auto 0;
}
</style>
