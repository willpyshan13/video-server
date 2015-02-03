Ext.define('dy.view.sys.ChangePassword', {
    extend:'Ext.panel.Panel',
    alias:'widget.changepasswordgrid',
    title: '修改管理员信息',
    store: 'ChangePassword',
    loadUrl:'ext/loadone/admin',
    updateUrl:"ext/update/admin",
    addUrl:"ext/update/admin",
    initComponent: function () {

        var me=this;
        var obj=null;
        Ext.apply(me, {
            items: [{
                xtype: "form",
                waitTitle: "请等待...",
                trackResetOnLoad:true,
                bodyPadding: 5,
                border:false,
                bodyStyle: "background:#DFE9F6",
                fieldDefaults: {
                    labelAlign : 'right'
                },
                items : [
                    {xtype:'textfield',fieldLabel:'编号',name:'adminId',readOnly:true,readOnlyCls: 'x-item-disabled',hidden:true},
                    {xtype:'textfield',fieldLabel:'管理员名字',name:'adminName',allowBlank: false},
                    {xtype:'textfield',fieldLabel:'管理员密码',name:'adminPass',allowBlank: false},
                    {
                        xtype: 'combo',
                        fieldLabel: '角色',
                        name: 'role',
                        allowBlank: false,
                        valueField: 'text',
                        editable: false,
                        store: Ext.create("dy.store.combo.Role")},
                    {
                        xtype: 'combo',
                        fieldLabel: '状态',
                        name: 'status',
                        allowBlank: false,
                        valueField: 'id',
                        editable: false,
                        store: Ext.create("dy.store.combo.Status")},
                ],
                buttons:[{
                    text : "保存",
                    handler : function(button) {
                        var form = button.up('form');
                        var submitUrl=me.action=="add"?me.addUrl:me.updateUrl;
                        form.submit({
                            url : submitUrl,
                            param : form.getValues(),
                            waitMsg:'处理中....',
                            jsonSubmit:true,
                            success : function() {
                                Ext.Msg.alert("修改成功！");
                            },
                            failure : function() {
                                this.up('form').getForm().reset();
                                Ext.Msg.alert("失败");
                            }
                        });
                    }
                }, {
                    text: "重置",
                    handler: function () {
                        me.down('form').getForm().setValues(obj);
                    }
                }]
            }],
            listeners:{
                "afterrender":function(){
                    Ext.Ajax.request({
                        url: 'ext/loadone/admin',
                        success: function(response, opts) {
                            obj = Ext.decode(response.responseText);
                            me.down('form').getForm().setValues(obj);
                        },
                        failure: function(response, opts) {
                        }
                    });
                }
            }
        });

        me.callParent();
    }
});