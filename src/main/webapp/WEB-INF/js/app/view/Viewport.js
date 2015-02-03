Ext.define('dy.view.Viewport', {
    extend: 'Ext.container.Viewport',
    id:'video-viewport',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    broder: false,
    requires: [
        'dy.view.MenuList'
	],
    initComponent: function () {
        this.items = [
			{
			    xtype: "toolbar",
			    height: 53,
			    id: "North",
			    items: [{
			        xtype: 'component',
			        cls: 'logo',
			        html: '东娱微电影后台管理系统'
			    }, "->", {
			        iconCls: "logout",
			        tooltip: "退出",
			        scale: "large",
			        handler: function () {
			        	Ext.Msg.show({
			        	     title:'是否退出登录?',
			        	     msg: '确认退出请点击是，否则请点击否！',
			        	     buttons: Ext.Msg.YESNO,
			        	     closeAction:'hide',
			        	     icon: Ext.Msg.QUESTION,
			        	     fn: function(button){
	        	            	 if (button == "yes") {
	        	            		 window.location = "logout";
								} }
			        	});
			        }
			    }]
			}, {
			    xtype: "panel",
			    flex: 1,
			    id: "mainPanel",
			    layout: 'border',
			    items: [
                    {
                        region:'west',
                        width:160,
                        xtype: 'menulist',
                        title: '菜单栏',
                        collapsible:true,
			            split:true
                    }, {
                        id:"content-panel",
                        region:'center',
                        xtype:'tabpanel',
            			defaults:{
            				autoScroll:true,
            				bodypadding:10
            			},
            			activeTab:0,
            			items:[
            				{
            					id:'home-page',
            					title:'首页',
            					layout:'fit',
            					bodyStyle:'background-color:rgb(53,92,45);',
            					html:"<img style='width:100%;' src='js/app/resources/images/welcome1.png' />"
            				}
            			]
            		
                    }
                ]
			}, {
			    xtype: "component",
			    height: 13,
			    id: "South"
			}
		];

        this.callParent();
    }
});