import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex);

//创建一个store仓库
const store = new Vuex.Store({
    //state用来存放数据的对象
    state: {  

    },
    mutations:{

    },
    //应用mutations
    actions:{
 
    },
    //获取属性的状态
    getters:{
        title:state=>state.title,
    }
})

export default store;