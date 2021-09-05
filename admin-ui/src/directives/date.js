
import * as momenttimezone from "moment-timezone";

export default {
    mounted: function (el,binding) {
        if(binding.value === null || binding.value === undefined || binding.value === '')
            return;
        if(binding.arg === 'dateOnly'){
            el.innerHTML = momenttimezone(binding.value).utcOffset(0).format('DD.MM.YYYY')
        } else {
            el.innerHTML = momenttimezone(binding.value).utcOffset(0).format('DD.MM.YYYY  HH:mm')
        }
    }
}