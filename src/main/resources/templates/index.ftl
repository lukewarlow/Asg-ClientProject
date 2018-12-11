<#include "common/base.ftl">
<#macro page_body>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Dashboard
                </div>
                <div class="card-body card-block">
                    <button class="btn btn-primary candidate stage-1" id="make-payment">Make Payment</button>
                    <h3 class="candidate stage-2">An admin will assign you to a ground school course soon.</h3>
                    <h3 class="candidate stage-3">You have been assigned to a ground school course.</h3>
                    <h3 class="candidate stage-4">Please download the operation manual template and submit when complete.</h3>
                    <h3 class="candidate stage-5">Please await the results from your operation manual submission.</h3>
                    <h3 class="candidate stage-6">You will be contacted to arrange your flight assessment.</h3>
                    <h3 class="candidate stage-7">Please await the flight assessment results.</h3>
                    <h3 class="candidate stage-8">Currently going through PfCO recommendation process.</h3>
                    <h3 class="candidate stage-9">Feel free to submit feedback of your time with us.</h3>
                    <h3 class="candidate stage-10">Thank you for your custom you have now completed the course.</h3>
                    <div class="admin">
                        <h4 class="text-center">Number of candidates at each stage</h4>
                        <canvas id="stage-metrics-chart"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro scripts>
    <script>
        $('#make-payment').on('click', function () {
            axios.post("/api/v1/candidates/sendpayment")
                    .then(function () {
                        location.reload(true);
                    });
        });

        if ($("#stage-metrics-chart")) {
            axios.get("/api/v1/metrics/stagenumbers")
                .then(function (response) {
                    var stageMetrics = response.data;
                    var totals = [];
                    for (var i = 0; i < 10; i++) {
                        var metricsForThisStage = stageMetrics.filter(function (s) {
                            return s.stageId === i + 1;
                        });

                        if (metricsForThisStage.length === 1) {
                            totals[i] = metricsForThisStage[0].total;
                        } else {
                            totals[i] = 0;
                        }
                    }
                    var stageMetricsChartElement = $("#stage-metrics-chart");
                    stageMetricsChartElement.height = 150;
                    Chart.defaults.global.legend.display = false;
                    var stageMetricsChart = new Chart(stageMetricsChartElement, {
                        type: 'bar',
                        data: {
                            datasets: [{
                                data: totals,
                                backgroundColor: [
                                    "rgba(148, 0, 211, 1)",
                                    "rgba(75, 0, 130, 1)",
                                    "rgba(0, 0, 255, 1)",
                                    "rgba(0, 255, 0, 1)",
                                    "rgba(255, 255, 0, 1)",
                                    "rgba(255, 127, 0, 1)",
                                    "rgba(255, 0 , 0, 1)",
                                    "rgba(211, 0, 148, 1)",
                                    "rgba(130, 0, 75, 1)",
                                    "rgba(255, 0, 255, 1)"
                                ],
                                hoverBackgroundColor: [
                                    "rgba(148, 0, 211, 0.8)",
                                    "rgba(75, 0, 130, 0.8)",
                                    "rgba(0, 0, 255, 0.8)",
                                    "rgba(0, 255, 0, 0.8)",
                                    "rgba(255, 255, 0, 0.8)",
                                    "rgba(255, 127, 0, 0.8)",
                                    "rgba(255, 0 , 0, 0.8)",
                                    "rgba(211, 0, 148, 0.8)",
                                    "rgba(130, 0, 75, 0.8)",
                                    "rgba(255, 0, 255, 0.8)"
                                ]

                            }],
                            labels: [
                                'Needs To Make Payment',
                                'Awaiting Ground School Assignment',
                                'Awaiting Ground School Result',
                                'Needs To Submit Operations Manual',
                                'Awaiting Operations Manual Result',
                                'Awaiting Flight Assessment',
                                'Awaiting Flight Assessment Result',
                                'Awaiting PfCO Recommendation',
                                'Feedback Collection',
                                'Completed'
                            ]
                        },
                        options: {
                            scales: {
                                xAxes: [{
                                    ticks: {
                                        fontSize: "12"
                                    }
                                }],
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true,
                                        fontSize: "15"
                                    }
                                }]
                            },
                            responsive: true
                        }
                    });
                });
        }
    </script>
</#macro>

<@display_page/>