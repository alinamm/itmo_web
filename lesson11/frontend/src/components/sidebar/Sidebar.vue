<template>
    <aside>
        <SidebarPost v-for="post in posts" :post="post" :key="post.id"/>
    </aside>
</template>

<script>
import SidebarPost from "./SidebarPost";

export default {
  name: "Sidebar",
  components: {
    SidebarPost
  },
  props: ["posts"],
  beforeCreate() {
    this.$root.$on("onChangePage", (page) => this.page = page)
    this.$root.$on("onChangePage2", (page, post) => {this.page = page; this.post=post})
  }
}
</script>

<style scoped>

</style>
