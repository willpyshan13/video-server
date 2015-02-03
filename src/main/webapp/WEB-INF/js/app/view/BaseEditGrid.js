Ext.define('dy.view.BaseEditGrid', {
	extend:'Ext.grid.Panel',
	editWindowType:null,
	requires: ["Ext.ux.CheckColumn","dy.view.BaseLoadEditWindow"],
    selType: "checkboxmodel",
    selModel: { checkOnly: false, mode: "MULTI" },
    useLoadWindow:function(editWin){
    	return editWin && editWin instanceof dy.view.BaseLoadEditWindow;
    },
    getEditWindow:function(){
    	var me=this;
    	if(!me.editWindowType){
    		throw new Error('please config [editWindowType]');
    	}
		var editWindowId='GridEditWindow-'+me.editWindowType;
    	var editWin=Ext.getCmp(editWindowId);
    	if(!editWin){
    		editWin=Ext.widget(me.editWindowType,{
    			id:editWindowId
    		});
    	}
    	return editWin;
	},
	openEdit:function(){
		var me=this;
    	var editWin=me.getEditWindow(),
    	rs = me.getSelectionModel().getSelection();
    	editWin.setTitle('编辑');
    	editWin.action="edit";
    	var record=rs[0];
    	editWin.store=me.getStore();
    	editWin.center();
    	if(me.useLoadWindow(editWin)){
    		editWin.showLoad(record.getId());
    	}else{
	    	editWin.down('form').loadRecord(record);
	    	editWin.show();
    	}
    },
    getAddParams:function(){
    	return {};
    },
    openAdd:function(){
    	var me=this;
		var editWin=me.getEditWindow();
    	var store=me.getStore();
    	
    	var record=store.model.create(me.getAddParams());
    	editWin.store=store;
    	editWin.down('form').loadRecord(record);
    	editWin.setTitle('增加');
    	editWin.action="add";
    	editWin.center();
    	editWin.show();
	},
    editOptionCallback:function(me){
        return {
            success: function () {
            	me.getStore().load();
            },
            failure: function (response, options) {
                Ext.Msg.alert("操作失败", "操作出错 " + response.exceptions[0].error.statusText);
                me.getStore().rejectChanges();
            }
        };
    }
});