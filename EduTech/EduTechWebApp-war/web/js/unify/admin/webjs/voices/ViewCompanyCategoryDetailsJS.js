var companyName, companyID, companyCategoryID;

$(document).ready(function () {
    App.init();
    Plugins.init();
    FormComponents.init();
    
    $('#associatedCompanyList tbody').on('click', 'tr', function() {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        
        companyName = $.trim(rowData[1]);
        companyID = companyName.split(';')[1];
        companyCategoryID = companyName.split(';')[2];
        window.location.href = 'VoicesAdmin?pageTransit=goToViewCompanyListDetailsInModal&companyID=' + companyID + '&companyCategoryID=' + companyCategoryID;
    });
});

/* FOR PROFILE PICTURE UPLOAD TO SYSTEM */
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('output-image');
        output.src = reader.result;
        document.getElementById('imageUploadStatus').value = "Uploaded";
    };
    reader.readAsDataURL(event.target.files[0]);
};