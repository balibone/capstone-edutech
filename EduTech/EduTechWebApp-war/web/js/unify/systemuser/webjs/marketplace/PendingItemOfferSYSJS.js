$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });
    
    $('.qtipAcceptOfferButton').qtip({
        content: { title: { text: 'Accept Item Offer', button: true }, text: $('#acceptOfferTooltip') },
        position: { at: 'top center', my: 'bottom center' },
        style: { width: 225, height: 125 },
        hide: { event: 'click' },
        show: 'click'
    });
    
    $('.qtipNegotiateOfferButton').qtip({
        content: { title: { text: 'Negotiate Item Offer', button: true }, text: $('#negotiateOfferTooltip') },
        position: { at: 'top center', my: 'bottom center' },
        style: { width: 225, height: 125 },
        hide: { event: 'click' },
        show: 'click'
    });
    
    $('.itemOfferBtn').click(function() {
        var btnItemOfferID = this.id;
        if(btnItemOfferID.indexOf('acceptOfferPanel') >= 0) {
            btnItemOfferID = btnItemOfferID.replace('acceptOfferPanel', '');
            $('#itemOfferHiddenID').val(btnItemOfferID);
        } else if(btnItemOfferID.indexOf('acceptOfferBtn') >= 0) {
            var acceptOfferConfirm = confirm("Are you sure to accept this item offer?");
            if (acceptOfferConfirm) {
                window.open('ProfileSysUser?pageTransit=acceptAnItemOfferSYS&urlItemOfferID=' + $('#itemOfferHiddenID').val() 
                        + '&itemIDHidden=' + $('#hiddenItemID').val() + '&sellerAcceptComments=' + $('#sellerAcceptComments').val(), '_self');
            }
        } else if(btnItemOfferID.indexOf('negotiateOfferPanel') >= 0) {
            btnItemOfferID = btnItemOfferID.replace('negotiateOfferPanel', '');
            $('#itemOfferHiddID').val(btnItemOfferID);
        } else if(btnItemOfferID.indexOf('negotiateOfferBtn') >= 0) {
            var acceptOfferConfirm = confirm("Are you sure to negotiate this item offer?");
            if (acceptOfferConfirm) {
                window.open('ProfileSysUser?pageTransit=negotiateAnItemOfferSYS&urlItemOfferID=' + $('#itemOfferHiddID').val() 
                        + '&itemIDHidden=' + $('#hiddenItemID').val() + '&sellerNegotiateComments=' + $('#sellerNegotiateComments').val(), '_self');
            }
        } else if (btnItemOfferID.indexOf('rejectOfferBtn') >= 0) {
            var rejectOfferConfirm = confirm("Are you sure to reject this item offer?");
            if (rejectOfferConfirm) { 
                btnItemOfferID = btnItemOfferID.replace('rejectOfferBtn', '');
                window.open('ProfileSysUser?pageTransit=rejectAnItemOfferSYS&urlItemOfferID=' + btnItemOfferID + '&itemIDHidden=' + $('#hiddenItemID').val(), '_self');
            }
        }
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});