<template>
	<ul class="bottom-navigation">
		<!-- active 클래스 추가여부로 색상전환 가능 -->
		<li
			class="nav-item"
			@click="clickNavBar('boardMain')"
			:class="{ active: isCurrentPage('boardMain') }"
		>
			<img src="@/assets/img/nav-icon1.svg" class="gray" />
			<img src="@/assets/img/nav-icon1-on.svg" class="pink" />
		</li>
		<li
			class="nav-item"
			@click="clickNavBar('game')"
			:class="{ active: isCurrentPage('game') }"
		>
			<img src="@/assets/img/nav-icon2.svg" class="gray" />
			<img src="@/assets/img/nav-icon2-on.svg" class="pink" />
		</li>
		<li
			class="nav-item"
			@click="clickNavBar('boardCreate')"
			:class="{ active: isCurrentPage('boardCreate') }"
		>
			<img src="@/assets/img/nav-icon3.svg" class="gray" />
			<img src="@/assets/img/nav-icon3-on.svg" class="pink" />
		</li>
		<li
			class="nav-item"
			@click="clickNavBar('adopt')"
			:class="{ active: isCurrentPage('adopt') }"
		>
			<img src="@/assets/img/nav-icon4.svg" class="gray" />
			<img src="@/assets/img/nav-icon4-on.svg" class="pink" />
		</li>
		<li
			class="nav-item"
			@click="clickNavBar('profile')"
			:class="{ active: isCurrentPage('profile') }"
		>
			<img src="@/assets/img/nav-icon5.svg" class="gray" />
			<img src="@/assets/img/nav-icon5-on.svg" class="pink" />
		</li>
	</ul>
</template>

<script>
import { useRouter, useRoute } from 'vue-router';
import { useStore } from 'vuex';
import { computed } from 'vue';
export default {
	setup() {
		const router = useRouter();
		const route = useRoute();
		const store = useStore();

		const userSeq = computed(() => store.getters['auth/user']?.userSeq);
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
						params: { userSeq: userSeq.value },
					});
					break;
				default:
					break;
			}
		};

		const isCurrentPage = name => {
			return route.name == name;
		};
		return { clickNavBar, isCurrentPage };
	},
};
</script>

<style lang="scss" scoped></style>
