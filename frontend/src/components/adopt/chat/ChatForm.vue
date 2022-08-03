<template>
	<div>
		<form @submit.prevent="sendMessage()">
			<input type="text" v-model="message" />
			<button>보내기</button>
		</form>
	</div>
</template>

<script>
import { ref } from 'vue';
import { useStore } from 'vuex';
export default {
	setup() {
		const store = useStore();
		const message = ref('');
		const sendMessage = () => {
			const chat = {
				userId: store.getters.auth.user.userId,
				time: Date.now(),
				message: message.value,
			};
			store.dispatch('adopt/sendChat', chat);
		};
		return { message, sendMessage };
	},
};
</script>

<style lang="scss" scoped></style>
