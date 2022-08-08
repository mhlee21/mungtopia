<template>
	<div>
		<form @submit.prevent="sendMessage()">
			<input type="text" v-model="message" />
			<button>보내기</button>
		</form>
	</div>
</template>

<script>
import { computed } from '@vue/reactivity';
import { ref } from 'vue';
import { useStore } from 'vuex';
export default {
	setup() {
		const store = useStore();
		const message = ref('');
		const userSeq = computed(() => store.getters['auth/user']['userSeq']);
		const sendMessage = () => {
			console.log(userSeq);
			const chat = {
				// userSeq,
				userSeq: 2,
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
