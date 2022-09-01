<template>
<div class="container">
  <div class="login-wrapper">
   <Header v-show="$route.meta.show"></Header>
    <router-view></router-view>
   <Footer v-show="$route.meta.show"></Footer>
  </div>
</div>
</template>

<script>
import Header from './components/Header'
import Footer from './components/Footer'
export default {
  name: 'App',
  components: {
    Header,
    Footer
  }
}
</script>

<style>
* {
    padding: 0;
    margin: 0;
    font-family: 'Open Sans Light';
    letter-spacing: .05em;
}
html {
    height: 100%;
}

body {
    height: 100%;
}

.container {
    height: 100%;
    background-image: linear-gradient(to right,#fbc2eb,#a6c1ee);
}

.login-wrapper {
    background-color: #fff;
    width: 250px;
    height: 500px;
    padding: 0 50px;
    position: relative;
    left: 50%;
    border-radius: 15px;
    top:50%;
    transform: translate(-50%,-50%);
}
</style>
