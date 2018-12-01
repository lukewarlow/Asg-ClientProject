<#include "base.ftl">
<#macro page_body>
    <div class="row">
        <div class="col-md-12">
            <div class="overview-wrap">
                <h2 class="title-1">overview</h2>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="au-card">
                <div class="au-card-inner">
                    <h2>Some stuff goes here</h2>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro scripts>
    <script>
        var app = new Vue({
            el: "#app",
            data: {
                account: {}
            },
            created: function () {
                axios.get("/api/v1/account/loggedin")
                    .then(function (response) {
                        app.account = response.data;
                        $("." + app.account.role.toLowerCase()).css('display', 'block');
                    });
            }
        });
    </script>
</#macro>

<@display_page/>