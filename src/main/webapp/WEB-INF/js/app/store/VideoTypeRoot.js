Ext.define('dy.store.VideoTypeRoot', {
	extend : 'Ext.data.Store',
	model : 'dy.model.VideoType',
	autoLoad : false,
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/videoType',
			read : 'ext/load/videoType',
			update : 'ext/update/videoType',
			destroy : 'ext/remove/videoType'
		},
		headers : {
			'Content-Type' : 'application/json; charset=UTF-8'
		},
		reader : {
			type : 'json',
			root : 'data'
		},
		writer : {
			encode : false,
			writeAllFields : true
		},
		extraParams:{
			filterType:'root'
		}
	}
});