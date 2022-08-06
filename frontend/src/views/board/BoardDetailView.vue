<template>
	<div>
		<div class="main not-pd">
			<DetailBoard></DetailBoard>
		</div>
		<NavBar></NavBar>
	</div>
</template>

<script>
import DetailBoard from '@/components/board/detail/DetailBoard.vue';
import NavBar from '@/components/NavBar.vue';
export default {
	components: { DetailBoard, NavBar },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
