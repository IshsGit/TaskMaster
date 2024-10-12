import { defineComponent, ref, computed } from 'vue';

export const TaskFilter = defineComponent({
  name: 'TaskFilter',
  props: {
    tasks: {
      type: Array,
      required: true
    }
  },
  setup(props) {
    const filterOption = ref('all');

    const filteredTasks = computed(() => {
      if (filterOption.value === 'completed') {
        return props.tasks.filter(task => task.completed);
      } else if (filterOption.value === 'incomplete') {
        return props.tasks.filter(task => !task.completed);
      }
      return props.tasks;
    });

    const setFilter = (option) => {
      filterOption.value = option;
    };

    return () => (
      <div class="task-filter">
        <h2>Filter Tasks</h2>
        <div class="filter-options">
          <button onClick={() => setFilter('all')} class={{ active: filterOption.value === 'all' }}>
            All
          </button>
          <button onClick={() => setFilter('completed')} class={{ active: filterOption.value === 'completed' }}>
            Completed
          </button>
          <button onClick={() => setFilter('incomplete')} class={{ active: filterOption.value === 'incomplete' }}>
            Incomplete
          </button>
        </div>
        <ul class="task-list">
          {filteredTasks.value.map((task, index) => (
            <li key={index} class={{ completed: task.completed }}>
              {task.name}
            </li>
          ))}
        </ul>
      </div>
    );
  }
});
