Ext.define("dy.view.BaseEditWindow", {
    extend: "Ext.window.Window",
    closeAction: "hide",
    border:false,
    autoScroll:true,
    resizable:false,
    modal: true,
    initComponent: function () {
    	var me=this;
    	var formItems=me.formItems||[];
    	Ext.apply(me,{
    		items: [{
    	        xtype: "form",
    	        waitTitle: "请等待...",
    	        trackResetOnLoad:true,
    	        bodyPadding: 5,
    	        border:false,
    	        bodyStyle: "background:#DFE9F6",
    	        fieldDefaults: {
    	            labelAlign : 'right'
    	        },
    	        items:formItems,
    	        buttons:[{
    	            text: "保存",
    	            handler: function (button) {
    	            	var win=button.up('window'),
    	            		form = win.down("form");
    	                if (form.isValid() && form.isDirty()) {
    	                	var store=me.store;
    	                    var record=form.getRecord(),
    	                    	values=form.getValues();
    	                    if(!record.getId()){
    	                    	record.set(values);
    	                    	store.add(record);
    	                    }else{
    	                    	store.getById(record.getId()).set(values);
    	                    }
    	                    store.sync({
    	                    	success: function () {
    	                            win.close();
    	                        },
    	                        callback:function(){
    	                        },
    	                        failure: function (response, options) {
    	                            Ext.Msg.alert("操作失败", "操作出错 " + response.exceptions[0].error.statusText);
    	                            store.rejectChanges();
    	                        }
    	                    });
    	                } else {
    	                    if (!form.isValid()) {
    	                        Ext.Msg.alert("修改", "请填写完整");
    	                    } else if (!form.isDirty()) {
    	                        Ext.Msg.alert("修改", "数据未修改");
    	                    }
    	                }
    	            }
    	        }, {
    	            text: "重置",
    	            handler: function () {
    	                this.up('form').getForm().reset();
    	            }
    	        }]
    	}]
    	});
    	
    	me.on("beforeshow",function(){
        	var f=me.down("form").getForm();
//        	f.reset();
        	if(f.getRecord().getId()){
        		this.setTitle("编辑");
        		f.findField(f.getRecord().idProperty).show();
        	}else{
        		this.setTitle("增加");
        		f.findField(f.getRecord().idProperty).hide();
        	}
        	return true;
        },me);
    	
    	me.callParent();
    }
});