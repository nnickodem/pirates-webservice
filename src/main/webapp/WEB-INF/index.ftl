<#import "/spring.ftl" as spring/>
<html lang="en">
    <head>
        <title>Spring with FreeMarker</title>
    </head>
    <body>
        <p>Get your greeting <a href="/greeting">here</a></p>

        <@spring.bind "formInput" />
        <form method="post" action="/greeting">
            ID: <br/>
            <@spring.formInput "formInput.id"/>
            <br/>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>