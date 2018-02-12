var rowCategoryName;
var rowCategoryName2;

$(document).ready(function () {

    // create a new tag
    $('#newTag').on('click', function () {
        //alert("TEST");
        $('iframe').attr('src', 'ContentAdmin?pageTransit=goToNewTag');
        $('#newTag-iframe').iziModal('open', event);
    });

    // new tag iframe
    $('#newTag-iframe').iziModal({
        title: 'New Tag',
        subtitle: 'Fill in the information of the new tag here',
        iconClass: 'fa fa-tags',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 525,
        overlayClose: true,
        iframe: true,
        iframeHeight: 325
    });

    // edit tag iframe
    $('#editTag-iframe').iziModal({
        title: 'Edit Tag',
        subtitle: 'Update information of new tag here',
        iconClass: 'fa fa-tags',
        transitionIn: 'transitionIn',
        transitionOut: 'transitionOut',
        headerColor: '#337AB7',
        width: 525,
        overlayClose: true,
        iframe: true,
        iframeHeight: 325
    });
    
    // show button on tr mouse hover
    //var trIndex = null;
    //$("#datatable-responsive tr td").mouseenter(function () {
    //    trIndex = $(this).parent();
    //    
    //    
    //    $(trIndex).find("td:last-child").html('<a href="">Edit /</a>&nbsp;&nbsp;<a href="ContentAdmin?pageTransit=goToDeleteTag&tagID="' + rowCategoryName + '>Delete</a>');
    //});
    
    
    //when button is clicked (for edit & delete)
    $('button').on('click', function() {
       
       //alert("TEST");
       
       var tagID = this.id;

       if (tagID.includes("Edit")){
           //alert("EDIT");
            //call edit tag function
            var tagEditID = tagID.replace("tagItemEdit", "");
            $('iframe').attr('src', 'ContentAdmin?pageTransit=goToEditTag&tagID='+tagEditID+'');
            $('#editTag-iframe').iziModal('open', event);
       }
       else if(tagID.includes("Delete")){
           //alert("DELETE");
           //retrieve tagID to delete
           //alert("Confirm Delete?");
           if(window.confirm("Confirm deletion of tag?")){
           var tagDeleteID = tagID.replace("tagItemDelete", "");
           //alert(tagDeleteID);
           //call delete tag function (redirect to deleteTag)
           $(location).attr('href','ContentAdmin?pageTransit=goToDeleteTag&tagID=' + tagDeleteID + '');
           }
        
        
        }
       
           
       
    });

    // remove button on tr mouse leave
    $("#datatable-responsive tr td").mouseleave(function () {
        $(trIndex).find('td:last-child').html("&nbsp;");
    });

    // close success
    $('#closeSuccess').click(function () {
        $('#successPanel').fadeOut(300);
    });
    
    // close error
    $('#closeError').click(function () {
        $('#errorPanel').fadeOut(300);
    });
});
