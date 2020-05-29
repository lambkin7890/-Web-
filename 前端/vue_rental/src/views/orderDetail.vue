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

        <div class="primary-content">
            <div class="order-content">
                <h2>订单信息</h2>
                <div class="list-content">
                    <ul class="ul-l">
                        <li>订单编号：{{orderInfo.sn}}</li>
                        <li>入住日期：{{orderInfo.checkinDay}}</li>
                        <li>退房日期：{{orderInfo.checkoutDay}}</li>
                        <li>预定天数：{{orderInfo.nightCount}}晚</li>
                        <li>订单总额：{{orderInfo.totalPrice}}元</li>
                        <li>订单状态：
                            <span v-if="orderInfo.status==0">待付款</span>
                            <span v-if="orderInfo.status==1">已支付</span>
                        </li>
                    </ul>
                    <ul class="ul-r">
                        <li>预订人：{{orderInfo.tenantName}}</li>
                        <li>联系人手机号：{{orderInfo.mobile}}</li>
                        <li>入住人数：{{orderInfo.checkinNum}}人</li>
                        <li>入住人姓名：{{orderInfo.checkinName}}</li>
                        <li>入住人身份证号：{{orderInfo.checkinIdCard}}</li>
                        <li>下单时间：{{orderInfo.createTime}}</li>
                    </ul>
                </div>
            </div>
            <div class="house-content">
                <h2>房源信息</h2>
                <div class="list-content">
                    <div class="house-img">
                        <img v-if="orderInfo.photo" :src="'http://localhost:8087'+orderInfo.photo" style="width: 350px;height: 230px;">
                    </div>
                    <div class="info-r">
                        <ul>
                            <li>房源编号：{{orderInfo.houseSn}}</li>
                            <li>房源名称：{{orderInfo.name}}</li>
                            <li>房源价格：{{orderInfo.price}}元/晚</li>
                            <li>房源地址：{{orderInfo.address}}</li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>

        <Footer></Footer>

    </div>
</template>

<script>
    import Footer from '../components/Footer.vue';
    export default {
        data() {
            return {
                orderInfo: {
                    id: '',
                    sn: '',
                    houseId: '',
                    houseHolderId: '',
                    checkinDay: '',
                    checkoutDay: '',
                    userId: '',
                    mobile: '',
                    checkinNum: '',
                    checkinName: '',
                    checkinIdCard: '',
                    nightCount: '',
                    totalPrice: '',
                    status: '',
                    createTime: '',

                    houseSn: '',
                    tenantName: '',//预订人用户名
                    name: '',//房源名称
                    photo: '',//房源图片
                    price: '',//房源价格
                    address: '',//房源地址
                },
                userInfo: {
                    id: '',
                    photo: '',
                    username: ''
                }
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
                    var userImg ='http://localhost:8087'+imgName;
                    return userImg;
                }
            }
        },
        mounted() {
            this.getUserInfo();
        },
        created: function () {
            this.loadData();
        },
        methods: {
            loadData() {
                const id = this.$route.query.id;
                var that = this;
                this.$http({
                    method: "get",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "book_order/detail",
                    params: {
                        id: id
                    }
                }).then((response) => {
                    that.orderInfo = response.data;
                    var itemId = that.orderInfo.houseId;
                    var itemSn = 0;
                    if (itemId < 10) {
                        itemSn = '000' + itemId;
                    } else if (itemId >= 10 && itemId < 100) {
                        itemSn = '00' + itemId;
                    } else if (itemId >= 100 && itemId < 1000) {
                        itemSn = '0' + itemId;
                    } else {
                        itemSn = itemId;
                    }
                    that.orderInfo.houseSn = itemSn
                })

            },
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
    .house-img {
        width: 40%
    }

    .info-r {
        width: 60%;
        padding: 0 0 30px 30px;
    }

    .primary-content {
        width: 900px;
        background: #fff;
        border: 1px solid #dce0e0;
        padding: 24px;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        margin: 0 0 50px 80px;
    }

    .order-content h2 {
        margin-top: 3px;
        width: 100%;
        font: 20px/52px "Microsoft yahei";
        color: #322c27 !important;
        border-bottom: 1px solid #dce0e0;
        position: relative;
        padding: 0px 15px;

    }

    .order-content h2:before {
        content: '';
        width: 5px;
        height: 20px;
        display: inline-block;
        background-color: #ff545a;
        margin-top: 9px;
        position: absolute;
        top: 8px;
        left: 2px;
    }

    .house-content h2 {
        margin-top: 3px;
        width: 100%;
        font: 20px/52px "Microsoft yahei";
        color: #322c27 !important;
        border-bottom: 1px solid #dce0e0;
        position: relative;
        padding: 0px 15px;

    }

    .house-content h2:before {
        content: '';
        width: 5px;
        height: 20px;
        display: inline-block;
        background-color: #ff545a;
        margin-top: 9px;
        position: absolute;
        top: 8px;
        left: 2px;
    }

    .list-content {
        width: 100%;
        padding: 20px;
        display: flex;
    }

    .list-content ul {
        color: #000;
        font-size: 14px;
    }

    .list-content ul li {
        margin: 20px 0;
    }

    .ul-l,
    .ul-r {
        width: 50%;
        padding-left: 10px;
    }
</style>