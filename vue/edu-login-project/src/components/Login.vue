<template>
  <div id="Login">
    <body id="poster">

      <el-form label-width="0px" label-position="left"
                class="login-container">
        <h3 class="login_title">系统登录</h3>
          <el-form-item>
            <el-input type="text" v-model="loginform.username"
                      auto-complete="off" placeholder="用户名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input type="password" v-model="loginform.password"
                      auto-complete="off" placeholder="密码"></el-input>
          </el-form-item>
          <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="submit" id="loginBtn">登录</el-button>
          </el-form-item>


          <router-link to="/Signin">
            <a>注册</a>
          </router-link>
<!--          <span style="margin-right: 100px"></span>-->
          <router-link to="/forgetPassword">
<!--            <a>忘记密码?</a>-->
          </router-link>
      </el-form>

    </body>

  </div>
</template>

<script>
    export default {
        name: "Login",
        data(){
            return{
                loginform:{
                    username:'',
                    password:''
                }
            }
            // responseResult:  []
        },
        methods:{
            submit(){
                this.$axios.post('/login',{
                    username: this.loginform.username,
                    password: this.loginform.password
                })
                    .then(successResponse => {
                    if (successResponse.data.code === 200){
                        alert('登录成功')
                        this.$store.commit('setUser',{
                            username:this.loginform.username,
                            password: this.loginform.password
                        })
                        // this.$router.replace({path: '/index'})
                        document.location.href = 'http://localhost:3333/'
                        // this.replace('http:/192.168.56.1:3333/')
                    }else {
                        alert('账号或密码错误')
                    }
                }).catch(failResponse =>{
                    alert('登录失败')
                })
            }
        }
    }
</script>


<style scoped>
  .login-container{
    width: 350px;
    margin: 90px auto;
    background-clip: padding-box;
    padding: 35px 35px 15px 35px;
    background-color: #fff;
    border: 1px solid #eaeaea;
    border-radius: 15px;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title{
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  #poster{
    background: url("../assets/loginBackground.jpg");
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0px;
  }
</style>
