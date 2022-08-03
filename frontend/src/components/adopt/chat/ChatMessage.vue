<template>
	<div class="chat-message" :class="{ 'chat-message-me': isMe }">
		{{ message }}
	</div>
</template>

<script>
export default {
	props: { message: String, isMe: Boolean },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped>
.chat-message {
	margin-top: 2vh;
	background-color: #ffd8d8;
	border-radius: 2rem;
	padding: 3% 1rem;
	display: inline-block;
	font-size: 12px;
}
.chat-message-me {
	background-color: #efefef;
}
</style>
