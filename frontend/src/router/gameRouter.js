import GameMainView from '@/views/game/GameMainView';
import KnowledgeGameView from '@/views/game/KnowledgeGameView';
import KnowledgePlayView from '@/views/game/KnowledgePlayView';
import KnowledgeFinishView from '@/views/game/KnowledgeFinishView';
import MbtiPlayView from '@/views/game/MbtiPlayView';
import MbtiFinishView from '@/views/game/MbtiFinishView';
import MatchPlayView from '@/views/game/MatchPlayView';
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
		path: '/game/1/play',
		name: 'MbtiPlay',
		component: MbtiPlayView,
	},
	{
		path: '/game/1/:mbtiResult',
		name: 'MbtiFinish',
		component: MbtiFinishView,
	},

	{
		path: '/game/2/play',
		name: 'MatchPlay',
		component: MatchPlayView,
	},
	{
		path: '/game/2/:matchResult',
		name: 'MatchFinish',
		component: MatchFinishView,
	},
];
