<template>
	<div
		style="
			background-color: white;
			height: 80%;
			border-radius: 2rem;
			padding: 10%;
		"
	>
		<div v-for="(title, index) in titleList" :key="index">
			<AdoptStateTitle :title="titleList[index]"></AdoptStateTitle>
			<div
				v-for="application in applicationList[index]"
				:key="application.application_id"
			>
				<ApplicationList :application="application"></ApplicationList>
			</div>
		</div>
	</div>
</template>

<script>
import AdoptStateTitle from '@/components/adopt/main/AdoptStateTitle';
import ApplicationList from '@/components/adopt/main/ApplicationList';
import { computed } from 'vue';
import { useStore } from 'vuex';
export default {
	components: { AdoptStateTitle, ApplicationList },
	setup() {
		const store = useStore();
		let titleList = computed(() => {
			return store.getters['adopt/isApplicant']
				? ['입양중이예요!', '새로운 가족이 되었어요!']
				: ['새로운 가족을 찾고 있어요!', '새로운 가족을 찾았어요!'];
		});
		const applicationList = computed(() => {
			return [
				store.getters['adopt/applicationList'].filter(
					application => application.application_status < 6,
				),
				store.getters['adopt/applicationList'].filter(
					application => application.application_status === 6,
				),
			];
		});

		return {
			titleList,
			applicationList,
		};
	},
};
</script>

<style lang="scss" scoped></style>
