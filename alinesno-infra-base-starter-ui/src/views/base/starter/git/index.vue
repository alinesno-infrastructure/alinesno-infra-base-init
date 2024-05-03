<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--仓库数据-->
      <el-col :span="24" :xs="24">
        <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
          <el-form-item label="仓库名称" prop="gitName">
            <el-input v-model="queryParams.gitName" placeholder="请输入仓库名称" clearable style="width: 240px"
              @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="仓库名称" prop="gitName">
            <el-input v-model="queryParams['condition[gitName|like]']" placeholder="请输入仓库名称" clearable
              style="width: 240px" @keyup.enter="handleQuery" />
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
          <el-table-column type="selection" width="50" align="center" disable="true" />
          <el-table-column label="图标" align="center" width="70" key="icon" v-if="columns[5].visible">
            <template #default="scope">
              <img :src="scope.row.gitIcon" style="width:35px" />
            </template>
          </el-table-column>

          <el-table-column label="仓库名称" align="center" width="100" prop="gitName" />
          <el-table-column label="简介" align="left" prop="fieldProp" :show-overflow-tooltip="true" />
          <el-table-column label="类型" width="80" align="center" prop="innerGit">
            <template #default="scope">
              <span v-if="scope.row.innerGit == 1">内置</span>
              <span v-else>个人</span>
            </template>
          </el-table-column>
          <el-table-column label="状态" width="80" prop="hasStatus">
            <template #default="scope">
              <el-switch v-model="scope.row.hasStatus" :active-value="0" :inactive-value="1" @change="handleStatusChange(scope.row)"></el-switch>
            </template>
          </el-table-column>

          <el-table-column label="操作" align="center" width="250" class-name="small-padding fixed-width">
            <template #default="scope">
              <el-button v-if="scope.row.hasBing != 1" type="primary" bg text @click="bingGit(scope.row)"> <i class="fas fa-link fa-fw"></i> 绑定账号</el-button>
              <el-button v-else class="bing-btn" type="danger" bg text @click="unBingGit(scope.row)"> <i class="fas fa-unlink fa-fw"></i> 解除绑定</el-button>
            </template>
          </el-table-column>

          <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
            <template #default="scope">
              <el-button size="mini" type="text" icon="Edit" @click="handleUpdate(scope.row)"> 修改</el-button>
              <el-button size="mini" type="text" icon="Delete" @click="handleDelete(scope.row)"> 删除</el-button>
            </template>
          </el-table-column>

        </el-table>
        <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
          v-model:limit="queryParams.pageSize" @pagination="getList" />
      </el-col>
    </el-row>

    <!-- 添加或修改仓库类型对话框 -->
    <el-dialog :title="title" :rules="rules" v-model="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="仓库类型" prop="gitType">
          <el-radio-group v-model="form.gitType">
            <el-radio :label="item.value" v-for="(item, index) in svnList" :key="index" :selected="item.status == 1">
              {{ item.name }}
            </el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="仓库名称" prop="gitName">
          <el-input v-model="form.gitName" placeholder="例如:企业代码管理平台" />
        </el-form-item>
        <el-form-item label="仓库简介" prop="fieldProp">
          <el-input v-model="form.fieldProp" placeholder="例如:test-cicd,没有则自动创建" />
        </el-form-item>

      </el-form>

      <el-link href="https://gitlab.your-domain.com" style="margin-left:50px" type="primary"
        target="_blank">PersonToken获取方式</el-link>

      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 绑定github/gitee -->
    <el-dialog title="提示" v-model="githubDialogVisible" width="30%">
      <el-timeline>
        <el-timeline-item @click="openGithubBing(activity)" v-for="(activity, index) in activities" :key="index">
          <a v-if="activity.type === 'openLink'" :href="githubBingLink" target="_blank"> {{ activity.content }} </a>
          <a v-if="activity.type === 'closeBack'" href="javascript:void(0)" @click="closeGithub"> {{ activity.content }}</a>
        </el-timeline-item>
      </el-timeline>

      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="closeGithub">确 定</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 绑定gitlab -->
    <el-dialog title="提示" v-model="gitlabDialogVisible" width="700px">

      <el-form ref="formGitlab" :model="formGitlab" :rules="rules" label-width="80px">

        <el-form-item label="仓库地址" prop="gitHost">
          <el-input v-model="formGitlab.gitHost" placeholder="例如:https://gitlab.your-domain.com" />
        </el-form-item>
        <el-form-item v-if="currentBingGit === 'gitlab'" label="用户名" prop="userName">
          <el-input v-model="formGitlab.userName" placeholder="例如:企业代码管理平台" />
        </el-form-item>
        <el-form-item label="密钥" prop="accessPrivateToken">
          <el-input v-model="formGitlab.accessPrivateToken" placeholder="请输入仓库管理密钥" />
        </el-form-item>

      </el-form>

      <el-link v-if="currentBingGit === 'gitlab'"
        href="https://help.aliyun.com/document_detail/202197.htm?spm=5176.8351553.help.dexternal.7f231991B2jC51#section-h93-qn7-zz2"
        style="margin-left:50px" type="primary" target="_blank">PersonToken获取方式</el-link>
      <el-link v-if="currentBingGit === 'gitea'" href="javascript:void(0)" style="margin-left:50px" type="primary"
        target="_blank">PersonToken获取方式</el-link>

      <template #footer>
        <span class="dialog-footer">
          <el-button type="primary" @click="submitFormGitlab">提 交</el-button>
        </span>
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
  addGit , 
  unBing , 
  getGithubAuthurl , 
  bingFormGitlab 
} from "@/api/base/starter/git";

import { ElLoading } from 'element-plus'

const router = useRouter();
const { proxy } = getCurrentInstance();

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
const svnList = ref([
  { icon: 'fab fa-gitlab', value: 'gitlab', name: 'Gitlab', isConnect: true, status: 1 },
  { icon: 'fab fa-gitlab', value: 'gitea', name: 'Gitea', isConnect: true, status: 1 },
]);

const formGitlab = ref({
  gitlabId: '' ,
  gitType: '' , 
});

const activities = ref([{
    content: '点击前往源代码仓库登录',
    type:'openLink' 
  }, {
    content: '返回控制台确认绑定完成',
    type:'closeBack' 
  }
]);

// 绑定github链接
const githubBingLink = ref(null);
// 是否显示github会话
const githubDialogVisible = ref(false);
// 显示gitlab会话
const gitlabDialogVisible = ref(false);

// 列显隐信息
const columns = ref([
  { key: 0, label: '仓库名称', visible: true },
  { key: 1, label: '仓库图标', visible: true },
  { key: 2, label: '仓库地址', visible: true },
  { key: 3, label: 'gitlab管理员', visible: true },
  { key: 4, label: '仓库空间', visible: true },
  { key: 5, label: '仓库类型', visible: true }
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
    gitName: [{ required: true, message: "名称不能为空", trigger: "blur" }],
    gitIcon: [{ required: true, message: "图标不能为空", trigger: "blur" }],
    gitHost: [{ required: true, message: "地址不能为空", trigger: "blur" }],
    gitUsername: [{ required: true, message: "管理员不能为空", trigger: "blur" }],
    gitNamespace: [{ required: true, message: "空间不能为空", trigger: "blur" }],
    gitType: [{ required: true, message: "类型不能为空", trigger: "blur" }]
  }
});

const { queryParams, form, rules } = toRefs(data);

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
  queryParams.value.id = undefined;
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
    open.value = true;
    title.value = "修改仓库";
  });
};

/** 提交按钮 */
function submitForm() {
  proxy.$refs["gitRef"].validate(valid => {
    if (valid) {
      if (form.value.id !== undefined) {
        updateGit(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addGit(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
};

// 绑定 bingGit
function bingGit(row) {

  const gitType = row.gitType;
  if (gitType === 'alicode' || gitType === 'bitbucket') {
    this.$message({
      message: '仓库' + gitType + '绑定暂未开通.',
      type: 'warning'
    });
    return;
  }

  console.log('git type = ' + gitType);
  if (gitType === 'github' || gitType === 'gitee') {

    const loading = ElLoading.service({
      lock: true,
      text: 'Loading',
      background: 'rgba(0, 0, 0, 0.7)',
    })

    getGithubAuthurl(row.gitType).then(res => {
      loading.close();

      if (res.code == 200) {
        githubBingLink.value = res.data;

        // 配置githu链接
        githubDialogVisible.value = true;
      } else {
        proxy.$modal.msgSuccess("删除成功");
      }
    }).catch(err => {
      loading.close();
    });

  } else if (gitType === 'gitlab' || gitType === 'gitea') {
    formGitlab.value.gitlabId = row.id;
    gitlabDialogVisible.value = true;
    currentBingGit = gitType;
  }

}

// 解除gith绑定
function unBingGit(row) {
  const gitType = row.gitType;

  this.$confirm('是否确认要解绑"' + gitType + '"仓库?', "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning"
  }).then(function () {
  }).then(() => {
    unBing(row.id, gitType).then(res => {
      console.log('res = ' + res);
      if (res.code == 200) {
        this.msgSuccess("解绑" + gitType + "成功");
        this.getList();
      }
    });
  })
}

// 关闭github链接
function closeGithub() {
  githubDialogVisible.value = false;
  gitlabDialogVisible.value = false;
  this.getList();
}

// 绑定github 
function openGithubBing(activity) {
  console.log('activity = ' + activity);
}

getList();

</script>
