var edit = function () {
    var title = $(".title h2").text();
    var content = $(".content pre").text();
    var titlehtml = "<input type='text' value='"+title+"'></input>";
    var contenthtml = "<textarea type='text' value='"+content+"'></textarea>";
    var ahtml = "<a href='javascript:save();'>保存</a>";
    $(".title").html(titlehtml);
    $(".content").html(contenthtml);
    $(".head").html(ahtml);
}


var save = function(){
    var title = $(".title input").val();
    var content = $(".content textarea").val();
    var titlehtml = "<h2>"+title+"</input>";
    var contenthtml = "<pre>"+content+"</pre>";
    var ahtml = "<a href='javascript:edit();'>编辑</a>";
    $(".title").html(titlehtml);
    $(".content").html(contenthtml);
    $(".head").html(ahtml);
}
