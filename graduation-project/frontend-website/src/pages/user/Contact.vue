<template lang="">
  <BasePage class="flex flex-col items-center justify-center gap-5 text-white">
    <template v-slot:body>
      <div class="grid w-full grid-cols-2 gap-5 px-10 py-10 bg-[#F8F8F8] text-cyan-800 rounded-lg">
        <div class="col-span-1">
          <VMap>
            <VMapOsmTileLayer />
          </VMap>
        </div>
        <div class="w-full h-full col-span-1">
          <div>
            <h1 class="font-[400] text-2xl leading-10 m-auto pb-2 -tracking-tighter">
              Liên hệ với chúng tôi..
            </h1>
          </div>
          
          <form class="w-full" method="post"  @submit.prevent="onSubmit()" enctype="multipart/form-data">
           
         
          <div class="flex flex-col w-full">
      
              <div class="mb-4">
                <span class="text-sm"
                  >Họ và tên <span class="text-lg align-middle text-brown">*</span>
                </span>
                <input-component
                  v-model="state.contactName"
                  type="text"
                  name="fullName"
                  placeholder="Họ tên của bạn"
                  @keyup="onPressText($event, 'fullName')"
                  :classChild="
                    v$.contactName.$error && error.contactName == true
                      ? 'border-red-600'
                      : 'border-transparent'
                  "
                />
                <span class="h-[20px] px-1 text-xs leading-4 text-red-600">
                  {{
                    v$.contactName.$error && error.contactName == true
                      ? v$.contactName.$errors[0].$message
                      : ''
                  }}
                </span>
              </div>
          
              <div class="mb-4 min-h-[40px]">
                <span class="text-sm"
                  >Email <span class="text-lg align-middle text-brown">*</span>
                </span>
                <input-component
                  type="email"
                  name="address"
                  placeholder="abc@gmail.com"
                  v-model="state.contactEmail"
                  @keyup="onPressText($event, 'email')"
                  :classChild="
                    v$.contactEmail.$error && error.contactEmail == true ? 'border-red-600' : 'border-transparent'
                  "
                />
                <span class="h-[20px] px-1 text-xs leading-4 text-red-600">
                  {{ v$.contactEmail.$error && error.contactEmail == true ? v$.contactEmail.$errors[0].$message : '' }}
                </span>
              </div>
            
              <!-- <div class="mb-4">
                <span class="text-sm"
                  >Điện thoại liên hệ <span class="text-lg align-middle text-brown">*</span>
                </span>
                <input-component
                  v-model="state.phone"
                  type="text"
                  name="phone"
                  placeholder="098xxxxxxx"
                  @keyup="onPressText($event, 'phone')"
                  :classChild="
                    v$.phone.$error && error.phone == true ? 'border-red-600' : 'border-transparent'
                  "
                />
                <span class="h-[20px] px-1 text-xs leading-4 text-red-600">
                  {{ v$.phone.$error && error.phone == true ? v$.phone.$errors[0].$message : '' }}
                </span>
              </div> -->
              
              <div class="mb-4 flex">
                <div class="w-[1000px]">
                  <span class="text-sm">Tin nhắn(nếu có)</span>
                  <textarea-component
                    placeholder="Ghi chú về thông tin đơn hàng..."
                    name="note"
                    v-model="state.contactMessage"
                  />
                </div>
                <!-- <div class="ml-4 mt-2">
                  <span class="text-sm mb-2">Mã sản phẩm </span>
                  <input-component
                  type="text"
                  name="productId"
                  placeholder="11"
                  v-model="state.productId"
                  
                />
                </div> -->
                
              </div>
              <div class="mb-4 min-h-[40px]">
                <span class="text-sm"
                  >Mã sản phẩm <span class="text-lg align-middle text-brown">*</span>
                </span>
                <input-component
                  type="number"
                  name="productId"
                  placeholder="11"
                  v-model="state.productId"
                  @keyup="onPressText($event, 'productId')"
                  :classChild="
                    v$.productId.$error && error.productId == true ? 'border-red-600' : 'border-transparent'
                  "
                />
                <span class="h-[20px] px-1 text-xs leading-4 text-red-600">
                  {{ v$.productId.$error && error.productId == true ? v$.productId.$errors[0].$message : '' }}
                </span>
              </div>
            </div>
            
            <button-component
            @click.prevent="onSubmit"
              type="button"
              className=" text-white w-full m-0 bg-[#80bb35]"
              name="sendMyMessage"
              text="Liên hệ"
            />
            


      

          </form>
        </div>
      </div>
    </template>
  </BasePage>
</template>
<script>
import { ref, reactive, computed } from 'vue'
import useValidate from '@vuelidate/core'
import { required, email, minLength } from '@vuelidate/validators'
import BasePage from '../auth/BasePage.vue'
import Button from '@/components/common/button/Button.vue'
import Input from '@/components/common/input/Input.vue'
import Textarea from '@/components/common/input/Textarea.vue'
import { VMap, VMapOsmTileLayer } from 'vue-map-ui'
import ContactService from '@/stores/modules/ContactService'

export default {
  name: 'ContactPage',
  components: {
    BasePage,
    ButtonComponent: Button,
    InputComponent: Input,
    TextareaComponent: Textarea,
    VMap,
    VMapOsmTileLayer
  },

  setup() {
    const error = reactive({
      contactName: false,
      contactEmail: false,
      productId: false
    })
    const state = reactive({
      contactName: '',
      contactEmail: '',
      contactMessage: '',
      productId:'',
      // phone: ''
    })
    const rules = computed(() => {
      return {
        contactName: { required, minLength: minLength(1) },
        contactEmail: { required, email },
        productId: {required, minLength: minLength(1)}
        // phone: { required, minLength: minLength(10) }
      }
    })
    const success = ref(false)
    const v$ = useValidate(rules, state)

    return { state, v$, error, success }
  },
  methods: {
    onSubmit() {
      this.v$.$validate()
      let state = {
        contactName: this.state.contactName,
        contactEmail: this.state.contactEmail,
        contactMessage: this.state.contactMessage,
        productId: this.state.productId,
        status: 0
      }
      
      if (!this.v$.$error) {
        ContactService.insertOrUpdate("/register", state)
        
        // if ANY fail validation
        this.success = true
        alert('Gửi liên hệ thành công')
        console.log(this.state)
      } else {
        alert('Form failed validation')
      }
    },
    onPressText(event, type) {
      if (type == 'email') {
        this.v$.$validate()
        this.error.contactEmail = true
      } else {
        this.error.contactEmail = false
      }
      if (type == 'fullName') {
        this.v$.$validate()
        this.error.contactName = true
      } else {
        this.error.contactName = false
      }
      if (type == 'productId') {
        this.v$.$validate()
        this.error.productId = true
      } else {
        this.error.productId = false
      }
      // if (type == 'phone') {
      //   this.v$.$validate()
      //   this.error.phone = true
      // } else {
      //   this.error.phone = false
      // }
    }
  }
}

</script>
<style lang="css" scoped>


</style>
