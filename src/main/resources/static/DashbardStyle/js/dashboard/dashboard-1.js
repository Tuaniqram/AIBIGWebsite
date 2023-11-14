$(document).ready(function() {
    // Chartist Line Chart
    var chartData = {
        labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
        series: [
            [12, 9, 7, 8, 5, 4, 6, 2, 3, 3, 4, 6],
            [4, 5, 3, 7, 3, 5, 5, 3, 4, 4, 5, 5],
            [5, 3, 4, 5, 6, 3, 3, 4, 5, 6, 3, 4],
            [3, 4, 5, 6, 7, 6, 4, 5, 6, 7, 6, 3]
        ]
    };

    var options = {
        low: 0,
        plugins: [Chartist.plugins.tooltip()]
    };

    var seq = 0,
        delays = 80,
        durations = 500;

    var chart = new Chartist.Line('#smil-animations', chartData, options);

    function animateElement(data, seq) {
        seq++;

        if (data.type === 'line') {
            data.element.animate({
                opacity: {
                    begin: seq * delays + 1000,
                    dur: durations,
                    from: 0,
                    to: 1
                }
            });
        } else if (data.type === 'label' && data.axis === 'x') {
            data.element.animate({
                y: {
                    begin: seq * delays,
                    dur: durations,
                    from: data.y + 100,
                    to: data.y,
                    easing: 'easeOutQuart'
                }
            });
        } else if (data.type === 'label' && data.axis === 'y') {
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
            var pos1Animation = {
                begin: seq * delays,
                dur: durations,
                from: data[data.axis.units.pos + '1'] - 30,
                to: data[data.axis.units.pos + '1'],
                easing: 'easeOutQuart'
            };

            var pos2Animation = {
                begin: seq * delays,
                dur: durations,
                from: data[data.axis.units.pos + '2'] - 100,
                to: data[data.axis.units.pos + '2'],
                easing: 'easeOutQuart'
            };

            var animations = {};
            animations[data.axis.units.pos + '1'] = pos1Animation;
            animations[data.axis.units.pos + '2'] = pos2Animation;
            animations['opacity'] = {
                begin: seq * delays,
                dur: durations,
                from: 0,
                to: 1,
                easing: 'easeOutQuart'
            };

            data.element.animate(animations);
        }
    }

    chart.on('created', function() {
        seq = 0;
        animateElement(chart, seq);
    });

    chart.on('draw', function(data) {
        animateElement(data, seq);
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
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"],
            datasets: [{
                label: "My First dataset",
                data: [65, 59, 80, 81, 56, 55, 40],
                borderColor: barChart_2gradientStroke,
                borderWidth: "0",
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

    // Chart.js Pie Chart
    const pie_chart = document.getElementById("pie_chart").getContext('2d');
    new Chart(pie_chart, {
        type: 'pie',
        data: {
            defaultFontFamily: 'Poppins',
            datasets: [{
                data: [45, 25, 20, 10],
                borderWidth: 0,
                backgroundColor: [
                    "rgba(0, 171, 197, .9)",
                    "rgba(0, 171, 197, .7)",
                    "rgba(0, 171, 197, .5)",
                    "rgba(0,0,0,0.07)"
                ],
                hoverBackgroundColor: [
                    "rgba(0, 171, 197, .9)",
                    "rgba(0, 171, 197, .7)",
                    "rgba(0, 171, 197, .5)",
                    "rgba(0,0,0,0.07)"
                ]
            }],
            labels: ["one", "two", "three", "four"]
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