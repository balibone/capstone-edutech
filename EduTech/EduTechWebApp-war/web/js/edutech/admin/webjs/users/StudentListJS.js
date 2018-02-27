var rowID;
$(document).ready(function() {
    //on click of table row, run function with event
    $('table tbody').on('click', '#view', function(event) {
        rowID=$(this).closest('tr').children('#id').text();
        console.log("Row ID is :"+rowID);
        $('iframe').attr('src', 'EduTechAdmin?pageTransit=ViewStudent&id=' + rowID);
        $('#viewStudent-iframe').iziModal('open', event);
    });
    
    $('#viewStudent-iframe').iziModal({
        title: 'View Student',
        iconClass: 'fas fa-graduation-cap',
        transitionIn: 'comingIn',
        transitionOut: 'comingOut',
        headerColor: '#337AB7',
        width: 600,
        overlayClose: true,
        iframe : true,
        iframeHeight: 400
    });
});