var companyCategoryName, companyCategoryID;
$(document).ready(function() {
    $('#newCompanyCategory').on('click', function() {
        $('iframe').attr('src', 'VoicesAdmin?pageTransit=goToNewCompanyCategory');
        $('#newCompanyCategory-iframe').iziModal('open', event);
    });
    
    $('#newCompanyCategory-iframe').iziModal({
        title: 'New Company Category',
        subtitle: 'Fill in the information of the new company category here',
        iconClass: 'fa fa-tag',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 600,
        overlayClose: true,
        iframe : true,
        iframeHeight: 325
    });
    
    
    $('#companyCategoryListing tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        companyCategoryName = $.trim(rowData[1]);
        companyCategoryID = companyCategoryName.split(';')[1];
        $('iframe').attr('src', 'VoicesAdmin?pageTransit=goToViewCompanyCategoryDetails&companyCategoryID=' + companyCategoryID);
        $('#editCompanyCategory-iframe').iziModal('open', event);
    });
    
    $("#editCompanyCategory-iframe").iziModal({
        title: 'Edit Company Category Details',
        subtitle: 'Administrator may deactivate this company category here',
        iconClass: 'fa fa-tag',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 725,
        overlayClose: true,
        iframe : true,
        iframeHeight: 425
    });
    
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});

    