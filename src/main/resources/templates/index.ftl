<#include "common/base.ftl">
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
            <div class="card">
                <div class="card-header">
                    Dashboard
                </div>
                <div class="card-body card-block">
                    <button class="btn btn-primary candidate stage-1" id="make-payment">Make Payment</button>
                    <h2 class="candidate stage-2">An admin will assign you to a ground school course soon.</h2>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro scripts>
    <script>
        $('#make-payment').on('click', function () {
            axios.post("/api/v1/candidates/sendpayment")
                    .then(function () {
                        $('#make-payment').css('display', 'block');
                    });
        });
    </script>
</#macro>

<@display_page/>