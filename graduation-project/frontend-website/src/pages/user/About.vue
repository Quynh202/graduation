<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <BasePage class="text-white">
    <template v-slot:body>
      <div>
        <img  src="../../assets/image/banner4.jpg" class=""/>
       <div class="gt ">
         <h1 class="block w-full  text-3xl font-medium text-center text-cyan-800 "> Về chúng tôi </h1>
          <p class="text-cyan-800 mt-4 ml-2">Trang chủ  / <b> Giới thiệu</b> </p>
       
      </div>
      </div>
      
      <div class="flex flex-col items-center justify-start pb-3   mt-6">
        <div class=" text-lg leading-8 text-cyan-800">
          <p><b>X.Mart</b> là hệ thống cửa hàng thực phẩm sạch uy tín nhất ở Việt Nam, chuyên cung cấp thực phẩm sạch tới từng bếp ăn của gia đình bạn.</p>
         <p> <b>Mục tiêu:</b> Sản phẩm được giao đến tay khách hàng luôn đúng cam kết, đúng chất lượng niệm yết, luôn được bảo quản trong môi trường lý tưởng, đảm bảo vệ sinh an toàn thực phẩm.
         </p>
        </div>

      </div>
      <div class="flex text-lg leading-8 text-cyan-800">
        <div >
          <img src="../../assets/images/about.jpg" class="w-[1100px] h-[300px] " />
        </div>
        <div class="ml-12">
            <div class="flex"><img src="../../assets/images/icon2.png" class="w-[22px] h-[18px] mt-2" /> Trang trại rau hữu cơ có ưu nhược điểm gì so với các loại rau khác?</div>
            <p>Chúng ta thường yên tâm với những loại rau an toàn. Những trong thực tiễn, rau an toàn là rau có sử dụng chất hóa học trong quy trindh nuôi dưỡng, chỉ ở mức tiêu chuẩn được cho phép</p>
            <p>- Rau củ sẽ được trồng trên đất hữu cơ và sử dụng bằng phân hữu cơ</p>
            <p>- Rau có nguồn nước sạch, đảm bảo chất lượng</p>
        </div>
       
        
      </div>
      
      

       
      <div class=" text-lg leading-8 text-cyan-800 mt-4">
         <p> <b>Tầm nhìn:</b>  Được nuôi trồng, chế biến theo phương Bio (sinh học), Organic (hữu cơ), Eco (sinh thái); cam kết không bán hàng giả, hàng nhái và hàng kém chất lượng. Sản phẩm được giao đến tay khách hàng luôn đúng cam kết, đúng chất lượng niệm yết, luôn được bảo quản trong môi trường lý tưởng, đảm bảo vệ sinh an toàn thực phẩm.
         </p>
         <p> <b>Chất lượng phục vụ:</b> Với mục tiêu trở thành nhà phân phối thực phẩm sạch hàng đầu Việt Nam, chúng tôi luôn đề cao chất lượng phục vụ khách hàng. Đội ngũ nhân viên trẻ trung luôn tư vấn nhiệt tình, ghi nhận giải đáp mọi thắc mắc của khách hàng về sản phẩm.
         </p>
         <p>Nơi cam kết đem những sản phẩm sạch 100%, hàng về mỗi buổi sáng Qúy khách vui lòng đặt trước để có hàng tươi, ngon, Đem bữa ăn ngon, bữa nhậu an toàn ngon miệng đãi khách những  ngày cuối tuần tuyệt vời, nhậu xả láng , sáng  dậy sớm cà phê cuối tuần cùng gia đình và bạn! </p>
       </div>
      <div class="flex mt-6 mb-16">
          <img src="../../assets/image/banner2.jpg" class="mr-6" />
          <img src="../../assets/image/banner3.jpg" class="mr-6"/>
          <img src="../../assets/image/banner2.jpg" class="mr-6"/>
          <img src="../../assets/image/banner3.jpg" class="mr-6"/>
          <img src="../../assets/image/banner2.jpg" class=""/>
      </div>
      
      
    </template>
  </BasePage>
</template>

<script setup>
import BasePage from '../auth/BasePage.vue'
</script>

<style lang="scss">
.gt{
  position: absolute;;
  z-index: 1;
  top: 100px;
  left: 40%
}
</style>
