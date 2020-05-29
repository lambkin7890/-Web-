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
								<li class="scroll" id="home-account" style="display: none;"><a href="javascript:;"
										@click="toHomeAccount">个人中心</a></li>
								<li class="scroll" id="loginBtn"><a href="javascript:;" @click="toLogin">登录</a></li>
								<li class="scroll" id="registerBtn"><a href="javascript:;" @click="toRegister">注册</a>
								</li>
								<li class="scroll"><a href="javascript:;" @click="toAbout">关于</a></li>
								<li class="scroll" id="logout" style="display: none;"><a href="javascript:;"
										@click="logout">退出登录</a></li>
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


		<div class="maincontent">
			<div class="list-content" id="list-content">
				<!-- 搜索表单 -->
				<div class="search-house">
					<el-form :inline="true" :model="formInline" class="demo-form-inline">
						<el-form-item label="地址">
							<el-input v-model="formInline.address" placeholder="请输入房源地址"></el-input>
						</el-form-item>
						<el-form-item label="房源名称">
							<el-input v-model="formInline.name" placeholder="请输入房源名称"></el-input>
						</el-form-item>
						<el-form-item label="房东">
							<el-select clearable  v-model="formInline.landlordId" style="width:100%" placeholder="-请选择-">
								<el-option v-for="item in landlords" :key="item.id" :label="item.username" :value="item.id">
								</el-option>
							</el-select>
						</el-form-item>
						<el-form-item>
							<el-button type="danger" @click="loadData">搜索房源</el-button>
						</el-form-item>
					</el-form>
				</div>
				<!--排序-->
				<div class="zu-sort">
					<!-- <span class="tit">为您找到<em>新乡</em>附近租房</span> -->
					<div class="sort-cond">
						<span>排序 ：</span>
						<a :class="{light:lightActive==0}" href="javascript:;" @click="defaultLoad">默认</a>
						<a :class="{light:lightActive==1}" href="javascript:;" @click="priceUpchange">租金<i
								class="icon icon-arrup"></i></a>
						<a :class="{light:lightActive==2}" href="javascript:;" @click="createTimeDownchange">最新<i
								class="icon icon-arrdown"></i></a>
					</div>
				</div>
				<!--区域板块租房房源列表页-->
				<div class="zu-itemmod" v-for="(item,index) in list" v-on:click="showDetail(item.id)">
					<a class="img" data-sign="true" target="_blank" hidefocus="true">
						<img v-if="item.photo" class="thumbnail" :src="'http://localhost:8087'+item.photo" width="180"
							height="135" data-loaded="true">
					</a>
					<div class="zu-info">
						<h3>
							<a target="_blank" _soj="Filter_1&amp;hfilter=filterlist">
								<b class="strongbox">{{item.name}}</b>
							</a>
						</h3>
						<p class="details-item tag">
							<b class="strongbox" style="font-weight: normal;">{{item.bedRoomNum}}</b>室
							<b class="strongbox" style="font-weight: normal;">{{item.sittingRoomNum}}</b>厅<span>|</span>
							<b class="strongbox"
								style="font-weight: normal;">{{item.area}}</b>平米<span>|</span>{{item.floorRange}}(共{{item.totalFloor}}层)
							<i class="jjr-icon">房东：</i>{{item.landlordRealName}}
						</p>
						<address class="details-item">
							地址：{{item.address}}
						</address>
						<p class="details-item bot-tag">
							<span v-if="item.isEntire==1" class="cls-1">整租</span>
							<span v-if="item.isSouth==1" class="cls-2">朝南</span>
							<span v-if="item.isElevator==1" class="cls-3">有电梯</span>
						</p>
					</div>
					<div class="zu-side">
						<p><strong><b class="strongbox">{{item.price}}</b></strong>元/月</p>
					</div>
				</div>
				<!-- pager -->
				<div class="page_block">
					<el-pagination background @current-change="handleCurrentChange" :current-page.sync="currentPage"
						:page-size="pageSize" layout="total, prev, pager, next, jumper" :total="totalCount">
					</el-pagination>
				</div>
			</div>
		</div>

		<Footer></Footer>
	</div>
</template>

<style scoped src="../assets/css/long-rent/long-index.css"></style>
<style scoped>
	.search-house{
		width: 945px;
		height: 80px;
		background-color: #fff;
		text-align: center;
		padding-top: 20px;
		border-radius: 4px;
	}
	.QQ-area {
		display: none;
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

	#token {
		margin-top: -7px;
		margin-bottom: -9px
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

	.hover-list {
		display: none;
		position: absolute;
		right: -32px;
		background: #fff;
		box-shadow: 0 0 12px 0 rgba(0, 0, 0, 0.04), 0 0 4px 0 rgba(0, 0, 0, 0.12);
		line-height: 20px;
		box-sizing: border-box;
		top: 84px;
		left: -48px;
		width: 199px;
	}

	.hover-list:before {
		content: '';
		position: absolute;
		top: -7px;
		left: 90px;
		display: inline-block;
		width: 0;
		height: 0;
		border-width: 0 7px 7px;
		border-style: solid;
		border-color: transparent transparent #dcdcdc;
	}

	.radius-6 {
		border-radius: 6px;
	}

	.hover-list li {
		padding: 0;
		border-radius: 6px;
		white-space: nowrap;
		margin: 0;
	}

	.t-left {
		text-align: left;
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

	a {
		text-decoration: none;
		cursor: pointer;
		outline: none;
		color: #322c27;
		font-family: "PingFang SC", "Hiragino Sans GB", Arial, Verdana, Tahoma, "微软雅黑" !important;
		font-size: 14px;
	}

	.c_gray_dark {
		color: #484848;
	}

	.hover-list hr {
		height: 1px;
		background: #ebebeb;
		border: none;
		margin-top: 0px;
	}

	.fd-nav.slidedown {
		margin-bottom: 15px;
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

	.fw600 {
		font-weight: 600;
	}

	.f16 {
		font-size: 16px;
	}

	.login-out {
		box-sizing: border-box;
		width: 160px;
		height: 36px;
		line-height: 36px;
		color: #ff545a;
		border: 1px solid #ff545a;
		border-radius: 100px;
		text-align: center;
		font-size: 14px;
		margin: 5px auto 16px;
	}

	.hover-list li a:hover {
		background-color: #f5f5f5;
	}

	.g-footer {
		border-top: 1px solid #edeff1;
	}
</style>
<script>
	import Footer from "@/components/Footer.vue";
	export default {
		name: "LongIndex",
		data() {
			return {
				list: [],
				currentPage: 1,  //默认显示第几页
				pageSize: 12,  // // 默认每页显示的条数（可修改）
				totalCount: 0,  // // 总条数，根据接口获取数据长度(注意：这里不能为空)
				userInfo: {
					username: '',
					photo: ''
				},
				lightActive: 0,
				formInline: {
					address: '',
					name: '',
					landlordId:''
				},
				landlords:[]
			};
		},
		components: {
			Footer
		},
		mounted() {
			this.getUserInfo();
		},
		created: function () {
			this.loadData();
			this.findLandlords();
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
		methods: {
			loadData() {
				var _this = this;
				this.$http({
					method: "post",
					changeOrigin: true, // 这个参数代表发送跨域请求
					url: "long_rent_house/list",
					params: {
						currentPage: this.currentPage,
						pageSize: this.pageSize,
						name: this.formInline.name,
						address: this.formInline.address,
						landlordId:this.formInline.landlordId
					}
				}).then((response) => {
					var data = response.data.pageSize;
					for (var d of data) {
						d.check = false;
						d.num = 0;
					}
					_this.list = data;
					_this.totalCount = response.data.totalCount;
					if (_this.lightActive == 1) {
						_this.priceUpchange();
					}
					if (_this.lightActive == 2) {
						_this.createTimeDownchange();
					}
				})

			},
			handleCurrentChange(val) {
				console.log(`当前页: ${val}`);
				//改变默认的页数
				this.currentPage = val;
				// 切换页码时，要获取每页显示的条数
				this.loadData();

			},
			showDetail(id) {
				this.$router.push({
					path: `/long-rent-detail/${id}`
				})

			},
			getUserInfo() {
				let userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
				if (userInfo != undefined) {
					this.userInfo = userInfo;
					var oLi = document.getElementById('token');
					oLi.style.display = "block";
					var oHA = document.getElementById('home-account');
					oHA.style.display = "block";
					var oLO = document.getElementById('logout');
					oLO.style.display = "block";
					var oLi2 = document.getElementById('loginBtn');
					oLi2.style.display = "none";
					var oReg = document.getElementById('registerBtn');
					oReg.style.display = "none";
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
			toHomeAccount() {
				this.$router.push({
					name: 'homeAccount',
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
			},
			defaultLoad(){
				this.lightActive = 0;
				this.loadData();
			},
			priceUpchange() {
				this.lightActive = 1;
				this.list.sort((a, b) => {
					//排序基于的数据
					return a.price - b.price;
				})
			},
			createTimeDownchange() {
				this.lightActive = 2;
				this.list.sort((a, b) => {
					//排序基于的数据
					var sArr = b.createTime.split("-");
					var eArr = a.createTime.split("-");
					var sRDate = new Date(sArr[0], sArr[1], sArr[2]);
					var eRDate = new Date(eArr[0], eArr[1], eArr[2]);
					return sRDate - eRDate;
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
		}
	};
</script>