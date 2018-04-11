var jobTransDate, jobID, jobTransID;

$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#jobTransTable').DataTable({
        'dom': 'Bfrtip',
        'responsive': true, 
        'pageLength': 10,
        'buttons': [
            {
                extend: 'collection',
                text: 'Export Options',
                autoClose: true,
                buttons: [
                    'copy', 'csv', 'excel', 'pdf', 'print'
                ]
            }
        ]
    });
    
    $('#jobTransTable tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        jobTransDate = $.trim(rowData[0]);
        jobID = jobTransDate.split(';')[1];
        jobTransID = jobTransDate.split(';')[2];
        window.location.href= 'ProfileSysUser?pageTransit=goToErrandsTransDetailsSYS&jobID=' + jobID + '&jobTransID=' + jobTransID;
        //$('#jobDetails-iframe').iziModal('open', event);
    });
    
    $("#jobDetails-iframe").iziModal({
        title: 'Job Listing Details',
        subtitle: 'Detailed information of your job listing',
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

