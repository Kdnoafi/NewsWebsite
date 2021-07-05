import Vue from 'vue'
import App from './App.vue'
import './plugins/axios'
import router from "./router";

Vue.config.productionTip = false
import JwPagination from 'jw-vue-pagination';
Vue.component('jw-pagination', JwPagination);
import { BPagination } from 'bootstrap-vue'
Vue.component('b-pagination', BPagination)
import { PaginationPlugin } from 'bootstrap-vue'
Vue.use(PaginationPlugin)
import {BTable} from "bootstrap-vue";
Vue.component('b-table', BTable)

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
