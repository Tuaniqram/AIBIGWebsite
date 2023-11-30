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