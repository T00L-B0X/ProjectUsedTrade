function deleteArticle() {
	const csrfToken = document.getElementById('csrfToken').value;
	const anumber = document.getElementById('anumber').value;
	const userid = document.getElementById('userid').value;
	
	const xhr = new XMLHttpRequest();
	xhr.open('PATCH', '/article/delete/' + anumber);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	
	const data = JSON.stringify({
		anumber : anumber,
		userid : userid,
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
	
	if(userid === "") {
	    if(confirm("로그인이 필요한 서비스입니다. 로그인하시겠습니까?")) {
	        window.location.href = "/user/login";
	    } else {
	        return;
	    }
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
	
	console.log(xhr);
	xhr.onload = () => {
		if (xhr.status === 200) {
			location.reload(true);

		} else {
			alert('내용을 확인해주세요.\n지속적으로 반복한다면 관리자에게 문의해주세요.');
		}
	};
}

function addComment() {
	const csrfToken = document.getElementById('csrfToken').value;
	const anumber = document.getElementById('anumber').value;
	const userid = document.getElementById('userid').value;
	const content = document.getElementById('commentContent').value;
	const commliv = document.getElementById('commliv') || 0;
	
	if(userid === "") {
	    if(confirm("로그인이 필요한 서비스입니다. 로그인하시겠습니까?")) {
	        window.location.href = "/user/login";
	    } else {
	        return;
	    }
	}
	
	const xhr = new XMLHttpRequest();
	xhr.open('POST', '/article/comment');
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
	
	const data = JSON.stringify({
		anumber : anumber,
		userid : userid,
		content : content,
		commliv : commliv,
	});
	
	xhr.send(data);
	
	xhr.onload = () => {
		if (xhr.status === 200) {
			location.reload(true);
		} else {
			alert('내용을 확인해주세요.\n지속적으로 반복한다면 관리자에게 문의해주세요.');
		}
	};

}

function editComment(cnumber, content) {
	const csrfToken = document.getElementById('csrfToken').value;
	const userid = document.getElementById('userid').value;
    var newContent = prompt("댓글을 수정하세요:", content);
    if (newContent !== null) {
        var xhr = new XMLHttpRequest();
        xhr.open("PUT", '/article/comment', true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
        
        var data = JSON.stringify({
        	cnumber : cnumber,
        	content : newContent,
        	ewriter : userid,
        });
        xhr.send(data);
        xhr.onload = () => {
            if (xhr.status === 200) {
                // 수정 성공 시 화면 갱신
                document.getElementById("modi" + cnumber).textContent = newContent;
                alert("댓글이 수정되었습니다.");
            } else {
                alert("댓글 수정에 실패했습니다.");
            }
        };
    }
}

function deleteComment(cnumber) {
    const csrfToken = document.getElementById('csrfToken').value;
    const userid = document.getElementById('userid').value;
    if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
        var xhr = new XMLHttpRequest();
        xhr.open("PATCH", "/article/comment");
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
        
        var data = JSON.stringify({
            cnumber: cnumber,
            ewriter: userid
        });
        
        xhr.send(data);
        
        xhr.onload = () => {
            if (xhr.status === 200) {
                alert("댓글이 삭제되었습니다.");
                location.reload(true);
            } else {
                alert("댓글 삭제에 실패했습니다.");
            }
        };
    }
}