<template>
  <div>
    <div class="container">
      <div class="login-wrapper">
        <meta charset="GBK">

          <div class="header">Login</div>

          <el-form ref="loginForm" :model="loginForm" :rules="loginFormRules" class="login_form">
            <el-form-item prop="username" class="input-item">
              username:
              <el-input v-model="loginForm.username" prefix-icon="el-icon-user-solid"></el-input>
            </el-form-item>

            <el-form-item prop="password">
              password:
              <el-input v-model="loginForm.password" prefix-icon="el-icon-lock"
              ></el-input>
            </el-form-item>

            <el-form-item class="btn">
              <el-button id="login" type="primary" @click="login">login</el-button>
            </el-form-item>

          </el-form>
          <div class="msg">
            Don't have account?
            <router-link to="/register">Sing up</router-link>
          </div>

        <router-view />
      </div>
    </div>
  </div>
</template>

<script>

import {login} from "@/plugins/login"
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: 'du',
        password: '1234'
      },
      loginFormRules: {
        username: [
          {required: true, message: 'username?', trigger: 'blur'},
        ],
        password: [
          {required: true, message: 'password?', trigger: 'blur'},
        ]
      }
    }
  },
  methods: {
    login() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          login(this.loginForm.username, this.loginForm.password)
              .then(res => {
                if (res.code === 200) {
                  this.msgSuccess(res.msg);
                  window.localStorage.setItem('token', res.data.token)
                  window.localStorage.setItem('username',this.loginForm.username)
                  //登录成功将token放在session中
                  this.$router.push({path: '/'})
                }
                else {
                  this.msgError(res.msg)
                }
              })
              .catch(() => {
                this.msgError("密码错误")
              })
        }
      })
    }
  }
}

</script>

<style>
* {
  padding: 0;
  margin: 0;
  font-family: "Open Sans Light";
  letter-spacing: 0.05em;
}
html {
  height: 100%;
}
body {
  height: 100%;
}
.container {
  position:fixed;
  width: 100%;
  height: 100%;
  background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
}
.login-wrapper {
  background-color: #fff;
  width: 250px;
  height: 500px;
  padding: 0 50px;
  /*  position: relative; */
  left: 50%;
  border-radius: 15px;
  top: 500%;
  transform: translate(600px, 100px);
}
.header {
  font-size: 30px;
  font-weight: bold;
  text-align: center;
  line-height: 200px;
}
.input-item {
  display: block;
  width: 100%;
  margin-bottom: 20px;
  border: none;
  padding: 10px;
  border-bottom: 2px solid rgb(128, 125, 125);
  font-size: 15px;
  outline: none;
}
.input-item::placeholder {
  text-transform: uppercase;
}
.btn {
  text-align: center;
  padding: 5px;
  margin-top: 40px;
  background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
  color: #fff;
}
#login {
  width: 100px;
  margin-left: 70px;
}
.msg {
  text-align: center;
  line-height: 80px;
}
.msg a {
  text-decoration-line: none;
  color: #a6c1ee;
}
</style>