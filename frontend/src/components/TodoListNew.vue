<template>
    <section class="mb-5">
        <div class="container">
            <div class="row justify-content-center m-2">
                <div class="col border border-primary rounded">
                    <input type="text" id="todo_input" class="form-control my-2" v-model="job" placeholder="할 일을 적어주세요">
                    <div class="row my-2">
                        <div class="col-6">
                            <input type="date" v-model="date" :min="today" class="form-control">
                        </div>
                        <div class="col-6">
                            <button type="button" class="btn btn-primary btn-sm rounded float-end" @click="onAddTodo">
                                추가하기
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
import { reactive, toRefs, inject } from 'vue'

export default {
    name: 'TodoListNew',
    setup() {
        const today = inject('today');
        const addTodo = inject('addTodo');

        const state = reactive({
            job: '',
            date: today,
            today,
            userId: '', // 유효한 userId?
            categoryId: '' // 유효한 categoryId??
        })

        const onAddTodo = async () => {
            if (state.job.trim().length === 0) {
                console.error('Error: 할 일을 입력해주세요');
                return;
            }

            const newTodo = {
                title: state.job,
                deadline: state.date,
                createdAt: new Date(),
                updatedAt: new Date(),
                deleteYn: false,
                doneYn: false,
                userId: state.userId,
                categoryId: state.categoryId,
            };

            try {
                await addTodo(newTodo);
                state.job = '';
                state.date = today;
            } catch (error) {
                console.error('Error adding new todo:', error);
            }
        }

        return {
            ...toRefs(state),
            onAddTodo,
        }
    },
}
</script>

<style></style>