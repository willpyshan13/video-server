Ext.define("dy.view.sys.AdminAddEditWindow", {
	extend : "dy.view.BaseEditWindow",
	alias : 'widget.adminaddeditwindow',
	initComponent : function() {
		var me = this;
		Ext.apply(me, {
			formItems : [
                {xtype:'textfield',fieldLabel:'管理员编号',name:'adminId',readOnly:true,readOnlyCls: 'x-item-disabled'},
                {xtype:'textfield',fieldLabel:'管理员用户名',name:'adminName',allowBlank: false},
                {xtype:'textfield',fieldLabel:'管理员密码',name:'adminPass',allowBlank: false},
                {
                    xtype: 'combo',
                    fieldLabel: '角色',
                    name: 'role',
                    allowBlank: false,
                    valueField: 'text',
                    editable: false,
                    store: Ext.create("dy.store.combo.Role")},
                {
                    xtype: 'combo',
                    fieldLabel: '状态',
                    name: 'status',
                    allowBlank: false,
                    valueField: 'id',
                    editable: false,
                    store: Ext.create("dy.store.combo.Status")},
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