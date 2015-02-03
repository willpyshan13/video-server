Ext.define("dy.view.video.VideoUrlEditWindow", {
    extend: "dy.view.video.BaseUrlEditWindow",
    alias: 'widget.videourleditwindow',
    width:300,
    initComponent: function () {
    	var me=this;
    	Ext.apply(me,{
    		formItems: [
	                {xtype:'textfield',fieldLabel:'编号',name:'videoUrlId',readOnly:true,readOnlyCls: 'x-item-disabled'},
	                {xtype:'textfield',fieldLabel:'所属视频编号',name:'videoId',readOnly:true,readOnlyCls: 'x-item-disabled'},
	                {xtype:'textfield',fieldLabel:'链接序号',name:'videoUrlIndex',allowBlank: false},
	                {xtype:'textfield',fieldLabel:'链接描述',name:'videoUrlDesc',allowBlank: false},
                    {
                         xtype:'combo',
                         fieldLabel:'视频来源',
                         name:'videoPlayUrl',
                         allowBlank: false,
                         store:Ext.create("dy.store.combo.VideoResource")
                    },
	                {xtype:'textfield',fieldLabel:'视频地址',name:'videoWebUrl',allowBlank: false},
	                {
	                	xtype:'combo',
	                	fieldLabel:'格式',
	                	name:'videoFormat',
	                	allowBlank: true,
                        hidden:true,
	                	store:Ext.create("dy.store.combo.VideoFormat")
	                },
	                {
	                	xtype:'combo',
	                	fieldLabel:'状态',
	                	name:'status',
	                	valueField:'id',
	                	allowBlank: false,
	                	store:Ext.create("dy.store.combo.Status")
	                }
	                ]
    	});
    	
    	me.callParent();
    },
listeners: {
}
});