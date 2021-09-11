<template>
<div class="flex flex-col  h-screen  w-full p-4">
  <div class="flex px-4 justify-between">
    <div class="flex flex-col space-y-4">
      <label for="users">Konu Seç:</label> 
      <select name="users" id="users" class="bg-white border px-2 py-1 focus:outline-none focus:ring-2 rounded" v-model="selectedTopicId">
        <option :value="null" selected>Konu Seç</option>
        <option  v-for="topic in topics" :key="topic" :value="topic.id"> {{topic.id}} </option>
      </select>
      <BaseInput id="postTitle" label="Konu Başlığı" placeholder="Bir başlık gir" v-model="post.title" />
      <BaseInput id="postHeader" label="Konu Headeri" placeholder="Bir header gir" v-model="post.header"/>
      <BaseCheckbox id="9" label="Konuyu gizle" v-model="post.isPublish" :checked="post.isPublish"/>
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
      <BaseInput id="googleTitle" label="Title" placeholder="Title" v-model="post.googleTitle"/>
      <BaseInput id="googleDescription" label="Description" placeholder="Description" v-model="post.googleDescription"/>
      <BaseInput id="googleImage" label="Image Path" placeholder="Image Path" v-model="post.googlePath"/>
      <BaseInput id="googleKeywords" label="Keywords" placeholder="Keywords" v-model="post.keywords"/>
      <BaseInput id="googleTags" label="Tags" placeholder="Tags" v-model="post.googleTag"/>

    </div>
    <div class="flex flex-col space-y-2">
      <h1 class="font-bold text-lg">Twitter SEO</h1>
      <BaseInput id="twitterTitle" label="Title" placeholder="Title" v-model="post.twitterTitle"/>
      <BaseInput id="twitterDescription" label="Description" placeholder="Description" v-model="post.twitterDescription"/>
      <BaseInput id="twitterImage" label="Image Path" placeholder="Image Path" v-model="post.twitterImagepath"/>
      <BaseInput id="twitterCreator" label="Creator" placeholder="Creator" v-model="post.twitterCreator"/>
      <BaseInput id="twitterCard" label="Card" placeholder="Card" v-model="post.twitterCard"/>

    </div>
    <div class="flex flex-col space-y-2">
      <h1 class="font-bold text-lg">Facebook SEO</h1>
      <BaseInput id="facebookTitle" label="Title" placeholder="Title" v-model="post.facebookTitle"/>
      <BaseInput id="facebookDescription" label="Description" placeholder="Description" v-model="post.facebookDescription"/>
      <BaseInput id="facebookImage" label="Image Path" placeholder="Image Path" v-model="post.facebookImagepath"/>
      <BaseInput id="facebookAuthor" label="Author" placeholder="Author" v-model="post.facebookAuthor"/>
      <BaseInput id="facebookSitename" label="Sitename" placeholder="Sitename" v-model="post.facebookSitename"/>
      <BaseInput id="facebookURL" label="URL" placeholder="URL" v-model="post.facebookUrl"/>
      <BaseInput id="facebookType" label="Type" placeholder="Type" v-model="post.facebookType"/>
      <BaseInput id="facebookTag" label="Facebook Tags" placeholder="Facebook Tags" v-model="post.facebookTag"/>
    </div>
  </div>
    <div class="flex flex-row space-x-12 justify-center">
      <button class="bg-green-500 px-2 py-2 text-sm rounded-md border hover:bg-green-600 focus:ring-2 transition duration-300 mt-4 text-white" @click="savePost"> Yeni Konu Kaydet </button>
      <button class="bg-blue-500 px-2 py-2 text-sm rounded-md border hover:bg-blue-600 focus:ring-2 transition duration-300 mt-4 text-white" v-if="post.id != null" @click="update"> Değişiklikleri Kaydet </button>
      <button class="bg-red-600 px-2 py-2 text-sm rounded-md border hover:bg-red-700 focus:ring-2 transition duration-300 mt-4 text-white" v-if="post.id != null"> Seçili Konuyu Sil </button>
      <button class="bg-red-600 px-2 py-2 text-sm rounded-md border hover:bg-red-700 focus:ring-2 transition duration-300 mt-4 text-white" @click="clear"> Temizle </button>
  </div>
  <div class="flex flex-col w-full mt-12">
      <BaseEditor @setValue="setValue" v-bind:body="this.post.body"></BaseEditor>
  </div>

</div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from 'vuex'

export default {
  name:'PostAdd',
  data(){
    return {
      selectedTopicId:null,
      post:{
        title:null,
        header:null,
        categories:null,
        body:null,
        isPublish:false,
        googleTitle:null,
        googleDescription:null,
        googlePath:null,
        googleTag:null,
        keywords:null,

        twitterDescription:null,
        twitterImagepath:null,
        twitterTitle:null,
        twitterTag:null,
        twitterCard:null,
        twitterCreator:null,

        facebookDescription:null,
        facebookImagepath:null,
        facebookTitle:null,
        facebookTag:null,
        facebookAuthor:null,
        facebookSitename:null,
        facebookUrl:null,
        facebookType:null,
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
    ...mapActions('topic',['getTopics','getTopicById','saveTopic','updateTopic']),
    ...mapActions('category',['saveCategory','getCategories']),
    ...mapGetters('topic',['getAllTopic','getTopic']),
    ...mapGetters('category',['getAllCategories']),
    ...mapMutations('alert',['pushAlert']),
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
      this.post.body = val
    },
    savePost(){
      this.post.categories = this.selectedCategories 
      this.saveTopic(this.post);
    },
    update(){
      this.updateTopic(this.post)
    },
    clear(){
      this.post.title = null;
      this.post.header=null,
      this.post.categories=null,
      this.post.body=null,
      this.post.isPublish=false,
      this.post.googleTitle=null,
      this.post.googleDescription=null,
      this.post.googlePath=null,
      this.post.googleTag=null,
      this.post.keywords=null,
      this.post.twitterDescription=null,
      this.post.twitterImagepath=null,
      this.post.twitterTitle=null,
      this.post.twitterTag=null,
      this.post.twitterCard=null,
      this.post.twitterCreator=null,
      this.post.facebookDescription=null,
      this.post.facebookImagepath=null,
      this.post.facebookTitle=null,
      this.post.facebookTag=null,
      this.post.facebookAuthor=null,
      this.post.facebookSitename=null,
      this.post.facebookUrl=null,
      this.post.facebookType=null

      this.selectedCategories = []
      this.selectedTopicId = null
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
      this.post.id = newVal?.post?.id
      this.post.title = newVal?.post?.title
      this.post.header = newVal?.post?.header
      this.post.title = newVal?.post?.title
      this.post.body = newVal?.body

      this.post.googleId =newVal?.meta?.googleSEO?.id
      this.post.googleTitle=newVal?.meta?.googleSEO?.title,
      this.post.googleDescription=newVal?.meta?.googleSEO?.description
      this.post.googlePath=newVal?.meta?.googleSEO?.image
      this.post.keywords=newVal?.meta?.googleSEO?.keywords

      this.post.twitterId =newVal?.meta?.twitterSEO?.id
      this.post.twitterDescription=newVal?.meta?.twitterSEO?.description,
      this.post.twitterImagepath=newVal?.meta?.twitterSEO?.image,
      this.post.twitterTitle=newVal?.meta?.twitterSEO?.title,
      this.post.twitterCard=newVal?.meta?.twitterSEO?.card,
      this.post.twitterCreator=newVal?.meta?.twitterSEO?.creator

      this.post.facebookId =newVal?.meta?.facebookSEO?.id
      this.post.facebookDescription=newVal?.meta?.facebookSEO?.description
      this.post.facebookImagepath=newVal?.meta?.facebookSEO?.image
      this.post.facebookTitle=newVal?.meta?.facebookSEO?.title
      this.post.facebookAuthor=newVal?.meta?.facebookSEO?.author
      this.post.facebookSitename=newVal?.meta?.facebookSEO?.siteName
      this.post.facebookUrl=newVal?.meta?.facebookSEO?.url
      this.post.facebookType=newVal?.meta?.facebookSEO?.type

      this.post.metaId = newVal?.meta?.id

      if(newVal?.post?.category?.length > 0){
        this.selectedCategories = newVal?.post?.category?.map(c => c.id)
      }
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