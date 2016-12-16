$(function(){
    getImage(0);
});

var getImage = function(pageNo){
    $.ajax({
        url:"./api/image?page="+page,
        method:'GET'
    }).done(function (data) {
        showImageList(data)
    });
}

var showImageList = function(data){
    var images = data.data;
    var trs = "";
    $.each(images,function(index,item){
        trs+="<tr>";
        trs+="<td>" + item.id + "</td>";

        trs+="</tr>";
    });
};