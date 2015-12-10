$(document).ready(function() {
    $.ajax({
        url: "http://localhost:9292/users/{userLogin}"
    }).then(function(data) {
       $('.xSessionid').append(data.xSessionId);
    });
});