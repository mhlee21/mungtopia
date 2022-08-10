<template>
	<div v-if="!images">
		<div class="image-box">
			<label class="custom-file-label" for="customFile">
				<i class="fa-solid fa-image image-upload-icon"></i>
				<p class="image-upload-notice">
					사진을 업로드 해주세요. <br />최대 3장까지 가능합니다.
				</p>
			</label>
			<input
				id="customFile"
				type="file"
				multiple
				@change="handleFileChange"
				class="fileUploadInput"
			/>
		</div>
	</div>
	<div v-else>
		<div class="button-box">
			<i class="fa-solid fa-angle-left" @click="previousImage()"></i>
			<i class="fa-solid fa-angle-right" @click="nextImage()"></i>
			<i class="fa-solid fa-rotate-right" @click="resetImage()"></i>
		</div>
		<img :src="images[fileNum]" class="preview-image" />
	</div>
</template>

<script>
import { computed, ref } from 'vue';
import { useStore } from 'vuex';
export default {
	emits: ['imageSave'],
	setup(props, { emit }) {
		const store = useStore();
		const applicationPageNum = computed(
			() => store.getters['board/applicationPageNum'],
		);
		const images = ref(null);
		let fileNum = ref(0);

		// 이미지 업로드 시, 파일 미리보기 구현
		const handleFileChange = e => {
			if (e.target.files.length > 3) {
				alert('3장 이하의 사진을 선택해주세요!');
			} else {
				let data = [];
				for (let i = 0; i < e.target.files.length; i++) {
					data.push(URL.createObjectURL(e.target.files[i]));
				}
				images.value = data;
				emit('imageSave', e.target.files);
			}
		};
		const previousImage = () => {
			fileNum.value = (3 + fileNum.value - 1) % images.value.length;
		};
		const nextImage = () => {
			fileNum.value = (fileNum.value + 1) % images.value.length;
		};
		const resetImage = () => {
			fileNum.value = 0;
			images.value = null;
		};
		return {
			applicationPageNum,
			handleFileChange,
			images,
			fileNum,
			previousImage,
			nextImage,
			resetImage,
		};
	},
};
</script>

<style lang="scss" scoped></style>
