package com.itwillbs.article;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/article")
public class ArticleControllerRest {

	private static final Logger logger = LoggerFactory.getLogger(ArticleControllerRest.class);

	@Inject
	ArticleService aService;

	@PostMapping(value = "")
	public ResponseEntity<String> addTopic(@RequestBody ArticleVO vo) throws Exception {
		logger.debug("addTopic() - POST 호출");

		ResponseEntity<String> result = null;

		try {
			aService.addTopic(vo);
			result = ResponseEntity.ok().contentType(MediaType.valueOf("text/plain; charset=UTF-8")).body("글쓰기 성공");
		} catch (Exception e) {
			result = ResponseEntity.badRequest().contentType(MediaType.valueOf("text/plain; charset=UTF-8"))
					.body("글쓰기 실패");
		}

		return result;
	}

}
