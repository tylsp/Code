package cn.hm.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import cn.hm.bean.Comment;
import cn.hm.bean.Posting;
import cn.hm.bean.Topic;
import cn.hm.bean.User;
import cn.hm.service.CommentService;
import cn.hm.service.PostingService;
import cn.hm.service.TopicService;
import cn.hm.service.UserService;
import cn.hm.service.impl.CommentServiceImpl;
import cn.hm.service.impl.PostingServiceImpl;
import cn.hm.service.impl.TopicServiceImpl;
import cn.hm.service.impl.UserServiceImpl;
import cn.hm.util.BaseServlet;

/**
 * 论坛模块的Servlet
 *
 */
@WebServlet("/ForumServlet")
public class ForumServlet extends BaseServlet {

	TopicService tservice = new TopicServiceImpl();
	PostingService pservice = new PostingServiceImpl();
	CommentService cservice = new CommentServiceImpl();
	UserService uservice = new UserServiceImpl();
	private static final long serialVersionUID = 1L;

	/*
	 * 获得所有帖子的主题，并在前端通过ajax加载主题名称
	 */
	protected String getAllTopic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Topic> topicList = tservice.getAllTopic();
		String json = JSONArray.toJSONString(topicList);
		response.getWriter().write(json);
		return null;
	}

	/*
	 * 获得所有的帖子并在用户界面通过点击所有帖子遍历显示帖子
	 */
	protected String getAllPosting(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Posting> postingList = getPosting();
		request.setAttribute("postingList", postingList);
		return "/forum/posting.jsp";
	}
	/*
	 * 获得所有的帖子并在管理员界面通过点击所有帖子遍历显示帖子
	 */
	protected String getPostings(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Posting> postinglist = getPosting();
		request.setAttribute("postinglist", postinglist);
		return "/forum/forummanage.jsp";
	}
	
	private List<Posting> getPosting() {
		// TODO Auto-generated method stub
		// 所有的帖子列表
		List<Posting> postingList = pservice.getAllPosting();
		for (int i = 0; i < postingList.size(); i++) {
			Posting posting = postingList.get(i);
			User user = uservice.getUserByUid(posting.getUid());
			posting.setUser(user);
		}
		return postingList;
	}

	/*
	 * 通过主题tid获得帖子,并在前端通过点击对应主题遍历显示帖子
	 */
	protected String getPostingByTid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tid = request.getParameter("tid");
		int ttid = 0;
		if (tid != null && !tid.trim().isEmpty()) {
			ttid = Integer.parseInt(tid);
		}
		// 按主题tid查询的帖子列表
		List<Posting> postingList = pservice.getPostingByTid(ttid);
		for (int i = 0; i < postingList.size(); i++) {
			Posting posting = postingList.get(i);
			User user = uservice.getUserByUid(posting.getUid());
			posting.setUser(user);
		}
		request.setAttribute("postingList", postingList);
		return "/forum/posting.jsp";
	}

	/*
	 * 发布帖子的方法
	 */
	protected String insertPosting(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 创建帖子对象
		Posting posting = new Posting();
		// 获取输入的帖子标题并设入帖子对象
		String title = request.getParameter("title");
		posting.setTitle(title);
		// 获取输入的帖子内容并设入帖子对象
		String content = request.getParameter("content");
		posting.setContent(content);
		// 获取系统时间并设入帖子对象
		Date time = new Date();
		posting.setTime(time);
		posting.setStatus(0);
		// 从session里获取
		User user = (User) request.getSession().getAttribute("user");
		posting.setUid(user.getUid());
		// 获取帖子主题id并设入帖子对象
		String tid = request.getParameter("tid");
		int ttid = 0;
		if (tid != null && !tid.trim().isEmpty()) {
			ttid = Integer.parseInt(tid);
		}
		posting.setTid(ttid);
		// 请求转发插入是否成功的消息
		pservice.insertPosting(posting);
		return "/forum/addPostingInfo.jsp";
	}

	/*
	 * 添加评论并显示被添加评论的帖子的所有评论
	 */
	protected String addComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Comment comment = new Comment();
		// 从session里获取
		User user = (User) request.getSession().getAttribute("user");
		comment.setUid(user.getUid());
		String comm = request.getParameter("comment");
		comment.setComment(comm);
		Date time = new Date();
		comment.setTime(time);
		String pid = request.getParameter("pid");
		int ppid = 0;
		if (pid != null && !pid.trim().isEmpty()) {
			ppid = Integer.parseInt(pid);
		}
		comment.setPid(ppid);
		cservice.addComment(comment);
		List<Comment> commentList = cservice.selectCommentByPid(ppid);
		for (int i = 0; i < commentList.size(); i++) {
			Comment thecomment = commentList.get(i);
			User usser = uservice.getUserByUid(thecomment.getUid());
			thecomment.setUser(usser);
		}
		request.setAttribute("commentList", commentList);
		return "/forum/comment.jsp";

	}

	/*
	 * 根据帖子id显示评论
	 */
	protected String selectCommentByPid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		int ppid = 0;
		if (pid != null && !pid.trim().isEmpty()) {
			ppid = Integer.parseInt(pid);
		}
		List<Comment> commentList = cservice.selectCommentByPid(ppid);

		for (int i = 0; i < commentList.size(); i++) {
			Comment thecomment = commentList.get(i);
			User user = uservice.getUserByUid(thecomment.getUid());
			thecomment.setUser(user);
		}
		request.setAttribute("commentList", commentList);
		return "/forum/comment.jsp";
	}

	/*
	 * 后台审核并修改指定帖子状态，1为审核通过，2为不通过
	 */
	protected String updatePostingStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String status = request.getParameter("status");
		String pid = request.getParameter("pid");
		int sstatus = 0;
		if (status != null && !status.trim().isEmpty()) {
			sstatus = Integer.parseInt(status);
		}
		int ppid = 0;
		if (pid != null && !pid.trim().isEmpty()) {
			ppid = Integer.parseInt(pid);
		}
		pservice.updatePostingStatus(sstatus, ppid);
		request.setAttribute("status", status);
		return "/forum/updatePostingInfo.jsp";
	}

	/*
	 * 遍历显示待审核的帖子
	 */
	protected String getPostingToCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Posting> postingList = pservice.getPostingToCheck();

		// new一个user对象并设入帖子对象
		for (int i = 0; i < postingList.size(); i++) {
			Posting posting = postingList.get(i);
			User user = uservice.getUserByUid(posting.getUid());
			posting.setUser(user);
		}
		request.setAttribute("postingList", postingList);
		return "/forum/forummanage.jsp";
	}

	/*
	 * 后台删除帖子
	 */
	protected String deletePosting(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		int ppid = 0;
		if (pid != null && !pid.trim().isEmpty()) {
			ppid = Integer.parseInt(pid);
		}
		pservice.deletePosting(ppid);
		
		return "/ForumServlet?method=getPostings";
	}

	/*
	 * 根据用户id查询帖子
	 */
	protected String getPostingByUid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		List<Posting> postingList = pservice.getPostingByUid(user.getUid());
		for (int i = 0; i < postingList.size(); i++) {
			Posting posting = postingList.get(i);
			posting.setUser(user);
		}
		request.setAttribute("postingList", postingList);
		return "/forum/posting.jsp";
	}

}
