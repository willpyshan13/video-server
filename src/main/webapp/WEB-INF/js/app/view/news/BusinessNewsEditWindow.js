Ext.define("dy.view.news.BusinessNewsEditWindow", {
	alias : 'widget.businessnewseditwindow',
	extend: "dy.view.BaseLoadEditWindow",
	loadUrl:'ext/loadOne/businessNews',
	updateUrl:"ext/update/businessNews",
	addUrl:"ext/add/businessNews",
	initComponent : function() {
		var me = this;
		Ext.apply(me, {
			formItems : [ {
				xtype : 'textfield',
				fieldLabel : '新闻编号',
				name : 'newsId',
				readOnly : true,
				readOnlyCls : 'x-item-disabled'
			}, {
				xtype : 'textfield',
				fieldLabel : '新闻标题',
				name : 'newsTitle',
				allowBlank : false
			}, {
				xtype:'fieldcontainer',
				layout:'hbox',
				items:[{
					xtype : 'textfield',
					fieldLabel : '新闻轮播图片',
					name : 'newsWheelPicUrl',
					allowBlank : false
				},{
					xtype:'button',text:'上传',handler:function(){
				    	   dy.UB.openUpload('news',function(action){
				    		   	me.down('#newsWheelPicShow').setSrc(action.result.data);
	                            me.down('form').getForm().findField("newsWheelPicUrl").setValue(action.result.data);
				    	   });
				       }
				}]
			},{
				xtype:'fieldcontainer',
				padding:"3 0",
				items:[{
					xtype:'image',
					fieldLabel:'图片展示',
					padding:'5 0 5 100',
					height:200,
					id:'newsWheelPicShow'
				}]

			}, {
				xtype : 'htmleditor',
				fieldLabel : '新闻内容',
				name : 'newsContent',
				allowBlank : false
			}, {
				xtype : 'textfield',
				fieldLabel : '涉及视频标题',
				name : 'videoTitle'
			},{
				xtype : 'combo',
				fieldLabel : '状态',
				name : 'status',
				store : Ext.create("dy.store.combo.Status"),
				valueField : 'id'
			}, {
				xtype : 'textfield',
				fieldLabel : '创建时间',
				name : 'createTime',
				readOnly : true,
				readOnlyCls : 'x-item-disabled',renderer:function(value){
				    	return Ext.Date.format(new Date(value),"Y-m-d");
				}
			} ]
		});

		me.callParent();
	},
	listeners : {
		"beforeshow":function(){
			var me=this;
			if(me.action=="add"){
				me.down('#newsWheelPicShow').setSrc("js/app/resources/default/default-preview.png");
			}
		},
		"afterload":function(action){
			var me=this;
	    	var record=me.down("form").getValues();

	    	var previewUrl=record['newsWheelPicUrl'];
	    	if(!previewUrl || previewUrl.match(/\s+/)){
	    		me.down('#newsWheelPicShow').setSrc("js/app/resources/default/default-preview.png");
	    	}else{
	    		me.down('#newsWheelPicShow').setSrc(previewUrl);
	    	}

	    	return true;
		}
	}
});