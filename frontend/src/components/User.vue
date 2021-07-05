<template>
  <div>
    <h2>
      User: {{ user.ime }}
    </h2>

    <h5>Description:</h5>
    <p>
      {{ subject.prezime }} <br><br>
    </p>
  </div>
</template>

<script>

export default {
  name: "User",
  props: {
    user: {
      type: Object,
      required: true,
    }
  },
}
</script>

<style scoped>

</style>
