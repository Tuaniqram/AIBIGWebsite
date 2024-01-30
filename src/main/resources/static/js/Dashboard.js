$("#mainpage").ready(function() {
    $.ajax({
        url: '/api/publication',
        type: 'GET',
        success: function(data) {
            var authorCounts = {}; // Object to store author counts

            // Count occurrences of each author
            data.forEach(function(publication) {
                var authors = publication.publicationAuthors.split(','); // Assuming authors are comma-separated
                authors.forEach(function(author) {
                    author = author.trim(); // Trim whitespace
                    if (author in authorCounts) {
                        authorCounts[author]++;
                    } else {
                        authorCounts[author] = 1;
                    }
                });
            });

            // Prepare data for chart
            var chartData = {
                labels: Object.keys(authorCounts), // Authors
                series: [{
                    name: 'publication',
                    data: Object.values(authorCounts) // Publication counts
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
});


$(document).ready(function() {
    $.ajax({
        url: "/api/news",
        type: "GET",
        success: function(newsData) {
            console.log("Data successfully fetched:", newsData);
            let categoryCounts = {
                "News": 0,
                "Meeting": 0,
                "Seminar": 0,
                "Conferences": 0,
                "Forums": 0
            };

            // Count occurrences of each category
            newsData.forEach(function(newsItem) {
                let category = newsItem.newsCategory;

                if (categoryCounts.hasOwnProperty(category)) {
                    categoryCounts[category]++;
                }
            });

            const barChart_2 = $("#barChart_2").get(0).getContext('2d');

            // Generate gradient
            const barChart_2gradientStroke = barChart_2.createLinearGradient(0, 0, 0, 250);
            barChart_2gradientStroke.addColorStop(0, "rgba(26, 51, 213, 1)");
            barChart_2gradientStroke.addColorStop(1, "rgba(26, 51, 213, 0.5)");

            // Set chart height
            barChart_2.canvas.height = 100;

            // Update labels and data using categoryCounts
            const labels = Object.keys(categoryCounts);
            const data = Object.values(categoryCounts);

            new Chart(barChart_2, {
                type: 'bar',
                data: {
                    defaultFontFamily: 'Poppins',
                    labels: labels,
                    datasets: [{
                        label: "News Count by Category",
                        data: data,
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
                            // Change here
                            barPercentage: 0.5
                        }]
                    }
                }
            });

            console.log("Category Counts:", categoryCounts);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching data:", error);
        }
    });
});

$(document).ready(function() {
    $.ajax({
        url: "/api/internships",
        type: "GET",
        success: function(internships) {
            console.log("Internships successfully fetched:", internships);
            // Sort internships by internStart date in descending order
            internships.sort((a, b) => new Date(b.internStart) - new Date(a.internStart));
            // Select the first 5 internships
            internships = internships.slice(0, 5);
            renderInternships(internships);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching internships:", error);
        }
    });

    function renderInternships(internships) {
        const internshipTableBody = $("#internshipTableBody");
        internshipTableBody.empty(); // Clear existing data

        internships.forEach(function(internship, index) {
            // Format the date strings
            const startDate = formatDate(internship.internStart);
            const endDate = formatDate(internship.internEnd);

            const row = `
                <tr>
                    <td>${index + 1}</td>
                    <td>${internship.name}</td>
                    <td><span>${internship.project}</span></td>
                    <td><span>${startDate} - ${endDate}</span></td>
                    <td><span>${internship.university}</span></td>
                </tr>
            `;
            internshipTableBody.append(row);
        });
    }

    function formatDate(dateString) {
        const parts = dateString.split('-'); // Split the date string
        if (parts.length === 3) {
            return `${parts[2]}/${parts[1]}/${parts[0]}`; // Reorder the components and join them
        }
        return dateString; // Return original string if format is not valid
    }
});

$(document).ready(function() {
    $.ajax({
        url: "/api/researchpapers",
        type: "GET",
        success: function(researchPapers) {
            console.log("Research papers successfully fetched:", researchPapers);
            renderResearchPapers(researchPapers);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching research papers:", error);
        }
    });

    function renderResearchPapers(researchPapers) {
        const researchPaperTableBody = $("#researchPaperTableBody");
        researchPaperTableBody.empty(); // Clear existing data

        // Sort research papers by date in descending order
        researchPapers.sort((a, b) => new Date(b.researchPaperDate) - new Date(a.researchPaperDate));

        // Select the top 5 latest research papers
        const top5Papers = researchPapers.slice(0, 5);

        top5Papers.forEach(function(paper, index) {
            // Format the date string
            const formattedDate = formatDate(paper.researchPaperDate);

            const row = `
                <tr>
                    <td>${index + 1}</td>
                    <td>${paper.researchPaperTitle}</td>
                    <td>${paper.researchPaperCategory}</td>
                    <td>${formattedDate}</td>
                </tr>
            `;
            researchPaperTableBody.append(row);
        });
    }


    function formatDate(dateString) {
        const parts = dateString.split('-'); // Split the date string
        if (parts.length === 3) {
            return `${parts[2]}/${parts[1]}/${parts[0]}`; // Reorder the components and join them
        }
        return dateString; // Return original string if format is not valid
    }
});

$(document).ready(function() {
    $.ajax({
        url: "/api/annex",
        type: "GET",
        success: function(annexData) {
            console.log("Annex data successfully fetched:", annexData);

            // Process the fetched data to count the occurrences of each category
            let categoryCounts = {};
            annexData.forEach(function(annex) {
                let category = annex.annexType;
                if (categoryCounts.hasOwnProperty(category)) {
                    categoryCounts[category]++;
                } else {
                    categoryCounts[category] = 1;
                }
            });

            // Extract labels and data from categoryCounts object
            let labels = Object.keys(categoryCounts);
            let data = Object.values(categoryCounts);

            renderPieChart(labels, data);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching annex data:", error);
        }
    });

    function renderPieChart(labels, data) {
        const pieChartCanvas = $("#pie_chart").get(0).getContext('2d');

        new Chart(pieChartCanvas, {
            type: 'pie',
            data: {
                defaultFontFamily: 'Poppins',
                datasets: [{
                    data: data,
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
                labels: labels
            },
            options: {
                responsive: true,
                legend: false,
                maintainAspectRatio: false
            }
        });
    }
});

$(document).ready(function() {
    $.ajax({
        url: "/program",
        type: "GET",
        success: function(programData) {
            console.log("Program data successfully fetched:", programData);

            // Sort the fetched data based on program date in descending order
            programData.sort((a, b) => new Date(b.programDate) - new Date(a.programDate));
            // Select the top 5 programs
            const top5Programs = programData.slice(0, 5);

            renderPrograms(top5Programs);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching program data:", error);
        }
    });

    function renderPrograms(programs) {
        const programTableBody = $(".card-body .table tbody");
        programTableBody.empty(); // Clear existing data

        programs.forEach(function(program, index) {
            const row = `
                <tr>
                    <td>${index + 1}</td>
                    <td>${program.programName}</td>
                    <td>${program.programVenue}</td>
                    <td>${program.programDate}</td>
                    <td>${program.programFee}</td>
                </tr>
            `;
            programTableBody.append(row);
        });
    }
});