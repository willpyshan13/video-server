Ext.define('dy.view.sys.AdminAdd', {
	alias:'widget.adminaddgrid',
	extend:'dy.view.BaseEditGrid',
	editWindowType:'adminaddeditwindow',
    title: "添加管理员",
    store: 'AdminAdd',
	initComponent: function () {
		var me=this;
		
		Ext.apply(me,{
			columns:[
                {text:'管理员编号',dataIndex:'adminId',flex:1},
                {text:'管理员用户名',dataIndex:'adminName',flex:1},
                {text:'管理员密码',dataIndex:'adminPass',flex:1},
                {text:'角色',dataIndex:'role',flex:1},
				{text:'创建时间',dataIndex:'createTime',flex:1,renderer:function(value){
				    	return Ext.Date.format(new Date(value),"Y-m-d");
				}}
			],
			
			tbar: {
                xtype: "pagingtoolbar",
                store: "AdminAdd",
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