$(document).ready(function () {
    setStyleInputForNumbers();
    //changeTextButton();
    //resetInput();
    hiddenBtn();
    hiddenForm();
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

function resetInput() {
    if($('#btnSmall1').click(function () {
        $('#inpNo2').val("");
    }));
    if($('#btnSmall2').click(function () {
        $('#inpNo3').val("");
    }));
    if($('#btnSmall3').click(function () {
        $('#inpNo4').val("");
    }));
}

function hiddenBtn() {
    if($('#btnSmall1').click(function () {
        $('#btnSmall1').hide();
        $('#inpNo2').val("");
    }));
    if($('#btnSmall2').click(function () {
        $('#btnSmall2').hide();
        $('#btnSmall2del').show();
        $('#inpNo3').val("");
    }));
    if($('#btnSmall3').click(function () {
        $('#btnSmall3').hide();
        $('#btnSmall3del').show();
        $('#btnSmall4del').show();
        $('#inpNo4').val("");
    }));
}

function hiddenForm() {
    if($('#btnSmall2del').click(function () {
        $('#form2').hide();
        $('#inpNo2').val("");
    }));
    if($('#btnSmall3del').click(function () {
        $('#form3').hide();
        $('#inpNo3').val("");
    }));
    if($('#btnSmall4del').click(function () {
        $('#form4').hide();
        $('#inpNo4').val("");
    }));
}