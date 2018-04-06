var webSocket = new WebSocket('ws://localhost:8080/EduTechWebApp-war/WebSocketGateway');
webSocket.onerror = function (event) {
    console.log(event.data);
};
webSocket.onopen = function (event) {
    console.log("Connection Established");
    console.log(event.data);
};
webSocket.onmessage = function (event) {
    onMessage(event);
};

$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#buyingContacts').show();
    $('#sellingContacts').hide();
    $(".messages").animate({scrollTop: $(document).height()}, "fast");
    $('ul').find('#contact' + $('#contentChatID').val() + '.contact').addClass('active');

    $('ul.buyingContacts li').click(function () {
        var buyingChatID = $(this).attr('id');
        buyingChatID = buyingChatID.replace('contact', '');
        window.open('ProfileSysUser?pageTransit=goToViewChatListContentSYS&hidChatID=' + buyingChatID, '_self');
    });

    $('ul.sellingContacts li').click(function () {
        var sellingChatID = $(this).attr('id');
        sellingChatID = sellingChatID.replace('contact', '');
        window.open('ProfileSysUser?pageTransit=goToViewChatListContentSYS&hidChatID=' + sellingChatID, '_self');
    });

    $('.submit').click(function () {
        sendMessage();
    });
    $(window).on('keydown', function (e) {
        if (e.which == 13) {
            sendMessage();
            return false;
        }
    });
    
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
});

function onMessage(event) {
    $('<li class="replies"><img src="uploads/commoninfrastructure/admin/images/' + $('#receiverIMG').val() + '" /><p>' + event.data + '</p></li>').appendTo($('.messages ul'));
}
function sendMessage() {
    var message = $(".message-input input").val();
    if ($.trim(message) == '') {
        return false;
    }
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
        success: function (returnString) {
            console.log(returnString);
        }
    });
    webSocket.send(message);
}

function toggleBuying() {
    $('#buyingContacts').show();
    $('#sellingContacts').hide();
}
function toggleSelling() {
    $('#buyingContacts').hide();
    $('#sellingContacts').show();
}