
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        
        <title>Sign Up</title>
        
        <!-- Cascading Style Sheet (CSS) -->
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/bootstrapv4.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/RegistrationCSS.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/dashboard/baselayout/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/coming-soon.min.css">
    </head>
    
    <body class="text-center">
        <form id="newUser" method="POST" action="CommonInfra" class="form-signin" style="background-color: #f2f2f2; border-radius: 24px;">
            <img class="mb-4" src="images/edubox-logo.png" width="auto" height="100">
            <h1 class="h3 mb-3 font-weight-normal">Register a new account.</h1>
            <div class="form-row">
                <div class="form-group col-sm-2">
                    <label for="salutation">Salutation</label>
                    <select class="form-control" required name="salutation">
                        <option value="Mr.">Mr.</option>
                        <option value="Ms.">Ms.</option>
                        <option value="Mrs.">Mrs.</option>
                        <option value="Mdm.">Mdm.</option>
                        <option value="Dr.">Dr.</option>
                    </select>
                </div>
                <div class="form-group col-sm-5">
                    <label for="firstName">First Name</label>
                    <input type="text" class="form-control" id="firstName" placeholder="First Name" required name="firstName">
                </div>
                <div class="form-group col-sm-5">
                    <label for="lastName">Last Name</label>
                    <input type="text" class="form-control" id="lastName" placeholder="Last Name" required name="lastName">
                </div>
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" placeholder="Username" required name="username">
                <small id="usernameHelpBlock" class="form-text text-muted">
                    Your username must be at least 5 characters long.
                </small>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Password" required name="password">
                <small id="passwordHelpBlock" class="form-text text-muted">
                    Your password must be at least 8 characters long, alphanumeric, and must not contain spaces, special characters, or emojis.
                </small>
            </div>
            <div class="form-row">
                <div class="form-group col-sm-6">
                    <label for="email">E-mail</label>
                    <input type="email" class="form-control" id="username" placeholder="E-mail" required name="email">
                </div>
                <div class="form-group col-sm-6">
                    <label for="contactNum">Contact Number</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text">+65</div>
                        </div>
                        <input type="tel" class="form-control" id="contactNum" placeholder="Contact Number" required name="contactNum">
                    </div>
                </div>
            </div>
            <input type="hidden" name="pageTransit" value="registerUser"/>
            <%  if(request.getAttribute("sysMessage") != null) {   %>
            <div class="alert alert-danger" role="alert"><%= request.getAttribute("sysMessage")%></div>
            <%  }   %>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
            <p class="mt-5 mb-3 text-muted">&copy; EduBox 2018</p>
        </form>
        <!-- JavaScript (JS) -->
        <script src="js/commoninfrastructure/login/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/login/bootstrapv4.min.js" type="text/javascript"></script>
        <!--Vide plugin JS-->
        <script src="js/commoninfrastructure/login/jquery.vide.min.js" type="text/javascript"></script>
        <!--Custom JS-->
        <script src="js/commoninfrastructure/login/coming-soon.min.js" type="text/javascript"></script>
        
<!--        <script>
            $(function(){
                $("#newUser").on("submit", function(event){
                    event.preventDefault();
                    var username = $("#username").val();
                    var password = $("#password").val();
                    var contact = $("#contactNum").val();
                    var goodToSubmit = true;
                    //validate username
                    if(username.length < 8){
                        goodToSubmit = false;
                        
                    }
                    //validate password
                    //validate contactNum
                    if(startTime >= endTime){
                        alert("Invalid time range!");
                    }
                    if(goodToSubmit){
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
        </script>-->
    </body>
</html>
