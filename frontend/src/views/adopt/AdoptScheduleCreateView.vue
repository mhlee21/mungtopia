<template>
	<div>
		<AdoptScheduleComponent></AdoptScheduleComponent>
		<AdoptScheduleCalendar></AdoptScheduleCalendar>
	</div>
</template>

<script>
import AdoptScheduleComponent from '@/components/adopt/AdoptScheduleComponent';
import AdoptScheduleCalendar from '@/components/adopt/AdoptScheduleCalendar';
export default {
	components: { AdoptScheduleComponent, AdoptScheduleCalendar },
	setup() {
		return {};
	},
};
</script>

<style lang="scss" scoped></style>
