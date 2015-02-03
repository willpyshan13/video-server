Ext.define('dy.store.combo.ToplineType',{
	extend:'Ext.data.ArrayStore',
	fields: [{name:"id",type:'id'}, "text"],
    data: [[0, "视频轮播"], [1, "视频热点"],[2,'新闻轮播']]
});