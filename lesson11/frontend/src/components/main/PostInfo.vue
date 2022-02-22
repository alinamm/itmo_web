<template>
  <div>
    <article>
      <div class="title"><a href="#" @click.prevent="changePage('Post', post)">{{ post.title }}</a></div>
      <div class="information">By {{ login(post).login }}</div>
      <div class="body">{{ post.text }}</div>
      <ul class="attachment">
        <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
        <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
      </ul>
      <div class="footer">
        <div class="left">
          <a href="#"><img src="../../assets/img/voteup.png" alt="Vote Up" title="Vote Up"/></a>
          <span class="positive-score">+173</span>
          <a href="#"><img src="../../assets/img/votedown.png" alt="Vote Down" title="Vote Down"/></a>
        </div>
        <div class="right">
          <a href="#"><img src="../../assets/img/date_16x16.png" alt="Publish Time" title="Publish Time"/></a>
          <a href="#"><img src="../../assets/img/comments_16x16.png" alt="Comments" title="Comments"/></a>
          <a href="#"> {{ post.comments.length }}</a>
        </div>
      </div>
    </article>
  </div>
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
article {
  margin-bottom: 2em;
}

article .title {
  color: var(--caption-color);
  font-weight: bold;
  font-size: 1.25rem;
}

article .information {
  margin-top: 0.25rem;
  font-size: 0.85rem;
  color: #888;
}

article .body {
  border-left: 4px solid var(--border-color);
  padding-left: 0.75rem;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

article .body p:last-child {
  margin: 0;
}

article .attachment {
  padding: 0;
  margin: 0.5rem 0 0 0;
}
</style>