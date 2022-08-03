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
		if (token) {
			store.dispatch('auth/saveToken', token);
			store.dispatch('auth/fetchUser');
		}
		router.replace('/board');
		return {};
	},
};
</script>
