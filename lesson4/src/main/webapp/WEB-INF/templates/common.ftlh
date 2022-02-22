<#-- @ftlvariable name="currentState" type="String" -->
<#-- @ftlvariable name="posts" type="ru.itmo.web.lesson4.model.Post[]" -->
<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar posts/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <#if currentState == item.href>
                            <div class="mark">
                                <a href="${item.href}">${item.name}</a>
                            </div>
                        <#else>
                            <div class="unmarked">
                                <a href="${item.href}">${item.name}</a>
                            </div>
                        </#if>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar posts>
<#-- @ftlvariable name="posts" type="ru.itmo.web.lesson4.model.Post[]" -->
    <aside>
        <#list posts?sort_by("id")?reverse as p>
            <section>
                <div class="header">Post#${p.id}</div>
                <div class="body">
                    ${p.text?truncate(250)}...
                </div>
                <div class="footer">
                    <a href="/post?post_id=${p.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro userlink user>
<#-- @ftlvariable name="user" type="ru.itmo.web.lesson4.model.User" -->
    <a href="/user?handle=${user.handle}">${user.name}</a>
</#macro>

<#macro userpage user, posts>
<#-- @ftlvariable name="posts" type="ru.itmo.web.lesson4.model.Post[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.web.lesson4.model.User" -->
    <div class="userpage">
        <#if user?has_content>
            <div class="name"><@userlink user/></div>
            <ul>
                <li>
                    <div class="item">id: ${user.id}</li>
                <li>
                    <div class="item">handle: ${user.handle}</li>
                <li>
                    <div class="item">name: ${user.name}</li>
                <li>
                    <#assign x = 0>
                    <#list posts as p>
                        <#if p.user_id == user.id><#assign x++></#if>
                    </#list>
                    <a href="/posts?user_id=${user.id}">${x}</a>
                </li>
            </ul>
            <img src="img/no-title.jpg" title="No-title" alt="No-title"/>

            <div class="links">
                <div class="prev">
                    <#assign prevUser = getPrev(users, "id", user.id)!/>
                    <#if prevUser?has_content>
                        <div class="next">
                            <a href="/user?handle=${prevUser.handle}">🠐</a>
                        </div>
                    <#else>
                        <div class="last">🠐</div>
                    </#if>
                </div>

                <div class="next">
                    <#assign nextUser = getNext(users, "id", user.id)!/>
                    <#if nextUser?has_content>
                        <div class="next">
                            <a href="/user?handle=${nextUser.handle}">🠒</a>
                        </div>
                    <#else>
                        <div class="last">🠒</div>
                    </#if>
                </div>
            </div>
        <#else>
            No such user
        </#if>
    </div>
</#macro>

<#function getNext items key value>
    <#list items?sort_by("id") as item>
        <#if item[key] gt value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function getPrev items key value>
    <#list items?sort_by("id")?reverse as item>
        <#if item[key] lt value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#macro datatable users>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Id</th>
                <th>Handle</th>
            </tr>
            </thead>
            <tbody>
            <#list users as u>
                <tr>
                    <td><@userlink u/></td>
                    <td>${u.id}</td>
                    <td>${u.handle}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</#macro>

<#macro article post, user, full=false>
<#-- @ftlvariable name="user" type="ru.itmo.web.lesson4.model.User" -->
<#-- @ftlvariable name="post" type="ru.itmo.web.lesson4.model.Post" -->
    <article>
        <div class="title">${post.title}</div>
        <div class="information">By ${user.handle}, 2 days ago, translation</div>
        <div class="body">
            <#if full>
                <div class="alltext">
                    ${post.text}
                </div>
            <#else>
                <div class="partoftext">
                    ${post.text?truncate(250)}...
                </div>
            </#if>
        </div>
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
</#macro>


<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>
