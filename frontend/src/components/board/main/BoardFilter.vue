<template>
	<div>
		<ul class="top-menu">
			<li class="menu-item">
				<a @click.prevent="clickAll()">
					<img :src="imagePath" class="pink" />
					<img src="@/assets/img/menu-icon1-on.svg" class="white" />
					<p>추천</p>
				</a>
			</li>
			<!-- active 클래스 추가 여부로 색상전환 가능 -->
			<li class="menu-item active">
				<a @click.prevent="clickAdopt()">
					<img src="@/assets/img/menu-icon2.svg" class="pink" />
					<img src="@/assets/img/menu-icon2-on.svg" class="white" />
					<p>입양</p>
				</a>
			</li>
			<li class="menu-item">
				<a @click.prevent="clickReview()">
					<img src="@/assets/img/menu-icon3.svg" class="pink" />
					<img src="@/assets/img/menu-icon3-on.svg" class="white" />
					<p>후기</p>
				</a>
			</li>
			<li class="menu-item">
				<a @click.prevent="clickFreeTalk()">
					<img src="@/assets/img/menu-icon4.svg" class="pink" />
					<img src="@/assets/img/menu-icon4-on.svg" class="white" />
					<p>잡담</p>
				</a>
			</li>
		</ul>
	</div>
</template>

<script>
export default {
	setup() {
		const clickAll = () => {};
		const clickAdopt = () => {};
		const clickReview = () => {};
		const clickFreeTalk = () => {};
		const imagePath = require('@/assets/img/menu-icon1.svg');

		return { imagePath, clickAll, clickAdopt, clickReview, clickFreeTalk };
	},
};
</script>

<style scoped>
.top-menu {
	display: flex;
	align-items: center;
	justify-content: space-between;
	width: 300px;
}
.top-menu .menu-item a {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 70px;
	height: 30px;
	box-sizing: border-box;
	border: 2px solid #ff9898;
	border-radius: 30px;
	cursor: pointer;
	background-color: #fff;
}
.top-menu .menu-item.active a {
	background-color: #ff9898;
}
.top-menu .menu-item p {
	color: #ff9898;
}
.top-menu .menu-item.active p {
	color: #fff;
}
.top-menu .menu-item img {
	margin-right: 4px;
}
.top-menu .menu-item img.pink {
	display: block;
}
.top-menu .menu-item img.white {
	display: none;
}
.top-menu .menu-item.active img.white {
	display: block;
}
.top-menu .menu-item.active img.pink {
	display: none;
}
</style>
