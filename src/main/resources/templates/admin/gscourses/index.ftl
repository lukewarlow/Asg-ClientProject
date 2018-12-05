<#include "../../common/base.ftl">

<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Manage Ground School Courses
                </div>
                <div class="card-body card-block">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th scope="col">
                                <input v-model="searchTerm" class="form-control" type="search" placeholder="Search course number...">
                            </th>
                            <th colspan="4"></th>
                        </tr>
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
                        <tr v-for="course in courses"  @click="goToPage(course.id)" style="cursor: pointer;">
                            <td>{{ course.courseNumber }}</td>
                            <td>{{ course.startDate }}</td>
                            <td>{{ course.endDate }}</td>
                            <td>{{ course.type.type }}</td>
                            <td>{{ course.location.location }}</td>
                        </tr>
                        <tr v-if="courses.length == 0">
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
                    <button type="button" class="btn btn-primary" @click="showAddCourse()">Add Ground School Course</button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro modals>
    <div class="modal fade" id="addCourse" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Add ground school course</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="newCourse = {}">
                        <i class="material-icons">close</i>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="startDate" class="form-control-label">Start Date</label>
                        <input type="date" id="startDate" name="startDate" :min="new Date().toISOString().split('T')[0]" class="form-control" v-model="newCourse.startDate">
                    </div>
                    <div  class="form-group">
                        <label for="endDate" class="form-control-label">End Date</label>
                        <input type="date" id="endDate" name="endDate" :min="endDateMin" class="form-control" v-model="newCourse.endDate">
                    </div>
                    <div  class="form-group">
                        <label for="type" class="form-control-label">Type</label>
                        <select id="type" name="type" v-model="newCourse.typeId">
                            <option v-for="t in types" :value="t.id">{{t.type}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="location" class="form-control-label">Location</label>
                        <select id="location" name="type" v-model="newCourse.locationId">
                            <option v-for="(l, index) in locations" :value="l.id">{{l.location}}</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" @click="newCourse = {}">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" @click="addCourse()">Add</button>
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
                courses: [],
                types: [],
                locations: [],
                newCourse: {
                    typeId: 1,
                    locationId: 1
                },
                noOfPages: 1,
                pageSize: 10,
                page: 1,
                orderBy: "id",
                orderByAscending: false,
                searchTerm: "",
                columns: [
                    {text: "Course Number", value: "courseNumber"},
                    {text: "Start Date", value: "startDate"},
                    {text: "End Date", value: "endDate"},
                    {text: "Type", value: "type"},
                    {text: "Location", value: "location"}
                ]
            },
            computed: {
                endDateMin: function () {
                    if (this.newCourse.startDate === undefined || this.newCourse.startDate === null || this.newCourse.startDate === "")
                        return new Date().toISOString().split('T')[0];
                    else
                        return new Date(this.newCourse.startDate.toString()).toISOString().split('T')[0];
                }
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
                refresh: function () {
                    axios.get("/api/v1/gscourses?page=" + app.page + "&pageSize=" + app.pageSize + "&orderBy=" + app.orderBy + "&orderByAscending=" + app.orderByAscending + "&search=" + app.searchTerm)
                        .then(function (response) {
                            app.courses = response.data.list;
                            app.noOfPages = response.data.noOfPages;
                            if (app.noOfPages === 0) {
                                app.noOfPages = 1;
                            }
                        });
                },
                showAddCourse: function () {
                    axios.get("/api/v1/gscourses/types/all")
                        .then(function (response) {
                            app.types = response.data;
                        });
                    axios.get("/api/v1/gscourses/locations/all")
                        .then(function (response) {
                            app.locations = response.data;
                        });
                    $('#addCourse').modal();
                },
                addCourse: function () {
                    axios.post("/api/v1/gscourses", app.newCourse)
                        .then(function (value) {
                            app.newCourse = {};
                            app.refresh();
                        })
                },
                goToPage: function (id) {
                    window.location.href = "/admin/courses/" + id;
                }
            }
        });
        app.refresh();
    </script>
</#macro>

<@display_page "GS Courses"/>