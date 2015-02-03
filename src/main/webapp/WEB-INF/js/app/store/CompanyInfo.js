Ext.define('dy.store.CompanyInfo', {
	extend : 'Ext.data.Store',
	model : 'dy.model.CompanyInfo',
	remoteSort : true,
	remoteFilter : true,
	autoLoad : false
});