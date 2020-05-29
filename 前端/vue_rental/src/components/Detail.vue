<template>
    <div class="main">
        <!-- 头部导航栏 -->
        <div class="header relave">
            <a href="/">
                <div class="asote head_left">
                    list<span>race</span>
                </div>
            </a>
            <div class="head_cnt relave">
                <ul class="nav_R asote nav_right">
                    <li style="display: none;" id="token" @mouseenter="displayBlock" @mouseleave="displayNone">
                        <a href="javascript:;">
                            <img v-bind:src="userImg" alt="user_img">
                            <span>{{userInfo.username}}</span>
                            <div id="loginInfoPanel" class="head_pop" style="display: none;">
                                <div class="pop_column">
                                    <p><a href="javascript:;" target="_self" id="myorder"
                                            @click="toHomeAccount(0)">我的订单</a></p>
                                    <p><a href="javascript:;" target="_self" id="myorder" class="slideactive"
                                            @click="toHomeAccount(1)">我的账户</a></p>
                                    <p><a href="javascript:;" target="_self" id="mycollect"
                                            @click="toHomeAccount(2)">我的收藏</a></p>
                                    <p class="textCt"><a href="javascript:;" class="stclick" @click="logout">退出登录</a>
                                    </p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li id="loginBtn"><a href="javascript:;" @click="toLogin">登录</a></li>
                    <li id="registerBtn"><a href="javascript:;" @click="toRegister">注册</a></li>
                    <li>
                        <div class="showinfo">
                            <a href="javascript:;" @mouseenter="displayQQ" @mouseleave="hiddenQQ">客服热线</a>
                            <span id="displayQQ" class="QQ-area">400-022-5757</span>
                        </div>
                    </li>
                    <li>
                        <a class="recruit" href="javascript:;" @click="toAbout">关于</a>
                    </li>
                    <li>

                    </li>
                </ul>
            </div>
        </div>
        <!-- 头部导航栏结束 -->

        <!-- container -->
        <div class="container">
            <!-- 路径导航 -->
            <ol class="breadcrumb">
                <li>
                    <router-link to="/">首页</router-link>
                </li>
                <li class="active">房源编号：{{houseDetail.sn}}</li>
            </ol>
            <!-- 路径导航结束 -->

            <!-- 主体部分 -->
            <div class="main-l clearfloat">
                <div class="desWord">
                    <h2 style="display: flex;">
                        <a href="javascript:;">{{houseDetail.name}}</a>
                        <div class="biaoqian">
                            <span v-if="houseDetail.isRealPhoto==1" class="shipai_1" title="实拍"><img src="../assets/images/icons/camera.png" alt="">实拍</span>
                            <span v-if="houseDetail.isCooking==1" class="kezuofan_3" title="可做饭"><img src="../assets/images/icons/cook.png" alt="">可做饭</span>
                            <span v-if="houseDetail.isParkFree==1" class="laoyangfang_5" title="免费停车"><img src="../assets/images/icons/hangup.png" alt="">免费停车</span>
                            <span v-if="houseDetail.isProject==1" class="shipai_1" title="有投影"><img src="../assets/images/icons/audio.png" alt="">有投影</span>
                        </div>
                        <div class="collection" v-show="collectionActive===0" style="cursor:pointer;"
                            @click="collectHouse">
                            <i class="iconfont icon-Collection"></i>
                            <span>收藏</span>
                        </div>
                        <div class="collection" v-show="collectionActive===1" style="cursor:pointer;"
                            @click="deleteCollection">
                            <i class="iconfont icon-shoucang"></i>
                            <span>取消收藏</span>
                        </div>
                    </h2>
                    <p>地址：{{houseDetail.address}}</p>

                </div>
                <div class="fl img_playPar">
                    <!--房屋轮播图-->
                    <div class="desTop fl">
                        <!--图片轮播-->
                        <div style="position:relative;">
                            <div class="picmap-change">
                                <div id="TabChangeCon_01" class="change-list">
                                    <div class="picbox">
                                        <ul class="piclist">
                                            <li><img v-bind:src="houseImg" alt=""></li>
                                        </ul>
                                        <a id="big_play_prev" class="home-picprev" href="javascript:;"></a>
                                        <a id="big_play_next" class="home-picnext" href="javascript:;"></a>
                                    </div>

                                </div>
                                <div id="TabChangeCon_02" class="change-list">
                                    <div id="mapDiv"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--图片轮播结束-->

                    <div class="feature">
                        <ul class="clearfloat">
                            <li>
                                <div class="clearfloat feature_chansu">
                                    <img src="../assets/images/icons/room_ping.png" class="fl">
                                    <span class="fl">房源类型</span>
                                </div>
                                <p>{{houseDetail.bedRoomNum}}室{{houseDetail.toiletNum}}卫</p>
                            </li>
                            <li class="w258">
                                <div class="clearfloat feature_chansu">
                                    <img src="../assets/images/icons/ruzhu.png" class="fl" />
                                    <span class="fl">可住{{houseDetail.liveNum}}人</span>
                                </div>
                                <p>共{{houseDetail.bedNum}}张床</p>
                            </li>
                            <li id="feature_chansu">
                                <div class="clearfloat feature_chansu">
                                    <img src="../assets/images/icons/haoping.png" class="fl">
                                    <span class="fl">品质房源</span>
                                </div>
                                <p>口碑较好</p>
                            </li>
                        </ul>
                    </div>

                    <!-- 房源概况 -->
                    <div class="house-introduce">
                        <div class="mod-title bottomed">
                            <h3 class="title">房源概况</h3>
                        </div>
                        <div class="auto-general">
                            <b class="strongbox" style="font-weight: normal;">{{houseDetail.introduce}}</b>
                        </div>
                    </div>

                </div>
                <!--右侧预订信息-->
                <div class="main-r_1" style="position:relative;">
                    <div class="order">
                        <div class="price clearfloat relave">
                            <div class="clearfloat">
                                <div class="priceL" id="priceL">￥<span>{{houseDetail.price}}</span>/晚</div>
                                <div class="suding">
                                    <img src="../assets/images/icons/suding_icon.png" alt="速订房源" class="suding_icon">
                                    <div class="suding_tan" style="display: none;">
                                        <p class="c22bb62">速订房源</p>
                                        <p>无需房东确认，支付后即预定成功</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 右侧日期显示区域 -->
                        <div class="date_par clearfloat" style="height:60px">
                            <div class="date fl relave inputfliter">
                                <el-date-picker v-model="value1" type="daterange" range-separator="至"
                                    start-placeholder="入住日期" end-placeholder="退房日期" value-format="yyyy-MM-dd"
                                    :picker-options="pickerOptions">
                                </el-date-picker>
                            </div>
                        </div>
                        <div class="priceTotal clearfloat">
                            <div class="priceTotalL" id="priceTotalL">总计￥<span>{{totalPrice}}</span></div>
                        </div>
                        <div class="pay">
                            <a id="goBookBtn" href="javascript:;" @click="toSubmitOrder">立即预订</a>
                        </div>
                    </div>
                    <!--房东信息-->
                    <div class="landlord">
                        <div class="landlordDes">
                            <div class="landlordDesL">
                                <img :src="'http://localhost:8087'+landlordInfo.photo" alt="">
                            </div>
                            <div class="landlordDesR">
                                <font>{{landlordInfo.nickName}}</font>
                                <p class="renzheng" style="margin-bottom:15px;margin-top:10px">
                                    <span class="relave" style="margin-right:16px">
                                        <i class="absot renzheng1"><img src="../assets/images/icons/idCardyz.png"></i>
                                        身份认证
                                    </span>
                                    <span class="relave">
                                        <i class="absot renzheng2"><img src="../assets/images/icons/mobileyz.png"></i>
                                        手机认证
                                    </span>
                                </p>
                                <span style="margin-left:20px">联系电话：{{landlordInfo.mobile}}</span>
                            </div>
                        </div>
                    </div>
                </div>
                <!--右侧预订信息结束-->
            </div>
            <!-- 主体部分结束 -->
        </div>
        <!-- container.end -->

        <Footer></Footer>
    </div>

</template>

<style scoped src="../assets/css/details_new.css"></style>
<style scoped src="../assets/css/iconfont/iconfont.css"></style>
<style>
    .inputfliter .el-range-editor .el-range-input {
        margin-left: 10px;
    }

    .inputfliter .el-date-editor .el-range__close-icon {
        display: none;
    }
</style>
<style scoped>
    .main{
        background-color: #fff;
    }
    .house-introduce {
        width: 780px;
        float: left;
        padding-top: 14px;
        padding-bottom: 20px;
        overflow: hidden;
        border-top: 1px solid #ddd9d9;
    }

    .mod-title.bottomed {
        padding: 20px 0 10px 10px;
    }

    .mod-title.bottomed h3 {
        font-size: 22px;
    }

    .auto-general {
        padding: 10px 10px 0 10px;
    }

    .pay a:link,
    .pay a:focus {
        color: #fff;
    }

    .QQ-area {
        display: none;
        position: absolute;
        width: 170px;
        height: 43px;
        background-color: #fff;
        margin-left: -55px;
        border-radius: 4px;
        text-align: center;
        padding-top: 2px;
        font-size: 18px;
        -webkit-box-shadow: 0 0 12px 0 rgba(0, 0, 0, 0.04), 0 0 4px 0 rgba(0, 0, 0, 0.12);
        box-shadow: 0 0 12px 0 rgba(0, 0, 0, 0.04), 0 0 4px 0 rgba(0, 0, 0, 0.12);
        color: #4c4c4c;
    }

    .QQ-area::before {
        content: '';
        position: absolute;
        top: -7px;
        left: 75px;
        display: inline-block;
        width: 0;
        height: 0;
        border-width: 0 7px 7px;
        border-style: solid;
        border-color: transparent transparent #fff;
    }

    .collection {
        font-size: 16px;
        color: #605a5a;
        margin-top: 15px;
        margin-left: 210px;
    }

    .collection i.icon-Collection {
        margin-right: 3px;
    }

    .home-picprev {
        cursor: url(&quot;//staticnew.mayi.com/resourcesWeb/images/detail/mouseup.cur&quot;), auto;
        background: rgb(255, 255, 255);
        opacity: 0;
        top: 0%;
    }

    .home-picnext {
        cursor: url(&quot;//staticnew.mayi.com/resourcesWeb/images/detail/mousedown.cur&quot;), auto;
        background: rgb(255, 255, 255);
        opacity: 0;
        bottom: 0%;
    }

    #token img {
        width: 32px;
        height: 32px;
        border-radius: 50%;
        border: 1px solid #fff;
        margin-bottom: 5px;
        vertical-align: middle;
    }

    #token span {
        margin-left: 2px;
    }

    .el-date-editor--daterange.el-input__inner {
        width: 288px;
        border-radius: 0;
        border: 1px solid #C9CACB;
    }
</style>
<script>
    import Footer from "./Footer.vue";
    export default {
        name: "Detail",
        data() {
            return {
                houseDetail: {
                    id: "",
                    sn: '',
                    name: "",
                    photo: "",
                    price: "",
                    address: "",
                    liveNum: "",
                    introduce: "",
                    landlordId: ""
                },
                userInfo: {
                    id: '',
                    username: '',
                    photo: ''
                },
                landlordInfo: {
                    photo: 'user-photo/landlord/person.png',
                    nickName: '',
                    mobile: ''
                },
                collectionActive: 0,
                collection: {
                    id: ''
                },
                currentPage: 1,
                pageSize: 10,
                value1: [
                    new Date().toLocaleDateString().replace(/\//g, '-'),
                    new Date(new Date().getTime() + 24 * 60 * 60 * 1000).toLocaleDateString().replace(/\//g, '-')
                ],
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() < Date.now() - 24 * 60 * 60 * 1000;
                    },
                },
                nightCount: '',

            }
        },
        computed: {
            houseImg() {
                var imgName = this.houseDetail.photo;
                if (imgName != '' || imgName != undefined) {
                    var houseImg = 'http://localhost:8087' + imgName;
                    return houseImg;
                } else {
                    return require(`../assets/images/user-photo/landlord/kodinger.jpg`);
                }

            },
            userImg() {
                var imgName = this.userInfo.photo;
                if (imgName == '' || imgName == null) {
                    return require(`../assets/images/user-photo/landlord/kodinger.jpg`);
                } else {
                    var userImg = 'http://localhost:8087' + imgName;
                    return userImg;
                }
            },
            totalPrice() {
                if (this.value1 == null || this.value1 == '') {
                    this.nightCount = 0;
                    return 0;
                }
                var sArr = this.value1[1].split("-");
                var eArr = this.value1[0].split("-");
                var sRDate = new Date(sArr[0], sArr[1], sArr[2]);
                var eRDate = new Date(eArr[0], eArr[1], eArr[2]);
                this.nightCount = (sRDate - eRDate) / (24 * 60 * 60 * 1000);
                var totalPrice = this.houseDetail.price * this.nightCount;
                return totalPrice;
            }
        },
        components: {
            Footer
        },
        mounted() {
            this.getHouseDetail();
            setTimeout(() => {
                this.getUserInfo();
            }, 100);
        },
        created: function () {

        },
        methods: {
            toSubmitOrder() {
                var un = JSON.parse(sessionStorage.getItem("userInfo"));
                if (un != undefined) {
                    var storage = localStorage;
                    storage.setItem("houseInfo", JSON.stringify(this.houseDetail));
                    storage.setItem("nightCount", this.nightCount);
                    storage.setItem("totalPrice", this.houseDetail.price * this.nightCount);
                    storage.setItem("checkinDay", this.value1[0])
                    storage.setItem("checkoutDay", this.value1[1])
                    this.$router.push({
                        name: 'submitOrder'
                    })
                } else{
                    this.$router.push({
                        name: 'login'
                    })
                }
            },
            collectHouse() {
                var that = this;
                if (this.userInfo.id != '') {
                    this.$http({
                        method: "post",
                        changeOrigin: true, // 这个参数代表发送跨域请求
                        url: "house_collection/add",
                        params: {
                            userId: that.userInfo.id,
                            houseId: that.houseDetail.id
                        }
                    }).then((response) => {
                        that.collectionActive = 1;
                    })
                } else {
                    this.$router.push({
                        name: 'login'
                    })
                }

            },
            deleteCollection() {
                var that = this;
                if (that.collection.id != undefined) {
                    this.$http({
                        method: "post",
                        changeOrigin: true, // 这个参数代表发送跨域请求
                        url: "house_collection/delete",
                        params: {
                            id: that.collection.id
                        }
                    }).then((response) => {
                        that.collectionActive = 0;
                    })
                }

            },
            isCollected(userId, houseId) {
                var that = this;
                this.$http({
                    method: "post",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "house_collection/list",
                    params: {
                        userId: userId,
                        houseId: houseId,
                        currentPage: that.currentPage,
                        pageSize: that.pageSize,

                    }
                }).then((response) => {
                    if (response.data.totalCount >= 1) {
                        that.collectionActive = 1;
                        that.collection = response.data.pageSize[0];
                    }
                    if (response.data.totalCount < 1) {
                        that.collectionActive = 0;
                    }
                })
            },
            getHouseDetail() {
                var that = this;
                const id = this.$route.params.id;
                if (id != undefined) {
                    this.$http({
                        method: "get",
                        changeOrigin: true, // 这个参数代表发送跨域请求
                        url: "house/detail",
                        params: {
                            id: id
                        }
                    }).then((response) => {
                        var data = response.data
                        that.houseDetail = data;
                        var itemId = id;
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
                        that.houseDetail.sn = itemSn;
                        that.getLandlordInfo();
                    })

                }
            },
            getLandlordInfo() {
                var that = this;
                this.$http({
                    method: "get",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "user/find_by_id",
                    params: {
                        id: that.houseDetail.landlordId
                    }
                }).then((response) => {
                    var res = response.data
                    that.landlordInfo = res;
                })
            },
            getUserInfo() {
                let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
                if (userInfo != undefined || userInfo != null) {
                    this.userInfo = userInfo;
                    var oLi = document.getElementById('token');
                    oLi.style.display = "block";
                    var oLi2 = document.getElementById('loginBtn');
                    oLi2.style.display = "none";
                    var oReg = document.getElementById('registerBtn');
                    oReg.style.display = "none";
                    this.isCollected(userInfo.id, this.$route.params.id);
                } else {
                    this.collectionActive = 0;
                }


            },
            toLogin() {
                this.$router.push({
                    name: 'login',
                })

            },
            logout() {
                sessionStorage.clear();
                this.$router.push({
                    name: 'login'
                })
            },
            toRegister() {
                this.$router.push({
                    name: 'register',
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
            toHomeAccount(val) {
                this.$router.push({
                    name: 'homeAccount',
                    params: {
                        activeIndex: val
                    }
                })
            },
            displayQQ() {
                var oQq = document.getElementById('displayQQ');
                oQq.style.display = "block";
            },
            hiddenQQ() {
                var oQq = document.getElementById('displayQQ');
                oQq.style.display = "none";
            },
            toAbout() {
                this.$router.push({
                    name: 'about',
                })
            }
        }
    };
</script>