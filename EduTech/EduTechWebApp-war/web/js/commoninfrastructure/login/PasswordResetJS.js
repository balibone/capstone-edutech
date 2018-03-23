$(function(){
    $("#resetPassword").on("submit", function(event){
        event.preventDefault();
        var pass1 = $("#password1").val();
        var pass2 = $("#password2").val();
        if(pass1 === pass2){
            if(pass1.length<8){
                alert("Password needs to be at least 8 characters long");
            }else if(pass1 === $("#oldpass").val()){
                alert("New password must be different from old password!");
            }else{
                var formData = $(this).serialize();
                $.ajax({
                    url: "CommonInfra",
                    type: 'POST',
                    data: formData,
                    success: function(data){
                        alert('Password successfully changed');
                        window.location.replace("CommonInfra?pageTransit=goToLogout");
                    },
                    error: function(jqXHR,status,error){
                        alert(status+": "+error);
                    }
                });
            }
        }else{
            alert("Passwords do not match!");
        }
    });
});