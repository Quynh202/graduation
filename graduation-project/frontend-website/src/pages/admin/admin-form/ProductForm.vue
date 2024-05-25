<template lang="">
  <div class="relative min-h-[100vh]">
    <div
      :class="modal.isDisplay == true && modal.isDisplay !== 'none' ? 'opacity-5 select-none' : ''"
    >
      <div class="text-white">
        <router-link :to="{ name: 'ProductAdmin' }">
          <Button
            type="button"
            text="< Back"
            id="addCategory"
            className="bg-[#80bb35] hover:bg-green-600 -ml-[2px] text-white font-bold"
          />
        </router-link>
      </div>
      <strong
        class="block w-full py-2 my-3 text-xl text-center uppercase rounded-md bg-[#80bb35] text-[white]"
      >
        {{ pathName !== 'ProductEditAdmin' ? 'Thêm sản phẩm' : 'Sửa sản phẩm' }}
      </strong>
      <form
        class="w-full post"
        method="post"
        @submit.prevent="onSubmit(pathName)"
        enctype="multipart/form-data"
      >
        <!-- Form Payment -->
        <div class="w-full">
          <!-- Form city and country. -->
          <div class="flex items-center justify-between w-full gap-2 text-[black]">
            <div class="w-full mb-3">
              <span class="text-base">Loại sản phẩm</span>
              <select
                name="categoryId"
                class="w-full p-2 mt-1 rounded-sm outline-none "
                id="categoryId"
                v-model="data.categoryId"
                @change="switchSelectTrademark($event)"
              >
                <option
                  v-for="item in category.data"
                  :key="item.categoryId"
                  :value="item.categoryId"
                  :selected="data.categoryId == item"
                >
                  {{ item.categoryName }}
                </option>
              </select>
            </div>
          </div>
          <!-- Form full name. -->
          <div class="mb-4">
            <span class="text-base">Tên sản phẩm</span>
            <Input
              type="text"
              name="categoryName"
              v-model="data.productName"
              placeholder="Tên sản phẩm..."
              classChild="mt-2"
            />
          </div>
          <!-- Form note detail. -->
          <div class="mb-3">
            <span class="text-base">Mô tả chi tiết sản phẩm</span>
            <!-- <Textarea name="categoryDescription" placeholder="Mô tả sản phẩm" /> -->
            <div class="bg-white">
              <quill-editor
                v-model:content="data.productDescription"
                contentType="html"
                theme="snow"
              ></quill-editor>
            </div>
          </div>
          <!-- Form product price. -->
          <div class="mb-3">
            <span class="text-base">Giá sản phẩm ban đầu</span>
            <Input
              type="text"
              name="productPrice"
              v-model="data.productPrice"
              placeholder="Giá..."
              classChild="mt-2"
            />
          </div>
          <div class="mb-3">
            <span class="text-base">Giá sản phẩm đã giảm</span>
            <Input
              type="text"
              name="productPriceSale"
              placeholder="Giá giảm..."
              classChild="mt-2"
              v-model="data.productPriceSale"
            />
          </div>
          <div class="mb-3">
            <span class="text-base">Số lượng</span>
            <Input
              type="text"
              name="productPriceSale"
              placeholder="Số lượng..."
              classChild="mt-2"
              v-model="data.quantity"
              @change="changeQuantity($event)"
            />
          </div>

          <div class="mb-3">
            <span class="text-base">Thời gian hết hạn</span>
            <Input
              type="date"
              name="expireDate"
              placeholder="Thời gian hết hạn..."
              classChild="mt-2"
              v-model="data.expireDate"
            />
          </div>

          <div class="mb-3">
            <span class="text-base">Tải ảnh</span>
            <div class="">
              <label for="file" class="grid grid-cols-8 mt-2">
                <input
                  type="text"
                  class="col-span-7 rounded-tr-none rounded-br-none disabled:bg-white max-h-[40px]"
                  name="fileName"
                  for="file"
                  disabled
                  :value="files"
                  placeholder="Chọn ảnh..."
                />
                <span
                  class="text-[white] block text-center rounded-tl-none rounded-bl-none col-span-1 rounded cursor-pointer bg-[#80bb35] py-2 px-3"
                  >Chọn ảnh</span
                >
              </label>
              <input
                @change="onChangeFile($event)"
                type="file"
                id="file"
                class="hidden w-full"
                multiple
              />
            </div>
          </div>
          
        </div>
        <!-- Form address. -->
        
        <!-- Button submit order. -->
        <Button
          type="submit"
          className="bg-[#80bb35]
        hover:bg-brown-hover text-white w-full m-0 mt-3"
          name="login"
          :text="pathName !== 'ProductEditAdmin' ? 'Thêm' : 'Cập nhật'"
        />
      </form>
    </div>
    
    
  </div>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import ProductService from '@/stores/modules/ProductService'
import CategoryService from '@/stores/modules/CategoryService'
import { PRODUCT_EDIT, PRODUCT_NEW, INSERT, UPDATE } from '@/constants/index'
const router = useRouter()

const category = reactive({
  data: []
})
const route = useRoute()
const pathName = useRoute().matched[0].name
onMounted(async () => {
  await getAllDataCategory(category)
  if (pathName === 'ProductEditAdmin') {
    await getById(route.params.categoryId, route.params.productId, data)
  }
})

const modal = ref({
  isDisplay: false,
  isType: 'none'
})
const data = reactive({
  categoryId: '',
  productName: '',
  productId: '',
  productDescription: '',
  productPrice: '',
  productPriceSale: '',
  quantity: '',
  expireDate:'',
  status: '1',
  
})
const backupQuantity = ref(0)
const totalQuantity = ref(0)
const form = reactive({ ...data })
const files = ref([])


const switchSelectTrademark = (event) => {
  data.categoryId = event.target.value
}
const onChangeFile = (event) => {
  console.log(event.target.files)
  console.log(typeof event.target.files)
  files.value = event.target.files
}
const onSubmit = (pathName) => {
  try {
    if (PRODUCT_NEW === pathName) {
      
      ProductService.insertOrUpdate(files.value, data, '/register', INSERT).then(() =>
        router.push({ name: 'ProductAdmin' })
      )
      Object.assign(form, data)
      files.value = ''
      data.productName = ''
      data.productPrice = ''
      data.productPriceSale = ''
      data.productPriceSale = ''
      data.quantity = ''
      data.productDescription = ''
      data.expireDate = ''
      data.categoryId = 1
    }

    if (PRODUCT_EDIT === pathName) {
      ProductService.insertOrUpdate(files.value, data, '/update', UPDATE).then(() =>
        router.push({ name: 'ProductAdmin' })
      )
    }
  } catch (e) {
    console.log(e)
  }
}

function changeQuantity(event) {
  backupQuantity.value = event.target.value
  totalQuantity.value = event.target.value
}

async function getAllDataCategory(api) {
  try {
    const res = await CategoryService.getAll('/init')

    const result = {
      status: res.status,
      headers: res.headers,
      data: res.data
    }

    api.data = result.data
    data.categoryId = category.data[0].categoryId
  } catch (error) {
    api = formatResponse(error.response?.data) || error
  }
}

async function getById(categoryId, productId, data) {
  try {
    const res = await ProductService.getProductById('/init', categoryId, productId)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }
    Object.assign(data, result.data)
    
  } catch (error) {
    formatResponse(error.response?.data) || error
  }
}
function formatResponse(res) {
  return JSON.stringify(res, null, 2)
}
</script>
<style lang="css" scoped>
span {
  color: black;
}
.title p,
.title input {
  border-right: 2px #17b1ea solid;
}
.title p:first-child {
  border-left: 2px #17b1ea solid;
}
</style>
