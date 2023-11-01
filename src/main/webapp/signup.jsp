<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup Form</title>
    <link rel="stylesheet" href="styles/signup-style.css">
</head>
<body>
<div class="wrapper">

    <div class="left-section">
        <div class="image-credit">
            Photo by <a href="https://unsplash.com/@thejoltjoker?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Johannes Andersson</a> on
            <a href="https://unsplash.com/s/photos/nature?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Unsplash</a>
        </div>
    </div>


    <div class="right-section">

        <div class="headers">
            <h1>
                This is not a real online service! You know you need something like this in your life to help you
                realize your deepest dreams.<br>
                Sign up <em>now</em> to get started.<br>
            </h1>
            <h1>You know you want to.</h1>
        </div>

        <div class="forms">
            <h1>Let's do this!</h1>

            <h3 style="color: red">${error}</h3>

            <form action="/signup" method="post" class="form-area">

                <div class="name-area">
                    <div>
                        <label for="firstName">FIRST NAME</label>
                        <input type="text" id="firstName" name="first_name" placeholder="enter your name" value = "${first_name}" required>
                    </div>
                    <div>
                        <label for="lastName">LAST NAME</label>
                        <input type="text" id="lastName" name="last_name" placeholder="enter your surname" value = "${last_name}" required>
                    </div>
                </div>

                <div class="contact-area">
                    <div>
                        <label for="email">EMAIL</label>
                        <input type="email" id="email" name="email" placeholder="sample@email.com" required>
                    </div>
                    <div>
                        <label for="phoneNumber">PHONE NUMBER</label>
                        <input type="tel" name="phone_number" id="phoneNumber" placeholder="+49 500 400 30" value="${phone_number}" required>
                    </div>
                </div>

                <div class="password-area">
                    <div>
                        <label for="password">PASSWORD</label>
                        <label>
                            <input type="password" id="password" placeholder="enter password" name="password" required>
                        </label>
                    </div>
                    <div>
                        <label for="password-repeat">REPEAT PASSWORD</label>
                        <label>
                            <input type="password" id="password-repeat" placeholder="repeat password" name="password-repeat" required>
                        </label>
                    </div>
                </div>

                <button type="submit">Create account</button>
            </form>
        </div>

        <div>
            Already have an account? <a href="/login" style="color: red; font-size: 16px;font-weight: 500;">Login</a>
        </div>

    </div>
</div>
</body>
</html>