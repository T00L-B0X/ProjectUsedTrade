function deleteArticle() {
	const csrfToken = document.getElementById('csrfToken').value;
	const anumber = document.getElementById('anumber').value;
	
	const xhr = new XMLHttpRequest();
	xhr.open('PATCH', '/article/delete/' + anumber);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	
	const data = JSON.stringify({
		anumber : anumber,
	});
	
	xhr.send(data);
	
	xhr.onload = () => {
		if (xhr.status === 200) {
			alert('성공적으로 글을 삭제하였습니다.');
            window.location.href = '/article/list';
		} else {
			alert('내용을 확인해주세요.\n지속적으로 반복한다면 관리자에게 문의해주세요.');
		}
	};
}

function like() {
	const csrfToken = document.getElementById('csrfToken').value;
	const userid = document.getElementById('userid').value;
	const anumber = document.getElementById('anumber').value;
	
	if(userid == null) {
		return alert("로그인 후 사용가능한 기능입니다.");
	}
	
	const xhr = new XMLHttpRequest();
	xhr.open('POST', '/article/like');
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	
	const data = JSON.stringify({
		userid : userid,
		anumber : anumber
	});
	
	xhr.send(data);
	
	console.log("like@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	console.log(xhr);
	xhr.onload = () => {
		if (xhr.status === 200) {
			location.reload(true);
		} else {
			alert('내용을 확인해주세요.\n지속적으로 반복한다면 관리자에게 문의해주세요.');
		}
	};
}

function dislike() {
	const csrfToken = document.getElementById('csrfToken').value;
	const userid = document.getElementById('userid').value;
	const anumber = document.getElementById('anumber').value;
	
	const xhr = new XMLHttpRequest();
	xhr.open('DELETE', '/article/dislike');
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	
	const data = JSON.stringify({
		userid : userid,
		anumber : anumber
	});
	
	xhr.send(data);
	
	console.log("dislike@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	console.log(xhr);
	xhr.onload = () => {
		if (xhr.status === 200) {
			location.reload(true);

		} else {
			alert('내용을 확인해주세요.\n지속적으로 반복한다면 관리자에게 문의해주세요.');
		}
	};
}