package com.vietsci.cms.frontend.main.controller;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.vietsci.cms.frontend.main.model.Member;

/**
 * Authorization controller 
 * 
 */
@Named(value = "auth")
@Scope("session")
public class AuthorizationController implements Serializable {

  private static final long serialVersionUID = -3583270597604889663L;
  
  private final static Logger log = LoggerFactory.getLogger(AuthorizationController.class);
  private final static String DUMMY_PASSWORD = "abc";
  
  static {
    memberData =  new String[][]{
        {"admin", "CMS fullname", "ADMIN", "A001"},
        {"co", "CMS CO fullname", "CO", "A001"},
        {"cp", "CMS CP fullname", "CP", "A001"},
        {"cc", "CMS CC fullname", "CC", "A001"}
    };
  }
  // dữ liệu giả dùng cho demo
  private final static String[][] memberData;
  private List<Member> members;
  
  // flag xác nhận đã login hay chưa
  private boolean isLoggedIn;
  // tên đăng nhập
  private String userName;
  // mật khẩu
  private String password;
  // đối tượng lưu giữ thông tin của người dùng đã đăng nhập
  private Member member;
  
  @PostConstruct
  public void init() {
    isLoggedIn = false;
    member = new Member();
	populateDummyData();
  }
  
  /**
   * Login 
   */
  public void login() {
    log.debug("do login");
    log.info("userName: " + userName + " password: " + password);
    // TODO this is would be used dummy data
    // replace them when authentication functionality is completed
    Member takenMember = findMember(userName, password); 
    isLoggedIn = takenMember != null;
    
    if (isLoggedIn) {
      
      log.debug("Access granted");
      
      try {
        BeanUtils.copyProperties(member, takenMember);
        
        FacesContext
            .getCurrentInstance()
            .getExternalContext()
            .redirect(
                ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
                    .getContextPath() + "/home/index.xhtml");
      } catch (IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      log.debug("Access denied. User: " + userName);
      FacesContext.getCurrentInstance().addMessage(null,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Tên đăng nhập hoặc mật khẩu không đúng."));
    }
  }

  /**
   * Tạo dữ liệu giả
   */
  private void populateDummyData() {
    members = new ArrayList<>();
    for (String[] row : memberData) {
//      Member profile = new Member(row[0], Long.parseLong(row[1]), Long.parseLong(row[2]), Long.parseLong(row[3]),
//          Long.parseLong(row[4]));
      Member profile = new Member(row[0],row[1], row[2], row[3]);
      members.add(profile);
    }
  }

  /**
   * Tìm kiếm member
   * 
   * @param userName tên đăng nhập
   * @param password mật khẩu
   * @return thông tin member, nếu không tìm thấy trả về NULL
   */
  private Member findMember(String userName, String password) {
    if (members == null) populateDummyData();
    //TODO cần tương tác với dữ liệu thật
    for (Member member : members) {
      if (userName.equals(member.getUserName()) && DUMMY_PASSWORD.equals(password)) {
        return member;
      }
    }
    return null;
  }
  
  /**
   * Logout
   */
  public String logout() {
    HttpSession httpSession = (HttpSession) (FacesContext.getCurrentInstance().getExternalContext()).getSession(false);
    if (httpSession != null) {
      httpSession.invalidate();
      return "/login";
    }
    return "";
  }
  
  // setter - getters
  public boolean isLoggedIn() {
    return isLoggedIn;
  }

  public void setLoggedIn(boolean isLoggedIn) {
    this.isLoggedIn = isLoggedIn;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Member getMember() {
    return member;
  }
  
  public void setMember(Member member) {
    this.member = member;
  }
}
