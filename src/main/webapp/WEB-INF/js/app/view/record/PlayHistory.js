Ext.define('dy.view.record.PlayHistory', {
	extend:'dy.view.BaseEditGrid',
	alias:'widget.playhistorygrid',
    store: 'VideoHistory',
	initComponent: function () {
		var me=this;
		
		Ext.apply(me,{
			columns:[
				{text:'历史编号',dataIndex:'historyId',flex:1},
                {text:'用户编号',dataIndex:'personId',flex:1,hidden:true},
                {text:'用户名',dataIndex:'userName',flex:1},
                {text:'视频编号',dataIndex:'videoId',flex:1,hidden:true},
                {text:'视频名称',dataIndex:'videoTitle',flex:1},
				{text:'创建时间',dataIndex:'createTime',flex:1,renderer:function(value){
					return Ext.Date.format(new Date(value),"Y-m-d");
				}}
			],
			tbar: {
                xtype: "pagingtoolbar",
                store: "VideoHistory",
                displayInfo: true,
                items: [
                    "-",
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