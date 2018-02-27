var itemName, itemID, itemCategoryID;

$(document).ready(function () {
    App.init();
    Plugins.init();
    FormComponents.init();

    $('#associatedItemListing tbody').on('click', 'tr', function () {
        var rowData = $(this).children("td").map(function () {
            return $(this).text();
        }).get();

        itemName = $.trim(rowData[1]);
        itemID = itemName.split(';')[1];
        itemCategoryID = itemName.split(';')[2];
        window.location.href = 'MarketplaceAdmin?pageTransit=goToViewItemListingDetailsInModal&itemID=' + itemID + '&itemCategoryID=' + itemCategoryID;
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});

/* FOR PROFILE PICTURE UPLOAD TO SYSTEM */
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var output = document.getElementById('output-image');
        output.src = reader.result;
        document.getElementById('imageUploadStatus').value = "Uploaded";
    };
    reader.readAsDataURL(event.target.files[0]);
}

function AlertIt(iCategoryID) {
    var answer = confirm("Are you sure to deactivate the category? ")
    if (answer)
        window.open('MarketplaceAdmin?pageTransit=deactivateAnItemCategory&hiddenItemCategoryID=' + iCategoryID, '_self');
}