Ext.define('dy.store.VideoTypeLeaf', {
	extend : 'Ext.data.Store',
	model : 'dy.model.VideoType',
//	remoteSort : true,
//	remoteFilter : true,
	autoLoad : false,
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
		},
		extraParams:{
			filterType:'leaf',
			typeParent:-1
		}
	},
	listeners:{
		beforeload:function( store, operation, eOpts ){
			if(store.getProxy().extraParams['typeParent']==-1){
				return false;
			}else{
				return true;
			}
		}
	}
});