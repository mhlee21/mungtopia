import ProfileView from '@/views/profile/ProfileView';
export default [
	{
		path: '/profile/:userSeq',
		name: 'profile',
		component: ProfileView,
	},
];
