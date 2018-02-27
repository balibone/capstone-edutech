<!-- ***************************************************************************************
*   Title:                  NewCompany.jsp
*   Purpose:                CREATION OF NEW COMPANY (UNIFY ADMIN)
*   Created By:             ZHU XINYI
*   Modified By:            TAN CHIN WEE WINSTON
*   Date:                   21 FEBRUARY 2018
*   Code version:           1.1
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
**************************************************************************************** -->

<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - New Company</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminPlugins.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/responsive.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/icons.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/easy-autocomplete/easy-autocomplete.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/voices/NewCompanyCSS.css" rel="stylesheet" type="text/css" />

        <!-- JAVASCRIPT -->
        <script type="text/javascript" src="js/unify/admin/basejs/jquery-v1.10.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/bootstrap-v3.1.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/lodash.compat-v2.0.0.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/breakpoints-v1.0.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll-v1.3.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll.horizontal-v0.6.5.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.sparkline-v2.1.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminAppJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminPluginJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminPluginFormComponentsJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminBaseJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/easy-autocomplete/jquery.easy-autocomplete.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/easy-autocomplete/jquery.easy-autocomplete.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/validator-v1.1.0.js"></script>
        <script type="text/javascript" src="js/unify/admin/webjs/voices/NewCompanyJS.js"></script> 
    </head>
    <body style="background-color: #FFFFFF;">
        <form action="VoicesAdmin" method="POST" enctype="multipart/form-data" target="_parent">
            <table border="0">
                <tr>
                    <td rowspan="4" style="width: 25%;">
                        <div class="form-group">
                            <div class="image-upload">
                                <img id="output-image" />
                            </div>
                            <label for="file-upload" class="btn btn-default btn-sm btn-block" style="margin-top: 10px; width: 151px;">
                                <i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;Upload Image
                            </label>
                            <input id="file-upload" name="companyImage" type="file" accept="image/*" required="required" onchange="javascript: previewImage(event)" />
                        </div>
                    </td>
                    <td colspan="2">
                        <div class="form-group">
                            <div class="input-group">
                                <input type="hidden" id="dbCompanyIndustry" value="<%= request.getAttribute("companyIndustryStr")%>" />
                                <select class="form-control" id="companyIndustry" name="companyIndustry">
                                    <option value="" disabled selected>-- Select Company Industry --</option>
                                </select>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-building"></i></span>
                                <input type="text" class="form-control" placeholder="Company Name (Required)" required="required" name="companyName" />
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="number" min="0" class="form-control" placeholder="Company Size (Required)" required="required" name="companySize" />
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-globe"></i></span>
                                <input type="text" class="form-control" placeholder="Company Website" name="companyWebsite" />
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-flag"></i></span>
                                <input type="text" class="form-control" placeholder="Company HQ (Required)" required="required" id="companyHQ" name="companyHQ" />
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                <textarea rows="5" cols="30" class="form-control" placeholder="Company Description (Required)" required="required" name="companyDescription"></textarea>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-address-card"></i></span>
                                <textarea rows="5" cols="30" class="form-control" placeholder="Company Address (Required)" required="required" name="companyAddress"></textarea>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <div class="form-group">
                            <div class="col-md-12 col-sm-12 col-xs-12" style="text-align: center;">
                                <input type="hidden" name="pageTransit" value="createCompany"/>
                                <button type="submit" class="btn btn-primary">Create Company</button>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>