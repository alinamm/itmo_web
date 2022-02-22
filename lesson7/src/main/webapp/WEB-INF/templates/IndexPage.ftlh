<#-- @ftlvariable name="error" type="java.lang.String" -->

<#import "commons.ftlh" as c>

<@c.page>
    <main>
        <template>
            <article>
                <div class="title"></div>
                <div class="information"></div>
                <div class="body"></div>
                <ul class="attachment">
                    <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
                    <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
                </ul>
                <div class="footer">
                    <div class="left">
                        <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                        <span class="positive-score">+173</span>
                        <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                    </div>
                    <div class="right">
                        <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        2 days ago
                        <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">68</a>
                    </div>
                </div>
            </article>
        </template>
        <div class="noData">
            No Data
        </div>
        <div class="error">${error!}</div>
    </main>


    <script>
        $(function () {
            const $noData = $(".noData");
            $noData.find(".noData").text("Loading...");

            function success(response) {

                response["articles"].forEach(function (article) {
                    $noData.hide();
                    const $article = $($("template").prop("content")).clone();
                    $article.find(".title").text(article["title"]);
                    $article.find(".information").text("By " + response["login"][article["userId"]] + " " + article["creationTime"])
                    $article.find(".body").text(article["text"]);
                    $("main").append($article);
                });
                return false;
            }

            const $error = $(this).find(".error");
            const data = {}
            data.action = "findAll"
            ajax(data, $error, success)
        })
    </script>
</@c.page>
