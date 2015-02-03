Ext.define('dy.store.NewsWheel', {
	extend : 'Ext.data.Store',
	model : 'dy.model.Video',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/topline/news',
			read : 'ext/load/topline/news',
			destroy : 'ext/remove/topline/news'
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