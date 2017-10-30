package org.ranji.lemon.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.ranji.lemon.core.system.SystemContext;


/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the"License"); 
 * you may not use this file except in compliance with the License.  
 * You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
 * See the License for the specific language governing permissions and limitations under the License.
 * Copyright [2017] [RanJi] [Email-jiran1221@163.com]
 * 
 * 实现分页的过滤器
 * @author RanJi
 * @date 2013-10-1
 * @since JDK1.7
 * @version 1.0
 */
@WebFilter(filterName="pagerFilter",urlPatterns="/*")
public class PagerFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// page-当前要显示的页，rows-每页要显示的条目数量（EasyUI）
		HttpServletRequest request = (HttpServletRequest) req;
		String pageNumStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		try {
			if (!StringUtils.isEmpty(pageNumStr) && !StringUtils.isEmpty(pageSizeStr)) {
				int pageNum = Integer.parseInt(pageNumStr);
				int pageSize = Integer.parseInt(pageSizeStr);
				int offset = (pageNum - 1) * pageSize;
				SystemContext.setOffset(offset);
				SystemContext.setPageSize(pageSize);
			}
			chain.doFilter(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SystemContext.removeOffset();
			SystemContext.removePageSize();
		}
	}

}
