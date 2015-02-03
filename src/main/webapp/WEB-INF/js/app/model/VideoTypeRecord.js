Ext.define('dy.model.VideoTypeRecord', {
	extend : 'Ext.data.Model',
	idProperty : 'typeRecordId',
	fields : [ 'typeRecordId', 'videoId', 'typeName' ],
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/videoTypeRecord',
			read : 'ext/load/videoTypeRecord',
			update : 'ext/update/videoTypeRecord',
			destroy : 'ext/remove/videoTypeRecord'
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