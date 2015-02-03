Ext.define('dy.view.record.SearchHistory', {
	extend:'dy.view.BaseEditGrid',
	alias:'widget.searchhistorygrid',
    store: 'Search',
	initComponent: function () {
		var me=this;
		
		Ext.apply(me,{
			columns:[
				{text:'搜索编号',dataIndex:'searchId',flex:1},
				{text:'搜索关键词',dataIndex:'searchKey',flex:1},
				{text:'搜索次数统计',dataIndex:'searchCount',flex:1},
				{text:'创建时间',dataIndex:'createTime',flex:1,renderer:function(value){
					return Ext.Date.format(new Date(value),"Y-m-d");
				}}
			],
			tbar: {
                xtype: "pagingtoolbar",
                store: "Search",
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