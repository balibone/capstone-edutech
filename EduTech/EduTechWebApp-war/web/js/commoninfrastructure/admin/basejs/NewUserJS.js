function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('output-image');
        output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
}
//if image has been replaced, set this hidden value to yes.
function imageReplacement(){
    document.getElementById('imageReplacement').value = 'yes';
}