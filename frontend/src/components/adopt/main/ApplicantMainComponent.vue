<template>
	<div
		style="display: flex; align-items: center"
		@click="clickApplication(application.applicationId)"
	>
		<img
			:src="application.dogImg"
			alt="dog image"
			class="application-dog-img"
		/>
		<div class="application-dog-info">
			<h4 style="margin: 20% 10%">
				{{ application.dogName }}
			</h4>
		</div>
	</div>
</template>

<script>
import { useRouter } from 'vue-router';

export default {
	props: { application: Object },
	setup() {
		const router = useRouter();
		const clickApplication = applicationId => {
			router.push({
				name: 'applicantDetail',
				params: { applicationId: applicationId },
			});
		};
		return { clickApplication };
	},
};
</script>

<style scoped>
.application-dog-img {
	width: 35vw;
	height: 35vw;
	border-radius: 1rem;
	box-shadow: 1px 4px 3px rgba(0, 0, 0, 0.2);
}
.application-dog-info {
	background-color: white;
	width: 30vw;
	height: 15vh;
	border-radius: 0 1rem 1rem 0;
	box-shadow: 1px 4px 3px rgba(0, 0, 0, 0.2);
}
</style>
