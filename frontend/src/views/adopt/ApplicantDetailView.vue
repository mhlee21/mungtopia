<template>
	<div class="applicant-detail-view">
		<button @click="goBack">back</button>
		<ApplicantDetailComponent></ApplicantDetailComponent>
		<AdoptDetailProcess></AdoptDetailProcess>
	</div>
</template>

<script>
import router from '@/router';
import { useStore } from 'vuex';
import { useRoute } from 'vue-router';
import ApplicantDetailComponent from '@/components/adopt/detail/ApplicantDetailComponent';
import AdoptDetailProcess from '@/components/adopt/detail/AdoptDetailProcess';

export default {
	components: { ApplicantDetailComponent, AdoptDetailProcess },
	setup() {
		const store = useStore();
		const route = useRoute();
		store.dispatch('adopt/fetchApplicantDetail', route.params.applicationId);
		store.dispatch(
			'adopt/fetchApplicantAdoptProcess',
			route.params.applicationId,
		);
		const goBack = () => {
			router.go(-1);
		};
		return { goBack };
	},
};
</script>

<style scoped>
.applicant-detail-view {
	padding: 10% 7%;
	overflow: auto;
	height: 80%;
	background-color: #ff9898;
}
</style>
