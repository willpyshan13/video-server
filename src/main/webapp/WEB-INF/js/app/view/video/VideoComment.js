Ext.define('dy.view.video.VideoComment', {
	alias:'widget.videocommentgrid',
	extend:'dy.view.BaseEditGrid',
	editWindowType:'videocommenteditwindow',
    title: "评论列表",
    store: 'Comment',
	initComponent: function () {
		var me=this;
		
		Ext.apply(me,{
			columns:[
				{text:'评论编号',dataIndex:'commentId',flex:1},
				{text:'人物编号',dataIndex:'personId',flex:1,hidden:true},
                {text:'人物名称',dataIndex:'userName',flex:1},
				{text:'视频编号',dataIndex:'videoId',flex:1,hidden:true},
				{text:'视频标题',dataIndex:'videoTitle',flex:1},
				{text:'评论内容',dataIndex:'commentContent',flex:1},
				{text:'状态',dataIndex:'status',flex:1,sortable:false,
					renderer:dy.util.Renderer.storeRenderer(Ext.getStore('combo.Status'),'id','text')
				},
				{text:'创建时间',dataIndex:'createTime',flex:1,renderer:function(value){
				    	return Ext.Date.format(new Date(value),"Y-m-d");
				}}
			],
			dockedItems:[{
				xtype:'toolbar',
				dock:'top',
				items:[{
					xtype:'form',
					layout:'hbox',
					bodyStyle: "background:#DFE9F6",
					border:false,
					items:[{
						xtype:'textfield',
						fieldLabel:'关键字',
						labelWidth:50,
						name:'commentContent'
					},{
						xtype:'numberfield',
						fieldLabel:'视频编号',
						labelWidth:60,
						width:120,
						name:'videoId'
					},{
						xtype:'numberfield',
						fieldLabel:'用户编号',
						labelWidth:60,
						width:120,
						name:'personId'
					},{
						xtype:'combo',
						width:100,
						fieldLabel:'状态',
						labelWidth:30,
						name:'status',
						store:Ext.create("dy.store.combo.Status"),
						valueField:'id'
					}]
				},' ',{
					xtype:'button',
					iconCls:'Zoom',
					text:'过滤',
					action:'filter',
					handler:function(btn){
						var form=btn.up('toolbar').down('form').getForm();
		            	var values=form.getValues();
		            	var store=me.getStore();
		            	store.clearFilter(true);
		            	var filters=[];
		            	var i=null;
		            	for(i in values){
		            		if(!Ext.isEmpty(values[i])){
		            			filters.push({property:i,value:values[i]});
		            		}
		            	}
		            	store.filter(filters);
					}
				},{
					xtype:'button',
					iconCls:'Stop',
					text:'清除过滤',
					action:'resetFilter',
					handler:function(btn){
						btn.up('toolbar').down('form').getForm().reset();
		            	me.getStore().clearFilter(false);
					}
				}]
			}],
			tbar: {
                xtype: "pagingtoolbar",
                store: "Comment",
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