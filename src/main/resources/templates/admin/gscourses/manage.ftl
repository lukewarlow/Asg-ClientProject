<#include "../../common/base.ftl">
<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Course Information
                </div>
                <div class="card-body card-block">
                    <table class="table">
                        <tr>
                            <th>Course Number</th>
                            <td>{{course.courseNumber}}</td>
                        </tr>
                        <tr>
                            <th>Start Date</th>
                            <td>{{displayDate(course.startDate)}}</td>
                        </tr>
                        <tr>
                            <th>End Date</th>
                            <td>{{displayDate(course.endDate)}}</td>
                        </tr>
                        <tr>
                            <th>Type</th>
                            <td>{{course.type.type}}</td>
                        </tr>
                        <tr>
                            <th>Location</th>
                            <td>{{course.location.location}}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Assigned Candidates
                </div>
                <div class="card-body card-block">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th scope="col" v-for="column in columns" @click="sortByChange(column.value)" style="cursor: pointer;">
                                <span class="d-inline float-left">{{column.text}}</span>
                                <span class="d-inline d-flex justify-content-start">
                                <i v-show="orderBy == column.value && orderByAscending == true" class="material-icons">arrow_upward</i>
                                <i v-show="orderBy == column.value && orderByAscending == false" class="material-icons">arrow_downward</i>
                                </span>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="candidate in assignedCandidates">
                            <td>{{ candidate.candidateNumber }}</td>
                            <td>{{ candidate.user.forename }}</td>
                            <td>{{ candidate.user.surname }}</td>
                            <td>{{ candidate.user.email }}</td>
                            <td>{{ candidate.preferedLocation.location }}</td>
                        </tr>
                        <tr v-if="assignedCandidates.length == 0">
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
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-modal-lg">Assign candidates to course</button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro modals>
    <div class="modal bd-modal-lg fade" id="assign-candidate" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content modal-lg">
                <div class="modal-header modal-lg">
                    <h5 class="modal-title" id="exampleModalLabel">Add ground school course</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <i class="material-icons">close</i>
                    </button>
                </div>
                <div class="modal-body modal-lg">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th scope="col">
                                <input v-model="modalSearchTerm" class="form-control" type="search" placeholder="Search candidate number...">
                            </th>
                            <th colspan="5"></th>
                        </tr>
                        <tr>
                            <th scope="col" v-for="column in modalColumns" @click="modalSortByChange(column.value)" style="cursor: pointer;">
                                <span class="d-inline float-left">{{column.text}}</span>
                                <span class="d-inline d-flex justify-content-start">
                                <i v-show="modalOrderBy == column.value && modalOrderByAscending == true" class="material-icons">arrow_upward</i>
                                <i v-show="modalOrderBy == column.value && modalOrderByAscending == false" class="material-icons">arrow_downward</i>
                                </span>
                            </th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="candidate in potentialCandidates">
                            <td>{{ candidate.candidateNumber }}</td>
                            <td>{{ candidate.user.forename }}</td>
                            <td>{{ candidate.user.surname }}</td>
                            <td>{{ candidate.user.email }}</td>
                            <td>{{ candidate.preferedLocation.location }}</td>
                            <td style="cursor: pointer;" @click="assignCandidateToCourse(candidate.id)"><i class="material-icons">insert_link</i></td>
                        </tr>
                        <tr v-if="potentialCandidates.length == 0">
                            <td colspan="6">No results</td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="d-flex flex-row justify-content-center bd-highlight mb-3">
                        <div class="p-2 bd-highlight">
                            <select class="form-control" v-model="modalPageSize">
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                            </select>
                        </div>

                        <div class="p-2 bd-highlight">
                            <ul class="pagination" style="margin: 0;">
                                <li class="page-item" v-for="page in modalNoOfPages">
                                    <a class="page-link" @click="modalPageChange(page)">{{ page }}</a>
                                </li>
                            </ul>
                        </div>
                    </div>
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
                course: {
                    type: {},
                    location: {}
                },
                potentialCandidates: {},
                assignedCandidates: {},
                id: 0,
                noOfPages: 1,
                pageSize: 10,
                page: 1,
                orderBy: "id",
                orderByAscending: false,
                modalNoOfPages: 1,
                modalPageSize: 10,
                modalPage: 1,
                modalOrderBy: "id",
                modalOrderByAscending: false,
                modalSearchTerm: "",
                columns: [
                    {text: "Candidate Number", value: "candidateNumber"},
                    {text: "Forename", value: "forename"},
                    {text: "Surname", value: "surname"},
                    {text: "Email", value: "email"},
                    {text: "Prefered Location", value: "preferedLocation"}
                ],
                modalColumns: [
                    {text: "Candidate Number", value: "candidateNumber"},
                    {text: "Forename", value: "forename"},
                    {text: "Surname", value: "surname"},
                    {text: "Email", value: "email"},
                    {text: "Prefered Location", value: "preferedLocation"}
                ]
            },
            watch: {
                pageSize: function () {
                    this.refresh();
                },
                modalPageSize: function () {
                    this.modalRefresh();
                },
                modalSearchTerm: function () {
                    this.modalRefresh();
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
                refresh: function () {
                    axios.get("/api/v1/gscourses/" + app.id + "/candidates?page=" + app.page + "&pageSize=" + app.pageSize + "&orderBy=" + app.orderBy + "&orderByAscending=" + app.orderByAscending)
                        .then(function (response) {
                            app.assignedCandidates = response.data.list;
                            app.noOfPages = response.data.noOfPages;
                            if (app.noOfPages === 0) {
                                app.noOfPages = 1;
                            }
                        });
                },
                modalPageChange: function (page) {
                    app.modalPage = page;
                    app.modalRefresh();
                },
                modalSortByChange: function (orderBy) {
                    if (orderBy === app.modalOrderBy)
                        app.modalOrderByAscending = !app.modalOrderByAscending;
                    else
                        app.modalOrderBy = orderBy;
                    app.modalRefresh();
                },
                modalRefresh: function () {
                    axios.get("/api/v1/candidates/needassigning?page=" + app.modalPage + "&pageSize=" + app.modalPageSize + "&orderBy=" + app.modalOrderBy + "&orderByAscending=" + app.modalOrderByAscending + "&search=" + app.modalSearchTerm)
                        .then(function (response) {
                            app.potentialCandidates = response.data.list;
                            app.modalNoOfPages = response.data.noOfPages;
                            if (app.modalNoOfPages === 0) {
                                app.modalNoOfPages = 1;
                            }
                        });
                },
                displayDate: function(date) {
                    var day = new Date(date).getDate();
                    var month = new Date(date).getMonth();
                    var year = new Date(date).getFullYear();
                    return day + " / " + month + " / " + year;
                },
                created: function() {
                    app.id = url(-1);
                    axios.get("/api/v1/gscourses/" + app.id)
                        .then(function (response) {
                            app.course = response.data;
                        });
                },
                assignCandidateToCourse: function (candidateId) {
                    axios.post("/api/v1/gscourses/" + app.id + "/candidates/" + candidateId)
                        .then(function () {
                            $('#assign-candidate').modal('hide');
                            app.modalRefresh();
                            app.refresh();
                        })
                }
            }
        });
        app.created();
        app.refresh();
        app.modalRefresh();
    </script>
</#macro>

<@display_page "Manage Course"/>