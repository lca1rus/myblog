<template>
  <div class="father">
    <div class="ribbon">
      <a href="index.html"><span>Home</span></a>
      <router-link to="/AdminManger"><span>Admin</span></router-link>
      <router-link to="/WriteBlog"><span>Write</span></router-link>
      <a href="form.html"><span>Form</span></a>
      <router-link to="/login"><span>login</span></router-link>
      <router-link to="/ChatView"><span>Talk</span></router-link>
    </div>
    <div class="title2">
      <div class="article-title">
        <span>{{blog.title}}</span>
        <div class="article-info">
          <div class="first-line">
            <!-- 发表时间 -->
            <span class="iconrili">
              发表于 {{blog.createTime}}
            </span>
            <span class="separator">|</span>
            <span class="iconrili">
              更新于 {{blog.updateTime}}
            </span>
            <span class="separator">|</span>
            <span class="iconrili">
              <a href="">项目介绍</a>
            </span>
          </div>
          <div class="second-line">
            <!-- 发表时间 -->
            <span class="iconrili">
              字数统计: {{this.wordNum}}
            </span>
            <span class="separator">|</span>
            <span class="iconrili">
              阅读时常: {{this.readTime}}
            </span>
            <span class="separator">|</span>
            <span class="iconrili">
              浏览量:{{blog.views}}
            </span>
             <span class="separator">|</span>
            <span class="iconrili">
              评论数目:{{blog.comment}}
            </span>
          </div>
        </div>
      </div>
    </div>

    <div class="blogdetail">
      <div class="yonghu1"></div>
      <div class="wenzhang">
       {{blog.content}}
             </div>
    </div>

    <div class="pinlun">
      <div class="head">
        <img src="./video/2.jpg" alt="" />
        <input type="text" placeholder="请输入评论 . . ." />
        <button>发布评论</button>
      </div>
      <div class="content">
        <div class="first" v-for="item in comments" :key="item.id">
          <div class="conte">
            <a href="javascript:;" class="first-img">
              <img src="./video/3.jpg" alt="" />
            </a>
            <div class="first-content">
              <h3 class="first-username">{{ item.username }}</h3>
              <span class="first-time">{{ item.data }}</span>
              <p class="first-comment">
                {{ item.content }}
              </p>
              <span class="iconfont icon-B1" @click="show(item)"></span>
              <span class="iconfont icon-B2"></span>
            </div>
            <div class="reply-comment" v-if="item.display">
              <input
                type="text"
                placeholder="请输入评论 . . ."
                v-model="childComments"
                @keyup.enter="reply_sumbit(item, 0)"
              />
              <!-- 0为回复一级评论 -->
              <button @click="reply_sumbit(item, 0)">发表评论</button>
            </div>
          </div>
          <!-- 次级评论 -->
          <div class="second">
            <ul>
              <li v-for="(sons, index) in erji" :key="index">
                <div class="top">
                  <a href="javascript:;" class="second-img">
                    <img src="./video/3.jpg" alt="" />
                  </a>
                  <div class="second-content">
                    <h3 class="second-username">{{ sons.username }}</h3>
                    <span class="second-time">{{ sons.data }}</span>
                    <!-- 次级评论内容 xxx回复xxx：评论内容-->
                    <p class="second-comment">
                      <span class="second-reply">
                        <span class="to_reply">{{ sons.username }}</span>
                        回复
                        <span class="to_reply">{{ sons.to_username }}</span> :
                        {{ sons.content }}
                      </span>
                    </p>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="aside"></div>
  </div>
</template>

<script>
import {getArticle} from "@/plugins/blog";

export default {
  name: "Detailed",
  data() {
    return {
      blog: {},
      wordNum:0,
      readTime:0,
      comments: [
        {
          id: 1,
          username: "小王",
          data: "2020.10.16",
          content: "哈喽哈喽",
          display: false,
        },
        {
          id: 2,
          username: "小李",
          data: "2020.10.26",
          content: "哈哈哈哈",
          display: false,
        },
      ],
      erji: [
        {
          id: 1,
          username: "小李",
          data: "2020.11.10",
          content: "你在干嘛",
          to_username: "小王",
        },
      ],
    };
  },
  created(){
    this. initUserData();
  },
  methods: {
    initUserData() {
      getArticle(this.$route.params.id)
          .then(res => {

            this.blog = res.data;

            // 统计文章字数
            this.wordNum = this.deleteHTMLTag(this.blog.content).length;

            this.readTime = Math.round(this.wordNum / 400) + "分钟";
          }
          ).catch(
      )
    },
    deleteHTMLTag(content) {
      return content
          .replace(/<\/?[^>]*>/g, "")
          .replace(/[|]*\n/, "")
          .replace(/&npsp;/gi, "");
    },

    show() {
      Vue.set(vm.comments, "display", "true");
    },
  },
  mounted() {
    window.addEventListener('scroll',()=>{
      if(window.scrollY>500){
        this.$refs.ribbon.classList.remove('bk')
      }else{
        this.$refs.ribbon.classList.add('bk')
      }
    })
  }
};
</script>

<style>
.father {
  position: relative;
  height: 1900px;
  width: 100%;
  background-repeat: no-repeat;
  background-size: cover;
}
.aside {
  position: absolute;
  width: 200px;
  height: 900px;
  margin-top: 50px;
  background-color: bisque;
  opacity: 0.5;
  left: 1100px;
}
.blogdetail {
  float: left;
  margin-top: 50px;
  width: 800px;
  /*   height: 1000px; */
  background-color: white;
  opacity: 0.5;
  margin-left: 240px;
}
.title1 {
  height: 100px;
  /*   background-color: bisque; */
  font-size: 50px;
  line-height: 90px;
}
.yonghu1 {
  background-color: cadetblue;
  height: 80px;
}
.wenzhang {
  /*   background-color: aqua; */
  /* height: 600px; */
  font-size: 18px;
  line-height: 28px;
  word-break: break-all;
  text-overflow: ellipsis;
  display: -webkit-box;
  overflow: visible;
  margin-left: 29px;
  margin-right: 29px;
}
.pinlun {
  float: left;
  margin-top: 30px;
  width: 800px;
  height: 600px;
  background-color: white;
  opacity: 0.5;
  margin-left: 240px;
}

/* 评论框 */
.head {
  background-color: rgb(248, 248, 248);
  position: relative;
  height: 75px;
  border-radius: 5px;
}
/* 评论框 */
.head input {
  position: absolute;
  top: 13px;
  left: 80px;
  height: 45px;
  border-radius: 5px;
  outline: none;
  width: 65%;
  font-size: 20px;
  padding: 0 20px;
  border: 2px solid #f8f8f8;
}
/* 发布评论按钮 */
.head button {
  position: absolute;
  top: 13px;
  right: 20px;
  width: 120px;
  height: 48px;
  border: 0;
  border-radius: 5px;
  font-size: 20px;
  font-weight: 500;
  color: #fff;
  background-color: rgb(183, 187, 188);
  cursor: pointer;
  letter-spacing: 2px;
}

.head button:hover {
  font-weight: 600;
}
.head img {
  width: 55px;
  height: 55px;
  border-radius: 50%;
  position: absolute;
  top: 10px;
  left: 13px;
}
.content {
  display: flex;
  padding: 10px 10px 0px 0;
  flex-direction: column;
  position: relative;
}
.first {
  padding: 10px 10px 0px 0;
}
.conte {
  display: flex;
}
.first .first-img {
  flex: 1;
  text-align: center;
}
.first img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}
.first-username,
.second-username {
  color: #504f4f;
}
.first-content {
  flex: 9;
  width: 100%;
}
.first-time,
.second-time {
  color: #767575;
}
.first-comment,
.second-comment {
  margin-top: 5px;
}
.iconfont {
  font: 16px sans-serif;
  margin-right: 30px;
}
/* 评论框 */
.reply-comment {
  margin: 10px 0 0 0;
  padding-right: 0px;
}
.reply-comment input {
  height: 30px;
  border-radius: 5px;
  outline: none;
  width: 30%;
  font-size: 18px;
  padding: 0 20px;
  /* border: 2px solid #f8f8f8; */
  border: 2px solid rgb(175, 187, 191);
}
/* 发布评论按钮 */
.reply-comment button {
  width: 30%;
  height: 30px;
  border: 0;
  border-radius: 5px;
  font-size: 18px;
  font-weight: 500;
  color: #fff;
  background-color: rgb(183, 187, 188);
  cursor: pointer;
  letter-spacing: 2px;
  margin-left: 20px;
}
/* 鼠标经过字体加粗 */
.reply-comment button:hover {
  font-weight: 600;
}

.second {
  background-color: #f3f3f3;
  top: 100px;
  width: 700px;
  margin-left: 50px;
}
.second li {
  padding: 10px 10px 10px 0;
  border-bottom: 1px solid rgb(237, 237, 237);
  list-style-type: none;
}
.second .top {
  display: flex;
  position: relative;
}
.second-img {
  flex: 1;
  text-align: center;
}
.second img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}
.to_reply {
  color: rgb(106, 106, 106);
  display: inline;
}
.second-reply {
  margin-left: -420px;
}
.second-content {
  flex: 9;
}
.title2 {
  /*  background-color: aquamarine; */
  width: 100%;
  height: 400px;
  position: relative;
  background-image: url("./video/backs.png");
  background-repeat: no-repeat;
  background-size: cover;
  filter: brightness(70%);
}
.article-title {
  height: 200px;
  width: 50%;
  font-size: 2.5rem;
  margin-bottom: 0.4rem;
  /*  background-color: red; */
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  margin: auto;
}
.separator:first-child {
  display: none;
}
.separator {
  float: left;
}
.article-info i {
  font-size: 14px;
}
.article-info {
  font-size: 14px;
  line-height: 1.9;
  display: inline-block;
}
.article-info span {
  font-size: 90%;
}
.article-info-container {
  position: absolute;
  bottom: 1.3rem;
  padding: 0 5%;
  width: 100%;
  color: #eee;
  text-align: left;
}
.first-line {
  /* background-color: red; */
  height: 30px;
  width: 300px;
  margin-left: 200px;
  margin-top: 30px;
}
.iconrili {
  float: left;
}
.second-line {
 /*  background-color: red; */
  height: 30px;
  width: 340px;
  margin-left: 180px;
  margin-top: 10px;
}
</style>