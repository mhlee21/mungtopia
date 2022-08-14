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
		path: '/game/0/finish',
		name: 'GameFinish',
		component: KnowledgeFinishView,
	},
	{
		path: '/game/1/:mbtiResult',
		name: 'MbtiFinish',
		component: MbtiFinishView,
	},

	{
		path: '/game/2/finish',
		name: 'MatchFinish',
		component: MatchFinishView,
	},
];
