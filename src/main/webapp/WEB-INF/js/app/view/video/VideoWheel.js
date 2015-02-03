Ext.define('dy.view.video.VideoWheel', {
	alias:'widget.videowheelgrid',
	extend:'dy.view.BaseEditGrid',
    title: "视频头条列表",
    store: 'VideoWheel',
    editWindowType:'videoeditwindow',
    initComponent: function () {
		var me=this;
		
		Ext.apply(me,{
			columns:[
					{text:'视频编号',dataIndex:'videoId',flex:1},
					{text:'视频标题',dataIndex:'videoTitle',flex:1},
					{text:'轮播图',hidden:true,dataIndex:'videoWheelPicUrl',flex:1},
					{text:'预览图',dataIndex:'videoPreviewPicUrl',align:'center',flex:1,hidden:true},
					{text:'简介',dataIndex:'videoBrief',flex:1,hidden:true},
					{text:'详情',hidden:true,dataIndex:'videoDesc',flex:1},
					{text:'导演',dataIndex:'videoDirector',flex:1},
					{text:'编剧',dataIndex:'videoScriptwriter',flex:1,hidden:true},
					{text:'演员',dataIndex:'videoActor',flex:1},
					{text:'区域',dataIndex:'videoRegion',flex:1,hidden:true},
					{text:'年份',dataIndex:'videoYear',flex:1,hidden:true},
					{text:'状态',dataIndex:'status',flex:1,renderer:dy.util.Renderer.storeRenderer(Ext.getStore('combo.Status'),'id','text')},
					{text:'创建时间',dataIndex:'createTime',flex:1,renderer:function(value){
				    	return Ext.Date.format(new Date(value),"Y-m-d");
					}}
				],
			tbar: {
                xtype: "pagingtoolbar",
                store: "VideoWheel",
                displayInfo: true,
                items: [
                    "-",
                    {
                        text: "取消头条",
                        disabled: true,
                        scope:me,
                        action: "remove",
                        handler:function(){
                        	var store=me.getStore();
                        	var rs=me.getSelectionModel().getSelection();

                        	 var content = ["确定取消以下头条? "];
                             for (var i = 0; i < rs.length; i++) {
                                 content.push(rs[i].getId());
                             }
                             Ext.Msg.confirm("删除记录", content.join("<br/>"), function (btn) {
                                 if (btn == "yes") {
                                     store.remove(rs);
                                     store.sync(me.editOptionCallback(me));
                                 }
                             });
                        }
                    }
                ]
            },
            listeners:{
            	"selectionchange":function(grid,rs){
            		if(rs){
            			
	               	 var length = rs.length;
	                 me.down('button[action=remove]').setDisabled(length < 1);
            		}
	            },
	            "itemdblclick":me.openEdit,
	            "afterrender":function(){
	            	me.getStore().load();
	            }
            }
		});
		
		me.callParent();
	}

});