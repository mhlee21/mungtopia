<template>
	<div class="dog-detail-wrap">
		<ol v-show="isAdoption" class="dog-detail-ol">
			<li class="dog-detail-li">
				<p class="tit">나이</p>
				<p class="txt">{{ age }}</p>
			</li>
			<li class="dog-detail-li">
				<p class="tit">몸무게</p>
				<p class="txt">{{ weight }}</p>
			</li>
			<li class="dog-detail-li">
				<p class="tit">성별</p>
				<p class="txt">{{ gender }}</p>
			</li>
			<li class="dog-detail-li">
				<p class="tit">접종여부</p>
				<p class="txt">{{ neutering }}</p>
			</li>
			<!-- <div class="dog-detail-li">
				<p class="tit">성격</p>
				<div class="character-box">
					<p
						class="txt character"
						v-for="(nature, index) in dogNature"
						:key="index"
					>
						{{ nature }}
					</p>
				</div>
			</div> -->
		</ol>
		<div class="textbox">
			<p>
				{{ contents }}
			</p>
		</div>
	</div>
</template>

<script>
import { computed, ref } from 'vue';
import { useStore } from 'vuex';
export default {
	setup() {
		const store = useStore();
		const isAdoption = ref(true);
		const age = computed(() => store.getters['board/board']['dogInfo']?.age);
		const weight = computed(
			() => store.getters['board/board']['dogInfo']?.weight,
		);
		const neutering = computed(() =>
			store.getters['board/board']['dogInfo']?.neutering === true ? 'Y' : 'N',
		);
		const gender = computed(
			() => store.getters['board/board']['dogInfo']?.gender,
		);
		const dogNature = computed(() => store.getters['board/board']['dogNature']);
		const contents = computed(() => store.getters['board/board']['contents']);
		const boardTag = computed(() => store.getters['board/board']['boardTag']);
		console.log('boardTag : ' + boardTag.value);
		if (boardTag.value != '입양') {
			isAdoption.value = false;
		}
		return {
			age,
			weight,
			neutering,
			gender,
			dogNature,
			contents,
			boardTag,
			isAdoption,
		};
	},
};
</script>

<style lang="scss" scoped></style>
