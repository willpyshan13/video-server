Ext.define('dy.view.person.PersonInfo', {
	alias:'widget.personinfogrid',
	extend:'dy.view.BaseEditGrid',
	editWindowType:'personeditwindow',
    title: "用户信息列表",
    store: 'Person',
	initComponent: function () {
		var me=this;
		var _window = new Ext.Window({
            title:'修改密码',  
            layout:'form',
            width:250,
            height:150,  
            bodyPadding: 10,
            closeAction: "hide",
            buttonAlign : 'center',
    	    modal: true,
            items:[
            {  
                xtype:'textfield',  
                fieldLabel:'新密码',
                minLength:6,
                maxLength:20,
                allowBlank: false,
                id:'password',
                type:'password'
            },{  
                xtype:'textfield',  
                fieldLabel:'确认新密码',
                minLength:6,
                maxLength:20,
                allowBlank: false,
                id:'repassword',
                type:'password'
            }],
            buttons:[
                     {
                text:'确定修改',  
                handler:function(button){
					if (Ext.getCmp("password").getValue()==="") {
						Ext.Msg.alert("密码不能为空！");
					}else{
                	if (Ext.getCmp("password").getValue()===Ext.getCmp("repassword").getValue()) {
                		var obj = (me.getSelectionModel().getSelection())[0].getData();
                		obj["passWord"]=Ext.getCmp("password").getValue();
                		Ext.Ajax.request({
                			url: 'ext/change/person',
                			method:'post',
                			rawData:Ext.encode(obj),
                			success: function(response, opts) {
                				var win=button.up('window');
                				win.hide();
            	            	Ext.Msg.alert("修改完成！");
                			},
                			failure: function(response, opts) {
                			}
                		});
					}
                	else{
                		Ext.Msg.alert("两次密码必须一致！");
					}
					}
                }
            }, {
	            text: "取消修改",
	            handler: function (button) {
	            	var win=button.up('window');
	            	win.hide();
	            }
	        }]  
        });  
		Ext.apply(me,{
			columns:[
				{text:'用户编号',dataIndex:'personId',flex:1},
				{text:'头像',dataIndex:'photoUrl',align:'center',flex:1,renderer:function(v){
					var dft;
					if (v === null) {
						dft="js/app/resources/default/default-user.png";
					}else
						dft = v;
					return "<img width='50'  alt='default photo' src='"+dft+"' />";
				}},
				{text:'用户名',dataIndex:'userName',flex:1},
				{text:'手机序号',dataIndex:'mobileSerial',flex:1},
				{text:'手机号',dataIndex:'mobileNumber',flex:1},
				{text:'性别',dataIndex:'gender',flex:1,renderer:function(v){
					var str="女";
					if(v){
						str="男";
					}
					return str;
				}},
				{text:'生日',dataIndex:'birthday',flex:1},
				{text:'个人说明',dataIndex:'personDesc',flex:1, sortable:false},
				{text:'状态',dataIndex:'status',flex:1, sortable:false},
				{text:'注册时间',dataIndex:'createTime',flex:1,renderer:function(value){
				    	return Ext.Date.format(new Date(value),"Y-m-d");
				}}
            ],
			tbar: {
                xtype: "pagingtoolbar",
                store: "Person",
                displayInfo: true,
                items: ["-",
                        {
                        	text:"增加",
                        	action:'add',
                        	iconCls:'Useradd',
                        	scope:me,
                        	handler:me.openAdd
                        },
                        {
                            text: "修改密码",
                            iconCls:'Useredit',
                            disabled: true,
                            action: "changepassword",
                            scope:me,
                            listeners:{  
                                "click":function(){  
                                	Ext.getCmp("password").setValue("");
                                	Ext.getCmp("repassword").setValue("");
                                    _window.show();
                                }  
                            }  
                        },
                        {
                            text: "编辑",
                            iconCls:'Useredit',
                            disabled: true,
                            action: "edit",
                            scope:me,
                            handler:me.openEdit
                        },
                        {
                            text: "删除",
                            iconCls:'Userdelete',
                            disabled: true,
                            action: "remove",
                            handler:function(){
                            	var store=me.getStore();
                            	var rs=me.getSelectionModel().getSelection();
                            	
                            	 var content = ["确定删除以下记录? "];
                                 for (var i = 0; i < rs.length; i++) {
                                     content.push(rs[i].getId());
                                 }
                                 Ext.Msg.confirm("删除记录", content.join("<br/>"), function (btn) {
                                     if (btn == "yes") {
                                         store.remove(rs);
                                         store.sync(me.editOptionCallback(me));
                                     }
                                 });
                            }
                        }]
            },
            listeners:{
            	"selectionchange":function(grid,rs){
	               	 var length = rs.length;
	                 me.down('button[action=remove]').setDisabled(length < 1);
	                 me.down('button[action=edit]').setDisabled(length != 1);
	                 me.down('button[action=changepassword]').setDisabled(length != 1);
	            },
	            "itemdblclick":me.openEdit,
	            "afterrender":function(){
	            	me.getStore().load();
	            }
           }
		});
		me.callParent();
	}
});