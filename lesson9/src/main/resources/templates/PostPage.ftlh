<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#import "commons.ftlh" as c>
<#import "/spring.ftl" as spring>

<@c.page>
    <#if post??>
        <@c.post post=post/>
        <#if user??>
            <form method="post" action="">
                <input type="hidden" name="action" value="publish"/>
                <div class="field">
                    <div class="name">
                        <label for="text">Comment</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text">${newComment.text!}</textarea>
                    </div>
                    <@c.error "newComment.text"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="publish">
                </div>
            </form>
        </#if>

        <#list post.comments as comment>
            <div class="comment">${comment.user.login}: ${comment.text}</div>
        </#list>
    <#else>
        No such post
    </#if>

</@c.page>