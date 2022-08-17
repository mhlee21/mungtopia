<template>
	<div class="schedule-view">
		<div class="back-button-wrapper schedule-btn">
			<i class="fa-solid fa-angle-left back-button" @click="goBack"></i>
		</div>
		<div class="schedule-component">
			<div>
				<h3 style="margin: 0">일정 예약</h3>
				<hr />
				<div style="padding: 10% 3%">
					<b>약속시간</b>
					<DatePicker
						v-model="date"
						:format="format"
						:previewFormat="format"
						style="margin: 5% 0 20%"
					></DatePicker>
					<div style="display: flex; justify-content: center">
						<button @click="scheduleReservation()" class="pink-button">
							저장
						</button>
						<button
							v-if="isReservated"
							@click="deleteReservation()"
							class="pink-button"
						>
							삭제
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import DatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import { ref, computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
export default {
	components: { DatePicker },
	setup() {
		const store = useStore();
		const router = useRouter();
		const date = ref(new Date(store.getters['adopt/date']) || new Date());
		const isReservated = computed(() => !(store.getters['adopt/date'] === ''));
		const scheduleReservation = () => {
			console.log(date.value);
			if (date.value === null) {
				alert('날짜를 선택해주세요');
			} else {
				store.dispatch('adopt/saveSchedule', date.value);
				router.go(-1);
			}
		};
		const deleteReservation = () => {
			store.dispatch('adopt/deleteSchedule');
			router.go(-1);
		};
		const goBack = () => {
			router.go(-1);
		};
		const format = date => {
			const year = date.getFullYear();
			const month =
				date.getMonth() + 1 > 9
					? date.getMonth() + 1
					: '0' + (date.getMonth() + 1);
			const day = date.getDate() > 9 ? date.getDate() : '0' + date.getDate();
			const hour =
				date.getHours() > 9 ? date.getHours() : '0' + date.getHours();
			const minute =
				date.getMinutes() > 9 ? date.getMinutes() : '0' + date.getMinutes();
			return `${year}.${month}.${day} ${hour}:${minute}`;
		};

		return {
			date,
			isReservated,
			scheduleReservation,
			deleteReservation,
			goBack,
			format,
		};
	},
};
</script>

<style lang="scss" scoped>
.schedule-view {
	padding: 0% 7%;
	background-color: #ff9898;
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	position: relative;
}

.schedule-view .schedule-btn {
	position: absolute;
	top: 5%;
	left: 7%;
}

.schedule-component {
	background-color: white;
	border-radius: 2rem;
	padding: 10% 10% 5%;
	margin-top: 5%;
}
.pink-button {
	background-color: #ff9898;
	border: none;
	color: white;
	border-radius: 2rem;
	width: 100px;
	height: 40px;
}
</style>
