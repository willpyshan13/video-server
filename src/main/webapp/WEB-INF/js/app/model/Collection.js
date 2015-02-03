Ext.define('dy.model.Collection', {
	extend : 'Ext.data.Model',
	idProperty : 'collectionId',
	fields : [ 'collectionId','personId','userName', 'videoId','videoTitle', 'createTime'],
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/collection',
			read : 'ext/load/collection',
			update : 'ext/update/collection',
			destroy : 'ext/remove/collection'
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