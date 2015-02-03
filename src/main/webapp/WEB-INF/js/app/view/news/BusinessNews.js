Ext.define('dy.view.news.BusinessNews', {
    extend: 'dy.view.BaseEditGrid',
    alias: 'widget.businessnewsgrid',
    editWindowType: 'businessnewseditwindow',
    store: 'BusinessNews',
    title: "行业新闻列表",
    initComponent: function () {
        var me = this;
        var yesOrNo = function (v) {
            if (v)return '是';
            return '否';
        };
        Ext.apply(me, {
            columns: [
                {text: '编号', dataIndex: 'newsId', flex: 1},
                {text: '轮播', dataIndex: 'wheel', flex: 1, renderer: yesOrNo,sortable:false},
                {text: '新闻标题', dataIndex: 'newsTitle', flex: 1},
                {text: '新闻图片(轮播)', dataIndex: 'newsWheelPicUrl', flex: 1, hidden: true},
                {text: '涉及视频编号', dataIndex: 'videoId', flex: 1,hidden:true},
                {text:'关联视频名称',dataIndex:'videoTitle',flex:1, sortable:false},
                {text: '状态', dataIndex: 'status', flex: 1, sortable:false, sortable:false,
                    renderer: dy.util.Renderer.storeRenderer(Ext.getStore('combo.Status'), 'id', 'text')
                },
                {text: '创建时间', dataIndex: 'createTime', flex: 1,renderer:function(value){
				    	return Ext.Date.format(new Date(value),"Y-m-d");
				}}
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        {
                            xtype: 'form',
                            layout: 'hbox',
                            bodyStyle: "background:#DFE9F6",
                            border: false,
                            items: [
                                {
                                    xtype: 'textfield',
                                    fieldLabel: '关键字',
                                    labelWidth: 50,
                                    name: "keyword"
                                },
                                {
                                    xtype: 'numberfield',
                                    fieldLabel: '新闻编号',
                                    labelWidth: 55,
                                    width: 140,
                                    name: 'newsId'
                                },
                                {
                                    xtype: 'numberfield',
                                    fieldLabel: '视频编号',
                                    labelWidth: 55,
                                    width: 140,
                                    name: 'videoId'
                                },
                                {
                                    xtype: 'combo',
                                    fieldLabel: '状态',
                                    labelWidth: 30,
                                    width: 100,
                                    store: Ext.getStore('combo.Status'),
                                    valueField: 'id',
                                    editable: false,
                                    name: 'status'
                                }
                            ]
                        },
                        ' ',
                        {
                            xtype: 'button',
                            iconCls: 'Zoom',
                            text: '过滤',
                            action: 'filter',
                            handler: function (btn) {
                                var form = btn.up('toolbar').down('form').getForm();
                                var values = form.getValues();
                                var store = me.getStore();
                                store.clearFilter(true);
                                var filters = [];
                                var i = null;
                                for (i in values) {
                                    if (!Ext.isEmpty(values[i])) {
                                        filters.push({property: i, value: values[i]});
                                    }
                                }
                                store.filter(filters);
                            }
                        },
                        {
                            xtype: 'button',
                            iconCls: 'Stop',
                            text: '清除过滤',
                            action: 'resetFilter',
                            handler: function (btn) {
                                btn.up('toolbar').down('form').getForm().reset();
                                me.getStore().clearFilter(false);
                            }
                        }
                    ]
                },
                {
                    dock: 'top',
                    xtype: "pagingtoolbar",
                    store: "BusinessNews",
                    displayInfo: true,
                    items: [
                        "-",
                        {
                            text: "增加",
                            action: 'add',
                            iconCls: 'Newspaperadd',
                            scope: me,
                            handler: me.openAdd
                        },
                        {
                            text: "编辑",
                            iconCls: 'Newspapergo',
                            disabled: true,
                            action: "edit",
                            scope: me,
                            handler: me.openEdit
                        },
                        {
                            text: "删除",
                            iconCls: 'Newspaperdelete',
                            disabled: true,
                            action: "remove",
                            handler: function () {
                                var store = me.getStore();
                                var rs = me.getSelectionModel().getSelection();

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
                        }, '-',
                        {
                            text: '轮播',
                            iconCls: 'Awardstaradd',
                            action: 'wheel',
                            disabled: true,
                            menu: [
                                {
                                    text: '设置为轮播',
                                    handler: function (m) {
                                        var rs = me.getSelectionModel().getSelection();
                                        if (rs) {
                                            var record = rs[0];
                                            if (!me.getStore().getById(record.getId()).get('wheel')) {
                                                Ext.Ajax.request({
                                                    url: 'ext/add/topline/news',
                                                    rawData: Ext.encode(record.data),
                                                    success: function () {
                                                        me.getStore().reload();
                                                        Ext.Msg.alert('提示', "设置成功!");
                                                    },
                                                    failure: function () {
                                                        Ext.Msg.alert('提示', "出错!");
                                                    }
                                                });
                                            }
                                        }

                                    }
                                },
                                {
                                    text: '取消轮播',
                                    handler: function (m) {
                                        var rs = me.getSelectionModel().getSelection();
                                        if (rs) {
                                            var record = rs[0];
                                            if (me.getStore().getById(record.getId()).get('wheel')) {
                                                Ext.Ajax.request({
                                                    url: 'ext/remove/topline/news',
                                                    rawData: Ext.encode(record.data),
                                                    success: function () {
                                                        me.getStore().reload();
                                                        Ext.Msg.alert('提示', "取消成功!");
                                                    },
                                                    failure: function () {
                                                        Ext.Msg.alert('提示', "出错!");
                                                    }
                                                });
                                            }
                                        }

                                    }
                                }
                            ]
                        }
                    ]

                }
            ],
            listeners: {
                "selectionchange": function (grid, rs) {
                    var length = rs.length;
                    me.down('button[action=remove]').setDisabled(length < 1);
                    me.down('button[action=edit]').setDisabled(length != 1);
                    me.down('button[action=wheel]').setDisabled(length != 1);
                },
                "itemdblclick": me.openEdit,
                "afterrender": function () {
                    me.getStore().load();
                }
            }
        });
        me.callParent();
    }
});