<template>
	<div class="tit-box">
		<div class="brief-info-box">
			<h2 class="dog-name">{{ dogName }}</h2>
			<ol class="hashtag-box">
				<li class="hashtag">{{ areaSido }}</li>
				<li class="hashtag">{{ breed }}</li>
			</ol>
		</div>
		<!-- 입양신청한 강아지가 있다면 disabled -->
		<!-- 입양글이 아니라면 안보임 -->
		<a
			@click.prevent="adoptionApply()"
			:class="{ 'is-adopting': isAdopting }"
			class="brief-btn"
			>입양 신청</a
		>
	</div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import { useRoute, useRouter } from 'vue-router';
export default {
	setup() {
		const store = useStore();
		const route = useRoute();
		const router = useRouter();
		const dogName = computed(() => store.getters['board/board']['dogName']);
		const isAdopting = computed(() => store.getters['board/isAdopting']);
		const areaSido = computed(
			() => store.getters['board/board']['dogInfo']['areaSido'],
		);
		const breed = computed(
			() => store.getters['board/board']['dogInfo']['breed'],
		);
		const boardId = computed(() => route.params.boardId);
		console.log(boardId.value);

		const adoptionApply = () => {
			if (!isAdopting.value) {
				router.push({
					name: 'applicationCreateDetail',
					params: { boardId: boardId.value },
				});
			}
		};
		return { dogName, areaSido, breed, isAdopting, adoptionApply };
	},
};
</script>

<style lang="scss" scoped>
.brief-btn {
	cursor: pointer;
}
.is-adopting {
	background-color: #e5e5e5 !important;
	color: #b7b7b7 !important;
	cursor: default;
}
</style>
