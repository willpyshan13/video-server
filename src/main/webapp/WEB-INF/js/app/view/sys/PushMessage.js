Ext.define('dy.view.sys.PushMessage', {
	extend:'Ext.panel.Panel',
	alias:'widget.pushmessagegrid',
    title: '消息推送',
    store: 'PushMessage',
    loadUrl:'ext/loadOne/push',
	updateUrl:"ext/update/push",
	addUrl:"ext/add/push",
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
                    {xtype:'textfield',fieldLabel:'推送编号',name:'pushMessageId',hidden:true,readOnly:true,readOnlyCls: 'x-item-disabled',width:400},
                    {xtype:'textfield',fieldLabel:'消息推送标题',name:'messageTitle',allowBlank: false,width:400},
                    {xtype:'textarea',fieldLabel:'消息推送内容',name:'messageContent',allowBlank: false,width:400,height:300},
                    {xtype:'textfield',fieldLabel:'最近一次推送时间',name:'pushTime',readOnly:true,width:400}
                ],
    	        buttons:[{
    	        	text : "推送消息",
					handler : function(button) {
						var form = button.up('form');
                        var submitUrl=me.action=="add"?me.addUrl:me.updateUrl;
						form.submit({
							url : submitUrl,
							param : form.getValues(),
							waitMsg:'信息推送中....',
							jsonSubmit:true,
							success : function() {
								Ext.Msg.alert("信息推送成功！");
							},
							failure : function() {
//                                form.reset();
								Ext.Msg.alert("信息推送失败！");
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
	            	    url: 'ext/loadOne/push',
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