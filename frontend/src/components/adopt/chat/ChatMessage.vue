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
	padding: 0 1rem 3%;
	display: inline-block;
	font-size: 14 px;
	max-width: 60%;
}
.chat-message-me {
	background-color: #efefef;
}
</style>
