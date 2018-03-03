$(function(){
    $("#eventForm").on("submit", function(event){
        event.preventDefault();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        if(startTime >= endTime){
            alert("Invalid time range!");
        }else{
            var formData = $(this).serialize();
            console.log(formData);
            $.ajax({
                url: "EduTechAdmin",
                type: 'POST',
                data: formData,
                success: function(data){
                    $('#newEventModal').modal('hide');
                    alert('Event successfully created');
                    top.location.reload();
                },
                error: function(jqXHR,status,error){
                    alert(status+": "+error);
                }
            });
        }
    });
});