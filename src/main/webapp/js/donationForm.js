function prepareDonation() {
    let descriptionField = $('#floatingTextarea').val();
    let amountField = $('#donationsAmount').val();
    checkDonationData(descriptionField, amountField);
    payDonation(descriptionField, amountField);
}

function checkDonationData(description, amount) {
    if (description.trim().length === 0 || amount.trim().length === 0) {
        alert('Uzupełnij brakujące pola')
    }
}

function payDonation(descriptionField, amountField) {
    $.ajax({
        url: "http://localhost:8080/payDonation",
        method: "POST",
        data: {
            "description": descriptionField,
            "amount": amountField
        },
        success: function (xhr) {
            alert(xhr);
            fundReserve(amountField);
            window.location.href = "http://localhost:8080/donationForm";
        },
        error: function (xhr) {
            alert(xhr)
        }
    })
}

function fundReserve(amount) {
    $.ajax({
        url: "http://localhost:8080/fundReserve",
        method: "POST",
        data:{
            "amount": amount
        },
        success: function () {
            console.log('Rezerwa muzeum została zasilona kwotą ' + amount);
        },
        error: function () {
            console.log('Rezerwa muzeum nie została zasilona')
        },
    })
}