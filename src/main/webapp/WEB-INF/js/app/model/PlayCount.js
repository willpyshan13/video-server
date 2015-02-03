Ext.define('dy.model.PlayCount', {
	extend : 'Ext.data.Model',
	idProperty : 'hotId',
	fields : [ 'hotId', 'videoId','videoTitle', 'typeId', 'createTime','todayCount','weekCount','monthCount','count'],
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/hot',
			read : 'ext/load/hot',
			update : 'ext/update/hot',
			destroy : 'ext/remove/hot'
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