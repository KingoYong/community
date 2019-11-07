function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            content: content,
            "type": 1
        }),
        success: function (response) {
            if (response.code === 200) {
                $("#comment_section").hide();
            } else if (response.code === 2003) {
                var isAccepted = confirm(response.message + "，是否登录");
                if (isAccepted) {
                    window.open("https://github.com/login/oauth/authorize?" +
                        "client_id=f33efe3834065df9399a&" +
                        "redirect_uri=http://localhost:8888/callback&scope=user&state=1")
                    window.localStorage.setItem("closable", "true")
                }
            } else {
                console.log(response.message);
            }
            console.log(response);
        },
        dataType: "json"
    });
}