<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Form</title>
    <script defer src="./assets/scripts/script.js"></script>
    <style><%@ include file="assets/styles/quiz-style.css"%></style>
</head>
<body>
<div class="wrapper">

    <div class="form-container">
        <h1>Quiz time!</h1>

        <form id="quiz-form" action="#" class="form-area">
            <div id="question-container"></div>
            <div class="button-container">
                <button type="button" onclick="addQuestion('question-container')">add question</button>
                <button type="button" onclick="collectValuesAndPost()">submit</button>
            </div>
        </form>
    </div>

</div>
</body>
</html>