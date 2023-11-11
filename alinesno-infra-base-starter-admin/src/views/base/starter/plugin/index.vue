<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--插件数据-->
      <el-col :span="24" :xs="24">
        <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
          <el-form-item label="插件名称" prop="tempName">
            <el-input v-model="queryParams.tempName" placeholder="请输入插件名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="插件名称" prop="tempName">
            <el-input v-model="queryParams['condition[tempName|like]']" placeholder="请输入插件名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
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
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete">删除</el-button>
          </el-col>

          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="PluginList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="图标" align="center" with="80" key="status" v-if="columns[5].visible">
          </el-table-column>

          <!-- 业务字段-->
          <el-table-column label="插件名称" align="center" key="tempName" prop="tempName" v-if="columns[0].visible" />
          <el-table-column label="插件描述" align="center" key="tempDesc" prop="tempDesc" v-if="columns[1].visible" :show-overflow-tooltip="true" />
          <el-table-column label="所属场景" align="center" key="screen" prop="screen" v-if="columns[2].visible" :show-overflow-tooltip="true" />
          <el-table-column label="所属类型" align="center" key="type" prop="type" v-if="columns[3].visible" :show-overflow-tooltip="true" />
          <el-table-column label="行业" align="center" key="industry" prop="industry" v-if="columns[4].visible" width="120" />
          <el-table-column label="来源作者" align="center" key="tempTeam" v-if="columns[5].visible" />

          <el-table-column label="添加时间" align="center" prop="addTime" v-if="columns[6].visible" width="160">
            <template #default="scope">
              <span>{{ parseTime(scope.row.addTime) }}</span>
            </template>
          </el-table-column>

          <!-- 操作字段  -->
          <el-table-column label="操作" align="center" width="150" class-name="small-padding fixed-width">
            <template #default="scope">
              <el-tooltip content="修改" placement="top" v-if="scope.row.PluginId !== 1">
                <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                           v-hasPermi="['system:Plugin:edit']"></el-button>
              </el-tooltip>
              <el-tooltip content="删除" placement="top" v-if="scope.row.PluginId !== 1">
                <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                           v-hasPermi="['system:Plugin:remove']"></el-button>
              </el-tooltip>
            </template>

          </el-table-column>
        </el-table>
        <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
      </el-col>
    </el-row>

    <!-- 添加或修改插件配置对话框 -->
    <el-dialog :title="title" v-model="open" width="900px" append-to-body>
      <el-form :model="form" :rules="rules" ref="PluginRef" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="名称" prop="tempName">
              <el-input v-model="form.tempName" placeholder="请输入插件名称" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="行业" prop="industry">
              <el-input v-model="form.industry" placeholder="请输入行业" maxlength="128" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="所属类型" prop="type">
              <el-input v-model="form.type" placeholder="请输入所属类型" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="所属场景" prop="screen">
              <el-input v-model="form.screen" placeholder="请输入所属场景" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="来源作者" prop="tempTeam">
              <el-input v-model="form.tempTeam" placeholder="请输入来源作者" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="tempDesc">
              <el-input v-model="form.tempDesc" placeholder="请输入插件备注"></el-input>
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

<script setup name="Plugin">

import {
  listPlugin,
  delPlugin,
  getPlugin,
  updatePlugin,
  addPlugin
} from "@/api/base/starter/plugin";

const router = useRouter();
const { proxy } = getCurrentInstance();

// 定义变量
const PluginList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const dateRange = ref([]);
const postOptions = ref([]);
const roleOptions = ref([]);

// 列显隐信息
const columns = ref([
  { key: 0, label: `插件名称`, visible: true },
  { key: 1, label: `插件描述`, visible: true },
  { key: 2, label: `所属场景`, visible: true },
  { key: 3, label: `所属类型`, visible: true },
  { key: 4, label: `行业`, visible: true },
  { key: 5, label: `来源作者`, visible: true },
  { key: 6, label: `更新时间`, visible: true }
]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    tempName: undefined,
    tempDesc: undefined
  },
  rules: {
    tempName: [{ required: true, message: "名称不能为空", trigger: "blur" }] ,
    industry: [{ required: true, message: "行业不能为空", trigger: "blur" }],
    type: [{ required: true, message: "所属类型不能为空", trigger: "blur" }] ,
    screen: [{ required: true , message: "所属场景不能为空", trigger: "blur"}],
    tempTeam: [{ required: true, message: "来源作者不能为空", trigger: "blur" }] ,
    tempDesc: [{ required: true, message: "备注不能为空", trigger: "blur" }]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询插件列表 */
function getList() {
  loading.value = true;
  listPlugin(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
    loading.value = false;
    PluginList.value = res.rows;
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
  const PluginIds = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除插件编号为"' + PluginIds + '"的数据项？').then(function () {
    return delPlugin(PluginIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => { });
};

/** 选择条数  */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
};

/** 重置操作表单 */
function reset() {
  form.value = {
    id: undefined,
    deptId: undefined,
    PluginName: undefined,
    screen: undefined,
    password: undefined,
    phonenumber: undefined,
    status: "0",
    remark: undefined,
  };
  proxy.resetForm("PluginRef");
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
  title.value = "添加插件";
};

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const PluginId = row.id || ids.value;
  getPlugin(PluginId).then(response => {
    form.value = response.data;
    form.value.PluginId = PluginId
    open.value = true;
    title.value = "修改插件";
  });
};

/** 提交按钮 */
function submitForm() {
  proxy.$refs["PluginRef"].validate(valid => {
    if (valid) {
      if (form.value.PluginId != undefined) {
        updatePlugin(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPlugin(form.value).then(response => {
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
