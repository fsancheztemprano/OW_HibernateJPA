package com.openwebinars.hibernate.hbnpost.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.openwebinars.hibernate.hbnpost.dao.CommentDao;
import com.openwebinars.hibernate.hbnpost.dao.PostDao;
import com.openwebinars.hibernate.hbnpost.model.Comment;
import com.openwebinars.hibernate.hbnpost.model.Post;
import com.openwebinars.hibernate.hbnpost.model.User;
import com.openwebinars.hibernate.hbnpost.model.beans.CommentBean;
import com.openwebinars.hibernate.hbnpost.model.beans.PostBean;

@Controller
public class PostController {
	
	@Autowired
	private PostDao postDao;
	
	@Autowired 
	CommentDao commentDao;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping(value="/submit") 
	public String showForm(Model model){
		model.addAttribute("post", new PostBean());
		return "submit";
	}
	
	@PostMapping(value="/submit/newPost")
	public String submitPost(@ModelAttribute("post") PostBean postBean, BindingResult result, Model model) {

		Post post = new Post();
		post.setTitulo(postBean.getTitulo());
		post.setContenido(postBean.getContenido());
		post.setUrl(postBean.getUrl());
		User autor = (User) httpSession.getAttribute("userLoggedIn");
		post.setAutor(autor);
		postDao.create(post);
		autor.getPosts().add(post);
		
		return "redirect:/";
	}
	
	
	@GetMapping(value="/post/{id}")
	public String detail(@PathVariable("id") long id, Model model) {
		
		Post result = null;
		if ((result = postDao.getById(id)) != null) {
			model.addAttribute("post", result);
			model.addAttribute("commentForm", new CommentBean());
			return "postdetail";			
		} else
			return "redirect:/";
	}
	
	@PostMapping(value="/submit/newComment")
	public String submitComment(@ModelAttribute("commentForm") CommentBean commentBean, BindingResult result, Model model) {
		
		User autor = (User) httpSession.getAttribute("userLoggedIn");
		

		Comment comment = new Comment();
		comment.setUser(autor);
		
		Post post = postDao.getById(commentBean.getPost_id());
		comment.setPost(post);
		comment.setText(commentBean.getContenido());
		
		commentDao.create(comment);
		
		post.getComments().add(comment);
		autor.getComments().add(comment);
		
		return "redirect:/post/" + commentBean.getPost_id() ;
	}

}
