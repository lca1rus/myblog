<template>
 <div class="content">
		<div class="msglist">
			<search></search>
            <chatlist></chatlist>
		</div>
		<div class="chatbox">
            <message></message>
            <hhh></hhh>
            
		</div>
	</div>
</template>

<script>
import search from "../../components/chat/search.vue"
import chatlist from "../../components/chat/chatlist.vue"
import message from "../../components/chat/message.vue"
import hhh from '../../components/chat/hhh.vue'

export default {
    naem:'ChatRoom',
    data(){
        return{

        }
    },
    components:{
        search,
        chatlist,
        message,
        hhh
    }

}
</script>

<style scoped>
.content{
    display: flex;
    width: 800px;
/*     background-color: aquamarine; */
    flex-direction:row;
}
.msglist{
    width:250px;
    background:rgb(230,230,230) ;
}
.chatbox{
    flex:1;
   /*  background-color: blueviolet; */
}
/* .content
  display: flex
  width: 800px
  .msglist
    width: 250px
    background: rgb(230,230,230)
  .chatbox
    flex: 1 */
</style>