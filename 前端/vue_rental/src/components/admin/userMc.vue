<template>
    <div class="main">
        <el-button type="primary" round style="float:left;margin-bottom:20px;" @click="openAddDialog">添加用户</el-button>
        <el-table :data="tableData" style="width: 100%;">
            <!--prop绑定相应实体的字段-->
            <el-table-column type="index" width="50" label="序号"></el-table-column>
            <el-table-column label="用户头像" width="120">
                <template slot-scope="scope">
                    <img v-if="scope.row.photo" :src="'http://localhost:8087'+scope.row.photo" style="width:60px;height:60px;">
                    <img v-if="scope.row.photo==null||scope.row.photo==''" v-bind:src="require('../../assets/images/user-photo/landlord/kodinger.jpg')" alt=""
                    style="width:60px;height:60px;">
                </template>
            </el-table-column>
            <el-table-column prop="username" width="100" label="用户名"></el-table-column>
            <el-table-column prop="password" width="100" show-overflow-tooltip label="密码"></el-table-column> 
            <el-table-column label="用户角色" width="100">
                <template slot-scope="scope">
                    <span v-show="scope.row.roleId==1">房客</span>
                    <span v-show="scope.row.roleId==2">房东</span>
                    <span v-show="scope.row.roleId==3">管理员</span>
                </template>
            </el-table-column>
            <el-table-column prop="nickName" label="昵称" width="150" show-overflow-tooltip></el-table-column>
            <el-table-column label="真实照片" width="120">
                <template slot-scope="scope">
                    <img v-if="scope.row.realPhoto" :src="'http://localhost:8087'+scope.row.realPhoto" style="width:60px;height:60px;">
                    <img v-else v-bind:src="require('../../assets/images/user-photo/landlord/kodinger.jpg')" alt=""
                    style="width:60px;height:60px;">
                </template>
            </el-table-column>
            <el-table-column prop="realName" label="真实姓名" width="80"></el-table-column>
            <el-table-column prop="idCard" label="身份证号" width="200" show-overflow-tooltip></el-table-column>
            <el-table-column label="性别" width="80">
                <template slot-scope="scope">
                    <span v-show="scope.row.sex==0">未知</span>
                    <span v-show="scope.row.sex==1">男</span>
                    <span v-show="scope.row.sex==2">女</span>
                </template>
            </el-table-column>
            <el-table-column prop="email" label="邮箱" show-overflow-tooltip width="160"></el-table-column>
            <el-table-column prop="mobile" label="手机号码" width="150"></el-table-column>
            <el-table-column fixed="right" label="操作" width="120">
                <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-edit" @click="handleEditClick(scope.row.id)" circle>
                    </el-button>
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

        <!-- 添加用户模态框 -->
        <el-dialog title="添加用户" :visible.sync="dialogFormVisible" width="50%" :before-close="handleClose">
            <span>
                <el-form ref="addUserForm" :rules="rules" :model="addForm" label-width="80px">
                    <el-form-item label="用户头像">
                        <el-upload
                            class="avatar-uploader"
                            action="#"
                            name="photo"
                            :http-request="uploadUserPhoto"
                            :auto-upload="true"
                            :show-file-list="false"
                            :before-upload="beforeAvatarUpload">
                                <img v-if="imageUrl" :src="'http://localhost:8087'+imageUrl" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item prop="username" label="用户名">
                        <el-input v-model.trim="addForm.username" maxlength="10" show-word-limit></el-input>
                    </el-form-item>
                    <el-form-item prop="password" label="密码">
                        <el-input type="password" v-model.number="addForm.password" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item prop="roleId" label="用户角色">
                        <el-select v-model="addForm.roleId" style="width:100%" placeholder="-请选择-">
                            <el-option label="房客" :value="1"></el-option>
                            <el-option label="房东" :value="2"></el-option>
                            <el-option label="管理员" :value="3"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="nickName" label="昵称">
                        <el-input v-model="addForm.nickName" maxlength="10" show-word-limit></el-input>
                    </el-form-item>
                    <el-form-item label="真实照片">
                        <el-upload
                            class="avatar-uploader"
                            action="#"
                            name="photo"
                            :http-request="uploadRealPhoto"
                            :auto-upload="true"
                            :show-file-list="false"
                            :before-upload="beforeAvatarUpload">
                                <img v-if="realPhotoUrl" :src="'http://localhost:8087'+realPhotoUrl" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item prop="realName" label="真实姓名">
                        <el-input v-model="addForm.realName"></el-input>
                    </el-form-item>
                    <el-form-item prop="idCard" label="身份证号">
                        <el-input v-model="addForm.idCard"></el-input>
                    </el-form-item>
                    <el-form-item prop="sex" label="性别">
                        <el-select v-model="addForm.sex" style="width:100%" placeholder="-请选择-">
                            <el-option label="保密" :value="0"></el-option>
                            <el-option label="男" :value="1"></el-option>
                            <el-option label="女" :value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="email" label="邮箱">
                        <el-input v-model="addForm.email"></el-input>
                    </el-form-item>
                    <el-form-item prop="mobile" label="手机号码">
                        <el-input v-model.number="addForm.mobile" maxlength="11" show-word-limit></el-input>
                    </el-form-item>
                </el-form>
            </span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible=false">取 消</el-button>
                <el-button type="primary" @click="addItem">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 修改用户信息 -->
        <el-dialog title="修改用户信息" :visible.sync="dialogEditFormVisible" width="50%" :before-close="handleClose">
            <span>
                <el-form ref="editUserForm" :rules="rules" :model="editForm" label-width="80px">
                    <el-form-item label="用户头像">
                        <el-upload
                            class="avatar-uploader"
                            action="#"
                            name="photo"
                            :http-request="uploadUserPhoto"
                            :auto-upload="true"
                            :show-file-list="false"
                            :before-upload="beforeAvatarUpload">
                                <img v-if="imageUrl" :src="'http://localhost:8087'+imageUrl" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item prop="username" label="用户名">
                        <el-input v-model.trim="editForm.username" maxlength="10" show-word-limit></el-input>
                    </el-form-item>
                    <el-form-item prop="password" label="密码">
                        <el-input type="password" v-model.number="editForm.password" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item prop="roleId" label="用户角色">
                        <el-select v-model="editForm.roleId" style="width:100%" placeholder="-请选择-">
                            <el-option label="房客" :value="1"></el-option>
                            <el-option label="房东" :value="2"></el-option>
                            <el-option label="管理员" :value="3"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="nickName" label="昵称">
                        <el-input v-model="editForm.nickName" maxlength="10" show-word-limit></el-input>
                    </el-form-item>
                    <el-form-item label="真实照片">
                        <el-upload
                            class="avatar-uploader"
                            action="#"
                            name="photo"
                            :http-request="uploadRealPhoto"
                            :auto-upload="true"
                            :show-file-list="false"
                            :before-upload="beforeAvatarUpload">
                                <img v-if="realPhotoUrl" :src="'http://localhost:8087'+realPhotoUrl" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item prop="realName" label="真实姓名">
                        <el-input v-model="editForm.realName"></el-input>
                    </el-form-item>
                    <el-form-item prop="idCard" label="身份证号">
                        <el-input v-model="editForm.idCard"></el-input>
                    </el-form-item>
                    <el-form-item prop="sex" label="性别">
                        <el-select v-model="editForm.sex" style="width:100%" placeholder="-请选择-">
                            <el-option label="保密" :value="0"></el-option>
                            <el-option label="男" :value="1">男</el-option>
                            <el-option label="女" :value="2">女</el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="email" label="邮箱">
                        <el-input v-model="editForm.email"></el-input>
                    </el-form-item>
                    <el-form-item prop="mobile" label="手机号码">
                        <el-input v-model.number="editForm.mobile" maxlength="11" show-word-limit></el-input>
                    </el-form-item>
                </el-form>
            </span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogEditFormVisible=false">取 消</el-button>
                <el-button type="primary" @click="editItem">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>


<script>
    export default {
        data() {
            return {
                tableData: [],
                dialogFormVisible: false,
                dialogEditFormVisible: false,
                addForm: {
                    id:'',
                    username: '',
                    photo: 'logo/kodinger.jpg',
                    password: '',
                    roleId: '',
                    nickName: '',
                    realName: '',
                    idCard: '',
                    sex: '',
                    email: '',
                    mobile: ''
                },
                editForm: {
                    id:'',
                    username: '',
                    photo: 'logo/kodinger.jpg',
                    password: '',
                    roleId: '',
                    nickName: '',
                    realName: '',
                    idCard: '',
                    sex: '',
                    email: '',
                    mobile: ''
                },
                file: '',
                currentPage: 1,  //默认显示第几页
                pageSize: 10,  // // 默认每页显示的条数（可修改）
                totalCount: 0,  // // 总条数，根据接口获取数据长度(注意：这里不能为空)
                userInfo: {
                    id: '',
                    roleId: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ],
                    roleId: [
                        { required: true, message: '请选择用户角色', trigger: 'blur' }
                    ],
                    sex: [
                        { required: true, message: '请选择性别', trigger: 'blur' }
                    ],
                },
                imageUrl: '',
                realPhotoUrl:''
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
                    url: "user/list",
                    params: {
                        currentPage: that.currentPage,
                        pageSize: that.pageSize
                    }
                }).then((response) => {
                    that.tableData = response.data.pageSize;
                    that.totalCount = response.data.totalCount;
                })
                
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                // 切换页码时，要获取每页显示的条数
                this.getTableData()
            },
            handleClose(done) {
                this.$confirm("确认关闭？")
                    .then(_ => {
                        done();
                    }).catch(_ => { });
            },
            openAddDialog() {
                var that = this;
                this.dialogFormVisible = true;
                this.$nextTick(() => {
                    that.$refs.addUserForm.resetFields();
                    that.imageUrl = '';
                    that.realPhotoUrl='';
                })
                
            },
            handleEditClick(itemId) {
                var that = this;
                this.$http({
                    method: "get",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "user/find_by_id",
                    params: {
                        id: itemId
                    }
                }).then((response) => {
                    that.editForm = response.data;
                    that.imageUrl=response.data.photo;
                    that.realPhotoUrl=response.data.realPhoto;
                    that.dialogEditFormVisible = true;
                })
            },
            addItem() {
                var that = this;
                this.addForm.photo=this.imageUrl;
                this.addForm.realPhoto=this.realPhotoUrl;
                this.$refs.addUserForm.validate((valid) => {
                    if (valid) {
                        this.$http({
                            method: "post",
                            changeOrigin: true, // 这个参数代表发送跨域请求
                            url: "user/register",
                            params: that.addForm
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
                                that.dialogFormVisible = false;
                                that.getTableData();
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            editItem() {
                let that = this;
                this.editForm.photo=this.imageUrl;
                this.editForm.realPhoto=this.realPhotoUrl;
                this.$http({
                    method: "post",
                    changeOrigin: true, // 这个参数代表发送跨域请求
                    url: "user/update",
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
                        that.dialogEditFormVisible = false
                        that.getTableData();
                       
                    }
                })
            },
            removeItem(id) {
                var that = this;
                this.$confirm("确认删除").then(() => {
                    this.$http({
                        method: "post",
                        changeOrigin: true, // 这个参数代表发送跨域请求
                        url: "user/delete",
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
            beforeAvatarUpload(file) {
                const isPhoto = (file.type === 'image/jpeg')||(file.type === 'image/png')||(file.type === 'image/gif');
                const isLt10M = file.size / 1024 / 1024 < 10;

                if (!isPhoto) {
                    this.$message.error('只能上传图片格式的文件!');
                }
                if (!isLt10M) {
                    this.$message.error('上传头像图片大小不能超过 10MB!');
                }
                return isPhoto && isLt10M;
            },
            uploadUserPhoto(fileObj){
                let param = new FormData();
                param.set("photo", fileObj.file);
                let config = {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    },
                    changeOrigin: true
                }
                this.$http.post('/upload_photo', param, config).then((response)=>{
                    if (response.data.type == 'success') {
                        this.$message({
                            message: response.data.msg,
                            type: 'success'
                        });
                        this.imageUrl=response.data.filepath;
                    } else {
                        this.$message({
                            message: response.data.msg,
                            type: 'error'
                        });
                    }
                });
            },
            uploadRealPhoto(fileObj){
                let param = new FormData();
                param.set("photo", fileObj.file);
                let config = {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    },
                    changeOrigin: true
                }
                this.$http.post('/upload_photo', param, config).then((response)=>{
                    if (response.data.type == 'success') {
                        this.$message({
                            message: response.data.msg,
                            type: 'success'
                        });
                        this.realPhotoUrl=response.data.filepath;
                    } else {
                        this.$message({
                            message: response.data.msg,
                            type: 'error'
                        });
                    }
                });
            }
        }
    };
</script>
<style>
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .el-upload .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 150px;
        height: 150px;
        line-height: 150px;
        text-align: center;
    }

    .avatar-uploader .avatar {
        width: 150px;
        height: 150px;
        display: block;
    }

    .el-upload .el-upload__input {
        display: none;
    }
</style>
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

    .add-input {
        display: table-row;
        margin-top: 20px;
        cursor: pointer
    }

    .a_upload {
        padding: 4px 10px;
        line-height: 20px;
        position: relative;
        cursor: pointer;
        color: #fff;
        background: #409eff;
        border-radius: 4px;
        overflow: hidden;
        display: inline-block;
        font-size: 14px;
        *display: inline;
        *zoom: 1;
        margin-top: 50px;
        margin-left: 20px;
    }

    .a_upload:hover {
        color: #fff;
        background: #409eff;
        text-decoration: none;
    }

    .a_upload:focus {
        outline: none;
    }

    a:link,
    a:visited {
        text-decoration: none;
    }

    /* 被选择的链接 */
</style>