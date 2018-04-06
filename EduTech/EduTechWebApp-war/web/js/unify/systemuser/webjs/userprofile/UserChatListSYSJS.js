$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#buyingContacts').show();
    $('#sellingContacts').hide();
    
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