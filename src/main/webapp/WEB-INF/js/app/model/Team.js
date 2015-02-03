Ext.define('dy.model.Team', {
	extend : 'Ext.data.Model',
	idProperty : 'teamId',
	fields : [ 'teamId', 'teamTitle', 'teamTypeId', 'teamTypeName', 'partnerUrl',
			'teamImgPreviewUrl', 'teamImgContentUrl', 'teamContent','createTime'
			],
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/team',
			read : 'ext/load/team',
			update : 'ext/update/team',
			destroy : 'ext/remove/team'
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