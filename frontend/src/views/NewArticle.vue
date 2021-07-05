<template>
  <div>
    <label for="naslov">Title</label>
    <input type="text" id="naslov" name="naslov" v-model="naslov"><br>
    <label for="kategorija">Category</label>
    <input type="text" id="kategorija" name="kategorija" v-model="kategorija"><br>
    <label for="tekst">Article text</label>
    <input type="text" id="tekst" name="tekst" v-model="tekst"><br>
    <label for="tagovi">Tags</label>
    <input type="text" id="tagovi" name="tagovi" v-model="tagovi"><br>

    <button v-on:click="click">Add article</button>

  </div>
</template>

<script>
import router from "../router";

export default {
  name: 'NewArticle',
  data() {
    return {
      naslov: '',
      kategorija: '',
      tekst: '',
      tagovi: ''
    }
  }, methods: {
    click: function() {
      return this.submit()
    },
    submit() {
      const params = new URLSearchParams();
      params.append("naslov", this.naslov)
      params.append("kategorija", this.kategorija)
      params.append("tekst", this.tekst)
      params.append("tagovi", this.tagovi)
      params.append('jwt', localStorage.getItem('jwt'))

      this.$axios.post('http://localhost:8080/WEB_Projekat_war_exploded/novaVest', params).then(response => {
        if(response.status === 200) {
          router.push({name: 'Articles'});
        }
      }).catch(error => {
        if(error.status === 500) {
          alert("Error adding article")
        }
      })
    }
  }
}
</script>