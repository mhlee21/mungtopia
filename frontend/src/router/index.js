import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/login/LoginView';
import BoardView from '@/views/board/BoardView';
import GameMainView from '@/views/game/GameMainView';
import KnowledgeGameView from '@/views/game/KnowledgeGameView';
import KnowledgePlayView from '@/views/game/KnowledgePlayView';
import KnowledgeFinishView from '@/views/game/KnowledgeFinishView';
import KnowledgeFailView from '@/views/game/KnowledgeFailView';
import MbtiPlayView from '@/views/game/MbtiPlayView';
import MbtiFinishView from '@/views/game/MbtiFinishView';
import MatchPlayView from '@/views/game/MatchPlayView';
import MatchFinishView from '@/views/game/MatchFinishView';
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
		path: '/board',
		name: 'board',
		component: BoardView,
	},
	{
		path: '/game',
		name: 'game',
		component: GameMainView,
	},
	{
		path: '/game/main',
		name: 'KnowledgeGame',
		component: KnowledgeGameView,
	},
	{
		path: '/game/main/play',
		name: 'KnowledgePlay',
		component: KnowledgePlayView,
	},
	{
		path: '/game/1/finish',
		name: 'KnowledgeFinish',
		component: KnowledgeFinishView,
	},
	{
		path: '/game/1/fail',
		name: 'KnowledgeFail',
		component: KnowledgeFailView,
	},
	{
		path: '/game/2/play',
		name: 'MbtiPlay',
		component: MbtiPlayView,
	},
	{
		path: '/game/2/finish',
		name: 'MbtiFinish',
		component: MbtiFinishView,
	},

	{
		path: '/game/4/play',
		name: 'MatchPlay',
		component: MatchPlayView,
	},
	{
		path: '/game/4/finish',
		name: 'MatchFinish',
		component: MatchFinishView,
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
