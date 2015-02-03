Ext.define('dy.store.combo.Status',{
	extend:'Ext.data.ArrayStore',
	fields: ["id", "text"],
    data: [[0, "正常"], [1, "已删除"],[2,"不可用"]]
});