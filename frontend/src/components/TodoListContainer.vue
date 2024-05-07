<template>
    <todo-list-new />
    <section class="container">
        <div class="row justify-content-center m-2">
            <todo-list-main />
        </div>
    </section>
</template>

<script>
import { ref, provide } from 'vue'
import TodoListNew from './TodoListNew.vue'
import TodoListMain from './TodoListMain.vue'
import axios from 'axios'


export default {
    name: 'TodoListContainer',

    components: {
        TodoListNew,
        TodoListMain
    },

    setup() {
        const todos = ref([]);

        // // 유저 ID 설정
        // const userId = 1 ;  // 로그인된 사용자의 ID



        // 할 일 목록 불러오기
        const loadTodos = async () => {
          try {
            const accessToken = localStorage.getItem('accessToken');
            console.log("accessToken:", accessToken);

            const response = await axios.get('http://localhost:8080/api/todo/all',{
              headers: {
                'Authorization': `Bearer ${accessToken}`
              }
            });
            todos.value = response.data;
          } catch (error) {
                console.error('할 일 목록 불러오는 중 에러 발생:', error);
            }
        };



        // 할 일 추가 함수
        const addTodo = async (todo) => {
            try {
                const response = await axios.post('http://localhost:8080/api/todo/add', {
                    ...todo,
                    deadline: `${todo.deadline}T00:00:00`,
                    createdAt: new Date(),
                    updatedAt: new Date(),
                    deleteYn: false,
                    doneYn: false,
                    userEmail: todo.userEmail ,
                    categoryId: todo.categoryId // 선택한 카테고리 ID 전달
                });
                todos.value.push(response.data);
            } catch (error) {
                console.error('할 일 추가하던 중 에러 발생:', error);
            }
        };

        // 할 일 업데이트 함수
        const updateExistingTodo = async (id, updatedTodo) => {
            try {
                const response = await axios.put(`http://localhost:8080/api/todo/update/${id}`, {
                    ...updatedTodo,
                    updatedAt: new Date().toISOString(),
                });
                const index = todos.value.findIndex(todo => todo.id === id);
                if (index !== -1) {
                    todos.value[index] = response.data;
                }
            } catch (error) {
                console.error('할 일 업데이트 중 에러 발생:', error);
            }
        }

        // 할 일 삭제 함수
        const deleteExistingTodo = async (id) => {
            try {
                await axios.delete(`http://localhost:8080/api/todo/delete/${id}`);
                const index = todos.value.findIndex(todo => todo.id === id);
                if (index !== -1) {
                    todos.value.splice(index, 1);
                }
            } catch (error) {
                console.error('할 일 삭제 중 에러 발생:', error);
            }
        }

        provide('todos', todos);
        provide('addTodo', addTodo);
        provide('updateExistingTodo', updateExistingTodo);
        provide('deleteExistingTodo', deleteExistingTodo);

        loadTodos();

        return {
            todos
        }
    }
}
</script>

<style></style>
