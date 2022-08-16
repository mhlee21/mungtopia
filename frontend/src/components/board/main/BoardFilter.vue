<template>
	<div>
		<ul class="top-menu">
			<li
				class="menu-item"
				:class="{ active: tagNo === index }"
				v-for="(filter, index) in boardFilter"
				:key="index"
			>
				<a @click.prevent="clickFilter(index)">
					<img
						:src="require('@/assets/img/menu-icon' + (index + 1) + '.svg')"
						class="pink"
					/>
					<img
						:src="require('@/assets/img/menu-icon' + (index + 1) + '-on.svg')"
						class="white"
					/>
					<p>{{ filter.name }}</p>
				</a>
			</li>
		</ul>
	</div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
export default {
	setup() {
		const store = useStore();
		const tagNo = computed(() => store.getters['board/tagNo']);
		const boardFilter = [
			{ name: '전체' },
			{ name: '입양' },
			{ name: '후기' },
			{ name: '잡담' },
		];
		const clickFilter = num => {
			store.dispatch('board/fetchBoardList', {
				tagNo: num,
				pageNo: 0,
			});
		};

		return {
			tagNo,
			boardFilter,
			clickFilter,
		};
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
