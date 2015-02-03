Ext.define('dy.view.video.video.VideoUrlGrid', {
	extend:'dy.view.BaseEditGrid',
	alias:'widget.videourlgrid',
	editWindowType:'videourleditwindow',
    store: 'VideoUrl',
	initComponent: function () {
		var me=this;
		
    	me.getAddParams=function(){
    		return {
    			videoId:me.getStore().getProxy().extraParams['videoId']
    		};
    	};
		
		Ext.apply(me,{
			columns:[
				{text:'编号',dataIndex:'videoUrlId',flex:1},
				{text:'所属视频',dataIndex:'videoId',flex:1,hidden:true},
				{text:'链接序号',dataIndex:'videoUrlIndex',flex:1},
				{text:'链接描述',dataIndex:'videoUrlDesc',flex:1},
				{text:'视频来源',dataIndex:'videoPlayUrl',flex:1},
				{text:'视频地址',dataIndex:'videoWebUrl',flex:1},
				{text:'视频格式',dataIndex:'videoFormat',flex:1,hidden:true},
				{text:'状态',dataIndex:'status',flex:1}
			],
			bbar: {
                xtype: "pagingtoolbar",
                store: "VideoUrl",
                displayInfo: true,
                items: [
                    "-",
                    {
                    	text:"增加",
                    	action:'add',
                    	handler:me.openAdd,
                    	scope:me
                    },
                    {
                        text: "编辑",
                        disabled: true,
                        action: "edit",
                        handler:me.openEdit,
                        scope:me
                    },
                    {
                        text: "删除",
                        disabled: true,
                        action: "remove",
                        handler:function(){
                        	var store=me.getStore();
                        	var rs=me.getSelectionModel().getSelection();
                        	
                        	 var content = ["确定删除以下记录? "];
                             for (var i = 0; i < rs.length; i++) {
                                 content.push(rs[i].getId());
                             }
                             Ext.Msg.confirm("删除记录", content.join("<br/>"), function (btn) {
                                 if (btn == "yes") {
                                     store.remove(rs);
                                     store.sync(me.editOptionCallback(me));
                                 }
                             });
                        }
                    }
                ]
            },
            listeners:{
            	"selectionchange":function(grid,rs){
	               	 var length = rs.length;
	                 me.down('button[action=remove]').setDisabled(length < 1);
	                 me.down('button[action=edit]').setDisabled(length != 1);
	            },
            	"itemdblclick":me.openEdit,
	            "afterrender":function(){
//	            	me.getStore().load();
	            }
            }
		});
		me.callParent();
	}
});