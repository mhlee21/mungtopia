<template>
	<div>
		<h3>신청자 <small>10</small></h3>
		<div v-for="applicant in applicantList" :key="applicant.id">
			<div
				style="display: flex; height: 10vh
						justify-content: center;
				background-color: white;
			border-radius: 1rem;
			padding: 7% 5% 2%;
			
			"
			>
				<div style="width: 20%">이미지</div>
				<div style="width: 50%">
					{{ applicant.name }}
				</div>
				<div style="width: 30%">채팅</div>
			</div>
			<AdoptDetailProcedure></AdoptDetailProcedure>
		</div>
	</div>
</template>

<script>
import AdoptDetailProcedure from '@/components/adopt/detail/AdoptDetailProcedure';
import { reactive } from 'vue';
export default {
	components: { AdoptDetailProcedure },
	setup() {
		const applicantList = reactive([{ id: 1, name: '황희원' }]);
		return { applicantList };
	},
};
</script>

<style lang="scss" scoped></style>
