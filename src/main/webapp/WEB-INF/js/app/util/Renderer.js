Ext.define("dy.util.Renderer",{
	statics: {
		storeRenderer:function(store,valueField,displayField){
			return function(value){
				return store.findRecord(valueField,value).get(displayField);
			};
		}
    }
});