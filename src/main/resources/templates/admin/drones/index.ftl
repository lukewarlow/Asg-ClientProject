<#include "../../common/base.ftl">
<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Manage Drones
                </div>
                <div class="card-body card-block">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th colspan="2">
                                <input v-model="searchTerm" class="form-control" type="search" placeholder="Search...">
                            </th>
                        </tr>
                        <tr>
                            <th scope="col" @click="sortByChange('manufacturer')" style="cursor: pointer;">Manufacturer
                                <span v-show="orderBy == 'manufacturer' && orderByAscending == true">▲</span>
                                <span v-show="orderBy == 'manufacturer' && orderByAscending == false">▼</span></th>
                            <th scope="col" @click="sortByChange('model')" style="cursor: pointer;">Model
                                <span v-show="orderBy == 'model' && orderByAscending == true">▲</span>
                                <span v-show="orderBy == 'model' && orderByAscending == false">▼</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="drone in drones" style="cursor: pointer;">
                            <td>{{ drone.manufacturer }}</td>
                            <td>{{ drone.model }}</td>
                        </tr>
                        <tr v-if="drones.length == 0">
                            <td colspan="4">No results</td>
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
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addDrone">Add Drone</button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro modals>
    <div class="modal fade" id="addDrone" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Add new drone</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="newDrone = {}">
                        <i class="material-icons">close</i>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="manufacturer" class="form-control-label">Manufacturer</label>
                        <input type="text" id="manufacturer" name="manufacturer" class="form-control" v-model="newDrone.manufacturer">
                    </div>
                    <div  class="form-group">
                        <label for="model" class="form-control-label">Model</label>
                        <input type="text" id="model" name="model" class="form-control" v-model="newDrone.model">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" @click="newDrone = {}">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" @click="addDrone">Add</button>
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
                drones: [],
                noOfPages: 0,
                pageSize: 10,
                page: 1,
                orderBy: "manufacturer",
                orderByAscending: false,
                searchTerm: "",
                newDrone: {}
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
                    axios.get("/api/v1/drones?page=" + app.page + "&pageSize=" + app.pageSize + "&orderBy=" + app.orderBy + "&orderByAscending=" + app.orderByAscending + "&search=" + app.searchTerm.split(' '))
                        .then(function (response) {
                            app.drones = response.data.list;
                            app.noOfPages = response.data.noOfPages;
                        });
                },
                addDrone: function () {
                    axios.post("/api/v1/drones", app.newDrone)
                        .then(function (response) {
                            app.newDrone = {};
                            app.refresh();
                        })
                }
            }
        });
        app.refresh();
    </script>
</#macro>

<@display_page "Users"/>