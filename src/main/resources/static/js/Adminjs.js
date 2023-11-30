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

    // Initialize PerfectScrollbar
    // const wt = new PerfectScrollbar('.widget-todo');
    // const wtl = new PerfectScrollbar('.widget-timeline');

    ClassicEditor.create(document.querySelector("#newsDescription"));
    ClassicEditor.create(document.querySelector("#competitionDescription"));
    ClassicEditor.create(document.querySelector("#programDescription"));
    ClassicEditor.create(document.querySelector("#researchPaperDescription"));

    var table = $('#admintable').DataTable({
        deferRender: true,
        scrollCollapse: true,
        fixedColumns: true,
        paging: false,

        dom: 'Bfrtip',
        responsive: true,
        fixedHeader: {
            header: true,
            footer: true
        },
        buttons: [{
                extend: 'copy',
                text: 'Copy to clipboard'
            },
            'excel',
            'pdf'
        ]
    });

    table.on('click', 'tbody tr', function() {
        var $row = table.row(this).nodes().to$();
        var hasClass = $row.hasClass('selected');
        if (hasClass) {
            $row.removeClass('selected')
        } else {
            $row.addClass('selected')
        }
    })

    table.rows().every(function() {
        this.nodes().to$().removeClass('selected')
    });


    $(document).ready(function() {
        // Select the form element
        var form = $('form.needs-validation');

        // Submit event listener
        form.on('submit', function(event) {
            if (!form[0].checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.addClass('was-validated');
        });

        // Input event listener for each form element
        form.find('.form-control').on('input', function() {
            if (this.checkValidity()) {
                $(this).removeClass('is-invalid');
            } else {
                $(this).addClass('is-invalid');
            }
        });
    });

});