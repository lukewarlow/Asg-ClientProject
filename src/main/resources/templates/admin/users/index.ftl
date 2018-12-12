<#include "../../common/base.ftl">
<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Manage Users
                </div>
                <div class="card-body card-block">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th scope="col">
                                <input v-model="searchTerm" class="form-control" type="search" placeholder="Search forename...">
                            </th>
                            <th colspan="5"></th>
                        </tr>
                        <tr>
                            <th scope="col" v-for="column in columns" @click="sortByChange(column.value)" style="cursor: pointer;">
                                <span class="d-inline float-left">{{column.text}}</span>
                                <span class="d-inline d-flex justify-content-start">
                                <i v-show="orderBy == column.value && orderByAscending == true" class="material-icons">arrow_upward</i>
                                <i v-show="orderBy == column.value && orderByAscending == false" class="material-icons">arrow_downward</i>
                                </span>
                            </th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="user in users" style="cursor: pointer;">
                            <td scope="row" @click="goToProfile(user.id)">{{ user.forename }}</td>
                            <td @click="goToProfile(user.id)">{{ user.surname }}</td>
                            <td @click="goToProfile(user.id)">{{ user.email }}</td>
                            <td @click="goToProfile(user.id)">{{ user.role }}</td>
                            <td @click="goToProfile(user.id)">
                                <span v-if="user.activated">Activated</span>
                                <span v-else>Not Activated</span>
                            </td>
                            <td>
                                <span data-toggle="modal" data-target="#confirmDelete" @click="confirmDelete(user)"><i class="material-icons">delete</i></span>
                                <span @click="showEdit(user)"><i class="material-icons">edit</i></span>
                            </td>
                        </tr>
                        <tr v-if="users.length == 0">
                            <td colspan="5">No results</td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="d-flex flex-row justify-content-center bd-highlight mb-3">
                        <div class="p-2 bd-highlight">
                            <select class="form-control" v-model="pageSize">
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                            </select>
                        </div>

                        <div class="p-2 bd-highlight">
                            <ul class="pagination" style="margin: 0;">
                                <li class="page-item" v-for="page in noOfPages">
                                    <a class="page-link" @click="pageChange(page)">{{ page }}</a>
                                </li>
                            </ul>
                        </div>
                    </div>

                </div>
                <div class="card-footer">
                    <button v-if="showDisabled" class="btn btn-primary" @click="showDisabled = false; refresh();">Show enabled</button>
                    <button v-else class="btn btn-primary" @click="showDisabled = true; refresh();">Show disabled</button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro modals>
    <div v-show="deleting != {}" class="modal fade" id="confirmDelete" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="confirm-delete-label">Confirm Delete</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="deleting = {}">
                        <i class="material-icons">close</i>
                    </button>
                </div>
                <div class="modal-body">Are you sure you want to delete {{deleting.forename}} {{deleting.surname}}? <br>
                    This action can not be undone!</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" @click="deleting = {}">Close</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal" @click="deleteUser">Delete</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="edit-user" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="edit-user-label">Edit user</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="editedUser = {}">
                        <i class="material-icons">close</i>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="edit-forename">Forename</label>
                        <input class="form-control" type="text" id="edit-forename" v-model="editedUser.forename"/>
                    </div>
                    <div class="form-group">
                        <label for="edit-surname">Surname</label>
                        <input class="form-control" type="text" id="edit-surname" v-model="editedUser.surname"/>
                    </div>
                    <div class="form-group">
                        <label for="edit-email">Email</label>
                        <input class="form-control" type="text" id="edit-email" v-model="editedUser.email"/>
                    </div>
                    <div class="form-group">
                        <label for="edit-phone">Phone number</label>
                        <input class="form-control" type="text" id="edit-phone" v-model="editedUser.phoneNumber"/>
                    </div>
                    <div class="form-group">
                        <label for="edit-role">Role</label>
                        <select class="form-control" v-model="editedUser.role">
                            <option value="Guest">Guest</option>
                            <option value="Candidate">Candidate</option>
                            <option value="Instructor">Instructor</option>
                            <option value="Admin">Admin</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" @click="editedUser = {}">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" @click="editUser">Submit</button>
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
                users: [],
                noOfPages: 0,
                pageSize: 10,
                page: 1,
                orderBy: "forename",
                orderByAscending: false,
                searchTerm: "",
                showDisabled: false,
                deleting: {},
                editedUser: {},
                columns: [
                    {text: "Forename", value: "forename"},
                    {text: "Surname", value: "surname"},
                    {text: "Email", value: "email"},
                    {text: "Role", value: "role"},
                    {text: "Activation", value: "activated"}
                ]
            },
            watch: {
                pageSize: function () {
                    this.refresh();
                },
                searchTerm: function () {
                    this.refresh();
                }
            },
            methods: {
                pageChange: function(page) {
                    app.page = page;
                    app.refresh();
                },
                sortByChange: function(orderBy) {
                    if (orderBy === app.orderBy)
                        app.orderByAscending = !app.orderByAscending;
                    else
                        app.orderBy = orderBy;
                    app.refresh();
                },
                goToProfile: function (id) {
                    window.location.href = "/admin/users/" + id;
                },
                refresh: function () {
                    var disabledUrl = app.showDisabled ? "/disabled" : "";
                    axios.get("/api/v1/users" + disabledUrl + "?page=" + app.page + "&pageSize=" + app.pageSize + "&orderBy=" + app.orderBy + "&orderByAscending=" + app.orderByAscending + "&search=" + app.searchTerm)
                        .then(function (response) {
                            app.users = response.data.list;
                            app.noOfPages = response.data.noOfPages;
                            if (app.noOfPages === 0) {
                                app.noOfPages = 1;
                            }
                        });
                },
                showEdit: function (user) {
                    app.editedUser = user;
                    $('#edit-user').modal();
                },
                editUser: function () {
                    axios.put("/api/v1/users/" + app.editedUser.id, app.editedUser)
                        .then(function (response) {
                            $('#edit-user').modal(false);
                        })
                },
                confirmDelete: function (user) {
                    app.deleting = user;
                },
                deleteUser: function() {
                    var toDeleteId = app.deleting.id;
                    app.deleting = {};
                    axios.delete("/api/v1/users/" + toDeleteId)
                        .then(function (response) {
                            app.refresh()
                        })
                        .catch(function (reason) {
                            console.log(reason.response);
                        })
                }
            }
        });
        app.refresh();
    </script>
</#macro>

<@display_page "Users"/>