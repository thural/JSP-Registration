<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <title>Profile</title>
    <link rel="stylesheet" href="./profile-style.css">
</head>

<body>

<div class="header">

    <div class="top flex">
        <div class="search flex">
            <img src="./resources/icons/magnify.svg" alt="">
            <label for="search-box"></label>
            <input type="text" name="search">
        </div>
        <div class="info flex">
            <img src="./icons/bell-ring-outline.svg" alt="">
            <span class="avatar"></span>
            <h3>${user.firstName}</h3>
        </div>
    </div>

    <div class="bottom flex">
        <div class="name">
            <span class="avatar"></span>
            <div>
                <p>${error}</p>
                <p>Hi there,</p>
                <h2>${user.firstName}</h2>
            </div>
        </div>
        <div class="buttons">
            <button type="button">New</button>
            <button type="button">Upload</button>
            <button type="button">Share</button>
        </div>
    </div>

</div>

<div class="sidebar">
    <div class="dashboard-logo">
        <h2><img src="./resources/icons/view-dashboard.svg">Dashboard</h2>
    </div>
    <div class="navigation">
        <ul>
            <li><a href="#"><img src="./resources/icons/home.svg">Home</a></li>
            <li><a href="#"><img src="./resources/icons/card-account-mail-outline.svg">Profile</a></li>
            <li><a href="#"><img src="./resources/icons/message-reply.svg">Messages</a></li>
            <li><a href="#"><img src="./resources/icons/history.svg">History</a></li>
            <li><a href="#"><img src="./resources/icons/calendar-check-outline.svg">Tasks</a></li>
            <li><a href="#"><img src="./resources/icons/account-multiple.svg">Communities</a></li>
        </ul>
    </div>
    <div class="branding">
        <ul>
            <li><a href="#"><img src="./resources/icons/cog.svg">Settings</a></li>
            <li><a href="#"><img src="./resources/icons/help-circle.svg">Support</a></li>
            <li><a href="#"><img src="./resources/icons/shield-account.svg">Privacy</a></li>
        </ul>
    </div>
</div>

<div class="content">
    <div class="main-content">
        <h2>Your Projects</h2>
        <div>
            <div class="card repo">
                <h3>Super cool project</h3>
                <p>
                    is simply dummy text of the printing and typesetting industry.
                    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
                </p>
                <div class="repo icons">
                    <a href="#"><img src="./resources/icons/star-outline.svg" alt=""></a>
                    <a href=""><img src="./resources/icons/eye.svg" alt=""></a>
                    <a href=""><img src="./resources/icons/directions-fork.svg" alt=""></a>
                </div>
            </div>
            <div class="card repo">
                <h3>Super cool project</h3>
                <p>
                    is simply dummy text of the printing and typesetting industry.
                    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
                </p>
                <div class="repo icons">
                    <a href="#"><img src="./resources/icons/star-outline.svg" alt=""></a>
                    <a href=""><img src="./resources/icons/eye.svg" alt=""></a>
                    <a href=""><img src="./resources/icons/directions-fork.svg" alt=""></a>
                </div>
            </div>
            <div class="card repo">
                <h3>Super cool project</h3>
                <p>
                    is simply dummy text of the printing and typesetting industry.
                    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
                </p>
                <div class="repo icons">
                    <a href="#"><img src="./resources/icons/star-outline.svg" alt=""></a>
                    <a href=""><img src="./resources/icons/eye.svg" alt=""></a>
                    <a href=""><img src="./resources/icons/directions-fork.svg" alt=""></a>
                </div>
            </div>
            <div class="card repo">
                <h3>Super cool project</h3>
                <p>
                    is simply dummy text of the printing and typesetting industry.
                    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
                </p>
                <div class="repo icons">
                    <a href="#"><img src="./resources/icons/star-outline.svg" alt=""></a>
                    <a href=""><img src="./resources/icons/eye.svg" alt=""></a>
                    <a href=""><img src="./resources/icons/directions-fork.svg" alt=""></a>
                </div>
            </div>
            <div class="card repo">
                <h3>Super cool project</h3>
                <p>
                    is simply dummy text of the printing and typesetting industry.
                    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
                </p>
                <div class="repo icons">
                    <a href="#"><img src="./resources/icons/star-outline.svg" alt=""></a>
                    <a href=""><img src="./resources/icons/eye.svg" alt=""></a>
                    <a href=""><img src="./resources/icons/directions-fork.svg" alt=""></a>
                </div>
            </div>
            <div class="card repo">
                <h3>Super cool project</h3>
                <p>
                    is simply dummy text of the printing and typesetting industry.
                    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
                </p>
                <div class="repo icons">
                    <a href="#"><img src="./resources/icons/star-outline.svg" alt=""></a>
                    <a href=""><img src="./resources/icons/eye.svg" alt=""></a>
                    <a href=""><img src="./resources/icons/directions-fork.svg" alt=""></a>
                </div>
            </div>
        </div>
    </div>
    <div class="side-content">

        <h2>Announcements</h2>
        <div class="card announc">
            <h3>Site Maintenance</h3>
            <p>
                is simply dummy text of the printing and typesetting industry.
                Lorem Ipsum has been the industry's ...
            </p>
            <hr>
            <h3>Community Share Day</h3>
            <p>
                is simply dummy text of the printing and typesetting industry.
                Lorem Ipsum has been the industry's ...
            </p>
            <hr>
            <h3>Privacy Policy Update</h3>
            <p>
                is simply dummy text of the printing and typesetting industry.
                Lorem Ipsum has been the industry's ...
            </p>
        </div>

        <h2>Trending</h2>
        <div class="card trending">

            <div>
                <div class="avatar"></div>
                <div>
                    <h3>@alex</h3>
                    <p>World Peace Builder</p>
                </div>
            </div>

            <div>
                <div class="avatar"></div>
                <div>
                    <h3>@corgy</h3>
                    <p>Super Cool Project</p>
                </div>
            </div>

            <div>
                <div class="avatar"></div>
                <div>
                    <h3>@johnny</h3>
                    <p>Life Changing App</p>
                </div>
            </div>

            <div>
                <div class="avatar"></div>
                <div>
                    <h3>@tommy</h3>
                    <p>Zero Fee Maker</p>
                </div>
            </div>

        </div>

    </div>
</div>


</body>

</html>