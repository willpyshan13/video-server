Ext.define('dy.view.sys.UpdateInfo', {
	extend:'Ext.panel.Panel',
	alias:'widget.updateinfogrid',
    title: '版本更新信息',
    store: 'UpdateInfo',
    loadUrl:'ext/loadone/update',
	updateUrl:"ext/update/update",
	addUrl:"ext/update/update",
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
                    {xtype:'textfield',fieldLabel:'更新编号',name:'updateId',readOnly:true,readOnlyCls: 'x-item-disabled',width:455,hidden:true},
                    {xtype:'textfield',fieldLabel:'Android版本',name:'androidVersion',allowBlank: false,width:455},
                    {
                        xtype: 'container',
                        padding: "3 0",
                        layout: 'hbox',
                        defaults: {
                            labelAlign: 'right'
                        },
                        items: [
                            {xtype:'textfield',fieldLabel:'Android下载地址',name:'androidUpdateUrl',allowBlank: false,width:455,paddingRight:10},
                            {xtype: 'button', text: '上传', handler: function () {
                                dy.UB.openUpload('android', function (action) {
                                    me.down('form').getForm().findField("androidUpdateUrl").setValue(action.result.data);
                                });
                            }}
                        ]
                    },
                    {xtype:'textarea',fieldLabel:'Android版本描述',name:'androidVersionDesc',allowBlank: false,width:455},
                    {xtype:'textfield',fieldLabel:'IOS版本号',name:'iosVersion',allowBlank: false,width:455},
                    {xtype:'textfield',fieldLabel:'Ios更新地址',name:'iosUpdateUrl',allowBlank: false,width:455},
                    {xtype:'textarea',fieldLabel:'IOS版本描述',name:'iosVersionDesc',allowBlank: false,width:455},
                    {xtype:'textfield',fieldLabel:'更新时间',name:'updateTime',readOnly:true,width:455}
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
	            	    url: 'ext/loadone/update',
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