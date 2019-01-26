(function(document) {
	var min = 1;
	var max = 9;
	var unit = -50;
	var duration = 1000;
	var initClock = function() {
		setTimeout(tick, duration)
	}
	var tick = function() {
		var now = new Date()
		var options = {year: 'numeric', month: '2-digit', day: '2-digit' };
		var nowDateStrings = now.toLocaleDateString('en-US', options).split('/').join('').split('')
		var nowTimeStrings = now.toLocaleTimeString("en-US", {hour12: false}).split(':').join('').split('')
		var nowStrings = nowDateStrings.concat(nowTimeStrings)
		var cycleHtmlCollection = document.getElementsByClassName('cycle')
		for (let cycleIndex = 0; cycleIndex < cycleHtmlCollection.length; cycleIndex++) {
			var cycleElement = cycleHtmlCollection[cycleIndex]
			cycleElement.style.top = nowStrings[cycleIndex] * unit + 'px';
		}
		initClock()
	}
	initClock()
})(document)