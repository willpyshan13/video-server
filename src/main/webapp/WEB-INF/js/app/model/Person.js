Ext.define('dy.model.Person', {
	extend : 'Ext.data.Model',
	idProperty : 'personId',
	fields : [ 'personId', 'userName', 'passWord', 'mobileSerial',
			'mobileNumber', 'gender', 'personDesc', 'email', 'birthday',
			'photoUrl', 'status', 'createTime' ],
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/person',
			read : 'ext/load/person',
			update : 'ext/update/person',
			destroy : 'ext/remove/person'
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
	}
});