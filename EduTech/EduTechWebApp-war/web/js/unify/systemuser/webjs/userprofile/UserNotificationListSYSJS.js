$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });
    
    $('.mailinbox tbody input:checkbox').click(function () {
        $(this).parents('tr').toggleClass('selected', $(this).prop('checked'));
    });
    
    $('.mailinbox tbody').on('click', 'tr', function() {
        var msgType, msgContent, msgContentID;
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        
        msgType = $.trim(rowData[2]);
        msgContent = $.trim(rowData[3]);
        msgContentID = msgContent.split(';')[1];
        
        if(msgType.indexOf('Marketplace (Item Offer)') >= 0) {
            window.open('ProfileSysUser?pageTransit=goToPendingItemOfferDetailsSYS&urlitemID=' + msgContentID, '_self');
        } else if (msgType.indexOf('Marketplace (My Item Offer)') >= 0) {
            window.open('ProfileSysUser?pageTransit=goToMyBuyerOfferListSYS', '_self');
        } else if (msgType.indexOf('Marketplace (Item Like)') >= 0) {
            window.open('MarketplaceSysUser?pageTransit=goToMsgViewItemDetailsSYS&itemHidID=' + msgContentID, '_self');
        } else if (msgType.indexOf('Errands') >= 0) {
            window.open('ProfileSysUser?pageTransit=', '_self');
        } else if (msgType.indexOf('Voices') >= 0) {
            window.open('ProfileSysUser?pageTransit=', '_self');
        }
    });
    
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
});