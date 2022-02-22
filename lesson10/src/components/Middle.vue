<template>
  <div class="middle">
    <Sidebar :posts="viewPosts"/>
    <main>
      <Index v-if="page === 'Index'" :posts="Object.values(this.posts).sort((a, b) => b.id - a.id)" :login="findLogin" :comments="findComments"/>
      <Enter v-if="page === 'Enter'"/>
      <Register v-if="page === 'Register'"/>
      <WritePost v-if="page === 'WritePost'"/>
      <EditPost v-if="page === 'EditPost'"/>
      <Users v-if="page === 'Users'" :users="users"/>
      <Post v-if="page === 'Post'" :post="post" :comments="findComments" :login="findLogin" :login2="findLogin2"/>
    </main>
  </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./page/Index";
import Enter from "./page/Enter";
import WritePost from "./page/WritePost";
import EditPost from "./page/EditPost";
import Register from "./page/Register";
import Users from "./page/Users";
import Post from "../../../../lesson11/lesson11/frontend/src/components/main/Post";

export default {
  name: "Middle",
  data: function () {
    return {
      page: "Index"
    }
  },
  components: {
    Register,
    WritePost,
    Enter,
    Index,
    Sidebar,
    EditPost,
    Users,
    Post
  },
  props: ["posts", "users", "comments", "post"],
  computed: {
    viewPosts: function () {
      return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
    }
  },
  beforeCreate() {
    this.$root.$on("onChangePage", (page) => this.page = page)
    this.$root.$on("onChangePage2", (page, post) => {this.page = page; this.post=post})
  },
  methods:{
    findLogin: function (post) {
      return Object.values(this.users).find(user => user.id === post.userId)
    },
    findLogin2: function (comment) {
      return Object.values(this.users).find(user => user.id === comment.userId)
    },
    findComments: function (post) {
      return Object.values(this.comments).filter(comment => comment.postId === post.id)
    }
  }
}
</script>

<style scoped>

</style>
