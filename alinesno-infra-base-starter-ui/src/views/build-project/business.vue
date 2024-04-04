<template>
  <div class="app-container">

    <div class="form-container">

      <el-row>
        <el-col :span="11">

          <div class="acp-compoent-title">
            组件信息
          </div>

          <el-form :model="form" :rules="rules" :label-position="labelPosition" v-loading="loading" ref="genRef"
            label-width="180px" class="demo-ruleForm">
            <el-form-item label="服务名称" prop="projectName">
              <el-input v-model="form.projectName" placeholder="请输入服务名称" />
            </el-form-item>
            <el-form-item label="生成类型" prop="serviceType">
              <el-radio-group v-model="form.serviceType">
                <el-radio label="all">前后端</el-radio>
                <el-radio label="service">后端</el-radio>
                <el-radio label="ui">前端</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-divider content-position="left" style="width:90%">工程信息</el-divider>

            <el-form-item label="工程标识(artifactId)" prop="artifactId">
              <el-input v-model="form.artifactId" placeholder="请输入企业名称" />
            </el-form-item>
            <el-form-item label="工程组标识(groupId)" prop="groupId">
              <el-input v-model="form.groupId" placeholder="请输入企业名称" />
            </el-form-item>

            <el-divider content-position="left" style="width:90%">集成能力</el-divider>

            <el-form-item label="上传创建" prop="gitId">
              <el-select v-model="form.gitId" style="width:100%" placeholder="选择你的仓库模型">
                <el-option label="个人Github仓库" value="shanghai" />
                <el-option label="个人Gitee仓库" value="beijing" />
                <el-option label="企业Gitlab仓库" value="beijing" />
              </el-select>
            </el-form-item>

            <el-form-item label="发布模型" prop="deployType">
              <el-radio-group v-model="form.deployType">
                <el-radio label="Kubernates" />
              </el-radio-group>
            </el-form-item>

            <el-form-item label="领域模型" prop="projectGenType">
              <el-select v-model="form.projectGenType" style="width:100%" placeholder="选择你的领域模型">
                <el-option label="普通MVC工程(适合插件)" value="shanghai" />
                <el-option label="面向领域服务工程" value="beijing" />
              </el-select>
            </el-form-item>

            <el-form-item label="示例集成" prop="generatorDemo">
              <el-switch v-model="form.generatorDemo" />
            </el-form-item>

            <!-- 
            <el-form-item label="集成数据治理">
              <el-switch v-model="form.delivery" />
            </el-form-item> 
            -->

          </el-form>
        </el-col>
        <el-col :span="13">
          <BusinessFunctionModel />
        </el-col>
      </el-row>

      <div style="text-align: center;width: 100%;margin-top:40px;border-top: 1px solid #f5f5f5;padding-top:30px">
        <div>
          <el-button type="primary" @click="submitForm('form')">
            <i class="fa-solid fa-pen-nib" style="margin-right:10px"></i>  创建服务 
          </el-button>
          <el-button type="info" @click="submitForm('form')">
            重置
          </el-button>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>

import BusinessFunctionModel from './businessPlugins.vue';
import {downLoadZip} from "@/utils/zipdownload";

import {
  generatorSeed,
} from "@/api/base/starter/project";
import {reactive , inject} from "vue";

const { proxy } = getCurrentInstance();
const loading = ref(false);
const labelPosition = ref('right');

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    ApplicationName: undefined,
    applicationName: undefined,
    showName: undefined,
    status: undefined,
    deptId: undefined
  },
  rules: {
    projectName: [{required: true, message: "服务名称不能为空", trigger: "blur"}],
    serviceType: [{required: true, message: "生成类型不能为空", trigger: "blur"}],
    artifactId: [{required: true, message: "工程标识不能为空", trigger: "blur"}],
    groupId: [{required: true, message: "工程组不能为空", trigger: "blur"}],
    deployType: [{required: true, message: "发布模型不能为空", trigger: "blur"}],
    projectGenType: [{required: true, message: "领域模型不能为空", trigger: "blur"}],
    generatorDemo: [{required: true, message: "集成示例不能为空", trigger: "blur"}],
  }
});

const {queryParams, form, rules} = toRefs(data);
const selectedItems = inject('selectedItems', null);

/** 提交按钮 */
function submitForm() {
  proxy.$refs["genRef"].validate(valid => {
    if (valid) {
      loading.value = true ;

      const queryParams = new URLSearchParams();

      queryParams.append('projectName', this.form.projectName)
      queryParams.append('artifactId', this.form.artifactId)
      queryParams.append('serviceType', this.form.serviceType)
      queryParams.append('groupId', this.form.groupId)
      queryParams.append('gitId', this.form.gitId)
      queryParams.append('generatorDemo', this.form.generatorDemo)
      queryParams.append('deployType', this.form.deployType)
      queryParams.append('projectGenType', this.form.projectGenType)

      console.log('selectedItems = ' + selectedItems) ;

      const url = '/api/infra/base/starter/seed/generatorSeed?' + queryParams.toString();
      downLoadZip(url);

      loading.value = false ;
    }
  });
};


</script>

<style scoped lang="scss">
.form-container {
  max-width: 95%;
  margin-left: auto;
  margin-right: auto;
}

.acp-group-header {
  text-align: center;
  margin-top: 0px;
  margin-bottom: 40px;

  .group-title {
    font-size: 25px;
    font-weight: bold;
    color: #3b5998;
    padding: 10px;
  }

}

.acp-compoent-title {
  font-size: 17px;
  margin-bottom: 20px;
  font-weight: bold;
  background-color: #fafafa;
  padding: 16px 20px;
  border-bottom: 1px solid #eaeded;
}
</style>


