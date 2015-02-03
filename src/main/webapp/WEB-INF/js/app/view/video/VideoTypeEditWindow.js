Ext.define("dy.view.video.VideoTypeEditWindow", {
	extend : "dy.view.BaseEditWindow",
	alias : 'widget.videotypeeditwindow',
	initComponent : function() {
		var me = this;
		Ext.apply(me, {
			formItems : [ {
				xtype : 'textfield',
				fieldLabel : '编号',
				name : 'typeId',
				readOnly : true,
				readOnlyCls : 'x-item-disabled'
			}, {
				xtype : 'textfield',
				fieldLabel : '父类型编号',
				name : 'typeParent',
				readOnly : true,
				readOnlyCls : 'x-item-disabled'
			}, {
				xtype : 'textfield',
				fieldLabel : '标记',
				name : 'typeName',
				allowBlank : false
			}, {
				xtype : 'textfield',
				fieldLabel : '描述',
				name : 'typeDesc',
				allowBlank : false
			} ]
		});

		me.callParent();
	}
});