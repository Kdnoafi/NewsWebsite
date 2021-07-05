<template>
  <div id ="editCategory">
    <label for="ime">Name</label>
    <input type="text" id="ime" name="ime"  v-model="ime"><br>
    <label for="opis">Description</label>
    <input type="text" id="opis" name="opis"  v-model="opis"><br>

    <button v-on:click="click">Save Changes</button>

  </div>
</template>

<script>
import router from "../router";

export default {
  name: 'EditCategory',
  data() {
    return {
      ime: '',
      opis: ''
    }
  }, methods: {
    click: function() {
      return this.submit()
    },
    submit() {
      const params = new URLSearchParams();
      params.append("ime", this.ime)
      params.append("opis", this.opis)

      const id = this.$route.params.id

      this.$axios.post('http://localhost:8080/WEB_Projekat_war_exploded/editCategoryServlet/' + id, params).then(
          router.push({name: 'Categories'})
      )
    }
  }
}
</script>
