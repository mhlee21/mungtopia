<template>
	<div class="application-detail-pg">
		<div class="top-wrapper">
			<div class="back-button-wrapper">
				<i class="fa-solid fa-angle-left back-button" @click="goBack"></i>
			</div>
			<!-- 제목 -->
		</div>
		<div class="application-title-wrapper">
			<div class="title">입양신청서</div>
			<div class="title-icon"><i class="fa-solid fa-copy"></i></div>
		</div>
		<!-- 내용 -->
		<div class="application-content-wrapper">
			<!-- 회원정보 -->
			<div v-if="page === 1">
				<div class="content-title">
					<div class="title-line"></div>
					회원정보
				</div>
				<div class="user-info-wrapper">
					<!-- 이름 -->
					<div class="user-info">
						<div class="user-input-label label">이름</div>
						<div class="user-info-content">{{ userInfo?.name }}</div>
					</div>
					<!-- 성별 -->
					<div class="user-info">
						<div class="user-input-label label">성별</div>
						<div class="user-info-content">{{ userInfo?.gender }}</div>
					</div>
					<!-- 생년월일 -->
					<div class="user-info">
						<label class="user-input-label label">생년월일</label>
						<div class="user-info-content">{{ userInfo?.birth }}</div>
					</div>
					<!-- 연락처 -->
					<div class="user-info">
						<label class="user-input-label label">연락처</label>
						<div class="user-info-content">{{ userInfo?.phoneNumber }}</div>
					</div>
					<!-- 직업 -->
					<div class="user-info">
						<label class="user-input-label label">직업</label>
						<div class="user-info-content">{{ userInfo?.job }}</div>
					</div>
					<!-- 주소 -->
					<div class="label">주소</div>
					<div class="user-info2">
						<div class="user-info">
							<div class="user-input-label label fontsize">우편번호</div>
							<div class="user-info-content">{{ userInfo?.zonecode }}</div>
						</div>
						<div class="user-info">
							<div class="user-input-label label fontsize">도로명 주소</div>
							<div class="user-info-content">{{ userInfo?.roadAddress }}</div>
						</div>
						<div class="user-info">
							<div class="user-input-label label fontsize">상세 주소</div>
							<div class="user-info-content">{{ userInfo?.detailAddress }}</div>
						</div>
					</div>
					<!-- 신청서 버튼 -->
					<div class="btn-wrapper">
						<button class="btn" @click="pageUp">신청서</button>
					</div>
				</div>
			</div>
			<!--  -->
			<div v-else-if="page === 2">
				<div class="content-title">
					<div class="title-line"></div>
					신청서
				</div>
				<div class="content-date">{{ createtime }}</div>
				<div
					class="content-box"
					v-for="(question, index) in answer"
					:key="index"
				>
					<div class="question">
						Q{{ index + 1 }}. {{ adoptQuestion.adoptQuestion[index]?.question }}
					</div>
					<div class="answer">{{ question.answer }}</div>
				</div>
				<div class="btn-wrapper">
					<button class="btn" @click="pageDown">개인정보</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { ref, computed } from 'vue';
import adoptQuestion from '@/assets/AdoptQ.json';

export default {
	setup() {
		const store = useStore();
		const route = useRoute();
		const router = useRouter();
		store.dispatch('adopt/fetchApplication', route.params.applicationId);

		const userInfo = computed(
			() => store.getters['adopt/application']?.userInfo,
		);
		const answer = computed(() => store.getters['adopt/application']?.answer);
		const createtime = computed(
			() => store.getters['adopt/application']?.createtime,
		);
		const page = ref(1);
		const pageUp = () => {
			page.value++;
		};
		const pageDown = () => {
			page.value--;
		};
		const goBack = () => {
			router.go(-1);
		};

		return {
			userInfo,
			answer,
			createtime,
			adoptQuestion,
			page,
			pageUp,
			pageDown,
			goBack,
		};
	},
};
</script>

<style lang="scss" scoped></style>
