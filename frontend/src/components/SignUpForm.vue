<!-- SignUpForm.vue -->
<template>
  <form @submit.prevent="signUp">
    <input v-model="formData.email" type="text" placeholder="이메일">
    <input v-model="formData.userNickname" type="text" placeholder="닉네임">
    <input v-model="formData.username" type="text" placeholder="사용자명">
    <input v-model="formData.password1" type="password" placeholder="비밀번호">
    <input v-model="formData.password2" type="password" placeholder="비밀번호 확인">
    <button type="submit">회원가입</button>
  </form>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const formData = ref({
  email: '',
  userNickname: '',
  username: '',
  password1: '',
  password2: ''
});

const signUp = () => {
  if (formData.value.password1 !== formData.value.password2) {
    alert('비밀번호가 일치하지 않습니다.');
    return;
  }

  axios.post('/signup', formData.value)
      .then(response => {
        alert('회원가입이 완료되었습니다.');
        // 회원가입이 성공하면 로그인 페이지로 이동하거나 다른 동작을 수행할 수 있습니다.
      })
      .catch(error => {
        alert('회원가입에 실패했습니다.');
        console.error('회원가입 오류:', error);
      });
};
</script>
