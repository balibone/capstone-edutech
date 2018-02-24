$(document).ready(function () {
    App.init();
    Plugins.init();
    FormComponents.init();

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