<template>
	<div class="adopt-state-component">
		<div v-if="mainList?.length">
			<div
				v-for="(mainTitle, status) in mainTitleList[isApplicant ? 1 : 0]"
				:key="status"
				class="component-wrapper"
			>
				<div class="title-wrapper" v-if="applicationList[status]?.length">
					<i class="fa-solid fa-heart title-icon"></i>
					<h3 class="title">{{ mainTitle }}</h3>
				</div>
				<div v-if="isApplicant">
					<div
						v-for="application in applicationList[status]"
						:key="application.appicationId"
					>
						<ApplicantMainComponent
							:application="application"
						></ApplicantMainComponent>
					</div>
				</div>
				<div v-else>
					<div v-for="board in applicationList[status]" :key="board.boardId">
						<ProtectorMainComponent :board="board"></ProtectorMainComponent>
					</div>
				</div>
			</div>
		</div>
		<div v-else>입양 관련 기록이 존재하지 않습니다</div>
	</div>
</template>

<script>
import ApplicantMainComponent from '@/components/adopt/main/ApplicantMainComponent';
import ProtectorMainComponent from '@/components/adopt/main/ProtectorMainComponent';
import { computed } from 'vue';
import { useStore } from 'vuex';
export default {
	components: {
		ApplicantMainComponent,
		ProtectorMainComponent,
	},
	setup() {
		const store = useStore();
		const mainTitleList = [
			['새로운 가족을 찾고 있어요!', '새로운 가족을 찾았어요!'],
			['입양 중이예요!', '새로운 가족이 되었어요!'],
		];

		const isApplicant = computed(() => store.getters['adopt/isApplicant']);
		const mainList = computed(() => store.getters['adopt/mainList']);
		const applicationList = computed(() =>
			isApplicant.value && mainList.value
				? [
						mainList?.value?.filter(
							application =>
								application.applicationStatus < 6 ||
								application.applicationStatus === 8,
						),
						mainList?.value?.filter(
							application => application.applicationStatus === 6,
						),
				  ]
				: [
						mainList?.value?.filter(
							application => application.adoptionStatus === false,
						),
						mainList?.value?.filter(
							application => application.adoptionStatus === true,
						),
				  ],
		);
		return {
			isApplicant,
			applicationList,
			mainList,
			mainTitleList,
		};
	},
};
</script>

<style scoped></style>
