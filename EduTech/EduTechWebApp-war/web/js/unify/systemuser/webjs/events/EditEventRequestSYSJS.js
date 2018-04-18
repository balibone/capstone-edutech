$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
});


function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var output = document.getElementById('output-image');
        output.src = reader.result;
        document.getElementById('imageUploadedCheck').value = "Uploaded";
    };
    reader.readAsDataURL(event.target.files[0]);
}