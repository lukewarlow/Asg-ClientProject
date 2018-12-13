<#include "../../common/base.ftl">
<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Manage GS Course Locations
                </div>
                <div class="card-body card-block">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th scope="col">
                                <input v-model="searchTerm" class="form-control" type="search" placeholder="Search locations...">
                            </th>
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
                        <tr v-for="location in locations">
                            <td scope="row" @click="goToProfile(user.id)">{{ location.location }}</td>
                        </tr>
                        <tr v-if="locations.length == 0">
                            <td>No results</td>
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
                    <button class="btn btn-primary" data-toggle="modal" data-target="#add-location" @click="newLocation = ''">Add Location</button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro modals>
    <div class="modal fade" id="add-location" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="add-location-label">Add location</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="newLocation = ''">
                        <i class="material-icons">close</i>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="location">Location</label>
                        <input class="form-control" type="text" id="location" v-model="newLocation"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" @click="newLocation = ''">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" @click="addLocation">Submit</button>
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
                locations: [],
                noOfPages: 0,
                pageSize: 10,
                page: 1,
                orderBy: "location",
                orderByAscending: false,
                searchTerm: "",
                newLocation: "",
                columns: [
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
                    axios.get("/api/v1/gscourses/locations?page=" + app.page + "&pageSize=" + app.pageSize + "&orderBy=" + app.orderBy + "&orderByAscending=" + app.orderByAscending + "&search=" + app.searchTerm)
                        .then(function (response) {
                            app.locations = response.data.list;
                            app.noOfPages = response.data.noOfPages;
                            if (app.noOfPages === 0) {
                                app.noOfPages = 1;
                            }
                        });
                },
                addLocation: function () {
                    axios.post("/api/v1/gscourses/locations", app.newLocation)
                        .then(function () {
                            $('#add-location').modal(false);
                            app.newLocation = "";
                            app.refresh();
                        })
                }
            }
        });
        app.refresh();
    </script>
</#macro>

<@display_page "Locations"/>