package com.my.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.vo.MyMemberVo;

@WebFilter("/myboard/*")
public class MyMemberFilter implements Filter {

    public MyMemberFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		MyMemberVo myMemberVo = (MyMemberVo) ((HttpServletRequest)request).getSession().getAttribute("myMemberVo");
		if (myMemberVo == null) {
			((HttpServletResponse)response).sendRedirect("/mypro17/mymember/login_form");
		} else {
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
