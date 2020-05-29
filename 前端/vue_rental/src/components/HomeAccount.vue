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
                        <div class="showinfo">
                            <a href="javascript:;">
                                <img :src="userImg" class="user_img">
                                <span class="name" id="head_nickname">{{userInfo.username}}</span>
                            </a>
                        </div>
                    </li>
                    <li id="logout" style="display:none">
                        <a href="javascript:;" @click="logout">退出登录</a>
                    </li>
                    <li>
                        <a class="recruit" href="javascript:;" @click="toAbout">关于</a>
                    </li>
                </ul>       
            </div>
        </div>
        <!-- 头部导航结束 -->

        <div class="online_btn"></div>

        <div class="main">
            <ul class="breadcrumb clearfix">
                <li><a>我是房客</a><span class="divider"></span></li>
                <li v-show="activeIndex===0">订单管理<span class="divider"></span></li>
                <li v-show="activeIndex===1">账户管理<span class="divider"></span></li>
                <li v-show="activeIndex===2">我的收藏<span class="divider"></span></li>
            </ul>
            <div class="personalCenter clearfix" id="personal-center" style="display: none;">
                <ul class="center-sidebar">
                    <li><a @click="activeIndex=0" :class="{slideactive:activeIndex===0}">订单管理</a></li>
                    <li><a @click="activeIndex=1" :class="{slideactive:activeIndex===1}">账户管理</a></li>
                    <li><a @click="activeIndex=2" :class="{slideactive:activeIndex===2}">我的收藏</a></li>
                </ul>
                <OrderManage v-show="activeIndex===0"></OrderManage>
                <AccountManage v-show="activeIndex===1"></AccountManage>
                <MyCollection v-show="activeIndex===2"></MyCollection>
            </div>
        </div>
        
        
        <Footer></Footer>
    </div>
</template>
<style scoped src="../assets/css/submitOrder.css"></style>
<style scoped src="../assets/css/home-account.css"></style>
<style scoped>
.g-footer{
    border-top: 1px solid #dce0e0;
}

</style>
<script>
    import Footer from "./Footer.vue";
    import OrderManage from "./tenant_center/orderManage.vue";
    import AccountManage from "./tenant_center/accountManage.vue";
    import MyCollection from "./tenant_center/myCollection.vue";
    export default {
        name:"HomeAccount",
        data() {
            return {                   
                userInfo:{
                    id:'',
                    photo:'',
                    realName:'',
                    idCard:'',
                    mobile:'',
                    username:''
                },
                activeIndex:0
            }
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
			}
        },
        components:{
            Footer,
            OrderManage,
            AccountManage,
            MyCollection
        },
        mounted(){
            this.getUserInfo();
            if(this.$route.params.activeIndex!=null&&this.$route.params.activeIndex!=undefined){
                this.activeIndex=this.$route.params.activeIndex;
            }
        },
        methods:{
            getUserInfo(){
                var userInfo=JSON.parse(sessionStorage.getItem("userInfo"));
                if(userInfo!=undefined||userInfo!=null){
					this.userInfo=userInfo;
                    var oLi=document.getElementById('user-info');
					oLi.style.display="block";
					var oLi2=document.getElementById('logout');
					oLi2.style.display="block";
                    var oLi3=document.getElementById('personal-center');
					oLi3.style.display="block";
				}
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
    }
</script>