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
                    <li><router-link to="/long_rent_index" style="color:#ff545a">长租首页</router-link></li>
                    <li style="display: none;" id="token" @mouseenter="displayBlock" @mouseleave="displayNone">
                        <a href="javascript:;">
                            <img v-bind:src="userImg" alt="user_img"> 
                            <span>{{userInfo.username}}</span>
                            <div id="loginInfoPanel" class="head_pop" style="display: none;">
                                <div class="pop_column">
                                    <p><a href="javascript:;" id="myorder" @click="toHomeAccount(0)">我的订单</a></p>
                                    <p><a href="javascript:;" id="myorder" class="slideactive" @click="toHomeAccount(1)">我的账户</a></p>
                                    <p><a href="javascript:;" id="mycollect" @click="toHomeAccount(2)">我的收藏</a></p>
                                    <p class="textCt"><a href="javascript:;" class="stclick" @click="logout">退出登录</a></p>
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
        <!-- 头部导航 END -->

        <div class="p_1180 p_crumbs">
            <router-link to="/long_rent_index" style="color:#ff545a;margin-right:5px">首页</router-link>&gt; 
            <span>房屋编号：{{houseDetail.sn}}</span>
        </div>

        <div class="wrapper">
            <h3 class="house-title" style="display: flex;">
                <div class="strongbox" style="font-weight:normal;">{{houseDetail.name}}</div>
                <div class="collection" v-show="collectionActive===0" style="cursor:pointer;" @click="collectHouse()">
                    <i class="iconfont icon-Collection"></i>
                    <span>收藏</span>                     
                </div>   
                <div class="collection" v-show="collectionActive===1" style="cursor:pointer;" @click="deleteCollection">
                    <i class="iconfont icon-shoucang"></i>
                    <span>取消收藏</span>                     
                </div> 
            </h3>
            <div class="title-basic-info clearfix">
                <span class="light info-tag">
                    <em><b class="strongbox" style="font-weight: normal;">{{houseDetail.price}}</b></em>元/月
                </span>
                <span class="info-tag">
                        <em><b class="strongbox" style="font-weight: normal;">{{houseDetail.bedRoomNum}}</b></em>室
                        <em><b class="strongbox" style="font-weight: normal;">{{houseDetail.sittingRoomNum}}</b></em>厅
                    </span>
                <span class="info-tag no-line"><em><b class="strongbox" style="font-weight: normal;">{{houseDetail.area}}</b></em>平方米</span>
                <ul class="title-label cf">
                    <li v-if="houseDetail.isEntire==1" class="title-label-item rent">整租</li>
                    <li v-if="houseDetail.isSouth==1" class="title-label-item buy">朝南</li>
                    <li v-if="houseDetail.isElevator==1" class="title-label-item subway">有电梯</li>
                </ul>
            </div>
            <div class="mainbox cf">
                <div class="lbox">                     
                    <div class="switch_warpper">
                        <div class="switch_with_map">
                            <div class="inner_switcher" id="inner_switcher">
                                <img style="height: 450px;width: 800px;" :src="'http://localhost:8087'+houseDetail.photo">
                                
                            </div>
                           
                        </div>
                        
                    </div>           
                    <!--房屋信息-->
                    <div class="mod-title bottomed">
                        <h3 id="houseInfo" class="title nav-scroll">房屋信息</h3>
                        <div class="right-info">
                            <span id="houseCode">房屋编号：{{houseDetail.sn}},</span>
                            发布时间：<b class="strongbox" style="font-weight: normal;">{{houseDetail.createTime}}</b>
                        </div>
                    </div>
                    <ul class="house-info-zufang cf">
                        <li class="full-line cf">
                            <span class="price">
                                <em><b class="strongbox" style="font-weight: normal;">{{houseDetail.price}}</b></em>元/月
                            </span>
                        </li>
                        <li class="house-info-item l-width">
                            <span class="type">户型：</span>
                            <span class="info">
                                <b class="strongbox" style="font-weight: normal;">{{houseDetail.bedRoomNum}}</b>室
                                <b class="strongbox" style="font-weight: normal;">{{houseDetail.sittingRoomNum}}</b>厅
                                <b class="strongbox" style="font-weight: normal;">{{houseDetail.toiletNum}}</b>卫
                            </span>
                        </li>
                        <li class="house-info-item">
                            <span class="type">面积：</span>
                            <span class="info">
                                <b class="strongbox" style="font-weight: normal;">{{houseDetail.area}}平方米</b>
                            </span>
                        </li>
                        <li class="house-info-item l-width">
                            <span class="type">楼层：</span>
                            <span class="info">{{houseDetail.floorRange}}(共33层)</span>
                        </li>
                        <li class="house-info-item l-width">
                            <span class="type">地址：</span>
                            <span class="info">{{houseDetail.address}}</span>
                        </li>
                    </ul>
                    
                    <!-- 房源概况 -->
                    <div class="mod-title bottomed">
                        <h3 class="title">房源概况</h3>
                    </div>
                    <div class="auto-general">
                        <b class="strongbox" style="font-weight: normal;">{{houseDetail.introduce}}</b>
                    </div>                                         
                </div>  
                <!-- 房东信息 -->
                <div class="rbox">         
                    <div class="broker-card">
                        <div class="broker-border">
                            <img class="broker-photo-main" :src="landlordImg" alt="房东头像">
                            <h2 class="broker-name" >{{landlordInfo.realName}}</h2>
                            <ul>
                                <li>邮箱：268312386@qq.com</li>
                                <li>手机号码：13452678222</li>
                            </ul>
                        </div>
                    </div>         
                    <!-- 如果有扫一扫 -->
                    <div class="chat-card show">
                        <p class="chat-word">微信扫一扫，在线聊</p>
                        <img src="../assets/images/u=1156543378,4129437373&fm=11&gp=0.jpg" alt="房东微信二维码" class="chat-img" id="chatImg">
                    </div>       
                </div>
            </div>

        </div>

        <Footer></Footer>
    </div>
</template>
<style scoped src="../assets/css/details_new.css"></style>
<style scoped src="../assets/css/long-rent/detail.css"></style>
<style scoped src="../assets/css/iconfont/iconfont.css"></style>
<style scoped>
.QQ-area{
	display:none;
	position: absolute;
	width: 170px;
	height: 43px;
	background-color: #fff;
	margin-left: -50px;
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
    left: 70px;
    display: inline-block;
    width: 0;
    height: 0;
    border-width: 0 7px 7px;
    border-style: solid;
	border-color: transparent transparent #fff;
}
#token img{
    width: 32px;
    height: 32px;
    border-radius: 50%;
    border: 1px solid #fff;
    margin-bottom: 5px;
    vertical-align: middle;
}
#token span{
    margin-left:2px;
}
.collection{
  font-size:16px;
  color:#605a5a;
  margin-left:210px;
}
.collection i.icon-Collection{
  margin-right:3px;
}
.broker-border li {
    font-size: 15px;
    margin-top: 10px;
    margin-left: 10px;
    color: #545454;
}
</style>
<script>
import Footer from "@/components/Footer.vue";
export default {
    name: "LongRentDetail",
    data() {
        return {
            houseDetail:{
                id:"",
                sn:'',
                name:"",
                photo: "",
                price: "",
                address: "",
                introduce: ""
            },
            userInfo:{
                username:'',
                photo:''
            },
            landlordInfo:{
                photo:'',
                nickName:'',
                mobile:'',
                realPhoto:''
            },
            collectionActive:0,
            collection:{
                id:''
            },
            currentPage:1,
            pageSize:10
        };
    },
    components:{
        Footer
    },
    computed:{
        userImg(){
            var imgName=this.userInfo.photo;
            if(imgName==''||imgName==null){
                return require(`../assets/images/user-photo/landlord/kodinger.jpg`);
            }else{
                var userImg='http://localhost:8087'+imgName;
                return userImg;
            }
        },
        landlordImg(){
            var imgName=this.landlordInfo.realPhoto;
            if(imgName==''||imgName==null){
                return require(`../assets/images/user-photo/landlord/kodinger.jpg`);
            }else{
                var landlordImg='http://localhost:8087'+imgName;
                return landlordImg;
            }
        }
    },
    mounted() {
        this.getUserInfo();
    },
    created:function(){
        this.getDetail();
    },
    methods:{
        getDetail(){
            var that=this;
            const id=this.$route.params.id;
            if(id!=undefined){
                this.$http({
                    method: "get",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "long_rent_house/detail",
                    params:{
                        id:id
                    }
                }).then((response) => {
                    var data = response.data
                    that.houseDetail=data;
                    var itemId=id;
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
                    that.houseDetail.sn=itemSn;
                    that.getLandlordInfo();
                })
            }
        },
        collectHouse() {
            var that = this;
            console.log(this.houseDetail.id)
            if(this.userInfo.id != ''&& this.userInfo.id!=undefined) {
                this.$http({
                    method: "post",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "long_rent_collection/add",
                    params: {
                        userId: that.userInfo.id,
                        longRentHouseId: that.houseDetail.id
                    }
                }).then((response) => {
                    that.collectionActive = 1;
                })
            }else{
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
                    url: "long_rent_collection/delete",
                    params: {
                        id: that.collection.id
                    }
                }).then((response) => {
                    that.collectionActive = 0;
                })
            }

        },
        isCollected(userId, longRentHouseId) {
            var that = this;
            this.$http({
                method: "post",
                changeOrigin: true, // 这个参数代表发送跨域请求
                url: "long_rent_collection/list",
                params: {
                    userId: userId,
                    longRentHouseId: longRentHouseId,
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
        getLandlordInfo(){
            var that=this;
            this.$http({
                method: "get",
                changeOrigin: true, // 这个参数代表发送跨域请求
                url: "user/find_by_id",
                params:{
                    id:that.houseDetail.landlordId
                }
            }).then((response) => {
                var res = response.data
                that.landlordInfo=res;
            })
        },
        getUserInfo(){
            let userInfo=JSON.parse(sessionStorage.getItem("userInfo"));
            if(userInfo!=undefined||userInfo!=null){
                this.userInfo = userInfo;
                var oLi=document.getElementById('token');
                oLi.style.display="block";
                var oLi2=document.getElementById('loginBtn');
                oLi2.style.display="none";
                var oReg=document.getElementById('registerBtn');
                oReg.style.display="none";
                this.isCollected(userInfo.id,this.$route.params.id);
            }else{
                this.collectionActive=0;
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
		toLogin() {
			this.$router.push({
				name: 'login',
			})

		},
		logout(){
			sessionStorage.clear();
			this.$router.push({
				name: 'login',
			})
		},
		toRegister() {
			this.$router.push({
				name: 'register',
			})
        },
        toHomeAccount(val){
            this.$router.push({
                name: 'homeAccount',
                params:{
                    activeIndex:val
                }
            })
        },
        displayQQ(){
			var oQq=document.getElementById('displayQQ');
			oQq.style.display="block";
		},
		hiddenQQ(){
			var oQq=document.getElementById('displayQQ');
			oQq.style.display="none";
		},
        toAbout(){
            this.$router.push({
                name: 'about',
            })
        }
    }
}
</script>