<!-- eslint-disable vue/multi-word-component-names -->
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
          class="flex items-center text-[#80bb35] justify-between w-full px-3 py-3 mb-2 rounded bg-[#F8F8F8]"
        >
          <div class="flex items-center justify-between gap-2 text-xl font-bold">
            <h3>Trang chủ</h3>
            <vue-feather type="chevron-right"></vue-feather>
            <h3>Sản phẩm</h3>
          </div>
          <div class="flex items-center justify-center">
            <Input
              type="text"
              classChild="min-w-[300px] px-2 rounded-sm"
              name="search"
              placeholder="Tìm kiếm..."
              v-model="state.search"
            />
            <button
              class="right-0 px-6 py-[13px] text-white font-bold bg-[#80bb35] hover:bg-[#80bb35] rounded-tr-[3px] rounded-br-[3px] m-0 text-xs rounded-none"
              @click="filter()"
            >
              Tìm
            </button>
          </div>
        </div>
        <div class="grid w-full grid-cols-4 gap-2">
          <div class="col-span-1 form-search h-[100%] flex flex-col gap-4  py-2 -mt-2 w-[260px] rounded-sm">
            <h3 class="block py-2 text-xl font-bold text-center rounded text-[#80bb35] bg-[#F8F8F8]">
              Danh mục sản phẩm
            </h3>
            <div>
              <ul class="flex flex-col gap-2 text-sm bg-[#F8F8F8] -mt-2">
                <li
                  class="py-3 px-4 text-base text-[#80bb35] hover:bg-[#ffb416] border-transparent border-l-[4px] border-solid"
                  @click="onSelectedCategory($event, 0)"
                  :class="active == 0 ? 'active' : ''"
                >
                  Tất cả
                </li>
                <li
                  v-for="item in api.categoryList"
                  :key="item"
                  @click="onSelectedCategory($event, item.categoryId)"
                  class="py-3 px-4 text-base text-[#80bb35] hover:bg-[#ffb416] border-transparent border-l-[4px] border-solid"
                  :class="active == item.categoryId && active != 0 ? 'active' : ''"
                >
                  {{ item.categoryName }}
                </li>
              </ul>
            </div>
            <form action="" class="flex flex-col gap-1 text-[#80bb35] bg-[#F8F8F8] rounded-sm">
              <h1 class="px-2 py-2 text-lg font-bold text-center">Tìm kiếm theo</h1>
              <div class="p-2">
                <h3>Giá</h3>
                <div>
                  <VueSimpleRangeSlider
                    class="w-[300px] mt-3"
                    :min="0"
                    :max="300000"
                    v-model="state.range"
                    active-bar-color="#17b1ea"
                    bar-color="#fff"
                  >
                    <template #suffix> đ</template>
                  </VueSimpleRangeSlider>
                </div>
              </div>
              
              
              <button
                @click.prevent="filter"
                class="right-0 bg-[#80bb35] text-white hover:bg-[#bedf94] m-0"
              >
                Lọc
              </button>
            </form>
          </div>
          <div class="col-span-3  ">
            <div class="grid grid-cols-4 gap-10 ">
              <div
                v-for="item in api.data"
                :key="item.productId"
                classChild="col-span-1 mb-3  text-[#17b1ea] " 
                class="productbd rounded-xl">
                <router-link
                  :to="{
                    name: 'DetailProduct',
                    params: { categoryId: item.categoryId, productId: item.productId }
                  }"
                >
                  <Item
                    :price="item.productPrice"
                    :priceSale="item.productPriceSale"
                    :quantitySold="Number(item.productQuantity)"
                    :productName="item.productName"
                    :src="item.fileName !== null ? item.fileName : 'default.png'"
                    :alt="item.productName"
                    :key="item.productId"
                  >
                    <template v-slot:imageChild>
                      <img
                        class="object-cover w-[150px] h-[150px] ml-[25px] mt-[25px]"
                        :src="getImageUrl(item.fileName)"
                        :alt="item.productName"
                      />
                    </template>
                  </Item>
                </router-link>
              </div>
            </div>
            <div class="w-full my-3 text-center">
              <button class="bg-[#80bb35] hover:bg-[#afce87] text-white" @click="loadMore">
                {{ api.size === api.totalElements ? 'Rút gọn' : 'Xem thêm' }}
              </button>
            </div>
          </div>
        </div>
      </div>
    </template>
  </BasePage>
</template>
<script setup>
import { ref, reactive, onMounted } from 'vue'
import BasePage from '../auth/BasePage.vue'
import Item from '@/components/common/product/Item.vue'
import Input from '@/components/common/input/Input.vue'
import axios from 'axios'
import ProductService from '@/stores/modules/ProductService'
import CategoryService from '@/stores/modules/CategoryService'
import VueSimpleRangeSlider from 'vue-simple-range-slider'
import 'vue-simple-range-slider/css'
const loading = ref(true)

const active = ref(0)
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
const onSelectedCategory = async (event, categoryId) => {
  active.value = categoryId
  try {
    if (categoryId == 0) {
      await getAllData(api, 0)
    } else {
      const res = await ProductService.getAll('/init-category/' + categoryId, 0)

      const result = {
        status: res.status + '-' + res.statusText,
        headers: res.headers,
        data: res.data
      }

      fetchData(result.data, api)
    }
  } catch (error) {
    console.log(error)
  }
}
onMounted(async () => {
  await getAllData(api, 0)
  await getAllCategory(api)
})
async function getAllData(api, pageNo) {
  loading.value = true
  try {
    await ProductService.getAllUser('/init/pageable', pageNo, 12, '', '', '')
      .then((res) => {
        const result = {
          status: res.status + '-' + res.statusText,
          headers: res.headers,
          data: res.data
        }

        fetchData(result.data, api)
      })
      .finally(() => (loading.value = false))
  } catch (error) {
    api = formatResponse(error.response?.data) || error
  }
}
function fetchData(result, api) {
  api.data = result.productModelList
  api.last = result.last
  api.pageNo = result.pageNo
  api.pageSize = result.pageSize
  api.totalElements = result.totalElements
  api.totalPages = result.totalPages
}

function formatResponse(res) {
  return JSON.stringify(res, null, 2)
}
async function loadMore() {
  try {
    api.size =
      api.renderProduct * 12 >= api.totalElements ? api.totalElements : api.renderProduct * 12

    let res
    if (api.size === api.pageSize) {
      res = await ProductService.getAllUser('/init/pageable', 0, 12, '', '', '')
      api.renderProduct = 2
    } else {
      res = await ProductService.getAllUser('/init/pageable', 0, api.size, '', '', '')
      api.renderProduct += 1
    }

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    fetchData(result.data, api)
  } catch (error) {
    console.log(error)
  }
}
async function getAllCategory(api) {
  try {
    const res = await CategoryService.getAll('/init')

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    api.categoryList = result.data
  } catch (error) {
    console.log(error)
  }
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
const getImageUrl = (root) => {
  return new URL(`../../../../../api-image/${root}`, import.meta.url).href
}
</script>
<style lang="css">
ul li.active {
  background-color: #80bb35;
  color: white;
}

.lds-facebook > div {
  background: black;
}

.productbd {
  border: 1px solid rgb(196, 196, 196);
  border-radius: 10px ;
}
</style>
