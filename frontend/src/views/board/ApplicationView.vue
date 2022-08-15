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
				<button @click="updateUserInfomation()" class="btn">
					회원 정보 수정
				</button>
				<!-- 입양신청서 -->
				<button @click="createApplication()" class="btn">
					입양신청서 작성
				</button>
				<!-- 제출 -->
				<button @click="submitApplication()" class="submit-btn">제출</button>
			</div>
		</div>
	</div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { computed } from 'vue';
export default {
	setup() {
		const router = useRouter();
		const route = useRoute();
		const store = useStore();
		const userSeq = computed(() => store.getters['auth/user']['userSeq']);

		const boardId = computed(() => route.params.boardId);

		// 개인정보 보기
		const updateUserInfomation = () => {
			router.push({
				name: 'userInfo',
				params: { userSeq: userSeq.value },
			});
		};
		const createApplication = () => {
			router.push({ name: 'applicationCreateDetail' });
		};
		const submitApplication = () => {
			const answer = confirm(
				'회원정보와 입양신청서는 보호자에게 전달됩니다. 이후 수정이 불가능하오니 다시 한 번 확인 부탁드립니다. 입양 신청은 신중히 해주시길 바랍니다.',
			);
			if (answer) {
				store.dispatch('board/createApplication', boardId.value);
				router.push({
					name: 'boardDetail',
					params: { board: boardId.value },
				});
			}
		};
		return { updateUserInfomation, createApplication, submitApplication };
	},
};
</script>

<style lang="scss" scoped></style>
