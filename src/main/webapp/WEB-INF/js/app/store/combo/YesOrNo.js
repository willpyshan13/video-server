Ext.define('dy.store.combo.YesOrNo',{
	extend:'Ext.data.ArrayStore',
	fields: ["id", {name:"value",type:'boolean'},"text"],
    data: [["Y",true, "是"], ["N",false, "否"]]
});