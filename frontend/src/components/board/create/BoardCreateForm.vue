<template>
	<div class="input-box">
		<!-- 입양 -->
		<div class="adopt-input-box" v-if="category === 0">
			<!-- 첫번째 페이지 -->
			<div v-if="applicationPageNum === 1">
				<div class="adopt-input-info">
					<i class="fa-solid fa-shield-dog adopt-input-info-icon"></i>
					<div class="adopt-input-info-text">
						입양을 보내고 싶은 강아지의 정보를 입력해주세요.
					</div>
				</div>
				<hr />
				<form class="adopt-input-form">
					<div class="adopt-input-form-box">
						<label for="name" class="label">이름</label>
						<input type="text" name="name" id="name" class="input" />
					</div>
					<div class="adopt-input-form-box">
						<label for="gender" class="label">성별</label>
						<select name="gender" id="gender" class="input">
							<option value="암컷">암컷</option>
							<option value="수컷">수컷</option>
						</select>
					</div>
					<div class="adopt-input-form-box">
						<label for="age" class="label">나이</label>
						<input type="text" name="age" id="age" class="input" />
					</div>
					<div class="adopt-input-form-box">
						<label for="weight" class="label">몸무게</label>
						<input type="number" name="weight" id="weight" class="input" />
					</div>
					<div class="adopt-input-form-box">
						<label for="breed" class="label">견종</label>
						<input type="text" name="breed" id="breed" class="input" />
					</div>
					<div class="adopt-input-form-box">
						<label for="areaSido" class="label">지역</label>
						<input type="text" name="areaSido " id="areaSido" class="input" />
					</div>
					<div class="adopt-input-form-box">
						<div class="label">중성화여부</div>
						<input
							type="radio"
							name="neutering"
							id="not-yet"
							value="0"
							checked
						/>
						<label for="not-yet" class="label">미완료</label>
						<input type="radio" name="neutering" id="done" value="1" />
						<label for="done" class="label">완료</label>
					</div>
					<div class="adopt-input-form-btn">
						<button class="btn" @click.prevent="clickNextButton()">다음</button>
					</div>
				</form>
			</div>
			<!-- 두번째 페이지 -->
			<div></div>
		</div>
		<!-- 후기/ 잡담 -->
		<div class="review-input-box" v-else></div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { computed } from 'vue';
export default {
	setup() {
		const store = useStore();
		const category = computed(() => store.getters['board/category']);
		const applicationPageNum = computed(
			() => store.getters['board/applicationPageNum'],
		);
		const clickNextButton = () => {
			store.dispatch('board/setApplicationPageNum', 2);
		};
		return { category, applicationPageNum, clickNextButton };
	},
};
</script>

<style lang="scss" scoped></style>
