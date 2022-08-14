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
import ApplicantDetailComponent from '@/components/adopt/detail/ApplicantDetailComponent';
import AdoptDetailProcess from '@/components/adopt/detail/AdoptDetailProcess';
import { useRoute, useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { computed } from 'vue';
export default {
	components: { ApplicantDetailComponent, AdoptDetailProcess },
	setup() {
		const store = useStore();
		const route = useRoute();
		const router = useRouter();
		const userSeq = computed(() => store.getters['auth/user']?.userSeq);
		store.dispatch('adopt/fetchApplicantDetail', route.params.applicationId);
		store.dispatch(
			'adopt/fetchApplicantAdoptProcess',
			route.params.applicationId,
		);
		const goBack = () => {
			router.push({
				name: 'adopt',
				params: { userSeq: userSeq.value },
			});
		};
		return { goBack };
	},
};
</script>

<style scoped></style>
