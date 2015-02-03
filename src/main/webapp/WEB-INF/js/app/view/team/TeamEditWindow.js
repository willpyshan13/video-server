Ext.define("dy.view.team.TeamEditWindow", {
    extend: "dy.view.BaseLoadEditWindow",
    alias: 'widget.teameditwindow',
    loadUrl: 'ext/loadOne/team',
    updateUrl: "ext/update/team",
    addUrl: "ext/add/team",
    width: 600,
    initComponent: function () {
        var me = this;
        Ext.apply(me, {
            formItems: {
                xtype: 'tabpanel',
                bodyPadding: 5,
                broder: false,
                bodyStyle: "background:#DFE9F6",
                items: [
                    {
                        title: '基本信息',
                        defaultType: 'textfield',
                        border: false,
                        bodyStyle: "background:#DFE9F6",
                        items: [
                            {xtype:'textfield',fieldLabel:'teamId',name:'teamId',readOnly:true,readOnlyCls: 'x-item-disabled',hidden:true},
                            {xtype:'textfield',fieldLabel:'团队标题',name:'teamTitle',allowBlank: false},
                            {xtype:'textfield',fieldLabel:'寻求合作连接地址',name:'partnerUrl',allowBlank: false,width:400},
                            {
                                xtype:'combo',
                                fieldLabel:'团队类型',
                                name:'teamTypeName',
                                allowBlank: false,
                                store:Ext.create("dy.store.combo.TeamProvider")
                            },
                            {xtype:'textarea',fieldLabel:'团队内容',name:'teamContent',allowBlank: false,height:200,width:400},
                            {xtype: 'textfield', fieldLabel: '创建时间', name: 'createTime', readOnly: true,readOnlyCls : 'x-item-disabled'}
                        ]
                    },
                    {
                        title: '图片信息',
                        border: false,
                        height: 500,
                        bodyStyle: "background:#DFE9F6",
                        items: [
                            {
                                xtype: 'container',
                                padding: "3 0",
                                layout: 'hbox',
                                items: [
                                    {xtype: 'textfield', fieldLabel: '团队预览图片', name: 'teamImgPreviewUrl', readOnly: true, width: 300},
                                    {xtype: 'button', text: '上传', handler: function () {
                                        dy.UB.openUpload('preview', function (action) {
                                            me.down('#teamImgPreviewUrlShow').setSrc(action.result.data);
                                            me.down('form').getForm().findField("teamImgPreviewUrl").setValue(action.result.data);
                                        });
                                    }}
                                ]
                            },
                            {
                                xtype: 'container',
                                padding: "3 0",
                                items: [
                                    {
                                        xtype: 'image',
                                        fieldLabel: '图片展示',
                                        padding: '5 0 5 100',
                                        maxHeight: 200,
                                        id: 'teamImgPreviewUrlShow'
                                    }
                                ]
                            },
                            {
                                xtype: 'container',
                                padding: "3 0",
                                layout: 'hbox',
                                items: [
                                    {xtype: 'textfield', fieldLabel: '团队内容图片', name: 'teamImgContentUrl', readOnly: true, width: 300},
                                    {xtype: 'button', text: '上传', handler: function () {
                                        dy.UB.openUpload('wheel', function (action) {
                                            me.down('#teamImgContentUrlShow').setSrc(action.result.data);
                                            me.down('form').getForm().findField("teamImgContentUrl").setValue(action.result.data);
                                        });
                                    }}
                                ]
                            },
                            {
                                xtype: 'container',
                                padding: '3 0',
                                items: [
                                    {
                                        xtype: 'image',
                                        maxHeight: 200,
                                        padding: '5 0 5 100',
                                        fieldLabel: '图片展示',
                                        id: 'teamImgContentUrlShow'
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        });

        me.callParent();
    },
    listeners: {
        "beforeshow": function () {
            var me = this;
            if (me.action == "add") {
                me.down('#teamImgPreviewUrlShow').setSrc("js/app/resources/default/default-preview.png");
                me.down('#teamImgContentUrlShow').setSrc("js/app/resources/default/default-preview.png");
                me.down('tabpanel').setActiveTab(0);
            }
        },
        "afterload": function (action) {
            var me = this;
            var record = me.down("form").getValues();

            var previewUrl = record['teamImgPreviewUrl'];
            if (!previewUrl || previewUrl.match(/\s+/)) {
                me.down('#teamImgPreviewUrlShow').setSrc("js/app/resources/default/default-preview.png");
            } else {
                me.down('#teamImgPreviewUrlShow').setSrc(previewUrl);
            }

            var wheelUrl = record['teamImgContentUrl'];
            if (!wheelUrl || wheelUrl.match(/\s+/)) {
                me.down('#teamImgContentUrlShow').setSrc("js/app/resources/default/default-preview.png");
            } else {
                me.down('#teamImgContentUrlShow').setSrc(wheelUrl);
            }

            me.down('tabpanel').setActiveTab(0);

            return true;
        }
    }
});