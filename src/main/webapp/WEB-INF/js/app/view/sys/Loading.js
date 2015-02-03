Ext.define('dy.view.sys.Loading', {
	extend:'Ext.panel.Panel',
	alias:'widget.loadinggrid',
    title: '公司信息信息',
    store: 'Loading',
    loadUrl:'ext/loadone/loading',
	updateUrl:"ext/update/loading",
	addUrl:"ext/update/loading",
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
                    {xtype:'textfield',fieldLabel:'编号',name:'loadingId',readOnly:true,readOnlyCls: 'x-item-disabled',hidden:true},
                    {
                        xtype:'fieldcontainer',
                        layout:'hbox',
                        items:[{
                            xtype : 'textfield',
                            fieldLabel : 'App首页启动图',
                            name : 'loadingImgUrl',
                            allowBlank : false
                        },{
                            xtype:'button',text:'上传',handler:function(){
                                dy.UB.openUpload('loading',function(action){
                                    me.down('#loadingImgUrlShow').setSrc(action.result.data);
                                    me.down('form').getForm().findField("loadingImgUrl").setValue(action.result.data);
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
                            padding:'5 0 5 50',
                            height:400,
                            width:300,
                            id:'loadingImgUrlShow'
                        }]

                    },
                    {xtype:'textfield',fieldLabel:'更新时间',name:'createTime',allowBlank: false}
                ],


    	        buttons:[{
    	        	text : "保存",
					handler : function(button) {
						var form = button.up('form');
                        var submitUrl=me.updateUrl;
						form.submit({
							url : submitUrl,
							param : form.getValues(),
							waitMsg:'处理中....',
							jsonSubmit:true,
							success : function() {
								Ext.Msg.alert("修改成功！");
							},
							failure : function() {
//								this.up('form').getForm().reset();
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
	            	    url: 'ext/loadone/loading',
	            	    success: function(response, opts) {
	            	        obj = Ext.decode(response.responseText);
	            	        me.down('form').getForm().setValues(obj);
                            var loadingImgUrl=obj['loadingImgUrl'];
                            if(!loadingImgUrl || loadingImgUrl.match(/\s+/)){
                                me.down('#loadingImgUrlShow').setSrc("js/app/resources/default/default-preview.png");
                            }else{
                                me.down('#loadingImgUrlShow').setSrc(loadingImgUrl);
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