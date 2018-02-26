var jobName, jobID, jobCategoryID;

$(document).ready(function () {
    App.init();
    Plugins.init();
    FormComponents.init();
    
    $('#associatedJobList tbody').on('click', 'tr', function() {
        var rowData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        
        jobName = $.trim(rowData[1]);
        jobID = jobName.split(';')[1];
        jobCategoryID = jobName.split(';')[2];
        window.location.href = 'ErrandsAdmin?pageTransit=goToViewJobDetailsInModal&jobID=' + jobID + '&jobCategoryID=' + jobCategoryID;
    });
});

function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('output-image');
        output.src = reader.result;
        document.getElementById('imageUploadStatus').value = "Uploaded";
        //alert('testing');
    };
    reader.readAsDataURL(event.target.files[0]);
}

function AlertIt(jCategoryID) {
            var answer = confirm ("Are you sure to deactivate the category? ")
            if (answer)
                window.open('ErrandsAdmin?pageTransit=deactivateAJobCategory&hiddenJobCategoryID=' + jCategoryID,'_parent');
}