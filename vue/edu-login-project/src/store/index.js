import Vue from 'vue'
import Vuex from 'vuex'
import userVuex from "../router/userVuex";

Vue.use(Vuex)


export default new Vuex.Store({
  modules:{
    userVuex
  }
})

