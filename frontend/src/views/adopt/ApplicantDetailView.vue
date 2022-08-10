<template>
	<div class="adopt-detail-applicant-pg">
		<div class="back-button-wrapper">
			<i class="fa-solid fa-angle-left back-button" @click="goBack"></i>
		</div>
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

<style scoped></style>
