fetch('https://v1.hitokoto.cn')
	.then(function(res) {
		return res.json();
	})
	.then(function(data) {
		var hitokoto = document.getElementById('hitokoto');
		hitokoto.innerText = "“" + data.from + "”" + "——  " + data.hitokoto;
	})
	.catch(function(err) {
		console.error(err);
	})

function whenError(a){
a.onerror = null;
a.src = 'img/random/1.jpg';
console.log('图片出错的时候调用默认的图片');
}
function whenQQError(a){
a.onerror = null;
a.src = 'img/random/1.jpg';
console.log('图片出错的时候调用默认的图片');
}
function whenLOGINError(a){
a.onerror = null;
a.src = 'img/random/2.jpg';
console.log('图片出错的时候调用默认的图片');
}