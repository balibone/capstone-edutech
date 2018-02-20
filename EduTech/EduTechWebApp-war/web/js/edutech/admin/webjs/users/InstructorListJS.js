var rowID;
$(document).ready(function() {
    //on click of table row, run function with event
    $('#datatable-responsive tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        rowID = $.trim(rowData[2]);
        console.log("Row ID is :"+rowID);
        $('iframe').attr('src', 'EduTechAdmin?pageTransit=ViewInstructor&id=' + rowID);
        $('#viewInstructor-iframe').iziModal('open', event);
    });
    
    $('#viewInstructor-iframe').iziModal({
        title: 'View Instructor',
        iconClass: 'fas fa-book',
        transitionIn: 'comingIn',
        transitionOut: 'comingOut',
        headerColor: '#337AB7',
        width: 600,
        overlayClose: true,
        iframe : true,
        iframeHeight: 400
    });
});