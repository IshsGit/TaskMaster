import { defineComponent, ref } from 'vue';
import TaskList from './TaskList.vue';

export const TaskManagerApp = defineComponent({
  name: 'TaskManagerApp',
  components: {
    TaskList,
  },
  setup() {
    const newTask = ref('');
    const tasks = ref([
      { name: 'Buy groceries', completed: false },
      { name: 'Walk the dog', completed: true },
    ]);

    const addTask = () => {
      const trimmedTask = newTask.value.trim();
      if (trimmedTask) {
        tasks.value.push({ name: trimmedTask, completed: false });
        newTask.value = '';
      }
    };

    const deleteTask = (index) => tasks.value.splice(index, 1);

    const toggleTaskCompletion = (index) => {
      tasks.value[index].completed = !tasks.value[index].completed;
    };

    const handleKeyup = (e) => {
      if (e.key === 'Enter') addTask();
    };

    return () => (
      <div class="taskmaster">
        <h1>Taskmaster</h1>
        <input
          type="text"
          v-model={newTask.value}
          placeholder="Add a new task"
          onKeyup={handleKeyup}
        />
        <button onClick={addTask}>Add Task</button>

        {/* Use the TaskList component */}
        <TaskList
          tasks={tasks.value}
          toggleTaskCompletion={toggleTaskCompletion}
          deleteTask={deleteTask}
        />
      </div>
    );
  },
});
