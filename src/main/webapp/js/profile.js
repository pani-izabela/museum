$(document).ready(function () {
    getFormData();
    hideButton();
})

var userDataFormIsDisabled
var userData;
var loggedUser;

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
    setFormDisabled();
    $('#userDataForm')[0].reset();
}

function submitChangeDataAppUser(){
    prepareDataToChange()
    $.ajax({
        url: "http://localhost:8080/changeDataAppUser",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(userData),
        success: function (res) {
            alert(res.responseText);
        },
        error: function (res) {
            alert(res.responseText);
        }

    })
}

function prepareDataToChange() {
    userData = {
        email: $('#emailField').val(),
        firstName: $('#nameField').val(),
        lastName: $('#lastNameField').val(),
        password: $('#passwordField').val()
    };
}

function setFormDisabled() {
    hideButton();
    $('#changeBtn').show();
    $('#userDataProfile').prop('disabled', true);
    $('#userDataProfile').css("background", "lightgray");
    $('.inp').css("background", "lightgray");
}
