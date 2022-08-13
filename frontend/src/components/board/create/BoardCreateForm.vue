<template>
	<div class="input-box">
		<!-- 첫번째 페이지 -->
		<!-- 사진 업로드 -->
		<div v-show="applicationPageNum === 1">
			<BaseUploadImage @image-save="imageSave"></BaseUploadImage>
		</div>

		<div class="adopt-input-box">
			<!-- 두번째 페이지 -->
			<div v-if="applicationPageNum === 2" class="adopt-input-form">
				<!-- 입양 -->
				<div v-if="category === 0">
					<div class="adopt-input-info">
						<i class="fa-solid fa-shield-dog adopt-input-info-icon"></i>
						<div class="adopt-input-info-text">
							입양을 보내고 싶은 강아지의 정보를 입력해주세요.
						</div>
					</div>
					<hr />
					<!-- 이름 -->
					<div class="adopt-input-form-box">
						<label for="name" class="label">이름</label>
						<input
							type="text"
							name="name"
							id="name"
							class="input"
							v-model="name"
						/>
					</div>
					<!-- 성별 -->
					<div class="adopt-input-form-box">
						<label for="gender" class="label">성별</label>
						<select name="gender" id="gender" class="input" v-model="gender">
							<option value="암컷">암컷</option>
							<option value="수컷">수컷</option>
						</select>
					</div>
					<!-- 나이 -->
					<div class="adopt-input-form-box">
						<label for="age" class="label">나이</label>
						<input
							type="text"
							name="age"
							id="age"
							class="input"
							v-model="age"
						/>
					</div>
					<!-- 몸무게 -->
					<div class="adopt-input-form-box">
						<label for="weight" class="label">몸무게</label>
						<input
							type="text"
							name="weight"
							id="weight"
							class="input"
							v-model="weight"
						/>
					</div>
					<!-- 견종 -->
					<div class="adopt-input-form-box">
						<label for="breed" class="label">견종</label>
						<input
							type="text"
							name="breed"
							id="breed"
							class="input"
							v-model="breed"
						/>
					</div>
					<!-- 지역 -->
					<div class="adopt-input-form-box">
						<label for="areaSido" class="label">지역</label>
						<input
							type="text"
							name="areaSido "
							id="areaSido"
							class="input"
							v-model="areaSido"
						/>
					</div>
					<!-- 중성화 여부 -->
					<div class="adopt-input-form-box">
						<div class="label">중성화여부</div>
						<input
							type="radio"
							name="neutering"
							id="not-yet"
							value="0"
							v-model="neutering"
							checked
						/>
						<label for="not-yet" class="radio-answer">미완료</label>
						<input
							type="radio"
							name="neutering"
							id="done"
							value="1"
							v-model="neutering"
						/>
						<label for="done" class="radio-answer">완료</label>
					</div>
				</div>
				<!-- 후기/ 잡담 -->
				<div v-else class="review-input-box">
					<div class="adopt-input-form-box2">
						<label class="adopt-input-info-title">내용</label>
						<textarea class="text-area" rows="10" v-model="contents"></textarea>
					</div>
				</div>
			</div>

			<div v-if="category === 0">
				<!-- 세번째 페이지 -->
				<div v-if="applicationPageNum === 3" class="adopt-input-form">
					<div class="adopt-input-form-box2">
						<label class="adopt-input-info-title">특성</label>
						<small class="adopt-input-info-text"
							>반려견 추천을 위해 내부적으로 활용되는 정보입니다. 게시글에는
							표시되지 않으니, 솔직한 답변 부탁드립니다.:)</small
						>
						<div
							v-for="(question, Qindex) in radioQuestion"
							:key="Qindex"
							class="radio-box"
						>
							<div class="radio-question">{{ Qindex + 1 }}. {{ question }}</div>
							<div
								v-for="(option, Aindex) in radioAnswer"
								:key="Aindex"
								class="radio-answer"
							>
								<input
									type="radio"
									:name="`dogNature-${Qindex}`"
									:id="`dogNature-${Qindex}-${Aindex}`"
									:value="Aindex + 1"
									class="radio"
									v-model="dogNature[Qindex]"
								/>
								<label :for="`dogNature-${Qindex}-${Aindex}`" class="radio">{{
									option
								}}</label>
							</div>
						</div>
					</div>
				</div>
				<!-- 네번째 페이지 -->
				<div v-if="applicationPageNum === 4" class="adopt-input-form">
					<div class="adopt-input-form-box2">
						<label class="adopt-input-info-title">추가사항</label>
						<small class="adopt-input-info-text"
							>추가적으로 적고 싶은 내용이 있다면 적어주세요!(300자 이내)</small
						>
						<textarea
							class="text-area"
							rows="10"
							v-model="contents"
							maxlength="300"
						></textarea>
					</div>
				</div>
			</div>
		</div>

		<!-- 하단 버튼 -->
		<div class="page-btn">
			<button
				class="btn"
				v-if="applicationPageNum > 1"
				@click.prevent="clickPreviousButton()"
			>
				이전
			</button>
			<button class="btn" v-if="!isLastPage" @click.prevent="clickNextButton()">
				다음
			</button>
			<button class="btn" v-else @click.prevent="clickSubmitButton()">
				작성완료
			</button>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex';
import { computed, ref, watch } from 'vue';
import BaseUploadImage from '@/components/board/create/BaseUploadImage.vue';
export default {
	components: { BaseUploadImage },
	setup() {
		const store = useStore();
		const category = computed(() => store.getters['board/category']);
		const applicationPageNum = computed(
			() => store.getters['board/applicationPageNum'],
		);
		// 데이터
		const name = ref(null);
		const gender = ref(null);
		const age = ref(null);
		const weight = ref(null);
		const breed = ref(null);
		const areaSido = ref(null);
		const neutering = ref(null);
		const dogNature = ref([]);
		const files = ref([]);
		const contents = ref(null);

		// 하단 버튼 구현
		const clickPreviousButton = () => {
			store.dispatch(
				'board/setApplicationPageNum',
				applicationPageNum.value - 1,
			);
		};
		const clickNextButton = () => {
			store.dispatch(
				'board/setApplicationPageNum',
				applicationPageNum.value + 1,
			);
		};
		const clickSubmitButton = () => {
			const formData = new FormData();
			let data = {};
			if (category.value === 0) {
				data = {
					userSeq: store.getters['auth/user']?.userSeq,
					boardTag: category.value,
					contents: contents.value,
					createtime: new Date(),
					dogInfo: {
						name: name.value,
						gender: gender.value,
						age: age.value,
						weight: weight.value,
						breed: breed.value,
						areaSido: areaSido.value,
						neutering: neutering.value,
						dogNature: dogNature.value,
						apoptionStatus: 1,
					},
				};
			} else {
				data = {
					userSeq: store.getters['auth/user']?.userSeq,
					boardTag: category.value,
					contents: contents.value,
					createtime: new Date(),
				};

				formData.append('data', data);
				formData.append('files', files);
			}
			// 페이지 초기화
			store.dispatch('board/setApplicationPageNum', 1);
			store.dispatch('board/createBoard', formData);
		};
		// 마지막 페이지 여부 확인
		const isLastPage = computed(() => {
			if (category.value === 0 && applicationPageNum.value === 4) {
				return true;
			} else if (category.value !== 0 && applicationPageNum.value === 2) {
				return true;
			} else {
				return false;
			}
		});

		// 라디오 버튼 data
		const radioAnswer = [
			'전혀 그렇지 않다',
			'그렇지 않다',
			'보통이다',
			'그렇다',
			'매우 그렇다',
		];
		const radioQuestion = [
			'털이 많이 빠지나요?',
			'활동적인가요?',
			'다른 강아지들과 잘 어울리나요?',
			'자주 짖나요?',
			'강아지가 큰 편인가요?',
			'학습력이 좋은가요?',
		];

		// 이미지 저장
		const imageSave = images => {
			files.value = images;
		};

		// 카테고리 전환시 pageNum reset
		watch(category, () => {
			store.dispatch('board/setApplicationPageNum', 1);
		});

		return {
			category,
			applicationPageNum,
			clickPreviousButton,
			clickNextButton,
			clickSubmitButton,
			isLastPage,
			radioAnswer,
			radioQuestion,
			name,
			gender,
			age,
			weight,
			breed,
			areaSido,
			neutering,
			dogNature,
			contents,
			imageSave,
		};
	},
};
</script>

<style lang="scss" scoped></style>
