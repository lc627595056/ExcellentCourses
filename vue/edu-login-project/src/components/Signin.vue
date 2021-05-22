<template>
    <div id="container">
      <body id="poster">
      <el-form class="sign-container">
        <h3 style="margin-top: 0px">用户注册</h3>
        <el-form-item>
          <el-input type="text" v-model="sign.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="password" v-model="sign.password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="password" v-model="sign.rePassword" placeholder="确认密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="onSign" type="primary" style="margin-right: 60px">注册</el-button>

          <router-link to="/">
            <el-button >返回</el-button>
          </router-link>
        </el-form-item>

      </el-form>
      </body>
    </div>
</template>

<script>
    export default {
        name: "Signin",
        data(){
            return{
                sign:{
                    username:'',
                    password:'',
                    rePassword:''
                }



            }
        },
        methods:{
            onSign(){
                if (this.sign.password != this.sign.rePassword){
                    this.$alert('密码不一致')
                    this.sign.rePassword = ''
                }else {
                    this.$axios.post('/signIn',{
                        username: this.sign.username,
                        password: this.sign.password
                    }).then(response => {
                        if (response.data.code == '200'){
                            alert(response.data.massage)
                            console.log(response)
                            this.$router.replace({path:'/Login'})
                        }else if(response.data.code == '401'){
                            this.$alert(response.data.massage)
                        }
                    })
                }

            },
        }
    }
</script>

<style scoped>
  .sign-container{
    width: 300px;
    margin: 30px auto;
    padding: 30px 30px 30px 30px;
    border: 1px solid #cac6c6;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    background-color: #fff;
  }

  #poster{
    background: url("../assets/loginBackground.jpg");
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
</style>
