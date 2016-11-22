/**
 *  Instagram feedi
 */


var feed = new Instafeed({
		get: 'user',
		userId: 4181455874,
		accessToken: '4181455874.1677ed0.96ef6a7ff545477997ba60ecd2aae92d',
		target: 'instafeed',
		template: '<a href="{{link}}"><img src="{{image}}" /></a>',
		after: function() {
			var el = document.getElementById('instafeed');
			if (el.classList)
				el.classList.add('show');
			else
				el.className += ' ' + 'show';
		}
});

window.onload = function() {
	feed.run();
}