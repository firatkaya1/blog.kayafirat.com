<template>
    <td class="px-6 py-4 whitespace-nowrap">
        {{comment.commentId}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
        {{comment.username}}
    </td>
    <td class="px-6 py-4">
        <div class="text-sm ">  
            <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800" v-date="comment.commentCreatedDate">
            </span> 
        </div>
        <div class="text-xs text-gray-900 py-1">
           <span v-if="!isEditMode">
               {{body}}
           </span>
           <textarea name="" id="" cols="70" rows="20" class="border focus:outline-none" v-if="isEditMode" v-model="body"></textarea>
        </div>
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full tracking-wider" :class="{'bg-green-100 text-green-800':!isDelete,'bg-red-100 text-red-800':isDelete}">
            <span v-if="!isDelete"> Aktif </span>
            <span v-else> Silindi </span>
        </span>
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full tracking-wider" :class="{'bg-green-100 text-green-800':!isHide,'bg-red-100 text-red-800':isHide}">
            <span v-if="!isHide"> Açık </span>
            <span v-else> Kapalı </span>
        </span>
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-sm text-purple-900">
        {{comment.totalVote}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
        <a class="text-red-600 hover:text-red-900 border rounded px-2 py-0.5 bg-red-50" @click="setIsdelete" v-if="isEditMode">
            <span v-if="!isDelete"> Sil </span>  
            <span v-if="isDelete"> Geri Getir </span>  
        </a>
        <a class="text-yellow-600 hover:text-yellow-900 border rounded px-2 py-0.5 bg-yellow-50 mx-2" @click="setIsHide" v-if="isEditMode">
            <span v-if="!isHide"> Gizle </span>  
            <span v-if="isHide"> Göster </span>  
        </a>
        <a class="text-blue-600 hover:text-blue-900 border rounded px-2 py-0.5 bg-blue-50 ml-2" @click="isEditMode = !isEditMode" v-if="!isEditMode">Edit</a>
        <a class="text-green-600 hover:text-green-900 border rounded px-2 py-0.5 bg-green-50 ml-2" @click="isEditMode = !isEditMode;changeComment()" v-if="isEditMode">Kaydet</a>
        <a class="text-gray-600 hover:text-gray-900 border rounded px-2 py-0.5 bg-gray-50 ml-2" @click="isEditMode = !isEditMode" v-if="isEditMode">Vazgeç</a>

    </td>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    name:'CommentListItem',
    props:{
        comment:{
            type:Object,
            required:true
        },
        index:{
            type:Number,
            required:true
        }
    },
    data(){
        return {
            isEditMode:false,
            body:this.comment.commentBody ,
            isHide:this.comment.commentIshide,
            isDelete:this.comment.commentIsdelete
        }
    },
    methods:{
        ...mapActions('comment',['updateComment']),
        changeComment(){
            const body = {
                id:this.comment.commentId,
                body: this.body,
                isHide:this.isHide,
                isDelete:this.isDelete 
            }
            this.updateComment({body:body,index:this.index})
        },
        setIsHide(){
            this.isHide = !this.isHide
            this.changeComment()
        },
        setIsdelete(){
            this.isDelete = !this.isDelete
            this.changeComment()
        }
    }
}
</script>

<style>

</style>