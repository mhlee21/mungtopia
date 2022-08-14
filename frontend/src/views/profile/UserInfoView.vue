<template>
	<div class="profile-info-pg">
		<div class="title-wrapper">
			<i class="fa-solid fa-user profile-icon"></i>
			<div>회원정보</div>
		</div>
		<div class="profile-info-form">
			<div class="user-info-wrapper">
				<form action="" @submit.prevent="submitProfile()">
					<!-- 이름 -->
					<div class="user-info">
						<div class="user-input-label label">이름</div>
						<input
							class="user-info-input"
							:disabled="after"
							v-model="newUserInfo.name"
						/>
					</div>
					<!-- 성별 -->
					<div class="user-info">
						<div class="user-input-label label">성별</div>
						<div class="user-gender">
							<div>
								<input
									type="radio"
									name="gender"
									id="male"
									:disabled="after"
									checked
									v-model="newUserInfo.gender"
								/>
								<label for="male">남성</label>
							</div>
							<div>
								<input
									type="radio"
									name="gender"
									id="female"
									:disabled="after"
									v-model="newUserInfo.gender"
								/>
								<label for="female">여성</label>
							</div>
						</div>
					</div>
					<!-- 생년월일 -->
					<div class="user-info">
						<label class="user-input-label label">생년월일</label>
						<input
							class="user-info-input"
							:disabled="after"
							v-model="newUserInfo.birth"
						/>
					</div>
					<!-- 연락처 -->
					<div class="user-info">
						<label class="user-input-label label">연락처</label>
						<input
							class="user-info-input"
							:disabled="after"
							v-model="newUserInfo.phoneNumber"
						/>
					</div>
					<!-- 직업 -->
					<div class="user-info">
						<label class="user-input-label label">직업</label>
						<input
							class="user-info-input"
							:disabled="after"
							v-model="newUserInfo.job"
						/>
					</div>
					<!-- 주소 -->
					<div class="user-info2">
						<div class="label">주소</div>
						<div class="user-info-address-box">
							<div class="user-info2">
								<label for="zonecode" class="label">우편번호</label>
								<div>
									<input
										type="text"
										id="zonecode"
										disabled
										class="input-round"
										v-model="newUserInfo.zonecode"
									/>
									<button v-if="!after">주소검색</button>
								</div>
							</div>
							<div class="user-info2">
								<label for="roadAddress" class="label">도로명 주소</label>
								<input
									type="text"
									id="roadAddress"
									disabled
									class="input-round"
									v-model="newUserInfo.roadAddress"
								/>
							</div>
							<div class="user-info2">
								<label for="detailAddress" class="label">상세 주소</label>
								<input
									type="text"
									id="detailAddress"
									:disabled="after"
									class="input-round"
									v-model="newUserInfo.detailAddress"
								/>
							</div>
						</div>
					</div>
					<!-- 저장하기 버튼 -->
					<div class="btn-wrapper">
						<button class="save-btn">저장하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</template>

<script>
import { ref, computed, reactive } from 'vue';
import { useStore } from 'vuex';
export default {
	setup() {
		const store = useStore();
		store.dispatch('profile/fetchUserInfo');

		const userInfo = computed(() => store.getters['profile/userInfo']);
		const newUserInfo = reactive({});

		// watch(userInfo, (newValue, old) => {
		// 	newUserInfo.value = userInfo.value;
		// 	// console.log(newUserInfo.value, 'd');
		// });

		const after = ref(false);
		const submitProfile = () => {
			store.dispatch('profile/updateUserInfo', newUserInfo);
		};
		return {
			after,
			newUserInfo,
			submitProfile,
			userInfo,
		};
	},
};
</script>

<style lang="scss" scoped></style>
