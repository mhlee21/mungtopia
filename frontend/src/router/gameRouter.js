import GameMainView from '@/views/game/GameMainView';
import KnowledgeGameView from '@/views/game/KnowledgeGameView';
import KnowledgePlayView from '@/views/game/KnowledgePlayView';
import KnowledgeFinishView from '@/views/game/KnowledgeFinishView';
import MbtiFinishView from '@/views/game/MbtiFinishView';
import MatchFinishView from '@/views/game/MatchFinishView';

export default [
	{
		path: '/game',
		name: 'game',
		component: GameMainView,
	},
	{
		path: '/game/:gameType',
		name: 'GameIntro',
		component: KnowledgeGameView,
	},
	{
		path: '/game/:gameType/play',
		name: 'GamePlay',
		component: KnowledgePlayView,
	},
	{
		path: '/game/:gameType/finish',
		name: 'GameFinish',
		component: KnowledgeFinishView,
	},
	{
<<<<<<< HEAD
		path: '/game/1/play',
		name: 'MbtiPlay',
		component: MbtiPlayView,
	},
	{
		path: '/game/1/:mbtiResult',
=======
		path: '/game/2/finish/:mbtiResult',
>>>>>>> develop
		name: 'MbtiFinish',
		component: MbtiFinishView,
	},

	{
<<<<<<< HEAD
		path: '/game/2/play',
		name: 'MatchPlay',
		component: MatchPlayView,
	},
	{
		path: '/game/2/:matchResult',
=======
		path: '/game/3/:matchResult',
>>>>>>> develop
		name: 'MatchFinish',
		component: MatchFinishView,
	},
];
