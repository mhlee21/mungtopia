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
			const date = new Date(Date.now());
			const chat = {
				userSeq: userSeq,
				createtime: date,
				content: message.value,
				isRequest: false,
				chatRoomId: 4, //chatRoomId?
			};
			store.dispatch('adopt/sendChat', chat);
		};
		return { message, sendMessage };
	},
};
</script>

<style lang="scss" scoped></style>
