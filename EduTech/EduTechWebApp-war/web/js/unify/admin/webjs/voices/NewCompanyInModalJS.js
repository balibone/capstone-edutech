$(document).ready(function () {
    App.init();
    Plugins.init();
    FormComponents.init();
    
    var dbCompanyIndustry = $('#dbCompanyIndustry').val();
    var splitResult = dbCompanyIndustry.split(';');
    splitResult.forEach(function (industryEntry) {
        $('#companyIndustry').append($('<option>', {value: industryEntry, text: industryEntry}));
    });

    var options = {
        url: "resources/countries.json",
        getValue: "name",
        list: {
            match: {enabled: true}
        },
        theme: "square"
    };
    $("#companyHQ").easyAutocomplete(options);
});

/* FOR PROFILE PICTURE UPLOAD TO SYSTEM */
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var output = document.getElementById('output-image');
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
}