<template>
	<div class="profile-pg">
		<div class="info-wrapper">
			<div class="info-component">
				<div class="image-wrapper">
					<img
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4724WArwLuLX-JSFv4mC5PUm_hOuVh0ZiwQ&usqp=CAU"
						alt=""
						class="profile-img"
					/>
				</div>
				<div>
					<a @click.prevent="changeProfile" class="change-btn"
						><small>사진 변경</small></a
					>
				</div>
			</div>
			<div class="info-component">
				<div class="username">황희원</div>
				<button class="info-btn" @click="goToUserInfo()">회원정보</button>
			</div>
		</div>
		<div class="board-wrapper">
			<div class="board-btn-wrapper">
				<button
					class="board-btn"
					:class="{ 'board-btn-active': boardType === 'write' }"
					@click="changeBoardType('write')"
				>
					작성한 글
				</button>
				<button
					class="board-btn"
					:class="{ 'board-btn-active': boardType === 'like' }"
					@click="changeBoardType('like')"
				>
					좋아요한 글
				</button>
				<button
					class="board-btn"
					:class="{ 'board-btn-active': boardType === 'save' }"
					@click="changeBoardType('save')"
				>
					저장한 글
				</button>
			</div>
			<div class="board-content-wrapper">
				<div
					class="board-content"
					v-for="(i, index) in [1, 2, 3, 4]"
					:key="index"
				>
					<img src="" alt="" />
				</div>
			</div>
		</div>
		<div class="btn-wrapper">
			<button class="logout-btn" @click="logout">logout</button>
		</div>
		<NavBar></NavBar>
	</div>
</template>

<script>
import NavBar from '@/components/NavBar.vue';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
export default {
	components: { NavBar },
	setup() {
		const router = useRouter();
		const boardType = ref('write');
		const store = useStore;
		const changeBoardType = type => {
			boardType.value = type;
		};
		const goToUserInfo = () => {
			console.log('userSeq 수정');
			router.push({
				name: 'userInfo',
				params: { userSeq: 1 || store.getters['auth/user']?.userSeq },
			});
		};
		const changeProfile = () => {};
		const logout = () => {};
		return { changeBoardType, changeProfile, boardType, logout, goToUserInfo };
	},
};
</script>

<style lang="scss" scoped></style>
