$(document).ready(function () {
    getFormData();
    hideButton();
})

var userDataFormIsDisabled;

function getFormData() {
    userDataFormIsDisabled = $('#userDataProfile').is(':disabled');
}

function hideButton() {
    $('#submitBtn').hide();
    $('#backBtn').hide();
}

function changeUserData() {
    if(userDataFormIsDisabled){
        $('#userDataProfile').css("background", "white");
        $('.inp').css("background", "white");
        $('#userDataProfile').removeAttr("disabled");
        $('#changeBtn').hide();
        $('#submitBtn').show();
        $('#backBtn').show();
    }
}

function resignFromChangingData() {
    hideButton();
    $('#changeBtn').show();
    $('#userDataProfile').prop('disabled', true);
    $('#userDataProfile').css("background", "lightgray");
    $('.inp').css("background", "lightgray");
}