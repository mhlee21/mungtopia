<template>
	<div class="app">
		<router-view class="view" />
		<!-- <NavBar></NavBar> -->
	</div>
</template>

<script>
// import NavBar from '@/components/NavBar';
export default {
	// components: { NavBar },
};
</script>
<style>
.app {
	width: 360px;
	height: 640px;
	overflow: hidden;
	border-radius: 2rem;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}
.view {
	padding: 10%;
	overflow: auto;
	height: 80%;
	background-color: #ff9898;
}
</style>
