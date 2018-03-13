$(function(){
    $("#tokenEntry").on("submit", function(event){
        event.preventDefault();
        var formData1 = $(this).serialize();
        console.log(formData1);//for debugging
        $.ajax({
            url: "CommonInfra",
            type: 'POST',
            data: formData1,
            success: function(data){
                $('#newPassword').modal({
                    keyboard: false
                });
            },
            error: function(jqXHR,status,error){
                alert(status+": "+error);
            }
        });
    });
    $("#resetPassword").on("submit", function(event){
        event.preventDefault();
        var pass1 = $("#password1").val();
        var pass2 = $("#password2").val();
        if(pass1 === pass2){
            var formData2 = $(this).serialize();
            console.log(formData2);//for debugging
            $.ajax({
                url: "CommonInfra",
                type: 'POST',
                data: formData2,
                success: function(data){
                    $('#newPassword').modal("hide");
                    alert('Password successfully changed');
                    window.location.href("CommonInfra?pageTransit=goToLogout");
                },
                error: function(jqXHR,status,error){
                    alert(status+": "+error);
                }
            });
        }else{
            alert("Passwords do not match!");
        }
    });
});