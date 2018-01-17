var rowSKU;
$(document).ready(function() {
    $('#compositeItemList tbody').on('click', 'tr', function(event) {
        var $cell= $(event.target).closest('td');
        if($cell.index() > 0) {
            var rowData = $(this).children("td").map(function() {
                return $(this).text();
            }).get();
            rowSKU = $.trim(rowData[2]);
            $('iframe').attr('src', 'SGMapleStore?pageTransit=goToCompositeItemDetails&compositeIdentifier=' + rowSKU);
            $('#modal-iframe').iziModal('open', event);
        }
    });
    
    $("#modal-iframe").iziModal({
        title: 'Composite Item Details',
        subtitle: 'Update the Composite Item Quantity here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 900,
        overlayClose: true,
        iframe : true,
        iframeHeight: 525
    });
});