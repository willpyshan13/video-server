Ext.define('dy.store.ChangePassword', {
	extend : 'Ext.data.Store',
	model : 'dy.model.ChangePassword',
	remoteSort : true,
	remoteFilter : true,
	pageSize : 20,
	autoLoad : false
});