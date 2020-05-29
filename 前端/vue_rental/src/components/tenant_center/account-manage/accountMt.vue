<template>
    <div style="margin:25px">
        <!-- 个人信息 -->
        <input type="hidden" name="issetpass" id="issetpass" value="false">
        <input type="hidden" name="isbandmobile" id="isbandmobile" value="true">
        <input type="hidden" name="usertype" id="usertype" value="tenant">
        <input type="hidden" name="bjlandlord" id="bjlandlord" value="0">
        <div class="MtList" style="display:block;">
            <div class="avatar-box">
                <el-upload class="avatar-uploader" action="#" name="photo" :http-request="uploadUserPhoto"
                    :auto-upload="true" :show-file-list="false" :before-upload="beforeAvatarUpload">
                    <img v-if="imageUrl" :src="'http://localhost:8087'+imageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </div>
            <div class="tabCon">
                <ul class="userInfo">
                    <li class="clearfix"><span class="infoTitle">用&nbsp;户&nbsp;名：</span>{{userInfo.username}}</li>
                    <li class="clearfix">
                        <span class="infoTitle">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</span>
                        <input class="personLargeInput" type="text" placeholder="4-20个字符（汉字、字母、数字、下划线）" id="nickname"
                            v-model="userInfo.nickName" style="width:276px;">

                    </li>
                    <li class="clearfix">
                        <span class="infoTitle">真实姓名：</span>
                        <input class="personLargeInput" type="text" id="realName" placeholder="请填写真实姓名"
                            v-model="userInfo.realName" style="width:276px;"  maxlength="4">
                    </li>
                    <li class="clearfix">
                        <span class="infoTitle">身份证号：</span>
                        <input class="personLargeInput" type="text" id="idCard" placeholder="请填写身份证号"
                            v-model="userInfo.idCard" style="width:276px;" maxlength="18" oninput="value=value.replace(/[^\w\.\/]/ig,'')">
                    </li>
                </ul>

                <ul class="userInfo">
                    <li class="clearfix">
                        <span class="infoTitle">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span>
                        <select class="ccitySelect" id="sex" v-model="userInfo.sex">
                            <option value="0">-请选择-</option>
                            <option value="1">男</option>
                            <option value="2">女</option>
                        </select>
                    </li>
                    <li class="clearfix">
                        <span class="infoTitle">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</span>
                        <input class="personLargeInput" type="text" id="email" v-model="userInfo.email">
                    </li>
                    <li class="clearfix" id="mobile_div">
                        <span class="infoTitle">手机号码：</span>
                        <input class="personLargeInput" type="text" id="mobile" v-model="userInfo.mobile"
                            style="color:#000;" oninput="value=value.replace(/[^\d]/g,'')" maxlength="11">
                    </li>
                </ul>
                <div class="submit-wrap">
                    <a id="saveuser" class="save-btn" @click="updateUserInfo">保存</a>
                </div>
            </div>
        </div>
        <!-- 个人信息 END -->



    </div>
</template>


<style scoped>
    .avatar-box {
        overflow: visible;
    }

    .avatar-box {
        width: 160px;
        overflow: hidden;
        float: left;
    }

    #headimage_div img {
        width: 100px;
        height: 100px;
        display: inline-block;
    }

    img {
        border: 0;
    }

    img {
        vertical-align: top;
    }

    .avatar-box p {
        text-align: center;
        margin-top: 5px;
    }

    .alertAvatar,
    .alertAvatar:focus {
        color: #ff545a;
        font-size: 12px;
        outline: none;
    }

    .tabCon {
        overflow: visible;
    }

    .tabCon {
        overflow: hidden;
        float: left;
        width: 360px;
        margin-left: 50px;
    }

    .userInfo li {
        margin-bottom: 15px;
        position: relative;
        line-height: 25px;
    }

    .infoTitle {
        overflow: visible;
        float: left;
    }

    .infoTitle {
        display: inline-block;
        width: 80px;
        height: 25px;
        line-height: 25px;
        overflow: hidden;
    }

    .personLargeInput {
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
    }

    .personLargeInput {
        width: 178px;
        height: 23px;
        line-height: 23px;
        padding: 0 3px;
        border: #999 solid 1px;
        float: left;
    }

    .zm_complete {
        cursor: pointer;
    }

    .ml10 {
        margin-left: 10px;
    }

    .blue {
        color: #458fce;
    }

    .namenotice {
        position: absolute;
        top: 28px;
        left: 80px;
    }

    .prompt,
    .namenotice {
        color: #888;
    }

    .prompt,
    .namenotice,
    .prompterror {
        line-height: 24px;
        font-size: 12px;
    }

    .zhima_des {
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        width: 274px;
        padding: 20px;
        position: absolute;
        left: 300px;
        top: 25px;
        z-index: 9;
        line-height: 20px;
        border: 1px solid #e0e0e0;
        -webkit-box-shadow: 0 0 3px 2px #EAEAEA;
        box-shadow: 0 0 3px 2px #EAEAEA;
        display: none;
        background-color: #fff;
    }

    .userInfo li p {
        margin-bottom: 10px;
    }

    .zm_accredit {
        font-size: 14px;
        color: #3eb382;
        padding: 10px;
        background: rgba(62, 179, 130, 0.1);
    }

    .zm_bottom {
        margin-bottom: 20px;
        padding-top: 10px;
    }

    .c322c27 {
        color: #322c27 !important;
    }

    .shouquan,
    .xuanfang {
        width: 140px;
        height: 36px;
        line-height: 36px;
        margin: 10px auto;
        display: block;
    }

    .green_button {
        background-color: #22bb62;
        color: #fff;
        text-align: center;
        cursor: pointer;
    }

    .t-center {
        text-align: center;
    }

    .userInfo li {
        margin-bottom: 15px;
        position: relative;
        line-height: 25px;
    }

    .userInfo {
        width: 590px;
    }

    .ccitySelect {
        width: 120px;
        height: 23px;
        border: #999 solid 1px;
        padding-left: 3px;
        margin-right: 5px;
    }

    input,
    button,
    textarea,
    select,
    optgroup,
    option,
    pre {
        border: 0 none;
        font-size: inherit;
        font-style: inherit;
        font-weight: inherit;
        outline: none;
    }

    .checking {
        font-size: 12px;
        color: #ff8a00;
        line-height: 25px;
        margin-left: 15px;
    }

    i.code-i {
        display: inline-block;
        border: 1px solid #999;
        height: 17px;
        line-height: 19px;
        padding: 2px 4px;
        border-left: none;
        width: 32px;
        cursor: default;
    }

    address,
    em,
    i {
        font-style: normal;
    }

    .clearfix .addAdress-btn {
        background-image: url(/resourcesWeb/images/bookorder/indent_icon.png);
        display: block;
        width: 78px;
        height: 28px;
        text-align: center;
        line-height: 28px;
        background-repeat: repeat-x;
        background-position: 0px -323px;
        color: #444;
        border: #bcbcbc solid 1px;
        border-radius: 2px;
        cursor: pointer;
        margin-top: 10px;
    }

    .submit-wrap {
        overflow: hidden;
        margin-bottom: 10px;
    }

    .save-btn {
        width: 98px;
        margin: 0 auto;
        display: block;
    }

    .save-btn,
    .AddNewAt-btn {
        display: block;
        height: 38px;
        text-align: center;
        line-height: 38px;
        font-size: 16px;
        color: #fff;
    }

    .save-btn,
    .AddNewAt-btn,
    .securitycode {
        background: #ff545a;
    }

    .securitycode {
        width: 78px;
        height: 25px;
        border: #bcbcbc solid 1px;
        cursor: pointer;
        font-size: 12px;
        color: #fff;
        float: left;
        margin-left: 10px;
        border-radius: 3px;
    }

    .prompt,
    .namenotice {
        color: #888;
    }

    .prompt,
    .namenotice,
    .prompterror {
        line-height: 24px;
        font-size: 12px;
    }

    .prompt,
    .prompterror {
        float: left;
        margin-left: 10px;
    }

    .selectoption {
        padding-bottom: 20px;
    }

    .selectoption li {
        float: left;
        margin-right: 40px;
        color: #4c4c4c;
    }

    .selectradio {
        float: left;
        margin: 5px 3px 0 0;
        *margin: 3px 2px 0 0;
    }

    .gathering,
    .coupontable {
        color: #010101;
    }

    .gathering {
        width: 748px;
    }

    div,
    table,
    form,
    input,
    button,
    select,
    textarea,
    fieldset,
    legend {
        outline: none;
        border: none;
    }

    table {
        border-collapse: collapse;
        border-spacing: 0;
    }

    tbody {
        display: table-row-group;
        vertical-align: middle;
        border-color: inherit;
    }

    .gathering th,
    .coupontable th {
        background: #f2f2f2;
        text-align: center;
        height: 30px;
    }

    .bordercolor {
        background: #fff;
        padding-left: 3px;
    }

    .cropper-box {
        width: 670px;
        position: relative;
        background: #fff;
        color: #959ea7;
    }

    .cropper-box h4 {
        height: 75px;
        text-align: center;
        font-size: 16px;
        color: #2b2f33;
        line-height: 75px;
    }

    .clearfloat:after {
        display: block;
        clear: both;
        content: "";
        visibility: hidden;
        height: 0;
    }

    .cropper-box .imgcropper_right {
        float: right;
        width: 240px;
    }

    .cropper-box .cropper_inner H5 {
        font-size: 14px;
        color: #2b2f33;
        height: 26px;
        line-height: 26px;
        padding-bottom: 10px;
    }

    .cropper-preview {
        width: 240px;
        height: 240px;
        overflow: hidden;
        -webkit-border-radius: 50%;
        -moz-border-radius: 50%;
        border-radius: 50%;
    }

    .cropper-box .imgcropper_right img {
        width: 240px;
        height: 240px;
    }

    .cropper-box .imgcropper_right p {
        font-size: 12px;
        color: #959ea7;
        text-align: center;
        padding: 15px 0 20px;
        white-space: nowrap;
    }

    .t-center {
        text-align: center;
    }

    #saveuser:hover,
    #saveuser:link {
        cursor: pointer;
        text-decoration: none;
    }
</style>
<script>
    export default {
        name: "AccountMt",
        data() {
            return {
                userInfo: {
                    id: '',
                    username: '',
                    photo: '',
                    nickName: '',
                    realName: '',
                    idCard: '',
                    sex: 0,
                    email: '',
                    mobile: '',

                },
                imageUrl: '',
            }
        },
        computed: {
            userImg() {
                var imgName = this.userInfo.photo;
                if (imgName == '' || imgName == null) {
                    return require(`../../../assets/images/user-photo/landlord/kodinger.jpg`);
                } else {
                    var userImg = 'http://localhost:8087' + imgName;
                    return userImg;
                }
            }
        },
        mounted() {
            this.getUserInfo();
        },
        methods: {
            getUserInfo() {
                var userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
                this.userInfo = userInfo;
                this.imageUrl = this.userInfo.photo;
            },
            updateUserInfo() {
                var that = this;
                this.userInfo.photo = this.imageUrl;
                this.$http({
                    url: "user/update",
                    method: "post",
                    changeOrigin: true, //发送跨域请求
                    params: that.userInfo
                }).then((response) => {
                    var res = response.data;
                    alert(res.msg);
                    if (res.type == "success") {
                        var _this = this;
                        this.$http({
                            url: "user/query",
                            method: "get",
                            changeOrigin: true, //发送跨域请求
                            params: {
                                username: that.userInfo.username
                            }
                        }).then((response) => {
                            var res = response.data;
                            if (res != null) {
                                sessionStorage.setItem("userInfo", JSON.stringify(res));
                            }
                        });
                    }
                })

            },
            beforeAvatarUpload(file) {
                const isPhoto = (file.type === 'image/jpeg') || (file.type === 'image/png') || (file.type === 'image/gif');
                const isLt10M = file.size / 1024 / 1024 < 10;

                if (!isPhoto) {
                    this.$message.error('只能上传图片格式的文件!');
                }
                if (!isLt10M) {
                    this.$message.error('上传头像图片大小不能超过 10MB!');
                }
                return isPhoto && isLt10M;
            },
            uploadUserPhoto(fileObj) {
                let param = new FormData();
                param.set("photo", fileObj.file);
                let config = {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    },
                    changeOrigin: true
                }
                this.$http.post('/upload_photo', param, config).then((response) => {
                    if (response.data.type == 'success') {
                        this.$message({
                            message: response.data.msg,
                            type: 'success'
                        });
                        this.imageUrl = response.data.filepath;
                        console.log(this.imageUrl)
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