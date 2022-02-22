<template>
  <div>
    <article v-for="post in posts" :post="post" :key="post.id">
      <PostInfo :login="login" :post="post"/>
      <div class="comments">Number of comments: {{ comments(post).length }}</div>
    </article>
  </div>
</template>

<script>
import PostInfo from "./PostInfo";
export default {
  name: "Index",
  components: {PostInfo},
  props: ["posts", "login", "comments"],
  methods: {
    changePage: function (page, post) {
      this.$root.$emit("onChangePage2", page, post);
    }
  }
}
</script>

<style scoped>

</style>
