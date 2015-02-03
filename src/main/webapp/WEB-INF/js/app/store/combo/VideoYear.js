Ext.define('dy.store.combo.VideoYear',{
	extend:'Ext.data.Store',
	model:'dy.model.VideoType',
    autoLoad : true,
    proxy : {
		type : 'ajax',
		url:'ext/load/videoType/year',
		reader : {
			type : 'json',
			root : 'data'
		}
	}
});