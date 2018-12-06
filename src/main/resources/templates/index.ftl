<#include "common/base.ftl">
<#macro page_body>
    <div class="row">
        <div class="col-md-12">
            <div class="overview-wrap">
                <h2 class="title-1">overview</h2>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    Dashboard
                </div>
                <div class="card-body card-block">
                    <button class="btn btn-primary candidate stage-1" id="make-payment">Make Payment</button>
                    <h2 class="candidate stage-2">An admin will assign you to a ground school course soon.</h2>
                    <div class="admin">
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
                    var stageMetricsChart = new Chart(stageMetricsChartElement, {
                        type: 'bar',
                        data: {
                            datasets: [{
                                data: totals,
                                backgroundColor: [
                                    "rgba(20, 125, 255, 1)",
                                    "rgba(35, 120, 255, 0.9)",
                                    "rgba(50, 115, 255, 0.8)",
                                    "rgba(65, 110, 255, 0.7)",
                                    "rgba(80, 105, 255, 0.6)",
                                    "rgba(95, 100, 255, 0.5)",
                                    "rgba(110, 95, 255, 0.4)",
                                    "rgba(125, 90, 255, 0.3)",
                                    "rgba(140, 85, 255, 0.2)",
                                    "rgba(155, 80, 255, 0.1)"
                                ],
                                hoverBackgroundColor: [
                                    "rgba(20, 125, 200, 1)",
                                    "rgba(35, 120, 200, 0.9)",
                                    "rgba(50, 115, 200, 0.8)",
                                    "rgba(65, 110, 200, 0.7)",
                                    "rgba(80, 105, 200, 0.6)",
                                    "rgba(95, 100, 200, 0.5)",
                                    "rgba(110, 95, 200, 0.4)",
                                    "rgba(125, 90, 200, 0.3)",
                                    "rgba(140, 85, 200, 0.2)",
                                    "rgba(155, 80, 200, 0.1)"
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
                            legend: {
                                position: 'top',
                                labels: {
                                    fontFamily: 'Poppins'
                                }

                            },
                            responsive: true
                        }
                    });
                });
        }
    </script>
</#macro>

<@display_page/>