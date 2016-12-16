$(function(){
    getUser(1);
});

var getUser = function(pageNo){
    $.ajax({
        url:"/admin/api/userlist?pageNo="+pageNo,
        method:'GET'
    }).done(function (data) {
        showUserList(data)
    });
}

var modify = function(id){
    var phone = $("#"+id+" #phone").val();
    var password = $("#"+id+" #password").val();
    $.ajax({
        url:"/admin/api/modifyuser?id="+id+"&phone="+phone + "&password="+password,
        method:'GET'
    }).done(function (data) {
        alert(data.msg);
    });
}

var deleteUser = function(){
    $.ajax({
        url:"/admin/api/deleteuser?id="+id,
        method:'GET'
    }).done(function (data) {
        alert(data.msg);
    });
}

var showUserList = function(data){
    var images = data.data;
    var trs = "";
    $.each(images,function(index,item){
        trs+="<tr id='"+item.id+"'>";
        trs+="<td>" + item.id + "</td>";
        trs+="<td><input type='text' id='phone' value='"+item.phone +"'></td>";
        trs+="<td><input type='text' id='password' value='"+item.password +"'></td>";
        trs+="<td>";
        trs+="<button onclick='modify("+item.id+")'>修改</button>";
        trs+="<button onclick='deleteUser()'>删除</button>";
        trs+="</td>";
        trs+="</tr>";
    });
    $("#userBody").html(trs);
};

