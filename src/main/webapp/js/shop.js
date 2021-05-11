$(document).ready(function () {
    setText();
    showMess();
})

function setText() {
    var inp1 = document.getElementById('i1');
    var inp2 = document.getElementById('i2');
    var result = document.getElementById('inputId');
    inp1.value = 0.0;
    inp2.value = 0.0;
    inp1.oninput = showText;
    inp2.oninput = showText;
    function showText() {
        var sum = parseFloat(inp1.value) + parseFloat(inp2.value);
        result.value = sum;
    }
}

function showMess() {
    var ddd = document.getElementById('rose');
    alert(ddd.getAttribute('label'));
    //alert(ddd.textContent);
}