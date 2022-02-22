<template>
  <footer>
    <a href="/">Codeforces</a> &copy; 2021 by Mike Mirzayanov
    <div>
    Количество пользователей: {{ users_num }}
    </div>
    <div>
    Количество постов: {{ posts_num }}
    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer",
  props: ["users_num", "posts_num"]
}
</script>

<style scoped>

</style>