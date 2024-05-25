<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div
    class="flex flex-col w-[300px] gap-4 py-2 px-1 rounded cursor-pointer   h-[270px] item text-cyan-800  transition-all"
    :class="classChild"
  >
    <slot name="imageChild" />
    <div class="flex flex-col gap-1 ml-12 h-[150px] ">
      <span class="text-base font-normal text-cyan-800 h-[30px]">{{ productName }}</span>
      <h4 class="text-base text-cyan-800">
        Giá: <span class="text-sm line-through">{{ formatPrice(product.price) }} đ</span>
      
        <p>{{ formatPrice(product.priceSale) }} đ</p>
      </h4>
      <h5 class="pb-3 text-sm text-cyan-800">Số lượng: {{ product.quantitySold }}</h5>
    </div>
  </div>
</template>
<script setup>
const product = defineProps({
  src: {
    type: String,
    default: 'default.png'
  },
  alt: {
    type: String,
    default: 'No image'
  },
  productName: {
    type: String,
    default: 'Giày'
  },
  price: {
    type: String,
    default: '0'
  },
  priceSale: {
    type: String,
    default: '0'
  },
  quantitySold: {
    type: Number,
    default: 0
  },
  classChild: {
    type: String,
    default: ''
  },
  srcImage: {
    type: String,
    default: 'default.png'
  }
})

const formatPrice = (value) => {
  let val = (value / 1).toFixed(0).replace('.', ',')
  return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
}
</script>
<style lang=""></style>
