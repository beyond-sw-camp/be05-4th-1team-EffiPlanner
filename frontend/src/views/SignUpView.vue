<template>
  <div class="container" style="background-color: #ffffff;">
    <!-- 이미지 삽입 -->
    <a href="/">
      <p class="logo_txt" style="color: black;">에피</p>
    </a>
    <div class="signup-container" style="background-color: #3B85CE;">
      <email-auth></email-auth>
      <p style="color: white;">비밀번호</p>
      <input type="password" v-model="password1" placeholder="비밀번호를 입력하세요." style="background-color: #ffffff; color: #3B85CE; width: calc(100% - 16px); margin-bottom: 10px; padding: 8px; border-radius: 5px; border: 1px solid #ccc;">
      <br>
      <input type="password" v-model="password2" placeholder="비밀번호를 다시 입력하세요." style="background-color: #ffffff; color: #3B85CE; width: calc(100% - 16px); margin-bottom: 10px; padding: 8px; border-radius: 5px; border: 1px solid #ccc;">
      <br>
      <button type="submit" style="background-color: #013B7A; color: white; width: calc(100% - 16px); margin-bottom: 10px; padding: 8px; border-radius: 5px; border: none; cursor: pointer;">비밀번호 일치 확인하기</button>
      <br>
      <p style="color: white;">닉네임</p>
      <input type="text" v-model="nickname" placeholder="닉네임을 입력해주세요." style="background-color: #ffffff; color: #3B85CE; width: calc(100% - 16px); margin-bottom: 10px; padding: 8px; border-radius: 5px; border: 1px solid #ccc;">
      <br>
      <button type="submit" style="background-color: #013B7A; color: white; width: calc(100% - 16px); margin-bottom: 10px; padding: 8px; border-radius: 5px; border: none; cursor: pointer;">가입 완료</button>
    </div>
  </div>
</template>

<script>
import EmailAuth from '../components/EmailAuth.vue'; // 이메일 인증 컴포넌트 임포트
import axios from "axios";

export default {
  components: {
    EmailAuth, // EmailAuth 컴포넌트 등록
  },
  data() {
    return {
      email: '',
      nickname: '',
      username: '',
      password1: '',
      password2: ''
    }
  },
  methods: {
    async signUp() {
      try {
        const response = await axios.post("http://localhost:8080/api/auth/signup", {
          email: this.email,
          nickname: this.nickname,
          username: this.username,
          password1: this.password1,
          password2: this.password2
        });
        // POST 요청에 대한 응답 처리
        console.log(response.data);

        // 회원가입 성공 시 로그인 페이지로 이동
        this.$router.push({
          name: "login",
        });

      } catch (error) {
        // 요청 실패 처리
        console.error(error);
      }
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column; /* 이미지 및 폼을 세로로 정렬하기 위해 추가 */
  height: 100vh; /* 화면 전체 높이 */
}

.signup-container {
  text-align: center;
  width: 350px; /* 폼의 너비를 조정합니다. */
  padding: 20px;
  border-radius: 10px; /* 모서리 둥근 사각형 */
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
  margin-top: 20px; /* 이미지와 간격 조정 */
}
.signup-container button {
  width: calc(100% - 16px);
  padding: 8px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.signup-form{
  background-color: #ffffff;
}

/* 로고 텍스트 스타일 */
.logo_txt {
  width: 200px; /* 로고 텍스트의 너비를 조정합니다. */
  height: auto; /* 높이 자동 조정 */
}
</style>
