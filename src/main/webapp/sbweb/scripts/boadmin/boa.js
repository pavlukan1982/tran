var serviceUrl;
var unlockAccountAction;
var resetPasswordAction;

function setServiceUrl(serviceUrlValue) {
    serviceUrl = serviceUrlValue;
}

function setUnlockAccountAction(unlockAccountActionValue) {
    unlockAccountAction = unlockAccountActionValue;
}

function setResetPasswordActionAction(resetPasswordActionValue) {
    resetPasswordAction = resetPasswordActionValue;
}

function showMessage(messageId, messageValue) {
    if (messageValue) {
        $(messageId).html(messageValue);
        $(messageId).show();
    } else {
        $(messageId).hide();
    }
}

function showMessages(jsonResult){
    if (jsonResult){
        showMessage('#alert-message', jsonResult.alertMessage);
        showMessage('#info-message', jsonResult.infoMessage);
    }
}

function callService(params) {
    var url = serviceUrl + '?' + params;
    var data = "";
    $.post(url, {}, function (response) {
        data = response;
        showMessages(data);
    }).error(function () {
        alert("Sorry could not proceed " + url);
    });
}

function processAction(actionString, userId) {
    callService('action=' + actionString + '&userId=' + userId);
}
function unlockAccount(userId) {
    processAction(unlockAccountAction, userId);
}

function resetPassword(userId) {
    processAction(resetPasswordAction, userId);
}
