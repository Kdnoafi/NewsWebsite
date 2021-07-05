<template>
  <div id="editArticle">
    <label for="naslov">Title</label>
    <input type="text" id="naslov" name="naslov" v-model="naslov"><br>
    <label for="kategorija">Category</label>
    <input type="text" id="kategorija" name="kategorija" v-model="kategorija"><br>
    <label for="tekst">Article text</label>
    <input type="text" id="tekst" name="tekst" v-model="tekst"><br>
    <label for="tagovi">Tags</label>
    <input type="text" id="tagovi" name="tagovi" v-model="tagovi"><br>

    <button v-on:click="click">Save changes</button>

  </div>
</template>

<script>
import router from "../router";

export default {
  name: 'EditArticle',
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
      const url = new URL(top.location.href)
      const id = url.pathname.substring(url.pathname.lastIndexOf('/') + 1)

      const params = new URLSearchParams();
      params.append("naslov", this.naslov)
      params.append("kategorija", this.kategorija)
      params.append("tekst", this.tekst)
      params.append("tagovi", this.tagovi)
      params.append("id", id)

      this.$axios.post('http://localhost:8080/WEB_Projekat_war_exploded/editArticleServlet/' + id, params).then(
          router.push({name: 'Articles'})
      )
    }
  }
}
</script>