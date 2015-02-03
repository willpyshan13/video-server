Ext.define('dy.model.Comment', {
	extend : 'Ext.data.Model',
	idProperty : 'commentId',
	fields : [ 'commentId', 'personId','userName', 'videoId','videoTitle', 'commentContent', 'status',
			'createTime' ],
	proxy : {
		type : 'ajax',
		api : {
			create : 'ext/add/comment',
			read : 'ext/load/comment',
			update : 'ext/update/comment',
			destroy : 'ext/remove/comment'
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