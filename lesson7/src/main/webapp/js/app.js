window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

window.ajax = function (data, $error, success = () => {}) {

    $.ajax( {
        type: "POST",
        url: "",
        dataType: "json",
        data: data,
        success: function (response) {
            success(response)
            if (response["error"]) {
                $error.text(response["error"]);
            } else if (response["redirect"]) {
                location.href = response["redirect"];
            }
        }
    });
}
