import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
Vue.config.productionTip = false
Vue.use(mavonEditor)
Vue.use(ElementUI)
Vue.prototype.msgSuccess = function (msg) {
  this.$message.success(msg)
}

Vue.prototype.msgError = function (msg) {
  this.$message.error(msg)
}

Vue.prototype.msgInfo = function (msg) {
  this.$message.info(msg);
}

import router from '@/router'
new Vue({
  render: h => h(App),
  router
}).$mount('#app')
