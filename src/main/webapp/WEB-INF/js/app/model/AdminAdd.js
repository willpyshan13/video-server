Ext.define('dy.model.AdminAdd', {
	extend : 'Ext.data.Model',
	idProperty : 'adminId',
	fields : [ 'adminId', 'adminName', 'adminPass', 'createTime','role','status'],
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/admin',
			read : 'ext/load/admin',
			update : 'ext/update/admin',
			destroy : 'ext/remove/admin'
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