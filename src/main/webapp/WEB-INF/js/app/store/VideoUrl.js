Ext.define('dy.store.VideoUrl', {
	extend : 'Ext.data.Store',
	model : 'dy.model.VideoUrl',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false,
	listeners:{
		beforeload:function( store, operation, eOpts ){
			if(store.getProxy().extraParams['videoId']==-1){
				return false;
			}else{
				return true;
			}
		}
	}
});