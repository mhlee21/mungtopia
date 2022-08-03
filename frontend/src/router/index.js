import { createRouter, createWebHistory } from 'vue-router';
import adoptRouter from './adoptRouter';
import boardRouter from './boardRouter';
import authRouter from './authRouter';
import gameRouter from './gameRouter';
import profileRouter from './profileRouter';

const routes = [
	...adoptRouter,
	...boardRouter,
	...authRouter,
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
