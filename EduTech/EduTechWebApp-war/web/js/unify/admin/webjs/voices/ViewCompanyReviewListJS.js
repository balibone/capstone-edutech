var companyName, companyID;
$(document).ready(function() {
    
    $('#reviewList tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        companyName = $.trim(rowData[2]);
        companyID = companyName.split(';')[1];
        $('iframe').attr('src', 'VoicesAdmin?pageTransit=goToViewCompanyListDetails&companyID=' + companyID);
        $('#editCompanyDetails-iframe').iziModal('open', event);
    });
    
    $("#editCompanyDetails-iframe").iziModal({
        title: 'Company Details',
        subtitle: 'Administrator may edit the company information and deactivate the company here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 750,
        overlayClose: true,
        iframe : true,
        iframeHeight: 475
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});