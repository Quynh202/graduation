<template lang="">
  <BasePage>
    <template v-slot:body>
      <div v-if="loading" class="flex items-center justify-center w-full h-[100vh]">
        <div class="lds-facebook">
          <div></div>
          <div></div>
          <div></div>
        </div>
      </div>
      <div v-else>
        <div
          class="flex items-center justify-start w-full px-6 py-2 text-lg font-bold text-[#80bb35] rounded-[4px] bg-[#F8F8F8]"
        >
          <span class="text-xl">Trang chủ</span>
          <span
            ><vue-feather class="w-5 h-5 translate-y-1" type="chevron-right"></vue-feather
          ></span>
          <span>Sản phẩm</span>
        </div>
        <div
          class="grid grid-cols-5 w-full gap-10 px-6 py-5 my-1 text-lg font-bold text-gray-800 rounded-[4px] bg-[#F8F8F8]"
        >
          <div class="flex flex-row items-center justify-start col-span-2">
            
            <div class="image item-select">
              <img
                :active-image-id="activeImage.imageId"
                :src="getImageUrl(activeImage.fileName)"
                :alt="activeImage.fileName"
                class="w-[450px] h-[300px] object-cover"
              />
            </div>
            
          </div>
          <div class="flex flex-col items-start w-full h-full col-span-3 gap-5 pl-20">
            <h2 class="w-full text-2xl font-bold text-left rounded">
              {{ product.productModel.productName }}
            </h2>
            <!-- <div>
              <div class="flex  ">
                <img class="w-[30px] h-[30px]"
                     src="../../assets/image/product/oto.jpg"/>
                <p class="text-gray-800 pl-2">Miễn phí vận chuyển</p>
              </div>
              
            </div> -->
            <div class="flex items-center justify-between w-full text-base font-normal">
              <div>
                <span class="text-2xl text-brown"
                  >{{ formatPrice(product.productModel.productPriceSale) }}đ</span
                >
                <span class="ml-3 mr-2 text-sm font-semibold text-gray-500 line-through"
                  >{{ formatPrice(product.productModel.productPrice) }}đ</span
                >
                <span class="p-1 text-sm text-white rounded-sm bg-brown">{{
                  (
                    ((product.productModel.productPrice - product.productModel.productPriceSale) /
                      product.productModel.productPrice) *
                    100
                  ).toFixed(0) + '%'
                }}</span>
              </div>
              <div class="text-sm">
                <span>{{ product.comment.length }} đánh giá</span>
              </div>
            </div>
            <div class="flex items-start justify-between w-full text-sm">
              <div>
                <p class=" text-lg">
                  Trạng thái:
                  <span class="text-sm">{{
                    product.productModel.quantity > 0 ? 'Còn hàng' : 'Hết hàng'
                  }}</span>
                </p>
              </div>
              
            </div>


            <div class="flex items-start justify-between w-full text-sm">
              <div>
                <p class=" text-lg">
                  Hạn sử dụng: 
                  <span class="text-sm">
                {{differenceInTime(product.productModel.expireDate ) > 0 ? differenceInTime(product.productModel.expireDate ) + ' ngày' : 'Sản phẩm đã hết hạn sử dụng'  }} 
                </span>
                </p>
              </div>
            </div>

 

            <div class="w-full  mr-3">
              <span
                class="text-lg"
                
              >
                {{ product.productModel.quantity === 0 ? 'Hết hàng' : 'Số lượng còn: ' +  product.productModel.quantity }}
              </span>
            </div>

            <div class="flex h-[33px]" :class="Number(product.productModel.quantity) === 0 ? 'hidden' : ''">
              <span class="text-lg w-[150px] ">Số lượng: </span>
              <InputIncrement
                class="text-sm p-0 w-full text-white  "
                name="quantity"
                v-model="product.productQuantity"
                :value="quantity"
              />
            </div>
            <form
              class="flex w-full gap-10"
              :class="Number(product.productModel.quantity) === 0 ? 'pointer-events-none' : 'pointer-events-auto'"
            >
              <Button
                @click.prevent=" hidden(product.productModel.expireDate, $event, 'add')  "
                type="submit"
                className="bg-[#80bb35] hover:bg-[#a8d173] text-white hover:text-cyanBlue w-full m-0"
                name="addCart"
                text="Thêm vào giỏ hàng"
                id="add"
                
              />  
              <Button
              @click.prevent=" hidden(product.productModel.expireDate, $event,  'buy')"
                type="submit"
                className="bg-[#80bb35] hover:bg-[#a8d173] text-white hover:text-cyanBlue w-full m-0"
                name="buyNow"
                text="Mua ngay"
                id="add"
               
              />
            </form>
          </div>
        </div>
        <div
          class="grid grid-cols-5 mb-3 w-full gap-10 px-6 py-5 my-1 font-bold text-gray-800 rounded-[4px] bg-[#F8F8F8]"
        >
          <div class="col-span-4">
            <div class="grid grid-cols-3 gap-1 text-white">
              
              <span
                @click="onUserSelect($event, 'desc')"
                class="w-full col-span-1 py-2 text-center transition-all cursor-pointer bg-[#80bb35] hover:bg-[#a8ca7a] hover:text-cyanBlue"
                :class="userSelect.desc === true ? 'bg-[#ffb416] text-white' : ''"
                >Mô tả sản phẩm</span
              >
              <span
                @click="onUserSelect($event, 'review')"
                class="w-full col-span-1 py-2 text-center transition-all cursor-pointer bg-[#80bb35] hover:bg-[#adda73] hover:text-cyanBlue"
                :class="userSelect.review === true ? 'bg-[#ffb416] text-white' : ''"
                >Đánh giá</span
              >
            </div>
            
            <CommonItem v-if="userSelect.desc === true" class="w-full mt-5">
              <div class="p-3 text-sm" v-html="product.productModel.productDescription"></div>
            </CommonItem>
            <div v-if="userSelect.review === true">
              <CommonItem class="w-full mt-5">
                <div class="p-3 text-sm" v-if="product.comment.length <= 0">
                  Chưa có đánh giá nào.
                </div>
                <div class="p-3 text-sm" v-if="product.comment.length > 0">
                  <ul>
                    <li
                      v-for="comment in product.comment"
                      :key="comment.commentId"
                      class="flex items-start justify-start gap-3"
                    >
                      <div>
                        <img
                          src="../../assets/images/default.png"
                          class="w-[60px] h-[60px] object-cover"
                          alt=""
                        />
                      </div>
                      <div class="flex flex-col items-start justify-start w-full">
                        <span class="-mt-1 text-cyanBlue">{{
                          comment.lastName +
                          ' ' +
                          (comment.firstName === null ? '' : comment.firstName)
                        }}</span>
                        <span class="text-sm">{{ comment.commentDetail }}</span>
                        <span class="w-full text-sm text-right cursor-pointer">Like</span>
                      </div>
                    </li>
                  </ul>
                </div>
              </CommonItem>
              <CommonItem class="w-full mt-5">
                <form action="" method="post" class="flex flex-col w-full mt-5">
                  <div class="mb-3">
                    <span class="text-sm">Ý kiến của bạn về sản phẩm</span>
                    <Textarea
                      class="border-solid border-[1px] border-gray-300"
                      placeholder=""
                      v-model="comment.commentDetail"
                      name="detail"
                    />
                  </div>
                  <div class="flex items-center justify-between w-full gap-3">
                    <div class="w-full mb-3">
                      <span class="text-sm"
                        >Họ tên của bạn <span class="text-lg align-middle text-brown">*</span>
                      </span>
                      <Input
                        type="text"
                        v-model="comment.commentName"
                        name="fullName"
                        placeholder=""
                      />
                    </div>
                    <div class="w-full mb-3">
                      <span class="text-sm"
                        >Email <span class="text-lg align-middle text-brown">*</span>
                      </span>
                      <Input
                        type="email"
                        v-model="comment.commentEmail"
                        name="email"
                        placeholder=""
                      />
                    </div>
                  </div>
                  <div class="flex items-center justify-end">
                    <Button
                      type="button"
                      @click.prevent="submitComment"
                      className="bg-[#80bb35]  text-white  w-[200px] m-0"
                      name="comment"
                      text="Bình luận"
                    />
                  </div>
                </form>
              </CommonItem>
            </div>
          </div>
          <div class="col-span-1 bd h-[320px]">
            
            <div class="mb-4">
              <img class=" mb-2 ml-20 mt-4 " src="../../assets/image/product/la.jpg" />
              <p class="text-center"> 100% tự nhiên</p>
            </div>
            <div class="mb-4">
              <img class=" mb-2 ml-20 " src="../../assets/image/product/at.jpg" />
              <p class="text-center"> An toàn sức khỏe</p>
            </div>
            <div class="mb-4"> 
              <img class=" mb-2 ml-20 " src="../../assets/image/product/cn.jpg" />
              <p class="text-center"> Chứng nhận ATTP</p>
            </div>
          </div>
        </div>
      </div>
    </template>
  </BasePage>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue'
import BasePage from '../auth/BasePage.vue'
import InputIncrement from '@/components/common/input/InputIncrement.vue'
import Button from '@/components/common/button/Button.vue'
import CommonItem from '@/components/common/CommonItem.vue'
import Input from '@/components/common/input/Input.vue'
import Textarea from '@/components/common/input/Textarea.vue'
import { useRoute, useRouter } from 'vue-router'
import render from '@/stores/modules/re-render'
import moment from 'moment';
import axios from 'axios'
const route = useRoute()
const router = useRouter()
const loading = ref(true)
const product = reactive({
  comment: [],
  productModel: {},
  productQuantity: 1
})
const comment = reactive({
  productId: route.params.productId,
  commentDetail: '',
  commentName: '',
  commentEmail: ''
})
const activeImage = reactive({
  fileName: '',
  imageId: ''
})
const order = ref([])
// const quantitySize = ref(0)
const quantity = ref(0)

const userSelect = reactive({
  spec: true,
  desc: false,
  review: false
})
onMounted(async () => {
  await fetchData(route.params.categoryId, route.params.productId, product)
  const itemStr = localStorage.getItem('order')

  // if the item doesn't exist, return null
  if (itemStr) {
    const item = JSON.parse(itemStr)
    const now = new Date()

    // compare the expiry time of the item with the current time
    if (now.getTime() > item.expiry) {
      console.log(true)
      // If the item is expired, delete the item from storage
      // and return null
      localStorage.removeItem('order')
      render()
    }
  }
})

async function fetchData(categoryId, productId, product) {
  loading.value = true
  axios
    .get(`http://localhost:8088/api/products/detail/${categoryId}/${productId}`)
    .then((response) => {
      product.comment = response.data.commentList
      product.productModel = response.data.productModel
      activeImage.fileName = product.productModel.sourceImageModelList[0].fileName
      activeImage.imageId = product.productModel.sourceImageModelList[0].imageId
    })
    .catch((error) => {
      console.log(error)
    })
    .finally(() => (loading.value = false))
}
const productModel = reactive({
  productId: '',
  categoryId: '',
  productQuantity: 1,
  productName: '',
  productPrice: '',
  expireDate: '',
  createdDate: '',
  productPriceSale: ''
})

const renderDate = (item) => {
  let dataObject = new Date(item);
  let y = dataObject.getFullYear();
  let m = dataObject.getMonth() + 1;
  if (m < 10) {
    m = '0' + m
  }
  let d = dataObject.getDate();
  if (d < 10) {
    d = '0' + d
  }
  let formatDate = y + "-" + m + "-" + d;
  return formatDate;
}

const differenceInTime = (endTime) => {
  
  const currentDate = new Date();
  const startTime = currentDate.getTime();
  const start = moment(renderDate(startTime));
  const end = moment(renderDate(endTime));
  const duration = moment.duration(end.diff(start));
  console.log("duration "+ duration.days());
  return duration.days();
}


const hidden = (endDate, event, type) => {
  if (differenceInTime(endDate) <= 0) {
  
    document.getElementById("add").disabled = true;
    alert('Không thể mua sản phẩm hết hạn sử dụng');
    
  } else {
    onSubmit(event, type);
  }
  
}

const onSubmit = (event, type) => {
  if (type == 'add') {
    let flag = false
    if (localStorage.getItem('order') !== null) {
      let itemJson = JSON.parse(localStorage.getItem('order'))
      order.value = itemJson.value
      itemJson.value.forEach((element) => {
        if (
          element.productId === product.productModel.productId 
        ) {
          element.productQuantity += product.productQuantity
          flag = true
        }
      })
    }
    if (!flag) {
      productModel.categoryId = product.productModel.categoryId
      productModel.productId = product.productModel.productId
      productModel.productQuantity = product.productQuantity
      productModel.productName = product.productModel.productName
      productModel.expireDate = product.productModel.expireDate
      productModel.createdDate = product.productModel.createdDate

      productModel.productPrice = product.productModel.productPrice
      productModel.productPriceSale = product.productModel.productPriceSale
      order.value.push(productModel)
    }
    let item = {
      value: order.value,
      expiry: new Date().getTime() + 86400000
    }
    
    localStorage.setItem('order', JSON.stringify(item))
    render()
    
  } else {
    if (product.productQuantity === null) {
      product.productQuantity = 1
    }
    
    productModel.categoryId = product.productModel.categoryId
    productModel.productId = product.productModel.productId
    productModel.productQuantity = product.productQuantity
    productModel.productName = product.productModel.productName
    productModel.productPrice = product.productModel.productPrice
    productModel.productPriceSale = product.productModel.productPriceSale
    productModel.expireDate = product.productModel.expireDate
    productModel.createdDate = product.productModel.createdDate
    order.value.push(productModel)
    let item = {
      value: order.value,
      expiry: new Date().getTime() + 1000000
    }
    localStorage.setItem('order', JSON.stringify(item))
    router.push({ name: 'Payment' })
    render()
  }
}
const submitComment = () => {
  let formData = new FormData()
  formData.append('commentModel', JSON.stringify(comment))
  axios
    .post('http://localhost:8088/api/comments/insert', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    .then((response) => {
      console.log(response)
      comment.commentDetail = ''
      comment.commentName = ''
      comment.commentEmail = ''
      fetchData(route.params.categoryId, route.params.productId, product)
    })
    .catch((error) => {
      console.log(error)
    })
}
function formatPrice(value) {
  let val = (value / 1).toFixed(0).replace('.', ',')
  return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
}
const getImageUrl = (name) => {
  return new URL(`../../../../../api-image/${name}`, import.meta.url).href
}

function onUserSelect(event, userSelectStatus) {
  if (userSelectStatus === 'spec') {
    userSelect.spec = true
    userSelect.desc = false
    userSelect.review = false
  }
  if (userSelectStatus === 'desc') {
    userSelect.spec = false
    userSelect.desc = true
    userSelect.review = false
  }
  if (userSelectStatus === 'review') {
    userSelect.spec = false
    userSelect.desc = false
    userSelect.review = true
  }
}

</script>
<style lang="scss" scoped>
.active {
  color: #ff2347;
}

.image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  cursor: pointer;
}
table {
  tr {
    td {
      padding-left: 20px;
      
      line-height: 40px;
      &:first-child {
        width: 200px;
      }
    }
  }
}
ul {
  li {
    cursor: auto;
  }
}
.lds-facebook {
  div {
    background: black;
  }
}
.bd {
  border: 2px solid greenyellow;
  border-radius: 8px;
}
.value {
  background-color: #ffb416;
}
</style>
