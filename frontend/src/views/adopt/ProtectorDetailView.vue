<template>
	<div>
		<button>back</button>
		<ProtectorDetailComponent></ProtectorDetailComponent>
		<ApplicantList></ApplicantList>
	</div>
</template>

<script>
import ProtectorDetailComponent from '@/components/adopt/detail/ProtectorDetailComponent';
import ApplicantList from '@/components/adopt/detail/ApplicantList';
export default {
	setup() {
		return {};
	},
	components: { ProtectorDetailComponent, ApplicantList },
};
</script>

<style lang="scss" scoped></style>
