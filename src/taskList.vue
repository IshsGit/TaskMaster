// TaskList.vue
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'TaskList',
  props: {
    tasks: {
      type: Array,
      required: true,
    },
    toggleTaskCompletion: {
      type: Function,
      required: true,
    },
    deleteTask: {
      type: Function,
      required: true,
    },
  },
  setup(props) {
    return () => (
      <ul class="task-list">
        {props.tasks.map((task, index) => (
          <li key={index} class={{ completed: task.completed }}>
            <span
              onClick={() => props.toggleTaskCompletion(index)}
              class="task-name"
            >
              {task.name}
            </span>
            <button onClick={() => props.deleteTask(index)} class="delete-btn">
              Delete
            </button>
          </li>
        ))}
      </ul>
    );
  },
});
