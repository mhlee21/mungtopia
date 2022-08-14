<template>
	<div>
		<div>
			<h3 class="applicant-cnt">
				신청자
				<small class="applicant-num">{{ applicationList?.length }}</small>
			</h3>
		</div>
		<div v-if="applicationList?.length === 0" class="no-applicant">
			신청자가 없습니다.
		</div>
		<div v-for="(applicant, index) in applicationList" :key="index">
			<div
				class="application-list-component"
				@click="clickApplicantComponent(index, applicant)"
			>
				<div class="profile-img-wrapper">
					<img class="profile-img" :src="applicant.profileImg" />
				</div>
				<div class="username-wrapper">
					<h3 class="username">{{ applicant.username }}</h3>
				</div>
				<div class="applicant-btn-wrapper">
					<button
						@click="
							goChatRoom(
								applicant.chatRoomId,
								applicant.adoptionProcessId,
								applicant.applicationStatus,
							)
						"
						class="applicant-btn"
					>
						<i class="fa-solid fa-comment-dots"></i>
					</button>
					<button class="applicant-btn" @click="denyAdoption(index)">
						<i class="fa-solid fa-xmark"></i>
					</button>
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
		const applicationList = computed(() =>
			protectorDetail?.value.applicationList?.filter(
				application => application.applicationStatus !== 8,
			),
		);
		store.dispatch('adopt/updateActiveApplicant', -1);
		const clickApplicantComponent = (index, applicant) => {
			if (activeApplicant.value === index) {
				store.dispatch('adopt/updateActiveApplicant', -1);
			} else {
				store.dispatch('adopt/updateActiveApplicant', index);
				store.dispatch('adopt/fetchProtectorAdoptProcess', {
					adoptionProcessId: applicant.adoptionProcessId,
					applicationStatus: applicant.applicationStatus,
				});
			}
		};
		const goChatRoom = (chatRoomId, adoptionProcessId, applicationStatus) => {
			// date 저장
			store.dispatch('adopt/saveDate', {
				adoptionProcessId,
				applicationStatus,
			});
			// 채팅 페이지로 이동
			router.push({
				name: 'chat',
				params: {
					chatRoomId,
				},
			});
		};
		const denyAdoption = index => {
			const answer = confirm('입양을 반려하시겠습니까?');
			if (answer == true) {
				store.dispatch('adopt/protectorCancel', index);
			}
		};
		return {
			protectorDetail,
			activeApplicant,
			applicationList,
			clickApplicantComponent,
			goChatRoom,
			denyAdoption,
		};
	},
};
</script>

<style scoped></style>
