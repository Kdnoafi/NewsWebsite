<template>
  <div class="articles">
    <table style="border: 1px solid grey;">
      <tr style="border: 1px solid grey;" v-for="article in sort(articles).slice(0, 10)" :key="article.id">
        <td style="border: 1px solid grey;">
          <h4><a v-bind:href="'/articles/' + article.id">{{ article.naslov }}</a></h4>
          <h6>{{ parseDate(article.vremeKreiranja) | formatDate }}</h6>
          <p v-if="article.tekst.length > '150'">{{ article.tekst.slice(0, 150) }}...</p>
          <p v-if="article.tekst.length <= '150'">{{ article.tekst }}</p>
          <a v-bind:href="'editArticle/' + article.id">Edit</a>
          <a href="#" @click="deleteArticle(article.id)">Delete</a>
        </td>
      </tr>
    </table><br>
  </div>
</template>

<script>
import moment from 'moment'
import Vue from 'vue'

Vue.filter('formatDate', function(value) {
  if (value) {
    return moment(String(value)).format('MM/DD/YYYY')
  }
});

export default {
  name: 'MostVisited',
  data() {
    return {
      articles: []
    }
  },
  mounted() {
    this.$axios.get('/api/articles').then((response) => {
      this.articles = response.data
    });
  },
  methods: {
    deleteArticle: function(id) {
      this.$axios.get('http://localhost:8080/WEB_Projekat_war_exploded/deleteArticleServlet/' + id).then(response => {
            this.$axios.get('/api/articles').then((response) => {
              this.articles = response.data
            });
            console.log(response)
          }
      )
    },
    sort: function(arr) {
      return arr.slice().sort(function(a, b) {
        return b.brojPoseta - a.brojPoseta
      });
    },
    parseDate(jsonDate) {
      var day = jsonDate.dayOfMonth;
      var month = jsonDate.monthValue - 1;
      var year = jsonDate.year;

      return new Date(Date.UTC(year, month, day));
    }
  },
  computed: {
    rows() {
      return this.articles.length
    }
  }
}
</script>

<style scoped>

</style>