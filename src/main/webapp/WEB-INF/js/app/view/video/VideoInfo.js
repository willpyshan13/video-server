Ext.define('dy.view.video.VideoInfo', {
	extend:'Ext.panel.Panel',
	alias:'widget.videoinfopanel',
	layout:'border',
	autoScroll:false,
	initComponent: function () {
		var me=this;
		
		
		Ext.apply(me,{
			items:[{
				region:'center',
				xtype:'videoinfogrid'
				//title: "视频信息列表"
			},{
				region:'south',
				xtype:'videourlgrid',
				title:'播放地址',
				collapsible:true,
				height:200,
				split:true
			}]
		});
		me.callParent();
	}
});