<#include "../common/base.ftl">

<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Download and Upload Operations Manual
                </div>
                <div class="card-body card-block">
                    <a class="btn btn-primary" href="/api/v1/opmanual">
                        Download Operations Manual Template
                    </a>
                    <br>
                    <br>
                    <div>Upload</div>
                    <form>
                        <input id="file" type="file" name="file" /><br/><br/>
                        <button id="upload-op-manual" type="button" class="btn btn-primary">Submit</button>
                    </form>
                    <br>
                    <div class="alert alert-danger" id="error" style="display: none;" role="alert">
                    </div>
                    <div class="alert alert-success" id="success" style="display: none;" role="alert">
                        Operation Manual Submission Successful!
                    </div>
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
                $('#success').css('display', 'block');
                $('#error').css('display', 'none');
                setTimeout(function () {
                    window.location.href = "/";
                }, 500);
            }).catch(function (error) {
                var status = error.response.status;
                $('#success').css('display', 'none');
                var errorElement = $('#error');
                if (status === 204) {
                    errorElement.text("Uploaded file was corrupt!");
                } else if (status === 415) {
                    errorElement.text("Uploaded file was the incorrect type (must be pdf)!");
                } else if (status === 500) {
                    errorElement.text("There was a server error, your submission was not saved!");
                } else {
                    errorElement.text("There was an error with your submission, your submission was not saved, please try again later!");
                }
                errorElement.css('display', 'block');
            })
        });
    </script>
</#macro>

<@display_page "Operation Manual"/>