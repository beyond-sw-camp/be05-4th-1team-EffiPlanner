<template>
  <div>
    <!-- 이메일 입력 -->
    <label style="color: white;">이메일</label>
    <br>
    <input type="email" v-model="email" placeholder="이메일을 입력해주세요." style="color: #3B85CE; background-color: #ffffff; width: calc(100% - 16px); margin-bottom: 10px; padding: 8px; border-radius: 5px; border: 1px solid #ccc;">

    <!-- 이메일 인증하기 버튼 -->
    <button @click="authenticateEmail" style="background-color: #013B7A; color: white; width: calc(100% - 16px); margin-bottom: 10px; padding: 8px; border-radius: 5px; border: none; cursor: pointer;">이메일 인증하기</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      email: '' // 이메일 입력값을 저장할 데이터
    };
  },
  methods: {
    async authenticateEmail() {
      try {
        // 이메일 인증 요청을 서버에 보냅니다.
        const response = await axios.post("http://localhost:8080/user/auth/send", {
          email: this.email
        });

        // 서버로부터의 응답을 처리합니다.
        console.log(response.data); // 응답 데이터를 콘솔에 출력하거나 필요한 작업을 수행합니다.
        
        // 이메일 인증이 성공했다는 메시지를 알립니다.
        alert('이메일로 인증번호가 전송되었습니다.');
      } catch (error) {
        // 요청 실패 처리
        console.error(error);
        alert('이메일 인증에 실패했습니다.');
      }
    }
  }
}
</script>
