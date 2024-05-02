// router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '../views/LoginView.vue';
import TodoListView from '../views/TodoListView.vue'


const routes = [
  {
    path: '/',
    name: 'Login',
    component: LoginView
  },
  {
    path: '/todo',
    name: 'TodoListView',
    component: TodoListView
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router;
