Ext.define('dy.store.combo.Gender',{
	extend:'Ext.data.ArrayStore',
	fields: ["id", "text"],
    data: [[true, "男"], [false, "女"]]
});