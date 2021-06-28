$(document).ready(function () {
    getDonations();
})

function getDonations(){
    $.ajax({
        url: "http://localhost:8080/getDonations",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            var myTable = $('#donationsTable').DataTable({
                data: data,
                paging: true,
                searching: false,
                destroy: true,
                autoWidth: true,
                columns: [{
                    data: null,
                    width: "5%"
                }, {
                    data: "description"
                }, {
                    data: "amount"
                }, {
                    data: "email"
                }],
                columnDefs: [{ targets: 3, visible: $('#donationBtn').is(":visible") === false }],
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