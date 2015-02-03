Ext.define('dy.view.MenuList',{
	extend:'Ext.panel.Panel',
	layout: {
        type: 'accordion',
        hideCollapseTool:true,
        animate: true
    },
	alias:'widget.menulist',
	initComponent:function(){
		var me=this;
		var fields=['name','controller'];
		var columns=[{dataIndex:'name',flex:1}];
		var ctrlUrl="dy.controller.";
		
		var videoInfoStore=Ext.create('Ext.data.ArrayStore', {
		    fields: fields,
		    data:[
		          ["视频信息",ctrlUrl+"video.VideoInfo"],
		          ['视频头条',ctrlUrl+'video.VideoWheel'],
		          ['视频热点',ctrlUrl+'video.VideoHot'],
		          ['视频分类',ctrlUrl+'video.VideoType'],
		          ['视频评论',ctrlUrl+'video.VideoComment']
		          ]
		});
		var personInfoStore=Ext.create('Ext.data.ArrayStore',{
			fields:fields,
			data:[
			      ["用户信息",ctrlUrl+'person.PersonInfo'],
			      ['"赞"管理',ctrlUrl+'person.PraiseInfo'],
			      ['收藏管理',ctrlUrl+'person.CollectionInfo'],
			      ['用户反馈信息',ctrlUrl+'person.FeedbackInfo']
			      ]
		});
		var recordStore=Ext.create('Ext.data.ArrayStore',{
			fields:fields,
			data:[
			      ['搜索记录',ctrlUrl+'record.SearchHistory'],
			      ['播放历史',ctrlUrl+'record.PlayHistory'],
                  ['播放次数统计',ctrlUrl+'record.PlayCount']
			      ]
		});
		var businessNewsStore=Ext.create('Ext.data.ArrayStore',{
			fields:fields,
			data:[
			      ['新闻管理',ctrlUrl+'news.BusinessNews'],
			      ['头条管理',ctrlUrl+'news.BusinessTopline']
			      ]
		});
		var systemStore=Ext.create('Ext.data.ArrayStore',{
			fields:fields,
			data:[
			      ['修改管理员信息',ctrlUrl+'sys.ChangePassword'],
                  ['添加管理账户',ctrlUrl+'sys.AdminAdd'],
			      ['邮箱设置',ctrlUrl+'sys.SystemConfig'],
                  ['客户端版本',ctrlUrl+'sys.UpdateInfo'],
                  ['App首页加载图',ctrlUrl+'sys.Loading'],
                  ['公司信息',ctrlUrl+'sys.CompanyInfo'] ,
                  ['消息推送',ctrlUrl+'sys.PushMessage']
			      ]
		});
        var teamStore=Ext.create('Ext.data.ArrayStore',{
            fields:fields,
            data:[
                ['团队信息',ctrlUrl+'team.Team'],
            ]
        });
		Ext.apply(this,{
			items:[{
				title:'视频信息管理',
				xtype:'grid',
				store:videoInfoStore,
				hideHeaders:true,
				columns:columns
			},{
				title:'行业新闻管理',
				xtype:'grid',
				store:businessNewsStore,
				hideHeaders:true,
				columns:columns
			},{
                title:'团队推荐管理',
                xtype:'grid',
                store:teamStore,
                hideHeaders:true,
                columns:columns
            },{
				title:'注册用户信息管理',
				xtype:'grid',
				store:personInfoStore,
				hideHeaders:true,
				columns:columns
			},{
				title:'使用信息统计',
				xtype:'grid',
				store:recordStore,
				hideHeaders:true,
				columns:columns
			},{
				title:'系统设置',
				xtype:'grid',
				store:systemStore,
				hideHeaders:true,
				columns:columns
			}]
		});
		
		
		this.callParent();
	},
	underGridItemClick:function( grid, record, item, index, e, eOpts ){
		this.fireEvent('itemclick',grid,record,item,index,e,eOpts);
	},
	listeners:{
		afterrender:function(){
			var me=this;
			Ext.each(me.query('grid'),function(item){
				item.addListener('itemclick',me.underGridItemClick,me);
			});
		}
	}
});