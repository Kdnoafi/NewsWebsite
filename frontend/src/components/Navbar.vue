<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <router-link :to="{name: 'Articles'}" tag="a" class="nav-link">Articles</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{name: 'Categories'}" tag="a" class="nav-link">Categories</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{name: 'MostVisited'}" tag="a" class="nav-link">Most visited</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "Navbar"
}
</script>

<style scoped>

</style>