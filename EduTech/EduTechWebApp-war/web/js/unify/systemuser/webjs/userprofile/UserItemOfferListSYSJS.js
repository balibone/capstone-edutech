$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#userItemOfferTable').DataTable({
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
});