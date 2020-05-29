<template>
	<div class="main">
		<section class="top-area">
			<div class="header-area">
				<!-- Start Navigation -->
				<nav class="navbar navbar-default bootsnav  navbar-sticky navbar-scrollspy navbar-fixed-top"
					data-minus-value-desktop="70" data-minus-value-mobile="55" data-speed="1000">

					<div class="container">

						<!-- Start Header Navigation -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse"
								data-target="#navbar-menu">
								<i class="fa fa-bars"></i>
							</button>
							<a class="navbar-brand" href="/">list<span>race</span></a>

						</div>
						<!--/.navbar-header-->
						<!-- End Header Navigation -->

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse menu-ui-design" id="navbar-menu">
							<ul class="nav navbar-nav navbar-right" data-in="fadeInDown" data-out="fadeOutUp">
								<li class="scroll active"><a href="javascript:;">首页</a></li>
								<li class="scroll"><a @click="toLongIndex">我要长租</a></li>
								<li class="scroll">
									<a href="javascript:;" @mouseenter="displayQQ" @mouseleave="hiddenQQ">客服热线</a>
									<span id="displayQQ" class="QQ-area">400-022-5757</span>
								</li>
								<li class="scroll" style="display:none;" id="token">
									<a href="javascript:;">
										<img v-bind:src="userImg" alt="user_img"> 
										<span>{{userInfo.username}}</span>
									</a>
								</li>
								<li class="scroll" id="home-account" style="display:none"><a href="javascript:;" @click="toHomeAccount">个人中心</a></li>
								<li class="scroll" id="loginBtn"><a href="javascript:;" @click="toLogin">登录</a></li>
								<li class="scroll" id="registerBtn"><a href="javascript:;" @click="toRegister">注册</a></li>
								<li class="scroll"><a href="javascript:;" @click="toAbout">关于</a></li>
								<li class="scroll" id="logout" style="display:none"><a href="javascript:;" @click="logout">退出登录</a></li>
							</ul>
							<!--/.nav -->
						</div><!-- /.navbar-collapse -->
					</div>
					<!--/.container-->
				</nav>
				<!--/nav-->
				<!-- End Navigation -->
			</div>
			<!--/.header-area-->
			<div class="clearfix"></div>
		</section>


		<!--welcome-hero start -->
		<section id="home" class="welcome-hero">
			<div class="container">
				<div class="welcome-hero-serch-box">
					<div class="welcome-hero-form">
						<div class="single-welcome-hero-form1" style="width:290px">
							<h4>地点</h4>
							<input type="text" v-model="searchForm.searchAddress" placeholder="请输入房源地址" />
						</div>
					
						<div class="single-welcome-hero-form1" style="width:380px">
							<h4>房源名称</h4>
							<input type="text" v-model="searchForm.searchName" placeholder="请输入房源名称" />
						</div>

						<div class="single-welcome-hero-form3">
							<h4>房东</h4>
							<el-select clearable  v-model="searchForm.landlordId" style="width:100%" placeholder="-请选择-">
								<el-option v-for="item in landlords" :key="item.id" :label="item.username" :value="item.id">
								</el-option>
							</el-select>
						</div>
					</div>
					<div class="welcome-hero-serch">
						<button class="welcome-hero-btn" @click="loadData">
							搜索民宿 <i data-feather="search"></i>
						</button>
					</div>
				</div>
			</div>
		</section>

		<!--explore start -->
		<section id="explore" class="explore">
			<div class="container">
				<div class="explore-content">
					<div class="row">
						<div class="col-md-4 col-sm-6" v-for="(item,index) in list" v-on:click="showDetail(item.id)">
							<div class="single-explore-item">
								<div class="single-explore-img">
									<img class="explore-img" v-bind:src="'http://localhost:8087'+item.photo" alt="explore image">
									<b class="moy-b">
										<p>
											<font>￥</font>{{item.price}}
										</p>
									</b>
									<a>
										<b class="btn-img">
											<img v-bind:src="'http://localhost:8087'+item.userPhoto" class="lazy landlazy"
												heigth="46" alt="房东头像" style="display: inline;" width="46">
										</b>
									</a>

								</div>
								<div class="single-explore-txt bg-theme-1">
									<h2><a href="#">{{item.name}}</a></h2>
									<ul class="explore-rating-price">
										<li><span class="explore-rating">INFO</span></li>
										<li>{{item.bedRoomNum}}居·</li>
										<li>{{item.toiletNum}}卫·</li>
										<li>可住{{item.liveNum}}人</li>
									</ul>
									<div class="room_intro_p">
										<span class="biaoqian_style"
											style="background: rgba(255,205,76,0.25);color: #FA9A00;">速订</span>
										<span v-if="item.isRealPhoto==1" class="biaoqian_style" style="background:#f4f4f4;">实拍</span>
										<span v-if="item.isCooking==1" class="biaoqian_style" style="background:#f4f4f4;"> 可做饭</span>
										<span v-if="item.isParkFree==1" class="biaoqian_style" style="background:#f4f4f4;">免费停车</span>
										<span v-if="item.isProject==1" class="biaoqian_style" style="background:#f4f4f4;">有投影</span>
									</div>

								</div>
							</div>
						</div>



					</div>
				</div>
			</div>
			<!--/.container-->

			<!-- pager -->
			<div class="page_block">
				<el-pagination
					background
					@current-change="handleCurrentChange"
					:current-page.sync="currentPage"
					:page-size="pageSize"
					layout="total, prev, pager, next, jumper"
					:total="totalCount"> 
				</el-pagination>
			</div>

		</section>
		
		
		<Footer></Footer>

		
	</div>
</template>

<script>
	import Footer from './Footer.vue';
	export default {
		name: "Index",
		data() {
			return {
				list: [],
				currentPage: 1,  //默认显示第几页
				pageSize:12,  // // 默认每页显示的条数（可修改）
				totalCount:0,  // // 总条数，根据接口获取数据长度(注意：这里不能为空)
				userInfo:{
					id:'',
					username:'',
					photo:''
				},
				searchForm:{
					searchName:'',
					searchAddress:'',
					landlordId:''
				},
				value1:'',
				value2:'',
				isRouterAlive: true,
				landlords:[]
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
			},
			nightCount(){
				return 6;
			}

		},
		components:{
			Footer
		}, 
		mounted() {
			this.getUserInfo();
			
		},
		created:function(){
			this.loadData();
			this.findLandlords();
			
    	},
		methods: {
			 loadData() {
				var _this = this;
				this.$http({
					method: "post",
					changeOrigin: true, // 这个参数代表发送跨域请求
					url: "house/list",
					params: {
						currentPage: this.currentPage,
						pageSize:this.pageSize,
						name:this.searchForm.searchName,
						address:this.searchForm.searchAddress,
						landlordId:this.searchForm.landlordId
					}
				}).then((response) => {
					var data = response.data.pageSize;
					for (var d in data) {
						
					}
					_this.list = data;
					_this.totalCount=response.data.totalCount;
				})

			},
			findLandlords() {
				var that = this;
				this.$http({
					method: "post",
					changeOrigin: true, // 这个参数代表发送跨域请求
					url: "user/list",
					params: {
						roleId: 2,
						currentPage: 1,
						pageSize: 100
					}
				}).then((response) => {
					that.landlords = response.data.pageSize;
				})
			},
			getUserInfo(){
				let userInfo=JSON.parse(sessionStorage.getItem("userInfo"));
				if(userInfo!=undefined){
					this.userInfo = userInfo;
					var oLi=document.getElementById('token');
					oLi.style.display="block";
					var oHA=document.getElementById('home-account');
					oHA.style.display="block";
					var oLO=document.getElementById('logout');
					oLO.style.display="block";
					var oLi2=document.getElementById('loginBtn');
					oLi2.style.display="none";
					var oReg=document.getElementById('registerBtn');
					oReg.style.display="none";
				}
					
			},
			handleCurrentChange(val) {
				this.currentPage=val;
				// 切换页码时，要获取每页显示的条数
				this.loadData()
			},		
			toLogin() {
				this.$router.push({
					name: 'login',
				})

			},
			logout(){
				sessionStorage.clear();
				this.$router.push({
                    name:'login'
                })
			},
			toRegister() {
				this.$router.push({
					name: 'register',
				})
			},
			showDetail(id) {
				const routeUrl=this.$router.resolve({
					path: `/detail/${id}`,
				})
				window.open(routeUrl.href, '_blank');

			},
			displayQQ(){
				var oQq=document.getElementById('displayQQ');
				oQq.style.display="block";
			},
			hiddenQQ(){
				var oQq=document.getElementById('displayQQ');
				oQq.style.display="none";
			},
			toHomeAccount(){
				this.$router.push({
					name: 'homeAccount'
				})
			},
			toLongIndex(){
				this.$router.push({
					name: 'longIndex',
				})
			},
			toAbout(){
				this.$router.push({
					name: 'about',
				})
			}
		},
		
		

	};
	
</script>

<style>
a:focus {
	outline: none;
}
.el-date-editor .el-input__icon.el-range__icon.el-icon-date{
	display:none;
}
</style>
<style scoped>
@import '../assets/css/font-awesome.min.css';
@import '../assets/css/linearicons.css';
@import '../assets/css/animate.css';
@import '../assets/css/flaticon.css';
@import '../assets/css/slick.css';
@import '../assets/css/slick-theme.css';
@import '../assets/css/bootstrap.min.css';
@import '../assets/css/bootsnav.css';
@import '../assets/css/style.css';
@import '../assets/css/responsive.css';
@import '../assets/css/page.css';
.QQ-area{
	display:none;
	position: absolute;
	width: 170px;
	height: 43px;
	background-color: #fff;
	margin-top: -20px;
	margin-left: -30px;
	border-radius: 4px;
	text-align: center;
	padding-top: 10px;
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
.count {
	text-align: center;
	margin-left: 2px;
	margin-right: 5px;
	/* display: inline-block; */
	width: 52px;
	height: 18px;
	line-height: 18px;
	vertical-align: text-bottom;
	background: url("http://staticnew.mayi.com/resourcesWeb/v201807/index_page_new/images/night-count.png") no-repeat center;
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
.hover-list {
	display: none;
	position: absolute;
	right: -32px;
	background: #fff;
	box-shadow: 0 0 12px 0 rgba(0, 0, 0, 0.04), 0 0 4px 0 rgba(0, 0, 0, 0.12);
	line-height: 20px;
	box-sizing: border-box;
	top: 84px;
	width: 130px;
}
.hover-list:before {
	content: '';
	position: absolute;
	top: -7px;
	left: 60px;
	display: inline-block;
	width: 0;
	height: 0;
	border-width: 0 7px 7px;
	border-style: solid;
	border-color:transparent transparent #efefef;
}
.radius-6 {
	border-radius: 6px;
}
.hover-list li {
	padding: 0;
	border-radius: 6px;
	white-space: nowrap;
	margin-top: 10px;
	margin-bottom:15px;
}
.t-left {
	text-align: center;
}
.mb15 {
	margin-bottom: 15px;
}
.hover-list li:first-child a {
	padding-top: 16px;
}
.hover-list li a {
	display: inline-block;
	padding: 6px 20px;
	margin: 0;
	text-decoration: none;
}
.mb15 .c_gray_dark {
	color: #484848;
}
.mb15 .f16 {
	font-size: 16px;
}	
a{
	text-decoration: none;
	cursor: pointer;
	outline: none;
	color: #322c27;
		font-family: "PingFang SC", "Hiragino Sans GB", Arial, Verdana, Tahoma, "微软雅黑" !important;
	font-size:14px;
}
.c_gray_dark {
	color: #484848;
}
.hover-list hr {
	height: 1px;
	background: #ebebeb;
	border: none;
	margin-top:0px;
}
.fd-nav.slidedown{
	margin-bottom:15px;
}
.fd-nav.slidedown:before {
	content: '';
	display: inline-block;
	width: 15px;
	height: 25px;
	background: url(../assets/images/icons/public_header_icon.png) no-repeat;
	background-position: -42px -106px;
	margin-right: 6px;
	vertical-align: middle;
	position: absolute;
	right: 10px;
	top: 160px;
	transform: rotate(-90deg);
	-ms-transform: rotate(-90deg);
	-moz-transform: rotate(-90deg);
	-webkit-transform: rotate(-90deg);
	-o-transform: rotate(-90deg);
}

.fd-nav:before {
	content: '';
	display: inline-block;
	width: 15px;
	height: 25px;
	background: url(../assets/images/icons/public_header_icon.png) no-repeat;
	background-position: -42px -106px;
	margin-right: 6px;
	vertical-align: middle;
	position: absolute;
	right: 10px;
	top: -1px;
	transform: rotate(90deg);
	-ms-transform: rotate(90deg);
	-moz-transform: rotate(90deg);
	-webkit-transform: rotate(90deg);
	-o-transform: rotate(90deg);
}
.w100 {
	width: 100%;
}
.fw600{
	font-weight:600;
}
.f16{
	font-size: 16px;
}
.login-out {
	box-sizing: border-box;
	width: 90px;
	height: 33px;
	line-height: 33px;
	color: #ff545a;
	border: 1px solid #ff545a;
	border-radius: 100px;
	text-align: center;
	font-size: 14px;
	margin: 5px auto 16px;
}
.hover-list li a:hover{
	background-color:#f5f5f5;
}
.g-footer {
	border-top: 1px solid #edeff1;
}
.explore-img{
	width: 360px;
	height: 240px;
}
.el-date-editor--daterange.el-input__inner{
	width: 280px;
}
.el-input__inner{
	border:none;
}
.el-date-editor .el-input__icon.el-range__icon.el-icon-date{
	display:none;
}
</style>

