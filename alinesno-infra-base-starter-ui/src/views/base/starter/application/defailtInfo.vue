<template>

  <div  class="tag-welcome" data-spm-anchor-id="5176.12883413.0.i0.58ae4a9cBPnlnH">
      <div class="nav-bar">
          <div  class="nav-bar-content">
              <div class="project-header">
                  <img src="http://data.linesno.com/icons/sepcialist/dataset_6.png" style="width: 50px;" />
                  <h2  class="nav-bar-title"> 数字化管理服务平台 {{ title }} </h2>
              </div>
              <p  class="nav-bar-desc">
                    通过 alinesno-init 脚手架生成的项目代码，能帮开发者准备好开发的前期工作，让开发更加迅速，不用在前期配置上花费时间，
                    降低框架学习成本，让开发者更加专注于业务逻辑开发，通过本页面提供的项目信息快速进入开发
              </p>
              <el-button @click="()=>callback()" type="text" class="fas fa-file-word" size="medium" tabindex="0"> 使用说明 </el-button>
          </div>
      </div>
  </div>
  
  </template>

  <script setup>
    const props = defineProps({
      title: String,
      likes: Number,
      isPublished: Boolean,
      commentIds: Array,
      author: Object,
      callback: Function,
      contactsPromise: Promise // 或者其他构造函数
    });
  </script>

  
  <style scoped lang="scss">
  
  .project-header{
    img{
        width: 50px;
        position: absolute;
        border-radius: 12px;
    }
  }
  
  // .tag-welcome {
  //     min-width: 860px;
  //     margin-bottom: 10px;
  //     margin-top: 20px;
  // }
  
  .nav-bar {
      display: flex;
  }
  
  .nav-bar-image {
      width: 185px;
      height: 136px;
      background-size: 100%;
  }
  
  .nav-bar-content {
      margin-left: 20px;
  }
  
  .nav-bar-desc {
      font-size: 14px;
      color: #333;
      text-align: left;
      line-height: 25px;
  }
  
  .nav-bar-button {
      background: #005bd4;
      cursor: pointer;
      width: 104px;
      height: 32px;
      display: block;
      border: none;
      outline: 0;
      color: #fff;
      margin-top: 0px;
      float: left;
      border-radius: 4px;
  }
  
  .nav-bar-title {
      font-size: 25px;
      color: #111;
      text-align: left;
      padding-left: 70px;
      line-height: 50px;
      width: 100%;
      height: 36px;
      margin-bottom: 20px;
      font-weight: normal;
  }
  
  .show-link-txt {
      font-size: 12px;
      line-height: 35px;
      margin-left: 20px;
  }
  
  </style>
  
  
  