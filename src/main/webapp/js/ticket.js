$(document).ready(function () {
    setStyleInputForNumbers();
    getElement()
    hideForms();
    deleteForm();
    setSum();
})

var inp1, inp2, inp3, inp4, result;

function getElement() {
    inp1 = document.getElementById('inpNo1');
    inp2 = document.getElementById('inpNo2');
    inp3 = document.getElementById('inpNo3');
    inp4 = document.getElementById('inpNo4');
    result = document.getElementById('inputSumPLN');
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
        result.value = parseFloat(inp1.value) + parseFloat(inp2.value) + parseFloat(inp3.value) + parseFloat(inp4.value);
    }
}

function updateSum() {
    result.value = parseFloat(inp1.value) + parseFloat(inp2.value) + parseFloat(inp3.value) + parseFloat(inp4.value);
}
