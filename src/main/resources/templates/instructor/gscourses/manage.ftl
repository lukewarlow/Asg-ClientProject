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
                            <td>{{course.startDate}}</td>
                        </tr>
                        <tr>
                            <th>End Date</th>
                            <td>{{course.endDate}}</td>
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
                    <h2 v-else>Course not found</h2>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Unmarked Candidates
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
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="candidate in assignedCandidates">
                            <td>{{ candidate.candidateNumber }}</td>
                            <td>{{ candidate.user.forename }}</td>
                            <td>{{ candidate.user.surname }}</td>
                            <td>{{ candidate.user.email }}</td>
                            <td @click="showResultModal(candidate)" style="cursor: pointer;"><i class="material-icons">assignment</i></td>
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
            </div>
        </div>
    </div>
</#macro>

<#macro modals>
    <div class="modal fade" id="submit-result" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Submit results</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="result = {}">
                        <i class="material-icons">close</i>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="question-bank" class="form-control-label">Question Bank</label>
                        <input type="text" maxlength="1" id="question-bank" name="manufacturer" class="form-control" v-model="result.questionBank">
                    </div>
                    <div  class="form-group">
                        <label for="result" class="form-control-label">Result</label>
                        <input type="text" id="result" name="result" class="form-control" v-model="result.result">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" @click="result = {}">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" @click="submitResult">Submit</button>
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
                result: {},
                assignedCandidates: {},
                id: 0,
                noOfPages: 1,
                pageSize: 10,
                page: 1,
                orderBy: "id",
                orderByAscending: false,
                columns: [
                    {text: "Candidate Number", value: "candidateNumber"},
                    {text: "Forename", value: "forename"},
                    {text: "Surname", value: "surname"},
                    {text: "Email", value: "email"}
                ]
            },
            watch: {
                pageSize: function () {
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
                created: function() {
                    app.id = url(-1);
                    axios.get("/api/v1/gscourses/" + app.id)
                        .then(function (response) {
                            app.course = response.data;
                        });
                },
                showResultModal: function (candidate) {
                   app.result.candidateId = candidate.id;
                   $('#submit-result').modal();
                },
                submitResult: function () {
                    app.result.gsCourseId = app.id;
                    axios.post("/api/v1/results/gscourse", app.result)
                        .then(function (value) {
                            app.refresh();
                        })
                }
            }
        });
        app.created();
        app.refresh();
    </script>
</#macro>

<@display_page "Manage Course"/>