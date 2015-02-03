Ext.define("dy.view.video.VideoEditWindow", {
    extend: "dy.view.BaseLoadEditWindow",
    alias: 'widget.videoeditwindow',
    loadUrl: 'ext/loadOne/video',
    updateUrl: "ext/update/video",
    addUrl: "ext/add/video",
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
                            {xtype: 'textfield', fieldLabel: '视频编号', name: 'videoId', readOnly: true, readOnlyCls: 'x-item-disabled'},
                            {xtype: 'textfield', fieldLabel: '视频标题', name: 'videoTitle', width: 450, allowBlank: false},
                            {
                                xtype: 'container',
                                padding: "3 0",
                                layout: 'hbox',
                                defaults: {
                                    labelAlign: 'right'
                                },
                                items: [
                                    {xtype: 'textfield', fieldLabel: '导演', name: 'videoDirector', allowBlank: false},
                                    {xtype: 'textfield', fieldLabel: '编剧', name: 'videoScriptwriter', allowBlank: false}
                                ]
                            },
                            {
                                xtype: 'container',
                                padding: "3 0",
                                layout: 'hbox',
                                defaults: {
                                    labelAlign: 'right'
                                },
                                items: [
                                    {xtype: 'textfield', fieldLabel: '演员', name: 'videoActor', allowBlank: false},
                                    {
                                        xtype: 'combo',
                                        fieldLabel: '区域',
                                        name: 'videoRegion',
                                        store: "combo.VideoRegion",
                                        valueField: 'typeId',
                                        displayField: 'typeDesc',
                                        editable: false
                                    }
                                ]
                            },
                            {
                                xtype: 'container',
                                padding: "3 0",
                                layout: 'hbox',
                                defaults: {
                                    labelAlign: 'right'
                                },
                                items: [
                                    {
                                        xtype: 'combo',
                                        fieldLabel: '年份',
                                        name: 'videoYear',
                                        store: "combo.VideoYear",
                                        valueField: 'typeId',
                                        allowBlank: false,
                                        displayField: 'typeDesc',
                                        editable: false
                                    },
                                    {
                                        xtype: 'combo',
                                        fieldLabel: '状态',
                                        name: 'status',
                                        allowBlank: false,
                                        valueField: 'id',
                                        editable: false,
                                        store: Ext.create("dy.store.combo.Status")}
                                ]
                            },
                            {xtype: 'textfield', fieldLabel: '视频简述', name: 'videoBrief', allowBlank: false, width: 450},
                            {xtype: 'textarea', fieldLabel: '视频详情', maxLength: 250, name: 'videoDesc', allowBlank: false, width: 450},
                            {
                                xtype: 'combo',
                                width: 450,
                                fieldLabel: '视频标签',
                                name: 'labels',
                                allowBlank: false,
                                store: 'combo.VideoLabel',
                                displayField: 'typeDesc',
                                valueField: 'typeId',
                                multiSelect: true,
                                editable: false
                            },
                            {xtype: 'textfield', fieldLabel: '创建时间', name: 'createTime', readOnly: true,readOnlyCls : 'x-item-disabled',
                            	renderer:function(value){
            				    	return Ext.Date.format(new Date(value),"Y-m-d");
            				}}
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
                                    {xtype: 'textfield', fieldLabel: '轮播图片', name: 'videoWheelPicUrl', readOnly: true, width: 300},
                                    {xtype: 'button', text: '上传', handler: function () {
                                        dy.UB.openUpload('wheel', function (action) {
                                            me.down('#videoWheelPicShow').setSrc(action.result.data);
                                            me.down('form').getForm().findField("videoWheelPicUrl").setValue(action.result.data);
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
                                        id: 'videoWheelPicShow'
                                    }
                                ]
                            },
                            {
                                xtype: 'container',
                                padding: "3 0",
                                layout: 'hbox',
                                items: [
                                    {xtype: 'textfield', fieldLabel: '预览图片', name: 'videoPreviewPicUrl', readOnly: true, width: 300},
                                    {xtype: 'button', text: '上传', handler: function () {
                                        dy.UB.openUpload('preview', function (action) {
                                            me.down('#videoPreviewPicShow').setSrc(action.result.data);
                                            me.down('form').getForm().findField("videoPreviewPicUrl").setValue(action.result.data);
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
                                        id: 'videoPreviewPicShow'
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
                me.down('#videoPreviewPicShow').setSrc("js/app/resources/default/default-preview.png");
                me.down('#videoWheelPicShow').setSrc("js/app/resources/default/default-preview.png");
                me.down('tabpanel').setActiveTab(0);
            }
        },
        "afterload": function (action) {
            var me = this;
            var record = me.down("form").getValues();

            var previewUrl = record['videoPreviewPicUrl'];
            if (!previewUrl || previewUrl.match(/\s+/)) {
                me.down('#videoPreviewPicShow').setSrc("js/app/resources/default/default-preview.png");
            } else {
                me.down('#videoPreviewPicShow').setSrc(previewUrl);
            }

            var wheelUrl = record['videoWheelPicUrl'];
            if (!wheelUrl || wheelUrl.match(/\s+/)) {
                me.down('#videoWheelPicShow').setSrc("js/app/resources/default/default-preview.png");
            } else {
                me.down('#videoWheelPicShow').setSrc(wheelUrl);
            }

            me.down('tabpanel').setActiveTab(0);

            return true;
        }
    }
});