Ext.define("dy.view.video.VideoCommentEditWindow", {
	alias : 'widget.videocommenteditwindow',
	extend: "dy.view.BaseLoadEditWindow",
	loadUrl:'ext/loadOne/comment',
	updateUrl:"ext/update/comment",
	addUrl:"ext/add/comment",
	initComponent : function() {
		var me = this;
		Ext.apply(me, {
			formItems : [ {
				xtype : 'textfield',
				fieldLabel : '编号',
				name : 'commentId',
				readOnly : true,
				readOnlyCls : 'x-item-disabled'
			}, {
				xtype : 'textfield',
				fieldLabel : '所属用户',
				name : 'personId',
				allowBlank : false
			}, {
				xtype : 'textfield',
				fieldLabel : '所属视频',
				name : 'videoId',
				allowBlank : false
			}, {
				xtype : 'textarea',
				fieldLabel : '评论内容',
				name : 'commentContent',
				allowBlank : false
			}, {
				xtype : 'combo',
				fieldLabel : '状态',
				name : 'status',
				store:Ext.create('dy.store.combo.Status'),
				valueField:'id',
				allowBlank : false,
				editable:false
			}, {
				xtype : 'textfield',
				fieldLabel : '创建时间',
				name : 'createTime',
				readOnly : true,
				editable:false,
				readOnlyCls : 'x-item-disabled',renderer:function(value){
				    	return Ext.Date.format(new Date(value),"Y-m-d");
				}
			}
			]
		});

		me.callParent();
	}
});