import { createStore } from 'vuex';
import adopt from '@/store/modules/adopt';

// Vue.useAttrs(Vuex);

import game from '@/store/modules/game';

export default createStore({
	modules: { game },
	modules: { adopt },
});
