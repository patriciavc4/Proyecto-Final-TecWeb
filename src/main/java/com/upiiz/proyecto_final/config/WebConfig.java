package com.upiiz.proyecto_final.config;

import com.upiiz.proyecto_final.entities.UsuarioEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {

                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        String path = request.getRequestURI();
                        HttpSession session = request.getSession();

                        if ((path.startsWith("/carreras") || path.startsWith("/listado")) && session.getAttribute("usuario") == null) {
                            response.sendRedirect("/login");
                            return false;
                        }
                        return true;
                    }

                    @Override
                    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                        HttpSession session = request.getSession();
                        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");

                        if (usuario != null && modelAndView != null) {
                            String nombreCompleto = usuario.getNombreUsuario() + " " +
                                    usuario.getPrimerApellidoUsuario() + " " +
                                    usuario.getSegundoApellidoUsuario();

                            modelAndView.addObject("nombreAdmin", nombreCompleto);
                        }
                    }
                })
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/css/**", "/js/**", "/assets/**");
    }
}