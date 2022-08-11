<template>
	<div class="profile-pg">
		<div class="info-wrapper">
			<div class="info-component">
				<div class="image-wrapper">
					<img src="" alt="" class="profile-img" />
				</div>
				<div>사진 변경</div>
			</div>
			<div class="info-component">
				<div class="username">황희원</div>
				<div>
					<button class="info-btn">회원정보</button>
				</div>
			</div>
		</div>
		<div class="board-wrapper"></div>
		<div class="btn-wrapper">
			<button class="logout-btn">logout</button>
		</div>

		<NavBar></NavBar>
	</div>
</template>

<script>
import NavBar from '@/components/NavBar.vue';
export default {
	components: { NavBar },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
