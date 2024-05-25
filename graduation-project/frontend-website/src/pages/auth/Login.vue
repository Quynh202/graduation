<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <Auth class="relative" :checkLogin="checkLogin">
    <div
      class="wrap w-[260px] h-[350px] bg-[white] flex flex-col items-center justify-center gap-5"
      :class="checkLogin ? '' : 'opacity-40'"
    >
      <div class="w-full text-left">
        <h1 class="text-3xl  font-medium text-center "> X.Mart</h1>
        
      </div>

      <h3
        v-if="recover !== true"
        className="w-full bg-transparent border-2 border-x-cyan-50 block text-center m-0 hover:text-white"
        name="loginWithGoogle"
       
        > Hãy đăng nhập vào tài khoản của bạn</h3>
     
      <form action="" method="post" class="flex flex-col w-full gap-5 ">
        <Input v-model="data.username" name="username" placeholder="Tên tài khoản" class="bg-[#f3f3f3]" />
        <Input
          v-if="recover === false"
          v-model="data.password"
          type="password"
          name="password"
          placeholder="Mật khẩu"
          class="bg-[#f3f3f3]"
        />
        <Input
          v-if="recover === true"
          v-model="data.email"
          type="text"
          name="email"
          placeholder="Email"
        />
        <Input
          v-if="recover === true"
          v-model="data.recoverPassword"
          type="password"
          name="recoverPassword"
          placeholder="Recover password"
        />
        <div class="flex items-center justify-between ">
          
          <div :class="recover === true ? 'inline-block w-full text-right' : ''">
            <span @click="resetPassword" class="text-sm underline cursor-pointer">{{
              recover === true ? 'Đăng nhập ngay !' : 'Quên mật khẩu'
            }}</span>
          </div>
        </div>
        <Button
          type="button"
          @click="onSubmit"
          className="bg-[#ffb416] hover:bg-[#e2c17a] text-white w-full m-0"
          name="login"
          :text="recover === true ? 'Lấy lại mật khẩu' : 'Đăng nhập'"
        />
        <div class="block w-full text-center">
          <h5 class="text-xs italic">
            Bạn chưa có tài khoản?
            <span class="underline"><router-link to="/auth/signup">Đăng ký ngay</router-link></span>
          </h5>
        </div>
      </form>
    </div>
    <div
      class="absolute w-[400px] h-[200px] bg-white z-99 left-[40%] p-5 text-center rounded-md shadow-md flex flex-col items-center justify-between"
      :class="checkLogin ? 'hidden' : ''"
    >
      <div><vue-feather class="w-16 h-16 text-red-600" type="x-circle"></vue-feather></div>
      <div class="text-black">Tài khoản hoặc mật khẩu là không chính xác, vui lòng kiểm tra lại!</div>
      <button
        type="button"
        class="px-10 text-white transition-all bg-[#ffb416] hover:bg-[#dbba74] hover:opacity-90"
        @click="checkLogin = true"
      >
        OK
      </button>
    </div>
  </Auth>
</template>
<script setup>
import { ref, reactive } from 'vue'
// import AuthService from '@/stores/modules/AuthService'
import Auth from './Auth.vue'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import { useRouter } from 'vue-router'
import store from '@/stores/store'
const data = reactive({
  username: '',
  email: '',
  password: '',
  recoverPassword: '',
  status: ''
})
const user = reactive({
  username: '',
  password: ''
})
const checkLogin = ref(true)
const recover = ref(false)
const router = useRouter()
const resetPassword = () => {
  recover.value = !recover.value
}
function onSubmit() {
  const list = ['CUSTOMER', 'USER']
  // loading.value = true
  user.username = data.username
  user.password = data.password
  store.dispatch('auth/login', user).then(
    (currentUser) => {
      if (currentUser.response) {
        checkLogin.value = false
      } else {
        checkLogin.value = true
        if (currentUser.roles.includes('ADMIN')) {
          router.push('/admin')
        } else if (list.includes(currentUser.roles[0])) {
          router.push('/')
        }
      }
    },
    (error) => {
      checkLogin.value = false
      console.log(error)
    }
  )
}
</script>
<style lang="scss">
section {
  .image {
    img {
      background-position: center;
      background-repeat: no-repeat;
      background-size: cover;
      height: 100%;
      width: 100%;
      object-fit: cover;
    }
  }
}
</style>
