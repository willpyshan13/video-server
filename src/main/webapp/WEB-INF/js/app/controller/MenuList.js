Ext.define('dy.controller.MenuList', {
    extend: 'Ext.app.Controller',
    init: function () {
        this.control({
            "menulist": {
                "itemclick": this.onFunctionListSelect
            }
        });
    },
    onFunctionListSelect: function (model, selected) {
        var controllerUrl = selected.get('controller');
        var title = selected.get('name');
        var tabId = "tab-" + controllerUrl.replace(/\./g, "-");
        var tabs = Ext.getCmp('content-panel');
        if (!tabs.down("#" + tabId)) {
            var ctrl = this.application.getController(controllerUrl);
            if (!ctrl.tabViewName) {
                ctrl.tabViewName = controllerUrl.replace('controller', "view");
            }
            try {
                var view = Ext.create(ctrl.tabViewName, {
                    id: tabId,
                    title: title,
                    closable: true
                });
//	    		var view=ctrl.getView(ctrl.tabViewName).create({
//	    			id:tabId,
//	    			closable:true
//	    		});
                tabs.add(view);
            } catch (e) {
                console.error("load view : " + ctrl.tabViewName + " failure!");
                console.error(e);
            }
        }
        tabs.setActiveTab(tabId);
    }
});