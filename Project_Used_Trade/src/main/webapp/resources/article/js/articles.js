function insertArticle() {
	const csrfToken = document.getElementById('csrfToken').value;
	
	const locatns = document.getElementById('locatns').value;
	const artitle = document.getElementById('artitle').value;
	const userid = document.getElementById('userid').value;
	const content = editor.getHTML();
	
	if (!artitle) {
        alert('제목을 입력해주세요.');
        return false;
    }

	if (content === "<p><br></p>") {
		alert('내용을 입력해주세요.');
		return false;
	}
	
	const xhr = new XMLHttpRequest();
	xhr.open('POST', '/article/articles');
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	
	const data = JSON.stringify({
		locatns : locatns,
		artitle : artitle,
		userid : userid,
		content : content,
	});
	
	xhr.send(data);
	
	xhr.onload = () => {
		if (xhr.status === 200) {
			alert('성공적으로 글을 작성하였습니다.');
			const anumber = xhr.responseText;
            window.location.href = '/article/articles/' + anumber;
		} else {
			alert('내용을 확인해주세요.\n지속적으로 반복한다면 관리자에게 문의해주세요.');
		}
	};
}