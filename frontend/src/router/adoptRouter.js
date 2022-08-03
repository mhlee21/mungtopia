import AdoptMainView from '@/views/adopt/AdoptMainView';
import ApplicantDetailView from '@/views/adopt/ApplicantDetailView';
import ProtectorDetailView from '@/views/adopt/ProtectorDetailView';
import ChatView from '@/views/adopt/ChatView';
import MeetingView from '@/views/adopt/MeetingView';

export default [
	{
		path: '/adopt/:userId',
		name: 'adopt',
		component: AdoptMainView,
	},
	{
		path: '/adopt/applicant/:applicationId/detail',
		name: 'applicantDetail',
		component: ApplicantDetailView,
	},
	{
		path: '/adopt/protector/:boardId/detail',
		name: 'protectorDetail',
		component: ProtectorDetailView,
	},
	{
		path: '/adopt/chat/:chatRoomId',
		name: 'chat',
		component: ChatView,
	},
	{
		path: '/adopt/meeting/:meetingRoomId',
		name: 'meeting',
		component: MeetingView,
	},
];
