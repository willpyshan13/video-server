Ext.define('dy.view.video.VideoInfoGrid', {
	extend:'dy.view.BaseEditGrid',
	alias:'widget.videoinfogrid',
	editWindowType:'videoeditwindow',
    store: 'Video',
	initComponent: function () {
		var me=this;
		
		var storeRenderer=dy.util.Renderer.storeRenderer;
		var yesOrNo=function(v){if(v)return '是';return '否';};
		Ext.apply(me,{
			columns:[
				{text:'视频编号',dataIndex:'videoId',flex:1},
				{text:'视频标题',dataIndex:'videoTitle',flex:1},
				{text:'轮播图',hidden:true,dataIndex:'videoWheelPicUrl',flex:1},
				{text:'预览图',dataIndex:'videoPreviewPicUrl',align:'center',flex:1,hidden:true},
				{text:'简介',dataIndex:'videoBrief',flex:1,hidden:true},
				{text:'详情',hidden:true,dataIndex:'videoDesc',flex:1},
				{text:'导演',dataIndex:'videoDirector',flex:1},
				{text:'编剧',dataIndex:'videoScriptwriter',flex:1,hidden:true},
				{text:'演员',dataIndex:'videoActor',flex:1},
				{text:'区域',dataIndex:'videoRegion',flex:1,hidden:true},
				//renderer:storeRenderer(Ext.getStore('combo.VideoYear'),'typeName','typeDesc')
				{text:'年份',dataIndex:'videoYear',flex:1,hidden:true},
				{text:'轮播',dataIndex:'wheel',sortable:false,flex:1,renderer:yesOrNo},
				{text:'热点',dataIndex:'hot',sortable:false,flex:1,renderer:yesOrNo},
				{text:'状态',dataIndex:'status',flex:1,renderer:storeRenderer(Ext.getStore('combo.Status'),'id','text')},
				{text:'创建时间',dataIndex:'createTime',flex:1,renderer:function(value){
				    	return Ext.Date.format(new Date(value),"Y-m-d");
				}},
				{text:'labels',dataIndex:'labels',flex:1,hidden:true}
			],
			dockedItems:[{
				dock:'top',
				xtype:'toolbar',
				items:[{
					xtype:'form',
					layout:'hbox',
					bodyStyle: "background:#DFE9F6",
					border:false,
					items:[{
							width:120,
							xtype:'numberfield',
							fieldLabel:'视频编号',
							labelWidth:55,
							name:'videoId'
						},{
							width:150,
							xtype:'textfield',
							fieldLabel:'关键字',
							labelWidth:45,
							name:'keyword'
						},{
							width:130,
							xtype:'combo',
							fieldLabel:'视频状态',
							labelWidth:60,
							editable:false,
							store:"combo.Status",
							valueField:'id',
							name:'status'
						},{
							width:100,
							xtype:'combo',
							fieldLabel:'标签',
							labelWidth:30,
							editable:false,
							store:'combo.VideoLabel',
							valueField:'typeId',
							displayField:'typeDesc',
							name:'videoLabel'
						},{
							width:100,
							xtype:'combo',
							fieldLabel:'区域',
							labelWidth:30,
							editable:false,
							store:'combo.VideoRegion',
							valueField:'typeId',
							displayField:'typeDesc',
							name:'videoRegion'
						},{
							width:100,
							xtype:'combo',
							fieldLabel:'年份',
							labelWidth:30,
							editable:false,
							store:'combo.VideoYear',
							valueField:'typeId',
							displayField:'typeDesc',
							name:'videoYear'
						}]
				},'',{
		            iconCls: 'Zoom',
		            text: '查询',
		            action: 'search',
		            handler:function(btn){
		            	var form=btn.up('toolbar').down('form').getForm();
		            	var values=form.getValues();
		            	var store=me.getStore();
		            	store.clearFilter(true);
		            	var filters=[];
		            	for(var i in values){
		            		if(!Ext.isEmpty(values[i])){
		            			filters.push({
                                    property:i,
                                    value:values[i]
                                });
		            		}
		            	}
		            	store.filter(filters);
		            }
		        },{
		            iconCls: 'Stop',
		            text: '重置',
		            action: 'reset',
		            handler:function(btn){
		            	btn.up('toolbar').down('form').getForm().reset();
		            	me.getStore().clearFilter(false);
		            }
		        }]
			},{
				dock:'top',
                xtype: "pagingtoolbar",
                store: "Video",
                displayInfo: true,
                items: [
                    "-",
                    {
                    	text:"增加",
                    	iconCls:'Televisionadd',
                    	action:'add',
                    	scope:me,
                    	handler:me.openAdd
                    },
                    {
                        text: "编辑",
                        iconCls:'Televisionin',
                        disabled: true,
                        action: "edit",
                        scope:me,
                        handler:me.openEdit
                    },
                    {
                        text: "删除",
                        iconCls:'Televisiondelete',
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
                    },'-',
                    {
                    	text:'轮播',
                    	iconCls:'Awardstaradd',
                    	action:'wheel',
                    	disabled: true,
                    	menu:[{
                    		text:'设置为轮播',
                    		handler:function(m){
                    			var rs=me.getSelectionModel().getSelection();
                    			if(rs){
                    				var record=rs[0];
                    				if(!me.getStore().getById(record.getId()).get('wheel')){
                    					Ext.Ajax.request({
                    						url:'ext/add/topline/wheel',
                    						rawData:Ext.encode(record.data),
                    						success:function(){
                    							me.getStore().reload();
                    							Ext.Msg.alert('提示',"设置成功!");
                    						},
                    						failure:function(){
                    							Ext.Msg.alert('提示',"出错!");
                    						}
                    					});
                    				}
                    			}
                    		
                    		}
                    	},{
                    		text:'取消轮播',
                    		handler:function(m){
                    			var rs=me.getSelectionModel().getSelection();
                    			if(rs){
                    				var record=rs[0];
                    				if(me.getStore().getById(record.getId()).get('wheel')){
                    					Ext.Ajax.request({
                    						url:'ext/remove/topline/wheel',
                    						rawData:Ext.encode(record.data),
                    						success:function(){
                    							me.getStore().reload();
                    							Ext.Msg.alert('提示',"取消成功!");
                    						},
                    						failure:function(){
                    							Ext.Msg.alert('提示',"出错!");
                    						}
                    					});
                    				}
                    			}
                    		
                    		}
                    	}]
                    },{
                    	text:'热点',
                    	action:'hot',
                    	iconCls:'Heart',
                    	disabled: true,
                    	menu:[{
                    		text:'设置为热点',
                    		handler:function(m){
                    			var rs=me.getSelectionModel().getSelection();
                    			if(rs){
                    				var record=rs[0];
                    				if(!me.getStore().getById(record.getId()).get('hot')){
                    					Ext.Ajax.request({
                    						url:'ext/add/topline/hot',
                    						rawData:Ext.encode(record.data),
                    						success:function(){
                    							me.getStore().reload();
                    							Ext.Msg.alert('提示',"设置成功!");
                    						},
                    						failure:function(){
                    							Ext.Msg.alert('提示',"出错!");
                    						}
                    					});
                    				}
                    			}
                    		
                    		}
                    	},{
                    		text:'取消热点',
                    		handler:function(m){
                    			var rs=me.getSelectionModel().getSelection();
                    			if(rs){
                    				var record=rs[0];
                    				if(me.getStore().getById(record.getId()).get('hot')){
                    					Ext.Ajax.request({
                    						url:'ext/remove/topline/hot',
                    						rawData:Ext.encode(record.data),
                    						success:function(){
                    							me.getStore().reload();
                    							Ext.Msg.alert('提示',"取消成功!");
                    						},
                    						failure:function(){
                    							Ext.Msg.alert('提示',"出错!");
                    						}
                    					});
                    				}
                    			}
                    		
                    		}
                    	}]
                    },{
                    	text:'设置标签',
                    	action:'label',
                    	disabled: true
                    }
                ]
            
			}],
            listeners:{
            	"selectionchange":function(grid,rs){
	               	 var length = rs.length;
	                 me.down('button[action=remove]').setDisabled(length < 1);
	                 me.down('button[action=edit]').setDisabled(length != 1);
	                 me.down('button[action=wheel]').setDisabled(length != 1);
	                 me.down('button[action=hot]').setDisabled(length != 1);
	                 me.down('button[action=label]').setDisabled(length != 1);
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