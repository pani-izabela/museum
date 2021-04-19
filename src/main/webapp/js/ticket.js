$(document).ready(function () {
    setStyleInputForNumbers();
})

function setStyleInputForNumbers() {
    $("input[type='number']").inputSpinner();
}

/*function setStyleInputForNumbers() {
    $("input[type='number']").inputSpinner({
        decrementButton: "<strong>-</strong>",
        incrementButton: "<strong>+</strong>",
        groupClass: "input-group-spinner",
        buttonsClass: "btn-outline-secondary",
        buttonsWidth: "2.5em",
        textAlign: "center"
    })
}*/
