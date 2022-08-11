<template>
	<div class="application-pg">
		<div class="application-wrapper">
			<div>
				<div class="title-wrapper">
					<i class="fa-solid fa-dog"></i>
					<div>입양신청서</div>
				</div>
				<hr />
			</div>
			<!-- 버튼 -->
			<div class="btn-wrapper">
				<!-- 개인정보 -->
				<button @click="showUserInfomation()" class="btn">신청자 정보</button>
				<!-- 입양신청서 -->
				<button @click="showApplication()" class="btn">입양신청서</button>
			</div>
		</div>
	</div>
</template>

<script>
import { computed } from '@vue/reactivity';
import { useRoute, useRouter } from 'vue-router';
import { useStore } from 'vuex';
// import { computed } from 'vue';
export default {
	setup() {
		const router = useRouter();
		const store = useStore();
		const route = useRoute();
		// const userSeq = computed(() => store.getters['auth/user']['userSeq']);
		// 개인정보 보기
		const showUserInfomation = () => {
			router.push({ name: '' });
		};
		const showApplication = () => {
			router.push({ name: '' });
		};

		const applicationId = computed(() => route.params.applicationId);
		store.dispatch('adopt/fetchApplication', applicationId);
		return { showUserInfomation, showApplication };
	},
};
</script>

<style lang="scss" scoped></style>
