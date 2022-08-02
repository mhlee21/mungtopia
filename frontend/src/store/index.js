import { createStore } from 'vuex';
import adopt from '@/store/modules/adopt';
import game from '@/store/modules/game';

export default createStore({
	modules: { game, adopt },
});
