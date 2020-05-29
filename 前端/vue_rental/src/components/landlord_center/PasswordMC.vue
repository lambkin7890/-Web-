<template>
    <div class="main">
        <el-form :model="ruleForm" status-icon label-position="left" ref="ruleForm" size="medium" label-width="80px" class="demo-ruleForm">
          <el-form-item label="原密码:" prop="oldPass">
            <el-input type="password" v-model="ruleForm.oldPass" style="width:350px"></el-input>
          </el-form-item>
          <el-form-item label="新密码:" prop="newPass">
            <el-input type="password" v-model="ruleForm.newPass" autocomplete="off" style="width:350px"></el-input>
          </el-form-item>
          <el-form-item label="确认密码:" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" style="width:350px"></el-input>
          </el-form-item>
          
          <el-form-item>
            <el-button type="primary" @click="submitForm(ruleForm)">保存</el-button>
          </el-form-item>
        </el-form>
    </div>
</template>



<script>
    export default {
      data() {
        return {
          ruleForm: {
            oldPass:'',
            newPass: '',
            checkPass: ''
          },
          userInfo:{
              id:'',
              username:'',
              password:''        
          },
        };
      },
      mounted(){
          this.getUserInfo();
      },
      methods: {
        getUserInfo(){
            var userInfo=JSON.parse(sessionStorage.getItem("userInfo"));
            this.userInfo=userInfo;
        },
        submitForm(formName){
          var that=this;
          if(formName.oldPass==''||formName.oldPass==null){
              this.$message({
                message: '请填写原密码！',
                type: 'error'
              });
          }else if(formName.newPass==''||formName.newPass==null){
              this.$message({
                message: '请填写新密码！',
                type: 'error'
              });
          }else if(formName.newPass!=formName.checkPass){
              this.$message({
                message: '两次输入密码不一致！',
                type: 'error'
              });
          }else{
              this.$http({
                  url: "user/edit_password",
                  method: "post",
                  changeOrigin: true, //发送跨域请求
                  params: {
                      username:that.userInfo.username,
                      oldpassword:that.ruleForm.oldPass,
                      newpassword:that.ruleForm.newPass
                  }
              }).then((response) => {
                  var res=response.data;
                  if(res.type=='error'){
                    this.$message({
                      message: res.msg,
                      type: 'error'
                    });
                  }else{
                    this.$message({
                      message: res.msg,
                      type: 'success'
                    });
                    sessionStorage.clear();
                    this.$router.push({
                        name: 'backLogin',
                    })
                  }
              })
          }
        },
      }
    }
  </script>

<style>
.el-form-item--medium .el-form-item__label{
  font-weight:500;
}
</style>
<style scoped>
.main{
  padding:47px;
}
.main .el-form{
    text-align: left;
}
.el-form.demo-ruleForm{
  padding:50px;
  padding-left:150px;
  padding-bottom:10px;
  margin-right:100px;
  border:1px solid #e6e6e6;
}

.el-button.el-button--primary{
  margin-top:20px;
  margin-left:70px
}
.el-button.el-button--default{
  margin-top:20px;
  margin-left:50px
}
</style>