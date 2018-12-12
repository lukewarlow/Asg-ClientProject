<#macro style>

</#macro>

<#macro page_body>

</#macro>

<#macro modals>

</#macro>

<#macro scripts>

</#macro>

<#macro display_page title="Dashboard">
<!DOCTYPE html>
    <html lang="en">

    <head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>${title}</title>

    <!-- Fontfaces CSS-->
    <link href="/css/font-face.css" type="text/css" rel="stylesheet" media="all">
    <link href="/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link rel="shortcut icon" href="/images/icon/favicon.png">

    <!-- Bootstrap CSS-->
    <link href="/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="/vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
    <link href="/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="/vendor/wow/animate.css" rel="stylesheet" media="all">
    <link href="/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="/vendor/slick/slick.css" rel="stylesheet" media="all">
    <link href="/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="/css/theme.css" rel="stylesheet" media="all">

    <style>
        .guest, .candidate, .instructor, .admin, .card {
            display: none;
        }

        [class^=”stage-”] {
            display: none !important;
        }
    </style>

    <@style></@style>

</head>
<body>
    <div class="page-wrapper">
    <!-- HEADER MOBILE-->
    <header class="header-mobile d-block d-lg-none">
        <div class="header-mobile__bar">
            <div class="container-fluid">
                <div class="header-mobile-inner">
                    <a class="logo" href="/">
                        <img src="/images/icon/asg_logo.png" width="60%" alt="AviationSystemsGroup"/>
                    </a>
                    <button class="hamburger hamburger--slider" type="button">
                        <span class="hamburger-box">
                            <span class="hamburger-inner"></span>
                        </span>
                    </button>
                </div>
            </div>
        </div>
        <nav class="navbar-mobile">
            <div class="container-fluid">
                <ul class="navbar-mobile__list list-unstyled">
                    <li>
                        <a href="/"> <i class="fas fa-tachometer-alt"></i>Dashboard</a>
                    </li>
                    <li class="guest">
                        <a href="/candidate/signup"> <i class="fas fa-check-square"></i>Candidate signup</a>
                    </li>
                    <li class="candidate stage-4">
                        <a href="/candidate/opmanual"> <i class="fas fa-check-square"></i>Operation Manual</a>
                    </li>
                    <li class="admin has-sub">
                        <a class="js-arrow" href="#">
                            <i class="fas fa-table"></i>Admin</a>
                        <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                            <li>
                                <a href="/admin/users">Users</a>
                            </li>
                            <li>
                                <a href="/admin/candidates">Candidates</a>
                            </li>
                            <li>
                                <a href="/admin/courses">Ground School Courses</a>
                            </li>
                            <li>
                                <a href="/admin/drones">Drones</a>
                            </li>
                        </ul>
                    </li>
                    <li class="instructor has-sub">
                        <a class="js-arrow" href="#">
                            <i class="fas fa-table"></i>Instructor</a>
                        <ul class="list-unstyled navbar-mobile-sub__list js-sub-list">
                            <li>
                                <a href="/instructor/courses">Assigned Ground School Courses</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- END HEADER MOBILE-->

    <!-- MENU SIDEBAR-->
    <aside class="menu-sidebar d-none d-lg-block">
        <div class="logo">
            <a href="/">
                <img src="/images/icon/asg_logo_transparent.png" alt="AviationSystemsGroup"/>
            </a>
        </div>
        <div class="menu-sidebar__content js-scrollbar1">
            <nav class="navbar-sidebar">
                <ul class="list-unstyled navbar__list">
                    <li>
                        <a href="/"><i class="fas fa-tachometer-alt"></i>Dashboard</a>
                    </li>

                    <li class="guest">
                        <a href="/candidate/signup"> <i class="fas fa-check-square"></i>Candidate signup</a>
                    </li>
                    <li class="candidate stage-4">
                        <a href="/candidate/opmanual"> <i class="fas fa-check-square"></i>Operation Manual</a>
                    </li>
                    <li class="admin has-sub">
                        <a class="js-arrow" href="#">
                            <i class="fas fa-table"></i>Admin</a>
                        <ul class="list-unstyled navbar__sub-list js-sub-list">
                            <li>
                                <a href="/admin/users">Users</a>
                            </li>
                            <li>
                                <a href="/admin/candidates">Candidates</a>
                            </li>
                            <li>
                                <a href="/admin/courses">Ground School Courses</a>
                            </li>
                            <li>
                                <a href="/admin/drones">Drones</a>
                            </li>
                        </ul>
                    </li>
                    <li class="instructor has-sub">
                        <a class="js-arrow" href="#">
                            <i class="fas fa-table"></i>Instructor</a>
                        <ul class="list-unstyled navbar__sub-list js-sub-list">
                            <li>
                                <a href="/instructor/courses">Assigned Ground School Courses</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </aside>
    <!-- END MENU SIDEBAR-->

    <!-- PAGE CONTAINER-->
    <div id="app" class="page-container">
        <!-- HEADER DESKTOP-->
        <header class="header-desktop" style="height: 40px;">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="header-wrap">
                        <div class="header-button" style="margin-top: 0;">
                            <div class="noti-wrap">
                            </div>
                            <div class="account-wrap">
                                <div class="account-item clearfix js-item-menu">
                                    <div class="content">
                                        <a class="js-acc-btn account-name"></a>
                                    </div>
                                    <div class="account-dropdown js-dropdown">
                                        <div class="info clearfix">
                                            <div class="content">
                                                <h5 class="name">
                                                    <a class="account-name"></a>
                                                </h5>
                                                <span class="email account-email"></span>
                                            </div>
                                        </div>
                                        <#--<div class="account-dropdown__body">-->
                                            <#--<div class="account-dropdown__item">-->
                                                <#--<a href="#">-->
                                                    <#--<i class="zmdi zmdi-account"></i>Account</a>-->
                                            <#--</div>-->
                                        <#--</div>-->
                                        <div class="account-dropdown__footer">
                                            <a href="/account/logout">
                                                <i class="zmdi zmdi-power"></i>Logout</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- HEADER DESKTOP-->
        <!-- MAIN CONTENT-->
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <@page_body></@page_body>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="copyright">
                                <p>Copyright © 2018 Colorlib. All rights reserved. Template by <a href="https://colorlib.com">Colorlib</a>.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END MAIN CONTENT-->
        <!-- END PAGE CONTAINER-->

        <@modals></@modals>
    </div>

    <!-- Jquery JS-->
    <script src="/vendor/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap JS-->
    <script src="/vendor/bootstrap-4.1/popper.min.js"></script>
    <script src="/vendor/bootstrap-4.1/bootstrap.min.js"></script>
    <!-- Vendor JS       -->
    <script src="/vendor/slick/slick.min.js"></script>
    <script src="/vendor/wow/wow.min.js"></script>
    <script src="/vendor/animsition/animsition.min.js"></script>
    <script src="/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <script src="/vendor/counter-up/jquery.waypoints.min.js"></script>
    <script src="/vendor/counter-up/jquery.counterup.min.js"></script>
    <script src="/vendor/circle-progress/circle-progress.min.js"></script>
    <script src="/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="/vendor/chartjs/Chart.bundle.min.js"></script>
    <script src="/vendor/select2/select2.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/js-url/2.5.3/url.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.5.17/vue.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.18.0/axios.js"></script>

    <script>
        $('.card').css('display', 'block');
        var account = {};
        axios.get("/api/v1/account/loggedinuser")
            .then(function (response) {
                this.account = response.data;
                $("." + account.role.toLowerCase()).css('display', 'block');
                $(".account-name").text(account.forename + " " + account.surname);
                $(".account-email").text(account.email);

                if (account.role === "Candidate") {
                    axios.get("/api/v1/account/loggedincandidate")
                        .then(function (response) {
                            var candidate = response.data;
                            for (var i = 0; i < 10; i++) {
                                if (i !== candidate.stage.id) {
                                    $(".stage-" + i).css('display', 'none');
                                }
                            }
                        })
                        .catch(function (reason) {
                            if (reason.response.status === 417) {
                                console.log("test");
                            }
                        });
                }
            });
    </script>


    <@scripts></@scripts>

    <!-- Main JS-->
    <script src="/js/main.js"></script>

</body>
</html>
<!-- end document-->

</#macro>
