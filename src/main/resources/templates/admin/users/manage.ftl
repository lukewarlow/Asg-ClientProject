<#include "../../common/base.ftl">
<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Manage User
                </div>
                <div class="card-body card-block">
                    <table class="table">
                        <tr>
                            <th>Forename</th>
                            <td>{{user.forename}}</td>
                        </tr>
                        <tr>
                            <th>Surname</th>
                            <td>{{user.surname}}</td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td>{{user.email}}</td>
                        </tr>
                        <tr>
                            <th>Phone Number</th>
                            <td>{{user.phoneNumber}}</td>
                        </tr>
                        <tr>
                            <th>Role</th>
                            <td>{{user.role}}</td>
                        </tr>
                        <tr>
                            <th>Activated</th>
                            <td>
                                <i v-if="user.activated" class="material-icons">done</i>
                                <i v-else class="material-icons">close</i>
                            </td>
                        </tr>
                        <tr>
                            <th>Enabled</th>
                            <td>
                                <i v-if="user.disabled" class="material-icons">close</i>
                                <i v-else class="material-icons">done</i>
                            </td>
                        </tr>
                        <tr>
                            <th>Created at</th>
                            <td>{{user.createdAt}}</td>
                        </tr>
                        <tr>
                            <th>Updated at</th>
                            <td>{{user.updatedAt}}</td>
                        </tr>
                    </table>
                </div>
                <div class="card-footer">
                    <button v-if="user.disabled" type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmToggleStatus">Enable</button>
                    <button v-if="!user.disabled" type="button" class="btn btn-danger"  data-toggle="modal" data-target="#confirmToggleStatus">Disable</button>
                </div>
            </div>
        </div>
    </div>

    </#macro>

<#macro modals>
    <div class="modal fade" id="confirmToggleStatus" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Confirm Action</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <i class="material-icons">close</i>
                    </button>
                </div>
                <div v-if="user.disabled" class="modal-body">Are you sure you want to enable {{user.forename}} {{user.surname}}?</div>
                <div v-else class="modal-body">Are you sure you want to disable {{user.forename}} {{user.surname}}?</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" @click="toggleStatus">Confirm</button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro scripts>
    <script>
        var app = new Vue ({
            el: "#app",
            data: {
                user: {},
                id: 0
            },
            methods: {
                toggleStatus: function () {
                    var task = app.user.disabled ? "/enable" : "/disable";
                    axios.put("/api/v1/users/" + app.id + task)
                        .then(function () {
                            app.refresh();
                        });
                },
                refresh: function () {
                    axios.get("/api/v1/users/" + app.id)
                        .then(function (response) {
                            app.user = response.data;
                        });
                }
            }
        });
        app.id = url(-1);
        app.refresh();
    </script>
</#macro>

<@display_page "Manage User"/>