<template>
  <div>
    <el-input placeholder="输入用户昵称进行搜索"
              prefix-icon="el-icon-search"
              style="width: 200px;margin: 0 10px"
              v-model="nameKeyword">
    </el-input>
    <el-button @click="initUserData" icon="el-icon-search" type="primary">搜索</el-button>
    <el-button @click="refreshTable" icon="el-icon-refresh" type="primary">刷新表格</el-button>
    <router-link to="/WriteBlog">
      <el-button  icon="el-icon-edit" type="primary">写一篇博客</el-button>
    </router-link>
    <el-table style="width: 100%" border :data="bloglists.slice((page-1)*limit,page*limit)" @cell-mouse-enter="handleCellEnter" @cell-mouse-leave="handleCellLeave" >
      <el-table-column prop="id"  label="序号" width="80px" align="center">
         </el-table-column>
      <el-table-column prop="title" label="博客名称" width="" align="center">
         <template slot-scope="scope">
           <el-input v-if = "scope.row.edit" class="item" v-model="scope.row.title" placeholder="请输入内容"></el-input>
           <div v-else class="text">{{scope.row.title}}</div>
         </template>
      </el-table-column>
      <el-table-column prop="content" label="博客内容" width="" align="center" show-overflow-tooltip>
         <template slot-scope="scope">
           <el-input v-if = "scope.row.edit" class="item" v-model="scope.row.content" placeholder="请输入内容"></el-input>
           <div v-else class="text">{{scope.row.content}}</div>
         </template>
      </el-table-column>
      <el-table-column prop="users.username" label="博客作者" width="140" align="center">
         <template slot-scope="scope">
           <el-input v-if = "scope.row.edit" class="item" v-model="scope.row.userLoginMsg.username" placeholder="请输入内容"></el-input>
           <div v-else class="text">{{scope.row.userLoginMsg.username}}</div>
         </template>
      </el-table-column>
       <el-table-column fixed="right" label="操作" width="240" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="update(scope.row)">编辑</el-button>
          <el-button type="text" size="small" @click="cancle(scope.row)">完成编辑</el-button>
          <el-button type="text" size="small" @click="DeleteById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
<el-pagination
      style="margin-top:10px;textAlign:center"
      :current-page="page"
      :total="total"
      :page-size="limit"
      :page-sizes="[3,5,10]"
       @current-change="handleCurrentChange"
       @size-change="handleSizeChange"
       layout="prev,pager,next,jumper,->,sizes,total"
     >
 </el-pagination>
  </div>
</template>

<script>
import {getAdminBlogList,deleteBlogs,updateBlog,GetBlogByName} from "@/plugins/blog";



export default {
   name:'BlogList',
   data(){
    return{
      //设置初始值
      obj:{
        index:Number(''),
        bkName:'',
        bkContent:'',
        bkWriter:'',
        isEdit:false
      },
     bloglists:[],
      page:1,
      limit:10,
      nameKeyword:'',
    }
   },
  created() {
    this.initUserData();
  },
   methods: {
     //刷新表格
     refreshTable(){
       this.nameKeyword='';
       this.initUserData();
     },
     // //获取表格数据
     initUserData(){
       if (this.nameKeyword!=''){//当搜索字不为空时,执行搜索
         GetBlogByName(this.nameKeyword).then(resp=>{
           if (resp.code === 200) {
             this.msgSuccess(resp.msg);
             this.bloglists = resp.data;
             setTimeout(() => {
               this.loading = false;
             }, 1000)
           }
           else {
             this.msgError(res.msg)
           }
         })}
       else {
         getAdminBlogList(1).then(resp => {
           if (resp.code === 200) {
             this.msgSuccess(resp.msg);
             this.bloglists = resp.data.list;
             setTimeout(() => {
               this.loading = false;
             }, 1000)
           }
           else {
             this.msgError(res.msg)
           }
         })
       }
     },

    handleCurrentChange(pager){
       //console.log(pager)
       //修改参数
      this.page=pager
    //  this.getPageList()
    },
    //当分页器每一页展示数据条数发生变化时
    handleSizeChange(limit){
        //console.log(limit)
        this.limit= limit
       // this.getPageList()
    },
    add(){
        this.list.push(JSON.parse(JSON.stringify(this.obj)))
      },
     DeleteById(id) {
      // 设置类似于console类型的功能
      this.$confirm("永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
          .then(() => {
            deleteBlogs(id).then(res => {
              this.msgSuccess(res.msg)
              this.getbloglist()
            })
          })
          .catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
     },



    update(row){
      row.edit = true;
    },


     cancle(row) {
      this.$confirm("永久更新该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning" })
          .then(() => {
      updateBlog(row.id,row.title,row.content).then(res  => {
        this.msgSuccess(res.msg)
            this.getbloglist()
              })
          }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消更新'
        })
          }
      )
       row.edit = false;
       
    },
  
   },
}
</script>

<style>

</style>