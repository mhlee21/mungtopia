import BoardMainView from '@/views/board/BoardMainView';
import BoardDetailView from '@/views/board/BoardDetailView';
import BoardCreateView from '@/views/board/BoardCreateView';
import ApplicationCreateDetailView from '@/views/board/ApplicationCreateDetailView';
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
		path: '/board/application/detail/:boardId',
		name: 'applicationCreateDetail',
		component: ApplicationCreateDetailView,
	},
];
