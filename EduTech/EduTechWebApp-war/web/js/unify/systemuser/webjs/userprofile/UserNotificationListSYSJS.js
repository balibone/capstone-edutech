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
        
        if(msgType.indexOf('Marketplace') >= 0) {
            window.open('ProfileSysUser?pageTransit=goToPendingItemOfferList&urlitemID=' + msgContentID, '_self');
        } else if (msgType.indexOf('Errands') >= 0) {
            window.open('ProfileSysUser?pageTransit=', '_self');
        } else if (msgType.indexOf('Voices') >= 0) {
            window.open('ProfileSysUser?pageTransit=', '_self');
        }
    });
});