<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#import "common.ftlh" as c>
<@c.page>
    <@c.article c.findBy(posts, "id", post_id) c.findBy(users, "id", c.findBy(posts, "id", post_id).user_id) true/>
</@c.page>