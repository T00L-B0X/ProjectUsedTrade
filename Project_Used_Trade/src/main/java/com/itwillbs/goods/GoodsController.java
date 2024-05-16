package com.itwillbs.goods;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.auction.AuctionRecordVO;
import com.itwillbs.auction.AuctionService;
import com.itwillbs.auction.AuctionVO;
import com.itwillbs.user.MemberVO;
import com.itwillbs.user.MemberService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class GoodsController {

	@Inject
	private GoodsService gService;

	@Inject
	private AuctionService aService;

	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

	// http://localhost:8088/goods/goodsMain
	@RequestMapping(value = "/goods/goodsMain", method = RequestMethod.GET)
	public void GoodsListGET() throws Exception {
		logger.debug(" GoodsListGET() 호출 ");
	}
	
	@RequestMapping(value = "/goods/read", method = RequestMethod.GET)
	public void GoodsInfoGET(@RequestParam("goods_id") int goods_id, Model model) throws Exception {
		logger.debug(" GoodsInfoGET() 호출 ");
		// 글정보 저장
		GoodsVO gvo = gService.getGoodsInfo(goods_id);
		AuctionVO avo = aService.getAuctionInfo(goods_id);
		
		AuctionRecordVO arvo = aService.getBuyInfo(goods_id);
		logger.debug("arvo : "+arvo);
		model.addAttribute("arvo", arvo);
		
		
		// 입찰 횟수
		int bidCount = aService.getBidCount(goods_id);
		List<String> imgList = gService.getImgList(goods_id);
		int imgCount = imgList.size();
		//int imgCount = gService.get
		model.addAttribute(gvo);
		model.addAttribute("avo", avo);
		model.addAttribute("imgCount", imgCount);
		logger.debug("gvo : " + gvo);
		
		model.addAttribute("bidCount", bidCount);
	}

	@RequestMapping(value = "/goods/record", method = RequestMethod.GET)
	public void GoodsRecordGET(@RequestParam("goods_id") int goods_id, Model model) {
		logger.debug(" GoodsRecordGET() 호출 ");
		// 경매정보 가져오기
		List<AuctionRecordVO> recordList = aService.getRecordList(goods_id);
		model.addAttribute("recordList", recordList);
	}

	@RequestMapping(value = "/goods/register", method = RequestMethod.GET)
	public void GoodsRegisterGet() {
		logger.debug("GoodsRegisterGet()");
	}

	@RequestMapping(value = "/goods/register", method = RequestMethod.POST)
	public String GoodsRegisterPost(GoodsVO gvo, @RequestParam(name = "Img0") MultipartFile Img0,
			@RequestParam(name = "Img1", required = false) MultipartFile Img1,
			@RequestParam(name = "Img2", required = false) MultipartFile Img2,
			@RequestParam(name = "Img3", required = false) MultipartFile Img3,
			@RequestParam(name = "Img4", required = false) MultipartFile Img4, HttpServletRequest req)
			throws Exception {
		logger.debug("GoodsRegisterPost()");
		// 로그인 한 사용자의 id
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userid = authentication.getName();
		gvo.setUserid(userid);
		// 등록할 새로운 상품번호(max(goods_id)+1) 가져오기

		int newGoodsId = gService.getNewGoodsId();
		gvo.setGoods_id(newGoodsId);
		gvo.setGoods_repimg("Img0");

		// insert 상품정보
		gService.insertGoodsInfo(gvo);
		System.out.println("gvo : " + gvo);
		// insert 된 상품정보의 regdate 가져오기
		Timestamp regdate = gService.getRegdate(newGoodsId);
		// insert 경매정보 -> Auction 테이블
		AuctionVO avo = new AuctionVO();
		avo.setGoods_id(gvo.getGoods_id());
		avo.setUserid(gvo.getUserid());
		avo.setAu_title(gvo.getGoods_title());
		avo.setInstant_price(gvo.getInstant_price());
		avo.setStart_time(regdate);
		Timestamp endTime = new Timestamp(regdate.getTime() + (gvo.getAuction_time() * 60 * 60 * 1000));
		avo.setEnd_time(endTime);
		avo.setStart_price(gvo.getStart_price());
		avo.setAu_status(1);
		aService.insertAuctionInfo(avo);

		// 업로드 폴더
		String uploadDirName;
		// 상품 이미지 폴더 이름
		String goodsDirName;

		// Img0 이름gvo 저장 + 폴더 생성 + 파일 넣기
		if (!Img0.isEmpty()) {
			// 대표 사진 이름 gvo에 저장
			// 업로드 폴더
			uploadDirName = req.getRealPath("/upload");
			logger.debug(uploadDirName); // C:\workspace_sts6\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Project_Used_Trade\
											// upload
			// 상품 이미지 폴더
			goodsDirName = uploadDirName + "/" + newGoodsId;
			// 상품 이미지 (Img0 + . + 확장자)
			String goodsFileName0 = "Img0" + "." + Img0.getOriginalFilename().split("\\.")[1];
			File uploadDir = new File(uploadDirName);

			// 상품이미지 경로
			String goodsImgPath = goodsDirName + File.separator + goodsFileName0;

			if (!uploadDir.exists()) {
				uploadDir.mkdirs(); // 업로드 이미지 폴더 만들기
			}
			File goodsDir = new File(goodsDirName);
			if (!goodsDir.exists()) {
				goodsDir.mkdirs(); // 상품번호(max(goods_id)+1) 폴더만들기
			}

			try (OutputStream os = new FileOutputStream(goodsImgPath)) {
				os.write(Img0.getBytes());
			}

			// 대표 사진 정보 insert
			GoodsImgVO ivo0 = new GoodsImgVO();
			ivo0.setGoods_id(newGoodsId);
			ivo0.setGoods_img(goodsFileName0);

			int result = gService.insertImageInfo(ivo0);

			if (result == 1) {
				logger.debug("Img0 insert 성공");
			} else {
				logger.debug("Img0 insert 실패");
			}

			// 나머지 상품 정보 해당 상품폴더에 넣기 + 상품 사진 테이블에 insert
			if (!Img1.isEmpty()) {
				String goodsFileName1 = "Img1" + "." + Img1.getOriginalFilename().split("\\.")[1];
				goodsImgPath = goodsDirName + File.separator + goodsFileName1;
				try (OutputStream os = new FileOutputStream(goodsImgPath)) {
					os.write(Img1.getBytes());
				}
				GoodsImgVO ivo1 = new GoodsImgVO();
				ivo1.setGoods_id(newGoodsId);
				ivo1.setGoods_img(goodsFileName1);
				int result1 = gService.insertImageInfo(ivo1);
				if (result1 == 1) {
					logger.debug("Img1 insert 성공");
				} else {
					logger.debug("Img1 insert 실패");
				}
			}

			if (!Img2.isEmpty()) {
				String goodsFileName2 = "Img2" + "." + Img2.getOriginalFilename().split("\\.")[1];
				goodsImgPath = goodsDirName + File.separator + goodsFileName2;
				try (OutputStream os = new FileOutputStream(goodsImgPath)) {
					os.write(Img2.getBytes());
				}
				GoodsImgVO ivo2 = new GoodsImgVO();
				ivo2.setGoods_id(newGoodsId);
				ivo2.setGoods_img(goodsFileName2);
				int result2 = gService.insertImageInfo(ivo2);
				if (result2 == 1) {
					logger.debug("Img2 insert 성공");
				} else {
					logger.debug("Img2 insert 실패");
				}
			}

			if (!Img3.isEmpty()) {
				String goodsFileName3 = "Img3" + "." + Img3.getOriginalFilename().split("\\.")[1];
				goodsImgPath = goodsDirName + File.separator + goodsFileName3;
				try (OutputStream os = new FileOutputStream(goodsImgPath)) {
					os.write(Img3.getBytes());
				}
				GoodsImgVO ivo3 = new GoodsImgVO();
				ivo3.setGoods_id(newGoodsId);
				ivo3.setGoods_img(goodsFileName3);
				int result3 = gService.insertImageInfo(ivo3);
				if (result3 == 1) {
					logger.debug("Img3 insert 성공");
				} else {
					logger.debug("Img3 insert 실패");
				}
			}

			if (!Img4.isEmpty()) {
				String goodsFileName4 = "Img4" + "." + Img4.getOriginalFilename().split("\\.")[1];
				goodsImgPath = goodsDirName + File.separator + goodsFileName4;
				try (OutputStream os = new FileOutputStream(goodsImgPath)) {
					os.write(Img4.getBytes());
				}
				GoodsImgVO ivo4 = new GoodsImgVO();
				ivo4.setGoods_id(newGoodsId);
				ivo4.setGoods_img(goodsFileName4);
				int result2 = gService.insertImageInfo(ivo4);
				if (result2 == 1) {
					logger.debug("Img4 insert 성공");
				} else {
					logger.debug("Img4 insert 실패");
				}
			}

		}

		return "redirect:/goods/read?goods_id=" + newGoodsId;

	}

	/*
	 * @RequestMapping(value="/displayImage", method = RequestMethod.GET) public
	 * void displayImage(@RequestParam("goods_id") int goods_id, HttpServletRequest
	 * req,HttpServletResponse resp) throws Exception {
	 * logger.debug("displayImages() 호출");
	 * 
	 * // 해당 상품의 이미지 파일 이름 목록을 가져오는 로직 List<String> imgList =
	 * gService.getImgList(goods_id);
	 * 
	 * // 이미지 파일 경로 String downloadPath = req.getRealPath("/upload") +
	 * File.separator + goods_id;
	 * 
	 * // 이미지 파일을 읽어와서 브라우저에 전송 try (OutputStream out = resp.getOutputStream()) { //
	 * 이미지의 MIME 타입 설정 resp.setContentType("image/jpeg");
	 * 
	 * // 모든 이미지 파일을 순회하며 출력 for (String imageName : imgList) { String imagePath =
	 * downloadPath + imageName; try (InputStream in = new
	 * FileInputStream(imagePath)) { // 이미지 데이터 전송 byte[] buffer = new byte[1024 *
	 * 8]; int bytesRead; while ((bytesRead = in.read(buffer)) != -1) {
	 * out.write(buffer, 0, bytesRead); } } catch (IOException e) {
	 * logger.error("이미지 파일을 읽는 중 오류 발생: " + e.getMessage());
	 * resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); } } } catch
	 * (IOException e) { logger.error("이미지 파일 출력 중 오류 발생: " + e.getMessage());
	 * resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); } }
	 */
	@RequestMapping(value = "/displayImages", method = RequestMethod.GET)
	public void displayImages(@RequestParam("goods_id") int goods_id, @RequestParam("imageNo") int imageNo,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		logger.debug("displayImages() 호출");

		// 해당 상품의 이미지 파일 이름 목록을 가져오는 로직
		List<String> imgList = gService.getImgList(goods_id);
		logger.debug("imgList : " + imgList);

		// 이미지 파일 경로
		String downloadPath = req.getRealPath("/upload") + File.separator + goods_id;
		logger.debug("downloadPath : " + downloadPath);
		// 이미지 파일을 읽어와서 브라우저에 전송
		try (OutputStream out = resp.getOutputStream()) {
			// 이미지의 MIME 타입 설정
			resp.setContentType("image/jpeg");

			// 모든 이미지 파일을 순회하며 출력
			/*
			 * for (String imageName : imgList) { try (InputStream in = new
			 * FileInputStream(downloadPath + File.separator + imageName)) { // 이미지 데이터 전송
			 * byte[] buffer = new byte[1024 * 8]; int bytesRead; while ((bytesRead =
			 * in.read(buffer)) != -1) { out.write(buffer, 0, bytesRead); } } catch
			 * (IOException e) { logger.error("이미지 파일을 읽는 중 오류 발생: " + e.getMessage());
			 * resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); } }
			 */
			String goodsImageName = imgList.get(imageNo);
			try (InputStream in = new FileInputStream(downloadPath + File.separator + goodsImageName)) {
				// 이미지 데이터 전송
				byte[] buffer = new byte[1024 * 8];
				int bytesRead;
				while ((bytesRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
			} catch (IOException e) {
				logger.error("이미지 파일을 읽는 중 오류 발생: " + e.getMessage());
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} catch (IOException e) {
			logger.error("이미지 파일 출력 중 오류 발생: " + e.getMessage());
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/goods/glist", method = RequestMethod.GET)
	public void getGlist(Model model) {
		logger.debug("getGlist() 호출");

		model.addAttribute("fake", gService.fakeVO());
	}

}
