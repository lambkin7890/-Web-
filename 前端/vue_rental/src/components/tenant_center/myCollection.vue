<template>
    <div class="centerCon">
        <ul class="centerTab">
            <li @click="activeIndex=0" :class="{active:activeIndex===0}">短租</li>
            <li @click="activeIndex=1" :class="{active:activeIndex===1}">长租</li>
        </ul>
        <CollectionShort v-show="activeIndex===0"></CollectionShort>
        <CollectionLong v-show="activeIndex===1"></CollectionLong>
    </div>
</template>
<style scoped src="../../assets/css/home-account.css"></style>
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

</style>
<script>
    import CollectionShort from "./collection-components/collection-short.vue";
    import CollectionLong from "./collection-components/collection-long.vue";
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
                },
                activeIndex:0
            }
        },
        components:{
            CollectionShort,
            CollectionLong
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
                    url: "house_collection/list",
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
                            var itemId = that.tableData[i].id;
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
                            that.tableData[i].sn = itemSn;
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
                    url: "house_collection/delete",
                    params:{
                        id:id
                    }
                }).then((response) => {
                    that.loadData();
                })
                
            },
        }
    }
</script>