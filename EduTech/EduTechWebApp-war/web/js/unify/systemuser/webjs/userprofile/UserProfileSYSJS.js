$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });
    
    $('.settingsBtn').click(function () {
        var tempSettingsBtnID = this.id;
        tempSettingsBtnID = tempSettingsBtnID.replace('settingsBtn', '');
        $('#qtipTrigger' + tempSettingsBtnID).qtip({
            content: { title: { text: 'Report Item Listing', button: true }, text: $('#reportItemListingTip') },
            position: { at: 'top center', my: 'bottom center' },
            style: { width: 250, height: 195 },
            hide: { event: 'click' },
            show: 'click'
        });
    });
    
    $('.userProfileBtn').click(function () {
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
                    if(returnString > 1) { $('#likeWording' + tempBtnID).text("Likes"); }
                    else { $('#likeWording' + tempBtnID).text("Like"); }
                }
            });
        } else if(tempBtnID.indexOf('reportItemListingBtn') >= 0) {
            tempBtnID = tempBtnID.replace('reportItemListingBtn', '');
            $('#qtipTrigger' + tempBtnID).trigger("click");
            $('#itemHiddenID').val(tempBtnID);
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
});