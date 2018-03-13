$(document).ready(function () {
    var dbCompanyIndustry = $('#dbCompanyIndustry').val();
    var splitResult = dbCompanyIndustry.split(';');
    splitResult.forEach(function (industryEntry) {
        $('#companyIndustry').append($('<option>', {value: industryEntry, text: industryEntry}));
    });
});

/* FOR PROFILE PICTURE UPLOAD TO SYSTEM */
