
<template lang="">
  <div class="flex items-center justify-center w-full bg-[#80bb35]">
    <footer class="2xl:w-[1280px] flex items-center justify-between h-[300px] text-white">
      <div class="flex flex-col gap-8">
      
        <div class="w-[350px]">
          <h3 class="mb-2 text-lg font-bold text-white">
            ĐĂNG KÝ NHẬN THÔNG TIN
          </h3>
          <h5>Đăng ký để nhận ưu đãi đặc biệt về sản phẩm của X.Mart</h5>
          <form @submit.prevent="onSubmit" class="flex items-center justify-center text-[#366b87] h-full">
            <input-common
              type="text"
              classChild="min-w-[200px] px-5 py-[10px] rounded-sm"
              name="email"
              placeholder="Email"
              v-model="data.email"
              @keyup="v$.$validate()"
            />
            <router-link to="/auth/signup">
              <button-common
                type="submit"
                text="Đăng ký"
                className="  py-3 rounded-sm bg-[#ffb416] hover:bg-[#e1b860] text-white font-bold w-[100px] "
              />
            </router-link>
          </form>
          <span class="h-[20px] px-1 text-xs leading-4 text-red-600">
            {{ v$.email.$error ? v$.email.$errors[0].$message : '' }}
          </span>
        </div>
      </div>
      <div class="flex items-start justify-around pl-[140px]">
        <div>
          <div class="text-5xl font-bold logo  ml-6">X.Mart</div>
          <p class="text-white w-72 ">Chúng tôi mang đến những sản phẩm xanh đạt chất lượng. Nhằm hướng đến một thị trường rộng mở  quốc tế</p>

          <img
            src="../../assets/image/chanh-vang.png"
            alt="footer"
            class="w-[200px]"
          />

        </div>
        
        <div class="pl-[140px] mt-16 ">
          <h3 class="text-2xl font-bold text-white mb-4">Liên hệ với chúng tôi</h3>
          <p class="text-white w-72">Địa chỉ:Minh Khai, Bắc Từ Liêm, Hà Nội </p>
          <p class="text-white w-72">Điện thoại: 039307333 </p>
          <p class="text-white w-72">Email: xmartcontact@gmail.com</p>
        </div>
      </div>
    </footer>
  </div>
</template>
<script>
import Input from '../common/input/Input.vue'
import Button from '../common/button/Button.vue'
import { reactive, computed } from 'vue'
import useValidate from '@vuelidate/core'
import { required, email } from '@vuelidate/validators'

export default {
  name: 'FooterItem',
  components: {
    InputCommon: Input,
    ButtonCommon: Button
  },
  setup(props) {
    const data = reactive({
      email: ''
    })
    const rules = computed(() => {
      return {
        email: { required, email }
      }
    })
    const v$ = useValidate(rules, data)
    return { props, data, v$ }
  }
}
</script>
<style lang="scss">
ul {
  li {
    font-weight: 500;
    cursor: pointer;
  }
}
.logo {
   
    font-family: "Segoe Script";
    color: white;
  }
</style>
