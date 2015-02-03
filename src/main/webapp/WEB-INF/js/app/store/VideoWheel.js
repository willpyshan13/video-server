Ext.define('dy.store.VideoWheel', {
	extend : 'Ext.data.Store',
	model : 'dy.model.Video',
//	remoteSort : true,
//	remoteFilter : true,
	pageSize : 20,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/topline/wheel',
			read : 'ext/load/topline/wheel',
			destroy : 'ext/remove/topline/wheel'
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
		}
	}
});