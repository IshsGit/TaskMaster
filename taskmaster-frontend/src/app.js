import { defineComponent } from 'vue';
import TaskManagerApp from './components/TaskManagerApp';

export default defineComponent({
  name: 'App',
  setup() {
    return () => (
      <div id="app">
        <TaskManagerApp />
      </div>
    );
  },
});
