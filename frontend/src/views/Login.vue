<template>
  <div id="login">
    <label for="email">Email</label>
    <input type="text" id="email" name="email" v-model="username"><br>
    <label for="lozinka">Password</label>
    <input type="text" id="lozinka" name="lozinka" v-model="password"><br>

    <button v-on:click="click">Log In</button>

  </div>
</template>

<script defer>
import router from "../router";

export default {
  name: 'Login',
  data() {
    return {
      username: '',
      password: '',
      user: undefined
    }
  },
  methods: {
    click() {
      if(!this.username.length && !this.password.length) {
        alert('Username and password cannot be empty');
        return;
      }
      if (!this.username.length) {
        alert('Username cannot be empty')
        return;
      }
      if (!this.password.length) {
        alert('Password cannot be empty')
        return;
      }
      if (this.username.length && this.password.length) {
        return this.submit();
      }
      return this.submit();
    },
    submit() {
      this.$axios.post('/api/users/login', {
        username: this.username,
        password: this.password,
      }).then(response => {
        localStorage.setItem('jwt', response.data.jwt)
        router.push({name: 'Articles'});
      }).catch(error => {
        if(error.response.status === 422) {
          alert("Incorrect credentials")
        }
      })
    }
  }
}
</script>