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
                <div class="card-body">
                    <div class="card-header">
                        Dashboard
                    </div>
                    <div class="card-body">
                        <button class="btn btn-primary candidate" id="make-payment">Make Payment</button>
                    </div>
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
        })
    </script>
</#macro>

<@display_page/>