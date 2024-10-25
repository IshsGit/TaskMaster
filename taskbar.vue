<template>
    <div class="task-progress-bar">
      <div class="progress" :style="{ width: progressPercentage + '%' }"></div>
    </div>
  </template>
  
  <script>
  import { defineComponent, computed } from 'vue';
  
  export default defineComponent({
    name: 'TaskProgressBar',
    props: {
      tasks: {
        type: Array,
        required: true,
      },
    },
    setup(props) {
      const progressPercentage = computed(() => {
        const total = props.tasks.length;
        const completed = props.tasks.filter(task => task.completed).length;
        return total === 0 ? 0 : (completed / total) * 100;
      });
  
      return {
        progressPercentage,
      };
    },
  });
  </script>
  
  <style scoped>
  .task-progress-bar {
    width: 100%;
    height: 20px;
    background-color: #f0f0f0;
    border-radius: 5px;
    margin-top: 10px;
  }
  .progress {
    height: 100%;
    background-color: #4caf50;
    border-radius: 5px;
  }
  </style>
  