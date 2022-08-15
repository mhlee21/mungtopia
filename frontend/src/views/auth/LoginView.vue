<template>
	<div class="main">
		<div class="login-pg">
			<div class="logo-box">
				<img src="@/assets/img/logo-icon.svg" alt="" />
			</div>
			<div class="login-wrap">
				<h1>로그인</h1>
				<ul>
					<li class="login-btn basic">
						<a href="/board">로그인 없이 들어가기</a>
					</li>
					<li class="login-btn kakao">
						<a :href="socialLoginUrl('kakao')">
							<div class="icon-box">
								<img
									src="@/assets/img/kakao-icon.svg"
									alt=""
									class="login-icon"
								/>
							</div>
							<p>카카오톡 계정으로 로그인</p>
						</a>
					</li>
					<li class="login-btn naver">
						<a :href="socialLoginUrl('naver')">
							<div class="icon-box">
								<img
									src="@/assets/img/naver-icon.png"
									alt=""
									class="login-icon"
								/>
							</div>
							<p>네이버 계정으로 로그인</p>
						</a>
					</li>
					<li class="login-btn google">
						<a :href="socialLoginUrl('google')">
							<div class="icon-box">
								<img
									src="@/assets/img/google-icon.svg"
									alt=""
									class="login-icon"
								/>
							</div>
							<p>구글 계정으로 로그인</p>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</template>

<script>
import $ from '@/utils';
export default {
	setup() {
		const socialLoginUrl = socialType => {
			return $.getSocialLoginUrl(socialType);
		};
		return { socialLoginUrl };
	},
};
</script>

<style scoped></style>
