import Vue from 'vue'
import App from './App.vue'
import VueRouter from "vue-router";

import store from "./modules/store.js";
import router from "./modules/router.js";

Vue.use(VueRouter);

new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})
