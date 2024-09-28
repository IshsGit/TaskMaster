import { defineComponent, ref } from 'vue';

export const TaskManagerApp = defineComponent({
  name: 'TaskManagerApp',
  setup() {
    const newTask = ref('');
    const tasks = ref([
      { name: 'Buy groceries', completed: false },
      { name: 'Walk the dog', completed: true },
    ]);

    const addTask = () => {
      if (newTask.value.trim()) {
        tasks.value.push({ name: newTask.value.trim(), completed: false });
        newTask.value = '';
      }
    };

    const deleteTask = (index) => {
      tasks.value.splice(index, 1);
    };

    const toggleTaskCompletion = (index) => {
      tasks.value[index].completed = !tasks.value[index].completed;
    };

    return () => (
      <div class="taskmaster">
        <h1>Taskmaster</h1>
        <input
          type="text"
          v-model={newTask.value}
          placeholder="Add a new task"
          onKeyup={(e) => (e.key === 'Enter' ? addTask() : null)}
        />
        <button onClick={addTask}>Add Task</button>
        <ul class="task-list">
          {tasks.value.map((task, index) => (
            <li key={index} class={{ completed: task.completed }}>
              <span onClick={() => toggleTaskCompletion(index)} class="task-name">
                {task.name}
              </span>
              <button onClick={() => deleteTask(index)} class="delete-btn">
                Delete
              </button>
            </li>
          ))}
        </ul>
      </div>
    );
  },
});
