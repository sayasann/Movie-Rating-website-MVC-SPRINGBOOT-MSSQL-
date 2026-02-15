package com.example_odev_1.odev_1.controller.ControllerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example_odev_1.odev_1.Service.IUserService;
import com.example_odev_1.odev_1.entity.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserControllerImpl {

	@Autowired
	private IUserService userService;

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		return "users/register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user , BindingResult bindingResult , Model model) {
		 if (bindingResult.hasErrors()) {
		        return "users/register";
		    }
		 try {
		        userService.registration(user);
		    } catch (IllegalStateException e) {
		        model.addAttribute("usernameError", e.getMessage());
		        return "users/register";
		    }
		return "redirect:/users/login";
	}

	@GetMapping("/login")
	public String loginPage(HttpSession session) {
		if (session.getAttribute("loggedUser") != null) {
			return "redirect:/";
		}
		return "users/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String userName, @RequestParam String password, HttpSession session,
			Model model) {
		User user = userService.login(userName, password);

		if (user != null) {
			session.setAttribute("loggedUser", user);
			userService.syncWatchedMoviesFromReviews(user);
			updateUserCounts(session);
			return "redirect:/";
		} else {
			model.addAttribute("error", "Username or password incorrect");
			return "users/login";
		}
		

	}

	@PostMapping("/delete")
	public String deleteAccount(@RequestParam String password, Model model,HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");

	    if (loggedUser == null) {
	        return "redirect:/users/login";
	    }
	    
	    try {
	        userService.deleteAccount(loggedUser.getUserID(), password);
	        session.invalidate();
	        return "redirect:/";

	    } catch (IllegalStateException e) {
	        model.addAttribute("error", e.getMessage());
	        return "users/account"; 
	    }
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/users/login";
	}

	@PostMapping("/favorites/{movieId}")
	public String favoriteMovies(@PathVariable Long movieId, HttpSession session) {

		// Login kontrolü
		User loggedUser = (User) session.getAttribute("loggedUser");

		if (loggedUser == null) {
			return "redirect:/users/login";
		}

		// Favori ekle / çıkar
		userService.favoriteMovies(loggedUser, movieId);
		updateUserCounts(session);

		// Geri dön (film listesi)
		return "redirect:/movies/" + movieId;
	}

	@GetMapping("/favorites")
	public String myFavorites(HttpSession session, Model model) {

		User loggedUser = (User) session.getAttribute("loggedUser");

		if (loggedUser == null) {
			return "redirect:/users/login";
		}

		// Güncel user’ı DB’den al
		User user = userService.findById(loggedUser.getUserID());

		model.addAttribute("favorites", user.getFavoriteMovies());

		return "users/favorites";
	}

	@GetMapping("/watchList")
	public String watchList( HttpSession session, Model model) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser == null) {
			return "redirect:/users/login";
		}
		User user = userService.findById(loggedUser.getUserID());
		model.addAttribute("watchList", user.getWatchList());
		return "users/watchList";
	}
	
	@GetMapping("/watched")
	public String watchedMovies(HttpSession session,Model model) {
		User loggedUser=(User)session.getAttribute("loggedUser");
		if(loggedUser==null) {
			return "redirect:/users/login";
		}
		User user = userService.findById(loggedUser.getUserID());
		model.addAttribute("watchedMovies", user.getWatchedMovies());
		return "users/watchedMovies";
	}
	public void updateUserCounts(HttpSession session) {

	

		    User loggedUser = (User) session.getAttribute("loggedUser");
		    if (loggedUser == null) return;

		    User freshUser = userService.findById(loggedUser.getUserID());

		    session.setAttribute("favCount",
		            freshUser.getFavoriteMovies().size());

		    session.setAttribute("watchListCount",
		            freshUser.getWatchList().size());

		    session.setAttribute("watchedCount",
		            freshUser.getWatchedMovies().size());
		

	}
	@PostMapping("/watchlist/{movieId}")
	public String toggleWatchList(@PathVariable Long movieId,
	                              HttpSession session,
	                              RedirectAttributes redirectAttributes) {

	    User loggedUser = (User) session.getAttribute("loggedUser");
	    if (loggedUser == null) {
	        return "redirect:/users/login";
	    }

	    try {
	        userService.watchList(loggedUser, movieId);
	        updateUserCounts(session);
	    } catch (IllegalStateException e) {
	        redirectAttributes.addFlashAttribute("error", e.getMessage());
	    }

	    return "redirect:/movies/" + movieId;
	}
	@PostMapping("/watched/{movieId}")
	public String toggleWatched(@PathVariable Long movieId,
	                            HttpSession session) {

	    User loggedUser = (User) session.getAttribute("loggedUser");
	    if (loggedUser == null) {
	        return "redirect:/users/login";
	    }

	    userService.watchedMovies(loggedUser, movieId);
	    updateUserCounts(session);

	    return "redirect:/movies/" + movieId;
	}
	
	@GetMapping("/account")
	public String myAccount(HttpSession session,Model model) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		if(loggedUser==null) {
			return "redirect:/users/login";
		}
		model.addAttribute("user" , loggedUser);
		return "users/account";
	}
	
	@PostMapping("/account/password")
	public String changePassword(@RequestParam String newPassword,HttpSession session , Model model) {
		User loggedUser = (User)session.getAttribute("loggedUser");
		if(loggedUser==null) {
			return "redirect:/users/login";
		}
		if(newPassword==null || newPassword.isBlank() || newPassword.length()<3) {
			model.addAttribute("passwordError", "Password Size Cannot Be Less Than 3");
			return "users/account";
		}
		if(newPassword.equals(loggedUser.getUserPassword())) {
			model.addAttribute("passwordError", "New Password Has To Be Different From Your Current Password");
			return  "users/account";
		}
		userService.changePassword(loggedUser.getUserID(),newPassword);
		return "redirect:/users/account";
	}

	


	
	

}
