$(function(){
    getImage(1);
});

var getImage = function(pageNo){
    $.ajax({
        url:"/admin/api/theme/summary",
        method:'GET'
    }).done(function (data) {
        showImageList(data)
    });
}

var showImageList = function(data){
    var images = data.data;
    var themes = "";
    $.each(images,function(index,item){
        themes += '<div class="theme" id="theme'+index+'">';
        themes += '<div class="header">';
        themes += '<label class="col-md-2 name">'+item.theme.creator+'</label>';
        themes += '<label class="col-md-2 date">'+item.theme.createTime+'</label>';
        themes += '</div>';
        themes += '<div class="content">'+item.theme.title;
        themes += '<div class="replylabel">回复</div>';
        themes += '</div>';
        $.each(item.comments,function(biz,comment){
            themes += '<div class="reply">';
            themes += '<div class="header">';
            themes += '<label class="col-md-4 name">'+comment.customer+' 回复 '+item.theme.creator+'</label>';
            themes += '<label class="col-md-2 date">'+comment.createTime+'</label>';
            themes += '</div>';
            themes += '<div class="content">'+comment.comment;
            themes += '<div class="replylabel">回复</div>';
            themes += '</div>';
            themes += '</div>';
            themes += '</div>';
        });
    });
    $("#themelist").html(themes);
};
