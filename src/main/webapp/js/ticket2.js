$(document).ready(function () {
    setStyleInputForNumbers();
    //changeTextButton();
    addNextForm();
    //hiddenBtn();
    deleteForm();
    //setSum();
})

function setStyleInputForNumbers() {
    $("input[type='number']").inputSpinner();
}

function changeTextButton() {
    $('.btn-sm').click(function () {
        $(this).text(function (i,old) {
            return old == "Dodaj kolejny" ?  'Schowaj kolejny' : 'Dodaj kolejny';
        })
    })
}

/*function addNextForm() {
        var form2 = document.getElementById('form2');
        var isVisibleForm2 = form2.getAttribute('class').includes('show');
        var deleteBtn2 = $('#btnSmall2del');
        var form3 = $('#form3');
        var deleteBtn3 = $('#btnSmall3del');
        var form4 = $('#form4');
        var deleteBtn4 = $('#btnSmall4del');
        if(isVisibleForm2==false){
            $('#form2').show();
            $('#btnSmall2del').show();
        }
        else {
            if(form3.getAttribute('class').includes('show')==false){
                form3.show();
                deleteBtn3.show();
            }
            else {
                form4.show();
                deleteBtn4.show();
            }
        }
};*/

function checkForm(form) {
    var formIsVisible = form.getAttribute('class').includes('show');
    return formIsVisible;
}

/*
function hiddenBtn() {
    if($('#btnSmall1').click(function () {
        //sprawdza, czy forms2 jest widoczny jeśli nie to pokazuje go z buttonem usun
            //jest widoczny forms2 wiec sprawdza czy jest widoczny forms3, jeśli nie otwiera go z buttonem usun
                //jest widoczny forms3 wiec otwiera forms 4 z buttonem usun
        $('#btnSmall1').hide();
        $('#inpNo2').val("0");
    }));
    if($('#btnSmall2').click(function () {
        $('#btnSmall2').hide();
        $('#btnSmall2del').show();
        $('#inpNo3').val("0");
    }));
    if($('#btnSmall3').click(function () {
        $('#btnSmall3').hide();
        $('#btnSmall3del').show();
        $('#btnSmall4del').show();
        $('#inpNo4').val("0");
    }));
}
*/

function deleteForm() {
    if($('#btnSmall2del').click(function () {
        $('#form2').hide();
        $('#inpNo2').val("0");
        //$('#inpNo2').setValue(0);
        correctSum();
        returnButton()
    }));
    if($('#btnSmall3del').click(function () {
        $('#form3').hide();
        $('#inpNo3').val("0");
        //$('#inpNo3').setValue(0);
        correctSum();
        returnButton()
    }));
    if($('#btnSmall4del').click(function () {
        $('#form4').hide();
        $('#inpNo4').val("0");
        //$('#inpNo4').setValue(0);
        correctSum();
        returnButton()
    }));
}

/*function setSum() {
    var inp1 = document.getElementById('inpNo1');
    var inp2 = document.getElementById('inpNo2');
    var inp3 = document.getElementById('inpNo3');
    var inp4 = document.getElementById('inpNo4');
    var result = document.getElementById('inputSumPLN');
    inp1.onchange = showText;
    inp2.onchange = showText;
    inp3.onchange = showText;
    inp4.onchange = showText;
    function showText() {
        var sum = parseFloat(inp1.value) + parseFloat(inp2.value) + parseFloat(inp3.value) + parseFloat(inp4.value);
        result.value = sum;
    }
}*/

function correctSum() {
    var inp1 = document.getElementById('inpNo1');
    var inp2 = document.getElementById('inpNo2');
    var inp3 = document.getElementById('inpNo3');
    var inp4 = document.getElementById('inpNo4');
    var result = document.getElementById('inputSumPLN');
    var sum = parseFloat(inp1.value) + parseFloat(inp2.value) + parseFloat(inp3.value) + parseFloat(inp4.value);
    result.value = sum;
}

function returnButton() {
    var form2 = document.getElementById('form2').getAttribute('class');
    var form3 = document.getElementById('form3').getAttribute('class');
    var form4 = document.getElementById('form4').getAttribute('class');
    //var deleteButton = document.getElementById('btnSmall1del');
    if(form2.includes('show')==false && !form3.includes('show')==false && !form4.includes('show')==false){
        $('#btnSmall1').show();
    }
}