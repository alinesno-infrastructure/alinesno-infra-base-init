<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--仓库数据-->
      <el-col :span="24" :xs="24">
        <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
          <el-form-item label="仓库名称" prop="gitName">
            <el-input v-model="queryParams.gitName" placeholder="请输入仓库名称" clearable style="width: 240px"
                      @keyup.enter="handleQuery"/>
          </el-form-item>
          <el-form-item label="仓库名称" prop="gitName">
            <el-input v-model="queryParams['condition[gitName|like]']" placeholder="请输入仓库名称" clearable
                      style="width: 240px" @keyup.enter="handleQuery"/>
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

        <el-table v-loading="loading" :data="GitList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column label="图标" align="center" with="80" key="status" v-if="columns[5].visible">
          </el-table-column>

          <!-- 业务字段-->
          <el-table-column label="仓库名称" align="center" prop="gitName" v-if="columns[0].visible"/>
          <el-table-column label="仓库图标" align="center" prop="gitIcon" v-if="columns[1].visible"/>
          <el-table-column label="仓库地址" align="center" prop="gitHost" v-if="columns[2].visible"/>
          <el-table-column label="gitlab管理员" align="center" prop="gitUsername" v-if="columns[3].visible"/>
          <el-table-column label="仓库空间" align="center" prop="gitNamespace" v-if="columns[4].visible"/>
          <el-table-column label="仓库类型" align="center" prop="gitType" v-if="columns[5].visible"/>


          <!-- 操作字段  -->
          <el-table-column label="操作" align="center" width="150" class-name="small-padding fixed-width">
            <template #default="scope">
              <el-tooltip content="修改" placement="top" v-if="scope.row.GitId !== 1">
                <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                           v-hasPermi="['system:Git:edit']"></el-button>
              </el-tooltip>
              <el-tooltip content="删除" placement="top" v-if="scope.row.GitId !== 1">
                <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                           v-hasPermi="['system:Git:remove']"></el-button>
              </el-tooltip>
            </template>

          </el-table-column>
        </el-table>
        <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
                    v-model:limit="queryParams.pageSize" @pagination="getList"/>
      </el-col>
    </el-row>

    <!-- 添加或修改仓库配置对话框 -->
    <el-dialog :title="title" v-model="open" width="900px" append-to-body>
      <el-form :model="form" :rules="rules" ref="gitRef" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="仓库名称" prop="gitName">
              <el-input v-model="form.gitName" placeholder="请输入仓库名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="仓库图标" prop="gitIcon">
              <el-input v-model="form.gitIcon" placeholder="请输入仓库图标"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="仓库地址" prop="gitHost">
              <el-input v-model="form.gitHost" placeholder="请输入仓库地址"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="gitlab管理员" prop="gitUsername">
              <el-input v-model="form.gitUsername" placeholder="请输入gitlab管理员"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="仓库空间" prop="gitNamespace">
              <el-input v-model="form.gitNamespace" placeholder="请输入仓库空间"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="仓库类型" prop="gitType">
              <el-input v-model="form.gitType" placeholder="请输入仓库类型"/>
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

<script setup name="Git">

import {
  listGit,
  delGit,
  getGit,
  updateGit,
  addGit
} from "@/api/base/starter/git";

const router = useRouter();
const {proxy} = getCurrentInstance();

// 定义变量
const GitList = ref([]);
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
  {key: 0, label: '仓库名称', visible: true},
  {key: 1, label: '仓库图标', visible: true},
  {key: 2, label: '仓库地址', visible: true},
  {key: 3, label: 'gitlab管理员', visible: true},
  {key: 4, label: '仓库空间', visible: true},
  {key: 5, label: '仓库类型', visible: true}
]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    gitName: undefined,
    gitHost: undefined
  },
  rules: {
    gitName: [{required: true, message: "名称不能为空", trigger: "blur"}],
    gitIcon: [{required: true, message: "图标不能为空", trigger: "blur"}],
    gitHost: [{required: true, message: "地址不能为空", trigger: "blur"}],
    gitUsername: [{required: true, message: "管理员不能为空", trigger: "blur"}],
    gitNamespace: [{required: true, message: "空间不能为空", trigger: "blur"}],
    gitType: [{required: true, message: "类型不能为空", trigger: "blur"}]
  }
});

const {queryParams, form, rules} = toRefs(data);

/** 查询仓库列表 */
function getList() {
  loading.value = true;
  listGit(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
    loading.value = false;
    GitList.value = res.rows;
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
  const GitIds = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除仓库编号为"' + GitIds + '"的数据项？').then(function () {
    return delGit(GitIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
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
    gitName: undefined,
    gitIcon: undefined,
    gitHost: undefined,
    gitUsername: undefined,
    gitNamespace: undefined,
    gitType: undefined
  };
  proxy.resetForm("gitRef");
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
  title.value = "添加仓库";
};

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const GitId = row.id || ids.value;
  getGit(GitId).then(response => {
    form.value = response.data;
    form.value.GitId = GitId
    open.value = true;
    title.value = "修改仓库";
  });
};

/** 提交按钮 */
function submitForm() {
  proxy.$refs["gitRef"].validate(valid => {
    if (valid) {
      console.log("正在提交")
      if (form.value.GitId != undefined) {
        console.log("正在修改")
        updateGit(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        console.log("正在添加")
        addGit(form.value).then(response => {
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
