var rowCategoryName;
$(document).ready(function() {
    
    
    $('#eventRequestList tbody').on('click', 'tr', function(event) {
        var $cell= $(event.target).closest('td');
        if($cell.index() > 0) {
            var rowData = $(this).children("td").map(function() {
                return $(this).text();
            }).get();
            rowCategoryName = $.trim(rowData[0]);
            $('iframe').attr('src', 'ContentAdmin?pageTransit=goToEventRequestDetails&requestView=' + rowCategoryName );
            $('#viewRequest-iframe').iziModal('open', event);
        }
    });
    
    $('#viewRequest-iframe').iziModal({
        title: 'Event Request Details',
        subtitle: 'Administrator may approve or reject event here',
        iconClass: 'fa fa-calendar',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 600,
        overlayClose: true,
        iframe : true,
        height: 500
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});
