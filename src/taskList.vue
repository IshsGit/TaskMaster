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
     
          </li>
        ))}
      </ul>
    );
  },
});
