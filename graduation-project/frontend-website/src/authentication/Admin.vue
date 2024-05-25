<!-- eslint-disable vue/multi-word-component-names -->

<template lang="">
  <NavbarAdmin v-on:userChat="getUserChat" />
  <div class="h-auto grid grid-cols-7 bg-[white] relative">
    <SidebarAdmin class="col-span-1" />
    <div class="col-span-6 m-3 p-5 rounded-md bg-[white] shadow-md">
      <router-view />
    </div>
    <div></div>
    
  </div>
</template>
<script setup>
// import Input from '../components/common/input/Input.vue'
import NavbarAdmin from '@/components/templates/NavbarAdmin.vue'
import SidebarAdmin from '@/components/templates/SidebarAdmin.vue'





</script>
<style lang=""></style> 
