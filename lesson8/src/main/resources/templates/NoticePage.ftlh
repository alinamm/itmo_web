<#import "commons.ftlh" as c>

<@c.page>
    <div class="article form-box">
        <div class="header">Notice</div>
        <div class="body">
            <form method="post" action="">
                <div class="field">
                    <div class="name">
                        <label for="content">Content</label>
                    </div>
                    <div class="value">
                        <textarea id="content" name="content">${newNotice.content!}</textarea>
                    </div>
                    <@c.error "newNotice.content"/>
                </div>

                <div class="button-field">
                    <input type="submit" value="publish">
                </div>
            </form>
        </div>
    </div>
</@c.page>