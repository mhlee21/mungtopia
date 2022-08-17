<template>
	<div>
		<div class="user-tit-box">
			<div class="user-info">
				<img :src="profile" alt="username image" class="user-profile" />
				<span class="user-name">{{ username }}</span>
			</div>
			<span class="time">{{ difTime(new Date(createtime)) }}</span>
		</div>
	</div>
</template>

<script>
export default {
	props: { username: String, profile: String, createtime: String },
	setup() {
		const difTime = timeValue => {
			const today = new Date();
			const betweenTime = Math.floor(
				(today.getTime() - timeValue.getTime()) / 1000 / 60,
			);
			if (betweenTime < 1) return '방금전';
			if (betweenTime < 60) {
				return `${betweenTime}분전`;
			}
			const betweenTimeHour = Math.floor(betweenTime / 60);
			if (betweenTimeHour < 24) {
				return `${betweenTimeHour}시간전`;
			}
			const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
			if (betweenTimeDay < 365) {
				return `${betweenTimeDay}일전`;
			}
			return `${Math.floor(betweenTimeDay / 365)}년전`;
		};
		return { difTime };
	},
};
</script>

<style scoped>
.user-profile {
	width: 30px;
}
</style>
