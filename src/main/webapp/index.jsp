<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <title>Home</title>
    <style>
        .navigation{
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }
    </style>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<div class="navigation">
    <a href="hello-servlet">Hello Servlet</a>
    <a href="signup">Signup</a>
    <a href="about">About</a>
</div>
</body>
</html>