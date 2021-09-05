<template>
    <td class="px-6 py-4 whitespace-nowrap">
        <div class="flex items-center">
            {{mail.emailAddress}}
        </div>
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
        <div class="text-sm text-gray-900"> {{mail.createdDate}}</div>
        <div class="text-sm text-gray-500">{{mail.sendDate}}</div>
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
 
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-200 text-green-600" v-if="mail.mailType == 'Verification'">
            {{mail.mailType}}
        </span>
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800" v-if="mail.mailType == 'VerificationSuccess'">
            {{mail.mailType}}
        </span>
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-yellow-100 text-yellow-800" v-if="mail.mailType == 'PasswordChanged'">
            {{mail.mailType}}
        </span>
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-blue-100 text-blue-800" v-if="mail.mailType == 'PasswordChangedSuccess'">
            {{mail.mailType}}
        </span>
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-purple-100 text-purple-800" v-if="mail.mailType == 'LoginSuccess'">
            {{mail.mailType}}
        </span>
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-red-100 text-red-800" v-if="mail.mailType == 'LoginAttempt'">
            {{mail.mailType}}
        </span>
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-indigo-100 text-indigo-800" v-if="mail.mailType == 'Other'">
            {{mail.mailType}}
        </span>
       
    </td>
    <td class="px-6 py-4 whitespace-nowrap">
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800" v-if="mail.send">
            Gönderildi.
        </span>
        <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-red-100 text-red-800" v-if="!mail.send">
            Gönderilmedi.
        </span>
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
        {{mail?.mailTitle}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
      {{mail?.mailSubtitle}}
    </td>
    <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
      <span v-if="mail?.body?.length < 50">
          {{mail.body}}
      </span> 
      <span v-if="mail?.body?.length >=50">
          {{mail.body.substring(0,51)}}
      </span>
    </td>
</template>

<script>
export default {
    name:'MailListItem',
    props:{
        mail:{
            type:Object,
            required:true
        }
    }

}
</script>

<style>

</style>