<template>
	<div class="protector-detail-view">
		<button @click="goBack">back</button>
		<ProtectorDetailComponent></ProtectorDetailComponent>
		<ApplicantList></ApplicantList>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';
import ProtectorDetailComponent from '@/components/adopt/detail/ProtectorDetailComponent';
import ApplicantList from '@/components/adopt/detail/ApplicantList';
export default {
	components: { ProtectorDetailComponent, ApplicantList },
	setup() {
		const store = useStore();
		const route = useRoute();
		const router = useRouter();

		store.dispatch('adopt/fetchProtectorDetail', route.params.boardId);
		const goBack = () => {
			router.go(-1);
		};
		return { goBack };
	},
};
</script>

<style scoped>
.protector-detail-view {
	padding: 10%;
	overflow: auto;
	height: 80%;
	background-color: #ff9898;
}
</style>
