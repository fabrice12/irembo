package io.rhenez.irembotest.controllers;


import io.rhenez.irembotest.Dto.VerificationRequest;
import io.rhenez.irembotest.Dto.VerificationResponse;
import io.rhenez.irembotest.helpers.HelperFunctions;
import io.rhenez.irembotest.models.User;
import io.rhenez.irembotest.repositories.UserRepository;
import io.rhenez.irembotest.security.IremboUserPrincipal;
import io.rhenez.irembotest.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@Slf4j
@AllArgsConstructor
public class BaseController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
//
        IremboUserPrincipal principal = (IremboUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User LoggedinUser = principal.getUser();
        User user = userRepository.findById(LoggedinUser.getId()).get();

//        userService.sendUserVerification(user);
        model.addAttribute("user", user);


        return "dashboard";
    }

    @PostMapping("verify-user")
    public String verifyUser(User user, @RequestPart("image") MultipartFile file, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setStatus("PENDING VERIFICATION");
        existingUser.setIdentityType(user.getIdentityType());
        existingUser.setIdentityNumber(user.getIdentityNumber());
        String uploadDir = "";
        if (file != null) {
            String fileName = System.currentTimeMillis() + StringUtils.cleanPath(file.getOriginalFilename());
            existingUser.setIdentityAttachment(fileName);
            uploadDir = "users/verification";
            try {
                HelperFunctions.saveFile(uploadDir, fileName, file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        userService.saveUser(existingUser);
        String appUrl = HelperFunctions.getAppURL(request) + "/uploads/" + uploadDir + "/" + existingUser.getIdentityAttachment();
        VerificationRequest verificationRequest = new VerificationRequest(existingUser.getId(), existingUser.getFirstName(), existingUser.getLastName(), existingUser.getIdentityType(), existingUser.getIdentityNumber(), appUrl, LocalDateTime.now());
        userService.sendUserVerification(verificationRequest);
        return "redirect:/";
    }
    @GetMapping("simulate-verification-response/{userId}")
    public String simulateVerificationResponse(@PathVariable("userId") Long userId){
        VerificationResponse verificationResponse=new VerificationResponse(userId,"APPROVED","Your account has been verified successfully");
        userService.simulateUserVerificationResponse(verificationResponse);
        return "redirect:/";
    }
}
