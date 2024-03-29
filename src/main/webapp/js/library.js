$(document).ready(function () {
    getBooks();
    $('#extendBtn').hide();
    //checkPossibilityExtension();
    showBtn();
})

var inaccessible = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-dash-circle-fill" style="color: red" viewBox="0 0 16 16">\n' +
    '  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM4.5 7.5a.5.5 0 0 0 0 1h7a.5.5 0 0 0 0-1h-7z"/>\n' +
    '</svg>'
var available = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-square" style="color: darkgreen" viewBox="0 0 16 16">\n' +
    '  <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>\n' +
    '  <path d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.235.235 0 0 1 .02-.022z"/>\n' +
    '</svg>'

function getBooks(){
    $.ajax({
        url: "http://localhost:8080/getBooks",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            var myTable = $('#booksTable').DataTable({
                data: data,
                paging: true,
                searching: false,
                destroy: true,
                autoWidth: true,
                columns: [{
                    orderable: false,
                    targets: 0,
                    data: null,
                    defaultContent: "",
                    className: 'select-checkbox'
                },{
                    data: null,
                    width: "5%"
                }, {
                    data: "title"
                }, {
                    data: "year"
                }, {
                    data: "epoch"
                }, {
                    data: "status",
                    render: function (data) {
                        if(data==true)
                            return available;
                        else
                            return inaccessible;
                    }
                }, {
                    data: "rental_time"
                }, {
                    data: "email"
                }],
                columnDefs: [{ targets: 7, visible: $('#borrowBtn').is(":visible") === false }],
                select: {
                    style:    'os',
                    selector: 'td:first-child'
                },
                order: [[ 1, 'asc' ]]
            })
            orderRows(myTable);
        }
    });
}

function orderRows(data) {
    data.on( 'order.dt search.dt', function () {
        data.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
}

function borrow() {
    var table = $('#booksTable').DataTable();
    var array = [];
    var data = table.rows({selected:  true}).data();
    array.push(data[0].title)
    array.push(data[0].status)
    if(!array[1]){
        alert("Ta książka jest wypożyczona, wybierz inną")
    }
    else {
        borrowBook(array[0])
    }

}

function borrowBook(title) {
    $.ajax({
        url: "http://localhost:8080/borrowBook",
        method: "POST",
        scriptCharset: "utf-8",
        data: {
            "title": title
        },
        success: function () {
            getBooks()
            alert("Wypożyczyłeś książkę pt. " + title)
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    })
}

function checkPossibilityExtension() {
    var table = $('#booksTable').DataTable();
    var array = [];
    var data = table.rows({selected:  true}).data();
    array.push(data[0].title)
    array.push(data[0].status)
    checkBook(array[0]);
}

function checkBook(data) {
    $.ajax({
        url: "http://localhost:8080/chceckBook",
        method: "POST",
        scriptCharset: "utf-8",
        data: {
            "title": data
        },
        success: function () {
            alert('Wydłużyłeś date')
        },
        error: function () {
            alert('Nie możesz przedłużyć terminu')
        }
    })
}

function showBtn() {
    $('#booksTable').on("click", function () {
        var elementExist = $('.select-info').length==0
        //i jeśli status = false
        if(elementExist){
            $("#extendBtn").show();
        }
        else if(!elementExist){
            $("#extendBtn").hide();
        }
    })
}
