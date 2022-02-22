<template>
  <article>
    <div class="title"><a href="#" @click.prevent="changePage('Post', post)">{{ post.title }}</a></div>
    <div class="information">By {{ login(post).login }}</div>
    <div class="body">{{ post.text }}</div>
  </article>
</template>

<script>
export default {
  name: "PostInfo",
  props: ["post", "login"],
  methods: {
    changePage: function (page, post) {
      this.$root.$emit("onChangePage2", page, post);
    }
  }
}
</script>

<style scoped>

</style>