<template>
	<div class="adopt-state-component">
		<!-- 입양하기 -->
		<div v-if="isApplicant">
			<div v-for="(mainTitle, index) in applicantMainTitle" :key="index">
				<AdoptStateTitle :title="mainTitle"></AdoptStateTitle>
				<div
					v-for="application in applicantMainList[index]"
					:key="application.appicationId"
				>
					<ApplicantMainComponent
						:application="application"
					></ApplicantMainComponent>
				</div>
			</div>
		</div>
		<!-- 입양보내기 -->
		<div v-else>
			<div v-for="(mainTitle, index) in protectorMainTitle" :key="index">
				<AdoptStateTitle :title="mainTitle"></AdoptStateTitle>
				<div v-for="board in protectorMainList[index]" :key="board.boardId">
					<ProtectorMainComponent :board="board"></ProtectorMainComponent>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import AdoptStateTitle from '@/components/adopt/main/AdoptStateTitle';
import ApplicantMainComponent from '@/components/adopt/main/ApplicantMainComponent';
import ProtectorMainComponent from '@/components/adopt/main/ProtectorMainComponent';
import { computed, reactive } from 'vue';
import { useStore } from 'vuex';
import { useRoute } from 'vue-router';
export default {
	components: {
		AdoptStateTitle,
		ApplicantMainComponent,
		ProtectorMainComponent,
	},
	setup() {
		const store = useStore();
		const route = useRoute();
		const applicantMainTitle = reactive([
			'입양중이예요!',
			'새로운 가족이 되었어요!',
		]);
		const protectorMainTitle = reactive([
			'새로운 가족을 찾고 있어요!',
			'새로운 가족을 찾았어요!',
		]);
		store.dispatch('adopt/fetchApplicantMainList', route.params.userSeq);

		const isApplicant = computed(() => store.getters['adopt/isApplicant']);
		const applicantMainList = computed(() => {
			return [
				store.getters['adopt/applicantMainList'].filter(
					application => application.applicationStatus < 6,
				),
				store.getters['adopt/applicantMainList'].filter(
					application => application.applicationStatus === 6,
				),
			];
		});
		const protectorMainList = computed(() => {
			return [
				store.getters['adopt/protectorMainList'].filter(
					application => application.adoptionStatus === 0,
				),
				store.getters['adopt/protectorMainList'].filter(
					application => application.adoptionStatus === 1,
				),
			];
		});

		return {
			isApplicant,
			applicantMainTitle,
			protectorMainTitle,
			applicantMainList,
			protectorMainList,
		};
	},
};
</script>

<style scoped>
.adopt-state-component {
	background-color: white;
	height: 80%;
	border-radius: 2rem;
	padding: 10%;
}
</style>
