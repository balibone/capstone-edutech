<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Marketplace - Item Likes List</title>

        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.8.1/bootstrap-table.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.8.1/bootstrap-table.min.js"></script>
        <script type="text/javascript">
            var checkedRows = [];

            $('#eventsTable').on('check.bs.table', function (e, row) {
                checkedRows.push({id: row.id, name: row.name, forks: row.forks});
                console.log(checkedRows);
            });

            $('#eventsTable').on('uncheck.bs.table', function (e, row) {
                $.each(checkedRows, function (index, value) {
                    if (value.id === row.id) {
                        checkedRows.splice(index, 1);
                    }
                });
                console.log(checkedRows);
            });

            $("#add_cart").click(function () {
                $("#output").empty();
                $.each(checkedRows, function (index, value) {
                    $('#output').append($('<li></li>').text(value.id + " | " + value.name + " | " + value.forks));
                });
            });
        </script>
    </head>
    <body>
        <table id="eventsTable"
               data-toggle="table"
               data-height="300"
               data-url="https://api.github.com/users/wenzhixin/repos?type=owner&sort=full_name&direction=asc&per_page=100&page=1"
               data-pagination="true"
               data-search="true"
               data-show-refresh="true"
               data-show-toggle="true"
               data-show-columns="true"
               data-toolbar="#toolbar">
            <thead>
                <tr>
                    <th data-field="state" data-checkbox="true"></th>
                    <th data-field="name">Name</th>
                    <th data-field="stargazers_count">Stars</th>
                    <th data-field="forks_count">Forks</th>
                    <th data-field="description">Description</th>
                </tr>
            </thead>
        </table>

        <button id="add_cart">Add to card</button>
        <ul id="output"></ul>
    </body>
</html>
