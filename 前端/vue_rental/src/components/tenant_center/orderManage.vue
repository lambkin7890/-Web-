<template>
    <div class="centerCon">
        <ul class="centerTab">
            <li id="paying" @click="activeIndex=0" :class="{active:activeIndex===0}">待付款</li>
            <li id="payed"  @click="activeIndex=1" :class="{active:activeIndex===1}">已支付</li>
            <li id="all" @click="activeIndex=2" :class="{active:activeIndex===2}">全部订单</li>
        </ul>
        
        <NotPay v-show="activeIndex===0"></NotPay>
        <Payed v-show="activeIndex===1"></Payed>
        <AllOrders v-show="activeIndex===2"></AllOrders>
    </div>
</template>
<style scoped src="../../assets/css/home-account.css"></style>
<script>
    import NotPay from "./order-manage/not-pay.vue";
    import Payed from "./order-manage/payed.vue";
    import AllOrders from "./order-manage/all-orders.vue";
    export default {
        name:"OrderManage",
        data(){
            return{
                list:[],
                searchForm:{
                    status:null
                },
                currentPage: 1,  //默认显示第几页
				pageSize:3,  // // 默认每页显示的条数（可修改）
				totalCount:0,  // // 总条数，根据接口获取数据长度(注意：这里不能为空)
                activeIndex:2
            }
        },
        components:{
            NotPay,
            Payed,
            AllOrders
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
						currentPage: this.currentPage,
						pageSize:this.pageSize,
						status:this.searchForm.status
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
        }
    
    }
</script>