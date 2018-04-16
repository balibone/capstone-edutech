$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    //$('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
    
    $('#companyReviewTable tbody').on('click', 'tr', function() {
        var rowData = $(this).children('td').map(function() {
            return $(this).text();
        }).get();
        var company = $.trim(rowData[1]);
        var companyID = company.split(';')[1];
        window.location.href = 'VoicesSysUser?pageTransit=goToViewCompanyDetailsSYS&hiddenCompanyID='+companyID;
    });
    
    $('#companyReviewTable').DataTable({ "responsive": true, "pageLength": 5, "lengthMenu": [5, 10, 20, 30] });
    
});