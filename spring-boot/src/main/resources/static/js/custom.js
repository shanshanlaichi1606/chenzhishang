$(function(){
//内容导航页面tab选项切换
  $(document).on("click",".mc_bar a",function(){
      $(this).addClass("default").siblings().removeClass();               
      var yy = $(this).attr("rel");
      $(this).closest(".main_con").find(".layer."+ yy).addClass("default").siblings().removeClass("default"); 
  }); 

//表格奇偶背景（鼠标移上效果）       
  $(".list tbody").hover(function(){ 
      $(this).addClass("tr_on"); 
      $(this).removeClass("tr_off"); 
    }, function(){ 
      $(this).addClass("tr_off"); 
      $(this).removeClass("tr_on"); 
  });    
 
 //th点击切换样式（排序）
 $(document).on("click","i.icon",function(){
      if(!$(this).hasClass("i_reverse")){
           $(this).addClass("i_reverse");
      }else{
           $(this).removeClass("i_reverse");
      }
 });
 $(document).on("click","i.i_reverse",function(){
      if(!$(this).hasClass("i_sort")){
           $(this).addClass("i_sort");
      }else{
           $(this).removeClass("i_sort");
      }
 });
$(document).on("click","i.i_sort",function(){
          $(this).removeClass("i_sort");
          $(this).removeClass("i_reverse");
 });

/*----*/
});
//禁用空格键方法
document.onkeydown= function(e){
  e = e ? e : event; 
  if(e.keyCode == 13 || e.keyCode == 32 ){
     e.returnValue = false; //IE下
     e.preventDefault(); //Firefox下
  } 
}
//在Firefox下单独禁用空格键方法
document.onkeyup = function(e){
  if(e.keyCode == 32 ){
     e.preventDefault();
  }
}

//2016-12-21 js
function canvas_c(id,c,n){
    var cirCanvs = $(id);
    cirCanvs.find("canvas").remove();
    cirCanvs.radialIndicator({
        radius: 88,         //圆环大小
        barBgColor: '#cccccc', //背景颜色
        barColor: c,//色值
        barWidth: 24,       //圆环宽度
        roundCorner : false, //内圆角
        fontFamily: 'Microsoft YaHei',
        fontSize: '30',
        percentage: true,  //百分比显示
        displayNumber: true //数值显示
    });
    var radialObj = cirCanvs.data('radialIndicator');
    radialObj.animate(n); //数值比例设置
   // return false;
}
//js方法添加千分号
function microNum(id){
    var number1 = $(id).text().toString();
    var regStr1 = /(\d{1,3})(?=(\d{3})+(?:$|\.))/g;//正则
    var ResultNum = number1.replace(regStr1, "$1,"); //console.debug(ResultNum);
    $(id).text(ResultNum);
}

//2017.2.10 add 鼠标经过图标按钮提示
var hoverTips = function(id,t){	
    var tipInner ='<div class="tips">'+t+'</div>';
    $("#"+id).hover(function(){
        $(this).append(tipInner);
        $(".tips").fadeIn();
    },function(){
        $(".tips").fadeOut().remove();
    });
}


//统计搜素tab切换 - 2017.07.20 add 
function queryTab(thumb, current, parent, nextDiv){
    $(document).on("mouseenter", thumb, function(){
        $(this).addClass(current).siblings().removeClass(current); 
        $("b.triangle").fadeIn();  
        var this_Lf = $(this).position().left; 
        $(this).hasClass("entshow_first") ? $("b.triangle").animate({left: this_Lf +126+'px'}, 100) : $("b.triangle").animate({left: this_Lf +166+'px'}, 100);
        $(nextDiv).animate({opacity:1, top:71 +'px'}, 300, function(){
               $(nextDiv).fadeIn();
        });                         
        var yy = $(this).attr("rel");
        $(this).closest(parent).find(".layer."+ yy).addClass(current).siblings().removeClass(current); 
        return false
    }); 
    $(document).on("mouseleave", $(thumb).closest(parent),function(){
        setTimeout(function(){
            $(nextDiv).animate({opacity:0, top:0 +'px'}, 200, function(){
                   $(nextDiv).fadeOut();
                   $(thumb).removeClass(current);
                   $("b.triangle").fadeOut();
            }); 
        },800);  
    }); 
}