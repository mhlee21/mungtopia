<template>
	<div
		style="
			margin-top: 2vh;
			background-color: white;
			border-radius: 2rem;
			padding: 3% 1rem;
		"
	>
		<!-- 입양 신청 절차가 진행되고 있을 떄 -->
		<div v-if="pass">
			<div
				v-for="procedure in adoptProcedures"
				:key="procedure.id"
				style="display: flex; height: 10vh"
			>
				<!-- 단계 -->
				<div
					style="
						border-radius: 50%;
						width: 1.5rem;
						height: 1.5rem;
						background-color: #ff9898;
						display: flex;
						align-items: center;
						margin: auto 0;
					"
				>
					<div style="margin: auto">
						{{ procedure.id }}
					</div>
				</div>
				<!-- 절차 -->
				<div style="padding: 0 10%; width: 50%; margin: auto 0">
					<div>{{ procedure.step }}</div>
					<div v-if="procedure.time">{{ procedure.time }}</div>
				</div>
				<!-- 아이콘 -->
				<div style="margin: auto 0">
					{{ procedure.icon }}
				</div>
			</div>
		</div>
		<!-- 입양 신청이 반려되었을 때 -->
		<div v-else style="padding: 15% 5%">
			<div style="padding: 5% 0">
				<div style="text-align: center">
					<h3>입양이 반려되었습니다.</h3>
				</div>
				<hr />
				<div>
					이 페이지를 나가면 신청 내역이 삭제됩니다. <strong>확인</strong>을
					눌러주세요.
				</div>
			</div>
			<div style="padding: 20% 0 0; text-align: center">
				<button>확인</button>
			</div>
		</div>
	</div>
</template>

<script>
import { ref, reactive } from 'vue';
export default {
	setup() {
		const adoptProcedures = reactive([
			{
				id: 1,
				step: '입양신청',
				time: '2022.07.10',
				icon: '신청서',
			},
			{
				id: 2,
				step: '화상 면담',
				time: '2022.07.12 2PM',
				icon: '영상통화',
			},
			{
				id: 3,
				step: '1차 방문',
				time: '',
				icon: '지도',
			},
			{
				id: 4,
				step: '2차 방문',
				time: '',
				icon: '지도',
			},
			{
				id: 5,
				step: '입양완료',
				time: '',
				icon: '완료',
			},
		]);
		const pass = ref(true);
		return { adoptProcedures, pass };
	},
};
</script>

<style lang="scss" scoped></style>
