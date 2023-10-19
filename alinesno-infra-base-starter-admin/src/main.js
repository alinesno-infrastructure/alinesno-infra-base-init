import { createApp } from 'vue'

import Cookies from 'js-cookie'

import ElementPlus from 'element-plus'
import locale from 'element-plus/lib/locale/lang/zh-cn' // 中文语言

import 'alinesno-infra-ui/assets/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import directive from 'alinesno-infra-ui/directive' // directive

// 注册指令
import plugins from 'alinesno-infra-ui/plugins' // plugins
import { download } from 'alinesno-infra-ui/utils/request'

// svg图标
import 'virtual:svg-icons-register'
import SvgIcon from 'alinesno-infra-ui/components/SvgIcon'
import elementIcons from 'alinesno-infra-ui/components/SvgIcon/svgicon'

import './permission' // permission control

import { useDict } from 'alinesno-infra-ui/utils/dict'
import { parseTime, resetForm, addDateRange, handleTree, selectDictLabel, selectDictLabels } from 'alinesno-infra-ui/utils/ruoyi'

// 分页组件
import Pagination from 'alinesno-infra-ui/components/Pagination'
// 自定义表格工具组件
import RightToolbar from 'alinesno-infra-ui/components/RightToolbar'
// 富文本组件
import Editor from "alinesno-infra-ui/components/Editor"
// 文件上传组件
import FileUpload from "alinesno-infra-ui/components/FileUpload"
// 图片上传组件
import ImageUpload from "alinesno-infra-ui/components/ImageUpload"
// 图片预览组件
import ImagePreview from "alinesno-infra-ui/components/ImagePreview"
// 自定义树选择组件
import TreeSelect from 'alinesno-infra-ui/components/TreeSelect'
// 字典标签组件
import DictTag from 'alinesno-infra-ui/components/DictTag'

// 引用全局变量文件
import GLOBAL_VAR from 'alinesno-infra-ui/api/global_variable.js'
import GLOBAL_FUN from 'alinesno-infra-ui/api/global_function.js'

const app = createApp(App)

app.config.globalProperties.GLOBAL_VAR = GLOBAL_VAR
app.config.globalProperties.GLOBAL_FUN = GLOBAL_FUN

// 全局方法挂载
app.config.globalProperties.useDict = useDict
app.config.globalProperties.download = download
app.config.globalProperties.parseTime = parseTime
app.config.globalProperties.resetForm = resetForm
app.config.globalProperties.handleTree = handleTree
app.config.globalProperties.addDateRange = addDateRange
app.config.globalProperties.selectDictLabel = selectDictLabel
app.config.globalProperties.selectDictLabels = selectDictLabels


// 全局组件挂载
app.component('DictTag', DictTag)
app.component('Pagination', Pagination)
app.component('TreeSelect', TreeSelect)
app.component('FileUpload', FileUpload)
app.component('ImageUpload', ImageUpload)
app.component('ImagePreview', ImagePreview)
app.component('RightToolbar', RightToolbar)
app.component('Editor', Editor)

app.use(router)
app.use(store)
app.use(plugins)
app.use(elementIcons)
app.component('svg-icon', SvgIcon)

directive(app)

// 使用element-plus 并且设置全局的大小
app.use(ElementPlus, {
  locale: locale,
  // 支持 large、default、small
  size: Cookies.get('size') || 'default'
})

app.mount('#app')
