$(document).ready(function() {
    // Chartist Line Chart
    // Fetch data from the backend
    $.ajax({
        url: '/admin/monthly-publication-count',
        type: 'GET',
        success: function(data) {
            var chartData = {
                labels: Object.keys(data),
                series: [{
                    name: 'publication',
                    data: Object.values(data)
                }]
            };

            var options = {
                low: 0,
                plugins: [
                    Chartist.plugins.tooltip()
                ]
            };

            var seq = 0,
                delays = 80,
                durations = 500;

            var chart = new Chartist.Line('#smil-animations', chartData, options);

            function animateElement(data, seq) {
                seq++;

                if (data.type === 'line') {
                    // If the drawn element is a line we do a simple opacity fade in. This could also be achieved using CSS3 animations.
                    data.element.animate({
                        opacity: {
                            // The delay when we like to start the animation
                            begin: seq * delays + 1000,
                            // Duration of the animation
                            dur: durations,
                            // The value where the animation should start
                            from: 0,
                            // The value where it should end
                            to: 1
                        }
                    });
                } else if (data.type === 'label' && data.axis.counterUnits.pos === 'x') {
                    data.element.animate({
                        y: {
                            begin: seq * delays,
                            dur: durations,
                            from: data.y + 100,
                            to: data.y,
                            // We can specify an easing function from Svg.Easing
                            easing: 'easeOutQuart'
                        }
                    });
                } else if (data.type === 'label' && data.axis.counterUnits.pos === 'y') {
                    data.element.animate({
                        x: {
                            begin: seq * delays,
                            dur: durations,
                            from: data.x - 100,
                            to: data.x,
                            easing: 'easeOutQuart'
                        }
                    });
                } else if (data.type === 'point') {
                    data.element.animate({
                        x1: {
                            begin: seq * delays,
                            dur: durations,
                            from: data.x - 10,
                            to: data.x,
                            easing: 'easeOutQuart'
                        },
                        x2: {
                            begin: seq * delays,
                            dur: durations,
                            from: data.x - 10,
                            to: data.x,
                            easing: 'easeOutQuart'
                        },
                        opacity: {
                            begin: seq * delays,
                            dur: durations,
                            from: 0,
                            to: 1,
                            easing: 'easeOutQuart'
                        }
                    });
                } else if (data.type === 'grid') {
                    // Using data.axis we get x or y which we can use to construct our animation definition objects
                    const pos1Key = `${data.axis.units.pos}1`;
                    const pos1Value = data[pos1Key];
                    const pos1Animation = {
                        begin: seq * delays,
                        dur: durations,
                        from: pos1Value - 30,
                        to: pos1Value,
                        easing: 'easeOutQuart'
                    };

                    const pos2Key = data.axis.units.pos + '2';
                    const pos2Value = data[pos2Key];
                    const pos2Animation = {
                        begin: seq * delays,
                        dur: durations,
                        from: pos2Value - 100,
                        to: pos2Value,
                        easing: 'easeOutQuart'
                    };

                    const animations = {
                        [data.axis.units.pos + '1']: pos1Animation,
                        [data.axis.units.pos + '2']: pos2Animation,
                        opacity: {
                            begin: seq * delays,
                            dur: durations,
                            from: 0,
                            to: 1,
                            easing: 'easeOutQuart'
                        }
                    };

                    data.element.animate(animations);
                }
            }

            chart.on('created', function(data) {
                seq = 0;
                animateElement(chart, seq);
            });

            chart.on('draw', function(data) {
                animateElement(data, seq);
            });
        },
        error: function(error) {
            console.error('Error fetching data: ', error);
        }
    });

    // Chart.js Bar Chart
    const barChart_2 = document.getElementById("barChart_2").getContext('2d');
    const barChart_2gradientStroke = barChart_2.createLinearGradient(0, 0, 0, 250);
    barChart_2gradientStroke.addColorStop(0, "rgba(26, 51, 213, 1)");
    barChart_2gradientStroke.addColorStop(1, "rgba(26, 51, 213, 0.5)");

    barChart_2.height = 100;

    new Chart(barChart_2, {
        type: 'bar',
        data: {
            defaultFontFamily: 'Poppins',
            labels: ["DR. MUHAMMAD AKMAL", "TS. DR MAZLAN", "DR. DOUDA", "TS. DR NOORAZIAH", "TS. DR. MARIEANNE", "TS. CIK SALMIAH"],
            datasets: [{
                label: "Total Student",
                data: [64, 70, 40, 57, 68, 43],
                borderColor: barChart_2gradientStroke,
                borderWidth: 0,
                backgroundColor: barChart_2gradientStroke,
                hoverBackgroundColor: barChart_2gradientStroke
            }]
        },
        options: {
            legend: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }],
                xAxes: [{
                    barPercentage: 0.5
                }]
            }
        }
    });

    // Initialize PerfectScrollbar if the elements exist
    if ($('.widget-todo').length) {
        const wt = new PerfectScrollbar('.widget-todo');
    }

    if ($('.widget-timeline').length) {
        const wtl = new PerfectScrollbar('.widget-timeline');
    }

    // Chart.js Pie Chart
    const pie_chart = document.getElementById("pie_chart").getContext('2d');
    const pieChartCanvas = document.getElementById("pie_chart");
    pieChartCanvas.height = 400;
    new Chart(pie_chart, {
        type: 'pie',
        data: {
            defaultFontFamily: 'Poppins',
            datasets: [{
                data: [45, 25, 20, 10, 50],
                borderWidth: 0,
                backgroundColor: [
                    "rgba(0, 171, 197, .9)",
                    "rgba(0, 171, 197, .7)",
                    "rgba(0, 171, 197, .5)",
                    "rgba(0, 171, 197, .3)",
                    "rgba(0,0,0,0.07)"
                ],
                hoverBackgroundColor: [
                    "rgba(0, 171, 197, .9)",
                    "rgba(0, 171, 197, .7)",
                    "rgba(0, 171, 197, .5)",
                    "rgba(0, 171, 197, .3)",
                    "rgba(0,0,0,0.07)"
                ]
            }],
            labels: ["DR. MUHAMMAD AKMAL", "TS. DR MAZLAN", "DR. DOUDA", "TS. DR NOORAZIAH", "TS. DR. MARIEANNE", "TS. CIK SALMIAH"]
        },
        options: {
            responsive: true,
            legend: false,
            maintainAspectRatio: false
        }
    });

    // Initialize PerfectScrollbar
    const wt = new PerfectScrollbar('.widget-todo');
    const wtl = new PerfectScrollbar('.widget-timeline');
});