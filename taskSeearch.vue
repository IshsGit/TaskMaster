<template>
    <div class="task-search">
      <input v-model="searchQuery" @input="emitSearch" placeholder="Search tasks..." />
    </div>
  </template>
  
  <script>
  import { defineComponent, ref } from 'vue';
  
  export default defineComponent({
    name: 'TaskSearch',
    emits: ['search'],
    setup(_, { emit }) {
      const searchQuery = ref('');
  
      const emitSearch = () => emit('search', searchQuery.value);
  
      return {
        searchQuery,
        emitSearch,
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
  