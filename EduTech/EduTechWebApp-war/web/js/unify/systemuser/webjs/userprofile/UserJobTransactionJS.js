var jobTransDate, jobID, jobTransID;

$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#jobTransTable').DataTable({ "responsive": true, "pageLength": 10, "lengthMenu": [10, 20, 30, 50] });
    
    $('#jobTransTable tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        jobTransDate = $.trim(rowData[0]);
        jobID = jobTransDate.split(';')[1];
        jobTransID = jobTransDate.split(';')[2];
        $('iframe').attr('src', 'ProfileSysUser?pageTransit=goToViewJobDetailsInModalSYS&jobID=' + jobID + '&itemTransID=' + jobTransID);
        $('#jobDetails-iframe').iziModal('open', event);
    });
    
    $("#jobDetails-iframe").iziModal({
        title: 'Job Listing Details',
        subtitle: 'Detailed information of your item listing',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 725,
        overlayClose: true,
        iframe: true,
        iframeHeight: 450
    });
});

