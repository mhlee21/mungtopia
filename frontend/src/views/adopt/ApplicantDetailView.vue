<template>
	<div>
		<button>back</button>
		<ApplicantDetailComponent></ApplicantDetailComponent>
		<AdoptDetailProcedure></AdoptDetailProcedure>
	</div>
</template>

<script>
import ApplicantDetailComponent from '@/components/adopt/detail/ApplicantDetailComponent';
import AdoptDetailProcedure from '@/components/adopt/detail/AdoptDetailProcedure';

export default {
	setup() {
		return {};
	},
	components: { ApplicantDetailComponent, AdoptDetailProcedure },
};
</script>

<style lang="scss" scoped></style>
