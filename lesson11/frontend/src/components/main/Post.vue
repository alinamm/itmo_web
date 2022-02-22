<template>
  <div>
  <PostInfo :post="post" :login="login"/>
    <div v-for="comment in post.comments" :comment="comment" :key="comment.id">
      {{ comment.user.login}}: {{ comment.text }}
    </div>
  </div>
</template>

<script>
import PostInfo from "./PostInfo";

export default {
  name: "Post",
  components: {PostInfo},
  props: ["post", "login"],
  beforeCreate() {
    this.$root.$on("onChangePage", (page) => this.page = page)
    this.$root.$on("onChangePage2", (page, post) => {this.page = page; this.post=post})
  }
}
</script>

<style scoped>

</style>