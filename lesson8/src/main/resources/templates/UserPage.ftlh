<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <div class="id">${user.id!}</div>
        <div class="login">${user.login!}</div>
        <div class="creationTime">${user.creationTime!}</div>
    <#else>
        No such user
    </#if>
</@c.page>
