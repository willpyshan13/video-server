package com.e_lliam.app.video.server.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.e_lliam.app.video.server.pojo.extjs.ExtFilter;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import com.e_lliam.app.video.server.pojo.extjs.ExtSort;
import com.e_lliam.app.video.server.utils.extjs.ExtPageRequest;
import com.google.common.collect.Lists;

public class PageableResolver implements HandlerMethodArgumentResolver {

	@Resource
	private ObjectMapper objectMapper;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return Arrays.asList(Pageable.class,ExtFilters.class).contains(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		if(Pageable.class.equals(parameter.getParameterType())){
			String pageStr=webRequest.getParameter("page");
			String pageSizeStr=webRequest.getParameter("limit");
			int page=1,pageSize=10;
			if(StringUtils.hasText(pageStr)){
				page=Integer.parseInt(pageStr);
			}
			if(StringUtils.hasText(pageSizeStr)){
				pageSize=Integer.parseInt(pageSizeStr);
			}
			String sortStr=webRequest.getParameter("sort");
			Sort sort=null;
			if(StringUtils.hasText(sortStr)){
				ExtSort[] extSort=objectMapper.readValue(sortStr, ExtSort[].class);
				List<Order> os=Lists.newArrayList();
				for(ExtSort es:extSort){
					Direction direction=es.isAsc()?Direction.ASC:Direction.DESC;
					Order o=new Order(direction, es.getProperty());
					os.add(o);
				}
				if(os.size()>0){
					sort=new Sort(os);
				}
			}
			if(sort==null){
				return new ExtPageRequest(page, pageSize);
			}else{
				return new ExtPageRequest(page, pageSize, sort);
			}
		}else if(ExtFilters.class.equals(parameter.getParameterType())){
			String filterStr=webRequest.getParameter("filter");
			if(StringUtils.hasText(filterStr)){
					List<ExtFilter> readValue = objectMapper.readValue(filterStr, new TypeReference<List<ExtFilter>>() {});
					return new ExtFilters(readValue);
			}else{
				return new ExtFilters(Collections.EMPTY_LIST);
			}
		}
		return WebArgumentResolver.UNRESOLVED;
	}

}
