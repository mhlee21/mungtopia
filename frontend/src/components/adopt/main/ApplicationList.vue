<template>
	<div style="display: flex; align-items: center">
		<img
			:src="application.dog_img"
			alt=""
			style="
				width: 35vw;
				height: 35vw;
				border-radius: 1rem;
				box-shadow: 1px 4px 3px rgba(0, 0, 0, 0.2);
			"
		/>
		<div
			style="
				background-color: white;
				width: 30vw;
				height: 15vh;
				border-radius: 0 1rem 1rem 0;
				box-shadow: 1px 4px 3px rgba(0, 0, 0, 0.2);
			"
		>
			<h4 style="margin: 20% 10%">
				{{ application.name }}
			</h4>
		</div>
	</div>
</template>

<script>
export default {
	props: { application: Object },
	setup() {
		return {};
	},
};
</script>

<style scoped></style>
