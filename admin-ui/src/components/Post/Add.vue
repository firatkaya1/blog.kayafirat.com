<template>
<div class="flex flex-col  h-screen  w-full p-4">
  <div class="flex px-4 justify-between">
    <div class="flex flex-col space-y-4">
      <label for="users">Konu Seç:</label> 
      <select name="users" id="users" class="bg-white border px-2 py-1 focus:outline-none focus:ring-2 rounded" v-model="selectedTopicId">
        <option :value="null" selected>Konu Seç</option>
        <option  v-for="topic in topics" :key="topic" :value="topic.id"> {{topic.id}} </option>
      </select>
      <BaseInput id="postTitle" label="Konu Başlığı" placeholder="Bir başlık gir" v-model="selectedTopic.post.title" />
      <BaseInput id="postHeader" label="Konu Headeri" placeholder="Bir header gir" v-model="selectedTopic.post.header"/>
      <BaseCheckbox id="9" label="Konuyu gizle" v-model="selectedTopic.post.hide" :checked="selectedTopic.post.hide"/>
    </div>
    <div class="flex flex-col">
      <h1 class="font-bold text-xl mb-4">Mevcut Kategoriler</h1>
      <label for="users">Kategori Ekle</label> 
      <select name="users" id="users" class="bg-white border px-2 py-1 focus:outline-none focus:ring-2 rounded" @onchange="addCategory" v-model="selectedCategory">
        <option v-for="c in categories" :key="c" :value="c.categoryId"> {{c.categoryName}} </option>
      </select>
      <div class="flex flex-wrap w-96 mt-4">
          <div class="flex px-2 py-1 rounded-full text-sm mx-1 my-1" :style="{'background-color':c.categoryColor,'color':c.categoryTextColor}" v-for="c in getCategory" :key="c"> 
            {{c.categoryName}} 
            <span @click="removeCategory(c.categoryId)">
              <Icon name="XIcon" className="h-4 w-4 ml-4 cursor-pointer hover:text-gray-500"/>
            </span>
          </div>
      </div>
    </div>
    <div class="flex flex-col space-y-4">
      <div class="flex justify-between">
        <h1 class="font-bold text-xl">Kategori Oluştur  </h1>   
        <div class="px-2 rounded-full text-xs my-auto py-1" :style="{'background-color':category.color,'color':category.textColor}" v-if="category.name.length > 0" > {{category.name}} </div>
      </div>
      <BaseInput id="categoryName" v-model="category.name" label="Kategori Adı" placeholder="Kategori Adı"/>
      <BaseInput id="categoryBGColor" v-model="category.color" label="Kategori Arkaplan Rengi" placeholder="Kategori Arkaplan Rengi"/>
      <BaseInput id="category" v-model="category.textColor" label="Kategori Yazı Rengi" placeholder="Kategori Yazı Rengi"/>
      <button class="bg-blue-500 px-2 py-2 text-sm rounded-md border hover:bg-blue-600 focus:ring-2 transition duration-300 mt-4 text-white" @click="createCategory"> Kategoriyi Oluştur </button>
    </div>
  </div>
  <div class="flex flex-row space-x-24 my-6">
    <div class="flex flex-col space-y-2">
      <h1 class="font-bold text-lg">Google SEO</h1>
      <BaseInput id="googleTitle" label="Title" placeholder="Title" v-model="selectedTopic.meta.googleSEO.title"/>
      <BaseInput id="googleDescription" label="Description" placeholder="Description" v-model="selectedTopic.meta.googleSEO.description"/>
      <BaseInput id="googleImage" label="Image Path" placeholder="Image Path" v-model="selectedTopic.meta.googleSEO.image"/>
      <BaseInput id="googleKeywords" label="Keywords" placeholder="Keywords" v-model="selectedTopic.meta.googleSEO.keywords"/>
    </div>
    <div class="flex flex-col space-y-2">
      <h1 class="font-bold text-lg">Twitter SEO</h1>
      <BaseInput id="twitterTitle" label="Title" placeholder="Title" v-model="selectedTopic.meta.twitterSEO.title"/>
      <BaseInput id="twitterDescription" label="Description" placeholder="Description" v-model="selectedTopic.meta.twitterSEO.description"/>
      <BaseInput id="twitterImage" label="Image Path" placeholder="Image Path" v-model="selectedTopic.meta.twitterSEO.image"/>
      <BaseInput id="twitterCreator" label="Creator" placeholder="Creator" v-model="selectedTopic.meta.twitterSEO.creator"/>
      <BaseInput id="twitterCard" label="Card" placeholder="Card" v-model="selectedTopic.meta.twitterSEO.card"/>

    </div>
    <div class="flex flex-col space-y-2">
      <h1 class="font-bold text-lg">Facebook SEO</h1>
      <BaseInput id="facebookTitle" label="Title" placeholder="Title" v-model="selectedTopic.meta.facebookSEO.title"/>
      <BaseInput id="facebookDescription" label="Description" placeholder="Description" v-model="selectedTopic.meta.facebookSEO.description"/>
      <BaseInput id="facebookImage" label="Image Path" placeholder="Image Path" v-model="selectedTopic.meta.facebookSEO.image"/>
      <BaseInput id="facebookAuthor" label="Author" placeholder="Author" v-model="selectedTopic.meta.facebookSEO.author"/>
      <BaseInput id="facebookSitename" label="Sitename" placeholder="Sitename" v-model="selectedTopic.meta.facebookSEO.siteName"/>
      <BaseInput id="facebookURL" label="URL" placeholder="URL" v-model="selectedTopic.meta.facebookSEO.url"/>
      <BaseInput id="facebookType" label="Type" placeholder="Type" v-model="selectedTopic.meta.facebookSEO.type"/>

    </div>
  </div>
    <div class="flex flex-row space-x-12 justify-center">
      <button class="bg-green-500 px-2 py-2 text-sm rounded-md border hover:bg-green-600 focus:ring-2 transition duration-300 mt-4 text-white"> Yeni Konu Kaydet </button>
      <button class="bg-blue-500 px-2 py-2 text-sm rounded-md border hover:bg-blue-600 focus:ring-2 transition duration-300 mt-4 text-white"> Değişiklikleri Kaydet </button>
      <button class="bg-red-600 px-2 py-2 text-sm rounded-md border hover:bg-red-700 focus:ring-2 transition duration-300 mt-4 text-white"> Seçili Konuyu Sil </button>
  </div>
  <div class="flex flex-col w-full mt-12">
    {{body}}
      <BaseEditor @setValue="setValue"></BaseEditor>
  </div>

</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'


export default {
  name:'PostAdd',
  data(){
    return {
      body:'',
      selectedTopicId:null,
      selectedTopic:{
        post:{},
        meta:{
          googleSEO:{},
          twitterSEO:{},
          facebookSEO:{}
        }
      },
      selectedCategories:[],
      selectedCategory:{},
      count:0,
      category:{
        name:'',
        color:'',
        textColor:''
      },
    }
  },
  methods:{
    ...mapActions('topic',['getTopics','getTopicById']),
    ...mapActions('category',['saveCategory','getCategories']),
    ...mapGetters('topic',['getAllTopic','getTopic']),
    ...mapGetters('category',['getAllCategories']),
    removeCategory(id){
      this.selectedCategories.splice(this.selectedCategories.indexOf(id),1)
    },
    addCategory(){
      console.log("calisti")
    },
    createCategory(){
      this.saveCategory(this.category);
    },
    setValue(val){
      this.body = val
    }
  },
  computed:{
    topics(){
      return this.getAllTopic()
    },
    topic(){
      return this.getTopic();
    },
    categories(){
      return this.getAllCategories();
    },
    getCategory(){
      return this.getAllCategories().filter(c => this.selectedCategories.includes(c.categoryId))
    }
  },
  created(){
    this.getTopics()
    this.getCategories()
  },
  watch:{
    selectedTopicId(newVal){
      if(newVal != null){
        this.getTopicById(newVal)
      }
    },
    topic(newVal){
      this.selectedTopic = newVal
    },
    selectedCategory(newVal){
      if(!this.selectedCategories.includes(newVal)){
        this.selectedCategories.push(newVal)
      }
    }
  }
}
</script>

<style>

</style>