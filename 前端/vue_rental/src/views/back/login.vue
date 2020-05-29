<template>
    <div class="body">
      <div class="lowin-brand">
        <img src="../../assets/images/logo/kodinger.jpg" alt="logo">
      </div>
      <div class="container">
        <h3>帐户登录</h3>
        <el-form ref="form" label-width="90px">
          <el-form-item prop="username" label="用户姓名：">
            <el-input type="text" v-model.trim="username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item prop="password" label="用户密码：">
            <el-input type="password" v-model.trim="password" placeholder="请输入密码" show-password  @keyup.enter.native="userSubmit"></el-input>
          </el-form-item>
          <el-button type="primary" @click="userSubmit">登录</el-button>
        </el-form>
      </div>
    </div>   
  </template>
  <style scoped>
  .container{
    width: 450px;
    background-color:#fff;
    border-radius: 8px;
    -webkit-box-shadow: 2px 2px 20px#dcdcdc;
            box-shadow: 2px 2px 20px#dcdcdc;
    text-align: center;
    padding-top: 50px;
    padding-left: 20px;
    padding-right: 30px;
  }
  .container h3{
    text-align:center;
    margin-bottom:30px;
    margin-left: 10px;
  }
  .lowin-brand {
    overflow: hidden;
    width: 100px;
    height: 100px;
    margin: 0 auto -50px auto;
    border-radius: 50%;
    -webkit-box-shadow: 0 4px 40px rgba(0, 0, 0, .07);
    box-shadow: 0 4px 40px rgba(0, 0, 0, .07);
    padding: 10px;
    background-color: #fff;
    position: relative;
    margin-top:65px;
  }
  .el-button{
    color:#fff;
    width: 30%;
    margin-bottom: 35px;
    height: 45px;
    font-size: 16px;
    margin-top: 5px;
  }
  </style>
  <script>
    export default {
      data() {
        return {
            username: "",
            password: ""
        };
      },
      beforeCreate () {     //设置body背景色
        document.querySelector('body').setAttribute('style', 'background-color:#f5f5f5');
      },
      methods: { 
        userSubmit() {
          var _that = this;
          this.$http({
            method: "post",
            changeOrigin: true, // 这个参数代表发送跨域请求
            url: "user/login",
            //注意：post方式提交 参数这里可以写data get方式提交用params
            params: {
              username: this.username,
              password: this.password
            }
          }).then((response) => {
            var res = response.data;
            if(res.username != _that.username) {
                this.$message({
                    message: '该用户名不存在！',
                    type: 'error'
                });
            }else if(res.roleId=='1'){
                this.$message({
                    message: '该用户属于普通用户，无权限登录！',
                    type: 'error'
                });
            }else if(res.password!=_that.password){
                this.$message({
                    message: '密码错误！',
                    type: 'error'
                });
            }else if(res.roleId=='2'){  //表示是房东
                sessionStorage.setItem("userInfo",JSON.stringify(res));
                this.$router.push({
                    name:'backLandlordHome'
                })
            }else{   //表示是管理员
                sessionStorage.setItem("userInfo",JSON.stringify(res));
                this.$router.push({
                    name:'backAdminHome'
                })
            }
          })
        }
      }
    };
  </script>