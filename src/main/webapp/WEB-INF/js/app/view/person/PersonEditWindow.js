Ext.define("dy.view.PersonEditWindow", {
    extend: "Ext.window.Window",
    alias: 'widget.personeditwindow',
    closeAction: "hide",
    modal: true,
    initComponent: function () {
    	var me=this;
    	Ext.apply(me,{
    		items: [{
    	        xtype: "form",
    	        bodyPadding: 5,
    	        bodyStyle: "background:#DFE9F6",
    	        border: false,
    	        trackResetOnLoad: true,
    	        waitTitle: "请等待...",
    	        defaultType:'textfield',
    	        items: [
						{xtype:'textfield',fieldLabel:'用户编号',name:'personId',readOnly:true,readOnlyCls: 'x-item-disabled'},
						{xtype:'textfield',fieldLabel:'用户名',name:'userName',allowBlank: false},
						{xtype:'textfield',fieldLabel:'手机编号(备用)',name:'mobileSerial',allowBlank: false},
						{xtype:'textfield',fieldLabel:'电话号码',name:'mobileNumber',allowBlank: false},
						{xtype:'combo',fieldLabel:'性别',name:'gender',valueField:'id',allowBlank: false,store:Ext.create("dy.store.combo.Gender")},
						{xtype:'textfield',fieldLabel:'人物描述',name:'personDesc',allowBlank: false},
						{xtype:'textfield',fieldLabel:'生日',name:'birthday',allowBlank: false},
						{xtype:'textfield',fieldLabel:'照片地址',name:'photoUrl',readOnly:true,readOnlyCls: 'x-item-disabled'},
						{xtype:'textfield',fieldLabel:'状态码',name:'status',allowBlank: false},
						{xtype:'textfield',fieldLabel:'创建时间',name:'createTime',readOnly:true,readOnlyCls: 'x-item-disabled'}
    	                ],
    	    dockedItems: [{
    	        xtype: 'toolbar',
    	        dock: "bottom",
    	        ui: "footer",
    	        layout: {
    	            pack: "center"
    	        },
    	        items: [{
    	            text: "保存",
    	            handler: function (button) {
    	                var win=button.up('window'),
    	                form = win.down("form"),
    	                f = form.getForm();
    	                if (f.isValid() && f.isDirty()) {
    	                    var record=form.getRecord(),
    	                    	values=form.getValues();
    	                    record.set(values);
    	                    var store=me.store;
    	                    if(!record.getId()){
    	                    	store.add(record);
    	                    }
    	                    store.sync({
    	                    	success: function () {
    	                    		store.load();
    	                            win.close();
    	                        },
    	                        failure: function (response, options) {
    	                            Ext.Msg.alert("操作失败", "操作出错 " + response.exceptions[0].error.statusText);
    	                            store.rejectChanges();
    	                        }
    	                    });
    	                } else {
    	                    if (!f.isValid()) {
    	                        Ext.Msg.alert("修改", "请填写完整");
    	                    } else if (!f.isDirty()) {
    	                        Ext.Msg.alert("修改", "数据未修改");
    	                    }
    	                }
    	            }
    	        }, {
    	            text: "重置",
    	            handler: function () {
    	                this.up("form").getForm().reset();
    	            }
    	        }]
    	    }]
    	}]
    	});
    	
    	me.callParent();
    },
listeners: {
    beforeshow:function(){
    	var me=this;
    	var f=me.down("form").getForm();
    	if(f.getRecord().getId()){
    		this.setTitle("编辑");
    		f.findField(f.getRecord().idProperty).show();
    	}else{
    		this.setTitle("增加");
    		f.findField(f.getRecord().idProperty).hide();
    	}
    	return true;
    }
}
});