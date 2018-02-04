<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Integrated Student System - Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Cascading Style Sheet (CSS) -->
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/IntegratedSPLoginCSS.css">

        <!-- JavaScript (JS) -->
        <script src="js/commoninfrastructure/login/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/login/jquery.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="session-page">
            <div class="container">
                <div class="row header">
                    <div class="col-md-12">
                        <h3 class="logo"><a href="index.html">Welcome to Integrated Student System</a></h3>
                        <p>Sign in to your account here.</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="wrapper clearfix">
                            <div class="formy">
                                <div class="row">
                                    <div class="col-md-12">
                                        <form action="CommonInfra" method="POST">
                                            <div class="form-group">
                                                <label for="username">Email Adddress</label>
                                                <input type="text" class="form-control" name="userEmail" />
                                            </div>
                                            <div class="form-group">
                                                <label for="password">Password</label>
                                                <input type="password" class="form-control" name="userPassword" />
                                            </div>
                                            <div class="checkbox">
                                                <label><input type="checkbox">&nbsp;Remember me</label>
                                            </div>
                                            <div class="submit">
                                                <input type="hidden" name="pageTransit" value="loginToSys"/>
                                                <button type="submit" class="btn btn-default">Sign in to my account</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>            
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>