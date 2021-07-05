<template>
  <div id = "prijava">
    <!-- kucaj na googleu "vue email already exists" -->
    <label for="email">Email</label>
    <input type="text" id="email" name="email" v-model="email"><br>
    <label for="ime">First Name</label>
    <input type="text" id="ime" name="ime" v-model="ime"><br>
    <label for="prezime">Last Name</label>
    <input type="text" id="prezime" name="prezime" v-model="prezime"><br>
    <label for="lozinka">Password</label>
    <input type="text" id="lozinka" name="lozinka" v-model="lozinka"><br>

    <button v-on:click="click">Add user</button>
  </div>
</template>

<script>
import router from "../router";

export default {
  name: 'Prijava',
  data() {
    return {
      email: '',
      ime: '',
      prezime: '',
      lozinka: ''
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
      params.append("lozinka", this.lozinka)

      this.$axios.get('/api/users/email/' + this.email).then(response => {
        if(response.data.length === 0) {
          this.$axios.post('http://localhost:8080/WEB_Projekat_war_exploded/prijava', params).then(router.push({name: 'Users'}));
        } else {
          alert("Email already in use")
        }
      });
    }
  }
}
</script>
