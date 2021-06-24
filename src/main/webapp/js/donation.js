$(document).ready(function () {
    getDonations();
})

function getDonations(){
    $.ajax({
        url: "http://localhost:8080/getDonations",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#donationsTable').DataTable({
                data: data,
                paging: false,
                searching: false,
                destroy: true,
                autoWidth: true,
                columns: [{
                    data: "lp"
                }, {
                    data: "description"
                }, {
                    data: "amount"
                }, {
                    data: "email"
                }
                ]
            })}
    });
}