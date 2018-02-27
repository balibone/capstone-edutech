var rowID;
$(document).ready(function() {
    $('table tbody').on('click', '#view', function(event) {
        rowID=$(this).closest('tr').children('#id').text();
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