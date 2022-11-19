<template>
  <div class="father">
    <div class="blogs">
      <div
        class="bloglist"
        @click="details(item.id)"
        v-for="item in blog"
        :key="item.id"
      >
        <div class="Title1">
          <span class="TitleSon">{{ item.title }}</span>
        </div>
        <div class="user"></div>
        <div class="Content">
          <div class="ContentSon">
            {{ item.content }}
          </div>
        </div>
        <div class="btt">
          <div class="talk">
            <span class="iconfont icon-B2" >
              {{item.comment}}
            </span>
          </div>
          <div class="good">
            <span class="iconfont icon-B1" >
              {{item.recommend }}</span>
          </div>
          <div class="view1">
            <span class="iconfont icon-B">
              {{item.views}}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="aside"></div>

    <div class="diye">
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="pageSizes"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
    </div>
  </div>
</template>

<script>
import {getIndexBlogList} from "@/plugins/blog";
export default {
  name: "Blog",
  data() {
    return {
      blog: [],
      currentPage: 1, //当前页
      total:0, //总条数
      tableData: [], //当前页码的数据
      pageSize: 6, //当前页容量
      pageSizes: [],
    };
  },
  created(){
    this.getbloglist();
  },
  methods: {
    getbloglist() {
      getIndexBlogList()
          .then(res => {
            this.blog = res.data.list;
            this.total=res.data.totalPage;
          })
    },

    details(id) {
      this.$router.push({path:`/Detailed/${id}`});
    },

    view(id) {
       this.$router.push({path:`/Detailed/${id}`});
    },
    getlist(){
       this.blog = blog;
       this.total = blog.length;
       this.pageSize=pageSize;
       this.currentPage = currentPage;
       this.tableData = this.getNeedArr(this.blog, this.pageSize)[this.currentPage - 1]  //当前页的表格数据
    },
    getNeedArr(array, size){
       const length = array.length;
      //判断不是数组，或者size没有设置，size小于1，就返回空数组
     /*  if (!length || !size || size < 1) {
        return [];
      } */
      
      let index = 0; //用来表示切割元素的范围start
      let resIndex = 0; //用来递增表示输出数组的下标
 
      //根据length和size算出输出数组的长度，并且创建它。
      let result = new Array(Math.ceil(length / size));
      //进行循环
      while (index < length) {
        //循环过程中设置result[0]和result[1]的值。该值根据array.slice切割得到。
        result[resIndex++] = array.slice(index, (index +=size));
      }
      //输出到新数组
      return result;
    },
   //切换每页条数容量
    handleSizeChange(val) {       
      this.currentPage = 1;
      this.pageSize = val;
      this.getList();
    },
    //切换当前页
    handleCurrentChange(val) {    
      this.currentPage = val;
      this.getList();
    },
  },
};
</script>

<style>
.father{
  position: relative;
}
.aside {
  
  position:absolute;
  width: 200px;
  height: 900px;
  background-color: bisque;
  left: 1100px;
}
.blogs {
  float: left;
  width: 800px;
  background-color: white;
  margin-left: 240px;
 
}
.bloglist {
  height: 160px;
  width: 700px;
  margin-left: 50px;
  /* margin-top: 25px;
  margin-bottom: 25px; */
  border-bottom: 1px darkgray solid;
  background-color: white;
}
.bloglist:hover {
  cursor: pointer;
}
.Title1 {
  width: 500px;
  height: 40px;
  /*  background-color: blueviolet; */
  margin-left: 20px;
}
.user{
  background-color: cadetblue;
  height: 90px;
  float: right;
  width: 150px;
  
}
.TitleSon {
  display: inline-block;
  margin-top: 10px;
  margin-left: 20px;
}
.Content {
  width: 500px;
  height: 100px;
/*   background-color: brown; */
  margin-left: 20px;
}
.ContentSon {
  width: 500px;
  height: 100px;
  font-size: 13px;
  line-height: 23px;
  word-break: break-all;
  word-break: break-all;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
}
.iconfont {
  float: right;
  margin-right: 40px;
  font: 10px sans-serif;
}
.iconfont:hover {
  color: red;
}
.diye {
  float: left;
  margin-top: 50px;
  margin-left: 500px;
}
</style>