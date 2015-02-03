Ext.define('dy.store.VideoHot', {
	extend : 'Ext.data.Store',
	model : 'dy.model.Video',
//	remoteSort : true,
//	remoteFilter : true,
	pageSize : 20,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/topline/hot',
			read : 'ext/load/topline/hot',
			destroy : 'ext/remove/topline/hot'
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