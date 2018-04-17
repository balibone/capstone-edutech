$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    
    $('#itemLikeTable').DataTable({ 
        "responsive": true, 
        "pageLength": 4, 
        "bFilter": false,
        "bLengthChange": false
    });
});