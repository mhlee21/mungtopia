import GameMainView from '@/views/game/GameMainView';
import GameIntroView from '@/views/game/GameIntroView';
import Game1PlayView from '@/views/game/Game1PlayView';
import Game2PlayView from '@/views/game/Game2PlayView';
import Game3PlayView from '@/views/game/Game3PlayView';
import Game1ResultView from '@/views/game/Game1ResultView';
import Game2ResultView from '@/views/game/Game2ResultView';
import Game3ResultView from '@/views/game/Game3ResultView';

export default [
	{
		path: '/game',
		name: 'game',
		component: GameMainView,
	},
	{
		path: '/game/:gameTag/intro',
		name: 'gameIntro',
		component: GameIntroView,
	},
	{
		path: '/game/1/play',
		name: 'game1Play',
		component: Game1PlayView,
	},
	{
		path: '/game/2/play',
		name: 'game2Play',
		component: Game2PlayView,
	},
	{
		path: '/game/3/play',
		name: 'game3Play',
		component: Game3PlayView,
	},
	{
		path: '/game/1/result/:score',
		name: 'game1Result',
		component: Game1ResultView,
	},
	{
		path: '/game/2/result/:mbti',
		name: 'game2Result',
		component: Game2ResultView,
	},
	{
		path: '/game/3/result',
		name: 'game3Result',
		component: Game3ResultView,
	},
];
