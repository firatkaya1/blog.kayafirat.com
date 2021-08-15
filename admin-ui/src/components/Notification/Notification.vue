<template>
  <div class="flex h-screen px-4 justify-between">
    <div class="flex flex-col w-1/3 space-y-4">
      <label for="users">Bir Icon seçiniz. </label>
      <select name="users" id="users" class="bg-white border px-2 py-1 focus:outline-none focus:ring-2 rounded">
        <option value="volvo">Konu 1</option>
        <option value="volvo">Konu 2</option>
      </select>
      <BaseInput id="notificationTitle" label="Başlık" placeholder="Bildirim başlığı giriniz." />
      <BaseTextarea id="notificationBody" placeholder="Bildirim Detayı giriniz." />
      <BaseInput id="link" label="Link" placeholder="Bildirime tıklanınca gidecek adresi giriniz." />
    </div>
    <div class="flex flex-col">
      <button class="bg-gray-100 px-2 py-1 text-sm rounded border hover:bg-gray-50 hover:text-gray-700 focus:ring-2 transition duration-300 mt-4"> Temizle </button> 
      <button class="bg-green-100 px-2 py-1 text-sm rounded border hover:bg-green-50 hover:text-green-700 focus:ring-2 transition duration-300 mt-4"> Bildirimleri Gönder </button> 
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>