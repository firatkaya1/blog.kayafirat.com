<template>
  <div style="min-height:500px;">
      <ckeditor v-model="cmodel" :editor="editor" :config="editorConfig"  tag-name="textarea">  </ckeditor>
  </div>
</template>

<script>
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import CKEditor from '@ckeditor/ckeditor5-vue';

export default {
    name:'BaseEditor',
    components:{ckeditor: CKEditor.component},
    data(){
        return {
            cmodel:(this.modelValue) ? this.modelValue : '',
            editor: ClassicEditor,
            editorData: '<p>Content of the editor.</p>',
            editorConfig: {}
        }
    },
    props:{
        modelValue:{
            type:[String],
            default:null
        },
    },
    watch:{
        cmodel(newWal){
            this.$emit('setValue',newWal)
        }
    }
}
</script>

<style>
  .ck-editor__editable {
    min-height: 500px;
    margin-bottom: 100px;
   }
</style>