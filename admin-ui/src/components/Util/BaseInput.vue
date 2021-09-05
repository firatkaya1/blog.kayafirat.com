
<template>
    <div v-if="show">
        <label :for="id"> {{label}} </label>
        <input
        :type="type"
        :id="id"
        :value="modelValue"
        :placeholder="placeholder"
        :ref="modelValue"
        :class="{'is-valid':isValid && dirty && active,'is-invalid':isInvalid && dirty && active}"
        @input="changed($event.target.value)"
        @blur="touched"
        class="form-control">
        <div class="text-red-500 mb-4 w-96 text-sm" v-if="active">
            <div v-if="required && dirty && requiredAttr.isInvalid">
                <span>
                    Bu alan boş bırakılamaz.
                </span>
            </div>
            <div v-if="minLength!=null && dirty && minLengthAttr.isInvalid">
                <span v-if="isInvalid">
                    Bu alan en az {{minLength}} haneli olabilir.
                </span>
            </div>
            <div v-if="maxLength!=null && dirty && maxLengthAttr.isInvalid">
                <span v-if="isInvalid">
                    Bu alan en fazla {{maxLength}} haneli olabilir.
                </span>
            </div>
            <div v-if="onlyNumber!=null && dirty && onlyNumberAttr.isInvalid">
                <span v-if="isInvalid">
                    Bu alana sadece sayı girebilirsiniz.
                </span>
            </div>
            <div v-if="email!=null && dirty && emailAttr.isInvalid">
                <span v-if="isInvalid">
                    Hatalı email adresi.
                </span>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name:"BaseInput",
    emits:['update:modelValue','change'],
    props:{
        type:{
            type:String,
            default:"text"
        },
        placeholder:{
            type:String,
            default:''
        },
        label:{
            type:String,
            default:''
        },
        id:{
            type:String,
            required:false
        },
        required:{
            type:Boolean,
            default:false
        },
        minLength:{
            type:Number,
            default:null
        },
        maxLength:{
            type:Number,
            default:null
        },
        onlyNumber:{
            type:Boolean,
            default:null
        },
        email:{
            type:Boolean,
            default:null
        },
        modelValue:{
            type:[String,Number],
            default:null
        },
        active:{
            type:Boolean,
            default:false
        },
        show:{
            type:Boolean,
            default:true
        }
    },
    data(){
        return {
            cmodel:(this.modelValue) ? this.modelValue : '',
            dirty:false,
            isValid:false,
            isInvalid:true,
            requiredAttr:{
                isValid:false,
                isInvalid:true
            },
            minLengthAttr:{
                isValid:false,
                isInvalid:true
            },
            maxLengthAttr:{
                isValid:false,
                isInvalid:true
            },
            onlyNumberAttr:{
                isValid:false,
                isInvalid:true
            },
            emailAttr:{
                isValid:false,
                isInvalid:true
            }
        }
    },
    methods:{
        touched(){
            this.dirty = true;
        },
        changed(value){
            this.cmodel = value;
            this.dirty = true;
            if(this.checkRequired() && this.checkMinLength() && this.checkMaxLength() && this.checkOnlyNumber() && this.checkEmail()){
                this.isValid = true;
                this.isInvalid = false;
            } else {
                this.isValid = false;
                this.isInvalid = true;
            }
            this.$emit('update:modelValue',value)
            this.$emit('change',value)
        },
        checkRequired(){
            if(!this.required || (this.required && String(this.cmodel).length > 0)) {
                this.requiredAttr.isValid = true
                this.requiredAttr.isInvalid = false
                return true;
            }else if(this.required && String(this.cmodel).length === 0) {
                this.requiredAttr.isValid = false
                this.requiredAttr.isInvalid = true
                return false;
            }
        },
        checkMinLength(){
            if(this.minLength!=null){
                if(this.cmodel.length >= this.minLength){
                    this.minLengthAttr.isValid = true
                    this.minLengthAttr.isInvalid = false
                    return true;
                } else {
                    this.minLengthAttr.isValid = false
                    this.minLengthAttr.isInvalid = true
                    return false;
                }
            }
            return true;
        },
        checkMaxLength(){
            if(this.maxLength!= null){
                if(this.cmodel.length <= this.maxLength){
                    this.maxLengthAttr.isValid = true
                    this.maxLengthAttr.isInvalid = false
                    return true;
                } else {
                    this.maxLengthAttr.isValid = false
                    this.maxLengthAttr.isInvalid = true
                    return false;
                }
            }
            return true;
        },
        checkOnlyNumber(){
            if(this.onlyNumber!=null) {
                if(!isNaN(this.cmodel)){
                    this.onlyNumberAttr.isValid = true;
                    this.onlyNumberAttr.isInvalid=false;
                    return true;
                } else {
                    this.onlyNumberAttr.isValid = false;
                    this.onlyNumberAttr.isInvalid=true;
                    return false;
                }
            }
            return true;
        },
        checkEmail(){
            const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(this.email!= null){
                if(regex.test(this.cmodel)){
                    this.emailAttr.isValid = true
                    this.emailAttr.isInvalid = false
                    return true;
                } else {
                    this.emailAttr.isValid = false
                    this.emailAttr.isInvalid = true
                    return false;
                }
            }
            return true;
        },
        reset(){
            this.dirty = false
            this.isValid=false
            this.isInvalid=true
        },
        checkValid(){
            this.changed(this.cmodel)
        }
    },
    watch:{
        modelValue(newValue){
            this.cmodel = newValue
        }
    }
}
</script>

<style scoped lang="postcss">
.form-control {
    @apply border w-full p-1 rounded focus:outline-none focus:ring  font-extralight transition duration-300
}
.is-valid {
    @apply border-green-400 border focus:ring-green-100
}
.is-invalid {
    @apply border-red-600 border focus:ring-red-100
}
</style>