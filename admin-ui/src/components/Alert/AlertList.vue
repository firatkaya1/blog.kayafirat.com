<template>
  <div class="box">
    <div
      v-for="alert in alerts"
      :key="alert.id"
      class="alert item"
      v-bind:class="{
        'alert-success': alert.type === 'success',
        'alert-danger': alert.type === 'error',}">
      <AlertItem v-bind:alert="alert" />
    </div>
  </div>
</template>

<script>
import AlertItem from "./AlertItem";
import { mapState } from "vuex";
export default {
  name: "AlertList",

  computed: mapState({
    alerts: (state) => state.alert.alerts,
  }),
  components: {
    AlertItem,
  },
};
</script>


<style scoped>
.box {
  position: fixed;
  right: 40px;
  height: auto;
  margin-top: 5px;
  z-index: 50;
  max-width: 500px;
  min-width: 500px;
  bottom: 30px;
}
.alert {
  border: 1px solid #1e1e1e;
  padding: 15px 10px;
  border-radius: 5px;
  margin-top: 15px;
}
.alert-success {
  background-color: #6DB33F;
  color: white;
}
.alert-danger {
  background-color: rgb(224, 6, 6);
  color: white;
}
</style>