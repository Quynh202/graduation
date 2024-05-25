<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div class="w-full h-[100vh] ">
    <h2 class="w-full my-2 mb-4 font-bold">
      <span class="text-xl">Dashboard</span> <strong> > </strong>
      <span class=" text-xl">Danh mục sản phẩm</span>
    </h2>
    <div class="flex items-center justify-between p-4 rounded-md">
      <div>
        <router-link :to="{ name: 'CategoryRegisterAdmin', params: {} }">
          <Button
            type="button"
            text="Thêm danh mục mới"
            id="addCategory"
            className="bg-[#80bb35] text-[white] hover:bg-[#b0da79] hover:text-white"
          />
        </router-link>
      </div>
    </div>
    <div class="w-full mt-5 rounded-md p-4 ">
      <div v-if="errorMessage == true" class="text-lg font-bold text-red-500">
        The connection to the network failed. Please check again.
      </div>
      <table v-if="errorMessage == false" class="w-full">
        <thead class="w-full bg-[#80bb35] text-[white]">
          <tr class="rounded-tl-md">
            <th>STT.</th>
            <th>Mã danh mục</th>
            <th>Tên danh mục</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhật</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody class="text-[black]">
          <tr v-for="(item, index) in api.category" :key="index">
            <td class="w-[50px]">{{ index + 1 }}</td>
            <td class="w-[150px]">{{ item.categoryId }}</td>
            <td >{{ item.categoryName }}</td>
            <td>{{ item.createdDate }}</td>
            <td>{{ item.updatedDate }}</td>
            <td>{{ item.categoryStatus === 'true' ? 'Active' : 'Inactive' }}</td>
            <td class="flex items-center justify-around gap-2">
              <router-link
                :to="{
                  name: 'CategoryEditAdminParam',
                  params: {  categoryId: item.categoryId }
                }"
              >
                <button
                  className="min-w-[60px] px-2 text-sm bg-[#80bb35] text-[white] hover:bg-green-600 block text-center m-0 hover:text-white"
                  name="edit"
                >
                  Sửa
                </button>
              </router-link>

              <button
                class="block min-w-[60px] px-2 m-0 text-sm text-center text-[white] bg-[#e54a4a] hover:bg-red-600 mr-3"
                name="delete"
                @click.prevent="onDelete($event, item.categoryId)"
              >
                Xóa
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script setup>
import { onMounted, reactive, ref } from 'vue'
import axios from 'axios'
import Button from '@/components/common/button/Button.vue'
import { API_CATEGORY_GET, API_CATEGORY_DETELE } from '@/stores/api'
let api = reactive({
  category: []
})
const errorMessage = ref(false)
onMounted(() => {
  apiGetAll()
})
const onDelete = (event, categoryId) => {
  // Delete item
  apiDelete( categoryId)
}
function apiDelete( categoryId) {
  axios
    .delete(API_CATEGORY_DETELE + categoryId)
    .then(() => {
      apiGetAll()
    })
    .catch((error) => {
      if (error.code != null) {
        errorMessage.value = true
      }
    })
}

function apiGetAll() {
  axios
    .get(API_CATEGORY_GET)
    .then((response) => {
      api.category = response.data
    })
    .catch((error) => {
      if (error.code != null) {
        errorMessage.value = true
      }
    })
}
</script>
<style lang="css" scoped>
ul li {
  padding: 4px 20px;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 3px;
  cursor: pointer;
}
ul li.active {
  background-color: #0c3247;
  color: #17b1ea;
}
table,
tr,
td,
th {
  padding: 5px 10px;
  text-align: center;
}

table tbody tr {
  border-bottom: 2px solid #464b53;
}
table tbody tr td {
  padding: 5px;
}


tr th {
  padding: 10px 0;
}
thead {
  line-height: 30px;
  font-weight: bold;
}
th:first-child,
th:nth-child(2) {
  width: 100px;
}
th:nth-child(3) {
  width: 500px;
}
th:nth-child(4),
th:nth-child(5) {
  width: 200px;
}
th:nth-child(6) {
  width: 150px;
}
</style>
