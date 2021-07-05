<template>
  <div>
    <label for="ime">Name</label>
    <input type="text" id="ime" name="ime"  v-model="ime"><br>
    <label for="opis">Description</label>
    <input type="text" id="opis" name="opis"  v-model="opis"><br>

    <button v-on:click="click">Add category</button>

  </div>
</template>

<script>
import router from "../router";

export default {
  name: 'NewCategory',
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

      this.$axios.post('http://localhost:8080/WEB_Projekat_war_exploded/novaKategorija', params).then(
          router.push({name: 'Categories'})
      )
    }
  }
}
</script>

<style scoped>

</style>