<template>
    <div class="centerCon">
        <ul class="centerTab">
            <li @click="activeIndex=0" :class="{active:activeIndex===0}">个人信息</li>
            <li @click="activeIndex=1" :class="{active:activeIndex===1}">密码设置</li>
        </ul>
        <div class="accountMt clearfix">
            <AccountMt v-show="activeIndex===0"></AccountMt>
            <SetPassword v-show="activeIndex===1"></SetPassword>       
        </div>
    </div>
</template>
<style scoped>  
.centerCon ul{
    margin-bottom:0px;
}
.centerTab{
    height: 39px;
    border-bottom: #999 solid 1px;
}
.centerTab li.active{
    height: 39px;
    font-weight: bold;
}
.centerTab li{
    height: 38px;
    line-height: 38px;
    text-align: center;
    border: #999 solid 1px;
    border-bottom: none;
    cursor: pointer;
    float: left;
    width: 98px;
    margin-right: 10px;
    background: #fff;
}
.accountMt{
    border: #999 solid 1px;
    border-top: none;
}
.MtList{
    padding: 20px;
    display: none;
}

</style>
<script>
    import AccountMt from "./account-manage/accountMt.vue";
    import SetPassword from "./account-manage/setPassword.vue";
    export default {
        name:"AccountManage",
        data(){
            return{
                activeIndex:0
            }
        },
        components:{
            AccountMt,
            SetPassword
        },
    }
</script>