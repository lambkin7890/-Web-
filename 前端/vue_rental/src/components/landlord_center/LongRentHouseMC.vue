<template>
  <div class="main">
    <el-button type="primary" round style="float:left;margin-bottom:20px;" @click="openAddDialog">上传房源
    </el-button>
    <el-table :data="tableData" style="width: 100%">
      <!--prop绑定相应实体的字段-->
      <el-table-column type="index" width="50" label="序号"></el-table-column>
      <el-table-column prop="sn" label="房源编号" width="100"></el-table-column>
      <el-table-column prop="name" label="房源名称" width="250" show-overflow-tooltip></el-table-column>
      <el-table-column label="房源图片" width="120">
        <template slot-scope="scope">
          <img v-if="scope.row.photo" :src="'http://localhost:8087'+scope.row.photo" style="width:80px;height:60px;">
        </template>
      </el-table-column>
      <el-table-column label="房源价格" width="100">
        <template slot-scope="scope">
          ￥{{scope.row.price}}/月
        </template>
      </el-table-column>
      <el-table-column prop="address" label="房源地址" width="150" show-overflow-tooltip></el-table-column>
      <el-table-column label="面积" width="80">
        <template slot-scope="scope">
          {{scope.row.area}}平米
        </template>
      </el-table-column>
      <el-table-column prop="floorRange" label="楼层位置" width="80"></el-table-column>
      <el-table-column prop="totalFloor" label="总楼层" width="80"></el-table-column>
      <el-table-column prop="introduce" label="房源简介" width="200" show-overflow-tooltip></el-table-column>
      <el-table-column label="房东" width="80">
        <template slot-scope="scope">
          {{scope.row.landlordName}}
        </template>
      </el-table-column>
      <el-table-column prop="bedRoomNum" label="卧室数" width="80"></el-table-column>
      <el-table-column prop="sittingRoomNum" label="客厅数" width="80"></el-table-column>
      <el-table-column prop="toiletNum" label="卫生间数" width="80"></el-table-column>
      <el-table-column prop="isEntire" label="整租" width="80"></el-table-column>
      <el-table-column prop="isSouth" label="朝南" width="80"></el-table-column>
      <el-table-column prop="isElevator" label="有电梯" width="80"></el-table-column>
      <el-table-column prop="createTime" label="发布时间" width="150"></el-table-column>
      <el-table-column fixed="right" label="操作" width="120">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" @click="handleEditClick(scope.row.id)" circle></el-button>
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

    <!-- 上传房源模态框 -->
    <el-dialog title="上传房源" :visible.sync="dialogFormVisible" width="50%" :before-close="handleClose">
      <span>
        <el-form ref="addHouseForm" :rules="rules" :model="addForm" label-width="80px">
          <el-form-item label="房源图片">
            <el-upload class="avatar-uploader house-uploader" action="#" name="photo" :http-request="uploadPhoto"
              :auto-upload="true" :show-file-list="false" :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="'http://localhost:8087'+imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item prop="name" label="房源名称">
            <el-input v-model="addForm.name" maxlength="30" show-word-limit></el-input>
          </el-form-item>
          <el-form-item prop="price" label="房源价格">
            <el-input v-model.number="addForm.price"></el-input>
          </el-form-item>
          <el-form-item prop="address" label="房源地址">
            <el-input v-model="addForm.address"></el-input>
          </el-form-item>
          <el-form-item prop="introduce" label="房源简介">
            <el-input v-model="addForm.introduce"></el-input>
          </el-form-item>
          <el-form-item prop="area" label="面积">
            <el-input v-model.number="addForm.area"></el-input>
          </el-form-item>
          <el-form-item prop="floorRange" label="楼层位置">
            <el-select v-model="addForm.floorRange" style="width:100%" placeholder="-请选择-">
              <el-option value="低层"></el-option>
              <el-option value="中层"></el-option>
              <el-option value="高层"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="totalFloor" label="总楼层">
            <el-input v-model.number="addForm.totalFloor"></el-input>
          </el-form-item>
          <el-form-item prop="bedRoomNum" label="卧室数">
            <el-select v-model="addForm.bedRoomNum" style="width:100%" placeholder="-请选择-">
              <el-option value="1"></el-option>
              <el-option value="2"></el-option>
              <el-option value="3"></el-option>
              <el-option value="4"></el-option>
              <el-option value="5"></el-option>
              <el-option value="6"></el-option>
              <el-option value="7"></el-option>
              <el-option value="8"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="sittingRoomNum" label="客厅数">
            <el-select v-model="addForm.sittingRoomNum" style="width:100%" placeholder="-请选择-">
              <el-option value="1"></el-option>
              <el-option value="2"></el-option>
              <el-option value="3"></el-option>
              <el-option value="4"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="toiletNum" label="卫生间数">
            <el-select v-model="addForm.toiletNum" style="width:100%" placeholder="-请选择-">
              <el-option value="1"></el-option>
              <el-option value="2"></el-option>
              <el-option value="3"></el-option>
              <el-option value="4"></el-option>
              <el-option value="5"></el-option>
              <el-option value="6"></el-option>
              <el-option value="7"></el-option>
              <el-option value="8"></el-option>
              <el-option value="9"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="isEntire" label="整租">
            <el-select v-model="addForm.isEntire" style="width:100%" placeholder="-请选择-">
              <el-option label="是" :value="1"></el-option>
              <el-option label="否" :value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="isSouth" label="朝南">
            <el-select v-model="addForm.isSouth" style="width:100%" placeholder="-请选择-">
              <el-option label="是" :value="1"></el-option>
              <el-option label="否" :value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="isElevator" label="有电梯">
            <el-select v-model="addForm.isElevator" style="width:100%" placeholder="-请选择-">
              <el-option label="是" :value="1"></el-option>
              <el-option label="否" :value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible= false">取 消</el-button>
        <el-button type="primary" @click="addItem">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑房源模态框 -->
    <el-dialog title="修改房源信息" :visible.sync="dialogEditFormVisible" width="50%" :before-close="handleClose">
      <span>
        <el-form ref="editHouseForm" :rules="rules" :model="editForm" label-width="80px">
          <el-form-item label="房源图片">
            <el-upload class="avatar-uploader house-uploader" action="#" name="photo" :http-request="uploadPhoto"
              :auto-upload="true" :show-file-list="false" :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="'http://localhost:8087'+imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item prop="name" label="房源名称">
            <el-input v-model="editForm.name"  maxlength="30" show-word-limit></el-input>
          </el-form-item>
          <el-form-item prop="price" label="房源价格">
            <el-input v-model="editForm.price"></el-input>
          </el-form-item>
          <el-form-item prop="address" label="房源地址">
            <el-input v-model="editForm.address"></el-input>
          </el-form-item>
          <el-form-item prop="introduce" label="房源简介">
            <el-input v-model="editForm.introduce"></el-input>
          </el-form-item>
          <el-form-item prop="area" label="面积">
            <el-input v-model="editForm.area"></el-input>
          </el-form-item>
          <el-form-item prop="floorRange" label="楼层位置">
            <el-select v-model="editForm.floorRange" style="width:100%" placeholder="-请选择-">
              <el-option value="低层"></el-option>
              <el-option value="中层"></el-option>
              <el-option value="高层"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="totalFloor" label="总楼层">
            <el-input v-model.number="editForm.totalFloor"></el-input>
          </el-form-item>
          <el-form-item prop="bedRoomNum" label="卧室数">
            <el-select v-model="editForm.bedRoomNum" style="width:100%" placeholder="-请选择-">
              <el-option value="1"></el-option>
              <el-option value="2"></el-option>
              <el-option value="3"></el-option>
              <el-option value="4"></el-option>
              <el-option value="5"></el-option>
              <el-option value="6"></el-option>
              <el-option value="7"></el-option>
              <el-option value="8"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="sittingRoomNum" label="客厅数">
            <el-select v-model="editForm.sittingRoomNum" style="width:100%" placeholder="-请选择-">
              <el-option value="1"></el-option>
              <el-option value="2"></el-option>
              <el-option value="3"></el-option>
              <el-option value="4"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="toiletNum" label="卫生间数">
            <el-select v-model="editForm.toiletNum" style="width:100%" placeholder="-请选择-">
              <el-option value="1"></el-option>
              <el-option value="2"></el-option>
              <el-option value="3"></el-option>
              <el-option value="4"></el-option>
              <el-option value="5"></el-option>
              <el-option value="6"></el-option>
              <el-option value="7"></el-option>
              <el-option value="8"></el-option>
              <el-option value="9"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="isEntire" label="整租">
            <el-select v-model="editForm.isEntire" style="width:100%" placeholder="-请选择-">
              <el-option label="是" :value="1"></el-option>
              <el-option label="否" :value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="isSouth" label="朝南">
            <el-select v-model="editForm.isSouth" style="width:100%" placeholder="-请选择-">
              <el-option label="是" :value="1"></el-option>
              <el-option label="否" :value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="isElevator" label="有电梯">
            <el-select v-model="editForm.isElevator" style="width:100%" placeholder="-请选择-">
              <el-option label="是" :value="1"></el-option>
              <el-option label="否" :value="0"></el-option>
            </el-select>
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
            if (value < 10 || value >= 100000) {
              callback(new Error('请输入合理范围内的价格'));
            } else {
              callback();
            }
          }
        }, 1000);
      };
      var checkArea = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('房源面积不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
          } else {
            callback();
          }
        }, 1000)

      };
      var checkTotalFloor = (rule, value, callback) => {
        if (value) {
          setTimeout(() => {
            if (!Number.isInteger(value)) {
              callback(new Error('请输入数字值'));
            } else {
              callback();
            }
          }, 1000)
        } else {
          callback();
        }
      };
      return {
        tableData: [],
        dialogFormVisible: false,
        dialogEditFormVisible: false,
        addForm: {
          id: '',
          sn: '',
          name: '',
          photo: 'logo/kodinger.jpg',
          price: '',
          address: '',
          introduce: '',
          area: '',
          floorRange: '',
          bedRoomNum: '',
          sittingRoomNum: '',
          toiletNum: '',
          landlordId: '',
          landlordId:'',
          isEntire:0,
          isSouth:0,
          isElevator:0,
          createTime:''
        },
        editForm: {
          id: '',
          sn: '',
          name: '',
          photo: 'logo/kodinger.jpg',
          price: '',
          address: '',
          introduce: '',
          area: '',
          floorRange: '',
          bedRoomNum: '',
          sittingRoomNum: '',
          toiletNum: '',
          landlordId:'',
          isEntire:0,
          isSouth:0,
          isElevator:0,
          createTime:''
        },
        file: '',
        currentPage: 1,
        pageSize: 10,
        totalCount: 0,
        rules: {
          name: [
            { required: true, message: '请输入房源名称', trigger: 'blur' }
          ],
          price: [
            { validator: checkPrice, trigger: 'blur' }
          ],
          address: [
            { required: true, message: '请输入房源地址', trigger: 'blur' }
          ],
          area: [
            {  required: true, validator: checkArea, trigger: 'blur' }
          ],
          bedRoomNum: [
            { required: true, message: '请填写卧室数', trigger: 'blur' }
          ],
          sittingRoomNum: [
            { required: true, message: '请输入客厅数', trigger: 'blur' }
          ],
          toiletNum: [
            { required: true, message: '请填写卫生间数', trigger: 'blur' }
          ],
          totalFloor: [
            { validator: checkTotalFloor, trigger: 'blur' }
          ]
        },
        userInfo: {
          id: '',
          roleId: ''
        },
        imageUrl: ''
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
          url: "long_rent_house/list",
          params: {
            currentPage: that.currentPage,
            pageSize: that.pageSize,
            landlordId: that.userInfo.id
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
        this.currentPage = val;
        // 切换页码时，要获取每页显示的条数
        this.getTableData()
      },
      handleClose(done) {
        this.$confirm("确认关闭？")
          .then(_ => {
            done();
          })
          .catch(_ => { });
      },
      openAddDialog(){
        var that=this;
        this.dialogFormVisible = true;
        this.$nextTick(()=>{
          that.$refs.addHouseForm.resetFields();
          that.imageUrl='';
        })
      },
      handleEditClick(itemId) {
        var that = this;
        this.$http({
          method: "get",
          changeOrigin: true, // 这个参数代表发送跨域请求
          url: "long_rent_house/detail",
          params: {
            id: itemId
          }
        }).then((response) => {
          that.editForm = response.data;
          that.imageUrl=response.data.photo;
          that.dialogEditFormVisible = true;
        })
      },
      addItem() {
        var that = this;
        that.addForm.landlordId = that.userInfo.id;
        this.addForm.photo = this.imageUrl;
        this.$refs.addHouseForm.validate((valid) => {
          if (valid) {
            this.$http({
              method: "post",
              changeOrigin: true, // 这个参数代表发送跨域请求
              url: "long_rent_house/add",
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
                that.dialogFormVisible = false
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
        this.editForm.photo = this.imageUrl;
        this.$refs.editHouseForm.validate((valid) => {
          if (valid) {
            this.$http({
              method: "post",
              changeOrigin: true, // 这个参数代表发送跨域请求
              url: "long_rent_house/edit",
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
                that.dialogEditFormVisible = false;
                that.getTableData();
              }
            })
          } else {
            return false;
          }
        })
      },
      removeItem(id) {
        var that = this;
        this.$confirm("确认删除").then(() => {
          this.$http({
            method: "post",
            changeOrigin: true, // 这个参数代表发送跨域请求
            url: "long_rent_house/delete",
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
        const isPhoto = (file.type === 'image/jpeg') || (file.type === 'image/png') || (file.type === 'image/gif');
        const isLt10M = file.size / 1024 / 1024 < 10;

        if (!isPhoto) {
          this.$message.error('只能上传图片格式的文件!');
        }
        if (!isLt10M) {
          this.$message.error('上传图片大小不能超过 10MB!');
        }
        return isPhoto && isLt10M;
      },
      uploadPhoto(fileObj) {
        let param = new FormData();
        param.set("photo", fileObj.file);
        let config = {
          headers: {
            'Content-Type': 'multipart/form-data'
          },
          changeOrigin: true
        }
        this.$http.post('/upload_photo', param, config).then((response) => {
          if (response.data.type == 'success') {
            this.$message({
              message: response.data.msg,
              type: 'success'
            });
            this.imageUrl = response.data.filepath;
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

  .el-button.is-round {
    margin-left: 20px;
  }

  .el-main {
    text-align: center;
    padding: 0px;
    overflow-x: visible;
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
    margin-top: 45px;
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