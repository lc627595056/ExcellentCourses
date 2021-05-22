const state = {
  user:{
    username:'',
    password:''
  }
}

const getters = {
  getUser(){
    return state.user
  }
}

const actions = {}

const mutations = {
  setUser(state,user){
    state.user.username = user.username;
    state.user.password = user.password;
    console.log(state.user)
  }
}

export default {
  namespace:true,
  state,
  getters,
  actions,
  mutations
}
