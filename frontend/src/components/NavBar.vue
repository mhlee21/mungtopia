<template>
	<ul class="bottom-navigation">
		<!-- active 클래스 추가여부로 색상전환 가능 -->
		<li class="nav-item active" @click="clickNavBar('boardMain')">
			<img src="@/assets/img/nav-icon1.svg" class="gray" />
			<img src="@/assets/img/nav-icon1-on.svg" class="pink" />
		</li>
		<li class="nav-item" @click="clickNavBar('game')">
			<img src="@/assets/img/nav-icon2.svg" class="gray" />
			<img src="@/assets/img/nav-icon2-on.svg" class="pink" />
		</li>
		<li class="nav-item" @click="clickNavBar('boardCreate')">
			<img src="@/assets/img/nav-icon3.svg" class="gray" />
			<img src="@/assets/img/nav-icon3-on.svg" class="pink" />
		</li>
		<li class="nav-item" @click="clickNavBar('adopt')">
			<img src="@/assets/img/nav-icon4.svg" class="gray" />
			<img src="@/assets/img/nav-icon4-on.svg" class="pink" />
		</li>
		<li class="nav-item" @click="clickNavBar('profile')">
			<img src="@/assets/img/nav-icon5.svg" class="gray" />
			<img src="@/assets/img/nav-icon5-on.svg" class="pink" />
		</li>
	</ul>
</template>

<script>
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

export default {
	setup() {
		const router = useRouter();
		const store = useStore();
		const clickNavBar = name => {
			switch (name) {
				case 'boardMain':
				case 'game':
				case 'boardCreate':
					router.push({ name });
					break;
				case 'adopt':
				case 'profile':
					router.push({
						name,
						params: { userId: 1 },
						// params: { userId: store.getters['auth/user']['userId'] },
					});
					console.log(store.getters['auth/user']['userId']);
					break;
				default:
					break;
			}
		};
		return { clickNavBar };
	},
};
</script>

<style lang="scss" scoped></style>
