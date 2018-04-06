$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    //$('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#resumeTable').DataTable({ "responsive": true, "pageLength": 5, "lengthMenu": [5, 10, 20, 30] });
});