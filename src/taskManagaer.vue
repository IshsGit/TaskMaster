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

    // Add a new task if the input is not empty
    const addTask = () => {
      const taskName = newTask.value.trim();
      if (taskName) {
        tasks.value.push({ name: taskName, completed: false });
        newTask.value = '';
      }
    };

    // Remove a task by its index
    const deleteTask = (index) => tasks.value.splice(index, 1);

    // Toggle the completion status of a task
    const toggleTaskCompletion = (index) => {
      tasks.value[index].completed = !tasks.value[index].completed;
    };

    // Add task on pressing 'Enter' key
    const handleKeyup = (e) => {
      if (e.key === 'Enter') addTask();
    };

    return () => (
      <div class="taskmanager">
        <h1>Task Manager</h1>
        <div class="input-group">
          <input
            type="text"
            v-model={newTask.value}
            placeholder="Add a new task"
            onKeyup={handleKeyup}
          />
          <button onClick={addTask}>Add Task</button>
        </div>

        {/* Render the TaskList component */}
        <TaskList
          tasks={tasks.value}
          toggleTaskCompletion={toggleTaskCompletion}
          deleteTask={deleteTask}
        />
      </div>
    );
  },
});
