$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');

    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });
    
    $('.qtipItemReportTrigger').qtip({
        content: { title: { text: 'Report Item Listing', button: true }, text: $('#reportItemListingTip') },
        position: { at: 'top center', my: 'bottom center' },
        style: { width: 250, height: 195 },
        hide: { event: 'click' },
        show: 'click'
    });
    
    $('.myAccountBtn').click(function () {
        var tempBtnID = this.id;
        if (tempBtnID.indexOf('likeItemBtn') >= 0) {
            tempBtnID = tempBtnID.replace('likeItemBtn', '');
            $.ajax({
                type: "POST",
                url: "MarketplaceSysUser",
                data: {
                    itemIDHid: tempBtnID,
                    pageTransit: 'likeItemListingDetails'
                },
                success: function (returnString) {
                    $('#itemNumOfLikes' + tempBtnID).text("");
                    $('#itemNumOfLikes' + tempBtnID).text(returnString);
                    if ($('#likeItemBtn' + tempBtnID).hasClass('likeStatus')) {
                        $('#likeItemBtn' + tempBtnID).removeClass('likeStatus');
                        $('#likeItemBtn' + tempBtnID).addClass('noLikeStatus');
                    } else if ($('#likeItemBtn' + tempBtnID).hasClass('noLikeStatus')) {
                        $('#likeItemBtn' + tempBtnID).removeClass('noLikeStatus');
                        $('#likeItemBtn' + tempBtnID).addClass('likeStatus');
                    }
                }
            });
        } else if (tempBtnID.indexOf('pendingItemOfferBtn') >= 0) {
            tempBtnID = tempBtnID.replace('pendingItemOfferBtn', '');
            window.open('ProfileSysUser?pageTransit=goToPendingItemOfferListSYS&urlitemID=' + tempBtnID, '_self');
        } else if(tempBtnID.indexOf('reportItemListingBtn') >= 0) {
            tempBtnID = tempBtnID.replace('reportItemListingBtn', '');
            $('#qtipItemReportTrigger' + tempBtnID).trigger("click");
            $('#itemHiddenID').val(tempBtnID);
        } else if (tempBtnID.indexOf('itemLikersBtn') >= 0) {
            tempBtnID = tempBtnID.replace('itemLikersBtn', '');
            $('iframe').attr('src', 'MarketplaceSysUser?pageTransit=goToItemLikeList&itemID=' + tempBtnID);
            $('#itemLikeList-iframe').iziModal('open', event);
        }
    });
    
    $('#sendItemReportBtn').click(function() {
        $.ajax({
            type: 'POST',
            url: 'MarketplaceSysUser',
            data: { 
                itemHiddenID: $('#itemHiddenID').val(),
                itemReportCategory: $('#itemReportCategory').val(),
                itemReportDescription: $('#itemReportDescription').val(),
                pageTransit: 'reportItemListing'
            },
            success: function(returnString) {
                $('#successReportResponse').hide();
                $('#failedReportResponse').hide();
                if(returnString.endsWith("!")) { $('#successReportResponse').text(returnString).show(); }
                else if(returnString.endsWith(".")) { $('#failedReportResponse').text(returnString).show(); }
            }
        });
    });

    $("#itemLikeList-iframe").iziModal({
        title: 'Your Item Likers',
        subtitle: 'List of users who like your item',
        iconClass: 'fa fa-heart-o',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#4D7496',
        width: 650,
        overlayClose: true,
        iframe: true,
        iframeHeight: 450
    });
    
    $('#makeOfferBtn').qtip({
        content: { title: { text: 'Report Item Listing', button: true }, text: $('#reportItemListingTip') },
        position: { at: 'top center', my: 'bottom center' },
        style: { width: 250, height: 195 },
        hide: { event: 'click', inactive: 10000 },
        show: 'click'
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});