<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="relative ">
    <div class="absolute ">
      <img src="../../assets/images/banner.jpg" alt=""  class="w-[1370px] h-[650px]  opacity-20">
    </div>
    <section class="absolute flex items-center justify-center mt-[100px] ml-[370px]  font-poppins last:">
      <div  :class="props.checkLogin ? '' : 'opacity-40'"> 
        <img src="../../assets/images/login.jpg" alt="" class="w-[300px] h-[430px] " /> 
      </div> 
      <div class="text-black form-login bg-[white] w-[330px] h-[430px] flex flex-col gap-[50px] items-center justify-center">
        <slot />
      </div>
      
      
    </section>
    
    
  </div> 

  <router-link :to="{ name: 'User', params: {} }" >
      <button class="absolute bottom-10 right-4 -translate-x-2 -translate-y-2 text-white bg-[#ffb416] hover:bg-[#f4c96d]">
        Trang chủ
      </button></router-link
     > 
</template>
<script setup>
const props = defineProps({
  checkLogin: {
    type: Boolean,
    default: true
  }
})

</script>
<style lang="scss"></style>
