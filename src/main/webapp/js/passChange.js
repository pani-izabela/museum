
function prepareData() {
    let emailField = $('#email').val();
    let newPassField = $('#pass').val();
    checkFields(emailField, newPassField);
    changePass(emailField, newPassField);
}

function checkFields(emailField, newPassField) {
    if (emailField.trim().length === 0 || newPassField.trim().length === 0)
        alert("Pola nie zostały prawidłowo wypełnione");
}

function changePass(emailField, newPassField) {
    $.ajax({
        url: "http://localhost:8080/changePassword",
        method: "POST",
        data: {
            "email": emailField,
            "newPass": newPassField
        },
        success: function (xhr) {
            alert(xhr);
            window.location.href = "http://localhost:8080/login";
        },
        error: function (xhr) {
            alert(xhr.responseText)
        }
    })
}