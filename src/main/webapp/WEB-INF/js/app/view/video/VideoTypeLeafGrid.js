Ext.define('dy.view.video.VideoTypeLeafGrid', {
	alias:'widget.videotypeleafgrid',
	editWindowType:'videotypeeditwindow',
	extend:'dy.view.BaseEditGrid',
    title: "二级视频分类列表",
    store: 'VideoTypeLeaf',
	initComponent: function () {
		var me=this;
		
    	me.getAddParams=function(){
    		return {
    			typeParent:me.getStore().getProxy().extraParams['typeParent']
    		};
    	};
		
		Ext.apply(me,{
			columns:[
				{text:'分类编号',dataIndex:'typeId',flex:1},
				{text:'分类父类',dataIndex:'typeParent',flex:1,hidden:true},
				{text:'分类名称',dataIndex:'typeName',flex:1 },
				{text:'分类描述',dataIndex:'typeDesc',flex:1 }
			],
			tbar: [
					' ',
					{
						  iconCls:'x-tbar-loading',
						  handler:function(){
							  me.getStore().reload();
						  }
					},
					'-',
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
               ],
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