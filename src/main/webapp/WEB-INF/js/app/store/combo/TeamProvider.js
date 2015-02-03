Ext.define('dy.store.combo.TeamProvider',{
	extend:'Ext.data.ArrayStore',
	fields: ["id", "text"],
    data: [[0, "导演"], [1, "编剧"],[2,"艺人"]]
});