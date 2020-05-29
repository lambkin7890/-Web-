<template>
    <div class="main">
        <div class="MtList">
            <div class="avatar-box">
                <el-upload
                    class="avatar-uploader"
                    action="#"
                    name="photo"
                    :http-request="uploadUserPhoto"
                    :auto-upload="true"
                    :show-file-list="false"
                    :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="'http://localhost:8087'+imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </div>
            <el-form ref="form" :model="form" label-position="left" size="medium" label-width="90px" >
                <el-form-item label="用 户 名:">
                    <span class="nameSpan">{{form.username}}</span>
                </el-form-item>
                <el-form-item label="昵 称:" >
                    <el-input v-model="form.nickName" style="width:300px;" placeholder="请填写昵称"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名:">
                    <el-input v-model="form.realName" style="width:200px;" placeholder="请填写真实姓名" maxlength="4"></el-input>
                </el-form-item>
                <el-form-item label="性 别:">
                    <el-select v-model="form.sex" style="width:100px;" placeholder="-请选择-">
                        <el-option label="男" :value="1"></el-option>
                        <el-option label="女" :value="2"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="邮 箱:">
                    <el-input v-model="form.email"  style="width:220px;" placeholder="请填写邮箱"></el-input>
                </el-form-item>
                <el-form-item label="手机号码:">
                    <el-input v-model="form.mobile"  style="width:220px;" placeholder="请填写手机号码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="updateUserInfo">保存</el-button>
                </el-form-item>
            </el-form>
        </div>
        
    </div>
</template>



<script>
    export default {
        data() {
            return {
                form: {
                    id:'',
                    username:'',
                    photo:'',
                    nickName:'',
                    realName:'',
                    idCard:'',
                    sex:null,
                    email:'',
                    mobile:'',
                },
                imageUrl: ''
            }
        },
        computed:{
            userImg(){
                return require(`../../assets/images/user-photo/landlord/person.png`);
			}
        },
        mounted(){
            this.getUserInfo();
        },
        methods: {
            getUserInfo(){
                var userInfo=JSON.parse(sessionStorage.getItem("userInfo"));
                this.form=userInfo;
                this.imageUrl=this.form.photo;
            },
            updateUserInfo(){
                var that=this;
                this.form.photo=this.imageUrl;
                if(this.form.nickName==''||this.form.nickName==null){
                        alert('昵称不能为空！')
                }else{
                    this.$http({
                        url: "user/update",
                        method: "post",
                        changeOrigin: true, //发送跨域请求
                        params: that.form
                    }).then((response) => {
                        var res=response.data;
                        if(res.type=='error'){
                            this.$message({
                                message: response.data.msg,
                                type: 'error'
                            });
                        }else if(res.type == "success") {
                            this.$message({
                                message: response.data.msg,
                                type: 'success'
                            });
                            this.$http({
                                url: "user/query",
                                method: "get",
                                changeOrigin: true, //发送跨域请求
                                params: {
                                    username: that.form.username
                                }
                            }).then((response) =>{
                                var res=response.data;
                                if(res!=null){
                                    sessionStorage.setItem("userInfo",JSON.stringify(res));
                                }
                            });
                        }else{
                            return;
                        }
                    })
                }
                
            },
            beforeAvatarUpload(file) {
                const isPhoto = (file.type === 'image/jpeg')||(file.type === 'image/png')||(file.type === 'image/gif');
                const isLt10M = file.size / 1024 / 1024 < 10;

                if (!isPhoto) {
                    this.$message.error('只能上传图片格式的文件!');
                }
                if (!isLt10M) {
                    this.$message.error('上传头像图片大小不能超过 10MB!');
                }
                return isPhoto && isLt10M;
            },
            uploadUserPhoto(fileObj){
                let param = new FormData();
                param.set("photo", fileObj.file);
                let config = {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    },
                    changeOrigin: true
                }
                this.$http.post('/upload_photo', param, config).then((response)=>{
                    if (response.data.type == 'success') {
                        this.$message({
                            message: response.data.msg,
                            type: 'success'
                        });
                        this.imageUrl=response.data.filepath;
                    } else {
                        this.$message({
                            message: response.data.msg,
                            type: 'error'
                        });
                    }
                });
            }
        }
    }
</script>
<style>
.MtList .el-form-item--medium .el-form-item__label{
  font-weight:500;
}
</style>
<style scoped>
.main{
    padding:47px;
}
.MtList{
    display:flex;
    padding-left:30px;
    padding-top:60px;
    margin-right:30px;
    border: 1px solid #e6e6e6;
} 
.MtList .el-form{
    text-align: left;
}
.el-form{
    padding-bottom:30px;
    margin-left: 60px;
}
.avatar-box{
    width:180px;
    height:180px;
}
#upheadimgdo{
    font-size:14px;
    text-decoration: none;
    margin-top:10px;
}
#upheadimgdo:hover{
    color:#606266;
}
.el-button{
    margin-top:10px;
    margin-left:50px;
    width:90px;
}
.nameSpan{
    margin-left: 13px;
    color: #606266;
}
</style>