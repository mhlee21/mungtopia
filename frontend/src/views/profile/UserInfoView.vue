<template>
	<div class="profile-info-pg">
		<div class="title-wrapper">
			<i class="fa-solid fa-user profile-icon"></i>
			<div>회원정보</div>
		</div>
		<div class="profile-info-form">
			<div class="user-info-wrapper">
				<!-- 이름 -->
				<div class="user-info">
					<div class="user-input-label label">이름</div>
					<input class="user-info-input" v-model="name" :disabled="after" />
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
								v-model="gender"
								value="남성"
								:disabled="after"
								checked
							/>
							<label for="male">남성</label>
						</div>
						<div>
							<input
								type="radio"
								name="gender"
								id="female"
								v-model="gender"
								value="여성"
								:disabled="after"
							/>
							<label for="female">여성</label>
						</div>
					</div>
				</div>
				<!-- 생년월일 -->
				<div class="user-info">
					<label class="user-input-label label">생년월일</label>
					<input class="user-info-input" v-model="birth" :disabled="after" />
				</div>
				<!-- 연락처 -->
				<div class="user-info">
					<label class="user-input-label label">연락처</label>
					<input class="user-info-input" v-model="phone" :disabled="after" />
				</div>
				<!-- 직업 -->
				<div class="user-info">
					<label class="user-input-label label">직업</label>
					<input class="user-info-input" v-model="job" :disabled="after" />
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
									v-model="zonecode"
									disabled
									class="input-round"
								/>
								<button v-if="!after">주소검색</button>
							</div>
						</div>
						<div class="user-info2">
							<label for="roadAddress" class="label">도로명 주소</label>
							<input
								type="text"
								id="roadAddress"
								v-model="roadAddress"
								disabled
								class="input-round"
							/>
						</div>
						<div class="user-info2">
							<label for="detailAddress" class="label">상세 주소</label>
							<input
								type="text"
								id="detailAddress"
								v-model="detailAddress"
								:disabled="after"
								class="input-round"
							/>
						</div>
					</div>
				</div>
				<!-- 저장하기 버튼 -->
				<div class="btn-wrapper">
					<button class="save-btn">저장하기</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { ref } from 'vue';

export default {
	setup() {
		const name = ref(null);
		const gender = ref(null);
		const age = ref(null);
		const birth = ref(null);
		const phone = ref(null);
		const job = ref(null);
		const address = ref(null);
		const zonecode = ref(null);
		const roadAddress = ref(null);
		const detailAddress = ref(null);
		const after = ref(true);
		return {
			name,
			gender,
			age,
			birth,
			phone,
			job,
			address,
			zonecode,
			roadAddress,
			detailAddress,
			after,
		};
	},
};
</script>

<style lang="scss" scoped></style>
