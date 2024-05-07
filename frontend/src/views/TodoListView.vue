<template>
  <header>
    <hgroup class="my-5">
      <h1>To Do List</h1>
      <em>{{ today }}</em>
    </hgroup>
  </header>
  <todo-list-container v-if="accessTokenExists" />
</template>

<script>
import { defineComponent, inject, ref } from 'vue';
import TodoListContainer from '../components/TodoListContainer.vue';

export default defineComponent({
  name: 'TodoListView',
  components: {
    TodoListContainer,
  },
  setup() {
    const accessToken = localStorage.getItem('accessToken');
    const today = inject('today');
    console.log("상단에 오늘 날짜  출력 " + today);

    const accessTokenExists = ref(false);
    if (accessToken !== undefined && accessToken !== null) {
      accessTokenExists.value = true;
    }

    return { today, accessTokenExists };
  },
});
</script>

<style scoped>
hgroup {
  text-align: center;
  font-family: 'Arial Bold';
}

hgroup h1 {
  font-weight: bolder;
}
</style>
