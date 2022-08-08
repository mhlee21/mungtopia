<template>
	<div class="adopt-main-view">
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
		const clickApplicantTab = () => {
			store.dispatch('adopt/changeAdoptType', {
				adoptType: 'applicant',
				userSeq: route.params.userSeq,
			});
		};
		const clickProtectorTab = () => {
			store.dispatch('adopt/changeAdoptType', {
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

<style scoped>
.adopt-type-applicant-btn {
	background-color: #ffd8d8;
	border: none;
	border-radius: 1rem 1rem 0 0;
	width: 35%;
	height: 2rem;
	margin: 0 2% 0;
}
.adopt-type-protector-btn {
	background-color: #ffd8d8;
	border: none;
	border-radius: 1rem 1rem 0 0;
	width: 35%;
	height: 2rem;
	margin: 0 2% 0;
}
.adopt-type-active {
	background-color: white;
}
.adopt-main-view {
	padding: 10% 7%;
	overflow: auto;
	height: 80%;
	background-color: #ff9898;
}
</style>
