Ext.define("dy.view.record.PlayEditWindow", {
	extend : "dy.view.BaseEditWindow",
	alias : 'widget.playeditwindow',
	initComponent : function() {
		var me = this;
		Ext.apply(me, {
			formItems : [
                {xtype:'textfield',fieldLabel:'播放编号',name:'hotId',readOnly:true,readOnlyCls: 'x-item-disabled'},
                {xtype:'textfield',fieldLabel:'视频编号',name:'videoId',allowBlank: false},
                {xtype:'textfield',fieldLabel:'视频标题',name:'videoTitle',allowBlank: false},
                {xtype:'textfield',fieldLabel:'今日播放次数',name:'todayCount',allowBlank: false} ,
                {xtype:'textfield',fieldLabel:'本周播放次数',name:'weekCount',allowBlank: false} ,
                {xtype:'textfield',fieldLabel:'本月播放次数',name:'monthCount',allowBlank: false} ,
                {xtype:'textfield',fieldLabel:'历史播放次数',name:'count',allowBlank: false} ,
			    {xtype : 'datefield',
			  	    fieldLabel : '近期播放时间',
					name : 'createTime',
					readOnly : true,
					readOnlyCls : 'x-item-disabled',renderer:function(value){
						    return Ext.Date.format(new Date(value),"Y-m-d");
				}} ]
		});

		me.callParent();
	},
	listeners : {}
});