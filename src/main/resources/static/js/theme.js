$(function(){
    getImage(1);
});

var getImage = function(pageNo){
    $.ajax({
        url:"/admin/api/theme/summary?page="+pageNo,
        method:'GET'
    }).done(function (data) {
        showImageList(data)
    });
}

var showImageList = function(data){
    var images = data.data;
    var themes = "";
    $.each(images,function(index,item){
        themes += '<div class="theme" data="'+item.theme.themeid+'" id="theme'+index+'">';
        themes += '<div class="header">';
        themes += '<label class="col-md-2 name">'+item.theme.creator+'</label>';
        themes += '<label class="col-md-2 date">'+moment(new Date(parseInt(item.theme.createTime))).format("YYYY-MM-DD HH:mm:ss")+'</label>';
        themes += '</div>';
        themes += '<div class="content">'+item.theme.title;
        themes += '</div>';
        $.each(item.comments,function(biz,comment){
            themes += '<div class="reply">';
            themes += '<div class="header">';
            themes += '<label class="col-md-4 name">'+comment.customer+'</label>';
            themes += '<label class="col-md-2 date">'+moment(new Date(parseInt(comment.createTime))).format("YYYY-MM-DD HH:mm:ss")+'</label>';
            themes += '</div>';
            themes += '<div class="content">'+comment.comment;
            themes += '</div>';
            themes += '</div>';
        });
        themes += '<div class="reply">';
        themes += '<textarea></textarea>';
        themes += '<button onclick="reply('+index+')">回复</button>';
        themes += '</div>';
    });
    $("#themelist").html(themes);
};

var reply = function(index){
    var id=$("#theme"+index).attr("data");
    var uid = $("ul #uid").attr("data");
    var bizId = $("#theme"+index +" .reply").length;
    var createTime = Date.parse(new Date());
    var comment = $("#theme"+index + " textarea").text();
    $.ajax({
        url: "/forum/theme/reply",
        method: 'POST',
        data: {
            uid: uid,
            themeid: id,
            bizId: bizId,
            createTime: createTime,
            comment: comment
        }
    }).done(function(data){
        alert(data.msg);
    });
}