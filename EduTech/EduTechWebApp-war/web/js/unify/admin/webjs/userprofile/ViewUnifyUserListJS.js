var rowUsername;

$(document).ready(function() {
    $('#unifyUserList tbody').on('click', 'tr', function() {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        rowUsername = $.trim(rowData[1]);
        window.location.href = 'ProfileAdmin?pageTransit=goToUnifyUserProfile&username=' + rowUsername;
    });
});