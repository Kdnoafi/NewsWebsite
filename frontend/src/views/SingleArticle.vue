<template>
 <div>
   <h1>{{ article.naslov }}</h1>
   <p>{{ parseDate(article.vremeKreiranja) | formatDate }}</p>
   <h5>{{ author.ime }} {{ author.prezime }}</h5>
   <p>{{ article.tekst }}</p>
   <h4>Tags</h4>
   <ul v-for="tag in splitTags(article.tagovi)" :key="tag">
     <a v-on:click="tagClick(tag)">{{ tag }}</a>
   </ul>

   <h3>Comments</h3>
   <ul v-for="comment in comments" :key="comment.id">
       <h5>{{ comment.imeAutora }}</h5>
       <p>{{ comment.tekst }}</p>
   </ul>

   <h4>New comment</h4>
   <label for="tekst">Text</label>
   <input type="text" id="tekst" name="tekst" v-model="tekst"><br>

   <button v-on:click="click">Comment</button>

 </div>
</template>

<script>
import Vue from "vue";
import moment from "moment";
import router from "../router";

Vue.filter('formatDate', function(value) {
  if (value) {
    return moment(String(value)).format('DD/MM/YYYY')
  }
});

export default {
  name: "SingleArticle",
  data() {
    return {
      article: undefined,
      user: {},
      author: {},
      comments: [],
      tekst: ''
    }
  }, mounted() {
    const id = this.$route.params.id
    this.$axios.get('/api/articles/' + id).then((response) => {
      this.article = response.data

      this.$axios.get('/api/comments/' + this.article.id).then((response) => {
        this.comments = response.data
      });

      this.$axios.get('/api/users/' + this.article.autorId).then((response) => {
        this.author = response.data
      });

      // update visits
      this.$axios.get('http://localhost:8080/WEB_Projekat_war_exploded/articles/' + this.article.id);
    });
  },
  methods: {
    click: function() {
      return this.submit()
    },
    submit() {
      const params = new URLSearchParams();
      params.append("tekst", this.tekst)
      //params.append("imeAutora", this.user.ime)
      params.append("articleId", this.article.id)
      params.append('jwt', localStorage.getItem('jwt'))

      this.$axios.post('http://localhost:8080/WEB_Projekat_war_exploded/noviKomentar', params).then(
          this.$axios.get('/api/comments/' + this.article.id).then((response) => {
            this.comments = response.data
          })
      )
    },
    parseDate(jsonDate) {
      var day = jsonDate.dayOfMonth;
      var month = jsonDate.monthValue - 1;
      var year = jsonDate.year;

      return new Date(Date.UTC(year, month, day));
    },
    splitTags(tags) {
      return tags.split(',');
    },
    tagClick: function(tag) {
      router.push({name: 'Articles', query: {tag: tag}});
    }
  },
}
</script>

<style scoped>

</style>