<template>
    <div class="task-search">
      <input v-model="searchQuery" @input="onSearch" placeholder="Search tasks..." />
    </div>
  </template>
  
  <script>
  import { defineComponent, ref } from 'vue';
  
  export default defineComponent({
    name: 'TaskSearch',
    emits: ['search'],
    setup(props, { emit }) {
      const searchQuery = ref('');
  
      const onSearch = () => {
        emit('search', searchQuery.value);
      };
  
      return {
        searchQuery,
        onSearch,
      };
    },
  });
  </script>
  
  <style scoped>
  .task-search input {
    width: 100%;
    padding: 8px;
  }
  </style>
  