
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
        register(registerData);
    };

}

function prepareRegisterEmployeeData() {
    let firstnameField = $('#firstname').val();
    let lastnameField = $('#lastname').val();
    let emailField = $('#email').val();
    let passField = $('#pass').val();
    let positionField = $('#position').val();
    let accountNumberField = $('#accountNo').val();
    let streetField = $('#street').val();
    let homeNumberField = $('#houseNo').val();
    let localNumberField = $('#apartmentNo').val();
    let cityField = $('#city').val();
    let postcodeField = $('#postcode').val();
    let registerData;
    if(emailField.trim().length===0 || passField.trim().length===0 || firstnameField.trim().length===0 || lastnameField.trim().length===0)
        alert('Uzupełnij brakujące pola');
    else {
        registerData = {
            //nazwy pól AppUserRegisterDTO
            firstName: firstnameField,
            lastName: lastnameField,
            email: emailField,
            password: passField,
            position: positionField,
            accountNumber: accountNumberField,
            street: streetField,
            homeNumber: homeNumberField,
            localNumber: localNumberField,
            city: cityField,
            postcode: postcodeField
        }
        registerEmployee(registerData);
    };

}

function register(data) {
    $.ajax({
        url: "http://localhost:8080/client/addAppUser",
        method: "POST",
        scriptCharset: "utf-8",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            alert('Rejestracja się powiodła');
            window.location.href = "http://localhost:8080/login";
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    })
}

function registerEmployee(data) {
    $.ajax({
        url: "http://localhost:8080/employee/addAppUser",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            alert('Rejestracja się powiodła');
            window.location.href = "http://localhost:8080/login";
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    })
}