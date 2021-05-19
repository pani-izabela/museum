$(document).ready(function () {
    setStyleInputForNumbers();
    getElement();
    getDate();
    hideForms();
    deleteForm();
    setSum();
})

var inp1, inp2, inp3, inp4, result, sel1, sel2, sel3, sel4;
var normalPrice, studentPrice, childPrice, pensionerPrice;
var normalTicket, studentTicket, childTicket, pensionerTicket;
var ticketTab = [];

function getElement() {
    inp1 = document.getElementById('inpNo1');
    inp2 = document.getElementById('inpNo2');
    inp3 = document.getElementById('inpNo3');
    inp4 = document.getElementById('inpNo4');
    result = document.getElementById('inputSumPLN');
    sel1 = document.getElementById('ticketType1');
    sel2 = document.getElementById('ticketType2');
    sel3 = document.getElementById('ticketType3');
    sel4 = document.getElementById('ticketType4');
}

function getDate() {
    normalPrice = document.getElementById('normalTicket1').getAttribute('normalPrice');
    studentPrice = document.getElementById('studentTicket1').getAttribute('studentPrice');
    childPrice = document.getElementById('childTicket1').getAttribute('childPrice');
    pensionerPrice = document.getElementById('pensionerTicket1').getAttribute('pensionerPrice');
    normalTicket = document.getElementById('normalTicket1').text;
    studentTicket = document.getElementById('studentTicket1').text;
    childTicket = document.getElementById('childTicket1').text;
    pensionerTicket = document.getElementById('pensionerTicket1').text;
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
    var isNotVisibleForm2 = form2.style.display.includes('none')
    var form3 = document.getElementById('form3');
    var isNotVisibleForm3 = form3.style.display.includes('none')
    if(isNotVisibleForm2){
        $('#form2').show();
    }
    else {
        if(isNotVisibleForm3){
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
    sel1.onchange = showText;
    sel2.onchange = showText;
    sel3.onchange = showText;
    sel4.onchange = showText;
    function showText() {
        updateSum()
    }
}

function updateSum() {
    var ticketsNumber = parseFloat(inp1.value) + parseFloat(inp2.value) + parseFloat(inp3.value) + parseFloat(inp4.value);
    var sum = (parseFloat(inp1.value) * calculatePriceForm('ticketType1'))
        + (parseFloat(inp2.value) * calculatePriceForm('ticketType2'))
        + (parseFloat(inp3.value) * calculatePriceForm('ticketType3'))
        + (parseFloat(inp4.value) * calculatePriceForm('ticketType4'));
    if(ticketsNumber >= 10 && ticketsNumber <= 50){
        var discount =  ((Math.floor(ticketsNumber / 10) / 2) / 10) * sum;
        result.value = sum - discount;
    }
    else if(ticketsNumber > 50){
        result.value = sum - (0.25 * sum);
    }
    else {
        result.value = sum;
    }
}

function calculatePriceForm(ticketType) {
    if(checkTicketType(ticketType).includes(normalTicket)){
        return normalPrice;
    }
    else if(checkTicketType(ticketType).includes(studentTicket)){
        return (normalPrice - (normalPrice * studentPrice));
    }
    else if(checkTicketType(ticketType).includes(childTicket)){
        return (normalPrice - (normalPrice * childPrice));
    }
    else if(checkTicketType(ticketType).includes(pensionerTicket)){
        return (normalPrice - (normalPrice * pensionerPrice));
    }
}

function checkTicketType(ticketType) {
    if($('#' + ticketType + ' option:selected').val()==1){
        return normalTicket;
    }
    else if($('#' + ticketType + ' option:selected').val()==2){
        return studentTicket;
    }
    else if($('#' + ticketType + ' option:selected').val()==3){
        return childTicket;
    }
    else if($('#' + ticketType + ' option:selected').val()==4){
        return pensionerTicket;
    }
}

function prepareTicketData() {
    let typeFirstTicket = checkTicketType('ticketType1');
    let priceFirstTicket = calculatePriceForm('ticketType1');
    for(let i=0; i<inp1.value; i++){
        prepareTicketToBuy(typeFirstTicket, priceFirstTicket)
    }
    if(inp2.value != 0 ){
        let typeSecondTicket = checkTicketType('ticketType2');
        let priceSecondTicket = calculatePriceForm('ticketType2');
        for(let i=0; i<inp2.value; i++){
            prepareTicketToBuy(typeSecondTicket, priceSecondTicket)
        }
    }
    if(inp3.value != 0 ){
        let typeThirdTicket = checkTicketType('ticketType3');
        let priceThirdTicket = calculatePriceForm('ticketType3');
        for(let i=0; i<inp3.value; i++){
            prepareTicketToBuy(typeThirdTicket, priceThirdTicket)
        }
    }
    if(inp4.value != 0 ){
        let typeFourthTicket = checkTicketType('ticketType4');
        let priceFourthTicket = calculatePriceForm('ticketType4');
        for(let i=0; i<inp4.value; i++){
            prepareTicketToBuy(typeFourthTicket, priceFourthTicket)
        }
    }
    buyTicket()
}

function prepareTicketToBuy(type, price) {
    ticketTab.push({type:type, price:price})
}

function buyTicket() {
    $.ajax({
        url: "http://localhost:8080/buyTicket",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(ticketTab),
        success: function () {
            console.log('Kupiłeś bilet/y, teraz możesz korzystać z zakładki atrakcje');
            //fundAccount()
            window.location.href = "http://localhost:8080/attractions"
        },
        error: function () {
            console.log('Nie udało się kupić biletu')
        },
    })
}

function fundAccount() {
    $.ajax({
        url: "http://localhost:8080/buyTicket1",
        method: "POST",
        data:{
            "amount": result.value
        },
        success: function () {
            console.log('Konto muzeum zostało zasilone kwotą ' + result.value);
        },
        error: function () {
        },
    })
}

/*
function unlockAttraction(){
    $('#ticketlessView').hide();
    $('#viewWithTicket').show();
}*/
