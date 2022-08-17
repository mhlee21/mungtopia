import GameMainView from '@/views/game/GameMainView';
// import KnowledgeGameView from '@/views/game/KnowledgeGameView';
import GameIntroView from '@/views/game/GameIntroView';
import KnowledgePlayView from '@/views/game/KnowledgePlayView';
import KnowledgeFinishView from '@/views/game/KnowledgeFinishView';
import MbtiFinishView from '@/views/game/MbtiFinishView';
import GamePlayView from '@/views/game/GamePlayView';
import GameResultView from '@/views/game/GameResultView';

export default [
	{
		path: '/game',
		name: 'game',
		component: GameMainView,
	},
	{
		path: '/game/:gameType/intro',
		name: 'gameIntro',
		component: GameIntroView,
	},
	{
		path: '/game/2/play',
		name: 'gamePlay',
		component: GamePlayView,
	},
	{
		path: '/game/2/result',
		name: 'gameResult',
		component: GameResultView,
	},
	// {
	// 	path: '/game/:gameType',
	// 	name: 'GameIntro',
	// 	component: KnowledgeGameView,
	// },
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
];
