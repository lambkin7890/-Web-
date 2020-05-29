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
                    <li style="margin-left:-200px;">
                        <b class="mayi_tel_top">
                            <span>客服电话:</span>
                            400-022-5757
                        </b>
                    </li>
                    <li id="user-info" style="display:none">
                        <div class="showinfo" @mouseenter="displayBlock" @mouseleave="displayNone">
                            <a href="javascript:;">
                                <img :src="userImg" class="user_img">
                                <span class="name" id="head_nickname">{{userInfo.username}}</span>
                                <div id="loginInfoPanel" class="head_pop" style="display: none;">
                                    <div class="pop_column">
                                        <p><a href="javascript:;" id="myorder" rel="nofollow"
                                                @click="toHomeAccount(0)">我的订单</a></p>
                                        <p><a href="javascript:;" id="myorder" rel="nofollow" class="slideactive"
                                                @click="toHomeAccount(1)">我的账户</a></p>
                                        <p><a href="javascript:;" id="mycollect" rel="nofollow"
                                                @click="toHomeAccount(2)">我的收藏</a></p>
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
                <div class="progress progress_0"></div>
                <b class="workflow1">提交订单</b>
                <b class="workflow2">支付订金</b>
                <b class="workflow3">房东确认</b>
                <b class="workflow4">预订成功</b>
                <span class="workflow1_1"><a href="javascript:;" class="a_after">1</a></span>
                <span class="workflow1_2"><a href="javascript:;">2</a></span>
                <span class="workflow1_3"><a href="javascript:;">3</a></span>
                <span class="workflow1_4"><a href="javascript:;" class="duigo"></a></span>
            </div>
        </div>

        <!-- 主体内容 -->
        <div class="content clearfloat" style="position: relative;display: none;" id="order-info">
            <form>
                <div class="fl content_left">

                    <!-- 预定信息 -->
                    <div class="cnt">
                        <div class="cnt1 clearfloat">
                            <!--预订信息-->
                            <h2>预订信息</h2>
                            <div class="fl bt-border">
                                <dl class="cnt1_dl clearfloat mt15">
                                    <dt class="fl">预订日期</dt>
                                    <dd class="fl roomer_cnt">
                                        <el-date-picker v-model="dateRange" type="daterange" @change="setDateRange"
                                            range-separator="至" start-placeholder="入住日期" end-placeholder="退房日期"
                                            value-format="yyyy-MM-dd" :picker-options="pickerOptions">
                                        </el-date-picker>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        共<span id="nights">{{orderInfo.nightCount}}</span>晚
                                    </dd>
                                </dl>
                                <!--预订人身份认证信息-->
                                <dl class="cnt1_dl clearfloat mt15">
                                    <dt class="fl">预订人</dt>
                                    <dd v-show="this.userInfo.realName != null && this.userInfo.realName != '' && this.userInfo.idCard != null && this.userInfo.idCard != ''" class="fl relave"
                                        style="margin-top:10px">
                                        <span
                                            style="color:black; margin-left:5px; margin-right:15px;">{{userInfo.realName}}</span>
                                        <span style="color:black;">身份证号：</span>
                                        <span style="color:black">{{userInfo.idCard}}</span>
                                        <span style="margin-left:20px">验证通过不可修改</span>
                                    </dd>
                                    <dd v-show="this.userInfo.realName == null || this.userInfo.realName == '' || this.userInfo.idCard == null || this.userInfo.idCard == ''" class="fl relave"
                                        style="margin-top:10px">
                                        <span style="color:black; margin-left:5px; margin-right:15px;">xxx</span>
                                        <span style="color:black;">身份证号：</span>
                                        <span style="color:black">xxxxxxxxxxxxxxxxxx</span>
                                        <span style="margin-left:20px;cursor: pointer;"
                                            @click="toHomeAccount(1)">立即完善信息</span>
                                    </dd>
                                </dl>
                                <!-- 联系人手机号 -->
                                <dl class="cnt1_dl clearfloat tel_box mt15">
                                    <dt class="fl">联系人手机号</dt>
                                    <dd class="fl relative">
                                        <input type="text" oninput="value=value.replace(/[^\d]/g,'')" maxlength="11"
                                            placeholder="必填" class="import fl" id="mobile"
                                            v-model="bookOrderForm.mobile" autocomplete="off">
                                    </dd>
                                </dl>
                            </div>

                        </div>

                    </div>


                    <!--start-->
                    <div id="insuranceDiv" class="cnt1 cnt2 cnt3 cnt4 clearfloat">
                        <h2 class="need">入住人信息</h2>
                        <!--意外险部分-->
                        <div class="fl cle bt-border">
                            <!--入住人数-->
                            <dl class="cnt1_dl clearfloat mt15">
                                <dt class="fl">入住人数</dt>
                                <dd class="fl relave people_in">
                                    <div class="select_parent w92 mar_r_6 fl">
                                        <select v-model="bookOrderForm.checkinNum" class="select_css w92 rens"
                                            autocomplete="off">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                        </select>
                                    </div>
                                </dd>
                            </dl>
                            <dl class="cnt1_dl clearfloat mt15">
                                <dt class="fl">入住人姓名</dt>
                                <dd class="fl relative">
                                    <input type="text" maxlength="4" placeholder="必填" class="import fl" id="mobile"
                                        v-model="bookOrderForm.checkinName" autocomplete="off">
                                    <span style="margin-left:15px;line-height: 33px;">其中一位入住人真实姓名</span>
                                </dd>
                            </dl>
                            <dl class="cnt1_dl clearfloat mt15">
                                <dt class="fl">入住人身份证号</dt>
                                <dd class="fl relative">
                                    <input type="text" maxlength="18" oninput="value=value.replace(/[^\w\.\/]/ig,'')"
                                        placeholder="必填" class="import fl" id="mobile"
                                        v-model="bookOrderForm.checkinIdCard" autocomplete="off" style="width:200px;">
                                    <span style="margin-left:15px;line-height: 33px;">对应身份证号</span>
                                </dd>
                            </dl>

                        </div>
                    </div>

                    <div class="cnt_foot" style="position:relative;">
                        <div class="order_sub_parent">
                            <a class="submit fl" @click="submitOrder">提交订单</a>
                        </div>
                    </div>

                </div>


            </form>
            <!--右侧开始-->
            <div class="fr content_right  pin" id="pin1"
                style="position: static; left: 886.667px; top: 0px; right: 0px; bottom: 30px;">
                <div class="right_cnt">
                    <img v-if="houseInfo.photo!=null&&houseInfo.photo!=undefined"
                        :src="'http://localhost:8087'+houseInfo.photo" style="vertical-align: middle">
                    <!--右侧房间开始-->
                    <div class="room_intro relave clearfloat">
                        <strong>{{houseInfo.name}}</strong>
                        <span>地址：{{houseInfo.address}}</span>
                    </div>
                    <div
                        style="padding: 6px 10px 0px 10px;line-height: 22px;position:relative;left:0;top:0px;color:#ef9b3e;">
                        此房源为速订房源，无需房东确认，支付后即预订成功。
                    </div>
                    <div class="time_true">
                        <p><span id="checkindayr">{{orderInfo.checkinDay}}</span>&nbsp;至&nbsp;<span
                                id="checkoutdayr">{{orderInfo.checkoutDay}}</span></p>
                        <p>共<span id="totlenight3">{{orderInfo.nightCount}}</span>晚</p>
                    </div>
                    <!--右侧表格开始-->
                    <table class="tab_fr">
                        <tbody>
                            <tr class="room_tr">
                                <td>
                                    <div style="width:178px;">房费</div>
                                </td>
                                <td class="relave bon tab_tex">
                                    <div style="width:130px;">
                                        ¥<i id="showtotalprice2">{{houseInfo.price}}</i>
                                    </div>
                                </td>
                            </tr>

                        </tbody>
                    </table>

                    <!--支付方式-->
                    <div class="on-line">
                        <div class="online_1 relave clearfloat">
                            <div class="clearfloat">
                                <b class="color font">订单总额</b>
                                ¥<i class="font2" id="actuallytotalprice">{{orderInfo.totalPrice}}</i>
                            </div>
                            <div class="clearfloat relave">
                                <b class="color font3">线上支付</b>
                                <i
                                    style="float:left;line-height:35px;margin-right:4px;font-size:24px;color:#ff5d51;">¥</i>
                                <i class="font4" id="onlinePayAmountShow"
                                    style="color:#ff5d51">{{orderInfo.totalPrice}}</i>
                                <span class="asote dingw4"></span>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
        </div>
        <Footer></Footer>
    </div>
</template>

<script>
    import Footer from './Footer.vue';
    import 'jquery-ui-dist/jquery-ui';
    export default {
        name: "SubmitOrder",
        data() {
            return {
                bookOrderForm: {
                    id: '',
                    sn: '',
                    houseId: '',
                    houseHolderId: '',
                    checkinDay: '',
                    checkoutDay: '',
                    userId: '',
                    mobile: '',
                    checkinNum: 1,
                    checkinName: '',
                    checkinIdCard: '',
                    nightCount: '',
                    totalPrice: '',
                    status: 0,
                    createTime: ''
                },
                userInfo: {
                    id: '',
                    photo: '',
                    realName: '',
                    idCard: '',
                    mobile: '',
                    username: ''
                },
                houseInfo: {
                    id: '',
                    name: '',
                    photo: '',
                    address: '',
                    price: ''
                },
                orderInfo: {
                    totalPrice: '',
                    nightCount: '',
                    checkinDay: '',
                    checkoutDay: ''
                },
                dateRange: [],
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() < Date.now() - 24 * 60 * 60 * 1000;
                    },
                },
            }
        },
        computed: {
            userImg() {
                var imgName = this.userInfo.photo;
                if (imgName == '' || imgName == null) {
                    return require(`../assets/images/user-photo/landlord/kodinger.jpg`);
                } else {
                    var userImg = 'http://localhost:8087' + imgName;
                    return userImg;
                }
            }
        },
        components: {
            Footer
        },
        mounted() {
            setTimeout(() => {
                this.changeTime();
            }, 100);
            this.getUserInfo();
        },
        created() {
            this.getOrderInfo();
        },
        methods: {
            submitOrder() {
                if (this.userInfo.realName == null || this.userInfo.realName == "" || this.userInfo.idCard == null || this.userInfo.idCard == "") {
                    alert('请完善身份信息后，再进行提交！')
                } else {
                    this.bookOrderForm.sn = new Date().getTime();
                    this.bookOrderForm.userId = this.userInfo.id;
                    this.bookOrderForm.houseId = this.houseInfo.id;
                    this.bookOrderForm.houseHolderId = this.houseInfo.landlordId;
                    this.bookOrderForm.nightCount = this.orderInfo.nightCount;
                    this.bookOrderForm.totalPrice = this.orderInfo.totalPrice;
                    this.bookOrderForm.checkinDay = this.dateRange[0],
                        this.bookOrderForm.checkoutDay = this.dateRange[1],
                        this.$http({
                            url: "book_order/add",
                            method: "post",
                            changeOrigin: true, // 这个参数代表发送跨域请求
                            //注意：post方式提交 参数这里可以写data get方式提交用params
                            params: this.bookOrderForm
                        }).then((response) => {
                            var res = response.data;
                            if (res.type == "success") {
                                alert(res.msg);
                                localStorage.setItem("orderInfo", JSON.stringify(this.bookOrderForm));
                                this.$router.push({
                                    name: 'pay'
                                })
                            } else {
                                alert(res.msg);
                            }

                        })
                }

            },
            changeTime() {
                $(function () {
                    $(".datepicker").datepicker({ "dateFormat": "yy-mm-dd" });
                });
            },
            getUserInfo() {
                var userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
                if (userInfo != undefined || userInfo != null) {
                    this.userInfo = userInfo;
                    var oLi = document.getElementById('user-info');
                    oLi.style.display = "block";
                    var oLi2 = document.getElementById('order-info');
                    oLi2.style.display = "block";
                }
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
                    name: 'login'
                })
            },
            dateChangeCheckinday(val) {
                console.log(val);
                this.bookOrderForm.checkinDay = val;
            },
            dateChangeCheckoutday(val) {
                console.log(val);
                this.bookOrderForm.checkoutDay = val;
            },
            toHomeAccount(val) {
                this.$router.push({
                    name: 'homeAccount',
                    params: {
                        activeIndex: val
                    }
                })
            },
            toAbout() {
                this.$router.push({
                    name: 'about',
                })
            },
            getOrderInfo() {
                var storage = localStorage;
                this.houseInfo = JSON.parse(storage.getItem("houseInfo"));
                this.orderInfo.nightCount = storage.getItem("nightCount");
                this.orderInfo.totalPrice = storage.getItem("totalPrice");
                this.orderInfo.checkinDay = storage.getItem("checkinDay");
                this.orderInfo.checkoutDay = storage.getItem("checkoutDay");
                this.dateRange[0] = storage.getItem("checkinDay");
                this.dateRange[1] = storage.getItem("checkoutDay");
            },
            setDateRange() {
                localStorage.setItem("checkinDay", this.dateRange[0]);
                localStorage.setItem("checkoutDay", this.dateRange[1]);
                var sArr = this.dateRange[1].split("-");
                var eArr = this.dateRange[0].split("-");
                var sRDate = new Date(sArr[0], sArr[1], sArr[2]);
                var eRDate = new Date(eArr[0], eArr[1], eArr[2]);
                this.orderInfo.nightCount = (sRDate - eRDate) / (24 * 60 * 60 * 1000);
                localStorage.setItem("nightCount", this.orderInfo.nightCount);
                localStorage.setItem("totalPrice", this.orderInfo.nightCount * this.houseInfo.price);
                this.orderInfo.checkinDay = localStorage.getItem("checkinDay");
                this.orderInfo.checkoutDay = localStorage.getItem("checkoutDay");
                this.getOrderInfo();
            }
        }

    };
</script>


<style scoped src="../assets/css/submitOrder.css"></style>
<style>
    .roomer_cnt .el-range-editor .el-range-input {
        margin-left: 10px;
    }

    .roomer_cnt .el-date-editor .el-range__close-icon {
        display: none;
    }
</style>
<style scoped>
    @import '//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css';

    .el-row {
        margin-bottom: 20px;
    }

    .el-col {
        border-radius: 4px;
    }

    .g-footer {
        background-color: #fff;
    }

    .calendar-input-wrap .el-date-editor.el-input {
        width: 150px;
        margin: 0px 5px;
    }

    .calendar-input-wrap .el-input__icon {
        text-align: center;
        line-height: 38px;
    }

    .calendar-input-wrap .el-input__prefix,
    .el-input__suffix {
        color: #5b5e62;
    }

    .calendar-input-wrap .el-input__inner {
        border: 1px solid #c1bcbc;
        border-radius: 0px;
        height: 36px;
    }

    .pop_column {
        margin-left: -10px;
    }

    .el-date-editor--daterange.el-input__inner {
        width: 240px;
        border-radius: 0;
        border: 1px solid #C9CACB;
        margin-left: 6px;
        height: 36px;
        margin-right: 20px;
    }
</style>