Ext.define("dy.view.person.CollectionEditWindow", {
	extend : "dy.view.BaseEditWindow",
	alias : 'widget.collectioneditwindow',
	initComponent : function() {
		var me = this;
		Ext.apply(me, {
			formItems : [ {xtype:'textfield',fieldLabel:'收藏编号',name:'collectionId',readOnly:true,readOnlyCls: 'x-item-disabled'},
			              {xtype:'textfield',fieldLabel:'人物编号',name:'personId',allowBlank: false},
			              {xtype:'textfield',fieldLabel:'视频编号',name:'videoId',allowBlank: false},
			              {xtype : 'textfield',
			  				fieldLabel : '创建时间',
							name : 'createTime',
							readOnly : true,
							readOnlyCls : 'x-item-disabled',renderer:function(value){
						    	return Ext.Date.format(new Date(value),"Y-m-d");
							}} ]
		});

		me.callParent();
	},
	listeners : {}
});