<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:url value="/style.css" var="styleUrl"/>
        <c:url value="/index.js" var="indexScriptUrl"/>
        <c:url value="/login.js" var="loginScriptUrl"/>
        <c:url value="/profile.js" var="profileScriptUrl"/>
        <c:url value="/back-to-profile.js" var="backToProfileScriptUrl"/>
        <c:url value="/poem.js" var="poemScriptUrl"/>
        <c:url value="/poems.js" var="poemsScriptUrl"/>
        <c:url value="/logout.js" var="logoutScriptUrl"/>
        <link rel="stylesheet" type="text/css" href="${styleUrl}">
        <script src="${indexScriptUrl}"></script>
        <script src="${loginScriptUrl}"></script>
        <script src="${poemScriptUrl}"></script>
        <script src="${poemsScriptUrl   }"></script>
        <script src="${profileScriptUrl}"></script>
        <script src="${backToProfileScriptUrl}"></script>
        <script src="${logoutScriptUrl}"></script>
        <title>App</title>
    </head>
<body>
<div id="login-content" class="content">
    <h1>Login</h1>
    <form id="login-form" onsubmit="return false;">
        <input type="text" name="email">
        <input type="password" name="password">
        <button id="login-button">Login</button>
    </form>
</div>
<div id="profile-content" class="hidden content">
    <h1>Profile</h1>
    <p>Name: <span id="poet-email"></span></p>
    <p>List poems:</p>
    <ul>
        <li><a href="javascript:void(0);" onclick="onPoemsClicked()">Poems</a> </li>
    </ul>

</div>

<div id="back-to-profile-content" class="hidden content">
    <button onclick="onBackToProfileClicked();">Back to profile</button>
</div>
<div id="logout-content" class="hidden content">
    <button id="logout-button">Logout</button>
</div>

<div id = "poems-content" class="hidden content">
    <h1>Poems</h1>
    <table id = "poems">
        <thead>
        <tr>
            <th>Id </th>
            <th>Title</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
    <form id="poem-form" onsubmit="return false;">
        <input type="text" name="title">
        <input type="text" name="content">

        <button onclick="onPoemAddClicked();">Add</button>
    </form>
</div>

<div id = "poem-content" class="hidden content">
    <h1>Poem</h1>
    <p>Title: <span id = "poem-title"></span> </p>
    <p>Content: <span id="poe-content"></span></p>

</div>

</body>
</html>
