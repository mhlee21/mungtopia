<template>
	<div class="frame">
		<div class="title">입양신청서</div>
		<div class="Subframe">
			<ApplicationQuestion></ApplicationQuestion>
		</div>
	</div>
</template>

<script>
import ApplicationQuestion from '@/components/board/application/ApplicationQuestion.vue';
// import { computed } from '@vue/reactivity';

export default {
	components: {
		ApplicationQuestion,
	},
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped>
.frame {
	width: 350px;
	height: 640px;
	background-color: #ff9898;
	border: black solid 1px;
	border-radius: 1rem;
	position: relative;
}
.Subframe {
	width: 300px;
	background-color: #ffffff;
	border: black solid 1px;
	border-radius: 1rem;
	position: relative;
	text-align: center;
	margin: 20px 25px 20px 25px;
}
.title {
	text-align: center;
	margin: 20px;
	padding: 10px;
	font-size: 30px;
	color: #ffffff;
}
.content {
	text-align: center;
	margin: 10px;
	padding: 10px;
}

.game-btn {
	width: 90px;
	height: 50px;
	background-color: #0d6aba;
	display: flex;
	align-items: center;
	text-align: center;
	padding: 10px;
	margin: 5px auto;
	border-radius: 1rem;
}
.start-btn {
	width: 100%;
	color: #fffbf0;
	font-size: 20px;
	font-weight: bolder;
}
</style>
