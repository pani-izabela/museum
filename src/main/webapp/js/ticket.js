$(document).ready(function () {
    setStyleInputForNumbers();
    getElement();
    getDate();
    hideForms();
    deleteForm();
    setSum();
})

var inp1, inp2, inp3, inp4, result;
var normalPrice, studentPrice, childPrice, pensionerPrice;

function getElement() {
    inp1 = document.getElementById('inpNo1');
    inp2 = document.getElementById('inpNo2');
    inp3 = document.getElementById('inpNo3');
    inp4 = document.getElementById('inpNo4');
    result = document.getElementById('inputSumPLN');
}

function getDate() {
    normalPrice = document.getElementById('normalTicket1').getAttribute('normalPrice');
    studentPrice = document.getElementById('studentTicket1').getAttribute('studentPrice');
    childPrice = document.getElementById('childTicket1').getAttribute('childPrice');
    pensionerPrice = document.getElementById('pensionerTicket1').getAttribute('pensionerPrice');
}

function setStyleInputForNumbers() {
    $("input[type='number']").inputSpinner();
}

function hideForms() {
    $('#form2').hide();
    $('#form3').hide();
    $('#form4').hide();
}

function addForm() {
    var form2 = document.getElementById('form2');
    var isVisibleForm2 = form2.style.display.includes('none')
    var form3 = document.getElementById('form3');
    var isVisibleForm3 = form3.style.display.includes('none')
    if(isVisibleForm2){
        $('#form2').show();
    }
    else {
        if(isVisibleForm3){
            $('#form3').show();
        }
        else {
            $('#form4').show();
        }
    }
}

function deleteForm() {
    $('.deleteBtn').on('click', function(){
        var form_id = $(this).parent().parent().attr('id')
        var input_id = $(this).parent().prev().children().attr('id')
        $('#'+input_id).val('0')
        $('#'+form_id).hide()
        updateSum()
    })
}

function setSum() {
    inp1.onchange = showText;
    inp2.onchange = showText;
    inp3.onchange = showText;
    inp4.onchange = showText;
    function showText() {
        updateSum()
    }
}

function updateSum() {
    result.value = (parseFloat(inp1.value) * calculatePriceForm('ticketType1'))
        + (parseFloat(inp2.value) * calculatePriceForm('ticketType2'))
        + (parseFloat(inp3.value) * calculatePriceForm('ticketType3'))
        + (parseFloat(inp4.value) * calculatePriceForm('ticketType4'));
}

function calculatePriceForm(ticketType) {
    if($('#' + ticketType + ' option:selected').val()==1){
        return normalPrice;
    }
    else if($('#' + ticketType + ' option:selected').val()==2){
        return (normalPrice - (normalPrice * studentPrice))
    }
    else if($('#' + ticketType + ' option:selected').val()==3){
        return (normalPrice - (normalPrice * childPrice))
    }
    else if($('#' + ticketType + ' option:selected').val()==4){
        return (normalPrice - (normalPrice * pensionerPrice))
    }
}
