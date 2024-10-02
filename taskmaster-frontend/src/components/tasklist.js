<script setup>
import { ref, defineComponent } from 'vue';
import { debounce } from 'lodash';

const TaskList = defineComponent({
  name: 'TaskList',
  props: {
    tasks: {
      type: Array,
      required: true,
    },
  },
  emits: ['toggle-completion', 'update-task', 'remove-task'],
  setup(props, { emit }) {
    const editingIndex = ref(-1);
    const editValue = ref('');

    const startEditing = (index, task) => {
      editingIndex.value = index;
      editValue.value = task.title;
    };

    const saveEdit = (index) => {
      emit('update-task', { index, newTitle: editValue.value });
      editingIndex.value = -1;
    };

    const cancelEdit = () => {
      editingIndex.value = -1;
    };

    const handleEditChange = debounce((value) => {
      editValue.value = value;
    }, 300);

    return {
      editingIndex,
      editValue,
      startEditing,
      saveEdit,
      cancelEdit,
      handleEditChange,
    };
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
          {editingIndex.value === index ? (
            <input 
              class="task-input-edit"
              type="text" 
              v-model={editValue.value}
              onInput={(e) => handleEditChange(e.target.value)}
            />
          ) : (
            <span 
              class={{
                'completed-task': task.completed,
                'task-title': true
              }}
              onDblClick={() => startEditing(index, task)}
            >
              {task.title}
            </span>
          )}
          {editingIndex.value === index ? (
            <div class="edit-actions">
              <button onClick={() => saveEdit(index)}>Save</button>
              <button onClick={cancelEdit}>Cancel</button>
            </div>
          ) : (
            <button class="delete-btn" onClick={() => emit('remove-task', index)}>Delete</button>
          )}
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

.task-input-edit {
  border: 1px solid #3498db;
  padding: 5px;
  font-size: 16px;
  flex-grow: 1;
}

.completed-task {
  text-decoration: line-through;
  color: gray;
}

.task-title {
  margin-right: auto;
  cursor: pointer;
  padding: 5px;
}

.edit-actions {
  margin-left: 10px;
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

.delete-btn {
  background-color: #e74c3c;
}

.delete-btn:hover {
  background-color: #c0392b;
}
</style>
