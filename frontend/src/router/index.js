import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/login/LoginView';
import BoardView from '@/views/board/BoardView';
import adoptRouter from './adoptRouter';
import boardRouter from './boardRouter';
import loginRouter from './loginRouter';
import gameRouter from './gameRouter';
import profileRouter from './profileRouter';

const routes = [
	{
		path: '/',
		name: 'home',
		component: LoginView,
	},
	{
		path: '/board',
		name: 'board',
		component: BoardView,
	},
	...adoptRouter,
	...boardRouter,
	...loginRouter,
	...gameRouter,
	...profileRouter,
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
