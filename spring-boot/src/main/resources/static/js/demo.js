//初始变量声明-默认
var topH = $(".header").height(),
    lfW = $(".main_left").width(),
    btmH =$(".footer").height()+1;
var seLH = 51,  //左侧分割高度
    seRH = 25;  //内容分割高度
var dtH = 38, //$(".left_nav > dt").height()
    len = 1;  //$(".left_nav > dt").length

//默认时，显示中间左侧、主体区域
function windowShow(len){
  var resizeTimer = null;
      if (resizeTimer) {
          clearTimeout(resizeTimer)
      }
      resizeTimer = setTimeout(function(){
          var winW = $(window).width(),
              winH = $(window).height(),
              mainH = winH-topH-btmH; 
              mainW = winW-lfW; 
          $(".mainbody,.main_left").css({"height":mainH+'px'});
          $(".main_con").css({height:mainH +'px',width:mainW +'px'});
          $(".mc_box").css({height:mainH - seRH +'px'});
          $(".lf_center").css({height:mainH  - seLH +'px'});     
          $(".left_nav dd.default").css({height:mainH -(dtH*len) - seLH +'px'});     
      }, 500);
      return false;  
}

//计算窗体中间区域宽高并赋值
function windowSet(len){          
    var resizeTimer = null;
    $(window).on('resize',function(){//对浏览器窗口调整大小进行计数
          if (resizeTimer) {
              clearTimeout(resizeTimer)
          }
          resizeTimer = setTimeout(function(){
              var winW = $(this).width(),
                  winH = $(this).height();  
                  mainH = winH-topH-btmH; 
                  mainW = winW-lfW; 
              $(".mainbody,.main_left").css({"height":mainH+'px'});
              $(".main_con").css({height:mainH +'px',width:mainW +'px'});
              $(".mc_box").css({height:mainH - seRH +'px'});
              $(".lf_center").css({height:mainH  - seLH +'px'});     
              $(".left_nav dd.default").css({height:mainH -(dtH*len) - seLH +'px'});              
          }, 400);
          return false;
    });
};


