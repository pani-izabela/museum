let userRole;

function prepareLoginData() {
    let emailField = $('#email').val();
    let passField = $('#pass').val();
    // getListUserRoles(emailField);
    let loginData;
    if (emailField.trim().length === 0 || passField.trim().length === 0){
        alert("Uzupełnij brakujące dane.");
    }
    else {
        loginData = {
            email: emailField,
            pass: passField
        };
        login(loginData);
    }
}

function getListUserRoles(email) {
    $.ajax({
        url: "http://localhost:8080/getRoles?email="+email,
        type: "GET",
        contentType: "application/json",
        success: function (result) {
            setRole(result);
        },
        error: function () {
            console.log('Jest problem z pobraniem roli użytkownika')
        }
    });
}

function login(data) {
    $.ajax({
        url: "http://localhost:8080/performLogin",
        type: "POST",
        contentType: "application/x-www-form-urlencoded",
        data: data,
        success: function () {
            window.location.href = "http://localhost:8080/postLoginUser";
            // if(userRole===1){
            //     //pokaż widok dla admina, chwilowo co innego
            //     alert('Logowanie admina zakończone sukcesem')
            //     window.location.href = "http://localhost:8080/client/register";
            // }
            // else if(userRole===2){
            //     alert('Logowanie kierownika zakończone sukcesem')
            //     window.location.href = "http://localhost:8080/client/register";
            // }
            // else if(userRole===3){
            //     alert('Logowanie pracownika zakończone sukcesem')
            //     window.location.href = "http://localhost:8080/client/register";
            // }
            // else if(userRole===4){
            //     alert('Logowanie klienta zakończone sukcesem')
            //     window.location.href = "http://localhost:8080/client/register";
            // }
            // else {
            //     alert('Logowanie ... zakończone sukcesem')
            //     window.location.href = "http://localhost:8080/client/register";
            // }
        },
        error: function () {
            //alert("Błąd logowania - zweryfikuj login i hasło")
            window.location.href = "http://localhost:8080/login?error";
        }
    });
}

function setRole(result) {
    if (result.length === 1) {
        userRole = result[0].id;
        console.log('wartosc userRole.id to ' + userRole)
    } else {
        for (i = 0; i < result.length; i++) {
            if (result[i].id === 1){
                userRole=result[i].id
                break;
            }
            else if(result[i].id === 2){
                userRole=result[i].id
                break;
            }
            else {
                userRole=result[i].id
            }
        }
    }
}