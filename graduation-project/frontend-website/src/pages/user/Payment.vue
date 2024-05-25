<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <BasePage
    class="flex flex-col items-center gap-5 text-white"
    :class="cart !== null && cart.length !== 0 ? 'justify-center' : 'justify-start'"
  >
    <template v-slot:body>
      <div
        v-if="cart === null || cart.length === 0"
        class="flex items-start justify-between  p-4 rounded-lg cart bg-[#9ed15b] w-full mt-4 "
      >
        <h1 class="block text-center">Chưa có sản phẩm nào trong giỏ hàng.</h1>
      </div>
      <div
        v-if="cart !== null && cart.length !== 0"
        class="flex items-start justify-between w-full gap-10 py-2 my-1 rounded-[4px]"
      >
        <!-- Order in Cart -->
        <div class="cart bg-[#eeeeee] w-[60%] p-5 rounded-lg">
          <!-- Item in Cart -->
          <div id="cart-item">
            <div
              class="flex items-center justify-between mb-5"
              v-for="(product, index) in productList"
              :key="index"
            >
              <!-- Information of product. -->
              <div class="flex items-start gap-5 align-middle">
                
                <img
                  src="../../assets/images/default.png"
                  class="h-[100px] w-[100px] object-cover"
                  alt=""
                />
                <div class="flex flex-col items-start justify-start h-full gap-2">
                  <h2 class="text-xl font-bold min-w-[200px] text-slate-600">{{ product.productName }}</h2>
                  <div class="text-lg text-slate-600">
                    <p>
                      {{ formatPrice(product.productPrice * product.productQuantity) }}
                      đ
                    </p>
                  </div>
                </div>
              </div>
              <!-- Increment, decrement, or delete product. -->
              <div class="flex items-center justify-between">
                <div>
                  <input-increment
                    :name="'quantity-' + product.productId"
                    v-model="product.productQuantity"
                    :value="product.productQuantity"
                    id="input"
                  />
                </div>
                <button class="bg-red-600" @click="handleDeleteItem($event, product)">Xóa</button>
              </div>
            </div>
            <!-- Total money a lot of product -->
            <div class="after-cart flex items-center font-bold justify-between mt-[30px] text-slate-600">
              <span>Tạm tính ({{ productList.length }} sản phẩm):</span>
              <span class="temporaryCalculation">{{ formatPrice(moneyOfOneProduct()) }} đ</span>
            </div>
          </div>
        </div>

        <!-- Form use to order -->
        <div class="order w-[40%] bg-[#eeeeee] p-5 rounded-lg">
          <h2 class="my-2 text-xl font-bold uppercase text-slate-600">Thanh toán và giao hàng</h2>
          <!-- Order -->
          <form @submit="onSubmit" class="w-full">
            <!-- Form Payment -->
            <div class="w-fill">
              <!-- Form full name. -->
              <div class="mb-4">
                <span class="text-sm text-slate-600"
                  >Họ và tên <span class="text-lg align-middle text-brown">*</span>
                  <span class="ml-1 text-xs leading-4 text-red-600">
                    {{
                      validate.fullName.$error && error.fullName == true
                        ? validate.fullName.$errors[0].$message
                        : ''
                    }}
                  </span>
                </span>
                <input-component
                  v-model="data.fullName"
                  type="text"
                  name="fullName"
                  placeholder="Họ tên của bạn"
                  @keyup="onPressText($event, 'fullName')"
                  :classChild="
                    validate.fullName.$error && error.fullName == true
                      ? 'border-red-600'
                      : 'border-transparent'
                  "
                   class="text-slate-600"
                />
              </div>
              <!-- Form phone. -->
              <div class="w-full mb-3">
                <span class="text-sm text-slate-600"
                  >Số điện thoại <span class="text-lg align-middle text-brown">*</span>
                  <span class="ml-1 text-xs leading-4 text-red-600">
                    {{
                      validate.phone.$error && error.phone == true
                        ? validate.phone.$errors[0].$message
                        : ''
                    }}
                  </span>
                </span>
                <input-component
                  v-model="data.phone"
                  type="text"
                  name="phone"
                  placeholder="0982211xxx"
                  @keyup="onPressText($event, 'phone')"
                  :classChild="
                    validate.phone.$error && error.phone == true
                      ? 'border-red-600'
                      : 'border-transparent'
                  "
                   class="text-slate-600"
                />
              </div>
              <!-- Form email. -->
              <div class="mb-4">
                <span class="text-sm text-[#191919]"
                  >Email: <span class="text-lg align-middle text-brown">*</span>
                  <span class="ml-1 text-xs leading-4 text-red-600">
                    {{
                      validate.email.$error && error.email == true
                        ? validate.email.$errors[0].$message
                        : ''
                    }}
                  </span>
                </span>
                <input-component
                  class="text-slate-600"
                  v-model="data.email"
                  type="email"
                  name="email"
                  placeholder="abc@gmail.com"
                  @keyup="onPressText($event, 'email')"
                  :classChild="
                    validate.email.$error && error.email == true
                      ? 'border-red-600'
                      : 'border-transparent'
                  "
                />
              </div>
              
              <!-- Form address. -->
              <div class="mb-3">
                <span class="text-sm text-slate-600"
                  >Địa chỉ <span class="text-lg align-middle text-brown">*</span>
                  <span class="ml-1 text-xs leading-4 text-red-600">
                    {{
                      validate.address.$error && error.address == true
                        ? validate.address.$errors[0].$message
                        : ''
                    }}
                  </span>
                </span>
                <input-component
                  v-model="data.address"
                  type="text"
                  name="address"
                  placeholder="Ví dụ: Số 20, Ngõ 90"
                  @keyup="onPressText($event, 'address')"
                  :classChild="
                    validate.address.$error && error.address == true
                      ? 'border-red-600'
                      : 'border-transparent'
                  "
                   class="text-slate-600"
                />
              </div>
              <!-- Form note detail. -->
              <div class="mb-3">
                <span class="text-sm text-slate-600">Ghi chú đơn hàng(nếu có) </span>
                <textarea-component
                  v-model="data.note"
                  placeholder="Ghi chú về đơn hàng, ví dụ: thời gian hay địa chỉ chi tiết hơn."
                  name="note"
                />
              </div>

              <div id="wrapper">
                <div >
                  <Button
                  v-on:click="handle()"
                  type="button"
                  text="Thêm danh mục mới"
                  id="btn-open"
                  className="bg-[#80bb35] text-[white] hover:bg-[#b0da79] hover:text-white"
                >Thêm mã giảm giá </Button>
                </div>

                  
                <div v-show="open" id="modal-container" class="show">
                  <div id="modal">
                    <div class="modal-body">
                      Bạn không có mã nào
                    </div>
                    <div class="modal-header">
                      <button v-on:click="close" id="btn-close">OK</button>
                    </div>
                  </div>
                </div>
                
              </div>
            
              
            </div>
            <!-- Total Payment -->
            <div class="flex flex-col gap-2 total text-slate-600">
              <div class="flex items-center justify-between">
                <span>Tạm tính:</span>
                <span class="text-sm temporaryCalculation-1"
                  >{{ formatPrice(moneyOfOneProduct()) }} đ</span
                >
              </div>
              <div class="flex items-center justify-between">
                <span>Giao hàng:</span>
                <span class="text-sm">Giao hàng miễn phí</span>
              </div>
              <div class="flex items-center justify-between">
                <span>Tổng:</span>
                <span class="text-sm temporaryCalculation-2"
                  >{{ formatPrice(moneyOfOneProduct()) }} đ</span
                >
              </div>
            </div>
            <!-- Payments. -->
            <div>
              <radio-component
            
                modelValue="cod"
                v-model="data.method"
                id="receivePayment"
                text="Nhận hàng thanh toán (COD)"
              />
              <radio-component
                modelValue="bank"
                v-model="data.method"
                id="bankTransfer"
                text="Chuyển khoản ngân hàng"
              />
            </div>
            <!-- Button submit order. -->
            <button-component
              type="submit"
              className="bg-[#ffb416] hover:bg-[#e3c27c] text-white w-full m-0"
              name="login"
              text="Đặt hàng"
            />
          </form>
          <!-- Form Payment -->
        </div>
      </div>
      <div class="block w-full text-cyan-800" v-if="oldOrder.length > 0">
        <div class="w-full mt-2 border-[1px] border-solid border-t-gray-500"></div>
        <div class="mt-1 mb-5 text-lg font-bold">Đơn hàng gần đây</div>
        <div
          v-for="order in oldOrder"
          :key="order.orderId"
          class="mb-5 flex flex-col items-start p-2 justify-between mt-1 border-[2px] border-solid border-gray-500"
        >
          <table class="w-full">
            <tr>
              <th>Mã sản phẩm</th>
              <th>Tên sản phẩm</th>
              <th>Số lượng</th>
              <th>Thành tiền</th>
              
            </tr>
            <tr v-for="item in order.orderList" :key="item.orderId">
              <td>{{ item.productID }}</td>
              <td>{{ item.productName }}</td>
              <td>{{ item.productQuantity }}</td>
              <td>{{ formatPrice(item.productTotalPrice) + ' đ' }}</td>
              
            </tr>
          </table>
          <div class="w-full text-right">
            <p>
              Tổng tiền: {{ formatPrice(order.totalPriceOfAllProduct.replace('.0 đ', '')) + ' đ' }}
            </p>
            <p>Mã đơn hàng: {{ order.orderCode }}</p>
            <p>
              Trạng thái:
              {{
                order.orderStatus === '1'
                  ? 'Xác nhận'
                  : order.orderStatus === '2'
                  ? 'Hủy'
                  : 'Đợi xác nhận'
              }}
            </p>
            <p v-if="order.orderStatus === '2'">Lí do hủy: {{ order.customer?.customerNote }}</p>
            <p>Thời gian đặt: {{ order.customer?.createdDate }}</p>
            <p class="text-right" v-if="order.orderStatus === '0'">
              <input
                class="w-[200px] text-cyan-900 border-solid border-[1px] border-cyan-900 p-2 mx-2"
                placeholder="Lý do hủy đơn"
                @change="cancelOrderNow($event)"
              />
              <button-component
                @click.prevent="cancelOrder($event, order.orderId)"
                type="button"
                className="bg-brown text-white w-[200px] mt-2 m-0"
                name="Hủy đơn"
                text="Hủy"
              />
            </p>
          </div>
        </div>
      </div>
    </template>
  </BasePage>
</template>
<script>
import { ref, reactive, computed } from 'vue'
import useValidate from '@vuelidate/core'
import { required, minLength, email, maxLength } from '@vuelidate/validators'
import BasePage from '../auth/BasePage.vue'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import Radio from '@/components/common/input/Radio.vue'
import Textarea from '@/components/common/input/Textarea.vue'
import InputIncrement from '@/components/common/input/InputIncrement.vue'
import OrderService from '@/stores/modules/OrderService'
import UserService from '@/stores/modules/UserService'
import render from '@/stores/modules/re-render'
import clear from '@/stores/modules/clear-item'
import { useRouter } from 'vue-router'
export default {

  data() {
    return {
      open: false
      
    }
  },

  name: 'PaymentPage',
  components: {
    InputComponent: Input,
    ButtonComponent: Button,
    RadioComponent: Radio,
    TextareaComponent: Textarea,
    InputIncrement: InputIncrement,
    BasePage
  },
  setup(props) {
    const router = useRouter()
    const cart = JSON.parse(localStorage.getItem('order'))
    const user = JSON.parse(localStorage.getItem('user'))
    const productList = reactive([])

    

    if (cart !== null) {
      cart.value.forEach((item) => {
        let product = {
          productId: '',
          productName: '',
          productQuantity: '',
          productPrice: 0,
          productTotalPrice: '',
        }
        product.productId = item.productId
        product.productName = item.productName
        product.productQuantity = item.productQuantity
        product.productPrice =
          item.productPriceSale !== null ? item.productPriceSale : item.productPrice
        productList.push(product)
      })
    }
    const data = reactive({
      email: '',
      fullName: '',
      phone: '',
      city: '',
      country: '',
      address: '',
      note: '',
      product: productList,
      method: ''
    })
    const count = 5
    const rules = computed(() => {
      return {
        email: { required, email },
        fullName: { required },
        phone: { required, minLength: minLength(10), maxLength: maxLength(10) },
        address: { required }
      }
    })
    const error = reactive({
      fullName: false,
      email: false,
      phone: false,
      address: false
    })
    const validate = useValidate(rules, data)
    const success = ref(false)
    const oldOrder = ref([])
    if (user !== null) {
      data.email = user.email
      let dataOrder = OrderService.getOrderByUserId(user.id)
      dataOrder.then((response) => {
        oldOrder.value = response.data
      })
      UserService.getByUserId(user.id).then((response) => {
        data.fullName =
          (response.data.lastName !== null ? response.data.lastName : '') +
          ' ' +
          (response.data.firstName !== null ? response.data.firstName : '')
        data.phone = response.data.phone !== null ? response.data.phone : ''
        data.address = response.data.address !== null ? response.data.address : ''
      })
      console.log(oldOrder)
    }

    return { props, productList, router, data, count, validate, success, error, cart, oldOrder }
  },
  methods: {
    onSubmit() {
      this.validate.$validate()
      if (!this.validate.$error) {
        let user = localStorage.getItem('user')
        let orderJasperModel = {
          customer: {
            userId: '',
            customerFirstName: '',
            customerLastName: this.data.fullName,
            customerAddress: this.data.address,
            customerPhone: this.data.phone,
            customerEmail: this.data.email,
            customerNote: this.data.note
          },
          orderList: this.productList,
          totalPriceOfAllProduct: '',
          totalQuantityOfAllProduct: ''
        }
        if (user !== null) {
          orderJasperModel.customer.userId = JSON.parse(user).id
        }
        this.success = true
        OrderService.insert('/', orderJasperModel)
        localStorage.removeItem('order')
      } else {
        alert('Form failed validation')
      }
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace('.', ',')
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
    },
    handleDeleteItem(event, item) {
      let orders = ref([])
      orders.value = JSON.parse(localStorage.getItem('order'))

      orders.value.value = orders.value.value.filter((x) => x.productId !== item.productId)
      let itemOrder = {
        value: orders.value.value,
        expiry: new Date().getTime() + 1000000
      }
      localStorage.setItem('order', JSON.stringify(itemOrder))
      if (orders.value.value.length === 0) {
        localStorage.removeItem('order')
      }
      render()
      clear()
      if (orders.value.value !== null) {
        orders.value.value.forEach((item) => {
          let product = {
            productId: '',
            productName: '',
            productQuantity: '',
            productPrice: 0,
            productTotalPrice: ''
          }
          product.productId = item.productId
          product.productName = item.productName
          product.productQuantity = item.productQuantity
          product.productPrice =
            item.productPriceSale !== null ? item.productPriceSale : item.productPrice
          this.productList.push(product)
        })
      }
    },
    onPressText(event, type) {
      if (type == 'email') {
        this.validate.$validate()
        this.error.email = true
      } else if (!this.error.email) {
        this.error.email = false
      }
      if (type == 'fullName') {
        this.validate.$validate()
        this.error.fullName = true
      } else if (!this.error.fullName) {
        this.error.fullName = false
      }
      if (type == 'address') {
        this.validate.$validate()
        this.error.address = true
      } else if (!this.error.address) {
        this.error.address = false
      }
      if (type == 'phone') {
        this.validate.$validate()
        this.error.phone = true
      } else if (!this.error.phone) {
        this.error.phone = false
      }
    },
    cancelOrder(event, orderId) {
      let userId = JSON.parse(localStorage.getItem('user')).id
      let cancelOrder = {
        userId: userId,
        userNote: this.data.customerNote
      }

      OrderService.delete(orderId, cancelOrder).then((res) => console.log(res))
      this.router.push('/product')
      setTimeout(() => {
        this.router.push('/payment')
      }, 500)
    },
    moneyOfOneProduct() {
      let orders = JSON.parse(localStorage.getItem('order'))
      let sum = 0
      orders.value.map(
        (x) =>
          (sum += (x.productPriceSale ? x.productPriceSale : x.productPrice) * x.productQuantity)
      )
      return sum
    },
    cancelOrderNow(event) {
      this.data.customerNote = event.target.value
    },

    handle() {
      this.open = true;
      
    },
    close() {
      this.open = false;
    }
  },
  computed: {}


  
  
}




</script>
<style lang="scss" scoped>
.after-cart {
  position: relative;
  padding-top: 20px;
  &::after {
    position: absolute;
    content: '';
    height: 1px;
    width: 100%;
    background-color: white;
    top: 0px;
  }
}
.total {
  width: 100%;
  &::before,
  &::after {
    content: '';
    margin: 8px auto;
    height: 1px;
    width: 450px;
    background-color: white;
  }
}
table,
tr,
td,
th {
  padding: 5px 10px;
  text-align: center;
}
table {
  tr {
    td,
    th {
      border: 1px solid black;
    }
  }
}
table tr td,
table tr td {
  border-bottom: 2px solid #464b53;
}
table tr td {
  padding: 5px;
}

#wrapper {
  // min-height: 100vh;
  color: black;
}
#modal-container {
  height: 100vh;
  width: 100vw;
  background-color: rgba(0,0,0,0.5);
  position: fixed;
  top: 0;
  left: 0;
//  opacity: 0;
//  pointer-events: none;
}
#modal{
  background-color: #fff;
  max-width: 500px;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  top: 150px
}

#modal .modal-header{
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
}
button#btn-close{
  outline: none;
  border: none;
  background-color: none;
}
#modal .modal-body{
  padding: 20px 40px;
}
// #modal-container.show{
//   opacity: 1;
//   pointer-events: all;
// }


</style>
