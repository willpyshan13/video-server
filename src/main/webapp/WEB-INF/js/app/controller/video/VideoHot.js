Ext.define('dy.controller.video.VideoHot', {
    extend: 'Ext.app.Controller',
    views:["video.VideoHot",'video.VideoEditWindow'],
    models:[],
    stores:["VideoHot","combo.Status","combo.VideoYear","combo.VideoRegion","combo.VideoLabel"],
    init: function () {
        this.control({
        	
        });
        
    }
});