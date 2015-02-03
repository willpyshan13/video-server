Ext.define('dy.model.BusinessNews', {
	extend : 'Ext.data.Model',
	idProperty : 'newsId',
	fields : [ 'newsId', 'newsTitle', 'newsWheelPicUrl', 'newsPreviewPicUrl',
			'newsContent', 'createTime', 'videoId','videoTitle', 'status',
			{name:'wheel',type:'boolean',persist:false}
			],
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/businessNews',
			read : 'ext/load/businessNews',
			update : 'ext/update/businessNews',
			destroy : 'ext/remove/businessNews'
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