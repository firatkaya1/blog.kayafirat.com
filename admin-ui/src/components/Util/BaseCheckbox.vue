<template class="was-validated">
    <div class="custom-control custom-checkbox custom-control-lg d-inline-block">
        <input
        type="checkbox"
        :id="id"
        :value="value" class="transform scale-150"
        :checked="checked"
        :disabled="disabled"
        :class="{'is-valid':isValid && dirty && active,'is-invalid':isInvalid && dirty && active}"
        @change="changed($event.target.checked)">
        <label class="custom-control-label ml-2" :for="id" v-if="label===null"> {{value}} </label>
        <label class="custom-control-label ml-3" :for="id" v-if="label!=null"> {{label}} </label>
    </div>
    <div class="red d-inline-block mt-2">
        <div v-if="required && dirty && requiredAttr.isInvalid">
            <span>
                {{ $t('message.notEmpty')}}
            </span>
        </div>
    </div>
</template>

<script>
export default {
    name:"BaseCheckboxGroup",
    emits:['update:modelValue'],
    props:{
        required:{
            type:Boolean,
            default:false
        },
        value:{
            type:String,
            default:''
        },
        id:{
            type:String,
            required:true
        },
        label:{
            type:String,
            default:null
        },
        checked:{
            type:Boolean,
            default:false
        },
        modelValue:{
            default:''
        },
        active:{
            type:Boolean,
            default:true
        },
        disabled:{
            type:Boolean,
            default:false
        }
    },
    data(){
        return {
            cmodel:(this.modelValue) ? this.modelValue : false,
            dirty:false,
            isValid:false,
            isInvalid:true,
            requiredAttr:{
                isValid:false,
                isInvalid:true
            }
        }
    },
    methods:{
        changed(value){
            this.cmodel = value;
            this.dirty = true;
            if(this.checkRequired()){
                this.isValid = true;
                this.isInvalid = false;
            } else {
                this.isValid = false;
                this.isInvalid = true;
            }
        this.$emit('update:modelValue',value)
        },
        checkRequired(){
            if(!this.required || (this.required && this.cmodel)) {
                this.requiredAttr.isValid = true
                this.requiredAttr.isInvalid = false
                return true;
            }else if(this.required && !this.cmodel) {
                this.requiredAttr.isValid = false
                this.requiredAttr.isInvalid = true
                return false;
            }
        },
        reset(){
            this.dirty = false
        },
        checkValid(){
            this.changed(this.cmodel);
        }
    },
    watch:{
        modelValue(newValue){
            this.cmodel = newValue
        }
    }
}
</script>