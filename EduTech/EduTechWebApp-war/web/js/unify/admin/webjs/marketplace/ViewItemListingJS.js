var itemName, itemID;
var rowSellerID;
$(document).ready(function() {
    $('#itemListing tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        itemName = $.trim(rowData[1]);
        itemID = itemName.split(';')[1];
        $('iframe').attr('src', 'MarketplaceAdmin?pageTransit=goToViewItemListingDetails&itemID=' + itemID);
        $('#modal-iframe').iziModal('open', event);
    });
    
    $("#modal-iframe").iziModal({
        title: 'Item Details',
        subtitle: 'Administrator may deactivate this item here',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe : true,
        iframeHeight: 450
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});