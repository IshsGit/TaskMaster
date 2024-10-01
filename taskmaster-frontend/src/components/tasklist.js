<script setup>
import { defineComponent } from 'vue';

const TaskList = defineComponent({
  name: 'TaskList',
  props: {
    tasks: {
      type: Array,
      required: true,
    },
  },
  emits: ['toggle-completion', 'edit-task', 'remove-task'],
  methods: {
    toggleTaskCompletion(index) {
      this.$emit('toggle-completion', index);
    },
    editTask(index) {
      this.$emit('edit-task', index);
    },
    removeTask(index) {
      this.$emit('remove-task', index);
    },
  },
});

</script>

<template>
  <div class="task-list">
    <h2>Your Tasks</h2>
    <ul>
      {props.tasks.map((task, index) => (
        <li key={index} class="task-item">
          <input 
            type="checkbox" 
            checked={task.completed} 
            onChange={() => emit('toggle-completion', index)} 
          />
          <span class={{ 'completed-task': task.completed }}>
            {task.title}
          </span>
          <button onClick={() => emit('edit-task', index)}>Edit</button>
          <button onClick={() => emit('remove-task', index)}>Delete</button>
        </li>
      ))}
    </ul>
  </div>
</template>

<style scoped>
.task-list {
  margin-top: 20px;
}

.task-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.completed-task {
  text-decoration: line-through;
  color: gray;
}

button {
  margin-left: 10px;
  background-color: #e74c3c;
  color: white;
  border: none;
  padding: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #c0392b;
}
</style>
