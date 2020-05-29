import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Index from '../views/Index.vue'
import Register from '../components/Register.vue'
import Detail from '../components/Detail.vue'
import SubmitOrder from '../components/SubmitOrder.vue'
import Pay from '../components/Pay.vue'
import HomeAccount from '../components/HomeAccount.vue'
import LongIndex from '../views/LongIndex.vue'
import LongRentDetail from '../views/LongRentDetail.vue'
import BackLogin from '../views/back/login.vue'
import BackLandlordHome from '../views/back/landlordIndex.vue'
import BackAdminHome from '../views/back/adminIndex.vue'
import About from '../views/about.vue'
import BookSuccess from '../views/bookSuccess.vue'
import OrderDetail from '../views/orderDetail.vue'

Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    name:'index',
    component: Index,
    meta: {
      title: '在线租房平台-首页'
    }
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {
      title: '用户登录'
    }
  },
  {
    path:'/register',
    name:'register',
    component:Register,
    meta: {
      title: '用户注册'
    }
  },
  {
    path:'/detail/:id',
    name:'detail',
    component: Detail,
    meta: {
      title: '详情页'
    }
  },
  {
    path:'/submitOrder',
    name:'submitOrder',
    component:SubmitOrder,
    meta: {
      title: '下单页'
    }
  },
  {
    path:'/pay',
    name:'pay',
    component:Pay,
    meta: {
      title: '订单-支付订金'
    }
  },
  {
    path:'/homeAccount',
    name:'homeAccount',
    component:HomeAccount,
    meta: {
      title: '房客-个人中心'
    }
  },
  {
    path:'/long_rent_index',
    name:'longIndex',
    component:LongIndex,
    meta: {
      title: '在线租房平台-长租首页'
    }
  },
  {
    path:'/long-rent-detail/:id',
    name:'longRentDetail',
    component:LongRentDetail,
    meta: {
      title: '详情页-长租'
    }
  },
  {
    path:'/back/login',
    name:'backLogin',
    component:BackLogin,
    meta: {
      title: '后台管理系统-账户登录'
    }
  },
  {
    path:'/back/home/landlord',
    name:'backLandlordHome',
    component:BackLandlordHome,
    meta: {
      title: '后台管理系统-首页'
    }
  },
  {
    path:'/back/home',
    name:'backAdminHome',
    component:BackAdminHome,
    meta: {
      title: '后台管理系统-首页'
    }
  },
  {
    path:'/about',
    name:'about',
    component:About,
    meta: {
      title: '在线租房平台-关于'
    }
  },
  {
    path:'/book_success',
    name:'book_success',
    component:BookSuccess,
    meta: {
      title: '订单-支付成功'
    }
  },
  {
    path:'/order_detail',
    name:'orderDetail',
    component:OrderDetail,
    meta: {
      title: '订单-详情'
    }
  }
]

const router = new VueRouter({
  // mode:'history',
  routes
})


export default router
