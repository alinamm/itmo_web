<template>
  <div>
    <article>
      <PostInfo :login="login" :post="post"/>
      <div v-for="comment in comments(post)" :comment="comment" :key="comment.id">
        <div class="comment">{{ login2(comment).login }} : {{ comment.text }}</div>
      </div>
    </article>
  </div>
</template>

<script>
import PostInfo from "./PostInfo";

export default {
  name: "Post",
  components: {PostInfo},

  props: ["post", "comments", "login", "login2"]

}
</script>

<style scoped>

</style>