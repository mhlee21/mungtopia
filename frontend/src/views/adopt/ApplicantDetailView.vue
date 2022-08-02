<template>
	<div>
		<button @click="goAdoptMain">back</button>
		<ApplicantDetailComponent></ApplicantDetailComponent>
		<AdoptDetailProcedure></AdoptDetailProcedure>
	</div>
</template>

<script>
import router from '@/router';
import ApplicantDetailComponent from '@/components/adopt/detail/ApplicantDetailComponent';
import AdoptDetailProcedure from '@/components/adopt/detail/AdoptDetailProcedure';

export default {
	setup() {
		const goAdoptMain = () => {
			router.push({
				name: 'adopt',
				// params: { appicantId },
			});
		};
		return { goAdoptMain };
	},
	components: { ApplicantDetailComponent, AdoptDetailProcedure },
};
</script>

<style lang="scss" scoped></style>
