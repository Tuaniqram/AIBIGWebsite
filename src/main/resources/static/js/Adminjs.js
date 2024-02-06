// Initialize PerfectScrollbar
// const wt = new PerfectScrollbar('.widget-todo');
// const wtl = new PerfectScrollbar('.widget-timeline');


$(document).ready(function() {

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

function confirmDelete(deleteId, args) {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: "btn btn-success",
            cancelButton: "btn btn-danger"
        },
        buttonsStyling: false
    });

    swalWithBootstrapButtons.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes, delete it!",
        cancelButtonText: "No, cancel!",
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            // Send AJAX request to delete collaboration
            $.ajax({
                type: "GET",
                url: args + deleteId,
                success: function(data) {
                    // Handle success response
                    swalWithBootstrapButtons.fire({
                        title: "Deleted!",
                        text: "Your file has been deleted.",
                        icon: "success"
                    }).then(() => {
                        location.reload();
                    });
                },
                error: function(error) {
                    // Handle error response
                    swalWithBootstrapButtons.fire({
                        title: "Error",
                        text: "An error occurred while deleting this data.",
                        icon: "error"
                    });
                }
            });
        } else if (
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire({
                title: "Cancelled",
                text: "Your Data file is safe :)",
                icon: "error"
            });
        }
    });
}

$(document).ready(function() {
    $('.delete-btn').on('click', function() {
        // Get collaboration ID from the button's data attribute or other appropriate way
        var deleteId = $(this).data('delete-id');
        var args = $(this).data('args');
        confirmDelete(deleteId, args);
    });
});