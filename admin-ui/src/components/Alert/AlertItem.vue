<template>
  <div>
    {{ alert.message }}
    <div class="exit">
      <img
        src="../../assets/img/x.svg"
        :style="{ width: '15px', height: '15px' }"
        @click="deleteAlert(alert.id)"
        alt=""
      />
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  name: "AlertItem",
  props: ["alert"],
  methods: mapMutations("alert", ["deleteAlert"]),
  mounted() {
    setTimeout(() => {
      this.deleteAlert(this.alert.id);
    }, 6000);
  },
};
</script>

<style scoped>
.exit {
  float: right;
  cursor: pointer;
}
</style>