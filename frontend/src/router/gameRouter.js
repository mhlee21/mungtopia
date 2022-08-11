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
		path: '/game/main/finish',
		name: 'KnowledgeFinish',
		component: KnowledgeFinishView,
	},
	{
		path: '/game/2/finish/:mbtiResult',
		name: 'MbtiFinish',
		component: MbtiFinishView,
	},

	{
		path: '/game/3/:matchResult',
		name: 'MatchFinish',
		component: MatchFinishView,
	},
];
