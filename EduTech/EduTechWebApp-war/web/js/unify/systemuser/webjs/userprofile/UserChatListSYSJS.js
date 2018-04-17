var windowIP = window.location.hostname;
var webSocket = new WebSocket('ws://windowIP:8080/EduTechWebApp-war/WebSocketGateway/' + $('#hiddenChatID').val());
webSocket.onerror = function (event) {
    console.log(event.data);
};
webSocket.onopen = function (event) {
    console.log("Connection Established");
    console.log(event.data);
};

$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    
    $('#buyingContacts').show();
    $('#sellingContacts').hide();
    
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
    
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
});

function toggleBuying() {
    $('#buyingContacts').show();
    $('#sellingContacts').hide();
}
function toggleSelling() {
    $('#buyingContacts').hide();
    $('#sellingContacts').show();
}