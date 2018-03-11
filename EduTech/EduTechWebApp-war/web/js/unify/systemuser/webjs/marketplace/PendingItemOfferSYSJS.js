$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });
    
    $('.itemOfferBtn').click(function() {
        var btnItemOfferID = this.id;
        if(btnItemOfferID.indexOf('acceptOfferBtn') >= 0) {
            var acceptOfferConfirm = confirm("Are you sure to accept this item offer?");
            if (acceptOfferConfirm) { 
                btnItemOfferID = btnItemOfferID.replace('acceptOfferBtn', '');
                window.open('ProfileSysUser?pageTransit=acceptAnItemOffer&urlItemOfferID=' + btnItemOfferID + '&itemIDHidden=' + $('#hiddenItemID').val(), '_self');
            }
        } else if (btnItemOfferID.indexOf('rejectOfferBtn') >= 0) {
            var rejectOfferConfirm = confirm("Are you sure to reject this item offer?");
            if (rejectOfferConfirm) { 
                btnItemOfferID = btnItemOfferID.replace('rejectOfferBtn', '');
                window.open('ProfileSysUser?pageTransit=rejectAnItemOffer&hiddenItemOfferID=' + btnItemOfferID + '&hiddenItemID=' + $('#hiddenItemID').val(), '_self');
            }
        }
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});