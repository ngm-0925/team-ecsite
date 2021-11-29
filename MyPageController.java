package jp.co.internous.nova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.internous.nova.model.domain.MstUser;
import jp.co.internous.nova.model.mapper.MstUserMapper;
import jp.co.internous.nova.model.session.LoginSession;

/**
 * マイページに関する処理を行うコントローラー
 */
@Controller
@RequestMapping("/nova/mypage")
public class MyPageController {
	
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	/**
	 * マイページ画面の初期表示
	 * @param m 画面表示用オブジェクト
	 * @return マイページ画面へ
	 */
	@RequestMapping("/")
	public String index(Model m) {
		MstUser user = userMapper.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());
		m.addAttribute("user", user);
		m.addAttribute("loginSession", loginSession);
		return "my_page";
	}
}