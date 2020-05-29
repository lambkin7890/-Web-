import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';//样式文件一定要引入
import axios from 'axios'
import "bootstrap3/dist/css/bootstrap.css"
import "bootstrap3/dist/js/bootstrap"
import store from './store';//仓库
import VModal from 'vue-js-modal'

//配置请求的根路径
axios.defaults.baseURL = 'http://localhost:8087/'

//关于跨域_：_在实际开发过程中,发现跨域问题并不是那么好解决的 因为Springboot安全控制框架使用了Securtiy,
//它的身份认证基于 JSESSIONID 而axios框架默认是不发送cookie的,因此需要在axios配置中添加
axios.defaults.withCredentials = true
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8'

Vue.prototype.$http = axios
Vue.config.productionTip = false


//全局注册自定义指令，用于判断当前图片是否能够加载成功，可以加载成功则赋值为img的src属性，否则使用默认图片
Vue.directive('real-img', async function (el, binding) {//指令名称为：real-img
  let imgURL = binding.value;//获取图片地址
  if (imgURL) {
      let exist = await imageIsExist(imgURL);
      if (exist) {
          el.setAttribute('src', imgURL);
      } 
  }
})

/**
* 检测图片是否存在
* @param url
*/
let imageIsExist = function(url) {
  return new Promise((resolve) => {
      var img = new Image();
      img.onload = function () {
          if (this.complete == true){
              resolve(true);
              img = null;
          }
      }
      img.onerror = function () {
          resolve(false);
          img = null;
      }
      img.src = url;
  })
}


Vue.use(ElementUI);
Vue.use(VModal,{
  dialog: true,
  dynamic: true
});
router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()

});




new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
