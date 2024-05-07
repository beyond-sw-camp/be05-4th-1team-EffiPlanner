<template>
    <todo-list-new />
    <section class="container">
        <div class="row justify-content-center m-2">
            <todo-list-main/>
        </div>
    </section>
</template>

<script>
import { ref, provide } from 'vue'
import { fetchTodos, addNewTodo, updateTodo, deleteTodo } from '../services/axios'
import TodoListNew from './TodoListNew.vue'
import TodoListMain from './TodoListMain.vue'

export default {
    name : 'TodoListContainer',

    components: {
        TodoListNew,
        TodoListMain 
    },
    
    setup() {
        const todos = ref([]);

        // 할 일 목록 불러오기
        const loadTodos = async () => {
            try {
                todos.value = await fetchTodos();
            } catch (error) {
                console.error('할 일 목록 불러오는 중 에러 발생:', error)
            }
        };

        // 할 일 추가 함수
        const addTodo = async (todo) => {
            try {
                const newTodo = await addNewTodo(todo);
                todos.value.push(newTodo);
            } catch (error) {
                console.error('할 일 추가하던 중 에러 발생:', error)
            }
        };

        // 할 일 업데이트 함수
        const updateExistingTodo = async (id, updatedTodo) => {
            try {
                const updated = await updateTodo(id, updatedTodo);
                const index = todos.value.findIndex(todo => todo.id === id);
                if (index !== -1) {
                    todos.value[index] = updated;
                }
            } catch (error) {
                console.error('할 일 업데이트 중 에러 발생:', error)
            }
        }

        // 할 일 삭제 함수
        const deleteExistingTodo = async (id) => {
            try {
                await deleteTodo(id);
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

<style>
</style>
