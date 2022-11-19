<template>
  <div>
    <el-form :model="form" :rules="formRules" ref="form" label-position="top">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="标题" prop="title">
            <el-input v-model="form.title" placeholder="请输入标题"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
        <el-form-item label="文件：" prop="firstPicture" >
          <el-upload
              class="upload-import"
              ref="uploadImport"
              action=""
              :limit="1"
              :auto-upload="false"
              accept="application/zip,.zip">
          <el-button  slot="trigger" size="small" type="primary" >选取文件</el-button>
          <div slot="tip" class="el-upload__tip">只能上传zip文件，且不超过10M</div>
          </el-upload>
        </el-form-item>
        </el-col>
        <router-link to="/Home">
          <el-button  icon="el-icon-s-home" type="primary">返回首页</el-button>
        </router-link>
        <el-col :span="12">
          <el-form-item label="图片URL" prop="firstPicture">
            <el-input v-model="form.firstPicture" placeholder="文章首图，用于随机文章展示"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="文章正文" prop="content">
        <mavon-editor v-model="form.content"/>
      </el-form-item>

      <el-form-item style="text-align: right;">
        <el-button type="primary" @click="dialogVisible=true">保存</el-button>
      </el-form-item>
    </el-form>


    <el-dialog title="博客可见性" width="30%" :visible.sync="dialogVisible">

      <el-form label-width="50px" @submit.native.prevent>
        <el-form-item>
          <el-radio-group v-model="radio">
            <el-radio :label="1" >公开</el-radio>
            <el-radio :label="2">私密</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="radio!==2">
          <el-row>
            <el-col :span="6">
              <el-switch v-model="form.recommend" active-text="推荐"></el-switch>
            </el-col>
            <el-col :span="6">
              <el-switch v-model="form.comments" active-text="评论"></el-switch>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
      <span slot="footer">
				<el-button @click="dialogVisible=false">取消</el-button>
				<el-button type="primary" @click="submit">保存</el-button>
			</span>
    </el-dialog>
  </div>
</template>

<script>
import {WriteBlog} from "@/plugins/blog";
export default {
  name: "WriteBlog",
  data() {

    return {
      dialogVisible: false,
      radio: 1,
      form: {
        title: '',
        firstPicture: '',
        content: '',
        recommend: -1,
        comment:-1,
        publish:-1,
      },
      formRules: {
        title: [{required: true, message: '请输入标题', trigger: 'change'}],
        firstPicture: [{required: true, message: '请输入首图链接', trigger: 'change'}],
      },
    }
  },
  watch: {
    'form.words'(newValue) {
      this.form.readTime = newValue ? Math.round(newValue / 200) : null
    },
  },
  created() {

  },
  methods: {
    submit() {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.radio === 1) {//公开的话打开评论与点赞数
             this.form.recommend= 0,
             this.form.comment=0,
            this.form.publish=0
          }
              WriteBlog(this.form).then(res => {
                if (res.code === 200) {
                  this.msgSuccess(res.msg)
                  console.log("成功")
                  this.$router.push('/home')
                }
                else {
                  this.msgError(res.msg)
                }

            })
        } else {
          this.dialogVisible = false
          return this.msgError('请填写必要的表单项')
        }
      })
    }

}}
</script>

<style scoped>

</style>