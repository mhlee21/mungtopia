import AdoptMainView from '@/views/adopt/AdoptMainView';
import ApplicantDetailView from '@/views/adopt/ApplicantDetailView';
import ProtectorDetailView from '@/views/adopt/ProtectorDetailView';
import ApplicationDetailView from '@/views/adopt/ApplicationDetailView';
import ChatView from '@/views/adopt/ChatView';
import ScheduleView from '@/views/adopt/ScheduleView';
import MeetingView from '@/views/adopt/MeetingView';
import ApplicationView from '@/views/adopt/ApplicationView';

export default [
	{
		path: '/adopt/:userSeq',
		name: 'adopt',
		component: AdoptMainView,
	},
	{
		path: '/adopt/applicant/detail/:applicationId',
		name: 'applicantDetail',
		component: ApplicantDetailView,
	},
	{
		path: '/adopt/protector/detail/:boardId',
		name: 'protectorDetail',
		component: ProtectorDetailView,
	},
	{
		path: '/adopt/application/after/:applicationId',
		name: 'application',
		component: ApplicationView,
	},
	{
		path: '/adopt/detail/application/:applicationId',
		name: 'applicationDetail',
		component: ApplicationDetailView,
	},
	{
		path: '/adopt/chat/:chatRoomId',
		name: 'chat',
		component: ChatView,
	},
	{
		path: '/adopt/chat/schedule/:chatRoomId/',
		name: 'schedule',
		component: ScheduleView,
	},
	{
		path: '/adopt/meeting/:meetingRoomId',
		name: 'meeting',
		component: MeetingView,
	},
];
