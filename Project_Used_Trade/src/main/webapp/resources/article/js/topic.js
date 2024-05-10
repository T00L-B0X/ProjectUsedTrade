function getArticle() {

	const LOCATNS = document.getElementById('location').value;
	const ARTITLE = document.getElementById('title').value;
	const USERID = document.getElementById('userid').value;
	const CONTENT = document.getElementById('content').value;

	const csrfToken = document.getElementById('csrfToken').value;

	const xhr = new XHLHttpRequest();
	xhr.open('POST', '/article/topic', true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);

	const data = JSON.stringify({
		LOCATNS : LOCATNS,
		ARTITLE : ARTITLE,
		USERID : USERID,
		CONTENT : CONTENT,
	});

	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				// 요청 성공 처리
			} else {
				// 요청 실패 처리
			}
		}
	};

	xhr.send(data);

}