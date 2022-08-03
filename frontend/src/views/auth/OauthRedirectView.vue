<template>
	<div></div>
</template>

<script>
import { useStore } from 'vuex';
import { useRoute, useRouter } from 'vue-router';
export default {
	setup() {
		const store = useStore();
		const route = useRoute();
		const router = useRouter();

		const token = route.query.token;

		console.log('token', route.query);
		if (token) {
			store.commit('auth/setToken', token);
			store.dispatch('auth/fetchUser');
		}
		router.replace('/game');
		return {};
	},
};
</script>
