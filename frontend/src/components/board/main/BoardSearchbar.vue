<template>
	<div>
		<div class="input-box">
			<form action="">
				<input type="text" placeholder="검색" :value="keyword" />
				<button @click.prevent="searchBoard()">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { ref } from 'vue';
export default {
	setup() {
		const store = useStore();
		const keyword = ref('');
		const searchBoard = () => {
			store.dispatch('board/searchBoard', { pageNo: 0, keyword });
		};
		return { keyword, searchBoard };
	},
};
</script>

<style lang="scss" scoped></style>
