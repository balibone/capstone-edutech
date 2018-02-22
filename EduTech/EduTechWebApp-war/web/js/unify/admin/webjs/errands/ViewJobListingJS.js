var jobName, jobID;
$(document).ready(function() {
    $('#jobListing tbody').on('click', 'tr', function() {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        jobName = $.trim(rowData[1]);
        jobID = jobName.split(';')[1];
        $('iframe').attr('src', 'ErrandsAdmin?pageTransit=goToViewJobDetails&jobID=' + jobID);
        $('#viewJobDetails-iframe').iziModal('open', event);
    });
    
    $("#viewJobDetails-iframe").iziModal({
        title: 'Job Details',
        subtitle: 'Administrator may delete this job listing here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe : true,
        iframeHeight: 450
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});