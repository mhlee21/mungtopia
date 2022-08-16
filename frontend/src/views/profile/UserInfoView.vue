<template>
	<div class="profile-info-pg">
		<div class="title-wrapper">
			<i class="fa-solid fa-user profile-icon"></i>
			<div>회원정보</div>
		</div>
		<div class="profile-info-form">
			<div class="user-info-wrapper">
				<div v-if="newUserInfo">
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
									value="M"
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
									value="W"
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
									<button
										v-if="!after"
										class="search-address-btn"
										@click.prevent="searchAddress"
									>
										주소검색
									</button>
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
									v-model="newUserInfo['detailAddress']"
								/>
							</div>
						</div>
					</div>
					<!-- 저장하기 버튼 -->
					<div class="btn-wrapper">
						<button class="save-btn" @click="cancel()">취소</button>
						<button class="save-btn" @click="submitProfile()">저장</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { ref, computed, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
export default {
	setup() {
		const store = useStore();
		const router = useRouter();
		store.dispatch('profile/fetchUserInfo');

		const userInfo = computed(() => store.getters['profile/userInfo']);
		const newUserInfo = reactive(userInfo);

		const after = ref(false);
		const submitProfile = () => {
			store.dispatch('profile/updateUserInfo', newUserInfo.value);
			router.go(-1);
		};
		const cancel = () => {
			router.go(-1);
		};

		const searchAddress = () => {
			new window.daum.Postcode({
				oncomplete: data => {
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

					// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
					// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					let fullRoadAddr = data.roadAddress; // 도로명 주소 변수
					let extraRoadAddr = ''; // 도로명 조합형 주소 변수

					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraRoadAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraRoadAddr +=
							extraRoadAddr !== ''
								? ', ' + data.buildingName
								: data.buildingName;
					}
					// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if (extraRoadAddr !== '') {
						extraRoadAddr = ' (' + extraRoadAddr + ')';
					}
					// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
					if (fullRoadAddr !== '') {
						fullRoadAddr += extraRoadAddr;
					}

					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					// this.zip = data.zonecode; //5자리 새우편번호 사용
					// this.addr1 = fullRoadAddr;
					newUserInfo.value['roadAddress'] = fullRoadAddr;
					newUserInfo.value['zonecode'] = data.zonecode;
				},
			}).open();
		};
		return {
			after,
			newUserInfo,
			submitProfile,
			userInfo,
			searchAddress,
			cancel,
		};
	},
};
</script>

<style lang="scss" scoped></style>
