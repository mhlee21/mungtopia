import { createStore } from 'vuex';
import adopt from '@/store/modules/adopt';
import game from '@/store/modules/game';
import auth from '@/store/modules/auth';
import board from '@/store/modules/board';
import createPersistedState from 'vuex-persistedstate';

export default createStore({
	plugins: [
		createPersistedState({
			paths: ['auth'],
			storage: window.sessionStorage,
		}),
	],
	modules: { game, adopt, auth, board },
});
