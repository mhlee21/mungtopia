<template>
	<div class="adopt-main-pg">
		<!-- 입양하기 입양보내기 버튼 -->
		<div style="display: flex; justify-content: center">
			<button
				class="adopt-type-applicant-btn"
				:class="{ 'adopt-type-active': isApplicant }"
				@click="clickApplicantTab"
			>
				입양하기
			</button>
			<button
				class="adopt-type-protector-btn"
				:class="{ 'adopt-type-active': !isApplicant }"
				@click="clickProtectorTab"
			>
				입양보내기
			</button>
		</div>
		<AdoptStateComponent></AdoptStateComponent>
		<NavBar></NavBar>
	</div>
</template>

<script>
import NavBar from '@/components/NavBar.vue';
import AdoptStateComponent from '@/components/adopt/main/AdoptStateComponent';
import { computed } from 'vue';
import { useStore } from 'vuex';
import { useRoute } from 'vue-router';
export default {
	components: { NavBar, AdoptStateComponent },
	setup() {
		const store = useStore();
		const route = useRoute();
		store.dispatch('adopt/fetchMainList', {
			userSeq: route.params.userSeq,
			adoptType: 'applicant',
		});
		const clickApplicantTab = () => {
			store.dispatch('adopt/fetchMainList', {
				adoptType: 'applicant',
				userSeq: route.params.userSeq,
			});
		};
		const clickProtectorTab = () => {
			store.dispatch('adopt/fetchMainList', {
				adoptType: 'protector',
				userSeq: route.params.userSeq,
			});
		};

		return {
			isApplicant: computed(() => store.getters['adopt/isApplicant']),
			clickApplicantTab,
			clickProtectorTab,
		};
	},
};
</script>

<style scoped></style>
