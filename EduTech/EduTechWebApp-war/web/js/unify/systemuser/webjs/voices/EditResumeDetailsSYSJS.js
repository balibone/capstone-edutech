var eduExpr_count = 0;
var proExpr_count = 0;
var award_count = 0;
var skill_count = 0;
var workExpr_count = 0;
var reference_count = 0;
$(document).ready(function () {
    $('#unifyPageNAV').load('webapp/unify/systemuser/masterpage/PageNavigation.jsp');
    $('#unifyFooter').load('webapp/unify/systemuser/masterpage/PageFooter.jsp');
    
    $('#eduExprBtn').click(function() {
        eduExpr_count++;
        var row_1 = '<tr class="eduExpr eduExpr-'+eduExpr_count+'">\n\
                        <td valign="middle"><b><center>School Name: </center></b></td>\n\
                        <td valign="middle">\n\
                            <input type="text" class="form-control" name="schoolName[]" style="margin-right: 10px; width: 80%"/></td>\n\
                        <td valign="middle"><b><center>Period: </center></b></td>\n\
                        <td valign="middle">\n\
                            <input type="text" class="form-control" name="schoolPeriod[]" style="margin-right: 10px; width: 50%"/></td>\n\
                        <td></td></tr>';
        var row_2 = '<tr class="eduExpr eduExpr-'+eduExpr_count+'">\n\
                        <td valign="middle"><b><center>Degree: </center></b></td>\n\
                        <td valign="middle">\n\
                            <input type="text" class="form-control" name="schoolDegree[]" style="margin-right: 10px; width: 80%"/></td>\n\
                        <td valign="middle"><b><center>Major: </center></b></td>\n\
                        <td valign="middle">\n\
                            <input type="text" class="form-control" name="schoolMajor[]" style="margin-right: 10px; width: 80%"/></td>\n\
                        <td></td></tr>';
        
        $('#eduExprTable').append(row_1);
        $('#eduExprTable').append(row_2);
    });
    
    $('#eduExprRemoveBtn').click(function() {
        if(eduExpr_count>0) {
            $('.eduExpr-'+eduExpr_count).remove();
            eduExpr_count--;
        }
    });
    
    $('#proExprBtn').click(function() {
        proExpr_count++;
        var row_1 = '<tr class="proExpr proExpr-'+proExpr_count+'">\n\
                            <td valign="middle"><b><center>Project Title: </center></b></td>\n\
                            <td valign="middle">\n\
                                <input type="text" class="form-control" name="projectTitle[]" style="margin-right: 10px; width: 80%"/></td>\n\
                            <td></td></tr>';
        var row_2 = '<tr class="proExpr proExpr-'+proExpr_count+'">\n\
                            <td valign="middle"><b><center>Description: </center></b></td>\n\
                            <td valign="middle">\n\
                                <textarea type="text" class="form-control" name="projectDes[]" rows="2"></textarea></td>\n\
                            <td></td></tr>';
        
        $('#proExprTable').append(row_1);
        $('#proExprTable').append(row_2);
    });
    
    $('#proExprRemoveBtn').click(function() {
        $('.proExpr-'+proExpr_count).remove();
        proExpr_count--;
    });
    
    $('#awardBtn').click(function() {
        award_count++;
        var row_1 = '<tr class="award award-'+award_count+'">\n\
                            <td valign="middle"><b><center>Achievement: </center></b></td>\n\
                            <td valign="middle">\n\
                                <input type="text" class="form-control" name="award[]" style="margin-right: 10px;"/></td>\n\
                            <td></td></tr>';
        $('#awardTable').append(row_1);
    });
    
    $('#awardRemoveBtn').click(function() {
        $(".award-"+award_count).remove();
        award_count--;
    });
    
    $('#skillBtn').click(function() {
        skill_count++;
        var row_1 = '<tr class="skill skill-'+skill_count+'">\n\
                            <td><input type="text" class="form-control" name="proSkillName[]" /></td>\n\
                            <td><select class="form-control" name="proSkillLevel[]" style="width: 100%">\n\
                                <option value="" default>-- Skill Level --</option>\n\
                                <option value="Beginner">Beginner</option>\n\
                                <option value="Intermediate">Intermediate</option>\n\
                                <option value="Advanced">Advanced</option>\n\
                                </select></td>\n\
                            <td><input type="text" class="form-control" name="perSkillName[]" /></td>\n\
                            <td><select class="form-control" name="perSkillLevel[]" style="width: 100%">\n\
                                <option value="" default>-- Skill Level --</option>\n\
                                <option value="Beginner">Beginner</option>\n\
                                <option value="Intermediate">Intermediate</option>\n\
                                <option value="Advanced">Advanced</option>\n\
                                </select></td>\n\
                            <td></td></tr>';
        
        $('#skillTable').append(row_1);
    });
    
    $('#skillRemoveBtn').click(function() {
        $('.skill-'+skill_count).remove();
        skill_count--;
    });
    
    $('#workExprBtn').click(function() {
        workExpr_count++;
        var row_1 = '<tr class="workExpr workExpr-'+workExpr_count+'" >\n\
                            <td valign="middle"><b><center>Title: </center></b></td>\n\
                            <td valign="middle">\n\
                                <input type="text" class="form-control" name="workTitle[]" style="margin-right: 10px; width: 100%" /></td>\n\
                            <td></td>\n\
                            <td></td></tr>';
        var row_2 = '<tr class="workExpr workExpr-'+workExpr_count+'" >\n\
                            <td valign="middle"><b><center>Company: </center></b></td>\n\
                            <td valign="middle">\n\
                                <input type="text" class="form-control" name="workCompany[]" style="margin-right: 10px; width: 80%"/></td>\n\
                            <td valign="middle"><b><center>Period: </center></b></td>\n\
                            <td valign="middle">\n\
                                <input type="text" class="form-control" name="workPeriod[]" style="margin-right: 10px; width: 50%"/></td></tr>';
        var row_3 = '<tr class="workExpr workExpr-'+workExpr_count+'" >\n\
                            <td valign="middle"><b><center>Description: </center></b></td>\n\
                            <td valign="middle">\n\
                                <textarea type="text" class="form-control" name="workDes[]" rows="2" style="width: 180%"></textarea></td>\n\
                            <td></td>\n\
                            <td></td></tr>';
        
        $('#workExprTable').append(row_1);
        $('#workExprTable').append(row_2);
        $('#workExprTable').append(row_3);
    });
    
    $('#workExprRemoveBtn').click(function() {
        $('.workExpr-'+workExpr_count).remove();
        workExpr_count--;
    });
    
    $('#referenceBtn').click(function() {
        reference_count++;
        var row_1 = '<tr class="reference reference-'+reference_count+'">\n\
                            <td valign="middle"><b><center>Referee Name: </center></b></td>\n\
                            <td valign="middle">\n\
                                <input type="text" class="form-control" name="refName[]" style="margin-right: 10px; width: 100%"/></td>\n\
                            <td></td>\n\
                            <td></td></tr>';
        var row_2 = '<tr class="reference reference-'+reference_count+'">\n\
                            <td valign="middle"><b><center>Company: </center></b></td>\n\
                            <td valign="middle">\n\
                                <input type="text" class="form-control" name="refCompany[]" style="margin-right: 10px; width: 80%"/></td>\n\
                            <td valign="middle"><b><center>Position: </center></b></td>\n\
                            <td valign="middle">\n\
                                <input type="text" class="form-control" name="refPosition[]" style="margin-right: 10px; width: 50%"/></td></tr>';
        var row_3 = '<tr class="reference reference-'+reference_count+'">\n\
                            <td valign="middle"><b><center>Reference: </center></b></td>\n\
                            <td valign="middle">\n\
                                <textarea type="text" class="form-control" name="refDes[]" rows="2" style="width: 190%"></textarea></td>\n\
                            <td></td>\n\
                            <td></td></tr>';
        
        $('#referenceTable').append(row_1);
        $('#referenceTable').append(row_2);
        $('#referenceTable').append(row_3);
    });
    
    $('#referenceRemoveBtn').click(function() {
        $('.reference-'+reference_count).remove();
        reference_count--;
    });
    
    $('.marketplaceBtn').click(function (event) { $('#modal-custom').iziModal('open', event); });
    
    $('#closeSuccess').click(function() { $('#successPanel').fadeOut(300); });
    $('#closeError').click(function() { $('#errorPanel').fadeOut(300); });
});



/* FOR PROFILE PICTURE UPLOAD TO SYSTEM */
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var output = document.getElementById('output-image');
        output.src = reader.result;
        document.getElementById('imageUploadStatus').value = "Uploaded";
    };
    reader.readAsDataURL(event.target.files[0]);
}

function createList() {
    
    if(document.getElementById("contactNum").value!=="") {
        var contactNum = document.getElementById("contactNum").value;
        if(document.getElementById("countryCode").value==="065") {
            if(contactNum.length !== 8) {
                alert("Mobile No. should be 8 digit");
                document.getElementById('contactNum').focus();
                event.preventDefault();
                return false;
            } else if(!(contactNum.charAt(0)==="9" || contactNum.charAt(0)==="8")) {
                alert("Mobile No. should start with 9 or 8 ");
                document.getElementById('contactNum').focus();
                event.preventDefault();
                return false;
            }
        } else if(document.getElementById("countryCode").value==="852") {
            if(contactNum.length !== 8) {
                alert("Mobile No. should be 8 digit");
                document.getElementById('contactNum').focus();
                event.preventDefault();
                return false;
            } else if(!(contactNum.charAt(0)==="9" || contactNum.charAt(0)==="8" || contactNum.charAt(0)==="7" 
                        || contactNum.charAt(0)==="6" || contactNum.charAt(0)==="5" || contactNum.charAt(0)==="3"
                        || contactNum.charAt(0)==="2")) {
                alert("Mobile No. should start with 2,3,5,6,7,8,9 ");
                document.getElementById('contactNum').focus();
                event.preventDefault();
                return false;
            }
        } else if(document.getElementById("countryCode").value==="090") {
            if(contactNum.length !== 10) {
                alert("Mobile No. should be 10 digit");
                document.getElementById('contactNum').focus();
                event.preventDefault();
                return false;
            } else if(!(contactNum.charAt(0)==="9" || contactNum.charAt(0)==="8" || contactNum.charAt(0)==="7")) {
                alert("Mobile No. should start with 7,8,9 ");
                document.getElementById('contactNum').focus();
                event.preventDefault();
                return false;
            }
        }
    }
    contactNum = document.getElementById("countryCode").value + "+" + contactNum;
    document.getElementById("fullContactNum").value = contactNum;
    
    
    var eduTableLength = $("input[name^='schoolName']").length;
    var schoolNameArr = $("input[name^='schoolName']");
    var schoolDegreeArr = $("input[name^='schoolDegree']");
    var schoolMajorArr = $("input[name^='schoolMajor");
    var schoolPeriodArr = $("input[name^='schoolPeriod']");
    var eduExprList = "";
    
    
    for(var i=0;i<eduTableLength;i++) {
        if(schoolNameArr[i].value !== "") {
            eduExprList = eduExprList + schoolNameArr[i].value + ";&";
            eduExprList = eduExprList + schoolDegreeArr[i].value + ";&";
            eduExprList = eduExprList + schoolMajorArr[i].value + ";&";
            eduExprList = eduExprList + schoolPeriodArr[i].value + ";&";
        }
    }
    eduExprList = eduExprList.substring(0, eduExprList.length-2);
    
    var proTableLength = $("input[name^='projectTitle']").length;
    var projectTitleArr = $("input[name^='projectTitle']");
    var projectDesArr = $("textarea[name^='projectDes']");
    var proExprList = "";
    
    for(var i=0;i<proTableLength;i++) {
        if(projectTitleArr[i].value !== "") {
            proExprList = proExprList + projectTitleArr[i].value + ";&";
            proExprList = proExprList + projectDesArr[i].value + ";&";
        }
    }
    proExprList = proExprList.substring(0, proExprList.length-2);
    
    var awardTableLength = $("input[name^='award']").length;
    var awardArr = $("input[name^='award']");
    var awardStr = "";
    
    for(var i=0;i<awardTableLength;i++) {
        if(awardArr[i].value !== "") {
            awardStr = awardStr + awardArr[i].value + ";&";
        }
    }
    awardStr = awardStr.substring(0, awardStr.length-2);
    
    var skillTableLength = $("input[name^='proSkillName']").length;
    var proSkillNameArr = $("input[name^='proSkillName']");
    var proSkillLevelArr = $("select[name^='proSkillLevel']");
    var perSkillNameArr = $("input[name^='perSkillName']");
    var perSkillLevelArr = $("select[name^='perSkillLevel']");
    var skillList = "";
    
    for(var i=0;i<skillTableLength;i++) {
        if(proSkillNameArr[i].value !== "") {
            skillList = skillList + proSkillNameArr[i].value + ";&";
            skillList = skillList + proSkillLevelArr[i].value + ";&";
            skillList = skillList + "Professional" + ";&";
        }
        if(perSkillNameArr[i].value !== "") {
            skillList = skillList + perSkillNameArr[i].value + ";&";
            skillList = skillList + perSkillLevelArr[i].value + ";&";
            skillList = skillList + "Personal" + ";&";
        }
    }
    skillList = skillList.substring(0, skillList.length-2);
    
    var workTableLength = $("input[name^='workTitle']").length;
    var workTitleArr = $("input[name^='workTitle']");
    var workCompArr = $("input[name^='workCompany']");
    var workPeriodArr = $("input[name^='workPeriod']");
    var workDesArr = $("textarea[name^='workDes']");
    var workExprList = "";
    
    for(var i=0;i<workTableLength;i++) {
        if(workTitleArr[i].value !== "") {
            workExprList = workExprList + workTitleArr[i].value + ";&";
            workExprList = workExprList + workCompArr[i].value + ";&";
            workExprList = workExprList + workPeriodArr[i].value + ";&";
            workExprList = workExprList + workDesArr[i].value + ";&";
        }
    }
    workExprList = workExprList.substring(0, workExprList.length-2);
    
    var referenceTableLength = $("input[name^='refName']").length;
    var refNameArr = $("input[name^='refName']");
    var refCompanyArr = $("input[name^='refCompany']");
    var refPositionArr = $("input[name^='refPosition']");
    var refDesArr = $("textarea[name^='refDes']");
    var referenceList = "";
    
    for(var i=0;i<referenceTableLength;i++) {
        if(refNameArr[i].value !== "") {
            referenceList = referenceList + refNameArr[i].value + ";&";
            referenceList = referenceList + refCompanyArr[i].value + ";&";
            referenceList = referenceList + refPositionArr[i].value + ";&";
            referenceList = referenceList + refDesArr[i].value + ";&";
        }
    }
    referenceList = referenceList.substring(0, referenceList.length-2);
    
    document.getElementById("pageTransit").value = "updateResumeSYS";
    document.getElementById("eduExprList").value = eduExprList;
    document.getElementById("proExprList").value = proExprList;
    document.getElementById("awardStr").value = awardStr;
    document.getElementById("skillList").value = skillList;
    document.getElementById("workExprList").value = workExprList;
    document.getElementById("referenceList").value = referenceList;
    //document.resumeForm.submit();
}
