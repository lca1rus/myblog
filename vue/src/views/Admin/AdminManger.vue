<template>
  <div>
    <el-container>
  <el-header>后台管理系统</el-header>
  <el-container>
    <el-aside width="200px">
      <el-menu>
        <el-menu-item index="2">
        <router-link to="/BlogList" slot="title" tag="a"><span>博客管理</span></router-link>
      </el-menu-item>
      <el-menu-item index="3">
        <router-link to="/ViewerList" slot="title" tag="a" ><span>用户管理</span></router-link>
      </el-menu-item>
        <el-menu-item index="3">
          <router-link to="/Home" slot="title" tag="a" ><span>返回主页</span></router-link>
        </el-menu-item>

      </el-menu>
    </el-aside>
    <el-main>
      <router-view/>
    </el-main>
  </el-container>
</el-container>
  </div>
</template>

<script>

export default {

  data:{
    name:'AdminManger',


  },
}
</script>

<style>
 .el-header{
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
    height: 700px;
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  a{
    text-decoration: none;
    color:black;
  }
</style>