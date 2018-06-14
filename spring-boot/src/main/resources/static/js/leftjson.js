//left_nav加载json数据
/*var jsonNav = [{
                    id:'lfn_t1',
                    names:'综合查询',
                    iclass:'i_l1',
                    list:[
                          {pid:'lfn_11',text:'注册企业查询',link:'page/1.html'},
                          {pid:'lfn_12',text:'从业人员查询',link:'page/2.html'},
                          {pid:'lfn_13',text:'进货台账查询',link:'page/3.html'},
                          {pid:'lfn_14',text:'配送台账查询',link:'page/4.html'},
                          {pid:'lfn_15',text:'回收台账查询',link:'page/3.html'},
                          {pid:'lfn_16',text:'留样台账查询',link:'page/2.html'},
                          {pid:'lfn_17',text:'团膳外卖台账查询',link:'page/1.html'},
                          {pid:'lfn_18',text:'大型宴会申报查询',link:'page/4.html'},
                          {pid:'lfn_19',text:'产品追溯',link:'page/3.html'}
                    ],
                    pid:'lfn_d1'
                },{
                    id:'lfn_t2',
                    names:'统计分析',
                    iclass:'i_l2',
                    list:[
                          {pid:'lfn_21',text:'许可企业录入情况统计',link:'page/2.html'},
                          {pid:'lfn_22',text:'企业录入情况统计',link:'page/4.html'},
                          {pid:'lfn_23',text:'台账录入情况统计',link:'page/1.html'},
                          {pid:'lfn_24',text:'区域录入情况统计',link:'page/3.html'},
                          {pid:'lfn_25',text:'追溯企业注册情况统计',link:'page/2.html'},
                          {pid:'lfn_26',text:'总体注册情况统计表',link:'page/4.html'},
                          {pid:'lfn_27',text:'总体注册情况统计图',link:'page/1.html'}
                    ],
                    pid:'lfn_d2'
                },{
                    id:'lfn_t3',
                    names:'预警提示',
                    iclass:'i_l3',
                    list:[
                          {pid:'lfn_31',text:'证照预警',link:'page/3.html'},
                          {pid:'lfn_32',text:'人员预警',link:'page/2.html'},
                          {pid:'lfn_33',text:'台账预警',link:'page/1.html'},
                          {pid:'lfn_34',text:'质保预警',link:'page/4.html'},
                          {pid:'lfn_35',text:'证照预警',link:'page/2.html'}
                    ],
                    pid:'lfn_d3'
                },{
                    id:'lfn_t4',
                    names:'政企互动',
                    iclass:'i_l4',
                    list:[
                          {pid:'lfn_41',text:'通知公告',link:'page/4.html'},
                          {pid:'lfn_42',text:'监管动态',link:'page/3.html'},
                          {pid:'lfn_43',text:'专项处置',link:'page/2.html'},
                          {pid:'lfn_44',text:'政策规章',link:'page/1.html'},
                          {pid:'lfn_45',text:'业务指导',link:'page/4.html'},
                          {pid:'lfn_46',text:'安全知识',link:'page/3.html'},
                          {pid:'lfn_47',text:'业务资料',link:'page/2.html'}
                    ],
                    pid:'lfn_d4'
                },{
                    id:'lfn_t5',
                    names:'系统管理',
                    iclass:'i_l5',
                    list:[
                          {
                            pid:'lfn_51',
                            text:'用户管理',
                            link:'javascript:void(0)',
                            list:[
                              {pid:'lfn_511',text:'用户帐号管理',link:'page/4.html'},
                              {pid:'lfn_512',text:'用户组维护',link:'page/3.html'}
                            ]
                        },{
                            pid:'lfn_52',
                            text:'权限管理',
                            link:'javascript:void(0)',
                            list:[
                              {pid:'lfn_521',text:'权限分配',link:'page/2.html'},
                              {
                                pid:'lfn_522',
                                text:'权限查询',
                                link:'javascript:void(0)',
                                list:[
                                   {pid:'lfn_5221',text:'用户视角',link:'page/4.html'},
                                   {pid:'lfn_5222',text:'权限视角',link:'page/3.html'}
                                ]
                              },{
                                pid:'lfn_523',
                                text:'权限配置',
                                link:'javascript:void(0)',
                                list:[
                                   {pid:'lfn_5231',text:'权限维护',link:'page/1.html'}
                                ]
                              }
                            ]
                          },
                          {pid:'lfn_53',text:'组织机构管理',link:'page/2.html'},
                          {pid:'lfn_54',text:'角色管理',link:'page/3.html'},
                          {pid:'lfn_55',text:'辖区管理',link:'page/2.html'}
                    ],
                    pid:'lfn_d5'
}];

//数组拼接成左侧菜单方法
function navLeft(data){
   var navL='';
   var objNav= eval(data);
   for(var i = 0; i < objNav.length; i++){ 
       navL = '<dt id='+ objNav[i].id +'><a href="javascript:void(0)"><i class='+ objNav[i].iclass +'></i><span>'+ objNav[i].names +'</span></a></dt>'+'<dd id='+ objNav[i].pid +'></dd>';
       $('.left_nav').append(navL);//添加一级
       if(objNav[i].list.length > 0 && $('#'+objNav[i].pid).find("ul").length==0){
           $('#'+objNav[i].pid).append("<ul class='lfnav_list'></ul>");
      	   var subL = '';
      	   var j = 0;
      	   var subObj = objNav[i].list; //console.log(JSON.stringify(subObj));
           for(;subObj[j];){ 
               subL = '<li id=\"'+subObj[j].pid +'\"><a href=\"'+subObj[j].link+'\" target=\"'+subObj[j].pid +'\">'+subObj[j].text+'</a></li>';  
      	       j++;
               $('#'+objNav[i].pid).find('.lfnav_list').append(subL);//添加二级
           }	
       }   
   }

   $('.left_nav > dt:first').find('a').addClass("default");
   $('.left_nav > dd:first').addClass("default");

   return false;
} */

var JsonLf = [{
      id: '0801_02',
      pId: 'null',
      name: '综合查询',
      appCode: '0801',
      linkPath: 'javascript:void(0)',
      icon:'i_l1'
    }, {
      id: '0801_02_01',
      pId: '0801_02',
      name: '注册企业查询',
      appCode: '0801',
      linkPath: 'search/query.html',
      icon:''
    }, {
      id: '0801_02_02',
      pId: '0801_02',
      name: '许可信息查询',
      appCode: '0801',
      linkPath: 'page/2.html',
      icon:''
    }, {
      id: '0801_02_03',
      pId: '0801_02',
      name: '连锁单位查询',
      appCod: '0801',
      linkPath: 'search/linkage.html',
      icon:'' 
    }, {
      id: '0801_02_04',
      pId: '0801_02',
      name: '从业人员查询',
      appCod: '0801',
      linkPath: 'search/people.html',
      icon:'' 
    }, {
      id: '0801_02_05',
      pId: '0801_02',
      name: '进货台账查询',
      appCod: '0801',
      linkPath: 'search/purchase.html',
      icon:'' 
    }, {
      id: '0801_02_06',
      pId: '0801_02',
      name: '配送台账查询',
      appCod: '0801',
      linkPath: 'search/delivery.html',
      icon:'' ,
    }, {
      id: '0801_02_07',
      pId: '0801_02',
      name: '回收台账查询',
      appCod: '0801',
      linkPath: 'search/recovery.html',
      icon:'' 
    }, {
      id: '0801_02_08',
      pId: '0801_02',
      name: '留样台账查询',
      appCod: '0801',
      linkPath: 'search/sample.html',
      icon:'' 
    }, {
      id: '0801_02_09',
      pId: '0801_02',
      name: '团膳外卖台账查询',
      appCod: '0801',
      linkPath: 'search/takeout.html',
      icon:''
    }, {
      id: '0801_02_10',
      pId: '0801_02',
      name: '大型宴会申报查询',
      appCod: '0801',
      linkPath: 'search/banquet.html',
      icon:'' 
    }, {
      id: '0801_02_11',
      pId: '0801_02',
      name: '产品追溯',
      appCod: '0801',
      linkPath: 'search/trace.html',
      icon:'' 
    }, {
      id: '0801_03',
      pId: 'null',
      name: '统计分析',
      appCode: '0801',
      linkPath: 'javascript:void(0)',
      icon:'i_l2'
    }, {
      id: '0801_03_01',
      pId: '0801_03',
      name: '许可企业录入情况统计',
      appCode: '0801',
      linkPath: 'statistic/license.html',
      icon:''
    }, {
      id: '0801_03_02',
      pId: '0801_03',
      name: '企业录入情况统计',
      appCode: '0801',
      linkPath: 'page/enterprise-query.html',
      icon:''
    }, {
      id: '0801_03_03',
      pId: '0801_03',
      name: '台账录入情况统计',
      appCode: '0801',
      linkPath: 'statistic/parameter.html',
      icon:''
    }, {
      id: '0801_03_04',
      pId: '0801_03',
      name: '区域录入情况统计',
      appCode: '0801',
      linkPath: 'statistic/region.html',
      icon:''
    }, {
      id: '0801_03_05',
      pId: '0801_03',
      name: '追溯企业注册情况统计',
      appCode: '0801',
      linkPath: 'page/1.html',
      icon:''
    }, {
      id: '0801_03_06',
      pId: '0801_03',
      name: '总体注册情况统计表',
      appCode: '0801',
      linkPath: 'statistic/total_table.html',
      icon:''
    }, {
      id: '0801_03_07',
      pId: '0801_03',
      name: '总体注册情况统计图',
      appCode: '0801',
      linkPath: 'statistic/total_chart.html',
      icon:''
     }, {
      id: '0801_04',
      pId: 'null',
      name: '预警提示',
      appCode: '0801',
      linkPath: 'javascript:void(0)',
      icon:'i_l3'
    }, {
      id: '0801_04_01',
      pId: '0801_04',
      name: '证照预警',
      appCode: '0801',
      linkPath: 'warning/licence.html',
      icon:''
    }, {
      id: '0801_04_02',
      pId: '0801_04',
      name: '人员预警',
      appCode: '0801',
      linkPath: 'warning/people.html',
      icon:''
    }, {
      id: '0801_04_03',
      pId: '0801_04',
      name: '台账预警',
      appCode: '0801',
      linkPath: 'warning/ledger.html',
      icon:''
    }, {
      id: '0801_04_04',
      pId: '0801_04',
      name: '质保预警',
      appCode: '0801',
      linkPath: 'page/4.html',
      icon:''
    }, {
      id: '0801_04_05',
      pId: '0801_04',
      name: '质保预警',
      appCode: '0801',
      linkPath: 'page/1.html',
      icon:''
    }, {
      id: '0801_05',
      pId: 'null',
      name: '政企互动',
      appCode: '0801',
      linkPath: 'javascript:void(0)',
      icon:'i_l4'
    }, {
      id: '0801_05_01',
      pId: '0801_05',
      name: '通知公告',
      appCode: '0801',
      linkPath: 'page/1.html',
      icon:''
    }, {
      id: '0801_05_02',
      pId: '0801_05',
      name: '监管动态',
      appCode: '0801',
      linkPath: 'page/2.html',
      icon:''
    }, {
      id: '0801_05_03',
      pId: '0801_05',
      name: '专项处置',
      appCode: '0801',
      linkPath: 'page/3.html',
      icon:''
    }, {
      id: '0801_05_04',
      pId: '0801_05',
      name: '政策规章',
      appCode: '0801',
      linkPath: 'page/4.html',
      icon:''
    }, {
      id: '0801_05_05',
      pId: '0801_05',
      name: '业务指导',
      appCode: '0801',
      linkPath: 'page/1.html',
      icon:''
    }, {
      id: '0801_05_06',
      pId: '0801_05',
      name: '安全知识',
      appCode: '0801',
      linkPath: 'page/2.html',
      icon:''
    }, {
      id: '0801_05_06_01',
      pId: '0801_05_06',
      name: '安全1',
      appCode: '0801',
      linkPath: 'page/2.html',
      icon:''
    }, {
      id: '0801_05_06_02',
      pId: '0801_05_06',
      name: '知识2',
      appCode: '0801',
      linkPath: 'page/1.html',
      icon:''

    }, {
      id: '0801_05_07',
      pId: '0801_05',
      name: '业务资料',
      appCode: '0801',
      linkPath: 'page/3.html',
      icon:''
    }, {
      id: '0801_06',
      pId: 'null',
      name: '系统管理',
      appCode: '0801',
      linkPath: 'javascript:void(0)',
      icon:'i_l5'
    }, {
      id: '0801_06_05',
      pId: '0801_06',
      name: '用户管理',
      appCode: '0801',
      linkPath: 'page/1.html',
      icon: ''
    }, {
      id: '0801_06_05_01',
      pId: '0801_06_05',
      name: '用户帐号管理',
      appCode: '0801',
      linkPath: 'page/1.html',
      icon: ''
    }, {
      id: '0801_06_05_02',
      pId: '0801_06_05',
      name: '用户组维护',
      appCode: '0801',
      linkPath: 'page/2.html',
      icon: ''
    }, {
      id: '0801_06_06',
      pId: '0801_06',
      name: '权限管理',
      appCode: '0801',
      linkPath: 'page/4.html',
      icon: ''
    }, {
      id: '0801_06_06_01',
      pId: '0801_06_06',
      name: '权限分配',
      appCode: '0801',
      linkPath: 'page/4.html',
      icon: ''
    }, {
      id: '0801_06_06_01_01',
      pId: '0801_06_06_01',
      name: '用户授权',
      appCode: '0801',
      linkPath: 'page/4.html',
      icon: ''
    }, {
      id: '0801_06_06_01_02',
      pId: '0801_06_06_01',
      name: '用户组授权',
      appCode: '0801',
      linkPath: 'page/2.html',
      icon: ''
    }, {
      id: '0801_06_06_01_03',
      pId: '0801_06_06_01',
      name: '角色授权',
      appCode: '0801',
      linkPath: 'page/3.html',
      icon: ''
    }, {
      id: '0801_06_06_02',
      pId: '0801_06_06',
      name: '权限查询',
      appCode: '0801',
      linkPath: 'page/1.html',
      icon: ''
    }, {
      id: '0801_06_06_02_01',
      pId: '0801_06_06_02',
      name: '用户视角',
      appCode: '0801',
      linkPath: 'page/1.html',
      icon: ''
    }, {
      id: '0801_06_06_02_02',
      pId: '0801_06_06_02',
      name: '权限视角',
      appCode: '0801',
      linkPath: 'page/3.html',
      icon: ''
    }, {
      id: '0801_06_06_03',
      pId: '0801_06_06',
      name: '权限配置',
      appCode: '0801',
      linkPath: 'page/2.html',
      icon: ''
    }, {
      id: '0801_06_06_03_01',
      pId: '0801_06_06_03',
      name: '权限维护',
      appCode: '0801',
      linkPath: 'page/2.html',
      icon: ''
    }, {
      id: '0801_06_07',
      pId: '0801_06',
      name: '组织机构管理',
      appCode: '0801',
      linkPath: 'page/4.html',
      icon: ''
}]

//数组拼接方法
function navLeft(data){
 var objNav= JSON.parse(JSON.stringify(data)); //eval(data);
 for(var i = 0; i < objNav.length; i++){
     if(objNav[i].pId != null && objNav[i].pId != "null" && objNav[i].pId != "" && $("#"+objNav[i].pId).length > 0){
        var subL = '<li id=\"'+objNav[i].id+'_li\"><a href=\"'+objNav[i].linkPath+'\" target=\"'+objNav[i].id+'\" id="'+objNav[i].id+'" title="'+objNav[i].name+'">'+objNav[i].name+'</a><ul class="lfnav_list" id="'+ objNav[i].id +'_ul"></ul></li>';//二级菜单 
		    var sunL ='<ul class="lfnav_list" id="'+ objNav[i].pId +'_ul">'+subL+'</ul>';//三级及以上菜单
          if($('#'+objNav[i].pId+'_ul').length > 0){
                $('#'+objNav[i].pId+'_ul').append(subL);//json数组二级id匹配一级的pid
          }else{
                $('#'+objNav[i].pId+"_dd").append(sunL); 
          }
      }else{
         var navL = '<dt><a id='+ objNav[i].id +' href="javascript:void(0)"><i class="'+objNav[i].icon+'"></i><span>'+ objNav[i].name +'</span></a></dt>'+'<dd id="'+ objNav[i].id +'_dd"></dd>';//一级菜单
         $('.left_nav').append(navL);//添加一级
     }
 }
 return false;
}





