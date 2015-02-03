Ext.define('dy.store.combo.VideoRegion',{
	extend:'Ext.data.Store',
	model:'dy.model.VideoType',
    autoLoad : true,
	proxy : {
		type : 'ajax',
		url:'ext/load/videoType/area',
		reader : {
			type : 'json',
			root : 'data'
		}
	}
});