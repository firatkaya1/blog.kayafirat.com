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
.alert-success {
    background-color: green;
    color: white;
}
</style>