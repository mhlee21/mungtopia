<template>
	<div class="profile-pg">
		<div class="info-wrapper">
			<div class="info-component">
				<div class="image-wrapper">
					<img :src="user.profile" alt="profile image" class="profile-img" />
				</div>
				<div>
					<!-- <a @click.prevent="changeProfile" -->
					<label for="profile" class="change-btn">
						<small>사진 변경</small>
					</label>
					<input
						type="file"
						name="profile"
						id="profile"
						class="change-input"
						@change="changeProfile"
					/>
				</div>
			</div>
			<div class="info-component">
				<div class="username-wrapper">
					<div>
						<div v-show="isClicked" class="username">
							{{ newNickName }}
						</div>
						<div v-show="!isClicked">
							<input
								placeholder="user.username"
								v-model="newNickName"
								@change="changeNickname"
							/>
						</div>
					</div>
					<div class="change-username">
						<button
							v-show="isClicked"
							class="info-btn"
							@click="changeBtnStatus"
						>
							수정
						</button>
						<button
							v-show="!isClicked"
							class="info-btn"
							@click="sendChangeNickname"
						>
							확인
						</button>
					</div>
				</div>
				<button class="info-btn" @click="goToUserInfo()">회원정보</button>
			</div>
		</div>
		<div class="board-wrapper">
			<div class="board-btn-wrapper">
				<button
					class="board-btn"
					:class="{ 'board-btn-active': boardType === 'board' }"
					@click="changeBoardType('board')"
				>
					<i class="fa-solid fa-pen"></i>
				</button>
				<button
					class="board-btn"
					:class="{ 'board-btn-active': boardType === 'like' }"
					@click="changeBoardType('like')"
				>
					<i class="fa-solid fa-heart"></i>
				</button>
				<button
					class="board-btn"
					:class="{ 'board-btn-active': boardType === 'star' }"
					@click="changeBoardType('star')"
				>
					<i class="fa-solid fa-star"></i>
				</button>
			</div>
			<div class="board-content-wrapper">
				<div
					class="board-content"
					v-for="board in curBoardList"
					:key="board.id"
					@click="clickBoard(board.id)"
				>
					<img :src="board.imgUrl" alt="board" />
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
import { computed, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

export default {
	components: { NavBar },
	setup() {
		const router = useRouter();
		const store = useStore();
		const user = computed(() => store.getters['auth/userInfo']);
		const newNickName = ref(user.value.username);
		const isClicked = ref(true);
		// const newProfile = ref(user.value.profile);
		const formData = new FormData();
		store.dispatch('profile/fetchBoardList');
		store.dispatch('profile/getUserInfo');
		const boardType = computed(() => store.getters['profile/boardType']);

		const curBoardList = computed(() => {
			if (boardType?.value === 'board') {
				return store.getters['profile/boardList'];
			} else if (boardType?.value === 'star') {
				return store.getters['profile/starList'];
			} else {
				return store.getters['profile/likeList'];
			}
		});

		const changeBoardType = type => {
			store.dispatch('profile/setBoardType', type);
		};

		const goToUserInfo = () => {
			router.push({
				name: 'userInfo',
				params: { userSeq: store.getters['auth/user']?.userSeq },
			});
		};
		const clickBoard = boardId => {
			router.push({
				name: 'boardDetail',
				params: { boardId: boardId },
			});
		};

		// 닉네임 수정 input 값 CSS 처리
		const changeBtnStatus = () => {
			isClicked.value = false;
		};
		const sendChangeNickname = () => {
			isClicked.value = true;
			const payload = {
				nickname: newNickName.value,
			};
			store.dispatch('profile/updateUserNickname', payload);
		};
		const changeProfile = e => {
			if (e.target.files.length > 1) {
				alert('프로필 사진은 한장만 선택해주세요!');
			} else {
				// let data = URL.createObjectURL(e.target.files);
				formData.append('files', e.target.files[0]);
				store.dispatch('profile/updateUserProfile', formData);
			}
		};
		const logout = () => {
			store.dispatch('auth/logout');
			router.push({ name: 'login' });
		};
		return {
			user,
			changeBoardType,
			changeProfile,
			boardType,
			logout,
			goToUserInfo,
			curBoardList,
			clickBoard,
			changeBtnStatus,
			newNickName,
			isClicked,
			sendChangeNickname,
		};
	},
};
</script>

<style lang="scss" scoped></style>
