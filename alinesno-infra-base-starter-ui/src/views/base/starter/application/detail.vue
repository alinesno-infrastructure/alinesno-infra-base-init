<template>
  <div class="home">
    <div class="app-container">
      <overview-welcome :callback="() =>
          goTarget(
            'http://alinesno-platform.linesno.com/technique/13_%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90%E5%99%A8/'
          )
        " :title="application.projectName" />

      <el-row :gutter="20" style="margin-bottom: 0px;">
        <el-col :span="24">
          <div class="WidgetHomeProductMy-detailContainer-3W8nd">
            <h3 class="h3-title">
              集成服务能力
            </h3>
            <div style="position: relative;overflow: hidden;width: 100%;">
              <ul class="product_list thm-uglier">
                <li class="product_list_li" style="width:calc(25% - 10px) !important ;padding:8px !important"
                  v-for="(item, index) in interList" :key="index" @click="() => item.nav()">
                  <div class="text-icons"
                    style="font-size:20px;width:40px;height:40px;background: #3b5998;box-shadow: 0 4px 12px -2px rgb(59 89 152);">
                    <i :class="item.icon"></i>
                  </div>
                  <div class="text-title">
                    <a style="line-height: 26px;font-size: 15px;">
                      {{ item.name }}
                    </a>
                    <div class="text-descript">
                      {{ item.desc }}
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </el-col>
      </el-row>

      <div class="kkmdu5-3 kMWWmc">
        <div class="show-tip">打开以下链接进入项目代码仓库，进行版本控制和更改吧！</div>
        <pre
          class="kkmdu5-0 eJlKYe"><code><a :href="application.gitProjectLink" target="_blank" style="color:#005bd5">{{ application.gitProjectLink }}</a></code></pre>
      </div>

      <div class="kkmdu5-3 kMWWmc">
        <div class="show-tip">工程构建，通过以下命令提示把代码获取到本地，开始编码</div>
        <pre class="kkmdu5-0 eJlKYe" style="height:180px">
          <span class="commond-label"># 下载工程/安装依赖</span>
          <code>git clone {{ application.gitProjectLink }}
cd {{ application.projectCode }} &amp;&amp; mvn clean package

# 运行
java -jar {{ application.projectCode }}-boot/target/{{ application.projectCode }}-boot.jar</code></pre>
        <pre class="kkmdu5-0 eJlKYe" style="height:105px">
          <span class="commond-label"># 前端工程构建</span>
          <code>cd {{ application.projectCode }}-ui 
yarn install && yarn run dev</code></pre>
      </div>

      <el-row :gutter="20">
        <el-col :span="24">
          <div class="WidgetHomeProductMy-detailContainer-3W8nd">
            <h3 class="h3-title">
              项目开发引导
            </h3>
            <div style="position: relative;overflow: hidden;width: 100%;height: 170px;">
              <ul class="product_list thm-uglier">
                <li class="product_list_li" style="width:calc(25% - 10px) !important ;padding:8px !important"
                  v-for="(item, index) in learnList" :key="index" @click="() => item.nav()">
                  <div class="text-icons"
                    style="font-size:20px;width:40px;height:40px;background: #3b5998;box-shadow: 0 4px 12px -2px rgb(59 89 152);">
                    <i :class="item.icon"></i>
                  </div>
                  <div class="text-title">
                    <a style="line-height: 26px;font-size: 15px;">
                      {{ item.name }}
                    </a>
                    <div class="text-descript">
                      {{ item.desc }}
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </el-col>
      </el-row>

    </div>
  </div>
</template>


<script setup>
import { ref } from 'vue';
import OverviewWelcome from "./defailtInfo.vue";

const cicdDialogVisible = ref(true);
const application = ref({
  projectName: '',
  gitProjectLink: 'https://gitee.com/alinesno-infrastructure/alinesno-infra-platform-press.git'
});
const interList = ref([
  {
    name: "监控服务",
    icon: "fa-solid fa-computer",
    desc: "学习配置并验证本地环境",
    recommend: true,
    nav: () =>
      this.goTarget(
        "http://alinesno-platform.linesno.com/technique/11_%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01_%E5%B9%B3%E5%8F%B0%E7%8E%AF%E5%A2%83%E8%A6%81%E6%B1%82.html"
      ),
  },
  {
    name: "实时数据",
    icon: "fab fa-css3-alt",
    desc: "掌握工程结构和开发方式",
    recommend: true,
    nav: () =>
      this.goTarget(
        "http://alinesno-platform.linesno.com/technique/03_%E9%A1%B9%E7%9B%AE%E8%A7%84%E8%8C%83/01_%E6%9C%8D%E5%8A%A1%E5%B7%A5%E7%A8%8B%E8%A7%84%E8%8C%83.html"
      ),
  },
  {
    name: "离线数据",
    icon: "fa-solid fa-link",
    desc: "使用平台的快速发布和技术",
    recommend: true,
    nav: () => this.goTarget(
      "https://alinesno-platform.linesno.com/technique/"
    ),
  },
  {
    name: "容器部署",
    icon: "fa-brands fa-docker",
    desc: "使用平台的快速发布和技术",
    recommend: true,
    nav: () => this.goTarget(
      "https://alinesno-platform.linesno.com/technique/"
    ),
  },
  {
    name: "自动运维",
    icon: "fas fa-drafting-compass",
    desc: "使用平台的快速发布和技术",
    recommend: true,
    nav: () => this.goTarget(
      "https://alinesno-platform.linesno.com/technique/"
    ),
  },
]);
const learnList = ref([
  {
    name: "搭建本地环境",
    icon: "fab fa-app-store-ios",
    desc: "学习配置并验证本地环境",
    recommend: true,
    nav: () =>
      this.goTarget(
        "http://alinesno-platform.linesno.com/technique/11_%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01_%E5%B9%B3%E5%8F%B0%E7%8E%AF%E5%A2%83%E8%A6%81%E6%B1%82.html"
      ),
  },
  {
    name: "代码工程规范",
    icon: "fab fa-css3-alt",
    desc: "掌握工程结构和开发方式",
    recommend: true,
    nav: () =>
      this.goTarget(
        "http://alinesno-platform.linesno.com/technique/03_%E9%A1%B9%E7%9B%AE%E8%A7%84%E8%8C%83/01_%E6%9C%8D%E5%8A%A1%E5%B7%A5%E7%A8%8B%E8%A7%84%E8%8C%83.html"
      ),
  },
  {
    name: "工程开发手册",
    icon: "fas fa-drafting-compass",
    desc: "使用平台的快速发布和技术",
    recommend: true,
    nav: () => this.goTarget(
      "https://alinesno-platform.linesno.com/technique/"
    ),
  },
  {
    name: "搭建本地环境",
    icon: "fab fa-app-store-ios",
    desc: "学习配置并验证本地环境",
    recommend: true,
    nav: () =>
      this.goTarget(
        "http://alinesno-platform.linesno.com/technique/11_%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01_%E5%B9%B3%E5%8F%B0%E7%8E%AF%E5%A2%83%E8%A6%81%E6%B1%82.html"
      ),
  },
  {
    name: "代码工程规范",
    icon: "fab fa-css3-alt",
    desc: "掌握工程结构和开发方式",
    recommend: true,
    nav: () =>
      this.goTarget(
        "http://alinesno-platform.linesno.com/technique/03_%E9%A1%B9%E7%9B%AE%E8%A7%84%E8%8C%83/01_%E6%9C%8D%E5%8A%A1%E5%B7%A5%E7%A8%8B%E8%A7%84%E8%8C%83.html"
      ),
  },
  {
    name: "搭建本地环境",
    icon: "fab fa-app-store-ios",
    desc: "学习配置并验证本地环境",
    recommend: true,
    nav: () =>
      this.goTarget(
        "http://alinesno-platform.linesno.com/technique/11_%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/01_%E5%B9%B3%E5%8F%B0%E7%8E%AF%E5%A2%83%E8%A6%81%E6%B1%82.html"
      ),
  },
]);

const directPath = (item) => {
  var projectId = $route.params.pId;
  console.log("item.path = " + item.path);
};

const initOverview = () => {
  var projectId = $route.params.pId;

  const loadingService = Loading.service({
    fullscreen: true,
    text: "正在获取生成信息.....",
  });

  applicationDetail(projectId)
    .then((response) => {
      loadingService.close();
      console.log("response = " + response);
      application.value = response.data;

      if (application.gitProjectLink) {
        clearInterval(timerId);
        loadingService.close();
      }
    })
    .catch(() => {
      loadingService.close();
    });
};

const goTarget = (href) => {
  window.open(href, "_blank");
};
</script>

<style scoped lang="scss">
.homeHeader {
  position: relative;
  box-sizing: border-box;
  padding: 24px 24px 0;
  background-color: #fff;
  box-shadow: 0 1px 0 0 #e3e4e6;
  z-index: 1;
}

.homeHeader h1 {
  margin: 0 0 8px;
  font-size: 16px;
  color: #333;
  line-height: 24px;
}

.app-container {
  background-color: #ffffff;
  max-width: 1240px;
  margin: auto;
}

.widget-title {
  font-size: 13px;
  color: #333;
  line-height: 24px;
  font-weight: 400;
  //    float: left;
  width: 100%;
}

.app-icon {
  width: 32px;
  height: 32px;
  position: relative;
}

.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 2px;
  background: #fff;
  padding: 20px !important;
  padding-left: 30px !important;
  padding-right: 10px !important;
}

.el-col-8 {
  width: calc(33.3333333333% - 10px);
  margin-left: 10px;
}

.widget-bulletin-list .item {
  height: 32px;
  line-height: 32px;
  white-space: nowrap;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  overflow: hidden;
  font-size: 12px;

  a {
    color: #555;
  }
}

.product_list_li {
  width: calc(33% - 10px) !important;
  padding: 15px !important;
  background: #f6f7f8;
  margin-right: 10px;
  border-radius: 10px;
  margin-top: 10px;

  .text-title {
    font-size: 18px;
    color: #333;
    letter-spacing: 0;
    line-height: 25px;
    float: left;
    font-weight: 550 !important;
  }

  .text-descript {
    opacity: 0.7;
    font-size: 12px;
    color: #333;
    letter-spacing: 0.37px;
    line-height: 20px;
  }
}

.widget-bulletin-list {
  .line {
    position: absolute;
    left: -16px;
    right: -16px;
    height: 1px;
    background-color: #e3e4e6;
  }

  .pin {
    position: relative;
    margin-bottom: 15px;
    margin-top: 15px;

    .msg {
      padding: 8px 12px;
      line-height: 18px;
      color: #333;
      font-size: 12px;
      background-color: #eff3f8;
      -webkit-border-radius: 2px;
      -moz-border-radius: 2px;
      border-radius: 2px;
      margin-bottom: 16px;
    }
  }
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.next-col-4 {
  -ms-flex: 0 0 16.66667%;
  -webkit-flex: 0 0 16.66667%;
  flex: 0 0 16.66667%;
  width: 16.66667%;
  max-width: 16.66667%;
  float: left;
}

.subtitle {
  font-weight: 500;
  font-size: 12px;
  color: #666;
  line-height: 20px;
  margin: 16px 0 8px;
}

.thm-uglier {
  list-style: none;
  margin: 0;
  padding: 0;
}

.product_list {
  a {
    display: flex;
    align-items: center;
    background-color: #f9f9f9;
    width: 100%;
    line-height: 36px;
    text-decoration: none;
    color: #333;
    transition: all 250ms linear;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
}

.product_list>li {
  padding: 0 8px 8px 0;
  overflow: hidden;
  flex: none;
  width: 25%;
  float: left;
  border-radius: 4px;
  font-size: 12px;

  a {

    i {
      display: none;
      border: 1px solid #dedede;
      border-radius: 14px;
      width: 28px;
      height: 28px;
      text-align: center;
    }
  }

  .WidgetHomeProductMy-product_name-TNtW6 {
    flex: 1;
    margin-left: 8px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
}

.h3-title {
  font-size: 17px;
  font-weight: 500;
  margin-top: 10px;
}

// li.product_list_li.count-li {
//   width: calc(50% - 16px) !important;
//   height: 140px;
//   border-right: 0px;
//   border-radius: 5px !important;
//   margin: 0px 0px 16px 16px !important;
//   margin-right: 10px;
//   margin-top: 10px;
// }

.count-text-button>button {
  padding: 0 16px;
  height: 32px;
  line-height: 30px;
  font-size: 12px;
  border-width: 1px;
  border-style: solid;
  background-color: #005bd4;
  border-color: transparent;
  margin-top: 20px;
  color: #fff;
  border-radius: 3px;
}

.el-aside {
  background: #f5f5f5 !important;
  margin-top: 12px !important;
  padding-top: 0px !important;
  padding-left: 0px;
  padding-right: 10px;
}

.rbac-data-card {
  .title {
    margin-top: 0px;
    margin-bottom: 10px;
    font-size: 16px;
    font-weight: 500;
    text-align: left;
  }
}

.count-text-button>button {
  padding: 0 16px;
  height: 32px;
  line-height: 30px;
  font-size: 12px;
  border-width: 1px;
  border-style: solid;
  background-color: #005bd4;
  border-color: transparent;
  margin-top: 20px;
  color: #fff;
  border-radius: 3px;
}

.kMWWmc {
  display: flex;
  flex-direction: column;
  -webkit-box-pack: center;
  justify-content: center;
  padding: 0px 20px;
  font-size: 13px;

  .eJlKYe {
    margin-bottom: 0.75rem;
    align-self: center;
    width: 100%;
    padding: 1rem 1.25rem;
    border-radius: 4px;
    background-color: #f7f9fa;
    color: #333;
    font-family: "SF Mono", "Roboto Mono", Menlo, monospace;
    font-size: 0.82rem;
    font-style: normal;
    font-weight: 400;
    line-height: 1.0;
  }

  .eJlKYe code {
    display: block;
  }
}

.h3-title {
  font-size: 17px;
  font-weight: 600;
  margin-top: 10px;
  margin-bottom: 10px;
  padding-left: 0px;
}

.text-icons {
  margin-right: 10px;
  margin-top: 5px;
  font-weight: 200;
  float: left;
  height: 50px;
  font-size: 25px;
  background: #ff7850;
  width: 50px;
  text-align: center;
  padding-top: 9px;
  border-radius: 3px;
  color: #fff;
  box-shadow: 0 4px 12px -2px rgb(255 120 80 / 40%);
}

pre code {
  padding: 0;
  font-size: inherit;
  color: inherit;
  white-space: pre-wrap;
  background-color: transparent;
  border-radius: 0;
  line-height: 26px;
  font-family: unset;
}

span.title-tips {
  font-size: 12px;
  border: 1px solid #f25643;
  font-weight: 400;
  padding: 0px 4px;
  border-radius: 3px;
  color: #f25643;
  position: absolute;
  margin-left: 5px;
  height: 24px;
  line-height: 24px;
}

span.commond-label {
  float: left;
}

.show-tip {
  padding: 10px 16px 10px 0px;
  font-weight: 500;
  font-size: 13px;
}
</style>
