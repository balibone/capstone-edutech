var rowID;
$(document).ready(function() {
    //on click of each eye, run function with event
    $('table tbody').on('click','#view', function(event) {
        rowID = $(this).closest('tr').children('#id').text();
        console.log("Row ID is :"+rowID);
        $('iframe').attr('src', 'EduTechAdmin?pageTransit=ViewModule&id=' + rowID);
        $('#viewModule-iframe').iziModal('open', event);
    });

    $('#viewModule-iframe').iziModal({
        title: 'View Module',
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
        var startDelete = confirm('Delete Module?');
        console.log(startDelete);
        if(startDelete === true){
            $.ajax({
                url: "EduTechAdmin?pageTransit=checkModule&id="+rowID,
                type: 'GET',
                success: function(response, status, xhr){
                    var confirmDelete;
                    var hasUsers = xhr.getResponseHeader("hasUsers");
                    if(hasUsers === "true"){//there are things assigned to this module
                        confirmDelete = confirm("This module has users assigned to it. Really delete?");
                        if(confirmDelete === true){
                            $.ajax({//user really wants to delete, so delete.
                                url: href="EduTechAdmin?pageTransit=deleteModule&id="+rowID,
                                type: 'GET'
                            });
                            alert('Module deleted.');
                            top.location.reload();
                        }
                    }else{//nothing assigned to this module, delete as per normal
                        $.ajax({
                            url: href="EduTechAdmin?pageTransit=deleteModule&id="+rowID,
                            type: 'GET'
                        });
                        alert('Module deleted.');
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