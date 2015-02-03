Ext.define("dy.view.video.VideoToplineEditWindow", {
	extend : "dy.view.BaseEditWindow",
	alias : 'widget.videotoplineeditwindow',
	initComponent : function() {
		var me = this;
		Ext.apply(me, {
			formItems : [ {
				xtype : 'textfield',
				fieldLabel : 'topId',
				name : 'topId',
				readOnly:true,
				readOnlyCls: 'x-item-disabled'
			}, {
				xtype : 'textfield',
				fieldLabel : 'dataId',
				name : 'dataId',
				allowBlank : false
			}, {
				xtype : 'textfield',
				fieldLabel : 'topType',
				name : 'topType',
				allowBlank : false
			}, {
				xtype : 'textfield',
				fieldLabel : 'priority',
				name : 'priority',
				allowBlank : false
			} ]
		});

		me.callParent();
	},
	listeners:{
		beforeshow:function(){
		}
	}
});