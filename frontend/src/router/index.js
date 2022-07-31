import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/login/LoginView';
import BoardView from '@/views/board/BoardView';

const routes = [
	{
		path: '/',
		name: 'home',
		component: LoginView,
	},
	{
		path: '/',
		name: 'board',
		component: BoardView,
	},
	// {
	// 	path: '/about',
	// 	name: 'about',
	// 	// route level code-splitting
	// 	// this generates a separate chunk (about.[hash].js) for this route
	// 	// which is lazy-loaded when the route is visited.
	// 	component: () =>
	// 		import(/* webpackChunkName: "about" */ '../views/AboutView.vue'),
	// },
];

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes,
});

export default router;
