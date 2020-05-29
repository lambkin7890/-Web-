<template>
  <div class="body">
    <div class="backIndex">
      <router-link to="/"><i><<</i>回到首页</router-link>
    </div>
    <div class="lowin-brand">
      <img src="../assets/images/logo/kodinger.jpg" alt="logo">
    </div>
    <div class="container">
      <h3>注册帐户</h3>
      <el-form ref="form" label-width="90px">
        <el-form-item label="用户姓名：">
          <el-input type="text" name="username" v-model="username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="用户密码：">
          <el-input type="password" name="password" v-model="password" placeholder="请输入密码" show-password  @keyup.enter.native="register"></el-input>
        </el-form-item>
        <el-button type="danger" @click="register">注册</el-button>
      </el-form>
    </div>
  </div>  
</template>
<style scoped>
.backIndex{
  padding-top: 30px;
}
.backIndex .router-link-active{
  font-size: 16px;
  color:#ff5f65;
  margin-left: 20px;
}
.backIndex a.router-link-active:hover{
  text-decoration: none;
}
.backIndex a.router-link-active:focus{
  outline: none;
}
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
  background-color:#ff545a;
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
    name: "Register",
    data() {
      return {
        username: "",
        password: ""
      }
    },
    mounted(){

    },
    methods: {
      register() {
        var that=this;
        if(this.username==''||this.username==null){
          alert('请填写用户名！');
        }else if(this.password==''||this.password==null){
          alert('请填写密码！');
        }else{
          this.$http({
            url: "user/register",
            method: "post",
            changeOrigin: true, // 这个参数代表发送跨域请求
            //注意：post方式提交 参数这里可以写data get方式提交用params
            params: {
              username: that.username,
              password: that.password,
              roleId:1
            }
          }).then((response) => {
            if(response.data.type=='success'){
              this.$message({
                  message: response.data.msg,
                  type: 'success'
              });
              this.$router.push({
                name: 'login'
              })
            }else{
              this.$message({
                  message: response.data.msg,
                  type: 'error'
              });
            }
          })
        }
        
      }
    }
  };
</script>