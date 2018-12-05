<#include "../../common/base.ftl">
<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Manage Candidate
                </div>
                <div class="card-body card-block">
                    <table class="table">
                        <tr>
                            <th>Candidate Number</th>
                            <td>{{candidate.candidateNumber}}</td>
                        </tr>
                        <tr>
                            <th>Forename</th>
                            <td>{{candidate.user.forename}}</td>
                        </tr>
                        <tr>
                            <th>Surname</th>
                            <td>{{candidate.user.surname}}</td>
                        </tr>
                        <tr>
                            <th>Date of Birth</th>
                            <td>{{dateOfBirth}}</td>
                        </tr>
                        <tr>
                            <th>Flying experience</th>
                            <td>{{candidate.flyingExperience}}</td>
                        </tr>
                        <tr>
                            <th>Drone</th>
                            <td>{{candidate.drone.manufacturer}} {{candidate.drone.model}}</td>
                        </tr>
                        <tr>
                            <th>Has Payed</th>
                            <td>
                                <i v-if="candidate.payed" class="material-icons">check</i>
                                <i v-else class="material-icons">close</i>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="card-footer">
                    <button type="link" class="btn btn-primary" @click="goToUserProfile()">Show user account page</button>
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
                candidate: {
                    user: {},
                    address: {},
                    drone: {}
                },
                id: 0
            },
            computed: {
              dateOfBirth: function() {
                  var day = new Date(this.candidate.dateOfBirth).getDate();
                  var month = new Date(this.candidate.dateOfBirth).getMonth();
                  var year = new Date(this.candidate.dateOfBirth).getFullYear();
                  return day + " / " + month + " / " + year;
              }
            },
            methods: {
                refresh: function () {
                    axios.get("/api/v1/candidates/" + app.id)
                        .then(function (response) {
                            app.candidate = response.data;
                        });
                },
                goToUserProfile: function () {
                    window.location.href = "/admin/users/" + app.candidate.user.id;
                }
            }
        });
        app.id = url(-1);
        app.refresh();
    </script>
</#macro>

<@display_page "Manage Candidate"/>