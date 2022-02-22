<template>
  <div>
    <div v-for="post in posts" :post="post" :key="post.id">
      <PostInfo :post="post" :login="login"/>
    </div>
  </div>
</template>

<script>
import PostInfo from "./PostInfo";
export default {
  name: "Index",
  components: {PostInfo},
  props: ["posts", "login"]
}
</script>

<style scoped>

</style>
