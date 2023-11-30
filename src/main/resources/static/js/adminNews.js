// News and Events datatable

$(document).ready(function() {
    var newsTable = $('#newstable').DataTable({
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

    newsTable.on('click', 'tbody tr', function() {
        var $row = newsTable.row(this).nodes().to$();
        var hasClass = $row.hasClass('selected');
        if (hasClass) {
            $row.removeClass('selected')
        } else {
            $row.addClass('selected')
        }
    })

    newsTable.rows().every(function() {
        this.nodes().to$().removeClass('selected')
    });

    var meetingTable = $('#meetingtable').DataTable({
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

    meetingTable.on('click', 'tbody tr', function() {
        var $row = meetingTable.row(this).nodes().to$();
        var hasClass = $row.hasClass('selected');
        if (hasClass) {
            $row.removeClass('selected')
        } else {
            $row.addClass('selected')
        }
    })

    meetingTable.rows().every(function() {
        this.nodes().to$().removeClass('selected')
    });

    var seminarTable = $('#seminartable').DataTable({
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

    seminarTable.on('click', 'tbody tr', function() {
        var $row = seminarTable.row(this).nodes().to$();
        var hasClass = $row.hasClass('selected');
        if (hasClass) {
            $row.removeClass('selected')
        } else {
            $row.addClass('selected')
        }
    })

    seminarTable.rows().every(function() {
        this.nodes().to$().removeClass('selected')
    });

    var conferenceTable = $('#conferencetable').DataTable({
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

    conferenceTable.on('click', 'tbody tr', function() {
        var $row = conferenceTable.row(this).nodes().to$();
        var hasClass = $row.hasClass('selected');
        if (hasClass) {
            $row.removeClass('selected')
        } else {
            $row.addClass('selected')
        }
    })

    conferenceTable.rows().every(function() {
        this.nodes().to$().removeClass('selected')
    });

    var forumTable = $('#forumtable').DataTable({
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

    forumTable.on('click', 'tbody tr', function() {
        var $row = forumTable.row(this).nodes().to$();
        var hasClass = $row.hasClass('selected');
        if (hasClass) {
            $row.removeClass('selected')
        } else {
            $row.addClass('selected')
        }
    })

    forumTable.rows().every(function() {
        this.nodes().to$().removeClass('selected')
    });
});