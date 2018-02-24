var rowCategoryName;
$(document).ready(function() {
    
    
    $('#reportedErrandsList tbody').on('click', 'tr', function(event) {
        var $cell= $(event.target).closest('td');
        if($cell.index() > 0) {
            var rowData = $(this).children("td").map(function() {
                return $(this).text();
            }).get();
            rowCategoryName = $.trim(rowData[0]);
            $('iframe').attr('src', 'ContentAdmin?pageTransit=goToReportedErrandDetails2&errandView=' + rowCategoryName );
            $('#viewErrand-iframe').iziModal('open', event);
        }
    });
    
    $('#viewErrand-iframe').iziModal({
        title: 'Errand Report',
        subtitle: 'Administrator may update report status here',
        iconClass: 'fa fa-wpforms',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 540,
        overlayClose: true,
        iframe : true,
        iframeHeight: 350
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});
