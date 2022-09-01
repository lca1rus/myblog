<template>
  <div>
         <div class="msg">
                Don't have account? <router-link to="/register">Sing up</router-link>
         </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.msg {
    text-align: center;
    line-height: 80px;
}

.msg a  {
    text-decoration-line:  none;
    color: #a6c1ee;
}
</style>