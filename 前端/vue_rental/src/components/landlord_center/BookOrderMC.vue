<template>
    <div class="main">
        <el-table :data="tableData" style="width: 100%">
            <!--prop绑定相应实体的字段-->
            <el-table-column type="index" width="50" label="序号"></el-table-column>
            <el-table-column prop="sn" width="150" label="订单号"></el-table-column>
            <el-table-column prop="houseSn" width="120" label="房源编号"></el-table-column>
            <el-table-column prop="checkinDay" width="100" label="入住日期"></el-table-column>
            <el-table-column prop="checkoutDay" width="100" label="退房日期"></el-table-column>
            <el-table-column width="80" label="预定天数">
                <template slot-scope="scope">
                    <span>{{scope.row.nightCount}}晚</span>
                </template>
            </el-table-column>
            <el-table-column width="100" label="订单总额">
                <template slot-scope="scope">
                    <span>{{scope.row.totalPrice}}元</span>
                </template>
            </el-table-column>
            <el-table-column prop="tenantName" width="100" label="预订人"></el-table-column>
            <el-table-column prop="mobile" width="120" label="联系手机号"></el-table-column>
            <el-table-column width="80" label="入住人数">
                <template slot-scope="scope">
                    <span>{{scope.row.checkinNum}}人</span>
                </template>
            </el-table-column>
            <el-table-column prop="checkinName" width="100" label="入住人姓名"></el-table-column>
            <el-table-column prop="checkinIdCard" width="200" label="入住人身份证号"></el-table-column>
            <el-table-column width="100" label="订单状态">
                <template slot-scope="scope">
                    <span v-show="scope.row.status==0">待付款</span>
                    <span v-show="scope.row.status==1">已支付</span>
                </template>
            </el-table-column>
            <el-table-column prop="createTime" width="150" label="下单时间"></el-table-column>
            <el-table-column fixed="right" width="120" label="操作">
                <template slot-scope="scope">
                    <el-button type="primary" @click="handleEditClick(scope.row.id)" icon="el-icon-edit" circle>
                    </el-button>
                    <el-button type="danger" icon="el-icon-delete" @click="removeItem(scope.row.id)" circle>
                    </el-button>
                </template>
            </el-table-column>
        </el-table>



        <!-- pager -->
        <div class="pager_block">
            <el-pagination background @current-change="handleCurrentChange" :current-page.sync="currentPage"
                :page-size="pageSize" layout="total, prev, pager, next, jumper" :total="totalCount">
            </el-pagination>
        </div>


        <!-- 编辑订单模态框 -->
        <el-dialog title="修改订单信息" :visible.sync="dialogEditFormVisible" width="50%" :before-close="handleClose">
            <span>
                <el-form ref="editForm" :rules="editFormRules" :model="editForm" label-width="120px">
                    <el-form-item label="订单编号">
                        {{editForm.sn}}
                    </el-form-item>
                    <el-form-item prop="houseId" label="房源编号">
                        <el-select @change="updatePriceByHouse" v-model="editForm.houseId" style="width:100%" placeholder="-请选择-">
                            <el-option v-for="item in houses" :key="item.id" :label="item.sn" :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="住宿日期">
                        <el-date-picker class="admin-date" @change="setDateRange" v-model="dateRange" type="daterange"
                            range-separator="至" start-placeholder="入住日期" end-placeholder="退房日期"
                            value-format="yyyy-MM-dd" :picker-options="pickerOptions">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item prop="nightCount" label="预定天数">
                        {{nightCount}}晚
                    </el-form-item>
                    <el-form-item prop="totalPrice" label="订单总额">
                        <el-input v-model.number="editForm.totalPrice"></el-input>
                    </el-form-item>
                    <el-form-item label="预订人">
                        <el-select v-model="editForm.userId" style="width:100%" placeholder="-请选择-">
                            <el-option v-for="item in tenants" :key="item.id" :label="item.username" :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="mobile" label="联系手机号">
                        <el-input v-model.number="editForm.mobile" maxlength=11 show-word-limit></el-input>
                    </el-form-item>
                    <el-form-item label="入住人数">
                        <el-select v-model="editForm.checkinNum" style="width:100%" placeholder="-请选择-">
                            <el-option value="1"></el-option>
                            <el-option value="2"></el-option>
                            <el-option value="3"></el-option>
                            <el-option value="4"></el-option>
                            <el-option value="5"></el-option>
                            <el-option value="6"></el-option>
                            <el-option value="7"></el-option>
                            <el-option value="8"></el-option>
                            <el-option value="9"></el-option>
                            <el-option value="10"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="checkinName" label="入住人姓名">
                        <el-input v-model="editForm.checkinName" maxlength="4" show-word-limit></el-input>
                    </el-form-item>
                    <el-form-item prop="checkinIdCard" label="入住人身份证号">
                        <el-input v-model="editForm.checkinIdCard" maxlength="16" show-word-limit></el-input>
                    </el-form-item>
                    <el-form-item prop="status" label="订单状态">
                        <el-select v-model="editForm.status" style="width:100%" placeholder="-请选择-">
                            <el-option label="待付款" :value="0"></el-option>
                            <el-option label="已支付" :value="1"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="createTime" label="下单时间">
                        {{editForm.createTime}}
                    </el-form-item>
                </el-form>
            </span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogEditFormVisible= false">取 消</el-button>
                <el-button type="primary" @click="editItem">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>


<script>
    export default {
        data() {
            var checkPrice = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('价格不能为空'));
                }
                setTimeout(() => {
                    if (!Number.isInteger(value)) {
                        callback(new Error('请输入数字值'));
                    } else {
                        if (value < 1 || value >= 100000) {
                            callback(new Error('请输入合理范围内的价格'));
                        } else {
                            callback();
                        }
                    }
                }, 1000);
            };
            var checkMobile = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('联系手机号不能为空'));
                }
                setTimeout(() => {
                    if (!Number.isInteger(value)) {
                        callback(new Error('请输入数字值'));
                    } else {
                        callback();
                    }
                }, 1000);
            };
            return {
                tableData: [],
                dialogFormVisible: false,
                dialogEditFormVisible: false,
                editForm: {
                    sn: '',
                    houseId: '',
                    checkinDay: '',
                    checkoutDay: '',
                    userId: '',
                    mobile: '',
                    checkinNum: '',
                    checkinName: '',
                    checkinIdCard: '',
                    status: ''
                },
                file: '',
                currentPage: 1,
                pageSize: 10,
                totalCount: 0,
                userInfo: {
                    id: '',
                    roleId: ''
                },
                dateRange: [],
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() < Date.now() - 24 * 60 * 60 * 1000;
                    },
                },
                houses: [],
                tenants: [],
                editFormRules: {
                    mobile: [
                        { validator: checkMobile, required: true, trigger: 'blur' }
                    ],
                    totalPrice: [
                        { validator: checkPrice, required: true, trigger: 'blur' }
                    ],
                    checkinName: [
                        { required: true, message: '必须填写其中一位入住人姓名', trigger: 'blur' }
                    ],
                    checkinIdCard: [
                        { required: true, message: '必须填写其中一位入住人身份证号', trigger: 'blur' }
                    ],
                },
            };
        },
        computed: {
            nightCount() {
                var sArr = this.editForm.checkoutDay.split("-");
                var eArr = this.editForm.checkinDay.split("-");
                var sRDate = new Date(sArr[0], sArr[1], sArr[2]);
                var eRDate = new Date(eArr[0], eArr[1], eArr[2]);
                var nightCount = (sRDate - eRDate) / (24 * 60 * 60 * 1000);
                this.editForm.nightCount=nightCount;
                this.editForm.totalPrice = this.editForm.price * nightCount;
                return nightCount;
            }

        },
        mounted() {
            this.getTableData();
        },
        created() {
            this.findHouses();
            this.findTenants();
        },
        methods: {
            getTableData() {
                var that = this;
                this.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
                this.$http({
                    method: "post",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "book_order/list",
                    params: {
                        currentPage: that.currentPage,
                        pageSize: that.pageSize,
                        houseHolderId: that.userInfo.id
                    }
                }).then((response) => {
                    var result = response.data.pageSize;
                    that.tableData = result;
                    that.totalCount = response.data.totalCount;
                    for (var i = 0; i <= that.tableData.length; i++) {
                        if (that.tableData[i] != undefined) {
                            var itemId = that.tableData[i].houseId;
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
                this.currentPage = val;
                // 切换页码时，要获取每页显示的条数
                this.getTableData();
            },
            handleClose(done) {
                this.$confirm("确认关闭？")
                    .then(_ => {
                        done();
                    })
                    .catch(_ => { });
            },
            handleEditClick(itemId) {
                var that = this;
                this.$http({
                    method: "get",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "book_order/detail",
                    params: {
                        id: itemId
                    }
                }).then((response) => {
                    that.editForm = response.data;
                    that.dateRange[0] = that.editForm.checkinDay;
                    that.dateRange[1] = that.editForm.checkoutDay;
                    that.dialogEditFormVisible = true;
                })
            },
            editItem() {
                let that = this;
                this.$http({
                    method: "post",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "book_order/edit",
                    params: that.editForm
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
                        that.dialogEditFormVisible = false
                    }
                })
            },
            findHouses() {
                var that = this;
                this.$http({
                    method: "post",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "house/list",
                    params: {
                        currentPage: 1,
                        pageSize: 10000
                    }
                }).then((response) => {
                    that.houses = response.data.pageSize;
                    for (var i = 0; i <= that.houses.length; i++) {
                        if (that.houses[i] != undefined) {
                            var itemId = that.houses[i].id;
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
                            that.houses[i].sn = itemSn;
                        }
                    }
                })
            },
            findTenants() {
                var that = this;
                this.$http({
                    method: "post",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "user/list",
                    params: {
                        roleId: 1,
                        currentPage: 1,
                        pageSize: 100
                    }
                }).then((response) => {
                    that.tenants = response.data.pageSize;
                })
            },
            removeItem(id) {
                var that = this;
                this.$confirm("确认删除").then(() => {
                    this.$http({
                        method: "post",
                        changeOrigin: true, // 这个参数代表发送跨域请求
                        url: "book_order/delete",
                        params: {
                            id: id
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
                })
            },
            setDateRange() {
                this.editForm.checkinDay = this.dateRange[0];
                this.editForm.checkoutDay = this.dateRange[1];
            },
            updatePriceByHouse(){
                var houseId=this.editForm.houseId;
                var that=this;
                this.$http({
                    method: "get",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "house/detail",
                    params: {
                        id:houseId
                    }
                }).then((response) => {
                    that.editForm.price=response.data.price;
                })

            }

        }
    };
</script>

<style scoped>
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

    .el-main {
        text-align: center;
        padding: 0px;
        overflow-x: visible;
    }
</style>