Ext.define('dy.view.record.PlayCount', {
	alias:'widget.playcountgrid',
	extend:'dy.view.BaseEditGrid',
	editWindowType:'playeditwindow',
    title: "播放统计",
    store: 'PlayCount',
	initComponent: function () {
		var me=this;
		
		Ext.apply(me,{
			columns:[
                {text:'播放编号',dataIndex:'hotId',flex:1,hidden:true},
                {text:'视频编号',dataIndex:'videoId',flex:1,hidden:true},
                {text:'视频名称',dataIndex:'videoTitle',flex:1},
                {text:'今日播放次数',dataIndex:'todayCount',flex:1},
                {text:'本周播放次数',dataIndex:'weekCount',flex:1},
                {text:'本月播放次数',dataIndex:'monthCount',flex:1},
                {text:'历史播放次数',dataIndex:'count',flex:1},
				{text:'最近一次播放时间',dataIndex:'createTime',flex:1,renderer:function(value){
				    	return Ext.Date.format(new Date(value),"Y-m-d");
				}}
			],
			
			tbar: {
                xtype: "pagingtoolbar",
                store: "PlayCount",
                displayInfo: true,
                items: [
                    "-",
                    {
                    	text:"增加",
                    	action:'add',
                    	scope:me,
                    	handler:me.openAdd
                    },
                    {
                        text: "编辑",
                        disabled: true,
                        action: "edit",
                        scope:me,
                        handler:me.openEdit
                    },
                    {
                        text: "删除",
                        disabled: true,
                        scope:me,
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
	            	me.getStore().load();
	            }
            }
		});
		me.callParent();
	}
});