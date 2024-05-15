function moveArticle(userid) {
	if (userid) {
		location.href = "/article/articles";
	} else {
		const confirmed = confirm('로그인이 필요한 서비스입니다.\n이동하시겠습니까?');

		if (confirmed) {
			location.href = "/user/login";
		}
	}
}

function changeLocatns() {
	var selectedLocatns = document.getElementById("locatns").value;
	var currentURL = new URL(window.location.href);
	currentURL.searchParams.set('locatns', selectedLocatns);
	currentURL.searchParams.set('page', '1');
	window.location.href = currentURL.href;
}

function search() {
	var search = document.getElementById("search").value;
	var keyword = document.getElementById("keyword").value;
	var url = "/article/list?";

	if (search) {
		url += "search=" + search + "&";
	}

	if (keyword) {
		url += "keyword=" + encodeURIComponent(keyword) + "&";
	}

	url += "page=1";

	window.location.href = url;
}