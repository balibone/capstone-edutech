var parseData;

$(document).ready(function() {
    $('#previewTable').DataTable();
    console.log("previewTable initialised");
    //submit user list JSON to UserREST endpoint.
    $("#uploadCsv").on("submit", function(event){
        event.preventDefault();
        if(typeof parseData === "undefined"){
            alert("Please upload a .csv file!");
        }else{
            console.log("------------------ Submitting JSON : ");
            console.log(JSON.stringify(parseData, null, "   "));
            $.ajax({
                url: "api/user/masscreate",
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(parseData),
                success: function(data){
                    alert("Mass creation successful");
                    top.location.replace("SystemAdmin?pageTransit=SystemAdminDashboard");
                },
                error: function(jqXHR,status,error){
                    console.log("Request fired to "+this.url);
                    alert(status+": "+error+". Please ensure usernames in CSV do not duplicate with any existing usernames and that your CSV is formatted according to the <b>example CSV<b>.");
                }
            });
        }
    });
    //ajax loading animation
    $(document).ajaxStart(function(){
        $("#wait").css("display", "block");
    });
    $(document).ajaxComplete(function(){
        $("#wait").css("display", "none");
    });
} );

function handleFileUpload(fileList){
    var file = fileList[0];//there will only be 1 file uploaded. so 1st in FileList.
    var parseError;
    var parseMeta;
    //set up config
    var config ={
        header : true,
        complete: function(results, file){
            console.log("Parsing complete:", results, file);
            parseData = results.data;
            //remove that last line that was showing empty row.
            parseData.pop();
            //remove rows with duplicate usernames AND/OR invalid user types
            for(var i = 0; i < parseData.length-1; i++) {
                var base = parseData[i];
                
                //if base object is not yet null and usertype is invalid, set it to null
                if(base !== null && base.userType !== "student" && base.userType !== "instructor" &&  base.userType !== "dualadmin" &&  base.userType !== "unifyadmin" &&  base.userType !== "edutechadmin" && base.userType !== "superadmin"){
                    parseData[i] = null;
                    base = parseData[i];
                }
                
                //if base object is still !== null 
                if(base !== null){
                    for(var j = i+1 ; j < parseData.length; j++){
                        var comparison = parseData[j];
                        //if base object is !== null
                        if(comparison != null){
                            console.log("comparing index "+i+" : "+base+" against index "+j+" : "+comparison+" !");
                            if(base.username === comparison.username){
                                console.log("Duplicate! Removing index "+j+" ...");
                                parseData[j] = null;
                            }
                        }
                    }
                }
            }
            if(parseData.indexOf(null)!==-1){
                alert("There were some discrepancies in your CSV, such as rows with duplicate usernames AND/OR invalid user types. We have cleaned this data for you.");
            }
            //throw away all null values
            parseData = parseData.filter(obj => obj !== null);
            
            //empty table div and replace with empty table
            $('#previewTableDiv').empty().append(
                    '<table id="previewTable" class="table table-striped table-bordered" style="width:100%"><thead><tr><th>Salutation</th><th>First Name</th><th>Last Name</th><th>Username</th><th>Email</th><th>Contact Number</th><th>Type</th></tr></thead></table>'
                    );
            //reinitialise table
            $('#previewTable').DataTable({
                data: parseData,
                columns: [
                    {data:'userSalutation'},
                    {data:'userFirstName'},
                    {data:'userLastName'},
                    {data:'username'},
                    {data:'email'},
                    {data:'contactNum'},
                    {data:'userType'}
                ]
            });
            console.log("CSV TO JSON  END --");
            
            console.log("------------------ FINAL JSON : ");
            console.log(JSON.stringify(parseData, null, "   "));
        }
    };
    console.log("CSV TO JSON START --");
    //start parsing
    Papa.parse(file, config);
};  