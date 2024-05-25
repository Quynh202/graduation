<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <Auth class="relative" :checkLogin="!success">
    <div
      class="wrap w-[260px] h-[340px] bg-[white] flex flex-col items-center justify-center  gap-2"
      :class="!success ? '' : 'opacity-40'"
    >
      <div class="w-full text-left">
        <h1 class="text-2xl  text-center">Tạo tài khoản mới</h1>
      </div>
      
      <form @submit.prevent="onSubmit" method="post" class="flex flex-col w-full">
      
        <div class=" ">
          <div class="flex flex-col   ">
            <input-component
              v-model="data.firstName"
              @keyup="onPressKey($event, 'firstName')"
              name="firstName"
              placeholder="Họ"
              :class="
                v$.firstName.$error && errors.firstName == true ? 'border-red-600' : 'border-white'
              "
               class="bg-[#f3f2f2]"
            />
            <span class="h-[10px] px-1 text-xs leading-4 text-red-600">
              {{
                v$.firstName.$error && errors.firstName == true
                  ? v$.firstName.$errors[0].$message
                  : ''
              }}
            </span>
          </div>
          <div class="flex flex-col ">
            <input-component
              v-model="data.lastName"
              @keyup="onPressKey($event, 'lastName')"
              name="lastName"
              placeholder="Tên"
              :class="
                v$.lastName.$error && errors.lastName == true
                  ? 'border-red-600'
                  : 'border-transparent'
              "
               class="bg-[#f3f2f2]"
            />
            <span class="h-[10px] px-1 text-xs leading-4 text-red-600">
              {{
                v$.lastName.$error && errors.lastName == true
                  ? v$.firstName.$errors[0].$message
                  : ''
              }}
            </span>
          </div>
        </div>
        
        

        <div class="flex flex-col w-full">
          <input-component
            @keyup="onPressKey($event, 'username')"
            v-model="data.username"
            name="username"
            placeholder="Tên tài khoản"
            :class="
              v$.username.$error && errors.username == true
                ? 'border-red-600'
                : 'border-transparent'
            "
            class="bg-[#f3f2f2]"
          />
          <span class="h-[10px] px-1 text-xs leading-4 text-red-600">
            {{
              v$.username.$error && errors.username == true ? v$.username.$errors[0].$message : ''
            }}
          </span>
        </div>
        <div class="flex flex-col w-full">
          <input-component
            v-model="data.email"
            type="email"
            name="email"
            placeholder="Email"
            @keyup="onPressKey($event, 'email')"
            :class="v$.email.$error && errors.email == true ? 'border-red-600' : 'border-white'"
            class="bg-[#f3f2f2]"
          />
          <span class="h-[10px] px-1 text-xs leading-4 text-red-600">
            {{ v$.email.$error && errors.email == true ? v$.email.$errors[0].$message : '' }}
          </span>
        </div>
        <div class="flex flex-col w-full">
          <input-component
            v-model="data.password"
            type="password"
            name="password"
            placeholder="Mật khẩu"
            @keyup="onPressKey($event, 'password')"
            :class="v$.password.$error ? 'border-red-600' : 'border-white'"
            class="bg-[#f3f2f2]"
          />
          <span class="h-[10px] px-1 text-xs leading-4 text-red-600">
            {{
              v$.password.$error && errors.password == true ? v$.password.$errors[0].$message : ''
            }}
          </span>
        </div>
        <div class="flex flex-col w-full">
          <input-component
            v-model="data.confirm"
            type="password"
            name="confirm"
            placeholder="Nhập lại mật khẩu"
            @keyup="onPressKey($event, 'confirm')"
            :class="v$.confirm.$error ? 'border-red-600' : 'border-white'"
            class="bg-[#f3f2f2]"
          />
          <span class="h-[10px] px-1 text-xs leading-4 text-red-600">
            {{ v$.confirm.$error && errors.confirm == true ? v$.confirm.$errors[0].$message : '' }}
          </span>
        </div>
        <button-component
          type="submit"
          className="bg-[#ffb416] text-white w-full m-0"
          name="signup"
          text="Tạo tài khoản"
        />
        <div class="relative w-full pb-2 text-center">
          
          
        </div>
       
        <div class="block w-full text-left">
          <h5 class="text-xs italic">
            Bạn đã có tài khoản?
            <span class="underline"
              ><router-link to="/auth/login">Đăng nhập ngay</router-link></span
            >
          </h5>
        </div>
      </form>
    </div>
    <div
      class="absolute w-[400px] h-[200px] bg-white z-99 left-[40%] p-5 text-center rounded-md shadow-md flex flex-col items-center justify-between"
      :class="!success ? 'hidden' : ''"
    >
      <div><vue-feather class="w-16 h-16 text-green-600" type="check-circle"></vue-feather></div>
      <div class="text-black">Đăng ký tài khoản thành công!</div>
      <button
        type="button"
        class="px-10 text-white transition-all bg-[#ffb416] hover:bg-[#e2c484] hover:opacity-90"
        @click="registerSuccess"
      >
        OK
      </button>
    </div>
    
    <!-- <div
      class="absolute w-[400px] h-[200px] bg-white z-99 left-[40%] p-5 text-center rounded-md shadow-md flex flex-col items-center justify-between"
      :class="!success || !register ? 'hidden' : ''"
    >
      <div><vue-feather class="w-16 h-16 text-red-600" type="x-circle"></vue-feather></div>
      <div class="">Register account error!</div>
      <button
        type="button"
        class="px-10 text-white transition-all bg-[#0c3247] hover:bg-[#135070] hover:opacity-90"
        @click="register = true"
      >
        OK
      </button>
    </div> -->
  </Auth>
</template>
<script>
import { ref, reactive, computed } from 'vue'
import Auth from './Auth.vue'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import AuthService from '@/stores/modules/AuthService'
import useValidate from '@vuelidate/core'
import { required, email, minLength, sameAs } from '@vuelidate/validators'
import { useRouter } from 'vue-router'
const router = useRouter()
export default {
  name: 'SignupPage',
  components: {
    ButtonComponent: Button,
    InputComponent: Input,
    Auth: Auth
  },
  setup(props) {
    const data = reactive({
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      confirm: '',
      username: ''
    })
    const rules = computed(() => {
      return {
        firstName: { required },
        lastName: { required },
        email: { required, email },
        password: { required, minLength: minLength(8) },
        confirm: { required, minLength: minLength(8), sameAs: sameAs(data.password) },
        username: { required }
      }
    })
    const errors = reactive({
      firstName: false,
      lastName: false,
      email: false,
      password: false,
      confirm: false,
      username: false
    })
    const success = ref(false)
    const v$ = useValidate(rules, data)
    return { props, data, success, errors, router, v$, AuthService, register: false }
  },
  methods: {
    onSubmit() {
      this.v$.$validate()
      if (!this.v$.$error) {
        console.log(this.data)
        AuthService.register(this.data)
          .then(
            (response) => {
              console.log(response)
              this.register = true
            },
            () => {
              this.register = true
            }
          )
          .finally(() => (this.success = true))
      } else {
        this.success = false
      }
    },
    onPressKey(event, type) {
      if (type === 'email') {
        this.v$.$validate()
        this.errors.email = true
      } else if (!this.error.email) {
        this.errors.email = false
      }
      if (type === 'username') {
        this.v$.$validate()
        this.errors.username = true
      } else if (!this.error.username) {
        this.errors.username = false
      }
      if (type === 'firstName') {
        this.v$.$validate()
        this.errors.firstName = true
      } else if (!this.error.firstName) {
        this.errors.firstName = false
      }
      if (type === 'lastName') {
        this.v$.$validate()
        this.errors.lastName = true
      } else if (!this.error.lastName) {
        this.errors.lastName = false
      }
      if (type === 'password') {
        this.v$.$validate()
        this.errors.password = true
      } else if (!this.error.password) {
        this.errors.password = false
      }
      if (type === 'confirm') {
        this.v$.$validate()
        this.errors.confirm = true
      } else if (!this.error.confirm) {
        this.errors.confirm = false
      }
    },
    registerSuccess() {
      this.success = false
      this.$router.push({ name: 'Login' })
    }
  }
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
