var itemTransDate, itemID;

$(document).ready(function() {
    $('#itemTransactionList tbody').on('click', 'tr', function(event) {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        itemTransDate = $.trim(rowData[0]);
        itemID = itemTransDate.split(';')[1];
        $('iframe').attr('src', 'MarketplaceAdmin?pageTransit=goToViewItemListingDetails&itemID=' + itemID);
        $('#itemListingDetails-iframe').iziModal('open', event);
    });
    
    $("#itemListingDetails-iframe").iziModal({
        title: 'Item Listing Details',
        subtitle: 'Item listing details of the completed marketplace transaction',
        iconClass: 'fa fa-cubes',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe: true,
        iframeHeight: 450
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});