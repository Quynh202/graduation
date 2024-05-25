<template lang="">
  <div class="min-h-screen">
    <div class="text-white">
      <router-link :to="{ name: 'VoucherAdmin' }">
        <Button
          type="button"
          text="< Trở lại"
          id="addCategory"
          className="bg-[#80bb35] hover:bg-green-600 -ml-[2px] text-white font-bold"
        />
      </router-link>
    </div>
    <strong
      class="block w-full py-2 my-3 text-xl text-center uppercase rounded-md bg-[#80bb35] text-[white]"
      >{{ pathName !== VOUCHER_EDIT ? 'Thêm mã giảm giá' : 'Sửa mã giảm giá' }}</strong
    >
    <form class="w-full post" method="post"  @submit.prevent="onSubmit()"
        enctype="multipart/form-data">
      <!-- Form Payment -->
      <div class="w-full">
        
        
        <!-- Form full name. -->
        <div class="mb-4">
          <p class="text-base text-[black]">Số lượng</p>
          <Input
            type="text"
            v-model="category.quantity"
            name="categoryName"
            placeholder="Số lượng"
            classChild="mt-2"
          />
        </div>
        <div class="mb-4">
          <p class="text-base text-[black]">Đơn hàng tối thiểu</p>
          <Input
            type="text"
            v-model="category.maxPriceOrder"
            name="categoryName"
            placeholder="Đơn hàng tối thiểu"
            classChild="mt-2"
          />
        </div>
        <div class="mb-4">
          <p class="text-base text-[black]">Số tiền giảm</p>
          <Input
            type="text"
            v-model="category.discount"
            name="categoryName"
            placeholder="Số tiền giảm"
            classChild="mt-2"
          />
        </div>
        <div class="mb-4">
          <p class="text-base text-[black]">Ngày bắt đầu</p>
          <Input
            type="date"
            v-model="category.startDate"
            name="categoryName"
            placeholder="Ngày bắt đầu"
            classChild="mt-2"
          />
        </div>
        <div class="mb-4">
          <p class="text-base text-[black]">Ngày hết hạn</p>
          <Input
            type="date"
            v-model="category.endDate"
            name="categoryName"
            placeholder="Ngày kết thúc"
            classChild="mt-2"
          />
        </div>
      </div>
      
      <Button
        @click.prevent="onSubmit"
        type="button"
        className="bg-[#ffb416] hover:bg-brown-hover text-white w-full m-0 mt-3"
        name="login"
        :text="pathName !== VOUCHER_EDIT ? 'Thêm mới' : 'Cập nhật'"
      />
    </form>
  </div>
</template>
<script setup>
import { reactive } from 'vue'
import { useRoute } from 'vue-router'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import { VOUCHER_EDIT } from '@/constants/index'
import axios from 'axios'
import {
  API_CATEGORY_BY_KEY,
  
} from '@/stores/api'
const route = useRoute()
const category = reactive({
  quantity: '',
  discount: '',
  maxPriceOrder: '',
  startDate: '',
  endDate: ''
})


const pathName = route.matched[0].name
const param = route.params
if (pathName === VOUCHER_EDIT) {
  apiGetByPrimaryKey( param.categoryId)
}
let onSubmit = () => {
  if (pathName === VOUCHER_EDIT) {
    apiUpdate(category)
  } else {
    apiPost(category)
    category.quantity = ''
    category.discount = ''
    category.maxPriceOrder = ''
    category.startDate = ''
    category.endDate = ''
  }
}



function apiPost(category) {
  axios
    .post("http://localhost:8088/api/voucher/register", category)
    .then((response) => {
      console.log('Success: ' + response)
    })
    .catch((error) => {
      console.log('Error: ' + error)
    })
}

function apiUpdate(category) {
  axios
    .put("http://localhost:8088/api/voucher/update" , category)
    .then((response) => {
      console.log('Success: ' + response)
      
    })
    .catch((error) => {
      console.log('Error: ' + error)
    })
}

function apiGetByPrimaryKey( categoryId) {
  axios
    .get(API_CATEGORY_BY_KEY  +  categoryId)
    .then((response) => {
      category.categoryName = response.data.categoryName
      category.categoryDescription = response.data.categoryDescription
      category.categoryStatus = response.data.categoryStatus == true ? '1' : '0'
    })
    .catch((error) => {
      console.log('Error: ' + error)
    })
}
</script>
<style lang="css" scoped>
span {
  color: white;
}
</style>
