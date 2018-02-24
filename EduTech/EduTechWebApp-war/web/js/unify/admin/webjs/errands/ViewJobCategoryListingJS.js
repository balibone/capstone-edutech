var jobCategoryName, jobCategoryID;
$(document).ready(function() {
    $('#newJobCategory').on('click', function() {
        $('iframe').attr('src', 'ErrandsAdmin?pageTransit=goToNewJobCategory');
        $('#newJobCategory-iframe').iziModal('open', event);
    });
    
    $('#newJobCategory-iframe').iziModal({
        title: 'New Job Category',
        subtitle: 'Fill in the information of the new job category here',
        iconClass: 'fa fa-tag',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 600,
        overlayClose: true,
        iframe : true,
        iframeHeight: 325
    });
    
    $('#jobCategoryListing tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        jobCategoryName = $.trim(rowData[1]);
        jobCategoryID = jobCategoryName.split(';')[1];
        $('iframe').attr('src', 'ErrandsAdmin?pageTransit=goToViewJobCategoryDetails&jobCategoryID=' + jobCategoryID);
        $('#editJobCategory-iframe').iziModal('open', event);
    });
    
    $('#editJobCategory-iframe').iziModal({
        title: 'Edit Job Category Details',
        subtitle: 'Administrator may deactivate this job category here',
        iconClass: 'fa fa-tag',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe : true,
        iframeHeight: 475
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});