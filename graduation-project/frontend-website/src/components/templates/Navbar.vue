<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div class="w-full bg-[#80bb35] flex items-center justify-center fixed -mt-20 z-999 py-[5px]">
    <nav class="2xl:w-[1280px] flex items-center justify-between h-[70px] text-white">
      <div class="flex items-center justify-center gap-1">
        <router-link :to="route.url" v-for="(route, index) in routes" :key="index"
          ><span class="font-bold text-white">{{ route.urlName }}</span></router-link
        >
      </div>
      <div class="flex cursor-pointer logo hover:cursor-pointer mx-[100px]">X.Mart</div>

      <div class="relative flex items-center justify-center gap-2 ">
        <div class="flex items-center justify-center text-[#2a546a]">
          <Input
            type="text"
            classChild="min-w-[120px] px-2 py-2 rounded-sm"
            name="search"
            placeholder="Tìm kiếm..."
            v-model="state.search"
          />
          <button
            class="right-0 px-3 py-[9px] bg-[#ffb416] hover:bg-[#ffb416] rounded-tr-[3px] -translate-x-3 rounded-br-[3px] m-0 text-xs rounded-none"
            @click="filter()"
            >
            <vue-feather class="w-5 h-5 text-white" type="search"></vue-feather>
          </button>
        </div>
        <ul
          class="absolute top-[55px] left-0 flex-col items-center justify-center w-[55%] text-black bg-white"
          v-if="api.size > 0"
          :class="api.searchValue === '' ? 'hidden' : ''"
        >
          <li
            class="border-b-[1px] border-[#0c3247] border-solid w-full p-1 hover:bg-slate-200 transition-all"
            v-for="item in api.data"
            :key="item.productId"
            @click="redirectPage"
          >
            <router-link
              :to="{
                name: 'DetailProduct',
                params: { categoryId: item.categoryId, productId: item.productId }
              }"
            >
              {{ item.productName }}
            </router-link>
          </li>
        </ul>
        <router-link to="/payment">
          <button
            class="w-[90px] -ml-4 relative bg-transparent hover:border-[rgba(87, 61, 61, 0.6)] border-2 rounded-lg card py-[8px] flex items-center justify-center"
          >
            <span class="relative mr-1" id="total-price-cart"
              >{{ formatPrice(total.totalPrice) }}đ</span
            >
            <vue-feather class="w-5 h-5" type="shopping-bag"></vue-feather>
            <span
              class="py-1 px-2 rounded-[100%] absolute -top-2 -right-3 z-[999] bg-[#ffb416] text-white"
              id="cart"
              >{{ total.item }}</span
            >
          </button>
        </router-link>
        <div v-if="currentUser !== null" class=" login">
          <Button
            @click="logout"
            text="Đăng xuất"
            class="  font-bold   text-white bg-[#ffb416]"
          />
        </div>
        <div v-if="currentUser === null" class=" login">
          <router-link :to="{ name: 'Login', params: {} }"
            ><Button
              text="Đăng nhập"
              class="  font-bold bg-[#ffb416] text-white"
          /></router-link>
        </div>
      </div>
    </nav>
  </div>
</template>
<script setup>
import {  reactive, computed } from 'vue'
import Input from '../common/input/Input.vue'
import Button from '../common/button/Button.vue'
// import ProductService from '@/stores/modules/ProductService'
import store from '@/stores/store'
import axios from 'axios'
import 'vue-simple-range-slider/css'


const routes = [
  {
    url: '/',
    urlName: 'Trang chủ'
  },
  {
    url: '/product',
    urlName: 'Sản phẩm'
  },
  {
    url: '/about',
    urlName: 'Giới thiệu'
  },
 
  {
    url: '/contact',
    urlName: 'Liên hệ'
  }
]


const state = reactive({
  range: [30000, 100000],
  number: 10000,
  search: ''
})
const api = reactive({
  data: [],
  last: '',
  pageNo: 0,
  pageSize: 0,
  totalElements: 0,
  totalPages: '',
  renderProduct: 2,
  categoryList: {},
  totalSize: 0
})
function fetchData(result, api) {
  api.data = result.productModelList
  api.last = result.last
  api.pageNo = result.pageNo
  api.pageSize = result.pageSize
  api.totalElements = result.totalElements
  api.totalPages = result.totalPages
}
const filter = async () => {
  let filterSearch = {
    pageNo: 0,
    pageSize: 12,
    sortBy: 'product_id',
    sortDirection: 'ASC',
    searchValue: state.search,
    categoryId: '',
    priceMin: 0,
    priceMax: 0,
    
  }
  filterSearch.priceMin = state.range[0]
  filterSearch.priceMax = state.range[1]

  // filterSearch.categoryId = active.value
  // filterSearch.searchValue = state.search
  let formData = new FormData()
  formData.append('filterProduct', JSON.stringify(filterSearch))
  axios
    .post('http://localhost:8088/api/products/search-filter', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    .then((res) => {
      const result = {
        status: res.status + '-' + res.statusText,
        headers: res.headers,
        data: res.data
      }

      fetchData(result.data, api)
    })
    .catch((error) => {
      console.log(error)
    })
}



const currentUser = computed(() => store.state.auth.user)
const logout = () => {
  store.dispatch('auth/logout')
}

const total = reactive({
  item: 0,
  totalPrice: 0
})
if (localStorage.getItem('order') !== null) {
  total.totalPrice = 0
  let object = JSON.parse(window.localStorage.getItem('order'))
  if (object !== null) {
    total.item = object.value.length
    object.value.forEach((element) => {
      let price =
        element.productPriceSale === null ? element.productPrice : element.productPriceSale
      total.totalPrice += total.totalPrice + Number(price) * Number(element.productQuantity)
    })
  }
} else {
  total.item = 0
  total.totalPrice = 0
}
// async function onSearchValue(event) {
//   setTimeout(async () => {
//     await getAllData(api, event.target.value)
//     api.size = api.data.length
//   }, 500)
// }
// async function getAllData(api, value) {
//   try {
//     const res = await ProductService.getAllSearch('/search', value)

//     const result = {
//       status: res.status + '-' + res.statusText,
//       headers: res.headers,
//       data: res.data
//     }
//     api.data = result.data
//     console.log(api.data)
//   } catch (error) {
//     console.log(error)
//   }
// }
function redirectPage() {
  api.searchValue = ''
}
function formatPrice(value) {
  let val = (value / 1).toFixed(0).replace('.', ',')
  return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
}
</script>
<style lang="scss" scoped>
nav {
  .logo {
    font-size: 50px;
    font-weight: bold;
    font-family: "Segoe Script";
    color: white;
  }
  div {
    a {
      padding: 8px 10px;
      width: 100px;
      transition: all 0.2s;
      border-radius: 3px;
      text-align: center;
      &:hover {
        color: #17b1ea;
      }
      &.router-link-active {
        background: #ffb416;
        color: #17b1ea;
      }
    }
    &:last-child {
      a {
        &:hover {
          background-color: transparent;
          button {
            background-color: #f2d79d;
            color: white;
          }
        }
        &.router-link-active {
          button {
            background-color: white;
          }
          background-color: transparent;
        }
      }
    }
  }
  input[type='text'] {
    height: 40px;
  }
  .card {
    span {
      line-height: 16px;
      font-size: 12spx;
    }
  }
}


</style>
