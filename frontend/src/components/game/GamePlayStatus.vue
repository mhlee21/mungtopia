<template>
	<div class="progressBar">
		<div id="bar" class="innerbar" :style="{ width: statusWidth + '%' }"></div>
	</div>
</template>
<script>
import { useStore } from 'vuex';
import { computed } from 'vue';
export default {
	setup() {
		const store = useStore();
		// const questionNumber = computed(() => store.getters['game/questionNumber']);
		// const gameType = computed(() => store.getters['game/gameType']);
		const statusWidth = computed(() => store.getters['game/statusWidth']);
		// let el = document.getElementById('bar');

		// let widthStr = statusWidth.value + '%';
		// console.log(widthStr);
		// el.style.width = widthStr;
		return { statusWidth };
	},
};
</script>

<style lang="scss" scoped>
.progressBar {
	width: 90%;
	height: 15px;
	align-items: center;
	border-radius: 3px;
	background: linear-gradient(#6fa6d66c, #7db1df54);
}

.innerbar {
	height: 100%;
	text-align: right;
	height: 15px; /* same as #progressBar height if we want text middle aligned */
	width: 0%;
	border-radius: 3px;
	background: linear-gradient(#5be6ba, #5ed1ad);
}
</style>
