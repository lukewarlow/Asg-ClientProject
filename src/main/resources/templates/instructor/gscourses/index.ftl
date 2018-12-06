<#include "../../common/base.ftl">

<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Assigned Ground School Courses
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
                noOfPages: 1,
                pageSize: 10,
                page: 1,
                orderBy: "courseNumber",
                orderByAscending: true,
                searchTerm: "",
                columns: [
                    {text: "Course Number", value: "courseNumber"},
                    {text: "Start Date", value: "startDate"},
                    {text: "End Date", value: "endDate"},
                    {text: "Type", value: "type"},
                    {text: "Location", value: "location"}
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
                goToPage: function (id) {
                    // window.location.href = "/instructor/courses/" + id;
                }
            }
        });
        app.refresh();
    </script>
</#macro>

<@display_page "Assigned GS Courses"/>