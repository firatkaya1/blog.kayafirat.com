<template>
  <div class="flex h-screen px-4 w-full justify-between">
    <div class="flex flex-col space-y-4">
      <label for="users">Konu Seç:</label>
      <select name="users" id="users" class="bg-white border px-2 py-1 focus:outline-none focus:ring-2 rounded">
        <option value="volvo">Konu 1</option>
        <option value="volvo">Konu 2</option>
      </select>
      <label for="users">Kullanıcı Seç:</label>
      <select name="users" id="users" class="bg-white border px-2 py-1 focus:outline-none focus:ring-2 rounded">
        <option value="volvo">Kullanıcı 1</option>
        <option value="volvo">Kullanıcı 2</option>
      </select>
      <BaseTextarea id="comment" placeholder="Yorum Detayı" />
      <BaseInput id="username" label="Yorumu Yapılma Tarihi" placeholder="Yorum Yapılma Tarihi" />
      <BaseInput id="username" label="Yorumu Güncelleme Tarihi" placeholder="Yorum Güncelleme Tarihi" />

    </div>

    <div class="flex flex-col">
      <h1 class="font-bold text-xl">Yorumu Beğenen Kullanıcılar</h1>
      <div class="flex flex-col space-y-1 h-1/2 overflow-y-auto">
        <div class="bg-gray-100 border rounded flex justify-between px-2 py-0.5 text-sm"> firatkaya  <Icon name="XIcon" className="h-5 w-5 hover:text-gray-600 cursor-pointer" /> </div>
        <div class="bg-gray-100 border rounded flex justify-between px-2 py-0.5 text-sm"> firatkaya  <Icon name="XIcon" className="h-5 w-5 hover:text-gray-600 cursor-pointer" /> </div>
        <div class="bg-gray-100 border rounded flex justify-between px-2 py-0.5 text-sm"> firatkaya  <Icon name="XIcon" className="h-5 w-5 hover:text-gray-600 cursor-pointer" /> </div>
        <div class="bg-gray-100 border rounded flex justify-between px-2 py-0.5 text-sm"> firatkaya  <Icon name="XIcon" className="h-5 w-5 hover:text-gray-600 cursor-pointer" /> </div>
        <div class="bg-gray-100 border rounded flex justify-between px-2 py-0.5 text-sm"> firatkaya  <Icon name="XIcon" className="h-5 w-5 hover:text-gray-600 cursor-pointer" /> </div>
        <div class="bg-gray-100 border rounded flex justify-between px-2 py-0.5 text-sm"> firatkaya  <Icon name="XIcon" className="h-5 w-5 hover:text-gray-600 cursor-pointer" /> </div>
        <div class="bg-gray-100 border rounded flex justify-between px-2 py-0.5 text-sm"> firatkaya  <Icon name="XIcon" className="h-5 w-5 hover:text-gray-600 cursor-pointer" /> </div>
      </div>

    </div>

    <div class="flex flex-col">
      <button class="bg-gray-100 px-2 py-1 text-sm rounded border hover:bg-gray-50 hover:text-gray-700 focus:ring-2 transition duration-300 mt-4"> Yorum Ekle </button> 
      <button class="bg-green-500 px-2 py-2 text-sm rounded-md border hover:bg-green-600 focus:ring-2 transition duration-300 mt-4 text-white"> Yorumu Güncelle </button>
      <button class="bg-red-600 px-2 py-2 text-sm rounded-md border hover:bg-red-700 focus:ring-2 transition duration-300 mt-4 text-white"> Yorumu Sil </button>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>