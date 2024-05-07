<template>
  <section class="mb-5">
      <div class="container">
          <div class="row justify-content-center m-2">
              <div class="col border border-primary rounded">
                  <input
                    type="text"
                    id="todo_input"
                    class="form-control my-2"
                    v-model="job"
                    placeholder="할 일을 적어주세요"
                  >
                  <div class="row my-2">
                      <div class="col-6">
                          <input
                            type="date"
                            v-model="date"
                            :min="today"
                          >
                      </div>
                      <div class="col-6">
                          <button
                            type="button"
                            class="btn btn-primary btn-sm rounded float-end"
                            @click="onAddTodo" 
                          >
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
        const val_obj = reactive({
            job: '',
            date: today,
            today: today,
        })

        const onAddTodo = () => {
            if (val_obj.job.length > 0) {
                addTodo({ title: val_obj.job, deadline: val_obj.date });
                val_obj.job = '';
                val_obj.date = today;
            }
        }

        return {
            ...toRefs(val_obj),
            onAddTodo,
        }
    },
}
</script>

<style>
</style>
