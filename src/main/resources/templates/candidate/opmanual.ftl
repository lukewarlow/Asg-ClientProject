<#include "../common/base.ftl">

<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Download and Upload Operations Manual
                </div>
                <div class="card-body card-block">
                    <button class="btn btn-primary" onclick="location.href='/api/v1/opmanual'">
                        Download Operations Manual Template
                    </button>
                    <br>
                    <br>
                    <div>Upload</div>
                    <form>
                        <input id="file" type="file" name="file" /><br/><br/>
                        <button id="upload-op-manual" type="button" class="btn btn-primary">Submit</button>
                    </form>
                </div>

        </div>
        </div>
    </div>
</#macro>

<#macro scripts>
    <script>
        $('#upload-op-manual').on('click', function () {
            var formData = new FormData();
            var file = document.querySelector("#file");
            formData.append('file', file.files[0]);
            axios.post("/api/v1/opmanual/upload", formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then(function (response) {
                console.log("test");
            })
        });
    </script>
</#macro>

<@display_page "Operation Manual"/>