Ext.define('dy.view.video.VideoType', {
	alias:'widget.videotypepanel',
	extend:'Ext.panel.Panel',
    title: "视频分类列表",
    border:false,
    autoScroll:false,
	initComponent: function () {
		var me=this;
		
		
		Ext.apply(me,{
			layout:'border',
			items:[{
				region:'west',
				width:350,
				xtype:'videotyperootgrid',
				split:true
			},{
				region:'center',
				xtype:'videotypeleafgrid'
			}]
		});
		me.callParent();
	}
});