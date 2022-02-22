<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#list posts as p>
        <#if p.user_id == c.findBy(users, "id", user_id).id><@c.article p, c.findBy(users, "id", user_id)/></#if>
    </#list>
</@c.page>