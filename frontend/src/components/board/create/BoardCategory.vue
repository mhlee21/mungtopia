<template>
	<div class="category-box">
		<label for="boardTag" class="category-label">카테고리</label>
		<select
			name="boardTag"
			id=""
			class="category-select"
			v-model="category"
			@change="selectCategory()"
		>
			<option :value="1">입양</option>
			<option :value="2">후기</option>
			<option :value="3">잡담</option>
		</select>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { ref } from 'vue';
export default {
	setup() {
		const store = useStore();
		const category = ref(1);
		const selectCategory = () => {
			store.dispatch('board/selectCategory', category.value);
		};
		return { category, selectCategory };
	},
};
</script>

<style lang="scss" scoped></style>
