import BoardMainView from '@/views/board/BoardMainView';
import BoardDetailView from '@/views/board/BoardDetailView';
import BoardCreateView from '@/views/board/BoardCreateView';
import ApplicationCreateView from '@/views/board/ApplicationCreateView';
export default [
	{
		path: '/board',
		name: 'boardMain',
		component: BoardMainView,
	},
	{
		path: '/board/detail/:boardId',
		name: 'boardDetail',
		component: BoardDetailView,
	},
	{
		path: '/board/new',
		name: 'boardCreate',
		component: BoardCreateView,
	},
	{
		// path: '/board/:boardId/application/create',
		path: '/board/application/create',
		name: 'applicationCreate',
		component: ApplicationCreateView,
	},
];
