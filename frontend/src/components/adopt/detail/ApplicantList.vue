<template>
	<div>
		<h3>
			신청자 <small>{{ protectorDetail.applicationList.length }}</small>
		</h3>
		<div
			v-for="(applicant, index) in protectorDetail['applicationList']"
			:key="index"
		>
			<div
				class="application-list-component"
				@click="
					clickApplicantComponent(
						index,
						applicant.userId,
						applicant.applicationStatus,
					)
				"
			>
				<img style="width: 20%" :src="applicant.profile" />
				<div style="width: 40%">
					{{ applicant.name }}
				</div>
				<div style="width: 40%">
					<button @click="goChatRoom(applicant.chatRoomId)">채팅</button>
					<button>취소</button>
				</div>
			</div>
			<AdoptDetailProcess v-if="activeApplicant === index"></AdoptDetailProcess>
		</div>
	</div>
</template>

<script>
import AdoptDetailProcess from '@/components/adopt/detail/AdoptDetailProcess';
import { computed } from 'vue';
import { useStore } from 'vuex';
import router from '@/router';
export default {
	components: { AdoptDetailProcess },
	setup() {
		const store = useStore();
		const protectorDetail = computed(
			() => store.getters['adopt/protectorDetail'],
		);
		const activeApplicant = computed(
			() => store.getters['adopt/activeApplicant'],
		);
		store.dispatch('adopt/updateActiveApplicant', -1);
		const clickApplicantComponent = (index, userId, applicationStatus) => {
			if (activeApplicant.value === index) {
				store.dispatch('adopt/updateActiveApplicant', -1);
			} else {
				store.dispatch('adopt/updateActiveApplicant', index);
				store.dispatch('adopt/fetchProtectorAdoptProcess', {
					boardId: protectorDetail.value['boardId'],
					userId,
					applicationStatus,
				});
			}
		};
		const goChatRoom = chatRoomId => {
			router.push({
				name: 'chat',
				params: {
					chatRoomId,
				},
			});
		};
		return {
			protectorDetail,
			activeApplicant,
			clickApplicantComponent,
			goChatRoom,
		};
	},
};
</script>

<style scoped>
.application-list-component {
	display: flex;
	height: 10vh;
	justify-content: center;
	background-color: white;
	border-radius: 1rem;
	padding: 7% 5% 2%;
}
</style>
