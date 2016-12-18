$(function(){
    getNotices(1);
});

var getNotices = function(pageNo){
    $.ajax({
        url:"/admin/api/noticelist?pageNo="+pageNo,
        method:'GET'
    }).done(function (data) {
        showNotices(data)
    });
}

var showNotices = function(data){
    var notices = data.data;
    var trs = "";
    $.each(notices,function(index,item){
        trs+="<tr>";
        trs+="<td>" + item.id + "</td>";
        trs+="<td><a href='/admin/noticedetail?id="+item.id+"'>" + item.title + "</td>";
        trs+="<td>" + moment(new Date(parseInt(item.createTime))).format("YYYY-MM-DD HH:mm:ss") + "</td>";
        trs+="</tr>";
    });
    $("#noticeBody").html(trs);
};