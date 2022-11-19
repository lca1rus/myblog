<template>
	<div class="ff">
		<div >
               <Header/>
		</div>
		
		<div class="main">
			<div>
				<Blog/>
			</div>
		</div>
		<!--APlayer-->
		<div class="m-mobile-hide">
			<MyAPlayer/>
		</div>
	</div>
</template>

<script>
import Header from "../components/index/Header"
import MyAPlayer from"../components/index/MyAplayer.vue"
import Blog from "./blog/IndexBlog.vue"
export default {
 name:'Index',
 components:{Header,MyAPlayer,Blog}
}
</script>

<style>
.main {
		margin-top: 40px;
		flex: 1;
	}
	body{
		background-color: rgb(242, 245, 248);
	}

	
</style>