$(document).ready(function () {
    getBooks();
})

function getBooks(){
    $.ajax({
        url: "http://localhost:8080/getBooks",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            var myTable = $('#booksTable').DataTable({
                data: data,
                paging: true,
                searching: false,
                destroy: true,
                autoWidth: true,
                columns: [{
                    data: null,
                    width: "5%"
                }, {
                    data: "title"
                }, {
                    data: "year"
                }, {
                    data: "epoch"
                }, {
                    data: "status"
                }, {
                    data: "rental_time"
                }, {
                    data: "email"
                }],
                columnDefs: [{ targets: 6, visible: $('#borrowBtn').is(":visible") === false }],
                order: [[ 1, 'asc' ]]
            })
            orderRows(myTable);
        }
    });
}

function orderRows(data) {
    data.on( 'order.dt search.dt', function () {
        data.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
}