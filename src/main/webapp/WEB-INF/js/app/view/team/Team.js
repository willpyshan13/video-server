Ext.define('dy.view.team.Team', {
    extend: 'dy.view.BaseEditGrid',
    alias: 'widget.teamgrid',
    editWindowType: 'teameditwindow',
    store: 'Team',
    title: "团队推荐",
    initComponent: function () {
        var me = this;
        Ext.apply(me, {
            columns: [
                {text:'团队编号',dataIndex:'teamId',flex:1},
                {text:'团队标题',dataIndex:'teamTitle',flex:1},
                {text:'团队类型编号',dataIndex:'teamTypeId',flex:1,hidden:true},
                {text:'团队类型名称',dataIndex:'teamTypeName',flex:1},
                {text:'团队类型预览图',dataIndex:'teamImgPreviewUrl',flex:1,hidden:true},
                {text:'团队内容图片地址',dataIndex:'teamImgContentUrl',flex:1,hidden:true},
                {text:'寻求合作连接地址',dataIndex:'partnerUrl',flex:1},
                {text:'团队信息',dataIndex:'teamContent',flex:1},
                {text: '更新时间', dataIndex: 'createTime', flex: 1,renderer:function(value){
				    	return Ext.Date.format(new Date(value),"Y-m-d");
				}}
            ],
            dockedItems: [
                {
                    dock: 'top',
                    xtype: "pagingtoolbar",
                    store: "Team",
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
                        },
                    ]

                }
            ],
            listeners: {
                "selectionchange": function (grid, rs) {
                    var length = rs.length;
                    me.down('button[action=remove]').setDisabled(length < 1);
                    me.down('button[action=edit]').setDisabled(length != 1);
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