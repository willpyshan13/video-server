Ext.define('dy.view.sys.SystemConfig', {
	extend:'Ext.panel.Panel',
	alias:'widget.systemconfiggrid',
    title: '邮箱设置',
    store: 'SystemConfig',
    loadUrl:'ext/loadone/config',
	updateUrl:"ext/update/config",
	addUrl:"ext/update/config",
	initComponent: function () {
		
		var me=this;
		var obj=null;
		Ext.apply(me, {
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
    	        items : [
                    {xtype:'textfield',fieldLabel:'配置编号',name:'configId',readOnly:true,readOnlyCls: 'x-item-disabled',width: 350,hidden:true},
                    {xtype:'textfield',vtype:'email',fieldLabel:'系统邮箱',name:'systemEmail',allowBlank: false,width: 350},
                    {xtype:'textfield',vtype:'alphanum',fieldLabel:'邮箱用户名',name:'systemUsername',allowBlank: false,width: 350},
                    {xtype:'textfield',vtype:'alphanum',fieldLabel:'邮箱密码',name:'systemPassword',allowBlank: false,width: 350},
                    {xtype:'textfield',fieldLabel:'邮箱主题',name:'emailSubject',allowBlank: false,width: 350},
                    {xtype:'textfield',fieldLabel:'邮箱服务器地址',name:'serverSmtp',allowBlank: false,width: 350},
                    {xtype:'textfield',fieldLabel:'邮箱服务器端口号',name:'serverPort',allowBlank: false,width: 350},
                ],
    	        buttons:[{
    	        	text : "保存",
					handler : function(button) {
						var form = button.up('form');
                        var submitUrl=me.action=="add"?me.addUrl:me.updateUrl;
						form.submit({
							url : submitUrl,
							param : form.getValues(),
							waitMsg:'处理中....',
							jsonSubmit:true,
							success : function() {
								Ext.Msg.alert("修改成功！");
							},
							failure : function() {
								this.up('form').getForm().reset();
								Ext.Msg.alert("失败");
							}
						});
					}
    	        }, {
    	            text: "重置",
    	            handler: function () {
    	            	me.down('form').getForm().setValues(obj);
    	            }
    	        }]
			}], 
			listeners:{
	            "afterrender":function(){
	            	Ext.Ajax.request({
	            	    url: 'ext/loadone/config',
	            	    success: function(response, opts) {
	            	        obj = Ext.decode(response.responseText);
	            	        me.down('form').getForm().setValues(obj);
	            	    },
	            	    failure: function(response, opts) {
	            	    }
	            	});
	            }
           }
		});
		
		me.callParent();
	}
});