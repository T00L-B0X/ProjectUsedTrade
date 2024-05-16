function modifyArticle() {
	const csrfToken = document.getElementById('csrfToken').value;
	
	const anumber = document.getElementById('anumber').value;
	const locatns = document.getElementById('locatns').value;
	const artitle = document.getElementById('artitle').value;
	const userid = document.getElementById('userid').value;
	const content = editor.getHTML();
	
	const xhr = new XMLHttpRequest();
	xhr.open('PUT', '/article/modify/' + anumber);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	
	const data = JSON.stringify({
		anumber : anumber,
		locatns : locatns,
		artitle : artitle,
		ewriter : userid,
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