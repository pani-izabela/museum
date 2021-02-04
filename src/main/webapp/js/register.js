
function prepareRegisterClientData() {
    let firstnameField = $('#firstname').val();
    let lastnameField = $('#lastname').val();
    let emailField = $('#email').val();
    let passField = $('#pass').val();
    let registerData;
    if(emailField.trim().length===0 || passField.trim().length===0 || firstnameField.trim().length===0 || lastnameField.trim().length===0)
        alert('Uzupełnij brakujące pola');
    else {
        registerData = {
            //nazwy pól AppUserRegisterDTO/AppUser
            firstName: firstnameField,
            lastName: lastnameField,
            email: emailField,
            password: passField
        }
    };
    register(registerData);
}

function register(data) {
    $.ajax({
        url: "http://localhost:8080/client/addAppUser",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            alert('Rejestracja się powiodła');
            window.location.href = "http://localhost:8080/login";
        },
        error: function () {
            alert('Nie udało się zarejestrować');
        }
    })
}