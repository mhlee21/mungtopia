import ProfileView from '@/views/profile/ProfileView';
import UserInfoView from '@/views/profile/UserInfoView';
export default [
	{
		path: '/profile/:userSeq',
		name: 'profile',
		component: ProfileView,
	},
	{
		path: '/profile/information/:userSeq',
		name: 'userInfo',
		component: UserInfoView,
	},
];
