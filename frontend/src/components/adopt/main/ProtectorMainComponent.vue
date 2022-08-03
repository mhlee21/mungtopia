<template>
	<div
		style="display: flex; align-items: center"
		@click="clickBoard(board.boardId)"
	>
		<img :src="board.dogImg" alt="dog image" class="board-dog-img" />
		<div class="board-dog-info">
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

<style scoped>
.board-dog-img {
	width: 35vw;
	height: 35vw;
	border-radius: 1rem;
	box-shadow: 1px 4px 3px rgba(0, 0, 0, 0.2);
}
.board-dog-info {
	background-color: white;
	width: 30vw;
	height: 15vh;
	border-radius: 0 1rem 1rem 0;
	box-shadow: 1px 4px 3px rgba(0, 0, 0, 0.2);
}
</style>
