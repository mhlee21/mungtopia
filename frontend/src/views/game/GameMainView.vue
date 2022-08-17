<template>
	<div class="game-menu-pg">
		<div class="game-menu-wrapper">
			<!-- 게임 리스트 -->
			<div class="game-list">
				<!-- 나와 잘맞는 반려견은? -->
				<div class="game-btn game-btn1" @click="gameEnter(3)">
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
				<!-- 댕BTI -->
				<div class="game-btn game-btn2" @click="gameEnter(2)">
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
				<!-- 강아지식 테스트 -->
				<div
					class="game-btn game-btn3"
					@click="gameEnter(1)"
					style="position: relative"
				>
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
					<a
						href="https://www.flaticon.com/kr/free-icons/"
						title="개 아이콘"
						style="position: absolute; bottom: 0; right: 0; color: slategray"
						>개 아이콘 제작자: Freepik - Flaticon</a
					>
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

		const gameEnter = gameTag => {
			router.push({
				name: 'gameIntro',
				params: { gameTag: gameTag },
			});
		};
		return { gameEnter, NavBar, isClear };
	},
};
</script>

<style lang="scss" scoped></style>
