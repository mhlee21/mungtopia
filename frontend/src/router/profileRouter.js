import ProfileView from '@/views/profile/ProfileView';
export default [
	{
		path: '/profile/:userId',
		name: 'profile',
		component: ProfileView,
	},
];
