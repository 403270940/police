$(function(){
    getVideo(1);
});

var getVideo = function(pageNo){
    $.ajax({
        url:"/admin/api/videolist?pageNo="+pageNo,
        method:'GET'
    }).done(function (data) {
        showVideoList(data)
    });
}

var showVideoList = function(data){
    var images = data.data;
    var trs = "";
    $.each(images,function(index,item){
        trs+="<tr>";
        trs+="<td>" + item.id + "</td>";
        trs+="<td>" + item.phone + "</td>";
        trs+="<td>" + moment(new Date(parseInt(item.createtime))).format("YYYY-MM-DD HH:mm:ss") + "</td>";
        trs+="<td>" + item.comment + "</td>";
        trs+="<td>" + item.location + "</td>";
        trs+="<td>";
        trs+="<button>查看</button>";
        trs+="<button onclick='window.open(\"/admin/api/upload/download?id="+item.id+"\")'>下载</button>";
        trs+="<button onclick='window.open(\"/admin/api/upload/delete?id="+item.id+"\")'>删除</button>";
        trs+="</td>";
        trs+="</tr>";
    });
    $("#videoBody").html(trs);
};

var showVideo = function(url){
    var body = "<div><video src='"+url+"'/></div>";
    $(".modal-body").html(body);
    $(".modal-title").text("视频");
    $('#myModal').modal({
        keyboard: true
    })
}