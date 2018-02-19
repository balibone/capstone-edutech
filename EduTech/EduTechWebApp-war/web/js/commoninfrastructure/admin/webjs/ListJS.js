var rowID;
$(document).ready(function() {
    //on click of table row, run function with event
    $('#datatable-responsive tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        rowID = $.trim(rowData[2]);
        $('iframe').attr('src', 'EduTechAdmin?pageTransit=ViewStudent&id=' + rowID);
        $('#viewStudent-iframe').iziModal('open', event);
    });
    
    $('#viewStudent-iframe').iziModal({
        title: 'View Student',
        iconClass: 'fa fa-cubes',
        transitionIn: 'comingIn',
        transitionOut: 'comingOut',
        headerColor: '#337AB7',
        width: 900,
        overlayClose: true,
        iframe : true,
        iframeHeight: 500
    });
});