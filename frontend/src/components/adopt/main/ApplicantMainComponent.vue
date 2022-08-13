<template>
	<div
		style=""
		@click="clickApplication(application.applicationId)"
		class="main-component"
	>
		<img :src="application.dogImg" alt="dog image" class="dog-img" />
		<div class="dog-info">
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
	setup(props) {
		console.log('here', props.application);
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

<style scoped></style>
