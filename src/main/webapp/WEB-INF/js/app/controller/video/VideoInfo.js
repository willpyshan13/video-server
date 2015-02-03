Ext.define('dy.controller.video.VideoInfo', {
    extend: 'Ext.app.Controller',
    views:['video.VideoInfoGrid','video.VideoUrlGrid','video.VideoEditWindow','video.VideoUrlEditWindow'],
//    models:['Video','VideoUrl'], //貌似models 没有用处
    stores:['Video','VideoUrl',"combo.VideoYear","combo.VideoRegion","combo.VideoLabel","combo.Status","VideoWheel"],
    refs:[
          {
        	  ref:'VideoUrlGrid',
        	  selector:'videourlgrid'
          },{
        	  ref:'VideoInfoGrid',
        	  selector:'videoinfogrid'
          }
         ],
    init: function () {
    	var me=this;
        me.control({
        	"videoinfogrid":{
        		"selectionchange":function(model, selected, eOpts){
        			// 选择的视频改变时,更新下属视频链接列表
        			if(selected.length==1){
        				var record=selected[0];
        				var videoId=record.get('videoId');
        				me.getVideoUrlGrid().setTitle("播放链接: "+record.get('videoTitle'));
        				var urlStore = me.getVideoUrlStore();
        				Ext.apply(urlStore.getProxy().extraParams,{
        					videoId:videoId
        				});
        				urlStore.load();
        			}
        		}
        	},
        	"videourlgrid button[action=add]":{
        		"click":function(btn,e){
        			//当前没有选中视频时,不能添加 视频链接
        			if(me.getVideoUrlStore().getProxy().extraParams["videoId"]==-1){
        				Ext.Msg.alert("提示","请先选择所属视频!");
        				return false;
        			}
        		}
        	},
        	"videoinfopanel":{
        		"afterrender":function(){
        			//重置 视频链接 的默认 所属视频
        			me.getVideoUrlStore().getProxy().extraParams["videoId"]=-1;
        		}
        	}
        });
    }
});