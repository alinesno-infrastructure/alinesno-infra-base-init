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
          <div class="acp-plugins-item-checkbox" @click="toggleSelection(item)" :class="{ 'selected': isSelected(item) }"></div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>

import { ref, provide } from 'vue';

const selectPluginItemList = ref([
  { id: '1', title: '项目生成器服务', icon: '', desc: 'AIP关系型数据库RDS（Relational Database Service）是一种稳定可靠.' },
  { id: '2', title: '权限资源引擎服务', icon: '', desc: '高可靠双机热备架构及可无缝扩展的集群架构，满足高读写性能场景及容量需。' },
  { id: '3', title: '数据资产服务', icon: '', desc: '开源产品，一个易于使用的高性能微服务分布式事务解决方案.' },
  { id: '4', title: '数据集成服务', icon: '', desc: '通过Nacos实现配置管理：支持分布式系统中的外部化配置、配置更改时自动刷新功能' },
  { id: '5', title: '数据集成服务', icon: '', desc: '通过Nacos实现配置管理：支持分布式系统中的外部化配置、配置更改时自动刷新功能' },
  { id: '6', title: '实时数据服务(Flink) ', icon: '', desc: '一款开源的分布式消息系统，基于高可用分布式集群技术，提供低延时的.' },
  { id: '7', title: '数据网关服务', icon: '', desc: 'AIP关系型数据库RDS（Relational Database Service）可弹性伸缩的在线数据库。' },
  { id: '8', title: '数据资产服务', icon: '', desc: '开源产品，一个易于使用的高性能微服务分布式事务解决方案.' },
  { id: '9', title: '数据集成服务', icon: '', desc: '通过Nacos实现配置管理：支持分布式系统中的外部化配置、配置更改时自动刷新功能' },
])

const selectedItems = ref([]);

const toggleSelection = (item) => {
  const index = selectedItems.value.findIndex((selectedItem) => selectedItem.id === item.id);
  if (index === -1) {
    selectedItems.value.push(item);
  } else {
    selectedItems.value.splice(index, 1);
  }
};

const isSelected = (item) => {
  return selectedItems.value.some((selectedItem) => selectedItem.id === item.id);
};

provide('selectedItems', selectedItems);

</script>

<style lang="scss" scoped>
.acp-plugins-item-checkbox {
  width: 20px;
  height: 20px;
  border: 2px solid #333;
  border-radius: 50%;
  cursor: pointer;
}

.acp-plugins-item-checkbox.selected {
  background-color: #3b5998;
}
</style>