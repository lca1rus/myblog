import Vue from 'vue'
import App from './App.vue'
import Element from 'element-ui'

Vue.config.productionTip = false

Vue.use(Element)
//全局方法
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
