<#-- @ftlvariable name="title" type="java.lang.String" -->
<#-- @ftlvariable name="text" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="article form-box">
        <div class="header">Article</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="publish"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input id="title" name="title" value="${title!}"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" type="text">${text!}</textarea>
                    </div>
                </div>
                <div class="error">${error!}</div>
                <div class="button-field">
                    <input type="submit" value="publish">
                </div>
            </form>
        </div>
    </div>

    <script>
        $(function () {
            $(".article").submit(function () {
                const data = {}
                data.action = "publish"
                data.title = $(this).find("input[name='title']").val();
                data.text = $(this).find("textarea[name='text']").val();

                const $error = $(this).find(".error");
                ajax(data, $error);

                return false;
            });
        })
    </script>
</@c.page>