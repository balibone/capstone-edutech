var rowID;
$(document).ready(function() {
    //on click of each eye, run function with event
    $('table tbody').on('click','#view', function(event) {
        rowID = $(this).closest('tr').children('#id').text();
        console.log("Row ID is :"+rowID);
        $('iframe').attr('src', 'EduTechAdmin?pageTransit=ViewSemester&id=' + rowID);
        $('#viewSemester-iframe').iziModal('open', event);
    });

    $('#viewSemester-iframe').iziModal({
        title: 'View Semester',
        transitionIn: 'comingIn',
        transitionOut: 'comingOut',
        headerColor: '#337AB7',
        width: 900,
        overlayClose: true,
        iframe : true,
        iframeHeight: 700
    });
});