package jp.co.internous.nova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import jp.co.internous.nova.model.domain.MstUser;
import jp.co.internous.nova.model.form.UserForm;
import jp.co.internous.nova.model.mapper.MstUserMapper;
import jp.co.internous.nova.model.session.LoginSession;

/**
 * ユーザー登録関連のコントローラー
 */
@Controller
@RequestMapping("/nova/user")
public class UserController {
	
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	/**
	 * 新規ユーザー登録画面を表示する。
	 * @param m 画面表示用オブジェクト
	 * @return 新規ユーザー登録画面へ
	 */
	@RequestMapping("/")
	public String index(Model m) {
		m.addAttribute("loginSession",loginSession);
		return "register_user";
	}
	
	/**
	 * 新規ユーザー登録画面で重複確認ボタンを押した際のユーザー名重複確認
	 * @param f ユーザーフォーム
	 * @return trueなら登録成功、falseなら登録失敗
	 */
	@RequestMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestBody UserForm f) {
		int count = userMapper.findCountByUserName(f.getUserName());
		return count > 0;
	}
	
	/**
	 * ユーザー情報をDBへ登録
	 * @param UserForm ユーザーフォーム
	 * @return trueなら登録成功、falseなら登録失敗
	 */
	@RequestMapping("/register")
	@ResponseBody
	public boolean register(@RequestBody UserForm f) {
		MstUser user = new MstUser(f);
		
		int count = userMapper.insert(user);
		
		return count > 0;
	}
}