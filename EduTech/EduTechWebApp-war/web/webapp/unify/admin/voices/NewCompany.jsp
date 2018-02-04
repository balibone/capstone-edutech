<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - New Company</title>

        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/marketplace/NewItemCategoryCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body style="background-color: transparent;">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div id="formContent">
                <form action="VoicesAdmin" method="POST" enctype="multipart/form-data" target="_parent">
                   <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                        <div class="image-upload">
                            <img id="output-image" />
                        </div>
                        <label for="file-upload" class="btn btn-outline btn-primary btn-sm btn-block" style="margin-top: 10px;">
                            <i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;Upload Image
                        </label>
                        <input id="file-upload" name="companyImage" type="file" accept="image/*" onchange="javascript: previewImage(event)" /> 
                    </div> 
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                        <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" style="margin-top: 7px;">
                            <input type="text" class="form-control has-feedback-left" placeholder="Company Name" name="companyName" />
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                        <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" style="margin-top: 7px;">
                            <input type="text" class="form-control has-feedback-left" placeholder="Company Website" name="companyWebsite" />
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                        <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" style="margin-top: 7px;">
                            <input type="text" class="form-control has-feedback-left" placeholder="Company Headquarter" name="companyHQ" />
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                        <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" style="margin-top: 7px;">
                            <input type="text" class="form-control has-feedback-left" placeholder="Company Size" name="companySize" />
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                        <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" style="margin-top: 7px;">
                            <input type="text" class="form-control has-feedback-left" placeholder="Company Industry" name="companyIndustry" />
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                        <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" style="margin-top: 7px;">
                            <textarea rows="5" class="form-control has-feedback-left" placeholder="Company Description" name="companyDescription"></textarea>
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                    </div>
            </div>
            <div class="ln_solid"></div>
            <div class="form-group" style="text-align: center;">
                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                    <input type="hidden" name="pageTransit" value="addCompany"/>
                    <button type="submit" class="btn btn-primary">Add Company</button>
                </div></form>
            </div>
        </div>

        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/webjs/voices/NewCompanyJS.js" type="text/javascript"></script>
    </body>
</html>
