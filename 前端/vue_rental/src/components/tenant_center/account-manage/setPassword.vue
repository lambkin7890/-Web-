<template>
    <div style="margin:25px">
        <!-- 密码设置 START -->
        <div class="MtList">
            <input type="hidden" name="issetpass" id="issetpass" value="false">
            <input type="hidden" name="isbandmobile" id="isbandmobile" value="true">
            <ul class="userInfo">
                <li class="clearfix"><span class="infoTitle">登录账号：</span>{{userInfo.username}}</li>
                <li class="clearfix">
                    <span class="infoTitle">原密码：</span> 
                    <input type="password" v-model="oldPassword" name="oldpass" id="oldpass" class="personLargeInput" onkeyup="this.value=this.value.replace(/\s+/g,'')">
                </li>
                <li class="clearfix">
                    <span class="infoTitle">新密码：</span>
                    <input type="password" v-model="newPassword" name="newpass" id="newpass" class="personLargeInput"
                        onkeyup="this.value=this.value.replace(/\s+/g,'')">
                </li>
                <li class="clearfix">
                    <span class="infoTitle">确认密码：</span>
                    <input type="password" value="" name="repass" id="repass" class="personLargeInput"
                        onkeyup="this.value=this.value.replace(/\s+/g,'')">
                    <span class="prompt" id="repassspan" name="repassspan">重复输入一次密码</span>
                </li>
            </ul>
            <div class="submit-wrap">
                <a class="save-btn" id="savepassbtn" name="savepassbtn" href="javascript:;" @click="saveBtn">保存</a>
            </div>
        </div>
        <!-- 密码设置 END-->
    </div>
</template>

<script>
    export default {
        name: "SetPassword",
        data() {
            return {
                userInfo: {
                    id: '',
                    username: '',
                    password: ''
                },
                newPassword: '',
                oldPassword:''
            }
        },
        mounted() {
            this.getUserInfo();
        },
        methods: {
            getUserInfo() {
                var userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
                this.userInfo = userInfo;
            },
            saveBtn() {
                var that = this;
                var oldpass = document.getElementById('oldpass');
                var newpass = document.getElementById('newpass');
                var repass = document.getElementById('repass');
                if (oldpass.value == '' || oldpass.value == null) {
                    alert('请填写原密码！')
                }else if (newpass.value == '' || newpass.value == null) {
                    alert('请填写新密码！')
                } else if (repass.value != newpass.value) {
                    alert('两次输入密码不一致！');
                } else {
                    this.$http({
                        url: "user/edit_password",
                        method: "post",
                        changeOrigin: true, //发送跨域请求
                        params: {
                            username: this.userInfo.username,
                            oldpassword: this.oldPassword,
                            newpassword: this.newPassword
                        }
                    }).then((response) => {
                        var res = response.data;
                        alert(res.msg);
                        if (res.type == 'success') {
                            sessionStorage.clear();
                            this.$router.push({
                                name: 'login',
                            })
                        }
                    })
                }
            },

        },
        watch: {

        }
    }       
</script>

<style scoped>
    .avatar-box {
        overflow: visible;
    }

    .avatar-box {
        width: 100px;
        overflow: hidden;
        float: left;
    }

    .avatar {
        width: 100px;
        height: 100px;
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

    .alertAvatar {
        color: #ff8a00;
        font-size: 12px;
    }

    .tabCon {
        overflow: visible;
    }

    .tabCon {
        overflow: hidden;
        float: right;
    }

    .userInfo {
        width: 590px;
        padding-bottom: 20px;
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
        padding-bottom: 20px;
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
        background: #22bb62;
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
</style>