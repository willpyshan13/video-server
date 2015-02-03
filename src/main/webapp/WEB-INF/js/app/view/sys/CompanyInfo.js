Ext.define('dy.view.sys.CompanyInfo', {
	extend:'Ext.panel.Panel',
	alias:'widget.companuinfogrid',
    title: '公司信息信息',
    store: 'CompanyInfo',
    loadUrl:'ext/loadone/companyinfo',
	updateUrl:"ext/update/companyinfo",
	addUrl:"ext/add/companyinfo",
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
                    {xtype:'textfield',fieldLabel:'公司信息编号',name:'infoId',readOnly:true,readOnlyCls: 'x-item-disabled',hidden:true},
                    {xtype:'textfield',fieldLabel:'公司信息标题',name:'infoTitle',allowBlank: false},
                    {xtype:'textfield',fieldLabel:'公司网址',name:'companyWebSiteUrl',allowBlank: false},
                    {
                        xtype:'fieldcontainer',
                        layout:'hbox',
                        items:[{
                            xtype : 'textfield',
                            fieldLabel : '公司Logo',
                            name : 'infoLogoUrl',
                            allowBlank : false
                        },{
                            xtype:'button',text:'上传',handler:function(){
                                dy.UB.openUpload('companyinfo',function(action){
                                    me.down('#InfoLogoUrlShow').setSrc(action.result.data);
                                    me.down('form').getForm().findField("infoLogoUrl").setValue(action.result.data);
                                });
                            }
                        }]
                    }
                    ,{
                        xtype:'fieldcontainer',
                        padding:"3 0",
                        items:[{
                            xtype:'image',
                            fieldLabel:'图片展示',
                            padding:'5 0 5 100',
                            height:200,
                            id:'InfoLogoUrlShow'
                        }]

                    }
                    ,
                    {xtype:'textarea',fieldLabel:'公司信息内容',name:'infoContent',allowBlank: false,width:400,height:250},
                    {xtype:'textfield',fieldLabel:'更新时间',name:'createTime',allowBlank: false,readOnly:true}
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
//                                form.reset();
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
	            	    url: 'ext/loadone/companyinfo',
	            	    success: function(response, opts) {
	            	        obj = Ext.decode(response.responseText);
	            	        me.down('form').getForm().setValues(obj);
                            var InfoLogoUrlShow=obj['infoLogoUrl'];
                            if(!InfoLogoUrlShow || InfoLogoUrlShow.match(/\s+/)){
                                me.down('#InfoLogoUrlShow').setSrc("js/app/resources/default/default-preview.png");
                            }else{
                                me.down('#InfoLogoUrlShow').setSrc(InfoLogoUrlShow);
                            }
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