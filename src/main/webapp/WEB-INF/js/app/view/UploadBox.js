Ext.define("dy.view.UploadBox", {
	extend : "Ext.window.Window",
	alias : 'widget.uploadbox',
	closeAction : "hide",
	modal : true,
	uploadType : 'none',
	resetUploadType : function(type) {
		if (type) {
			this.uploadType = type;
		} else {
			this.uploadType = 'none';
		}
	},
	initComponent : function() {
		var me = this;

		Ext.apply(me, {

			items : [ {
				xtype : "form",
				bodyPadding : 5,
				bodyStyle : "background:#DFE9F6",
				border : false,
				enctype : 'multipart/form-data',
				trackResetOnLoad : true,
				items : [ {
					xtype : 'filefield',
					fieldLabel : '要上传的文件',
					allowBlank : false,
					name : 'uploadFile'
				} ],
				bbar : {
					ui : "footer",
					layout : {
						pack : 'center'
					},
					items : [ {
						text : '上传',
						handler : function() {
							var form = me.down('form').getForm();
							if (form.isValid()) {
								form.submit({
									waitMsg : '正在上传请稍候',
									waitTitle : '提示',
									url : 'ext/upload',
									params : {
										type : me.uploadType
									},
									method : 'POST',
									success : function(t, action) {
										me.close();
										if (me.successCallback) {
											me.successCallback(action);
										}
									},
									failure : function(t, action) {
										Ext.MessageBox.show({
											title : '失败',
											msg : '上传失败!\r\n' + action,
											buttons : Ext.MessageBox.OK,
											icon : Ext.MessageBox.ERROR
										});
									}
								});
							}

						}
					}, {
						text : '取消',
						handler : function() {
							me.down('form').getForm().reset();
							me.close();
						}
					} ]
				}
			} ]
		});

		me.callParent();
	},
	openUpload : function(type, scb) {
		var me = this;
		me.resetUploadType(type);
		if (scb) {
			me.successCallback = scb;
		} else {
			if (me.successCallback) {
				delete me.successCallback;
			}
		}
		me.show();
	}
}, function() {
	dy.UploadBox = dy.UB = new this();
});