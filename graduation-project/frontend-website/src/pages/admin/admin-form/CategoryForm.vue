<template lang="">
  <div class="min-h-screen">
    <div class="text-white">
      <router-link :to="{ name: 'CategoryAdmin' }">
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
      >{{ pathName !== CATEGORY_EDIT ? 'Thêm danh mục sản phẩm' : 'Sửa danh mục sản phẩm' }}</strong
    >
    <form class="w-full post" method="post"  @submit.prevent="onSubmit()"
        enctype="multipart/form-data">
      <!-- Form Payment -->
      <div class="w-full">
        <!-- Form city and country. -->
        <div class="flex items-center justify-between w-full gap-2">
          
        </div>
        <!-- Form full name. -->
        <div class="mb-4">
          <p class="text-base text-[black]">Tên danh mục sản phẩm</p>
          <Input
            type="text"
            v-model="category.categoryName"
            name="categoryName"
            placeholder="Tên danh mục"
            classChild="mt-2"
          />
        </div>
        <!-- Form note detail. -->
        <div class="mb-3">
          <p class="text-base">Mô tả danh mục sản phẩm (nếu có) </p>
          <div class="mt-2 bg-white">
            <quill-editor
              name="categoryDescription"
              v-model:content="category.categoryDescription"
              contentType="html"
              theme="snow"
            ></quill-editor>
          </div>
        </div>
      </div>
      
      <Button
        @click.prevent="onSubmit"
        type="button"
        className="bg-[#ffb416] hover:bg-brown-hover text-white w-full m-0 mt-3"
        name="login"
        :text="pathName !== CATEGORY_EDIT ? 'Thêm mới' : 'Cập nhật'"
      />
    </form>
  </div>
</template>
<script setup>
import { reactive } from 'vue'
import { useRoute } from 'vue-router'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import { CATEGORY_EDIT } from '@/constants/index'
import axios from 'axios'
import {
  API_CATEGORY_POST,
  API_CATEGORY_BY_KEY,
  API_CATEGORY_UPDATE,
  
} from '@/stores/api'
const route = useRoute()
const category = reactive({
  categoryName: '',
  categoryDescription: '',
  categoryStatus: '1'
})


const pathName = route.matched[0].name
const param = route.params
if (pathName === CATEGORY_EDIT) {
  apiGetByPrimaryKey( param.categoryId)
}
let onSubmit = () => {
  if (pathName === CATEGORY_EDIT) {
    apiUpdate(category, param.categoryId)
  } else {
    apiPost(category)
    category.categoryName = ''
    category.categoryDescription = ''
    category.categoryStatus = ''
  }
}



function apiPost(category) {
  axios
    .post(API_CATEGORY_POST, category)
    .then((response) => {
      console.log('Success: ' + response)
    })
    .catch((error) => {
      console.log('Error: ' + error)
    })
}

function apiUpdate(category,  categoryId) {
  axios
    .put(API_CATEGORY_UPDATE  +  categoryId, category)
    .then((response) => {
      console.log(response.data.status)
      category.categoryName = response.data.categoryName
      category.categoryDescription = response.data.categoryDescription
      category.categoryStatus = response.data.status == true ? '1' : '0'
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
