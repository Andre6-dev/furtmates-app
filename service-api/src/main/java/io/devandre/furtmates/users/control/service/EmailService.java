package io.devandre.furtmates.users.control.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import io.devandre.furtmates.shared.exception.ResourceNotFoundException;
import io.devandre.furtmates.users.entity.ActivationCode;
import io.devandre.furtmates.users.entity.User;
import io.devandre.furtmates.users.entity.jdbc.JdbcUserRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final Configuration freemarkerConfig;
    private final JdbcUserRepository userRepository;
    private static final String subject = "Activate your account";

    public EmailService(JavaMailSender javaMailSender, Configuration freemarkerConfig, JdbcUserRepository userRepository) {
        this.javaMailSender = javaMailSender;
        this.freemarkerConfig = freemarkerConfig;
        this.userRepository = userRepository;
    }

    @Value("${spring.mail.username}")
    private String from;

    @SneakyThrows
    public void sendActivationCode(ActivationCode activationCode) {
        log.info("Sending activation code to userId: {}", activationCode.getUserId());
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        Template template = freemarkerConfig.getTemplate("email.html");

        User user = userRepository.findById(Long.valueOf(activationCode.getUserId())).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + activationCode.getUserId())
        );

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", user.getFirstName());
        map.put("code", activationCode.getKey());
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        helper.setSubject(subject);
        helper.setFrom(from);
        helper.setTo(user.getEmail());
        helper.setText(html, true);

        javaMailSender.send(message);
    }

    public void sendPasswordResetEmail(String to, String resetUrl) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the link below:\n" + resetUrl);
        javaMailSender.send(message);
    }
}
