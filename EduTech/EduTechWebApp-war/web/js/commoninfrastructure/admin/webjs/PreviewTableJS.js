$(document).ready(function() {
    $('#previewTable').DataTable({
    });
    console.log("previewTable initialised");
} );

function handleFileUpload(fileList){
    var file = fileList[0];//there will only be 1 file uploaded. so 1st in FileList.
    var parseData;
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
            console.log("PARSED DATA : "+parseData);
            parseError = results.errors;
            console.log("PARSING ERRORS : "+parseError);
            parseMeta = results.meta;
            console.log("PARSING META DATA : "+parseMeta);
            
            //empty table div and replace with empty table
            $('#previewTableDiv').empty().append(
                    '<table id="previewTable" class="table table-striped table-bordered" style="width:100%"><thead><tr><th>Salutation</th><th>First Name</th><th>Last Name</th><th>Username</th><th>Email</th><th>Contact Number</th></tr></thead></table>'
                    );
            //reinitialise table
            console.log("data to be displayed in preview table is : "+parseData);
            $('#previewTable').DataTable({
                data: parseData,
                columns: [
                    {data:'salutation'},
                    {data:'firstName'},
                    {data:'lastName'},
                    {data:'username'},
                    {data:'email'},
                    {data:'contactNum'}
                ]
            });
        }
    };
    console.log("PAPA START --");
    //start parsing
    Papa.parse(file, config);
    console.log("PAPA END --");
};  