<template>
   <div class="app-container">
      <el-row :gutter="20">
         <!--数据库数据-->
         <el-col :span="24" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="108px">
               <el-form-item label="数据库名称" prop="DatabaseName">
                  <el-input v-model="queryParams.DatabaseName" placeholder="请输入数据库名称" clearable style="width: 240px"
                     @keyup.enter="handleQuery" />
               </el-form-item>
               <el-form-item>
                  <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
                  <el-button icon="Refresh" @click="resetQuery">重置</el-button>
               </el-form-item>
            </el-form>

            <el-row :gutter="10" class="mb8">
               <el-col :span="1.5">
                  <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
               </el-col>
               <el-col :span="1.5">
                  <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
                     v-hasPermi="['system:Database:edit']">修改</el-button>
               </el-col>
               <el-col :span="1.5">
                  <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
                     v-hasPermi="['system:Database:remove']">删除</el-button>
               </el-col>

               <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
            </el-row>

            <el-table v-loading="loading" :data="DatabaseList" @selection-change="handleSelectionChange">
               <el-table-column type="selection" width="50" align="center" />
               <el-table-column label="图标" align="center" with="80" key="status" v-if="columns[5].visible">
                  <template #default="scope">
                     <el-switch v-model="scope.row.status" active-value="0" inactive-value="1"
                        @change="handleStatusChange(scope.row)"></el-switch>
                  </template>
               </el-table-column>
               <el-table-column label="数据库名称" align="center" key="DatabaseId" prop="DatabaseId"
                  v-if="columns[0].visible" />
               <el-table-column label="数据库描述" align="center" key="DatabaseName" prop="DatabaseName"
                  v-if="columns[1].visible" :show-overflow-tooltip="true" />
               <el-table-column label="表数据量" align="center" key="nickName" prop="nickName" v-if="columns[2].visible"
                  :show-overflow-tooltip="true" />
               <el-table-column label="类型" align="center" key="deptName" prop="dept.deptName" v-if="columns[3].visible"
                  :show-overflow-tooltip="true" />
               <el-table-column label="数据库地址" align="center" key="phonenumber" prop="phonenumber" v-if="columns[4].visible"
                  width="120" />
               <el-table-column label="状态" align="center" key="status" v-if="columns[5].visible">
                  <template #default="scope">
                     <el-switch v-model="scope.row.status" active-value="0" inactive-value="1"
                        @change="handleStatusChange(scope.row)"></el-switch>
                  </template>
               </el-table-column>
               <el-table-column label="更新时间" align="center" prop="createTime" v-if="columns[6].visible" width="160">
                  <template #default="scope">
                     <span>{{ parseTime(scope.row.createTime) }}</span>
                  </template>
               </el-table-column>
               <el-table-column label="操作" align="center" width="150" class-name="small-padding fixed-width">
                  <template #default="scope">
                     <el-tooltip content="修改" placement="top" v-if="scope.row.DatabaseId !== 1">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                           v-hasPermi="['system:Database:edit']"></el-button>
                     </el-tooltip>
                     <el-tooltip content="删除" placement="top" v-if="scope.row.DatabaseId !== 1">
                        <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                           v-hasPermi="['system:Database:remove']"></el-button>
                     </el-tooltip>
                     <el-tooltip content="重置密码" placement="top" v-if="scope.row.DatabaseId !== 1">
                        <el-button link type="primary" icon="Key" @click="handleResetPwd(scope.row)"
                           v-hasPermi="['system:Database:resetPwd']"></el-button>
                     </el-tooltip>
                     <el-tooltip content="分配角色" placement="top" v-if="scope.row.DatabaseId !== 1">
                        <el-button link type="primary" icon="CircleCheck" @click="handleAuthRole(scope.row)"
                           v-hasPermi="['system:Database:edit']"></el-button>
                     </el-tooltip>
                  </template>
               </el-table-column>
            </el-table>
            <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
               v-model:limit="queryParams.pageSize" @pagination="getList" />
         </el-col>
      </el-row>

      <!-- 添加或修改数据库配置对话框 -->
      <el-dialog :title="title" v-model="open" width="900px" append-to-body>
         <el-form :model="form" :rules="rules" ref="DatabaseRef" label-width="100px">
            <el-row>
               <el-col :span="24">
                  <el-form-item label="数据库昵称" prop="nickName">
                     <el-input v-model="form.nickName" placeholder="请输入数据库昵称" maxlength="30" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="归属部门" prop="deptId">
                     <el-tree-select v-model="form.deptId" :data="deptOptions"
                        :props="{ value: 'id', label: 'label', children: 'children' }" value-key="id" placeholder="请选择归属部门"
                        check-strictly />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="手机号码" prop="phonenumber">
                     <el-input v-model="form.phonenumber" placeholder="请输入手机号码" maxlength="11" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="邮箱" prop="email">
                     <el-input v-model="form.email" placeholder="请输入邮箱" maxlength="50" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item v-if="form.DatabaseId == undefined" label="数据库名称" prop="DatabaseName">
                     <el-input v-model="form.DatabaseName" placeholder="请输入数据库名称" maxlength="30" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item v-if="form.DatabaseId == undefined" label="数据库密码" prop="password">
                     <el-input v-model="form.password" placeholder="请输入数据库密码" type="password" maxlength="20"
                        show-password />
                  </el-form-item>
               </el-col>
            </el-row>

            <el-row>
               <el-col :span="24">
                  <el-form-item label="备注">
                     <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
                  </el-form-item>
               </el-col>
            </el-row>
         </el-form>
         <template #footer>
            <div class="dialog-footer">
               <el-button type="primary" @click="submitForm">确 定</el-button>
               <el-button @click="cancel">取 消</el-button>
            </div>
         </template>
      </el-dialog>

   </div>
</template>

<script setup name="Database">

import {
   listDatabase,
   delDatabase,
   getDatabase,
   updateDatabase,
   addDatabase
} from "@/api/base/starter/database";

const router = useRouter();
const { proxy } = getCurrentInstance();

const DatabaseList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const dateRange = ref([]);
const deptOptions = ref(undefined);
const postOptions = ref([]);
const roleOptions = ref([]);

// 列显隐信息
const columns = ref([
   { key: 0, label: `数据库编号`, visible: true },
   { key: 1, label: `数据库名称`, visible: true },
   { key: 2, label: `数据库昵称`, visible: true },
   { key: 3, label: `部门`, visible: true },
   { key: 4, label: `手机号码`, visible: true },
   { key: 5, label: `状态`, visible: true },
   { key: 6, label: `创建时间`, visible: true }
]);

const data = reactive({
   form: {},
   queryParams: {
      pageNum: 1,
      pageSize: 10,
      DatabaseName: undefined,
      phonenumber: undefined,
      status: undefined,
      deptId: undefined
   },
   rules: {
      DatabaseName: [{ required: true, message: "数据库名称不能为空", trigger: "blur" }, { min: 2, max: 20, message: "数据库名称长度必须介于 2 和 20 之间", trigger: "blur" }],
      nickName: [{ required: true, message: "数据库昵称不能为空", trigger: "blur" }],
      password: [{ required: true, message: "数据库密码不能为空", trigger: "blur" }, { min: 5, max: 20, message: "数据库密码长度必须介于 5 和 20 之间", trigger: "blur" }],
      email: [{ type: "email", message: "请输入正确的邮箱地址", trigger: ["blur", "change"] }],
      phonenumber: [{ pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur" }]
   }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询数据库列表 */
function getList() {
   loading.value = true;
   listDatabase(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
      loading.value = false;
      DatabaseList.value = res.rows;
      total.value = res.total;
   });
};

/** 搜索按钮操作 */
function handleQuery() {
   queryParams.value.pageNum = 1;
   getList();
};

/** 重置按钮操作 */
function resetQuery() {
   dateRange.value = [];
   proxy.resetForm("queryRef");
   queryParams.value.deptId = undefined;
   proxy.$refs.deptTreeRef.setCurrentKey(null);
   handleQuery();
};
/** 删除按钮操作 */
function handleDelete(row) {
   const DatabaseIds = row.DatabaseId || ids.value;
   proxy.$modal.confirm('是否确认删除数据库编号为"' + DatabaseIds + '"的数据项？').then(function () {
      return delDatabase(DatabaseIds);
   }).then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
   }).catch(() => { });
};

/** 数据库状态修改  */
function handleStatusChange(row) {
   let text = row.status === "0" ? "启用" : "停用";
   proxy.$modal.confirm('确认要"' + text + '""' + row.DatabaseName + '"数据库吗?').then(function () {
      return changeDatabaseStatus(row.DatabaseId, row.status);
   }).then(() => {
      proxy.$modal.msgSuccess(text + "成功");
   }).catch(function () {
      row.status = row.status === "0" ? "1" : "0";
   });
};

/** 跳转角色分配 */
function handleAuthRole(row) {
   const DatabaseId = row.DatabaseId;
   router.push("/system/Database-auth/role/" + DatabaseId);
};
/** 重置密码按钮操作 */
function handleResetPwd(row) {

};
/** 选择条数  */
function handleSelectionChange(selection) {
   ids.value = selection.map(item => item.DatabaseId);
   single.value = selection.length != 1;
   multiple.value = !selection.length;
};

/** 重置操作表单 */
function reset() {
   form.value = {
      DatabaseId: undefined,
      deptId: undefined,
      DatabaseName: undefined,
      nickName: undefined,
      password: undefined,
      phonenumber: undefined,
      email: undefined,
      sex: undefined,
      status: "0",
      remark: undefined,
      postIds: [],
      roleIds: []
   };
   proxy.resetForm("DatabaseRef");
};
/** 取消按钮 */
function cancel() {
   open.value = false;
   reset();
};

/** 新增按钮操作 */
function handleAdd() {
   reset();
   open.value = true;
   title.value = "添加数据库";
};

/** 修改按钮操作 */
function handleUpdate(row) {
   reset();
   const DatabaseId = row.DatabaseId || ids.value;
   getDatabase(DatabaseId).then(response => {
      form.value = response.data;
      postOptions.value = response.posts;
      roleOptions.value = response.roles;
      form.value.postIds = response.postIds;
      form.value.roleIds = response.roleIds;
      open.value = true;
      title.value = "修改数据库";
      form.password = "";
   });
};

/** 提交按钮 */
function submitForm() {
   proxy.$refs["DatabaseRef"].validate(valid => {
      if (valid) {
         if (form.value.DatabaseId != undefined) {
            updateDatabase(form.value).then(response => {
               proxy.$modal.msgSuccess("修改成功");
               open.value = false;
               getList();
            });
         } else {
            addDatabase(form.value).then(response => {
               proxy.$modal.msgSuccess("新增成功");
               open.value = false;
               getList();
            });
         }
      }
   });
};

getList();

</script>
