<template>
  <div class="acp-component-container">
    <div class="acp-compoent-title">
      已集成能力
      <div class="componet-add-plugins"><i class="fa-solid fa-pen-nib"></i> 添加能力</div>
    </div>
    <div class="acp-component-plugins-panel">
      <ul>
        <li class="acp-plugins-item" v-for="item in selectPluginItemList" :key="item">
          <div class="acp-plugins-item-title">{{ item.title }}</div>
          <div class="acp-plugins-item-desc">{{ item.desc }}</div>
          <div class="acp-plugins-item-checkbox"></div> 
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>

const selectPluginItemList = ref([
  { id: '14', title: '审计日志服务', icon: '', desc: '通过nacos实现的服务发现平台.' },
  { id: '15', title: '配置管理服务', icon: '', desc: '开源产品，一个易于使用的高性能微服务分布式事务解决方案.' },
  { id: '16', title: '自动化操作服务', icon: '', desc: '通过Nacos实现配置管理：支持分布式系统中的外部化配置、配置更改时自动刷新功能' },
  { id: '17', title: '一体化安全触感服务 ', icon: '', desc: '一款开源的分布式消息系统，基于高可用分布式集群技术，提供低延时的、高可靠的消息发布与订阅服务.' },
  { id: '15', title: '配置管理服务', icon: '', desc: '开源产品，一个易于使用的高性能微服务分布式事务解决方案.' },
])

</script>

<style lang="scss" scoped>
.acp-component-container {
  font-size: 14px;
  padding-left: 30px;

  .acp-compoent-title {
    font-size: 17px;
    margin-bottom: 20px;
    font-weight: bold;
    background-color: #fafafa;
    padding: 16px 20px;
    border-bottom: 1px solid #eaeded;

    .componet-add-plugins {
    float: right;
    color: #3b5998;
    font-size: 14px;
    font-weight: 550;
}
  }

  ul {
    margin: 0px;
    padding: 0px;

    li {
      list-style: none;
    }
  }

  .acp-component-plugins-panel {

    li.acp-plugins-item {
      margin-bottom: 15px;
      position: relative;
      background: #fafafa;
      padding: 15px;
      height: 100px;
      overflow: hidden;
      width: calc(50% - 10px);
      float: left;
      margin-right: 5px;
      margin-left: 5px;
      padding-right: 70px;
      line-height: 30px;
      border-radius: 2px;

      .acp-plugins-item-title {
        font-size: 15px;
        font-weight: 500;
        margin-bottom: 5px;
      }

      .acp-plugins-item-desc {
        font-size: 12px;
        color: #545b64;
        line-height: 1.2rem;
      }

      .acp-plugins-item-checkbox {
        position: absolute;
    display: block;
    border: 2px solid #e4e4e4;
    height: 24px;
    width: 24px;
    margin-top: -12px;
    top: 50%;
    right: 16px;
    border-radius: 14px;
      }

    }
  }

}
</style>