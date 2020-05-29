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
                    <li id="user-info" style="display:none">
                        <div class="showinfo" @mouseenter="displayBlock" @mouseleave="displayNone">
                            <a href="javascript:;">
                                <img :src="userImg" class="user_img">
                                <span class="name" id="head_nickname">{{userInfo.username}}</span>
                                <div id="loginInfoPanel" class="head_pop" style="display: none;">
                                    <div class="pop_column">
                                        <p><a href="javascript:;" id="myorder" rel="nofollow" @click="toHomeAccount(0)">我的订单</a></p>
                                        <p><a href="javascript:;" id="myorder" rel="nofollow" class="slideactive" @click="toHomeAccount(1)">我的账户</a></p>
                                        <p><a href="javascript:;" id="mycollect" rel="nofollow" @click="toHomeAccount(2)">我的收藏</a></p>
                                        <p class="textCt"><a href="javascript:;" class="stclick" @click="logout">退出登录</a></p>
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
                <div class="progress progress_1"></div>
                <b class="workflow1">提交订单</b>
                <b class="workflow2">支付订金</b>
                <b class="workflow3">房东确认</b>
                <b class="workflow4">预订成功</b>
                <span class="workflow1_1"><a href="javascript:;" class="a_after">1</a></span>
                <span class="workflow1_2"><a href="javascript:;" class="a_after">2</a></span>
                <span class="workflow1_3"><a href="javascript:;">3</a></span>
                <span class="workflow1_4"><a href="javascript:;" class="duigo"></a></span>                
            </div>
        </div>


        <div class="content clearfloat min_h">
            <div class="cnt_tix clearfloat">
                <!-- <div class="fl duigo_par"><span></span></div> -->
                <div class="fl you_ti">
                    <p class="you_p1">此房源为速订房源，支付后即可预订成功，请放心支付。</p>
                </div>
            </div>
            <div class="pay_parent">
                <dl class="clearfloat" style="height:44px">
                    <dt class="fl">线上支付</dt>
                    <dd class="fl color_tao"><span id="onlinePayAmountShow">{{orderInfo.totalPrice}}</span>元</dd>
                </dl>
                <div id="coupon"></div>
                <!--选择支付方式-->
                <dl class="clearfloat" style="height:76px;display:flex;">
                    <dt class="fl bank_dt">支付方式</dt>
                    <dd class="bank fl">
                        <ul class="clearfloat bank_ul1">
                            <li class="bank_radio clearfloat">
                                <label>
                                    <input type="radio" value="1" checked="checked">
                                    <img src="../assets/images/logo/zhifubao.jpg" title="支付宝">
                                </label>
                            </li>
                            <!-- <li class="bank_radio clearfloat">
                                <label>
                                    <input type="radio" name="paymethod" value="4">
                                    <img src="//staticnew.mayi.com/resourcesWeb/ordernew/images/weixin.jpg" title="微信支付">
                                </label>
                            </li>
                            <li class="bank_radio clearfloat">
                                <label class="yinhk">
                                    <input type="radio" name="paymethod" value="2">
                                    <img src="//staticnew.mayi.com/resourcesWeb/ordernew/images/bank_ka.jpg" title="银行卡">
                                </label>
                            </li> -->
                        </ul>
                    </dd>
                </dl>
            </div>
            <div class="go_pay">
                <a @click="goPay">去支付</a>
            </div>
        </div>
        <Footer></Footer>
    </div>
</template>
<style scoped src="../assets/css/submitOrder.css"></style>
<style scoped src="../assets/css/pay.css"></style>
<style scoped>
    .content{
        height:auto;
        margin-bottom: 0px;
    }
    .pop_column{
        left:-10px;
    }
</style>
<script>
    import Footer from './Footer.vue';
    export default {
        name: "Pay",
        data() {
            return {
                orderInfo:{
                    id:'',
                    sn:'',
                    totalPrice:'',
                    houseName:''
                },
                userInfo:{
                    id:'',
                    photo:'',
                    username:''
                }
            }
        },
        components:{
            Footer
        },
        computed:{
            userImg(){
				var imgName=this.userInfo.photo;
				if(imgName==''||imgName==null){
					return require(`../assets/images/user-photo/landlord/person.png`);
				}else{
                    var userImg='http://localhost:8087'+imgName;
                    return userImg;
                }
			}
        },
        mounted(){
            this.getUserInfo();
        },
        created(){
            this.orderInfo=JSON.parse(localStorage.getItem("orderInfo"));
            this.orderInfo.houseName=JSON.parse(localStorage.getItem("houseInfo")).name;
        },
        methods: {
            goPay() {
                const newTab = window.open();
                this.$http({
                    url: "book_order/pay",
                    method: "post",
                    changeOrigin: true, 
                    params: {
                        oid:this.orderInfo.sn,
                        houseName:this.orderInfo.houseName,
                        totalPrice:this.orderInfo.totalPrice
                    }
                }).then((response) => {
                    const div = document.createElement('div');
                    div.innerHTML = response.data; // html code
                    newTab.document.body.appendChild(div);
                    newTab.document.forms[0].submit();
                })
            },
            getUserInfo(){
                var userInfo=JSON.parse(sessionStorage.getItem("userInfo"));
                if(userInfo!=undefined||userInfo!=null){
					this.userInfo=userInfo;
                    var oLi=document.getElementById('user-info');
					oLi.style.display="block";
				}
            },
            displayBlock(){
				var oPa=document.getElementById('loginInfoPanel');
				oPa.style.display="block";
			},
			displayNone(){
				var oPa=document.getElementById('loginInfoPanel');
				oPa.style.display="none";
            },
            toHomeAccount(val){
				this.$router.push({
					name: 'homeAccount',
					params:{
						activeIndex:val
					}
				})
			},
            logout(){
				sessionStorage.clear();
				this.$router.push({
                    name:'login'
                })
            },
            toAbout(){
				this.$router.push({
					name: 'about',
				})
			}
        }
    };
</script>