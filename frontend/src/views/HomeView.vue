<template>
  <div class="cotn_principal">
    <div class="cont_centrar">
      <div class="cont_login">
        <div class="cont_info_log_sign_up">
          <!-- 에피 로고와 버튼 -->
          <div class="epi">
            <h1>에피</h1>
            <!-- 로그인과 회원가입 버튼을 한 줄에 배치 -->
            <div class="button-container">
              <!-- 로그인 버튼 -->
              <button class="btn_login" @click="goToLogin">로그인</button>
              <!-- 회원가입 버튼 -->
              <button class="btn_sign_up" @click="goToSignUp">회원가입</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const goToLogin = () => {
  router.push({ name: 'login' });
};

const goToSignUp = () => {
  router.push({ name: 'signup' });
};
</script>

<style scoped>
/* 나머지 스타일은 여기에 복사해주세요 */
.epi {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

.epi h1 {
  color: black;
  font-weight: bold;
}

.button-container {
  margin-top: 20px;
}

.btn_login,
.btn_sign_up {
  background-color: #007bff;
  border: none;
  padding: 10px;
  width: 200px;
  border-radius: 3px;
  box-shadow: 1px 5px 20px -5px rgba(0, 0, 0, 0.4);
  color: #fff;
  cursor: pointer;
}

.btn_sign_up {
  background-color: #ef5350;
  margin-left: 10px; /* 로그인 버튼과의 간격 조절 */
}
</style>
