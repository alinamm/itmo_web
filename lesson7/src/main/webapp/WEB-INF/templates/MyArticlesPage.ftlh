<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="article datatable">
        <div class="caption">Articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Created</th>
            </tr>
            </thead>
            <tbody>
            <#list articles as article>
                <tr>
                    <td>${article.id}</td>
                    <td>${article.title}</td>

                    <th class="bottom">
                        <div class="button-field">
                            <#if article.hidden>
                                <input type="submit" value="Show" class="button-field1" data-articleId="${article.id}">
                            <#else>
                                <input type="submit" value="Hide" class="button-field1" data-articleId="${article.id}">
                            </#if>
                        </div>
                    </th>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>


    <script>
        $(function () {
            const $error = $(this).find(".error");
            const $tbody = $(".article").find("tbody");

            $tbody.find(".button-field1").click(function () {
                const $bottom = $(this);
                const req = {};
                req.action = "changeStatus";
                req.artId = $bottom.attr("data-articleId");
                if ($bottom.attr("value") === "Show") {
                    req.status = false;
                } else if ($bottom.attr("value") === "Hide") {
                    req.status = true;
                } else {
                    req.status = -1;
                }

                function success() {
                    if ($bottom.attr("value") === "Show") {
                        $bottom.val("Hide")
                    } else if ($bottom.attr("value") === "Hide") {
                        $bottom.val("Show")
                    }
                }
                ajax($error, req, success);
            })
        })
    </script>
</@c.page>
