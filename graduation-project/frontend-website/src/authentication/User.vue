<!-- eslint-disable vue/multi-word-component-names -->

<template lang="">
  <div class="relative">
    <Navbar />
    <router-view />
    <Footer />
    <div v-if="activeSupport">
      <div
        v-if="socket.connected == false"
        @click="connect"
        class="fixed flex items-center justify-end cursor-pointer right-2 bottom-3"
      >
        
      </div>

      
    </div>
  </div>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue'
import Navbar from '../components/templates/Navbar.vue'
import Footer from '../components/templates/Footer.vue'




const socket = reactive({
  socket: '',
  stompClient: '',
  connected: false
})
const activeSupport = ref(false)

onMounted(() => {
  if (localStorage.getItem('user') !== null) {
    activeSupport.value = true
  } else {
    activeSupport.value = false
  }
  // await connect()
})

</script>
<style lang="css">
.message-left {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.message-right {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
</style>
