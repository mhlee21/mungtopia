<template>
	<div>
		<h2 class="content">
			{{ Qlist['knowledge'][0]['question'] }}
		</h2>
	</div>
</template>

<script>
import Qdata from '@/assets/Qdata.json';
export default {
	setup() {
		const Qlist = Qdata;
		return { Qlist };
	},
};
</script>

<style lang="scss" scoped>
.content {
	text-align: center;
	margin: 10px;
	padding: 10px;
}
</style>
