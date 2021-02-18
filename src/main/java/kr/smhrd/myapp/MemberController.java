package kr.smhrd.myapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.smhrd.model.MemberDAO;
import kr.smhrd.model.MemberVO;

@Controller
public class MemberController {

    @Autowired
    private MemberDAO memberDAO;

    // list.do --> memberList()
    @RequestMapping("/list.do")
    public String memberList(Model model) {
        // Model(DA)
        List<MemberVO> list = memberDAO.memberList();
        model.addAttribute("list", list);
        // WEB-INF/views/list.jsp ( WEB-INF/views -> 접두사(프리픽스) .jsp -> 접미사(서픽스) )
        // jsp는 프리픽스와 서픽스가 붙어서 자동으로 바뀜.
        return "list"; 
    }
    
    @RequestMapping("/insertForm.do")
    public String insertForm() {
        return "insertForm";
    }
    
    @RequestMapping("/insert.do")
    public String memberInsert(MemberVO vo) {
        int cnt = memberDAO.memberInsert(vo);
        return "redirect:list.do";
    }
    
    @RequestMapping("/delete.do")
    /*
     * public String memberDelete(int num) { // 받아온 값을 다른으로 바꾸고 싶을 때 
     * 아래처럼 int num에서 num에 aaa 같은 형태로 변수선언하면 된다.
     */
    
    public String memberDelete(@RequestParam("num") int num) {
        int cnt = memberDAO.memberDelete(num);
        return "redirect:list.do";
    }
    
    @RequestMapping("/content.do")
    public String memberContent(@RequestParam("num") int num, Model model) {
        MemberVO vo = memberDAO.memberContent(num);
        model.addAttribute("vo", vo);
        return "content";
    }
    
    @RequestMapping("/update.do")
    public String memberUpdate(MemberVO vo) {
        int cnt = memberDAO.memberUpdate(vo);
        return "redirect:list.do";
    }
}
