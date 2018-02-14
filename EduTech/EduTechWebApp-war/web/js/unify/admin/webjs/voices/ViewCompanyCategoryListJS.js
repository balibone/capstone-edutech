var rowItemName;
$(document).ready(function() {
    $('#newCompanyCategory').on('click', function() {
        $('iframe').attr('src', 'VoicesAdmin?pageTransit=goToNewCompanyCategory');
        $('#newCompanyCategory-iframe').iziModal('open', event);
    });
    
    $('#newCompanyCategory-iframe').iziModal({
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
            rowCompanyName = $.trim(rowData[2]);
            $('iframe').attr('src', 'VoicesAdmin?pageTransit=goToViewCompanyCategoryListDetails&categoryName=' + rowCompanyName + '&categoryType=company');
            $('#modal-iframe').iziModal('open', event);
        }
    });
    
    $("#modal-iframe").iziModal({
        title: 'Category Details',
        subtitle: 'Administrator may deactivate this company category here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 900,
        overlayClose: true,
        iframe : true,
        iframeHeight: 525
    });
    
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});

    