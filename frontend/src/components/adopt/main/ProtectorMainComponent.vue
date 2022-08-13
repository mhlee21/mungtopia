<template>
	<div @click="clickBoard(board.boardId)" class="main-component">
		<img :src="board.dogImg" alt="dog image" class="dog-img" />
		<div class="dog-info">
			<h4 style="margin: 20% 10%">
				{{ board.dogName }}
			</h4>
		</div>
	</div>
</template>

<script>
import { useRouter } from 'vue-router';
export default {
	props: { board: Object },
	setup() {
		const router = useRouter();
		const clickBoard = boardId => {
			router.push({
				name: 'protectorDetail',
				params: { boardId: boardId },
			});
		};
		return { clickBoard };
	},
};
</script>

<style scoped></style>
