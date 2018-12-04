<#include "../../common/base.ftl">

<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Manage Candidates
                </div>
                <div class="card-body card-block">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th scope="col">
                                <input v-model="searchTerm" class="form-control" type="search" placeholder="Search candidate number...">
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
                        <tr v-for="candidate in candidates" style="cursor: pointer;">
                            <td>{{ candidate.candidateNumber }}</td>
                            <td>{{ candidate.user.forename }}</td>
                            <td>{{ candidate.user.surname }}</td>
                            <td>{{ candidate.user.email }}</td>
                            <td>
                                <i v-if="candidate.payed" class="material-icons">check</i>
                                <i v-else class="material-icons">close</i>
                            </td>
                        </tr>
                        <tr v-if="candidates.length == 0">
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
                candidates: [],
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
                    {text: "Candidate Number", value: "candidateNumber"},
                    {text: "Forename", value: "forename"},
                    {text: "Surname", value: "surname"},
                    {text: "Email", value: "email"},
                    {text: "Has Payed", value: "hasPayed"}
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
                    axios.get("/api/v1/candidates?page=" + app.page + "&pageSize=" + app.pageSize + "&orderBy=" + app.orderBy + "&orderByAscending=" + app.orderByAscending + "&search=" + app.searchTerm)
                        .then(function (response) {
                            app.candidates = response.data.list;
                            app.noOfPages = response.data.noOfPages;
                            if (app.noOfPages === 0) {
                                app.noOfPages = 1;
                            }
                        });
                }
            }
        });
        app.refresh();
    </script>
</#macro>

<@display_page "Candidates"/>