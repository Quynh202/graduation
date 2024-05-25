<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <BasePage
    class="m-auto 2xl:w-[1280px] xl:w-[100%] container flex items-start justify-start flex-col min-h-[100vh]"
  >
    <template v-slot:body>
      
      <img src="../../assets/image/slider.jpg"
            alt="Banner"
            class="w-full h-[420px] mb-6"
      />
        
      <div class="w-full" >
        
        <div class="flex items-center justify-around w-full py-2 my-1 rounded-[4px]  text-cyan-800 ba mb-4">
          <div
            class="flex flex-col items-center justify-center transition-all rounded-lg cursor-pointer hover:scale-125 hover:#80bb35 log"
            v-for="(item, index) in trademark"
            :key="index">
            <router-link :to="{ name: 'Product' }"> 
              <img class="w-[150px] h-[130px] px-4 py-2 rounded-[50%]" :src="getImage(item.img)" :alt="item.title" />
               <p class="mb-2 text-base text-cyan-800 text-center"> {{ item.title }}</p>
            </router-link>
            
          </div>
        </div>
        
        <BoxCommon>
          <template v-slot:title>
            <div class="w-[400px] px-4 py-2 text-[#80bb35]">
              <h3
                class="text-2xl font-bold leading-9 tracking-tight uppercase border-[#80bb35] border-b-[2px] border-solid"
              >
                Sản phẩm được giảm giá
              </h3>
            </div>
          </template>
          <template v-slot:detail>
            <div class=" flex">
              <div class=" w-[340px] text-black mr-10  ml-4 mt-4  ">
                <div><img src="../../assets/images/home.jpg" class="img-home  w-[340px] h-[270px] " /></div>
                <div class="  categogy-home leading-8 ml-10 mt-6 "> 
                  <li class="hover:text-[#ffb416]" > Trái cây</li>
                  <li class="hover:text-[#ffb416]" > Thực phẩm khô</li>
                  <li class="hover:text-[#ffb416]" > Đồ uống </li>
                  <li class="hover:text-[#ffb416]"> Rau củ </li>
                  <p class=" bg-[#e95050] text-white px-[20px] py-[2px] mt-4 rounded-xl hover:bg-[#ffb416]">Mua sắm ngay</p>
                </div>
                  
              </div>
              <div class="grid grid-cols-4 gap-8 py-2 mt-[10px] ">
                <div v-for="product in dataSale[0]" :key="product.productId " class="productbd">
                  <router-link
                  :to="{
                    name: 'DetailProduct',
                    params: { categoryId: product.categoryId, productId: product.productId }
                  }"
                 >
                  <Item
                    :price="product.productPrice"
                    :priceSale="product.productPriceSale"
                    :quantitySold="Number(product.productQuantity)"
                    :productName="product.productName"
                    :src="product.fileName === null ? 'item.png' : product.fileName"
                    :alt="product.productName"
                  >
                    <template v-slot:imageChild>
                      <img
                        class="object-cover w-[150px] h-[150px] ml-[25px] mt-[25px] "
                        :src="getImageUrl(product.fileName)"
                        :alt="product.productName"
                      />
                    </template>
                  </Item>
                  </router-link>
                </div>
              </div>
            </div>
            
          </template>
        </BoxCommon>
        <div class="flex my-10">
          <img class="w-[617px] h-[250px] rounded-xl mr-12 cursor-pointer hover:scale-105 " 
                src="../../assets/image/content.jpg"  />
          <img class="w-[617px] h-[250px] rounded-xl cursor-pointer hover:scale-105" 
                src="../../assets/image/content1.jpg" />
        </div>
        <div class="my-4 rounded-[4px] w-full  shadow-custom">
          <div
            class="flex flex-col items-start justify-between"
            v-for="(item, index) in dataNew"
            :key="index"
          >
            <div class="flex items-center justify-between w-full px-4 py-2">
              <h3
                class="text-2xl font-bold leading-9 tracking-widest uppercase text-[#80bb35] border-[#80bb35] border-b-[2px] border-solid"
              >
                Sản phẩm mới
              </h3>
             
            </div>
            <div class="grid grid-cols-5 gap-10 py-4 mx-3">
              <div v-for="product in item" :key="product.productId" class="productbd">
                <router-link
                  :to="{
                    name: 'DetailProduct',
                    params: { categoryId: product.categoryId, productId: product.productId }
                  }"
                >
                  <Item
                    :price="product.productPrice"
                    :priceSale="product.productPriceSale"
                    :quantitySold="Number(product.productQuantity)"
                    :productName="product.productName"
                    :src="product.fileName"
                    :alt="product.productName"
                    :srcImage="product.fileName"
                  >
                    <template v-slot:imageChild>
                      <img
                        class="object-cover w-[150px] h-[150px] ml-[25px] mt-[25px]"
                        :src="getImageUrl(product.fileName)"
                        :alt="product.productName"
                      />
                    </template>
                  </Item>
                </router-link>
              </div>
            </div>
          </div>
        </div>
        
        <div class=" d ml-2 mr-4 mt-[60px]">
          <div class="t"><p class="dat text-2xl font-medium leading-9 tracking-widest uppercase text-[#80bb35] ">Khách hàng nói về chúng tôi</p></div>
          <div class="flex text-cyan-800">
            <div class="ng ">
              <p class="pr-[30px]"> Tôi đã sử dụng sản phẩm nông sản của cửa hàng, tôi rất yên tâm cho bữa ăn gia đình mình. </p>
              <div class="flex  mt-[20px]">
                <img src="../../assets/image/home/testi_01.png" class="w-[60px] mr-2">
                <p>chị Lan <br> Hà Nội </p>
              </div>
              
            </div>
            <div class="ng ">
              <p class="mr-[30px]"> Các sản phẩm của cửa hàng nông sản làm tôi tin tưởng và cảm thấy rất an tâm </p>
              <div class="flex mt-[20px]">
                <img src="../../assets/image/home/team-member-03.jpg" class="w-[60px] mr-2">
                <p>anh Nam<br> Hà Nội </p>
              </div>
              
            </div>
            <div class="ng ">
              <p class="mr-[30px]">Tôi rất yên tâm khi sử dụng thực phẩm do Cửa hàng Nông sản sạch cung cấp.  </p>
              <div class="flex  mt-[20px]">
                <img src="../../assets/image/home/team-member-01.jpg" class="w-[60px] mr-2">
                <p>anh Khoa <br> Hà Nội </p>
              </div>
              
            </div>
            <div >
              <p class="mr-[30px] ml-[40px] mt-[16px]"> Tôi đã sử dụng nông sản của cửa hàng, tôi rất yên tâm cho bữa ăn gia đình mình. </p>
              <div class="flex mr-[30px] ml-[40px] mt-[20px]">
                <img src="../../assets/image/home/testi_01.png" class="w-[60px] mr-2">
                <p>chị Lan <br> Hà Nội </p>
              </div>
              
            </div>
          </div>
        </div>
        <div class="flex mt-12 mb-14">
          <img src="../../assets/image/banner2.jpg" class="mr-6" />
          <img src="../../assets/image/banner3.jpg" class="mr-6"/>
          <img src="../../assets/image/banner2.jpg" class="mr-6"/>
          <img src="../../assets/image/banner3.jpg" class="mr-6"/>
          <img src="../../assets/image/banner2.jpg" class=""/>
        </div>
      </div>
    </template>
  </BasePage>
</template>
<script setup>
import {  ref, onMounted } from 'vue'
import Item from '@/components/common/product/Item.vue'
import BoxCommon from '@/components/common/BoxCommon.vue'
import BasePage from '../auth/BasePage.vue'
import ProductService from '@/stores/modules/ProductService'
const trademark = [
  {
    img: 'lo.jpg',
    title: 'Rau củ'
  },
  {
    img: 'thit.jpg',
    title: 'Thịt'
  },
  {
    img: 'do-kho.png',
    title: 'Đồ khô'
  },
  {
    img: 'trai-cay.jpg',
    title: 'Trái cây'
  },
  {
    img: 'trung.png',
    title: 'Trứng và bơ'
  },
  {
    img: 'do-uong.jpg',
    title: 'Đồ uống'
  }
  
]
const dataNew = ref([])
const dataSale = ref([])
const loading = ref(true)
onMounted(async () => {
  await getAllDataSale(10, loading)
  await getAllDataNew(15, loading)
  
})

async function getAllDataSale(limitItem, loading) {
  loading.value = true
  try {
    await ProductService.getProductSaleByCategoryId('/init-sale', limitItem)
      .then((res) => {
        const result = {
          status: res.status + '-' + res.statusText,
          headers: res.headers,
          data: res.data.slice(2,6)
        }
        
        dataSale.value.push(result.data)
      })
      .catch((error) => {
        console.log(error)
      })
      .finally(() => (loading.value = false))
  } catch (error) {
    console.log(error)
  }
}

async function getAllDataNew(limitItem, loading) {
  loading.value = true
  try {
    await ProductService.getProductSaleByCategoryId('/init-new', limitItem)
      .then((res) => {
        const result = {
          status: res.status + '-' + res.statusText,
          headers: res.headers,
          data: res.data.slice(1,10)
        }

        dataNew.value.push(result.data)
      })
      .catch((error) => {
        console.log(error)
      })
      .finally(() => (loading.value = false))
  } catch (error) {
    console.log(error)
  }
}


const getImage = (root) => {
  return new URL(`../../assets/images/trademark/${root}`, import.meta.url).href
}
const getImageUrl = (root) => {
  return new URL(`../../../../../api-image/${root}`, import.meta.url).href
}
</script>
<style lang="scss">
section {
  color: white;
  .item {
    overflow: hidden;
    margin: 0 auto;
  }
  .item img {
    overflow: hidden;
    transition: 0.4s all ease-in-out;
  }
}
ul {
  li.selected {
    padding: 2px 10px;
    background-color: #cc334d;
    border-radius: 4px;
  }
}
.lds-facebook > div {
  background: black;
}
.log {
  // border: 0.5px solid rgb(236, 234, 234);
  background-color: white;
  box-shadow: 3px 9px 8px 5px rgba(0,0,0,0.05);
  border-radius: 10px;

}
.img-home{
  // position: absolute;
  // z-index: 1;
  top: 730px;
  border-radius: 15px;
}
.categogy-home{
  position: absolute;
  z-index: 1;
  top: 740px;
}
.dat{
 
 
  text-align: center;
  
}
.t {
  box-shadow: 0px 9px 10px 5px rgba(0,0,0,0.05);
  background-color: #fffefe;
  padding: 7px;
  margin-top: -20px;
  width: 480px;
  height: 55px;
  border-radius: 25px;
  margin-left:400px ;
  border: 1px solid rgb(236, 234, 234);

}
.d{
  border: 2px solid #ffb416;
  border-radius: 20px;
  
}
.ng {
  border-right: 2px solid #ffb416;
  margin-bottom: 30px;
  margin-top: 5px;
  padding-top: 10px;
  margin-left: 40px;
  width: 540px;
}
</style>
