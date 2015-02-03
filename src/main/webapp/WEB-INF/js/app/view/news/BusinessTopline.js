Ext.define('dy.view.news.BusinessTopline', {
	alias:'widget.businesstoplinegird',
	extend:'dy.view.BaseEditGrid',
	editWindowType:'businessnewseditwindow',
	title:'轮播新闻',
	initComponent: function () {
		var me=this;
		
		var mystore=Ext.create("Ext.data.Store",{
			model:'dy.model.BusinessNews',
			proxy : {
				type : 'ajax',
				url:'ext/load/topline/news',
				reader : {
					type : 'json',
					root : 'data'
				}
			}
		});
		Ext.apply(me,{
			store:mystore,
			columns:[
			        {text:'编号',dataIndex:'newsId',flex:1},
					{text:'新闻标题',dataIndex:'newsTitle',flex:1},
					{text:'涉及视频',dataIndex:'videoId',flex:1,hidden:true, sortable:false},
                {text: '状态', dataIndex: 'status', flex: 1,sortable:false,
                    renderer: dy.util.Renderer.storeRenderer(Ext.getStore('combo.Status'), 'id', 'text')
                },
					{text:'创建时间',dataIndex:'createTime',flex:1,renderer:function(value){
				    	return Ext.Date.format(new Date(value),"Y-m-d");
					}}
					],
			tbar:{
                xtype: "pagingtoolbar",
                store: mystore,
                displayInfo: true,
                items: [
                    "-",
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