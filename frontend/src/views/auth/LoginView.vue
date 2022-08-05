<template>
	<div>
		<div class="socal_login_container">
			<h2>로그인</h2>
			<div v-for="(social, index) in socials" :key="index">
				<a class="social_btn" :href="socialLoginUrl(social.socialType)">
					<img
						class="social_login"
						:src="social.src"
						:style="{ width: social.width, height: social.height }"
					/>
					| {{ social.comment }}
				</a>
			</div>
		</div>
	</div>
</template>

<script>
import $ from '@/utils';
export default {
	setup() {
		const socials = [
			{
				socialType: 'google',
				src: $.getSocialImage('google'),
				width: '32px',
				height: '32px',
				comment: '구글 로그인',
			},
			{
				socialType: 'naver',
				src: $.getSocialImage('naver'),
				width: '32px',
				height: '32px',
				comment: '네이버 로그인',
			},
			{
				socialType: 'kakao',
				src: $.getSocialImage('kakao'),
				width: '32px',
				height: '32px',
				comment: '카카오 로그인',
			},
		];
		const socialLoginUrl = socialType => {
			return $.getSocialLoginUrl(socialType);
		};
		return { socials, socialLoginUrl };
	},
};
</script>

<style scoped>
.socal_login_container {
	text-align: center;
	width: 90%;
	margin: auto;
}
.social_login {
	height: 32px;
	float: left;
	margin-top: 5.2px;
	margin-left: 10px;
}
.social_btn:hover {
	color: #2098f3;
}
.social_btn {
	margin-bottom: 15px;
	font-weight: 400;
	font-size: 16px;
	display: block;
	width: 100%;
	height: 45px;
	line-height: 45px;
	border-radius: 4px;
	border: 1px solid #e8e8e8;
	cursor: pointer;
	color: black;
	text-decoration: none;
}
</style>
