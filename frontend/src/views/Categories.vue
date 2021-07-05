<template>
  <div>
    <table style="border: 1px solid grey;">
      <tr style="border: 1px solid grey;" v-for="category in categories" :key="category.id">
        <td style="border: 1px solid grey;">
          <h5><a v-bind:href="'/articles/category/' + category.id" v-on:click="categoryClick(category.id)">{{ category.ime }}</a></h5>
          <p>{{ category.opis}}</p>
          <a v-bind:href="'/editCategory/' + category.id">Edit</a>
          <a href="#" @click="deleteCategory(category.id)">Delete</a>
        </td>
      </tr>
    </table><br>

    <button v-on:click="click">New category</button>

  </div>
</template>

<script>
import router from "../router";

export default {
  name: 'Categories',
  data() {
    return {
      categories: []
    }
  },
  mounted() {
    this.$axios.get('/api/categories').then((response) => {
      this.categories = response.data
    });
  },
  methods: {
    click: function() {
      return this.submit()
    },
    submit() {
      router.push({name: 'NewCategory'});
    }, categoryClick: function(id) {
      const params = new URLSearchParams();
      params.append("categoryId", id)
      this.$router.params = params
    },
    deleteCategory: function(id) {
      this.$axios.get('http://localhost:8080/WEB_Projekat_war_exploded/deleteCategoryServlet/' + id).then(response => {
            if(this.$route.params.id === undefined) {
              this.$axios.get('/api/categories').then((response) => {
                this.categories = response.data
              });
            } else{
              this.$axios.get('/api/articles/category/' + this.$route.params.id).then((response) => {
                this.articles = response.data
              });
            }
            console.log(response)
          }
      )
    }
  }
}
</script>