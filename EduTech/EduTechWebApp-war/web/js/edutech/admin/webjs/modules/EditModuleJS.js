$(function(){
    $("#eventForm").on("submit", function(event){
        event.preventDefault();
        var formData = $(this).serialize();
        console.log(formData);
        $.ajax({
            url: "EduTechAdmin",
            type: 'POST',
            data: formData,
            success: function(data){
                $('#newEventModal').modal('hide');
                alert('Event successfully created');
            },
            error: function(jqXHR,status,error){
                alert(status+": "+error);
            }
        });
    });
});