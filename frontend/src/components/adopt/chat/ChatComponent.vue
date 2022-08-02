<template>
	<div
		style="
			margin-top: 2vh;
			background-color: white;
			border-radius: 2rem;
			padding: 3% 1rem;
			height: 80%;
			overflow: auto;
		"
	>
		<div v-for="message in messages" :key="message.id">
			<div v-if="!isCurrentUser(message.user.value)">
				<div>
					{{ message.user.name }}
				</div>
				<div>{{ message.user.imageUrl }}</div>
			</div>
			<ChatMessage :message="message.content"></ChatMessage>
		</div>
	</div>
</template>

<script>
import { reactive } from 'vue';
import ChatMessage from '@/components/adopt/chat/ChatMessage';
export default {
	components: { ChatMessage },
	setup() {
		const messages = reactive([
			{
				id: 1,
				user: { name: '이연정', imageUrl: '' },
				content: '안녕하세요! 몽이 입양신청서 작성하신거 보고 연락드렸어요!!',
			},
			{
				id: 2,
				user: { name: '황희원', imageUrl: '' },
				content: '안녕하세요:)',
			},
			{
				id: 3,
				user: { name: '황희원', imageUrl: '' },
				content:
					'면담일정은 어떻게 되나요? 제가 내일은 발표가 있어서 어렵습니다.:)',
			},
		]);
		const currentUser = { name: '이연정', imageUrl: '' };
		const isCurrentUser = user => user === currentUser;
		console.log(messages[1].user.target);
		return { messages, currentUser, isCurrentUser };
	},
};
</script>

<style lang="scss" scoped></style>
