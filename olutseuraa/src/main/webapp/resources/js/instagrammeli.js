/**
 *  Instagram feedi
 */


//$(function(){
//
//	$(window).bind("resize",function(){
//	    console.log($(this).width())
//	    if($(this).width() <500){
//	    insta.run();
//	    }
//	});
//	});

//var feed = new Instafeed({
//		get: 'user',
//		userId: 4181455874,
//		accessToken: '4181455874.1677ed0.96ef6a7ff545477997ba60ecd2aae92d',
//		target: 'instafeed',
//		template: '<a href="{{link}}"><img src="{{image}}" /></a>',
//		limit: 5,
//		after: function() {
//			var el = document.getElementById('instafeed');
//			if (el.classList)
//				el.classList.add('show');
//			else
//				el.className += ' ' + 'show';
//		}
//});
//
//window.onload = insta();
//
//
// function insta() {
//	var instafeedi = document.getElementById("instafeed");
//	//jos feedi löytyy sivulta
//	if(instafeedi) {
//		feed.run();
//	}
//}


var feed = new Instafeed({
        get: 'user',
        userId: 4181455874,
        accessToken: '4181455874.1677ed0.96ef6a7ff545477997ba60ecd2aae92d',
        target: 'instafeed',
        template: '<a href="{{link}}"><img src="{{image}}" /></a>',
        limit: '5',
        after: function() {
            var el = document.getElementById('instafeed');
            if (el.classList)
                el.classList.add('show');
            else
                el.className += ' ' + 'show';
        }
});


