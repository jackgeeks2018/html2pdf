package com.huishi;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;


@SuppressWarnings("deprecation")
public class HtmlGenerator {
	private static Configuration config = null;
	private final static String SRC = "./src/main/resources/template";
	/**
	 * Static initialization.
	 *
	 * Initialize the configuration of Freemarker.
	 */
	static {
		config = new Configuration();
		//ClassPathResource resource = new ClassPathResource("template");
		try {
		config.setDirectoryForTemplateLoading(new File(SRC));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	/**
	 * Generate html string.
	 * 
	 * @param template
	 *            the name of freemarker teamlate.
	 * @param variables
	 *            the data of teamlate.
	 * @return htmlStr
	 * @throws Exception
	 */
	public static String generate(String template, Map<String, Object> variables)
			throws Exception {
		Template tp = config.getTemplate(template);
		StringWriter stringWriter = new StringWriter();
		BufferedWriter writer = new BufferedWriter(stringWriter);
		tp.setEncoding("UTF-8");
		tp.process(variables, writer);
		String htmlStr = stringWriter.toString();
		writer.flush();
		writer.close();
		return htmlStr;
	}
}
