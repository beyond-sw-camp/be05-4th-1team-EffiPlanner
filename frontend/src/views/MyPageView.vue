<template>
    <div class="user-info">
        <h2>사용자 정보</h2>
        <div class="info-item">
            <label>아이디:</label>
            <p>{{ userInfo.userId }}</p>
        </div>
        <div class="info-item">
            <label>이름:</label>
            <p>{{ userInfo.name }}</p>
        </div>
        <div class="info-item">
            <label>이메일:</label>
            <p>{{ userInfo.email }}</p>
        </div>
        <button class="reset-password-btn" @click="showPwdResetModal = true">비밀번호 재설정</button>
        <button class="delete-account-btn" @click="showDeleteModal = true">회원 탈퇴</button>

        <!-- 회원 탈퇴 모달 -->
        <div v-if="showDeleteModal" class="modal">
            <div class="modal-content">
                <h2>회원 탈퇴</h2>
                <h6>확인을 위해 비밀번호를 다시 한 번 입력해주세요</h6>
                <br />
                <input v-model="password" type="password" placeholder="비밀번호">
                <div class="button-container">
                    <button class="cancel-btn" @click="cancelDeletion">취소</button>
                    <button class="delete-btn" @click="deleteAccount">회원 탈퇴</button>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios'

export default {
    name: 'UserInfo',

    props: {
        userInfo: {
            type: Object,
            required: true
        }
    },

    setup(props, { emit }) {
        const router = useRouter();
        const showDeleteModal = ref(false);
        const password = ref('');
        const email = ref('');

        const resetResult = ref('');

        const deleteAccount = async () => {
            try {
                const response = await axios.delete('http://localhost:8080/api/auth/signout', {
                    data: {
                        userId: localStorage.getItem('userId'),
                        password1: password.value,
                    }
                });
                if (response.status === 200) {
                    console.log('회원 탈퇴 성공:', response.data);
                    localStorage.removeItem('token');
                    localStorage.removeItem('userId');
                    alert('회원 탈퇴가 완료되었습니다.');
                    router.push('/');
                } else {
                    console.log('회원 탈퇴 실패', response.data);
                    alert('회원 탈퇴에 실패했습니다.');
                }
            } catch (error) {
                console.error('회원 탈퇴 오류:', error);
            }
            showDeleteModal.value = false;
        };

        const cancelDeletion = () => {
            showDeleteModal.value = false;
        };

        const fetchUserInfo = async () => {
            try {
                const response = await axios.post('http://localhost:8080/api/auth/userinfo', {
                    userId: localStorage.getItem('userId')
                });
                emit('update:userInfo', response.data);
                console.log("사용자 정보:", response.data);
            } catch (error) {
                console.error('사용자 정보 불러오기 실패:', error);
            }
        };

        onMounted(() => {
            fetchUserInfo();
        });

        return {
            showDeleteModal,

            password,
            email,
            resetResult,
            deleteAccount,
            cancelDeletion,
        }
    }
}
</script>

<style scoped>
.user-info {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
}

h2 {
    font-size: 1.5rem;
    margin-bottom: 20px;
}

.info-item {
    margin-bottom: 10px;
}

label {
    font-weight: bold;
}

button {
    padding: 10px 20px;
    margin-right: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.reset-password-btn {
    background-color: #007bff;
    color: #fff;
}

.delete-account-btn {
    background-color: #dc3545;
    color: #fff;
}

.modal {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
}

.modal-content {
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
    padding: 20px;
    max-width: 50%;
    /* Reduced width */
}

.modal-content h2 {
    margin-bottom: 20px;
}

.modal-content input[type="password"] {
    width: calc(100% - 20px);
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}

.button-container {
    display: flex;
    justify-content: flex-end;
    margin-top: 20px;
    /* Increase spacing between buttons */
}

.cancel-btn,
.delete-btn {
    padding: 8px 16px;
    /* Reduced button size */
    margin-left: 10px;
    /* Increase spacing between buttons */
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.cancel-btn {
    background-color: #dc3545;
    color: #fff;
}

.delete-btn {
    background-color: #007bff;
    color: #fff;
}

.cancel-btn:hover,
.delete-btn:hover {
    opacity: 0.8;
}

.auth-wrapper {
    display: flex;
    flex-direction: row;
    justify-content: space-between;

}

.button-wrapper {
    background-color: lightseagreen;
    color: black;
    border-radius: 5px;
    padding: 10px 20px;
    text-align: center;
    cursor: pointer;
    margin-top: 20px;
    margin-bottom: 10px;
    margin-left: auto;
}

.input-class {
    width: 80%;
}
</style>