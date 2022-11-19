<template>
   <div id="app">
    <div class="sidebar">
      <mycard></mycard>
    </div>
    <div class="main">
     <ChatRoom></ChatRoom>
    </div>
  </div>
</template>

<script>
import ChatRoom from "./ChatRoom.vue"
import mycard from "../../components/chat/mycard.vue"
export default {
components:{
ChatRoom,
mycard
}
}
</script>

<style scoped>
#app{
  display: flex;
  border-radius :50px ;
  margin: 20px auto;
  width: 400px;
  height: 400px;
  background-color: #fff;
}
.sidebar{
  width: 80px;
    height: 615px;
    background: #2b2c2f
}
.main{
    flex: 1;
    height: 600px;
    margin-top: -10px;
    background: #f2f2f2
}
/* #app
  display: flex
  border-radius 50px
  margin: 20px auto
  width: 860px
  height: 600px
  background-color: #fff
  .sidebar
    width: 60px
    height: 600px
    background: #2b2c2f
  .main
    flex: 1
    height: 600px
    background: #f2f2f2 */
</style>