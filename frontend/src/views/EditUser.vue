<template>
 <div id="editUser">
   <label for="email">Email</label>
   <input type="text" id="email" name="email" v-model="email"><br>
   <label for="ime">First Name</label>
   <input type="text" id="ime" name="ime" v-model="ime"><br>
   <label for="prezime">Last Name</label>
   <input type="text" id="prezime" name="prezime" v-model="prezime"><br>
   <label for="tip">Type</label>
   <input type="text" id="tip" name="tip" v-model="tip"><br>

   <button v-on:click="click">Save changes</button>

 </div>
</template>

<script>
import router from "../router";

export default {
  name: 'EditUser',
  data() {
    return {
      email: '',
      ime: '',
      prezime: '',
      tip: ''
    }
  },
  methods: {
    click: function() {
      return this.submit()
    },
    submit() {
      const params = new URLSearchParams();
      params.append("email", this.email)
      params.append("ime", this.ime)
      params.append("prezime", this.prezime)
      params.append("tip", this.tip)

      const id = this.$route.params.id

      this.$axios.post('http://localhost:8080/WEB_Projekat_war_exploded/editUserServlet/' + id, params).then(
          router.push({name: 'Users'})
      )
    }
  }
}
</script>
