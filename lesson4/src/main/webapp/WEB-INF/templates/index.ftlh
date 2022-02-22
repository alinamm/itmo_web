<#import "./common.ftlh" as c>

<@c.page>
    <ul>
        <#list posts?sort_by("id")?reverse as p>
            <#list users as u>
                <#if u.id == p.user_id><@c.article p, u, false/></#if>
            </#list>
        </#list>
    </ul>
</@c.page>
