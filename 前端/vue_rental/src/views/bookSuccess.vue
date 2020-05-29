<template>
    <div class="body">
        <!-- 头部导航 -->
        <div class="header relave">
            <a href="/">
                <div class="asote head_left">
                    list<span>race</span>
                </div>
            </a>
            <div class="head_cnt relave">
                <ul class="nav_R asote nav_right">
                    <li style="margin-left:-211px;">
                        <b class="mayi_tel_top">
                            <span>客服电话:</span>
                            400-022-5757
                        </b>
                    </li>
                    <li id="user-info" style="display:none" @mouseenter="displayBlock" @mouseleave="displayNone">
                        <div class="showinfo">
                            <a href="javascript:;">
                                <img :src="userImg" class="user_img">
                                <span class="name" id="head_nickname">{{userInfo.username}}</span>
                                <div id="loginInfoPanel" class="head_pop" style="display: none;">
                                    <div class="pop_column">
                                        <p><a href="javascript:;" id="myorder" @click="toHomeAccount(0)">我的订单</a></p>
                                        <p><a href="javascript:;" id="myorder" class="slideactive"
                                                @click="toHomeAccount(1)">我的账户</a></p>
                                        <p><a href="javascript:;" id="mycollect" @click="toHomeAccount(2)">我的收藏</a></p>
                                        <p class="textCt"><a href="javascript:;" class="stclick"
                                                @click="logout">退出登录</a></p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </li>
                    <li>
                        <a class="recruit" href="javascript:;" @click="toAbout">关于</a>
                    </li>
                </ul>

            </div>

        </div>
        <!-- 头部导航结束 -->

        <div class="online_btn"></div>

        <!-- 步骤条 -->
        <div class="one_workflow">
            <div class="workflow relave">
                <div class="progress progress_3"></div>
                <b class="workflow1">提交订单</b>
                <b class="workflow2">支付订金</b>
                <b class="workflow3">房东确认</b>
                <b class="workflow4">预订成功</b>
                <span class="workflow1_1"><a href="javascript:;" class="a_after">1</a></span>
                <span class="workflow1_2"><a href="javascript:;" class="a_after">2</a></span>
                <span class="workflow1_3"><a href="javascript:;" class="a_after">3</a></span>
                <span class="workflow1_4"><a href="javascript:;" class="duigo a_after"></a></span>
            </div>
        </div>

        <!-- 主体内容 -->
        <div class="primary-content">
            <p>您的订单已支付成功，请在入住当天携带有效证件到店办理入住，谢谢！</p>
            <div class="look_order">
                <a @click="toOrderDetail">查看订单</a>
            </div>
        </div>

        <Footer></Footer>

    </div>
</template>

<script>
    import Footer from '../components/Footer.vue';
    import { getUrlKey } from '@/utils/getUrlKey.js';
    export default {
        data() {
            return {
                userInfo: {
                    id: '',
                    photo: '',
                    username: ''
                },
            }
        },
        components: {
            Footer
        },
        computed: {
            userImg() {
                var imgName = this.userInfo.photo;
                if (imgName == '' || imgName == null) {
                    return require(`../assets/images/user-photo/landlord/kodinger.jpg`);
                } else {
                    var userImg = 'http://localhost:8087'+imgName;
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
                if (userInfo != undefined || userInfo != null) {
                    this.userInfo = userInfo;
                    var oLi = document.getElementById('user-info');
                    oLi.style.display = "block";
                }
            },
            toAbout() {
                this.$router.push({
                    name: 'about',
                })
            },
            toOrderDetail() {
                this.$router.push({
                    path: '/order_detail',
                    query: {
                        id: getUrlKey('oid')
                    }
                })
            },
            displayBlock() {
                var oPa = document.getElementById('loginInfoPanel');
                oPa.style.display = "block";
            },
            displayNone() {
                var oPa = document.getElementById('loginInfoPanel');
                oPa.style.display = "none";
            },
            logout() {
                sessionStorage.clear();
                this.$router.push({
                    name: 'login',
                })
            },
            toHomeAccount(val) {
                this.$router.push({
                    name: 'homeAccount',
                    params: {
                        activeIndex: val
                    }
                })
            },
        }
    }
</script>

<style scoped src="../assets/css/submitOrder.css"></style>
<style scoped>
    .primary-content {
        height: 260px;
        width: 100%;
        display: inline-grid
    }

    .primary-content p {
        color: #202020;
        font-size: 20px;
        float: left;
        margin-left: 100px;
        margin-top: 50px;
    }

    .look_order {
        padding-left: 100px;
        padding-bottom: 60px;
    }

    .look_order a {
        display: block;
        width: 136px;
        font-size: 16px;
        color: #fff;
        text-align: center;
        background: #ff545a;
        line-height: 40px;
        border-radius: 3px;
    }

    .progress_3 {
        width: 100%;
    }
</style>