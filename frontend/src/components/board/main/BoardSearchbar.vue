<template>
	<div>
		<div class="input-box">
			<form @submit.prevent="searchBoard()">
				<input type="text" placeholder="검색" v-model="newKeyword" />
				<button>
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { ref, watch } from 'vue';
import { computed } from '@vue/reactivity';
export default {
	setup() {
		const store = useStore();
		const keyword = computed(() => store.getters['board/keyword']);
		//keyword 값이 변할 때마다 newKeyword 값에 할당
		const newKeyword = ref(keyword.value);
		watch(keyword, () => {
			newKeyword.value = keyword.value;
		});

		// 상세글 페이지에서 뒤로가기 하는 경우 검색 페이지 다시 보여주기 위해
		if (newKeyword.value.length) {
			store.dispatch('board/searchBoard', {
				pageNo: 0,
				keyword: newKeyword.value,
			});
		}

		// 키워드 검색
		const searchBoard = () => {
			store.dispatch('board/searchBoard', {
				pageNo: 0,
				keyword: newKeyword.value,
			});
			store.commit('board/SET_KEYWORD', newKeyword.value);
		};
		return { keyword, newKeyword, searchBoard };
	},
};
</script>

<style lang="scss" scoped></style>
