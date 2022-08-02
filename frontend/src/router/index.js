import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/login/LoginView';
import BoardView from '@/views/board/BoardView';
import AdoptMainView from '@/views/adopt/AdoptMainView';
import ApplicantDetailView from '@/views/adopt/ApplicantDetailView';
import ProtectorDetailView from '@/views/adopt/ProtectorDetailView';
import ChatView from '@/views/adopt/ChatView';
import MeetingView from '@/views/adopt/MeetingView';

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
	{
		path: '/adopt/:appicantId',
		name: 'adopt',
		component: AdoptMainView,
	},
	{
		path: '/adopt/applicant/:applicationId/detail',
		name: 'applicantDetail',
		component: ApplicantDetailView,
	},
	{
		path: '/adopt/protector/:boardId/detail',
		name: 'protectorDetail',
		component: ProtectorDetailView,
	},
	{
		path: '/adopt/chat/:chatRoomId',
		name: 'chat',
		component: ChatView,
	},
	{
		path: '/adopt/meeting/:meetingRoomId',
		name: 'meeting',
		component: MeetingView,
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
