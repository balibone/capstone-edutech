<!--initialization-->
console.log("ONESIGNAL INITIALIZED!!!!!!!!!!!!!!!!!!!!");
var OneSignal = window.OneSignal || [];
OneSignal.push(function() {
  OneSignal.init({
    appId: "7c25a78b-f0ea-4190-a170-b04ecd514446",
  });
});

<!--ONESIGNAL PUSH USERNAME TAG-->
OneSignal.push(function() {
    var usernameValue = retrieveCookie("username");
    /* These examples are all valid */
    OneSignal.sendTag("username", usernameValue);

    console.log("tag sent to OneSignal is username:"+usernameValue);
                
});

function retrieveCookie(name) {
    const value = '; ' + document.cookie;
    const parts = value.split('; '+name+'=');
    if (parts.length === 2) return parts.pop().split(';').shift();
    return false; 
};
