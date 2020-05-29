<template>
    <div>
        <div class="accountMt clearfix">
            <div class="MtList pd00" style="display: block;">
                <div class="order-box"  v-for="(item,index) in list">
                    <div class="order-info">
                        <div class="fl">
                            <span>订单号：{{item.sn}}</span>
                            <span>下单时间：{{item.createTime}}</span>
                        </div>	
                        <div class="order-detail">
                            <a @click="toOrderDetail(item.id)">订单详情</a>
                        </div>										
                    </div>
                    <div class="order-con clearfix">
                        <div class="order-img">
                            <img :src="'http://localhost:8087'+item.photo" alt="房源图片">
                        </div>
                        <div class="order-title">
                            <h2><a href="javascript:;" @click="toHouseDetail(item.houseId)">{{item.name}}</a></h2>
                            <p class="adress">地址：{{item.address}}</p>
                        </div>
                        <div class="order-book">
                            <p class="book">入住时间：<br>{{item.checkinDay}}</p>
                            <p class="book">退房时间：<br>{{item.checkoutDay}}</p>
                        </div>
                        <div class="order-money">	
                            <p class="money mgtp10">订单总额：<br/>{{item.totalPrice}}元</p>
                        </div>
                        <div class="order-feedback">
                            <p class="feedbackp">订单状态</p>
                            <p v-if="item.status==1" class="feedbackp mgtp30"><span class="feedback">已支付</span></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>         
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
    </div>
</template>
<style scoped src="../../../assets/css/home-account.css"></style>
<script>
    export default {
        name:"OrderManage",
        data(){
            return{
                list:[],
                currentPage: 1,  //默认显示第几页
				pageSize:10,  // // 默认每页显示的条数（可修改）
				totalCount:0,  // // 总条数，根据接口获取数据长度(注意：这里不能为空)
                activeIndex:2
            }
        },
        created:function(){
			this.loadData();
    	},
        methods:{
            loadData() {
				var that = this;
				this.$http({
					method: "post",
					changeOrigin: true, // 这个参数代表发送跨域请求
					url: "book_order/list",
					params: {
                        status:1,
						currentPage: this.currentPage,
                        pageSize:this.pageSize,
                        userId: JSON.parse(sessionStorage.getItem("userInfo")).id
					}
				}).then((response) => {
					var data = response.data.pageSize;
					for (var d of data) {
						d.check = false;
						d.num = 0;
					}
                    that.list = data;
                    that.totalCount=response.data.totalCount;
				})

            },
            handleCurrentChange(val) {
				//改变默认的页数
				this.currentPage=val;
				// 切换页码时，要获取每页显示的条数
				this.loadData()
            },
            toHouseDetail(id){
                this.$router.push({
                    path:`/detail/${id}`
                })
            },
            toOrderDetail(id) {
                this.$router.push({
                    path: '/order_detail',
                    query: {
                        id: id
                    }
                })
            },
        }
    
    }
</script>