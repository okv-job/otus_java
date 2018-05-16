<template>
  <div class="status-container">
    <h1>status</h1>
    <div class="status-container-data" v-if="isLoaded">
        <div>
            <span>IdleTime: {{status.cacheConfig.idleTimeMs}}</span>
            <span>lifeTimeMs: {{status.cacheConfig.lifeTimeMs}}</span>
            <span>maxElements: {{status.cacheConfig.maxElements}}</span>
            <span>isIternal: {{status.cacheConfig.isIternal}}</span>
        </div>
        <div>
            <span>hitCount: {{status.hitCount}}</span>
            <span>missCount: {{status.missCount}}</span>
        </div>
    </div>
    <button class="btn" @click="logout">Logout</button>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "status",
  computed: {
    ...mapState(["status"])
  },
  data() {
    return {
      isLoaded: false
    };
  },
  mounted() {
    let that = this;
    this.$store.dispatch("getStatus").then(res => {
      if (!res) this.$router.push("/");
      that.isLoaded = true;
    });
  },
  methods: {
    logout() {
      this.$store.dispatch("logout").then(res => this.$router.push("/"));
    }
  }
};
</script>

<style scoped>
.status-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  text-align: center;
  margin: auto;
  background: whitesmoke;
  width: 450px;
  padding: 0;
  margin: auto;
}

.status-container-data {
  display: flex;
  justify-content: center;
  align-items: center;
}

.status-container-data > div {
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  flex-direction: column;
  padding: 20px;
  font-size: 1.2em;
}
</style>
