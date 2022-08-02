import GameMainView from '@/views/game/GameMainView';
import KnowledgeGameView from '@/views/game/KnowledgeGameView';
import KnowledgePlayView from '@/views/game/KnowledgePlayView';
import KnowledgeFinishView from '@/views/game/KnowledgeFinishView';
import KnowledgeFailView from '@/views/game/KnowledgeFailView';
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
];
