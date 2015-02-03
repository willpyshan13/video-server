Ext.define('dy.controller.video.VideoType', {
    extend: 'Ext.app.Controller',
    views:["video.VideoType","video.VideoTypeEditWindow","video.VideoTypeRootGrid","video.VideoTypeLeafGrid"],
    models:["VideoType"],
    stores:["VideoTypeRoot","VideoTypeLeaf"],
    refs:[
          {
        	  ref:'VideoTypeLeafGrid',
        	  selector:'videotypeleafgrid'
          }
         ],
    init: function () {
    	var me=this;
        me.control({
        	"videotyperootgrid":{
        		"selectionchange":function(grid,rs){
	               	 if(rs.length==1){
	               		 var record=rs[0];
	               		 var typeParent=record.getId();
	               		 Ext.apply(me.getVideoTypeLeafStore().getProxy().extraParams,{
	               			typeParent:typeParent 
	               		 });
	               		me.getVideoTypeLeafStore().load();
	               		me.getVideoTypeLeafGrid().setTitle("所属分类 : "+record.get('typeDesc'));
	               	 }
	            }
       	
        		
        	},
        	"videotypeleafgrid":{
        		
        	},
        	"videotypeleafgrid button[action=add]":{
        		"click":function(btn,e){
        			//当前没有选中视频时,不能添加 视频链接
        			if(me.getVideoTypeLeafStore().getProxy().extraParams["typeParent"]==-1){
        				Ext.Msg.alert("提示","请先选择一级分类!");
        				return false;
        			}
        		}
        	},
        	"videotypepanel":{
        		"afterrender":function(){
        			//重置 视频链接 的默认 所属视频
        			me.getVideoTypeLeafStore().getProxy().extraParams["typeParent"]=-1;
        		}
        	}
        });
    }
});