var jobTransDate, jobID, jobTransID;

$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
   
    $('#jobReviewTable').DataTable({
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
   
    $('#jobReviewTable tbody').on('click', 'tr', function(event) {
         var rowData = $(this).children("td").map(function() {
             return $(this).text();
         }).get();
         jobTransDate = $.trim(rowData[4]);
         jobID = jobTransDate.split(';')[1];
         jobTransID = jobTransDate.split(';')[2];
         window.location.href= 'ProfileSysUser?pageTransit=goToErrandsTransDetailsSYS&jobID=' + jobID + '&jobTransID=' + jobTransID;
         
     });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });

});


