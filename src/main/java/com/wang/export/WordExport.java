package com.wang.export;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@RestController
@RequestMapping("/wanghe")
public class WordExport {
	
	@RequestMapping("/word")
	public String getFree(HttpServletRequest request,HttpServletResponse response,@RequestParam String id) throws IOException, TemplateException {
		// 1.设置配置类
		Configuration configuration=new Configuration(Configuration.getVersion());
		//2. 设置模板所在的目录
		configuration.setDirectoryForTemplateLoading(new File("D:\\wh\\code\\workspace\\my_workspace\\wordExport\\Web-INF\\page"));
		//3.设置字符集
		configuration.setDefaultEncoding("utf-8");	
		//4.加载模板
		Template template=configuration.getTemplate("test.ftl","utf-8");
		//5.创建数据模型
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("name","SW-X19108");
		map.put("age","中华人民gong");
		//6.创建Writer对象
	
		response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode("测试文档.doc","UTF-8"));
		
		Writer out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(),"utf-8"), 10240);
		
		
		//7.输出数据模型到文件中
		template.process(map,out);
		//8.关闭Writer对象
		out.close();
        return "成功了";
	}
	
}
