var rowItemName;
$(document).ready(function() {
    $('#newCompany').on('click', function() {
        $('iframe').attr('src', 'VoicesAdmin?pageTransit=goToNewCompany');
        $('#newCompany-iframe').iziModal('open', event);
    });
    
    $('#newCompany-iframe').iziModal({
        title: 'New Company',
        subtitle: 'Fill in the information of the new category here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 525,
        overlayClose: true,
        iframe : true,
        iframeHeight: 325
    });
    
    
    $('#datatable-responsive tbody').on('click', 'tr', function(event) {
        var $cell= $(event.target).closest('td');
        if($cell.index() > 0) {
            var rowData = $(this).children("td").map(function() {
                return $(this).text();
            }).get();
            rowCompanyName = $.trim(rowData[1]);
            $('iframe').attr('src', 'VoicesAdmin?pageTransit=goToViewCompanyListDetails&companyName=' + rowCompanyName /*+ '&itemSellerID=' + rowSellerID*/);
            $('#modal-iframe').iziModal('open', event);
        }
    });
    
    $("#modal-iframe").iziModal({
        title: 'Company Details',
        subtitle: 'Administrator may deactivate this company here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 760,
        overlayClose: true,
        iframe : true,
        iframeHeight: 525
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});