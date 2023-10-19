<template>

  <div class="app-container">

      <!-- 模板选择 -->
      <div class="template-header">
          <div class="vc-div div_l14lqa17 top-container" v-loading="loadingFilter">
              <div class="vc-div div_l14lqa16">
                  <div style="padding-bottom: 20px;font-weight: 550; border-bottom: var(--card-border-width, 1px) var(--card-border-style, solid) var(--card-border-color, #e3e4e6);margin-bottom: 20px;">
                      <i class="el-icon-link"></i> 应用模板中心
                      <span style="font-size:13px;color:#a5a5a5">
                          应用模板中心会自动上传到你的项目git基线
                      </span>
                      <div style="float:right">
                          <el-button type="primary" plain icon="el-icon-refresh" @click="syncTemplates()" size="mini">同步</el-button>
                      </div>
                  </div>
                  <div class="vc-div div_l14lqa15">
                      <div class="vc-div div_l14lqa0k" style="margin-bottom:10px" v-for="(item, index) in filterRules"
                          :key="index">
                          <div class="vc-div div_l14lqa0h">
                              <div class="vc-div div_l14lqa0e tagMain">
                                  <div class="vc-text" title="">
                                      {{ item.name }}
                                  </div>
                              </div>
                              <div class="vc-div div_l14lqa0g">
                                  <div class="vc-div div_l14lqa0f tagNormal" v-for="(i, ind) in item.items"
                                      :key="ind">
                                      <div class="vc-text" title="">
                                          {{ i.codeName }}
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>

                  </div>
              </div>
          </div>
      </div>

      <div class="gen-template-box" v-if="typeList.length == 0">
          <el-col :sm="24">
              <el-empty description="还没有创建模板,可以根据提示链接创建自己的工程模板">
                  <el-link type="primary" icon="el-icon-link">如何创建工程模板?</el-link>
              </el-empty>
          </el-col>
      </div>

      <!-- 模板内容 -->
      <div class="vc-div div_l14lqa1k tpl-container" v-loading="loading || loadingFilter">

          <div class="vc-div div_l14lqa1j tpl-item" v-for="(item, index) in typeList" :key="index">
              <div class="vc-div div_l14lqa19 tpl-item-image">
                  <img class="vc-image--yida image_l14lqa18"
                      :src="require('@/asserts/images/template/demo-template.png')" alt="Image 404" title=""
                      style="height: 90px; object-fit: cover; border-radius: 0px;">
              </div>
              <div class="vc-div div_l14lqa1i" data-spm-anchor-id="0.0.0.i3.72032480dZzISR">
                  <div class="vc-div div_l14lqa1c tpl-item-title">
                      <div class="vc-text text_l14lqa1a" title=""
                          style="display: -webkit-box; -webkit-box-orient: vertical; overflow: hidden; -webkit-line-clamp: 1;">
                          {{ item.tempName }}
                      </div>
                  </div>
                  <div class="vc-div tpl-item-description">
                      <div class="vc-text text_l14lqa1d" :title="item.tempDesc" style="display: -webkit-box; -webkit-box-orient: vertical; overflow: hidden; -webkit-line-clamp: 2;">
                          {{ item.tempDesc }}
                      </div>
                  </div>
                  <div class="vc-div div_l14lqa1f tpl-item-tags">
                      <div class="vc-text text_l14lqa1e" :class="item.fieldProp == 'dev'?'':'active'" @click="installTemplate(item)">
                         <i class="el-icon-link"></i> 安装模板
                      </div>
                  </div>
                  <div class="vc-div div_l14lqa1h tpl-item-footer">
                      <div class="vc-text text_l14lqa1g" :title="item.tempTeam">
                          来自 {{ item.tempTeam }}
                      </div>
                      <div class="vc-text" title="">
                          已启用 {{ item.installCount }} 
                      </div>
                  </div>
              </div>
          </div>

      </div>

      <el-dialog title="创建模板" v-model="open" width="1240">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="模板分类" prop="screen">
                  <el-row>
                      <el-col :span="8">
                          <el-select style="width:100%" v-model="ruleForm.screen" clearable placeholder="请选择场景">
                              <el-option :label="item.codeName" :value="item.codeValue" v-for="(item , index) in filterRules[0].items" :key="index" ></el-option>
                          </el-select>
                      </el-col>
                      <el-col :span="1" style="text-align:center">-</el-col>
                      <el-col :span="7">
                          <el-form-item prop="industry">
                              <el-select style="width:100%" v-model="ruleForm.industry" clearable placeholder="请选择行业">
                                  <el-option :label="item.codeName" :value="item.codeValue" v-for="(item , index) in filterRules[1].items" :key="index" ></el-option>
                              </el-select>
                          </el-form-item>
                      </el-col>
                      <el-col :span="1" style="text-align:center">-</el-col>
                      <el-col :span="7">
                          <el-form-item prop="type">
                              <el-select style="width:100%" v-model="ruleForm.type" clearable placeholder="请选择类型">
                                  <el-option :label="item.codeName" :value="item.codeValue" v-for="(item , index) in filterRules[2].items" :key="index" ></el-option>
                              </el-select>
                          </el-form-item>
                      </el-col>
                  </el-row>
              </el-form-item>
              <el-form-item label="模板名称" prop="tempName">
                  <el-col :span="12">
                      <el-input type="input" v-model="ruleForm.tempName" placeholder="模板名称" maxlength="64" show-word-limit></el-input>
                  </el-col>
              </el-form-item>

              <el-form-item label="团队信息" prop="tempTeam">
                  <el-col :span="12">
                      <el-input type="input" v-model="ruleForm.tempTeam" placeholder="团队信息"  maxlength="64" show-word-limit></el-input>
                  </el-col>
              </el-form-item>

              <el-form-item label="上传模板" prop="tempZip">
                  <el-upload class="upload-demo" action="https://jsonplaceholder.typicode.com/posts/"
                      list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                      <div slot="tip" class="el-upload__tip">只能上传zip文件，且不超过2048kb</div>
                  </el-upload>
              </el-form-item>

              <el-form-item label="模板描述" prop="tempDesc">
                  <el-input type="input" v-model="ruleForm.tempDesc" placeholder="模板描述"  maxlength="128" show-word-limit></el-input>
              </el-form-item>

          </el-form>
          <span slot="footer" class="dialog-footer">
              <el-button size="medium" @click="reset()">重 置</el-button>
              <el-button size="medium" type="primary" @click="submitForm">确 定</el-button>
          </span>
      </el-dialog>

      <!-- 集成工程示例 -->
      <el-dialog title="集成工程" v-model="openProject" width="1240">
          <project-template :templateId="currentTemplateId"></project-template>
          <br/>
          <br/>
          <br/>
      </el-dialog>

  </div>

</template>

<script setup>

const typeList = ref([]);
const openProject = ref(false);
const open = ref(false);

</script>

<style scoped lang="scss"> 

.gen-template-box {
  width: 100%;
  text-align: center;
  float: left;
  margin-top: 100px;
}

.vc-text.text_l14lqa1e.active {
  color: #fff;
  background: #005bd5;
}

</style>


