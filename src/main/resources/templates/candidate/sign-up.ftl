<#include "../common/base.ftl">

<#macro style>
    <style>
        .autocomplete-container {
            position: relative;
        }

        .autocomplete-content {
            position: absolute;
            background: white;
            border: 2px solid #c9c9c9;
            border-top: 0;
            min-width: 445px;
            z-index: 1;
        }
        .autocomplete-content .header {
            padding: 10px;
        }
        .autocomplete-content ul {
            width: 100%;
            list-style-type: none;
            padding: 0;
            margin: 0;
        }
        .autocomplete-content li {
            padding: 10px;
            cursor: pointer;
        }
        .autocomplete-content li:hover {
            background: #e6e6e6;
        }

        .chosen-item h4 {
            margin-top: 10px;
        }
        .chosen-item p {
            margin: 5px 0;
        }

        .remove-item {
            font-weight: bold;
            color: red;
            display: inline-block;
            margin-left: 20px;
        }
        .remove-item:hover {
            cursor: pointer;
        }
    </style>
</#macro>

<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Sign up as candidate
                </div>
                <div class="card-body card-block">
                    <form method="post" id="candidate-sign-up" @submit.prevent="submit()">
                        <div class="form-group">
                            <label for="preferedLocation" class="form-control-label">Prefered Location *</label>
                            <select v-model="candidateSignup.preferedLocation" class="form-control" id="preferedLocation" name="preferedLocation" required>
                                <option v-for="l in locations" value="l.id">Cardiff</option>
                                <option value="Somerset">Somerset</option>
                                <option value="Aberdeen">Aberdeen</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="dob" class="form-control-label">Date of Birth *</label>
                            <input v-model="candidateSignup.dob" :max="getMaxDob()" class="form-control" class="form-control" type="date" id="dob" name="dob" required>
                        </div>
                        <div class="form-group">
                            <label for="address1" class="form-control-label">Address Line 1 *</label>
                            <input v-model="candidateSignup.address1" class="form-control" type="text" id="address1" name="address1" required>
                        </div>
                        <div class="form-group">
                            <label for="address2" class="form-control-label">Address Line 2 <small>(optional)</small></label>
                            <input v-model="candidateSignup.address2" class="form-control" type="text" id="address2" name="address2">
                        </div>
                        <div class="form-group">
                            <label for="city" class="form-control-label">Town / City *</label>
                            <input v-model="candidateSignup.city" class="form-control" type="text" id="city" name="city" required>
                        </div>
                        <div class="form-group">
                            <label for="county" class="form-control-label">County <small>(optional)</small></label>
                            <input v-model="candidateSignup.county" class="form-control" type="text" id="county" name="county">
                        </div>
                        <div class="form-group">
                            <label for="postcode" class="form-control-label">Postcode *</label>
                            <input v-model="candidateSignup.postcode" class="form-control" type="text" id="postcode" name="postcode" required>
                        </div>
                        <div class="form-group">
                            <label for="company-name" class="form-control-label">Company Name <small>(optional)</small></label>
                            <input v-model="candidateSignup.companyName" class="form-control" type="text" id="company-name" name="company-name">
                        </div>
                        <div class="form-group">
                            <label for="company-email" class="form-control-label">Company Email <small>(optional)</small></label>
                            <input v-model="candidateSignup.companyEmail" class="form-control" type="email" id="company-email" name="company-email">
                        </div>
                        <div class="form-group">
                            <label for="company-phone-number" class="form-control-label">Company Phone Number <small>(optional)</small></label>
                            <input v-model="candidateSignup.companyPhone" class="form-control" type="email" id="company-phone-number" name="company-phone-number">
                        </div>
                        <div class="form-group autocomplete-container">
                            <label for="drone-selection" class="form-control-label">Search for your drone <a href="/candidate/signup/newdrone" target="_blank"><i class="material-icons">add_circle</i></a></label>
                            <input class="form-control" type="text" id="drone-selection" name="drone-selection" v-model="searchTerm" @input="find()" autocomplete="off" placeholder="Search for drone (manufacturer model)">
                            <div v-if="showSuggestions" class="autocomplete-content">
                                <h4 class="header">Suggestions</h4>
                                <ul v-for="s in suggestions">
                                    <li @click="selectSuggestion(s)">{{s.manufacturer}} {{s.model}}</li>
                                </ul>
                            </div>
                            <div class="chosen-items">
                                <h4>Selected</h4>
                                <p v-if="selectedItem.manufacturer === undefined">No Item</p>
                                <p v-else>{{selectedItem.manufacturer}} {{selectedItem.model}} <span @click="removeItem()" class="remove-item">X</span></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="flying-experience" class="form-control-label">Flying Experience Details *</label>
                            <input v-model="candidateSignup.flyingExperience" class="form-control" type="text" id="flying-experience" name="flying-experience" required>
                        </div>
                        <div>* means required.</div>
                    </form>
                </div>
                <div class="card-footer">
                    <button type="submit" class="btn btn-primary" form="candidate-sign-up">
                        Submit
                    </button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro modals>
    <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Success</h5>
                </div>
                <div class="modal-body">Candidate sign up successful, you will now be prompted to re-login.</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="window.location.href =  '/account/logout'">Confirm</button>
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
                showSuggestions: false,
                searchTerm: "",
                suggestions: [],
                selectedItem: {},
                candidateSignup: { },
                locations: []
            },
            created: function() {
                axios.get("/api/v1/gscourses/locations/all")
                    .then(function (response) {
                        this.locations = response.data;
                    });
            },
            methods: {
                find: function() {
                    if (app.searchTerm === "") {
                        app.showSuggestions = false;
                        return;
                    }
                    app.showSuggestions = true;
                    axios.get("/api/v1/drones/autocomplete?search=" + encodeURIComponent(app.searchTerm))
                        .then(function(response) {
                            app.suggestions = response.data;
                        });
                },
                selectSuggestion: function(s) {
                    app.selectedItem = s;
                    $("#drone-selection").attr('disabled','disabled');
                    app.searchTerm = "";
                    app.showSuggestions = false;
                },
                removeItem: function() {
                    app.selectedItem = {};
                    $("#drone-selection").removeAttr('disabled');
                },
                submit: function() {
                    app.candidateSignup.droneId = app.selectedItem.id;
                    axios.post("/api/v1/candidates", app.candidateSignup)
                        .then(function() {
                            $("#successModal").modal();
                        });
                },
                getMaxDob: function () {
                    var eighteenYearsAgo = new Date();
                    eighteenYearsAgo.setFullYear(eighteenYearsAgo.getFullYear()-18);
                    return eighteenYearsAgo.toISOString().split('T')[0];
                }
            }
        });
    </script>
</#macro>

<@display_page "Candidate Sign up"/>