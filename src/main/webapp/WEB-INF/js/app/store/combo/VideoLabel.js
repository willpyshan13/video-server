Ext.define('dy.store.combo.VideoLabel',{
	extend:'Ext.data.Store',
	model:'dy.model.VideoType',
    autoLoad : true,
	proxy : {
		type : 'ajax',
		url:'ext/load/videoType/label',
		reader : {
			type : 'json',
			root : 'data'
		}
	}
});