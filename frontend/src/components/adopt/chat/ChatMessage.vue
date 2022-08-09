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
	border-radius: 0px 20px 20px 20px;
	padding: 0 1rem 3%;
	display: inline-block;
	font-size: 14px;
	max-width: 75%;
	padding: 10px;
}
.chat-message-me {
	background-color: #efefef;
	border-radius: 20px 20px 0px 20px;
}
</style>
