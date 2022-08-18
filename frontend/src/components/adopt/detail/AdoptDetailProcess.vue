<template>
	<div class="applicant-detail-process">
		<!-- 입양 신청 절차가 진행되고 있을 떄 -->
		<div v-if="applicationStatus < 6">
			<div v-for="(title, index) in adoptProcessTitle" :key="index">
				<div
					class="applicant-detail-process-step"
					:class="{
						'active-step': applicationStatus === adoptProcess[index]?.step,
					}"
				>
					<!-- 단계 -->
					<div
						class="applicant-detail-process-step-num"
						:class="{ 'step-num-todo': index + 1 > applicationStatus }"
					>
						<h5 style="margin: auto">
							{{ index + 1 }}
						</h5>
					</div>
					<!-- 절차 -->
					<div
						class="step-title-wrapper"
						:class="{ 'step-title-todo': index + 1 > applicationStatus }"
					>
						<div class="step-title">
							{{ adoptProcessTitle[index] }}
						</div>
						<small
							v-if="
								adoptProcess[index]?.date == null
									? ''
									: adoptProcess[index]?.date
							"
							class="step-date"
							>{{ format(new Date(adoptProcess[index]?.date)) }}</small
						>
					</div>
					<!-- 아이콘 -->
					<div
						class="step-icon"
						:class="{ 'step-icon-todo': !adoptProcess[index]?.stepStatus }"
					>
						<button
							:disabled="buttonDisabled(index)"
							@click="clickAdoptProcessIcon(adoptProcess[index]?.step)"
							class="process-btn"
						>
							<i :class="adoptProcessIcon[index]"></i>
						</button>
					</div>
				</div>
				<div>
					<!-- 현재까지의 마지막 절차일때, 다음 단계로 넘어가는 버튼 구현 -->
					<button
						v-if="
							adoptProcess[index]?.step === applicationStatus &&
							$route.name === 'protectorDetail'
						"
						@click="adoptNextStep(adoptProcess[index]?.step)"
						class="next-btn"
					>
						<i class="fa-solid fa-chevron-down"></i>
						<span v-if="adoptProcess[index]?.step < 5"> 다음 단계 </span>
						<span v-else>입양 허가</span>
						<i class="fa-solid fa-chevron-down"></i>
					</button>
				</div>
			</div>
		</div>
		<!-- 입양이 완료되었을 때 -->
		<div v-else-if="applicationStatus === 6" style="padding: 15% 10%">
			<div style="padding: 5% 0">
				<div style="text-align: center">
					<h3 class="notice">입양이 완료되었습니다.</h3>
				</div>
			</div>
		</div>
		<!-- 입양 신청을 취소했을 때 -->
		<div
			v-else-if="applicationStatus === 7 && !isApplicant"
			class="cancel"
			style="padding: 20% 10%"
		>
			<div>
				<div style="text-align: center">
					<h3 class="notice">입양이 취소되었습니다</h3>
				</div>
				<div>
					이 페이지를 나가면 신청 내역이 삭제됩니다. <strong>확인</strong>을
					눌러주세요.
				</div>
			</div>
			<div
				class="cancel-ok-wrapper"
				style="padding: 20% 0 0; text-align: center"
			>
				<button class="cancel-ok-btn" @click="deleteAdoption">확인</button>
			</div>
		</div>
		<!-- 입양 신청을 반려했을 때 -->
		<div
			v-else-if="applicationStatus === 8 && isApplicant"
			class="cancel"
			style="padding: 20% 10%"
		>
			<div>
				<div style="text-align: center">
					<h3 class="notice">입양이 반려되었습니다</h3>
				</div>
				<div>
					이 페이지를 나가면 신청 내역이 삭제됩니다. <strong>확인</strong>을
					눌러주세요.
				</div>
			</div>
			<div
				class="cancel-ok-wrapper"
				style="padding: 20% 0 0; text-align: center"
			>
				<button class="cancel-ok-btn" @click="deleteAdoption">확인</button>
			</div>
		</div>
	</div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';
export default {
	setup() {
		const store = useStore();
		const router = useRouter();
		const route = useRoute();
		const adoptProcess = computed(() => store.getters['adopt/adoptProcess']);
		const isApplicant = computed(() => route.name === 'applicantDetail');
		const applicationStatus = computed(
			() => store.getters['adopt/applicationStatus'],
		);
		const userSeq = computed(() => store.getters['auth/user'].userSeq);
		const adoptProcessTitle = [
			'입양신청',
			'화상 면담',
			'1차 방문',
			'2차 방문',
			'입양완료',
		];
		const adoptProcessIcon = [
			'fa-solid fa-file-lines',
			'fa-solid fa-video',
			'fa-solid fa-location-dot',
			'fa-solid fa-location-dot',
			'fa-solid fa-circle-check',
		];
		const findEvenNumber = element => {
			if (
				element.adoptionProcessId === store.getters['adopt/adoptionProcessId']
			)
				return true;
		};
		const deleteAdoption = () => {
			if (isApplicant.value) {
				store.dispatch('adopt/deleteAdoption', { isApplicant: true });
				router.push({ name: 'adopt', params: { userSeq: userSeq.value } });
			} else {
				const index =
					store.getters['adopt/protectorDetail']['applicationList'].findIndex(
						findEvenNumber,
					);
				store.dispatch('adopt/deleteAdoption', { isApplicant: false, index });
			}
		};
		const activeApplicant = computed(
			() => store.getters['adopt/activeApplicant'],
		);
		const buttonDisabled = index => {
			if (index + 1 > applicationStatus.value) {
				return true;
			}
			if (index + 1 == 2) {
				console.log(adoptProcess.value[index].date);
				return new Date(adoptProcess.value[index].date) > new Date();
			}
			return false;
		};
		const clickAdoptProcessIcon = step => {
			if (step === 1) {
				// 입양자일 경우
				if (route.name == 'applicantDetail') {
					router.push({
						name: 'applicationDetail',
						params: {
							applicationId: route.params.applicationId,
						},
					});
				}
				// 보호자일 경우
				else {
					router.push({
						name: 'applicationDetail',
						params: {
							applicationId:
								store.getters['adopt/protectorDetail']['applicationList'][
									activeApplicant.value
								]['applicationId'],
						},
					});
				}
				console.log(step);
			} else if (step === 2) {
				console.log(adoptProcess.value[1].date); // 현재 localdateTime이랑 가져온 값 비교하기

				// const date = new Date();

				// const year = date.getFullYear();
				// const month = date.getMonth() + 1;
				// const day = date.getDate();

				// const hours = date.getHours();
				// const minutes = date.getMinutes();
				// const seconds = date.getSeconds();
				// const milliseconds = date.getMilliseconds();

				// 나중에 일정 예약시간 이전에는 disabled 처리할 것
				// router.push({
				// 	name: 'meeting',
				// 	params: { meetingRoomId: meetingRoomId.value },
				// });

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

		// 다음 단계
		const adoptNextStep = step => {
			const answer = confirm(
				'다음 단계로 넘어가시겠습니까? 한 번 넘어가면 다시 넘어올 수 없으니 신중한 선택 부탁드립니다.',
			);
			if (answer) {
				const newStep = {
					step: step,
					stepStatus: true,
				};
				store.dispatch('adopt/updateAdoptProcess', newStep);
			}
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
			buttonDisabled,
			deleteAdoption,
			isApplicant,
		};
	},
};
</script>

<style></style>
