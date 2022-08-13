<template>
	<div class="adopt-detail-protector-pg">
		<div class="back-button-wrapper">
			<i class="fa-solid fa-angle-left back-button" @click="goBack"></i>
		</div>
		<ProtectorDetailComponent></ProtectorDetailComponent>
		<ApplicantList></ApplicantList>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { useRouter, useRoute } from 'vue-router';
import ProtectorDetailComponent from '@/components/adopt/detail/ProtectorDetailComponent';
import ApplicantList from '@/components/adopt/detail/ApplicantList';
import { computed } from 'vue';
export default {
	components: { ProtectorDetailComponent, ApplicantList },
	setup() {
		const store = useStore();
		const route = useRoute();
		const router = useRouter();
		const userSeq = computed(() => store.getters['auth/user']?.userSeq);
		store.dispatch('adopt/fetchProtectorDetail', route.params.boardId);
		console.log('30줄', userSeq);
		const goBack = () => {
			router.push({
				name: 'adopt',
				params: { userSeq: 6 },
			});
		};
		return { goBack };
	},
};
</script>

<style scoped>
.protector-detail-view {
	padding: 10% 7%;
	overflow: auto;
	height: 80%;
	background-color: #ff9898;
}
</style>
