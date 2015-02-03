<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <base href="<%=basePath%>" />
    <title>
	Index
</title>
    <style type="text/css"> 
  #loading-mask{ 
        position:absolute; 
        left:0; 
        top:0; 
        width:100%; 
        height:100%; 
        z-index:20000; 
        background-color:white; 
    } 
    #loading{ 
        position:absolute; 
        left:45%; 
        top:40%; 
        padding:2px; 
        z-index:20001; 
        height:auto; 
    } 
    
    #loading .loading-indicator{ 
        background:white; 
        color:#444; 
        font:bold 20px tahoma,arial,helvetica; 
        padding:10px; 
        margin:0; 
        height:auto; 
    } 
    #loading-msg { 
        font: normal 18px arial,tahoma,sans-serif; 
    } 
  </style> 


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
 <!-- 加载效果 -->   
<div id="loading">   
    <div class="loading-indicator">   
      <img src="js/ext/loading.gif"  style="margin-right:8px;float:left;vertical-align:top;"/>   
       <br/><span id="loading-msg">Loading ...</span>   
    </div>   
</div>   
 <link href="js/ext/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
    <script src="js/ext/ext-all.js" type="text/javascript"></script>
    <script src="js/ext/locale/ext-lang-zh_CN.js" type="text/javascript"></script>
    <link href="js/app/resources/css/app.css" rel="stylesheet" type="text/css" />
    <link href="js/app/resources/css/icon.css" rel="stylesheet" type="text/css" />
    <script src="js/app/app.js" charset="utf-8" type="text/javascript"></script>
    <script type="text/javascript">
        Ext.Loader.setConfig({
        //disableCaching : false //Comment this line out when you need to reload your Extjs js files
    });
    Ext.onReady(function () {
        if (Ext.BLANK_IMAGE_URL.substr(0, 4) == "data") {
            Ext.BLANK_IMAGE_URL = "js/ext/s.gif";
        }
        Ext.Ajax.on('requestcomplete', function (conn, response, options) {
        	if(response.getResponseHeader){
	            var sessionStatus = response.getResponseHeader("sessionstatus");
	            if (sessionStatus) {
	                /**Ext.Msg.confirm("操作异常", "操作超时，请重新登陆！", function (btn) {
	                if (btn == "yes") {
	                window.location.href = "Admin/Login";
	                }
	                });
	                */
	                window.location.href = "Admin/Login";
	                throw new Error("timeout!");
	            }
        	}
        });
    });
    </script>
<!-- 退去加载效果 --> 
  <script type="text/javascript">
      //Ext.get('loading').setOpacity(0.0, { duration: 1.0, callback: function () { this.hide(); } });
      Ext.get("loading").remove();
  </script> 
</body>
</html>
    