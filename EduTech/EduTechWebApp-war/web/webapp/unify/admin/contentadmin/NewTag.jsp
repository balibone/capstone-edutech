<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - New Tag</title>

        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css">

    </head>
    
    <%-- start of form --%>
    <body style="background-color: white;">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div id="formContent">
                <form action="ContentAdmin?pageTransit=goToTagListing" method="GET" enctype="multipart/form-data" target="_parent">

                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">

                        <%-- form field 1 --%>
                        <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" style="margin-top: 7px;">
                            <input type="text" class="form-control has-feedback-left" required="required" placeholder="Tag Name" name="tagName" />
                            <span class="fa fa-info form-control-feedback left" aria-hidden="true"></span>
                        </div>

                        <%-- dropdown form/field 2 --%>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            
                                <%-- field 1 --%>
                                <label for="tagTypeTitle">Type of Tag</label>
                                
                                &nbsp;&nbsp;
                                
                                <%-- field 2 --%>
                                <select id="tagType" name="tagType" style="height: 25px;" required="required">
                                    <option></option>
                                    <option value="Errand">Errand</option>
                                    <option value="Marketplace">Marketplace</option>
                                    <option value="Voice">Voice</option>
                                </select>
                            
                        </div>

                    </div>
            </div>


            <%-- submit form --%>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
                <div class="ln_solid"></div>
                <div class="form-group" style="text-align: center;">
                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                        <input type="hidden" name="pageTransit" value="createTag"/>
                        <button type="submit" class="btn btn-primary">Create Tag</button>
                    </div></form>
                </div></form>

            </div>

            <!-- JAVASCRIPT (JS) -->
            <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
            <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
            <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>
            <!-- validator -->
            <script src=".js/unify/admin/basejs/validator-v1.1.0.js"></script>
    </body>
</html>
