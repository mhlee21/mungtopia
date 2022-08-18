import { createRouter, createWebHistory } from 'vue-router';
import adoptRouter from './adoptRouter';
import boardRouter from './boardRouter';
import authRouter from './authRouter';
import gameRouter from './gameRouter';
import profileRouter from './profileRouter';
// import NotFound404 from '@/views/NotFound404.vue';
import store from '@/store';

const routes = [
	...adoptRouter,
	...boardRouter,
	...authRouter,
	...gameRouter,
	...profileRouter,
	// {
	// 	path: '/404',
	// 	name: 'NotFound404',
	// 	component: NotFound404,
	// },
	// {
	// 	path: '*',
	// 	redirect: '/404',
	// },
];
const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes,
});

router.beforeEach((to, from, next) => {
	const isLoggedIn = store.getters['auth/isLoggedIn'];

	const noAuthPages = [
		'login',
		'OauthRedrectView',
		'boardMain',
		'boardDetail',
		'game',
		'gameIntro',
		'game1Play',
		'game2Play',
		'game3Play',
		'game1Result',
		'game2Result',
		'game3Result',
		'NotFound404',
	];

	const isAuthRequired = !noAuthPages.includes(to.name);

	// 로그인되어 있지 않고, 권한이 필요한 요청을 했다면
	if (isAuthRequired && !isLoggedIn) {
		alert('로그인이 필요합니다');
		next({ name: 'login' });
	} else {
		next();
	}
});

export default router;
