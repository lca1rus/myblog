const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
})

module.exports = {
  devServer: {      
    host: 'localhost',
    port: 8081,
    https: false,
    hot: false,
    proxy: null,
  },
   //关闭eslint
  lintOnSave:false
};
