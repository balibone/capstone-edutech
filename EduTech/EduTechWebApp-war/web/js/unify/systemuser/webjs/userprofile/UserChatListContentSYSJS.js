var windowIP = window.location.hostname;
var windowURL = window.location.href;
var windowURLCast = new URL(windowURL);
var hidChatType = windowURLCast.searchParams.get("hidChatType");
var hidBuyerCRED = windowURLCast.searchParams.get("hidBuyerCRED");

var webSocket = new WebSocket('ws://' + windowIP + ':8080/EduTechWebApp-war/WebSocketGateway/' + $('#hiddenItemID').val() + '&' + hidBuyerCRED);
webSocket.onerror = function (event) {
    console.log(event.data);
};
webSocket.onopen = function (event) {
    console.log("Connection Established");
    console.log(event.data);
};
webSocket.onmessage = function (event) {
    var dbResponse, dbResponseHTML;
    var socketMSG = event.data;
    
    var itemID = (socketMSG.split('|')[1]).split('&')[0];
    var itemBuyerID = (socketMSG.split('|')[1]).split('&')[1];
    
    $.ajax({
        type: "POST",
        url: "ProfileSysUser",
        data: { 
            itemIDSocket: itemID,
            itemBuyerIDSocket: itemBuyerID,
            pageTransit: 'getChatAvailability'
        },
        success: function(returnString) { 
            dbResponse = returnString;
        }
    });
    
    if(socketMSG != null && ($('#messageSenderID').val() != $('#loggedInUserID').val())) {
        $('<li class="replies"><img src="uploads/commoninfrastructure/admin/images/' + $('#receiverIMG').val() + '" /><p>' + socketMSG.split('|')[0] + '</p></li>').appendTo($('.messages ul'));
        
        if(dbResponse.split('|')[0] == 1 && hidChatType == 'Selling') {
            dbResponseHTML = '<li id="contact' + dbResponse.split('|')[0] + '%' + itemBuyerID + '" class="contact"><div class="wrap"><img src="uploads/commoninfrastructure/admin/images/' + dbResponse.split('|')[2] + '" /><div class="meta"></div><p class="name" style="font-weight:bolder;">' + itemBuyerID + '<span style="display:none">;' + dbResponse.split('|')[0] + '</span></p><p class="name" style="font-weight:bolder;">' + dbResponse.split('|')[1] + '</p><p class="preview" style="font-weight:bolder;">' + socketMSG.split('|')[0] + '</p></div></li>';
            $('#sellingContactsUL').prepend(dbResponseHTML);
        }
    }
    $(".messages").animate({scrollTop: $('#frame').prop('scrollHeight')}, "fast");
    
    var messageContent = event.data;
    if (messageContent.indexOf('Offered') >= 0 && hidChatType == 'Selling') {
        $('#buyerItemOfferStatus').html("<span class='badge badge-info custom-badge'>Pending</span>");
        $('#noOfferPanel').hide();
        $('#acceptRejectOfferPanelInterior').show();
    } else if (messageContent.indexOf('Updated Offer') >= 0 && hidChatType == 'Buying') {
        $('#buyerItemOfferStatus').html("<span class='badge badge-warning custom-badge'>Processing</span>");
        messageContent = messageContent.replace('Updated Offer $', '');
        $('#tempItemOfferPrice').val(messageContent);
    } else if (messageContent.indexOf('Updated Offer') >= 0 && hidChatType == 'Selling') {
        $('#buyerItemOfferStatus').html("<span class='badge badge-info custom-badge'>Pending</span>");
        $('#updateOfferPanel').hide();
        $('#updateOfferPanelInterior').hide();
        $('#acceptRejectOfferPanelInterior').show();
    } else if (messageContent.indexOf('Accepted Offer') >= 0 && hidChatType == 'Buying') {
        $('#buyerItemOfferStatus').html("<span class='badge badge-theme custom-badge'>Accepted</span>");
        $('#updateOfferPanel').hide();
        $('#updateOfferPanelInterior').hide();
    } else if (messageContent.indexOf('Accepted Offer') >= 0 && hidChatType == 'Selling') {
        $('#buyerItemOfferStatus').html("<span class='badge badge-theme custom-badge'>Accepted</span>");
        $('#acceptRejectOfferPanel').hide();
        $('#acceptRejectOfferPanelInterior').hide();
        $('#acceptedOfferPanelInterior').show();
    } else if (messageContent.indexOf('Rejected Offer') >= 0 && hidChatType == 'Buying') {
        $('#buyerItemOfferStatus').html("<span class='badge badge-info custom-badge'>Pending</span>");
        $('#updateOfferPanel').hide();
        $('#updateOfferPanelInterior').hide();
    } else if (messageContent.indexOf('Rejected Offer') >= 0 && hidChatType == 'Selling') {
        $('#buyerItemOfferStatus').html("<span class='badge badge-warning custom-badge'>Processing</span>");
        $('#acceptRejectOfferPanel').hide();
        $('#acceptRejectOfferPanelInterior').hide();
    } else if (messageContent.indexOf('Completed Offer of') >= 0) {
        $('#buyerItemOfferStatus').html("<span class='badge badge-success custom-badge'>Completed</span>");
        $('#acceptedOfferPanel').hide();
        $('#acceptedOfferPanelInterior').hide();
        $('#provideFeedbackPanelInterior').show();
    } else if (messageContent.indexOf('Failed Offer of') >= 0) {
        $('#buyerItemOfferStatus').html("<span class='badge badge-danger custom-badge'>Failed</span>");
        $('#acceptedOfferPanel').hide();
        $('#acceptedOfferPanelInterior').hide();
        $('#provideFeedbackPanelInterior').show();
    }
    $('#messageSenderID').val("");
};

$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    
    if(hidChatType == 'Buying') {
        $('#buyingContacts').show();
        $('#buyingPanelBtn').addClass('active');
        $('#sellingContacts').hide();
    } else if(hidChatType == 'Selling') {
        $('#sellingContacts').show();
        $('#sellingPanelBtn').addClass('active');
        $('#buyingContacts').hide();
    }
    
    $(".messages").animate({scrollTop: $('#frame').prop('scrollHeight')}, "fast");
    $('ul').find('#contact' + $('#contentChatID').val() + '\\%' + hidBuyerCRED + '.contact').addClass('active');
    
    $('ul.buyingContacts li').click(function () {
        var tempBuyingChatID = $(this).attr('id');
        tempBuyingChatID = tempBuyingChatID.replace('contact', '');
        var buyingChatID = tempBuyingChatID.split('%')[0];
        var buyerCRED = tempBuyingChatID.split('%')[1];
        window.open('ProfileSysUser?pageTransit=goToViewChatListContentSYS&hidChatID=' + buyingChatID + '&hidChatType=Buying&hidBuyerCRED=' + buyerCRED, '_self');
    });

    $('ul.sellingContacts li').click(function () {
        var tempSellingChatID = $(this).attr('id');
        tempSellingChatID = tempSellingChatID.replace('contact', '');
        var sellingChatID = tempSellingChatID.split('%')[0];
        var buyerCRED = tempSellingChatID.split('%')[1];
        window.open('ProfileSysUser?pageTransit=goToViewChatListContentSYS&hidChatID=' + sellingChatID + '&hidChatType=Selling&hidBuyerCRED=' + buyerCRED, '_self');
    });
    
    $('.chatListContentBtn').click(function () {
        var tempChatListContentBtn = this.id;
        if (tempChatListContentBtn.indexOf('sendOfferBtn') >= 0) {
            var itemOfferPrice = $('#itemOfferPrice').val();
            if (itemOfferPrice == "" || itemOfferPrice == null) {
                bootbox.dialog({
                    title: '<h5>Error Occured</h5>',
                    message: "<p>Offer price cannot be empty. Please fill in a value.</p>",
                    closeButton: false,
                    buttons: {
                        cancel: {
                            label: "Ok",
                            className: 'btn-primary'
                        }
                    }
                });
            } else if(isNaN(itemOfferPrice)) {
                bootbox.dialog({
                    title: '<h5>Error Occured</h5>',
                    message: "<p>Offer price must be a numeric value.</p>",
                    closeButton: false,
                    buttons: {
                        cancel: {
                            label: "Ok",
                            className: 'btn-primary'
                        }
                    }
                });
            } else if(itemOfferPrice < 1 || itemOfferPrice > 9999) {
                bootbox.dialog({
                    title: '<h5>Error Occured</h5>',
                    message: "<p>Offer price must be within $1 and $9999.</p>",
                    closeButton: false,
                    buttons: {
                        cancel: {
                            label: "Ok",
                            className: 'btn-primary'
                        }
                    }
                });
            } else {
                bootbox.dialog({
                    title: '<h5>Confirmation Required</h5>',
                    message: "<p>Confirm send this item offer?</p>",
                    closeButton: false,
                    buttons: {
                        "Confirm Update": {
                            label: "Confirm Send Offer",
                            className: 'btn-success',
                            callback: function () {
                                $.ajax({
                                    type: "POST",
                                    url: "MarketplaceSysUser",
                                    data: { 
                                        itemIDHidden: $('#hiddenItemID').val(),
                                        itemOfferPrice: $('#itemOfferPrice').val(),
                                        itemOfferDescription: '',
                                        pageTransit: 'sendItemOfferPrice'
                                    },
                                    success: function(returnString) { 
                                        console.log(returnString);
                                        sendMessageOfferStatus('SendOffer');
                                        $('#buyerItemOfferStatus').html("<span class='badge badge-warning custom-badge'>Processing</span>");
                                        $('#sendOfferPanel').hide();
                                        $('#updateOfferPanelInterior').show();
                                        
                                        $('#tempItemOfferPrice').val($('#itemOfferPrice').val());
                                        $('#updatedOfferPriceInterior').attr('placeholder', 'Current Offer: $' + $('#itemOfferPrice').val());
                                        $('#itemOfferPrice').val('');
                                    }
                                });
                            }
                        },
                        cancel: {
                            label: "Cancel",
                            className: 'btn-primary'
                        }
                    }
                });
            }
        } else if (tempChatListContentBtn.indexOf('updateOfferBtn') >= 0) {
            var updatedOfferPrice = $('#updatedOfferPrice').val();
            if(updatedOfferPrice == "" || updatedOfferPrice == null) {
                updatedOfferPrice = $('#updatedOfferPriceInterior').val();
            }
            
            if (updatedOfferPrice == "" || updatedOfferPrice == null) {
                bootbox.dialog({
                    title: '<h5>Error Occured</h5>',
                    message: "<p>Updated offer price cannot be empty. Please fill in a value.</p>",
                    closeButton: false,
                    buttons: {
                        cancel: {
                            label: "Ok",
                            className: 'btn-primary'
                        }
                    }
                });
            } else if(isNaN(updatedOfferPrice)) {
                bootbox.dialog({
                    title: '<h5>Error Occured</h5>',
                    message: "<p>Updated offer price must be a numeric value.</p>",
                    closeButton: false,
                    buttons: {
                        cancel: {
                            label: "Ok",
                            className: 'btn-primary'
                        }
                    }
                });
            } else if(updatedOfferPrice < 1 || updatedOfferPrice > 9999) {
                bootbox.dialog({
                    title: '<h5>Error Occured</h5>',
                    message: "<p>Updated offer price must be within $1 and $9999.</p>",
                    closeButton: false,
                    buttons: {
                        cancel: {
                            label: "Ok",
                            className: 'btn-primary'
                        }
                    }
                });
            } else {
                bootbox.dialog({
                    title: '<h5>Confirmation Required</h5>',
                    message: "<p>Are you sure that you want to update this item offer?</p>",
                    closeButton: false,
                    buttons: {
                        "Confirm Update": {
                            label: "Confirm Update",
                            className: 'btn-success',
                            callback: function () {
                                $.ajax({
                                    type: 'POST',
                                    url: 'ProfileSysUser',
                                    data: {
                                        itemOfferHiddenID: $('#hiddenItemOfferID').val(),
                                        revisedItemOffer: updatedOfferPrice,
                                        pageTransit: 'editPersonalItemOfferSYS'
                                    },
                                    success: function (returnString) {
                                        console.log(returnString);
                                        $('#tempItemOfferPrice').val(updatedOfferPrice);
                                        sendMessageOfferStatus('UpdateOffer');
                                        
                                        $('#updatedOfferPrice').attr('placeholder', 'Current Offer: $' + updatedOfferPrice);
                                        $('#updatedOfferPrice').val('');
                                    }
                                });
                            }
                        },
                        cancel: {
                            label: "Cancel",
                            className: 'btn-primary'
                        }
                    }
                });
            }
        } else if (tempChatListContentBtn.indexOf('acceptOfferBtn') >= 0) {
            bootbox.dialog({
                title: '<h5>Confirmation Required</h5>',
                message: "<p>Are you sure that you want to accept this item offer?</p>",
                closeButton: false,
                buttons: {
                    "Confirm Accept": {
                        label: "Confirm Accept",
                        className: 'btn-success',
                        callback: function () {
                            $.ajax({
                                type: 'POST',
                                url: 'ProfileSysUser',
                                data: {
                                    urlItemOfferID: $('#hiddenItemOfferID').val(),
                                    itemIDHidden: $('#hiddenItemID').val(),
                                    sellerAcceptComments: '',
                                    pageTransit: 'acceptAnItemOfferSYS'
                                },
                                success: function (returnString) { 
                                    console.log(returnString);
                                    $('#tempItemOfferPrice').val(updatedOfferPrice);
                                    sendMessageOfferStatus('AcceptOffer');
                                }
                            });
                        }
                    },
                    cancel: {
                        label: "Cancel",
                        className: 'btn-primary'
                    }
                }
            });
        } else if (tempChatListContentBtn.indexOf('negotiateOfferBtn') >= 0) {
            $.ajax({
                type: 'POST',
                url: 'ProfileSysUser',
                data: {
                    urlItemOfferID: $('#hiddenItemOfferID').val(),
                    itemIDHidden: $('#hiddenItemID').val(),
                    sellerNegotiateComments: '',
                    pageTransit: 'negotiateAnItemOfferSYS'
                },
                success: function (returnString) { 
                    console.log(returnString);
                    sendMessageOfferStatus('RejectOffer');
                }
            });
        } else if (tempChatListContentBtn.indexOf('markedOfferAsCompletedBtn') >= 0) {
            bootbox.dialog({
                title: '<h5>Confirmation Required</h5>',
                message: "<p>This item offer shall be marked as completed.<br/>Do you want to mark your item listing as sold or still available?</p>",
                closeButton: false,
                buttons: {
                    "Sold": {
                        label: "Mark Listing As Sold",
                        className: 'btn-danger',
                        callback: function () {
                            $.ajax({
                                type: 'POST',
                                url: 'ProfileSysUser',
                                data: {
                                    urlItemOfferID: $('#hiddenItemOfferID').val(),
                                    itemIDHidden: $('#hiddenItemID').val(),
                                    itemStatus: 'Sold',
                                    pageTransit: 'completeAnItemOfferSYS'
                                },
                                success: function (returnString) { 
                                    console.log(returnString);
                                    sendMessageOfferStatus('CompleteOffer');
                                    $('#acceptedOfferPanel').hide();
                                    $('#provideFeedbackPanel').show();
                                }
                            });
                        }
                    },
                    "Available": {
                        label: "Mark Listing As Available",
                        className: 'btn-success',
                        callback: function () {
                            $.ajax({
                                type: 'POST',
                                url: 'ProfileSysUser',
                                data: {
                                    urlItemOfferID: $('#hiddenItemOfferID').val(),
                                    itemIDHidden: $('#hiddenItemID').val(),
                                    itemStatus: 'Available',
                                    pageTransit: 'completeAnItemOfferSYS'
                                },
                                success: function (returnString) { 
                                    console.log(returnString);
                                    sendMessageOfferStatus('CompleteOffer');
                                    $('#acceptedOfferPanel').hide();
                                    $('#provideFeedbackPanel').show();
                                }
                            });
                        }
                    },
                    cancel: {
                        label: "Cancel",
                        className: 'btn-primary'
                    }
                }
            });
        } else if (tempChatListContentBtn.indexOf('reopenListingBtn') >= 0) {
            bootbox.dialog({
                title: '<h5>Confirmation Required</h5>',
                message: "<p>This item offer shall be marked as 'Failed', and item listing will be marked as 'Available'.ontinue?</p>",
                closeButton: false,
                buttons: {
                    "Confirm Re-Open": {
                        label: "Confirm Re-Open",
                        className: 'btn-success',
                        callback: function () {
                            $.ajax({
                                type: 'POST',
                                url: 'ProfileSysUser',
                                data: {
                                    urlItemOfferID: $('#hiddenItemOfferID').val(),
                                    itemIDHidden: $('#hiddenItemID').val(),
                                    itemStatus: 'Available',
                                    pageTransit: 'reopenAnItemOfferSYS'
                                },
                                success: function (returnString) { 
                                    console.log(returnString);
                                    sendMessageOfferStatus('FailOffer');
                                    $('#acceptedOfferPanel').hide();
                                    $('#provideFeedbackPanel').show();
                                }
                            });
                        }
                    },
                    cancel: {
                        label: "Cancel",
                        className: 'btn-primary'
                    }
                }
            });
        } else if (tempChatListContentBtn.indexOf('provideFeedbackBtn') >= 0) {
            
        }
    });

    $('.submit').click(function () { sendMessage(); });
    $(window).on('keydown', function (e) {
        if (e.which == 13) {
            sendMessage();
            return false;
        }
    });
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
});

function toggleBuying() {
    $('#buyingContacts').show();
    $('#buyingPanelBtn').addClass('active');
    $('#sellingContacts').hide();
    $('#sellingPanelBtn').removeClass('active');
}
function toggleSelling() {
    $('#buyingContacts').hide();
    $('#buyingPanelBtn').removeClass('active');
    $('#sellingContacts').show();
    $('#sellingPanelBtn').addClass('active');
}

function sendMessage() {
    var message = $('.message-input input').val();
    if ($.trim(message) == '') { return false; }
    $('<li class="sent"><img src="uploads/commoninfrastructure/admin/images/' + $('#senderIMG').val() + '" /><p>' + message + '</p></li>').appendTo($('.messages ul'));
    $('.message-input input').val(null);
    $('.contact.active .preview').html('<span>You: </span>' + message);
    $(".messages").animate({scrollTop: $(document).height()}, "fast");
    
    $.ajax({
        type: 'POST',
        url: 'ProfileSysUser',
        data: {
            receiverID: $('#receiverID').text(),
            chatContent: message,
            buyerOrSellerStat: $('#buyerOrSellerStat').val(),
            buyerOrSellerID: $('#buyerOrSellerID').val(),
            hiddenItemID: $('#hiddenItemID').val(),
            pageTransit: 'addNewChatContent'
        },
        success: function (returnString) { console.log(returnString); }
    });
    webSocket.send(message);
    $('#messageSenderID').val($('#loggedInUserID').val());
    
    if(hidChatType == 'Buying') {
        $('#contact' + $('#contentChatID').val() + '\\%' + hidBuyerCRED).prependTo('#buyingContactsUL');
    } else if(hidChatType == 'Selling') {
        $('#contact' + $('#contentChatID').val() + '\\%' + hidBuyerCRED).prependTo('#sellingContactsUL');
    }
}

function sendMessageOfferStatus(messageType) {
    var message;
    if (messageType == 'SendOffer') {
        message = 'Offered $' + $('#itemOfferPrice').val();
    } else if (messageType == 'UpdateOffer') {
        message = 'Updated Offer $' + $('#tempItemOfferPrice').val();
    } else if (messageType == 'AcceptOffer') {
        message = 'Accepted Offer $' + $('#tempItemOfferPrice').val();
    } else if (messageType == 'RejectOffer') {
        message = 'Rejected Offer $' + $('#tempItemOfferPrice').val();
    } else if (messageType == 'CompleteOffer') {
        message = 'Completed Offer of $' + $('#tempItemOfferPrice').val();
    } else if (messageType == 'FailOffer') {
        message = 'Failed Offer of $' + $('#tempItemOfferPrice').val();
    }
    
    if ($.trim(message) == '') { return false; }
    $('<li class="sent"><img src="uploads/commoninfrastructure/admin/images/' + $('#senderIMG').val() + '" /><p>' + message + '</p></li>').appendTo($('.messages ul'));
    $('.contact.active .preview').html('<span>You: </span>' + message);
    $(".messages").animate({scrollTop: $(document).height()}, "fast");
    
    $.ajax({
        type: 'POST',
        url: 'ProfileSysUser',
        data: {
            receiverID: $('#receiverID').text(),
            chatContent: message,
            buyerOrSellerStat: $('#buyerOrSellerStat').val(),
            buyerOrSellerID: $('#buyerOrSellerID').val(),
            hiddenItemID: $('#hiddenItemID').val(),
            pageTransit: 'addNewChatContent'
        },
        success: function (returnString) { console.log(returnString); }
    });
    webSocket.send(message);
    $('#messageSenderID').val($('#loggedInUserID').val());
}