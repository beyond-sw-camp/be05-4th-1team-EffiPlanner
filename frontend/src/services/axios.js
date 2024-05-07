import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
});

axiosInstance.interceptors.request.use(
    config => {
        const accessToken = localStorage.getItem('accessToken');
        if (accessToken) {
            config.headers.Authorization = `Bearer ${accessToken}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

export default axiosInstance;

export const fetchTodos = async () => {
  try {
    const response = await axiosInstance.get('/api/todo/all');
    return response.data;
  } catch (error) {
    console.error('할 일 목록을 불러오는 중 오류가 발생했습니다:', error);
    throw error;
  }
};

export const addNewTodo = async (todo) => {
  try {
    const response = await axiosInstance.post('/api/todo/add', todo);
    return response.data;
  } catch (error) {
    console.error('할 일을 추가하는 중 오류가 발생했습니다:', error);
    throw error;
  }
};

export const updateTodo = async (id, todo) => {
  try {
    const response = await axiosInstance.post(`/api/todo/update/${id}`, todo);
    return response.data;
  } catch (error) {
    console.error('할 일을 업데이트하는 중 오류가 발생했습니다:', error);
    throw error;
  }
};

export const deleteTodo = async (id, todo) => {
  try {
    const response = await axiosInstance.post(`/api/todo/delete/${id}`, todo);
    return response.data;
  } catch (error) {
    console.error('할 일을 삭제하는 중 오류가 발생했습니다:', error);
    throw error;
  }
};
