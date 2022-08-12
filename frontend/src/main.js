import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import { createVueKakaoSdk } from 'vue3-kakao-sdk';

const app = createApp(App);

app
	.use(store)
	.use(router)
	.use(createVueKakaoSdk('244b7c9102f092d2e098f89ada6f9cf6'))
	.mount('#app');
