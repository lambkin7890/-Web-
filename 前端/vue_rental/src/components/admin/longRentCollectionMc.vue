<template>
    <div class="main">
        <el-table :data="tableData" style="width: 100%;">
            <!--prop绑定相应实体的字段-->
            <el-table-column type="index" width="70" label="序号"></el-table-column>
            <el-table-column prop="tenantName" width="100" label="用户名"></el-table-column>
            <el-table-column prop="sn" width="120" label="房源编号"></el-table-column>
            <el-table-column label="操作" width="100">
                <template slot-scope="scope">
                    <el-button type="danger" icon="el-icon-delete" @click="removeItem(scope.row.id)" circle></el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- pager -->
        <div class="pager_block">
            <el-pagination background @current-change="handleCurrentChange" :current-page.sync="currentPage"
                :page-size="pageSize" layout="total, prev, pager, next, jumper" :total="totalCount">
            </el-pagination>
        </div>

    </div>
</template>


<script>
    export default {
        data() {
            return {
                tableData: [],
                currentPage: 1,  //默认显示第几页
                pageSize: 10,  // // 默认每页显示的条数（可修改）
                totalCount: 0,  // // 总条数，根据接口获取数据长度(注意：这里不能为空)
                userInfo: {
                    id: '',
                    roleId: ''
                }
            };
        },

        mounted() {
            this.getTableData();
        },
        methods: {
            getTableData() {
                var that = this;
                this.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
                this.$http({
                    method: "post",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "long_rent_collection/list",
                    params: {
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
                this.currentPage = val;
                // 切换页码时，要获取每页显示的条数
                this.getTableData()
            },
            removeItem(id){
                var that=this;
                this.$http({
                    method: "post",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "long_rent_collection/delete",
                    params:{
                        id:id
                    }
                }).then((response) => {
                    if (response.data.type == 'error') {
                        this.$message({
                            message: response.data.msg,
                            type: 'error'
                        });
                    } else {
                        this.$message({
                            message: response.data.msg,
                            type: 'success'
                        });
                        that.getTableData();
                    }
                })
                
            }
        }
    };
</script>

<style scoped>
    .el-table .el-table-column td .cell {
        text-align: center;
    }

    .el-container {
        margin: 0;
        padding: 0;
        width: 100%;
        height: 100%;
    }

    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200px;
        min-height: 400px;
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

    .el-button.is-round {
        margin-left: 20px;
    }

    .el-main {
        text-align: center;
        padding: 0px;
        overflow-x: visible;
    }

    .el-form-item__content {
        display: flex;
    }

    a:link,
    a:visited {
        text-decoration: none;
    }

    /* 被选择的链接 */
</style>