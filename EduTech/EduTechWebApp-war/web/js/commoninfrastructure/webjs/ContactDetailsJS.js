$(document).ready(function() {
    $('.sidebar-menu > li > a').click(function(e) {
        e.preventDefault();
        var active_tab_selector = $('.sidebar-menu > li.active > a').attr('href');

        var actived_nav = $('.sidebar-menu > li.active');
        actived_nav.removeClass('active');

        /* ADD 'active' CSS INTO CLICKED NAVIGATION */
        $(this).parents('li').addClass('active');

        /* HIDE DISPLAYING TAB CONTENT */
        $(active_tab_selector).removeClass('active');
        $(active_tab_selector).addClass('hide');

        /* SHOW TARGET TAB CONTENT */
        var target_tab_selector = $(this).attr('href');
        $(target_tab_selector).removeClass('hide');
        $(target_tab_selector).addClass('active');
    });
});

function deactivateCheck() {
    if(confirm("Confirm deactivate this contact account?")) { document.forms[0].submit(); }
    else { return false; }
}

function activateCheck() {
    if(confirm("Confirm activate this contact account?")) { document.forms[0].submit(); }
    else { return false; }
}