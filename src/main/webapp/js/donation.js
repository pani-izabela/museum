$(document).ready(function () {
    getDonations();
    getDonationsViewClient();
})

function getDonations(){
    $.ajax({
        url: "http://localhost:8080/getDonations",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#donationsTableEmployee').DataTable({
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
function getDonationsViewClient(){
    $.ajax({
        url: "http://localhost:8080/getDonations",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#donationsTableClient').DataTable({
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
                }]
            })}
    });
}