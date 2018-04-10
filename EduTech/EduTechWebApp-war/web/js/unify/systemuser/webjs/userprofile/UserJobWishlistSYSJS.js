$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    
    /*jQuery.fn.jplist.settings = {
        priceSlider: function ($slider) {
            $slider.slider({
                min: 0, max: 1000, range: true, values: [0, 1000]
                , slide: function (event, ui) {
                    $('#min-price').val(ui.values[0]);
                    $('#max-price').val(ui.values[1]);
                }
            });
        },
        priceValues: function ($slider) {
            $('#min-price').val($slider.slider('values', 0));
            $('#max-price').val($slider.slider('values', 1));
        }
    };*/
    
    var dbJobCategory = $('#dbJobCategory').val();
    var splitResult = dbJobCategory.split(',');
    $("#jobCategoryDropdown ul").append('<li><span data-path="default">All Job Categories</span></li>');
    splitResult.forEach(function (jobCategoryEntry) {
        var category = jobCategoryEntry;
        category = category.replace("[", "");
        category = category.replace("]", "");
        
        jobCategoryEntry = jobCategoryEntry.replace("[", "");
        jobCategoryEntry = jobCategoryEntry.replace("]", "");
        jobCategoryEntry.replace(/ /g, '');
        
        jobCategoryEntry = jobCategoryEntry.replace(new RegExp(' ', 'g'), '');
        $("#jobCategoryDropdown ul").append('<li><span data-path=".' + jobCategoryEntry.replace(" ", "") + '">' + category + '</span></li>');
    });
    
    $('#contentArea').jplist({
        itemsBox: '.list', itemPath: '.list-item', panelPath: '.jplist-search'
    });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});


$(document).on('click', '.likeIcon', function() {
        var str = $(this).attr('id');
        var jobID = str.replace("likeIcon", "");
        var likeCount = "likeCount" + jobID;
        
        $.ajax({
            type: "POST",
            url: "ErrandsSysUser",
            data: { 
                jobIDHid: jobID,
                usernameHid: $('#usernameHidden').val(),
                pageTransit: 'likeJobListingDetails'
            },
            success: function(returnString) {
                $("#" + likeCount).text("");
                $("#" + likeCount).text(returnString);
                if($("#likeIcon" + jobID).hasClass('like')) {
                    $("#likeIcon" + jobID).removeClass('like');
                    $("#likeIcon" + jobID).addClass('noLike');
                } else if($("#likeIcon" + jobID).hasClass('noLike')) {
                    $("#likeIcon" + jobID).removeClass('noLike');
                    $("#likeIcon" + jobID).addClass('like');
                }
                //if(returnString > 1) { $('.likeWording').text("Likes"); }
                //else { $('.likeWording').text("Like"); }
            }
        });
    });



