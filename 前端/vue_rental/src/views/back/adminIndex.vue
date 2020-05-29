<template>
    <div class="main">
        <div class="header-area">
            <!-- Start Navigation -->
            <nav class="navbar navbar-default bootsnav  navbar-sticky navbar-scrollspy"
                data-minus-value-desktop="70" data-minus-value-mobile="55" data-speed="1000">

                <div class="container">

                    <!-- Start Header Navigation -->
                    <div class="navbar-header">
                        <a class="navbar-brand" href="javascript:;">
                            <i class="iconfont icon-product"></i>
                            后台管理中心
                        </a>
                    </div>
                    <!--/.navbar-header-->
                    <!-- End Header Navigation -->

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse menu-ui-design" id="navbar-menu">
                        <ul class="nav navbar-nav navbar-right" data-in="fadeInDown" data-out="fadeOutUp">
                            <li class="scroll" style="display: none;" id="token">
                                <a href="javascript:;">
                                    <img v-bind:src="userImg" alt="user_img"> 
                                    <span>{{userInfo.username}}</span>
                                </a>
                            </li>
                            <li class="scroll"><a href="javascript:;" @click="logout">退出登录</a></li>
                        </ul>
                        <!--/.nav -->
                    </div><!-- /.navbar-collapse -->
                </div>
                <!--/.container-->
            </nav>
            <!--/nav-->
        </div>

        <div class="body-area">
            <el-container style="min-height:430px">
                <el-aside width="200px">
                    <el-radio-group v-model="isCollapse" style="margin-bottom: 5px;">
                        <el-radio-button :label="false">展开</el-radio-button>
                        <el-radio-button :label="true">收起</el-radio-button>
                    </el-radio-group>
                    <el-menu default-active="this.$route.path" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
                        :collapse="isCollapse" router>
                        <el-submenu index="1">
                            <template slot="title">
                                <i class="el-icon-office-building"></i>
                                <span slot="title">房源管理</span>
                            </template>
                            <el-menu-item index="" @click="activeIndex=0" :class="{active:activeIndex===0}">短租</el-menu-item>
                            <el-menu-item index="" @click="activeIndex=1" :class="{active:activeIndex===1}">长租</el-menu-item>
                        </el-submenu>
                        <el-menu-item index="" @click="activeIndex=2" :class="{active:activeIndex===2}">
                            <i class="el-icon-time"></i>
                            <span slot="title">订单管理</span>
                        </el-menu-item>
                        <el-menu-item index="" @click="activeIndex=3" :class="{active:activeIndex===3}">
                            <i class="el-icon-user"></i>
                            <span slot="title">用户管理</span>
                        </el-menu-item>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-star-off"></i>
                                <span slot="title">收藏管理</span>
                            </template>
                            <el-menu-item index="" @click="activeIndex=4" :class="{active:activeIndex===4}">短租</el-menu-item>
                            <el-menu-item index="" @click="activeIndex=5" :class="{active:activeIndex===5}">长租</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main>
                    <House v-show="activeIndex===0"></House>
                    <LongRentHouse v-show="activeIndex===1"></LongRentHouse>
                    <BookOrder v-show="activeIndex===2"></BookOrder>
                    <UserMc v-show="activeIndex===3"></UserMc>
                    <CollectionMc v-show="activeIndex===4"></CollectionMc>
                    <LongRentCollection v-show="activeIndex===5"></LongRentCollection>
                </el-main>
            </el-container>
        </div>

        <div class="footer-area">
            <span class="g-crt-int-links">
                <a href="javascript:void(0)">关于我们</a> 
                <span class="g-text-sep">|</span> 
                <a href="javascript:;">我是租客</a> 
                <span class="g-text-sep">|</span> 
                <a href="javascript:;">加入平台</a> 
                <span class="g-text-sep">|</span> 
                <a href="javascript:;">网站地图</a>
            </span>      
        </div>
    </div>
</template>


<script>
import House from "@/components/admin/houseMc.vue";
import BookOrder from "@/components/admin/BookOrderMC.vue";
import LongRentHouse from "@/components/admin/LongRentHouseMC.vue";
import UserMc from "@/components/admin/userMc.vue";
import CollectionMc from "@/components/admin/collectionMc.vue";
import LongRentCollection from "@/components/admin/longRentCollectionMc.vue";
export default {
    data() {
        return {
            isCollapse: true,
            userInfo:{
                id:'',
                username:'',
                photo:''
            },
            activeIndex:0
           
        };
    },
    components: {
        House,
        BookOrder,
        LongRentHouse,
        UserMc,
        CollectionMc,
        LongRentCollection
    },
    computed:{
        userImg(){
            var imgName=this.userInfo.photo;
            if(imgName==''||imgName==null){
                return require(`../../assets/images/user-photo/landlord/kodinger.jpg`);
            }else{
                var userImg='http://localhost:8087'+imgName;
                return userImg;
            }
        }
    },
    mounted() {
        this.getUserInfo();
    },
    methods: {
        handleOpen(key, keyPath) {
            // console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            // console.log(key, keyPath);
        },
        getUserInfo(){
            let userInfo=JSON.parse(sessionStorage.getItem("userInfo"));
            if(userInfo!=undefined||userInfo!=null){
                this.userInfo = userInfo;
                var oLi=document.getElementById('token');
                oLi.style.display="block";
            }else{
                this.$router.push({
                    name: 'backLogin',
                })
            }
                
        },
        logout(){
            sessionStorage.clear();
            this.$router.push({
                name: 'backLogin',
            })
        },
    }
};
</script>

<style>
.el-main .main .el-table td, .el-main .main .el-table th {
    text-align: center;
}
.el-main .main .el-dialog__body{
    text-align:left;
    padding-left:30px;
    padding-right:45px;
}
.el-main .main .el-dialog__footer{
    padding-right:45px;
    padding-top:0;
}
</style> 
<style scoped src="../../assets/css/iconfont/iconfont.css"></style>
<style>
a:focus {
    outline: none;
}
</style>
<style scoped>
.main{
    background-color: #fff;
}
.header-area{
    height: 86px;
}
.header-area nav.navbar.bootsnav ul.nav > li > a{
  color:#54595a;
}
.header-area nav{
    border-bottom: 1px solid #edeff1;
}
.body-area{
    margin:20px 15px 0 15px;;
    height:auto;
}
.icon-product{
    font-size: 20px;
}
a.navbar-brand{
    font-weight:500;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 430px;
}
@media (max-width: 767px) {
  .el-aside {
    width: 130px !important;
  }
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 130px;
    min-height: 400px;
  }
}
.el-aside{
    text-align: center;
    padding-top: 20px;
}
.el-main {
  text-align: center;
  padding: 20px;
  overflow-x: visible;
}
#token{
	margin-top:-7px;
	margin-bottom: -9px
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
.navbar-header a.navbar-brand:hover,.navbar-header a.navbar-brand:visited,.navbar-header a.navbar-brand:link{
    font-weight:500;
}
.footer-area{
    text-align: center;
    padding-bottom: 13px;
    border-top: 1px solid #edeff1;
    padding-top: 12px;
    padding-left: 70px;
}
.g-crt-int-links a{
  font-size:14px;
  margin-right:5px;
  margin-left:5px;
}
.g-crt-int-links a:hover,.g-crt-int-links a:link,.g-crt-int-links a:visited{
  text-decoration: none;
  color:#7f7d7d;
}
.g-crt-int-links a:focus{
    outline:none;
}
.g-text-sep{
  color: #7f7d7d;
}

</style>

  