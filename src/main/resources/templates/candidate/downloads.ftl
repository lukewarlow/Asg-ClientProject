<#include "../common/base.ftl">

<#macro style>
    <style>
        .autocomplete-container {
            position: relative;
        }

        .autocomplete-content {
            position: absolute;
            background: white;
            border: 2px solid #c9c9c9;
            border-top: 0;
            min-width: 445px;
            z-index: 1;
        }
        .autocomplete-content .header {
            padding: 10px;
        }
        .autocomplete-content ul {
            width: 100%;
            list-style-type: none;
            padding: 0;
            margin: 0;
        }
        .autocomplete-content li {
            padding: 10px;
            cursor: pointer;
        }
        .autocomplete-content li:hover {
            background: #e6e6e6;
        }

        .chosen-item h4 {
            margin-top: 10px;
        }
        .chosen-item p {
            margin: 5px 0;
        }

        .remove-item {
            font-weight: bold;
            color: red;
            display: inline-block;
            margin-left: 20px;
        }
        .remove-item:hover {
            cursor: pointer;
        }
    </style>
</#macro>

<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Download and Upload Operations Manual
                </div>
                <div class="card-body card-block">
                    <button class="btn btn-primary" onclick="location.href='/download/OperationsManual.pdf'">
                        Submit
                    </button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<@display_page "Candidate Downloads"/>