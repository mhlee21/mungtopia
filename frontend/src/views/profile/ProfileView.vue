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
					<a @click="changeProfile"><small>사진 변경</small></a>
				</div>
			</div>
			<div class="info-component">
				<div class="username">황희원</div>
				<button class="info-btn">회원정보</button>
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
		const changeProfile = () => {};
		return { changeProfile };
	},
};
</script>

<style lang="scss" scoped></style>
