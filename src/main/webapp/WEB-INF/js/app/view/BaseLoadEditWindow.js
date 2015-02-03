Ext.define("dy.view.BaseLoadEditWindow", {
	extend: "Ext.window.Window",
    closeAction: "hide",
    loadUrl:null, // load url
    updateUrl:null,
    addUrl:null,
    border:false,
    autoScroll:true,
    resizable:false,
    modal: true,
	initComponent : function() {
		var me = this;
		if(Ext.isEmpty(me.updateUrl)){
			throw new Error("Please Config updateUrl");
		}
        if(Ext.isEmpty(me.addUrl)){
            throw new Error("Please Config addUrl");
        }
		if(Ext.isEmpty(me.loadUrl)){
			throw new Error("Please Config loadUrl");
		}
		if(Ext.isEmpty(me.formItems)){
			throw new Error("Please Config formItems");
		}
		var formItems=me.formItems||[];
		Ext.apply(me, {
			items : [ {
				xtype : "form",
				waitTitle : "请等待...",
				trackResetOnLoad : true,
				bodyPadding : 5,
				border : false,
				bodyStyle : "background:#DFE9F6",
				fieldDefaults : {
					labelAlign : 'right'
				},
				items :formItems,
				buttons : [ {
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
								me.close();
								if(Ext.isDefined(me.store)){
									me.store.reload();
								}
							},
							failure : function() {
								Ext.Msg.alert("failure");
							}
						});
					}
				}, {
					text : "重置",
					handler : function() {
						this.up('form').getForm().reset();
					}
				} ]
			} ]
		});

		me.callParent();
	},
	showLoad:function(id){
		var me=this;
		me.show();
		var form=me.down("form");
		form.load({
			url:me.loadUrl,
			waitMsg:'加载中....',
			params:{id:id},
			success : function(form,action) {
				me.fireEvent("afterload",action.result);
			},
			failure : function() {
				Ext.Msg.alert("数据加载失败!");
			}
		});
	}
});