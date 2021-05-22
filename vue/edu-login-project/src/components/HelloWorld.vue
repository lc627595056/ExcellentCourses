<template>
  <div class="hello">
    <router-link to="Login">
      <el-button v-model="goLogin" type="primary">登录</el-button>
    </router-link>
    <router-link to="Signin">
    <el-button v-model="goSignin" type="primary">注册</el-button>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
        goLogin:'',
        goSignin:''
    }
  },
    methods:{
        gotoLogin(){
            alert('df')
            this.$route.path('/Login')
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
