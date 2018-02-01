var rowItemName;
var rowSellerID;
$(document).ready(function() {
    $('#datatable-responsive tbody').on('click', 'tr', function(event) {
        var $cell= $(event.target).closest('td');
        if($cell.index() > 0) {
            var rowData = $(this).children("td").map(function() {
                return $(this).text();
            }).get();
            rowItemName = $.trim(rowData[1]);
            rowSellerID = $.trim(rowData[3]);
            $('iframe').attr('src', 'MarketplaceAdmin?pageTransit=goToViewItemListingDetails&itemName=' + rowItemName + '&itemSellerID=' + rowSellerID);
            $('#modal-iframe').iziModal('open', event);
        }
    });
    
    $("#modal-iframe").iziModal({
        title: 'Item Details',
        subtitle: 'Administrator may deactivate this item here',
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