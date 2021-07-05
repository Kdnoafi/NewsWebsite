<template>
  <div class="users">
    <table style="border: 1px solid grey;">
      <tr style="border: 1px solid grey;" v-for="user in users" :key="user.id">
        <td style="border: 1px solid grey;">
          <p>{{ user.email}}</p>
          <p v-if="user.status === '1'">User status: active</p>
          <p v-if="user.status === '0'">User status: inactive</p>
          <a v-bind:href="'/editUser/' + user.id" v-on:click="userClick(user.id)">Edit</a>
        </td>
      </tr>
    </table><br>

    <button v-on:click="click">New user</button>

  </div>
</template>

<script>
import router from "../router";

export default {
  name: 'Users',
  data() {
    return {
      users: []
    }
  },
  mounted() {
    this.$axios.get('/api/users').then((response) => {
      this.users = response.data
    });
  },
  methods: {
    click: function() {
      return this.submit()
    },
    submit() {
      this.$axios.post('/api/users/authorization', {
        token: localStorage.getItem('jwt')
      }).then(response => {
        if(response.status === 200) {
          router.push({name: 'Prijava'});
        }
      }).catch(error => {
        if(error.response.status === 403) {
          alert("Forbidden")
        }
      })
    }, userClick: function(id) {
      const params = new URLSearchParams();
      params.append("id", id)

      this.$router.params = params
    }
  }
}
</script>