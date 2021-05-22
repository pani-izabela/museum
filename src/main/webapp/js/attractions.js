$(document).ready(function () {
    unlockAttraction();
})

function unlockAttraction(){
    $.ajax({
        url: "http://localhost:8080/checkClientsTicket",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if(data===false){
                $('#ticketLessView').show();
                $('#viewWithTicket').hide();
            }
            else {
                $('#ticketLessView').hide();
                $('#viewWithTicket').show();
            }
        }
    });

}