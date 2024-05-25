<template lang="">
  <div class="w-full flex items-center justify-between h-[75px]  px-6 ]">
    <div
      class="text-3xl font-bold tracking-[5px] cursor-pointer logo hover:cursor-pointer flex items-center leading-[30px]"
    >
      <vue-feather class="mt-1 w-7 h-7" type="menu"></vue-feather>
      <router-link to="/admin"><span class="ml-3 text-[#80bb35]">Admin</span></router-link>
    </div>
    <div class="flex items-center justify-center gap-5 ml-2">
      
      <div
        class="relative flex items-center justify-center gap-3 cursor-pointer logout"
        @click="onLogout"
      >
        <img
          src="../../assets/images/default.png"
          class="w-[40px] h-[40px] object-cover rounded-full"
          alt=""
        />
        <span class="text-base"> Admin </span>
        <vue-feather class="w-5 h-5 mt-1 -ml-2" type="chevron-down"></vue-feather>
        <div class="logout-child absolute w-[150px] flex flex-col top-[55px] right-0 z-50">
          <!-- <div
            class="flex items-center text-white justify-between px-4 py-2 rounded-tl-sm rounded-tr-sm bg-[#80bb35] hover:bg-[#cbe6a7]"
            @click="logout"
          >
            <span>Logout</span>
            <vue-feather class="relative w-5 h-5 mt-1 message-circle" type="log-out"></vue-feather>
          </div> -->
          <router-link :to="{ name: 'Login', params: {} }">
            <div
              class="flex items-center text-white justify-between px-4 py-2 rounded-tl-sm rounded-tr-sm bg-[#80bb35] hover:bg-[#cbe6a7]"
            >
              <span>Logout</span>
              <vue-feather class="relative w-5 h-5 mt-1 message-circle" type="log-out"></vue-feather>
            </div>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
// import { onMounted, ref } from 'vue'
// import store from '@/stores/store'
// import { useRouter } from 'vue-router'
// const router = useRouter()

// const logout = () => {
//   router.push('/')
//   store.dispatch('auth/logout')
// }
// async function getAllUserChat(data) {
//   const res = await ChatService.getAllMessage()
//   data.value = res.data
// }
</script>
<style lang="scss">
.announce {
  &:hover {
    & > div {
      display: block;
    }
  }
  & > div {
    z-index: 1;
    top: 50px;
    display: none;
    transition: all 0.3s ease-in-out;
    div {
      & > span {
        text-align: left;
        width: 100%;
        padding: 10px;
        border-bottom: 1px solid gray;
        &.announce-check {
          background-color: rgb(201, 201, 201);
        }
      }
    }
    &::after {
      position: absolute;
      content: '';
      right: 15px;
      top: -10px;
      width: 0;
      height: 0;
      border-left: 10px solid transparent;
      border-right: 10px solid transparent;
      border-bottom: 10px solid white;

      clear: both;
    }
  }
}
.logout {
  position: relative;
  .logout-child {
    display: none;
  }
  &:hover {
    .logout-child {
      display: block;
    }
  }
  &::after {
    position: absolute;
    content: '';
    height: 20px;
    width: 150px;
    bottom: -15px;
    &:hover {
      .logout-child {
        display: block;
      }
    }
  }
}
</style>
