<template>
  <div class="articles">
    <table style="border: 1px solid grey;">
      <tr style="border: 1px solid grey;" v-for="article in sort(articles).slice(0, 10)" :key="article.id">
        <td v-if="tagPresent === false" style="border: 1px solid grey;">
          <h4><a v-bind:href="'/articles/' + article.id">{{ article.naslov }}</a></h4>
          <h6>{{ users[article.autorId] }}</h6>
          <h6>{{ parseDate(article.vremeKreiranja) | formatDate }}</h6>
          <p v-if="article.tekst.length > '150'">{{ article.tekst.slice(0, 150) }}...</p>
          <p v-if="article.tekst.length <= '150'">{{ article.tekst }}</p>
          <a v-bind:href="'editArticle/' + article.id">Edit</a>
          <a href="#" @click="deleteArticle(article.id)">Delete</a>
        </td>
        <td v-if="tagPresent === true && article.tagovi.includes(tag)" style="border: 1px solid grey;">
          <h4><a v-bind:href="'/articles/' + article.id">{{ article.naslov }}</a></h4>
          <h6>{{ users[article.autorId] }}</h6>
          <h6>{{ parseDate(article.vremeKreiranja) | formatDate }}</h6>
          <p v-if="article.tekst.length > '150'">{{ article.tekst.slice(0, 150) }}...</p>
          <p v-if="article.tekst.length <= '150'">{{ article.tekst }}</p>
          <a v-bind:href="'editArticle/' + article.id">Edit</a>
          <a href="#" @click="deleteArticle(article.id)">Delete</a>
        </td>
      </tr>
    </table><br>

    <button v-on:click="click">New article</button><br><br>

  </div>
</template>

<script>
import moment from 'moment'
import Vue from 'vue'
import router from "../router";

Vue.filter('formatDate', function(value) {
  if (value) {
    return moment(String(value)).format('DD/MM/YYYY')
  }
});

export default {
  name: 'Articles',
  data() {
    return {
      articles: [],
      users: {},
      tagPresent: false,
      tag: ''
    }
  },
  mounted() {
    if(this.$route.query.tag !== undefined) {
      this.$axios.get('/api/articles').then((response) => {
        this.tagPresent = true
        this.tag = this.$route.query.tag
        this.articles = response.data
      });
    } else {
      if (this.$route.params.id === undefined) {
        this.$axios.get('/api/articles').then((response) => {
          this.articles = response.data
        });
      } else {
        this.$axios.get('/api/articles/category/' + this.$route.params.id).then((response) => {
          this.articles = response.data
        });
      }
    }

    /*this.articles.forEach(function(article) {
      this.$axios.get('http://localhost:8080/WEB_Projekat_war_exploded/api/users/' + article.autorId).then((response) => {
        this.users[article.autorId] = response.data
      });
    })*/
  },
  methods: {
    click: function() {
      return this.submit()
    },
    submit() {
      router.push({name: 'NewArticle'});
    },
    deleteArticle: function(id) {
      this.$axios.get('http://localhost:8080/WEB_Projekat_war_exploded/deleteArticleServlet/' + id).then(response => {
        if(this.$route.params.id === undefined) {
          this.$axios.get('/api/articles').then((response) => {
            this.articles = response.data
          });
        } else{
          this.$axios.get('/api/articles/category/' + this.$route.params.id).then((response) => {
            this.articles = response.data
          });
        }
        console.log(response)
      }
      )
    },
    sort: function(arr) {
      return arr.slice().sort(function(a, b) {
        return b.vremeKreiranja - a.vremeKreiranja;
      });
    },
    parseDate(jsonDate) {
      var day = jsonDate.dayOfMonth;
      var month = jsonDate.monthValue - 1;
      var year = jsonDate.year;

      return new Date(Date.UTC(year, month, day));
    },
  },
  computed: {
    rows() {
      return this.articles.length
    }
  }
}
</script>

<style>
a:link {
  color: black;
  background-color: transparent;
  text-decoration: none;
}
a:visited {
  color: black;
  background-color: transparent;
  text-decoration: none;
}
a:hover {
  color: black;
  background-color: transparent;
  text-decoration: underline;
}
a:active {
  color: black;
  background-color: transparent;
  text-decoration: none;
}

.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
  border: 1px solid #4CAF50;
}

.no_header {
  display: none;
}

.custom-border > td {
  border: 1px solid black;
}

.custom-border > td:nth-child(1) {
  font-weight: bold;
  font-size: 150%;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>