var rowID;
$(document).ready(function() {
    //on click of each eye, run function with event
    $('table tbody').on('click','#view', function(event) {
        rowID = $(this).closest('tr').children('#id').text();
        console.log("Row ID is :"+rowID);
        $('iframe').attr('src', 'EduTechAdmin?pageTransit=ViewSemester&id=' + rowID);
        $('#viewSemester-iframe').iziModal('open', event);
    });

    $('#viewSemester-iframe').iziModal({
        title: 'View Semester',
        transitionIn: 'comingIn',
        transitionOut: 'comingOut',
        headerColor: '#337AB7',
        width: 900,
        overlayClose: true,
        iframe : true,
        iframeHeight: 700
    });
    
    //on click of each bin, run function with event
    $('table tbody').on('click','#delete', function(event) {
        rowID = $(this).closest('tr').children('#id').text();
        console.log("Row ID is :"+rowID);
        var startDelete = confirm('Delete Semester?');
        console.log(startDelete);
        if(startDelete === true){
            $.ajax({
                url: "EduTechAdmin?pageTransit=checkSemester&id="+rowID,
                type: 'GET',
                success: function(response, status, xhr){
                    var confirmDelete;
                    var hasModules = xhr.getResponseHeader("hasModules");
                    if(hasModules === "true"){//there are modules assigned to this semester.
                        confirmDelete = confirm("This semester has modules assigned to it. Really delete?");
                        if(confirmDelete === true){
                            $.ajax({//user really wants to delete, so delete.
                                url: href="EduTechAdmin?pageTransit=deleteSemester&id="+rowID,
                                type: 'GET'
                            });
                            alert('Semester deleted.');
                            top.location.reload();
                        }
                    }else{//no mods assigned to it, delete as per normal. 
                        $.ajax({
                            url: href="EduTechAdmin?pageTransit=deleteSemester&id="+rowID,
                            type: 'GET'
                        });
                        alert('Semester deleted.');
                        top.location.reload();
                    }
                },
                error: function(jqXHR,status,error){
                    alert(status+": "+error);
                }
            });
        }
    });
});