<template>
    <div>
        <div class="accountMt save_parent clearfix">
            <!--我的收藏页面开始-->
            <div id="save_parent">
                <!--如果有收藏-->
                <div v-if="totalCount>=1" class="collection-box" v-for="(item,index) in tableData">
                    <div class="order-info">
                        <div class="fl">
                            <span>房源编号：{{item.houseSn}}</span>
                        </div>									
                    </div>
                    <div class="order-con clearfix">
                        <div class="collection-img">
                            <a><img style="width:160px;height:111px;" :src="'http://localhost:8087'+item.photo" alt=""></a>
                        </div>
                        <div class="collection-title">
                            <h2><a @click="toHouseDetail(item.longRentHouseId)">{{item.name}}</a></h2>
                            <p class="money mgtp10" style="margin-top:8px">房源价格：￥{{item.price}}/月</p>
                            <p class="adress">地址：{{item.address}}</p>
                        </div>
                        <div class="order-handle" style="overflow:visible;position:relative;float:right">
                            <p class="textcenter" style="line-height:111px;">
                                <a class="blue" @click="deleteCollection(item.id)">移除</a>
                            </p>
                        </div>
                    </div>
                </div>
    
                <!--如果没有收藏页面-->
                <div v-if="totalCount==0" id="no_save">
                    您还没有收藏
                </div>
            </div>
            <!--我的收藏页面结束-->		
        </div>
        <!-- pager -->
        <div class="page_block">
            <el-pagination background @current-change="handleCurrentChange" :current-page.sync="currentPage"
                :page-size="pageSize" layout="total, prev, pager, next, jumper" :total="totalCount">
            </el-pagination>
        </div>
    </div>
</template>
<style scoped src="../../../assets/css/home-account.css"></style>
<style scoped>
.centerCon {
    width: 790px;
    float: right;
}
.centerCon ul{
    margin-bottom:0px;
}
.centerTab {
    height: 39px;
    border-bottom: #999 solid 1px;
}
.centerTab li.active {
    height: 39px;
    font-weight: bold;
}
.centerTab li {
    height: 38px;
    line-height: 38px;
    text-align: center;
    border: #999 solid 1px;
    border-bottom: none;
    cursor: pointer;
    float: left;
    width: 98px;
    margin-right: 10px;
    background: #fff;
}
.accountMt {
    border: #999 solid 1px;
    border-top: none;
}
#save_parent {
    display: block;
    padding:20px;
}
#no_save {
    text-align: center;
    font: 16px/120px '微软雅黑';
    color: #999;
}
#no_save {
    display: block;
}
.collection-img{
    overflow: hidden;
    float: left;
}
.collection-title{
    padding: 0px 3px 9px 9px;
    margin-left: 10px;
    height: 111px;
    overflow: hidden;
    float: left;
    width:460px;
}
.collection-title a{
    color:#458fce;
}
.collection-box{
    margin-top:11px;
    border-top: #e6e6e6 solid 1px;
    border: #e6e6e5 solid 1px;
}
</style>
<script>
    export default {
        name:"MyCollection",
        data(){
            return{
                tableData:[],
                currentPage:1,
                pageSize:10,
                totalCount:0,
                userInfo: {
                    id: ''
                }
            }
        },
        created:function(){
            this.loadData();
        },
        methods:{
            loadData(){
                var that = this;
                this.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
                this.$http({
                    method: "post",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "long_rent_collection/list",
                    params: {
                        userId:that.userInfo.id,
                        currentPage: that.currentPage,
                        pageSize: that.pageSize
                    }
                }).then((response) => {
                    that.tableData = response.data.pageSize;
                    that.totalCount = response.data.totalCount;
                    for (var i = 0; i <= that.tableData.length; i++) {
                        if (that.tableData[i] != undefined) {
                            var itemId = that.tableData[i].longRentHouseId;
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
                            that.tableData[i].houseSn = itemSn;
                        }
                    }
                })
            },
            handleCurrentChange(val) {
				console.log(`当前页: ${val}`);
				//改变默认的页数
				this.currentPage=val;
				// 切换页码时，要获取每页显示的条数
				this.loadData()
            },
            deleteCollection(id){
                var that=this;
                this.$http({
                    method: "post",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "long_rent_collection/delete",
                    params:{
                        id:id
                    }
                }).then((response) => {
                    that.loadData();
                })
                
            },
            toHouseDetail(id){
                this.$router.push({
                    path:`/long-rent-detail/${id}`
                })
            }
        }
    }
</script>