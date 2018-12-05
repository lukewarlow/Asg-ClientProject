<#include "../../common/base.ftl">
<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Course Information
                </div>
                <div class="card-body card-block">
                    <table class="table" v-if="course.courseNumber != null">
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
                        <tr>
                            <th>Instructor forename</th>
                            <td v-if="course.instructor">{{course.instructor.forename}}</td>
                            <td v-else>N/A</td>
                        </tr>
                        <tr>
                            <th>Instructor surname</th>
                            <td v-if="course.instructor">{{course.instructor.surname }}</td>
                            <td v-else>N/A</td>
                        </tr>
                    </table>
                    <h2 v-else>Course not found</h2>
                </div>
                <div class="card-footer" v-if="!course.instructor">
                    <button class="btn btn-primary" data-toggle="modal" data-target="#assign-instructor">Assign Instructor</button>
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
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#assign-candidates">Assign candidates to course</button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro modals>
    <div class="modal bd-modal-lg fade" id="assign-instructor" tabindex="-1" role="dialog" aria-labelledby="assign-instructor-label" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content modal-lg">
                <div class="modal-header modal-lg">
                    <h5 class="modal-title" id="assign-instructor-label">Assign instructor to course</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <i class="material-icons">close</i>
                    </button>
                </div>
                <div class="modal-body modal-lg">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th scope="col">
                                <input v-model="instructorModalSearchTerm" class="form-control" type="search" placeholder="Search forename...">
                            </th>
                            <th colspan="3"></th>
                        </tr>
                        <tr>
                            <th scope="col" v-for="column in instructorModalColumns" @click="instructorModalSortByChange(column.value)" style="cursor: pointer;">
                                <span class="d-inline float-left">{{column.text}}</span>
                                <span class="d-inline d-flex justify-content-start">
                                <i v-show="instructorModalOrderBy == column.value && instructorModalOrderByAscending == true" class="material-icons">arrow_upward</i>
                                <i v-show="instructorModalOrderBy == column.value && instructorModalOrderByAscending == false" class="material-icons">arrow_downward</i>
                                </span>
                            </th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="instructor in potentialInstructors">
                            <td>{{ instructor.forename }}</td>
                            <td>{{ instructor.surname }}</td>
                            <td>{{ instructor.email }}</td>
                            <td style="cursor: pointer;" @click="assignInstructorToCourse(instructor.id)"><i class="material-icons">insert_link</i></td>
                        </tr>
                        <tr v-if="potentialInstructors.length == 0">
                            <td colspan="4">No results</td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="d-flex flex-row justify-content-center bd-highlight mb-3">
                        <div class="p-2 bd-highlight">
                            <select class="form-control" v-model="instructorModalPageSize">
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                            </select>
                        </div>

                        <div class="p-2 bd-highlight">
                            <ul class="pagination" style="margin: 0;">
                                <li class="page-item" v-for="page in instructorModalNoOfPages">
                                    <a class="page-link" @click="instructorModalPageChange(page)">{{ page }}</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal bd-modal-lg fade" id="assign-candidates" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content modal-lg">
                <div class="modal-header modal-lg">
                    <h5 class="modal-title" id="exampleModalLabel">Assign candidates to course</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <i class="material-icons">close</i>
                    </button>
                </div>
                <div class="modal-body modal-lg">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th scope="col">
                                <input v-model="candidateModalSearchTerm" class="form-control" type="search" placeholder="Search candidate number...">
                            </th>
                            <th colspan="5"></th>
                        </tr>
                        <tr>
                            <th scope="col" v-for="column in candidateModalColumns" @click="candidateModalSortByChange(column.value)" style="cursor: pointer;">
                                <span class="d-inline float-left">{{column.text}}</span>
                                <span class="d-inline d-flex justify-content-start">
                                <i v-show="candidateModalOrderBy == column.value && candidateModalOrderByAscending == true" class="material-icons">arrow_upward</i>
                                <i v-show="candidateModalOrderBy == column.value && candidateModalOrderByAscending == false" class="material-icons">arrow_downward</i>
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
                            <select class="form-control" v-model="candidateModalPageSize">
                                <option value="5">5</option>
                                <option value="10">10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                            </select>
                        </div>

                        <div class="p-2 bd-highlight">
                            <ul class="pagination" style="margin: 0;">
                                <li class="page-item" v-for="page in candidateModalNoOfPages">
                                    <a class="page-link" @click="candidateModalodalPageChange(page)">{{ page }}</a>
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
                    location: {},
                    instructor: {}
                },
                potentialCandidates: {},
                potentialInstructors: {},
                assignedCandidates: {},
                id: 0,
                noOfPages: 1,
                pageSize: 10,
                page: 1,
                orderBy: "id",
                orderByAscending: false,
                candidateModalNoOfPages: 1,
                candidateModalPageSize: 10,
                candidateModalPage: 1,
                candidateModalOrderBy: "id",
                candidateModalOrderByAscending: false,
                candidateModalSearchTerm: "",
                instructorModalNoOfPages: 1,
                instructorModalPageSize: 10,
                instructorModalPage: 1,
                instructorModalOrderBy: "id",
                instructorModalOrderByAscending: false,
                instructorModalSearchTerm: "",
                columns: [
                    {text: "Candidate Number", value: "candidateNumber"},
                    {text: "Forename", value: "forename"},
                    {text: "Surname", value: "surname"},
                    {text: "Email", value: "email"},
                    {text: "Prefered Location", value: "preferedLocation"}
                ],
                instructorModalColumns: [
                    {text: "Forename", value: "forename"},
                    {text: "Surname", value: "surname"},
                    {text: "Email", value: "email"}
                ],
                candidateModalColumns: [
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
                candidateModalPageSize: function () {
                    this.modalRefresh();
                },
                candidateModalSearchTerm: function () {
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
                candidateModalPageChange: function (page) {
                    app.candidateModalPage = page;
                    app.candidateModalRefresh();
                },
                candidateModalSortByChange: function (orderBy) {
                    if (orderBy === app.candidateModalOrderBy)
                        app.candidateModalOrderByAscending = !app.candidateModalOrderByAscending;
                    else
                        app.candidateModalOrderBy = orderBy;
                    app.candidateModalRefresh();
                },
                candidateModalRefresh: function () {
                    axios.get("/api/v1/candidates/needassigning?page=" + app.candidateModalPage + "&pageSize=" + app.candidateModalPageSize + "&orderBy=" + app.candidateModalOrderBy + "&orderByAscending=" + app.candidateModalOrderByAscending + "&search=" + app.candidateModalSearchTerm)
                        .then(function (response) {
                            app.potentialCandidates = response.data.list;
                            app.candidateModalNoOfPages = response.data.noOfPages;
                            if (app.candidateModalNoOfPages === 0) {
                                app.candidateModalNoOfPages = 1;
                            }
                        });
                },
                instructorModalPageChange: function (page) {
                    app.modalPage = page;
                    app.instructorModalRefresh();
                },
                instructorModalSortByChange: function (orderBy) {
                    if (orderBy === app.modalOrderBy)
                        app.modalOrderByAscending = !app.modalOrderByAscending;
                    else
                        app.modalOrderBy = orderBy;
                    app.instructorModalRefresh();
                },
                instructorModalRefresh: function () {
                    axios.get("/api/v1/users/instructors?page=" + app.instructorModalPage + "&pageSize=" + app.instructorModalPageSize + "&orderBy=" + app.instructorModalOrderBy + "&orderByAscending=" + app.instructorModalOrderByAscending + "&search=" + app.instructorModalSearchTerm)
                        .then(function (response) {
                            app.potentialInstructors = response.data.list;
                            app.instructorModalNoOfPages = response.data.noOfPages;
                            if (app.instructorModalNoOfPages === 0) {
                                app.instructorModalNoOfPages = 1;
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
                            $('#assign-candidates').modal('hide');
                            app.candidateModalRefresh();
                            app.refresh();
                        })
                },
                assignInstructorToCourse: function (instructorId) {
                    axios.post("/api/v1/gscourses/" + app.id + "/instructor/" + instructorId)
                        .then(function () {
                            $('#assign-instructor').modal('hide');
                            app.instructorModalRefresh();
                            app.refresh();
                        })
                }
            }
        });
        app.created();
        app.refresh();
        app.candidateModalRefresh();
        app.instructorModalRefresh();
    </script>
</#macro>

<@display_page "Manage Course"/>