import LoginView from '@/views/auth/LoginView';
import OauthRedirectView from '@/views/auth/OauthRedirectView';
export default [
	{
		path: '/',
		name: 'login',
		component: LoginView,
	},
	{
		path: '/oauth/redirect',
		name: 'OauthRedrectView',
		component: OauthRedirectView,
	},
];
