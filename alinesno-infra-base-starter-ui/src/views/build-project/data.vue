<template>
  <div class="app-container">

    <div class="acp-group-header">
      <div class="group-title">集成数据服务组件</div>
      <div class="group-body">集成数据服务治理能力和管理，包括实时计算和数据抽取能力</div>
    </div>

    <div class="form-container">

      <el-row>
        <el-col :span="11">
          <DataProjectItem />
        </el-col>
        <el-col :span="13">
          <DataPlugins />
        </el-col>
      </el-row>

    </div>
  </div>
</template>


<script setup>

import DataProjectItem from './dataProject.vue'
import DataPlugins from './dataPlugins.vue'

const labelPosition = ref('right')

const form = ref({
  name: null,
  logo: null,
  enDesc: null,
  creditCode: null,
});

</script>

<style scoped lang="scss">
.form-container {
  max-width: 95%;
  margin-left: auto;
  margin-right: auto;
  margin-top: 20px;
}

.acp-group-header {
  text-align: center;
  margin-top: 20px;
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


