import { createStore } from 'vuex';

// Vue.useAttrs(Vuex);

import game from '@/store/modules/game';

export default createStore({
	modules: { game },
});
