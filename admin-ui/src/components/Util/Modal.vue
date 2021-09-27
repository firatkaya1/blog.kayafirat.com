<template>
    <div class="modal cursor-default" v-if="isOpen" @click="closeOutside($event.target.id)" id="bg-modal">
        <div class="modal-content top-16 p-4 shadow-lg border bg-white 5xl:h-10/12" style="max-height:1200px;" :class="getWidth()">
            <div class="flex flex-col">
                <div class="flex flex-row pb-4 h-auto">
                    <div class="font-bold inline-block mr-auto" :class="getTextSize()">{{title}}</div>
                    <Icon name="XIcon" className="w-7 h-7 inline-block float-right cursor-pointer my-auto hover:text-red-500 transition duration-300" :onClick="close" />
                </div>
            </div>
            <div class="flex flex-col mt-5 text-lg font-sans font-light">
                <slot></slot>
            </div>
            <div class="flex flex-row justify-end mx-auto text-white mt-12" v-if="!hideButtons">
                <BaseButton :value="'Vazgeç'"  :onClick="close" :class="'btn-red mr-4'"/>
                <BaseButton :value="successVal" :onClick="success" />
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name:"Modal",
    props:{
        isOpen:{
            type:Boolean,
            default:false
        },
        title:{
            type:String,
            required:true
        },
        successVal:{
            type:String,
            default:"Sil"
        },
        hideButtons:{
            type:Boolean,
            default:false
        },
        size:{
            type:Number,
            default:3
        },
        titleSize:{
            type:Number,
            default:5
        }
    },
    methods:{
        close(){
            this.$emit('closeModal');
        },
        closeOutside(id){
            if(id === 'bg-modal')
                this.$emit('closeModal')
        },
        success(){
            this.$emit('successModal')
        },
        getWidth(){
            if(this.size == 1) return 'w-1/12'
            else if(this.size == 2) return 'w-2/12'
            else if(this.size == 3) return 'w-3/12'
            else if(this.size == 4) return 'w-4/12'
            else if(this.size == 5) return 'w-5/12'
            else if(this.size == 6) return 'w-6/12'
            else if(this.size == 7) return 'w-7/12'
            else if(this.size == 8) return 'w-8/12'
            else if(this.size == 9) return 'w-9/12'
            else if(this.size == 10) return 'w-10/12'
            else if(this.size == 11) return 'w-11/12'
            else if(this.size == 12) return 'w-12/12'
            else return 'w-3/12'
        },
        getTextSize(){
            if(this.titleSize == 1) return 'text-xs'
            else if(this.titleSize == 2) return 'text-sm'
            else if(this.titleSize == 3) return 'text-base'
            else if(this.titleSize == 4) return 'text-lg'
            else if(this.titleSize == 5) return 'text-2xl'
            else if(this.titleSize == 6) return 'text-3xl'
            else if(this.titleSize == 7) return 'text-4xl'
            else if(this.titleSize == 8) return 'text-5xl'
            else if(this.titleSize == 9) return 'text-6xl'
            else if(this.titleSize == 10) return 'text-7xl'
            else if(this.titleSize == 11) return 'text-8xl'
            else if(this.titleSize == 12) return 'text-9xl'
            else return 'text-2xl'
        }
    }
}
</script>
