<template>
	<div class="game-menu-pg">
		<div class="game-menu-wrapper">
			<!-- 게임 리스트 -->
			<div class="game-list">
				<div class="game-btn game-btn1" @click="gameEnter(2)">
					<div v-if="isClear[2]" class="img-wrapper">
						<img
							:src="require('@/assets/img/dog1_bone.png')"
							class="game-img"
						/>
						<div class="game-title">DONE</div>
					</div>
					<div v-else>
						<img
							:src="require('@/assets/img/dog1_no_bone.png')"
							class="game-img"
						/>
					</div>
					<div class="game-title">나와 잘맞는<br />반려견은?</div>
				</div>
				<div class="game-btn game-btn2" @click="gameEnter(1)">
					<div v-if="isClear[1]" class="img-wrapper">
						<img
							:src="require('@/assets/img/dog2_bone.png')"
							class="game-img"
						/>
						<div class="game-title">DONE</div>
					</div>
					<div v-else>
						<img
							:src="require('@/assets/img/dog2_no_bone.png')"
							class="game-img"
						/>
					</div>
					<div class="game-title">댕BTI</div>
				</div>
				<div class="game-btn game-btn3" @click="gameEnter(0)">
					<div v-if="isClear[0]" class="img-wrapper">
						<img
							:src="require('@/assets/img/dog3_bone.png')"
							class="game-img"
						/>
						<div class="game-title">DONE</div>
					</div>
					<div v-else>
						<img
							class="game-img"
							:src="require('@/assets/img/dog3_no_bone.png')"
						/>
					</div>
					<div class="game-title">강아지식<br />테스트</div>
				</div>
			</div>
		</div>
		<NavBar></NavBar>
		<!-- 네브바 -->
	</div>
</template>

<script>
import { useStore } from 'vuex';
import router from '@/router';
import NavBar from '@/components/NavBar.vue';
import { computed } from 'vue';

export default {
	components: {
		NavBar,
	},
	setup() {
		const store = useStore();
		const user = computed(() => store.getters['auth/user']);
		if (user.value != null) {
			store.dispatch('game/receiveClear');
		}
		const isClear = computed(() => store.getters['game/isClear']);
		console.log(isClear);
		const gameEnter = gameType => {
			store.dispatch('game/enterGame', gameType);
			router.push({
				name: 'gameIntro',
				params: { gameType: gameType },
			});
		};
		return { gameEnter, NavBar, isClear };
	},
};
</script>

<style lang="scss" scoped>
.game-list {
}
// .game-btn {
// 	width: 280px;
// 	height: 140px;
// 	background-color: #fffbf0;
// 	display: flex;
// 	align-items: center;
// 	text-align: center;
// 	padding: 10px;
// 	margin: 5px auto;
// }
.game-title {
	width: 70%;
	font-size: 100%;
}
// .frame {
// 	width: 350px;
// 	height: 640px;
// 	background-color: #ff9898;
// 	border: black solid 1px;
// 	border-radius: 1rem;
// 	position: relative;
// }
.frame {
	width: 100%;
	min-height: 100vh;
	background-color: #ff9898;
	position: relative;
	padding: 25px 30px 85px;
	box-sizing: border-box;
}
.game-img {
	width: 130px;
	height: 130px;
	border-radius: 1rem;
}
.stamp {
	width: 50px;
	height: 40px;
}
.title {
	text-align: center;
	margin: 0;
	padding: 10px;
	font-size: 200%;
}
.navbar {
	display: flex;
	justify-content: space-evenly;
	background-color: white;
	padding: 15px 0;
	position: absolute;
	bottom: 0;
	width: 100%;
	border-radius: 0 0 1rem 1rem;
}
.game-menu-pg {
	display: flex;
	justify-content: center;
}

.material-symbols-outlined {
	font-variation-settings: 'FILL' 0, 'wght' 200, 'GRAD' 0, 'opsz' 48;
	font-size: 50px !important;
}

.paws {
	border-radius: 50%;
	width: 50px !important;
	height: 50px !important;
}

.circle {
	margin: 0 auto;
	width: 50px;
	height: 45px;
	border: 3px solid #000;
	border-radius: 50%;
}
</style>
