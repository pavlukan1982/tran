var serviceUrl;
var unlockAccountPath;

var usersTable = 'table#users';
var resultRowTpl = '#resultRow-tpl';

var currentFormName;

function setServiceUrl(serviceUrlValue) {
    serviceUrl = serviceUrlValue;
}

function setUnlockAccountPath(unlockAccountValue) {
    unlockAccountPath = unlockAccountValue;
}

function showMessage(messageId, messageValue, signAppend) {
    if (messageValue) {
        if (signAppend==1){
            $(messageId).append("<br>"+messageValue);
        } else {
            $(messageId).html(messageValue);
        }
        $(messageId).show();
    } else {
        $(messageId).hide();
    }
}

function showAlertMessage(messageValue, signAppend) {
    showMessage('#alert-message', messageValue, signAppend);
}

function showInfoMessage(messageValue, signAppend) {
    showMessage('#info-message', messageValue, signAppend);
}

function showMessages(jsonResult, signAppendMessages) {
    if (jsonResult) {
        showAlertMessage(jsonResult.alertMessage, signAppendMessages);
        showInfoMessage(jsonResult.infoMessage, signAppendMessages);
    }
}

function clearMessages() {
    showAlertMessage("");
    showInfoMessage("");
}


function postForm(formName, onDoneFunction) {
    currentFormName = formName;
    var $form = $(formName);
    console.log('submit');
    console.log('parameters: ' + $form.serialize());
    $.ajax({
        type: "POST",
        url: serviceUrl,
        data: $form.serialize(), // serializes the form's elements.
    }).done(function (data) {
        console.log("data.status: " + data.status);
        onDoneFunction(data);
    });
}

function processActionAndRefresh(actionString, userId) {
    $.ajax({
        type: "GET",
        url: unlockAccountPath + '/' + userId,
    }).done(function (data) {
        console.log("data.status: " + data.status);
        showMessages(data);
        if (data.status == "OK") {
            postForm(currentFormName,refreshResponseData);
        }
    });
}
function unlockAccount(userId) {
    processActionAndRefresh(unlockAccountPath, userId);
}

function emptyResultTable() {
    $(usersTable + ' tbody').empty();
    $(usersTable).hide();
}
function showResultTable() {
    $(usersTable).show();
}

function refreshResultTable(arr) {
    emptyResultTable();
    var tableRowTpl = $(resultRowTpl).html();
    var arrLen = arr.length;
    for (var i = 0; i < arrLen; i++) {
        var rec = arr[i];
        tableRow = tableRowTpl.replace(/#count/g, i + 1).replace(/#userId/g, rec.userId)
            .replace(/#firstName/g, rec.firstName).replace(/#lastName/g, rec.lastName)
            .replace(/#gaId/g, rec.gaId).replace(/#gaName/g, rec.gaName)
            .replace(/#failedLoginCount/g, rec.failedLoginCount);
        $(usersTable + ' tbody').append(tableRow);
        console.log('Added row for user #' + rec.userId);
        if (rec.failedLoginCount == 0) {
            $(usersTable + ' tbody tr#' + rec.userId + ' td#tdUnlockAccount a').remove();
        }
    }
    if (arrLen > 0) {
        showResultTable();
    }

}

function refreshResponseData(data) {
    if (data.status != "OK") {
        showMessages(data,1);
    }
    if (data.status=="OK") {
        refreshResultTable(data.userLookupJsons);
    }
}

function displayResponseData(data) {
    showMessages(data);
    if (data.status=="OK") {
        refreshResultTable(data.userLookupJsons);
    }
}

function submitForm() {
    emptyResultTable();
    clearMessages();
    postForm("#userLookup",displayResponseData);
    return false;
}

function submitFormAgency() {
    emptyResultTable();
    clearMessages();
    postForm("#userLookupAgency",displayResponseData);
    return false;
}
