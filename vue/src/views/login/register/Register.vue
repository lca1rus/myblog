<template>
  <div class="register-container">
    <!-- 注册内容 -->
    <div class="register">
      <h3>注册新用户
        <span class="go">我有账号，去 <a href="login.html" target="_blank">登陆</a>
        </span>
      </h3>
      <div class="content">
        <label>手机号:</label>
        <input type="text" placeholder="请输入你的手机号">
      </div>
      <div class="content">
        <label>验证码:</label>
        <input type="text" placeholder="请输入验证码">
      </div>
      <div class="content">
        <label>登录密码:</label>
        <input type="text" placeholder="请输入你的登录密码">
      </div>
      <div class="content">
        <label>确认密码:</label>
        <input type="text" placeholder="请输入确认密码">
      </div>
      <div class="controls">
        <input name="m1" type="checkbox">
        <span>同意协议并注册《用户协议》</span>
      </div>
      <div class="btn">
        <button>完成注册</button>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Register'
  }
</script>

<style lang="less" scoped>
  .register-container {
    .register {
      width: 199px;
      height: 99px;
      border: 1px solid rgb(223, 223, 223);
      margin: 0 auto;

      h3 {
       /*  background: #ececec; */
        margin: 0;
        padding: 6px 15px;
        color: #333;
        border-bottom: 1px solid #dfdfdf;
        font-size: 30px;
        line-height: 30.06px;
        border-width: 0px;

        span {
          font-size: 14px;
          float: right;

          a {
            color: #e1251b;
          }
        }
      }

      div:nth-of-type(1) {
        margin-top: 40px;
      }

      .content {
        padding-left: 0px;
        margin-bottom: 14px;
        position: relative;

        label {
          font-size: 14px;
          //width: px;
          text-align:right;
          display: inline-block;
        }

        input {
         // width: px;
          height: 28px;
          padding-left: 8px;
          box-sizing: border-box;
          margin-left: 50px;
          outline: none;
          border: 1px solid #999;
        }

        img {
          vertical-align: sub;
        }

        .error-msg {
          position: absolute;
          top: 100%;
          left: 49px;
          color: red;
        }
      }

      .controls {
        /* text-align: center; */
        position: relative;

        input {
          vertical-align: middle;
        }

        /* .error-msg {
          position: absolute;
          top: 100%;
          left: 495px;
          color: red;
        } */
      }

      .btn {
   /*      text-align: center;
        line-height: 36px;
        margin: 17px 0 0 55px; */
        

        button {
          outline: none;
          width: 140px;
          height: 50px;
          background-image: linear-gradient(to right,#fbc2eb,#a6c1ee);
          color: #fff !important;
          display: inline-block;
          font-size: 16px;
          border-width:0px ;
          background: transparent;
        }
      }
    }

    .copyright {
      width: 1200px;
      margin: 0 auto;
      text-align: center;
      line-height: 24px;

      ul {
        li {
          display: inline-block;
          border-right: 1px solid #e4e4e4;
          padding: 0 20px;
          margin: 15px 0;
        }
      }
    }
  }
</style>