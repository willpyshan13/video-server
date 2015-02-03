Ext.define('dy.model.VideoType', {
	extend : 'Ext.data.Model',
	idProperty : 'typeId',
	fields : [ {name:'typeId'}, {name:'typeParent',type:'int'}, 'typeName', 'typeDesc' ],
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
		}
	}
});