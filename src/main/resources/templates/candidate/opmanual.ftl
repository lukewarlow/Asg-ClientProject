<#include "../common/base.ftl">

<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Download Operations Manual
                </div>
                <div class="card-body card-block">
                    <button class="btn btn-primary" onclick="location.href='/api/v1/opmanual'">
                        Download Operations Manual Template
                    </button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<@display_page "Operation Manual"/>