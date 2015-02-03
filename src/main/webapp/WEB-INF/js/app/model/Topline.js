Ext.define('dy.model.Topline', {
	extend : 'Ext.data.Model',
	idProperty : 'topId',
	fields : [ 'topId', 'dataId', 'topType', 'priority' ],
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/topline',
			read : 'ext/load/topline',
			update : 'ext/update/topline',
			destroy : 'ext/remove/topline'
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