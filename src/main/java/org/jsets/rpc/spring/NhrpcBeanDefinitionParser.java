package org.jsets.rpc.spring;

import java.util.ArrayList;
import java.util.List;

import org.jsets.rpc.utils.StringKit;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

/**
 * AbstractBeanDefinitionParser
 * 
 * @author william.liangf
 * @export
 */
public class NhrpcBeanDefinitionParser implements BeanDefinitionParser {

	private final Class<?> beanClass;
	private final boolean required;

	public NhrpcBeanDefinitionParser(Class<?> beanClass, boolean required) {
		this.beanClass = beanClass;
		this.required = required;
	}

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		String id = element.getAttribute("id");
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(beanClass);
		beanDefinition.setLazyInit(false);
		if (ProvidersBean.class.equals(beanClass)) {
			List<Element> providers = DomUtils.getChildElementsByTagName(element, "provider"); 
			List<ProviderBean> providerBeans = new ArrayList<ProviderBean>();
			for(Element provider:providers){
				String providerId = provider.getAttribute("id");
				String interfaze = provider.getAttribute("interface");
				String ref = provider.getAttribute("ref");
				String asyn = provider.getAttribute("asyn");
				ProviderBean providerBean = new ProviderBean();
				providerBean.setBeanId(providerId);
				providerBean.setInterfaze(interfaze);
				providerBean.setRef(ref);
				if(StringKit.isNotEmpty(asyn)&&"true".equals(asyn)){
					providerBean.setAsyn(true);
				}
				providerBeans.add(providerBean);
			}
			int port = Integer.valueOf(element.getAttribute("port"));
			String contextRoot = element.getAttribute("contextRoot");
			beanDefinition.getPropertyValues().addPropertyValue("port", port);
			beanDefinition.getPropertyValues().addPropertyValue("contextRoot", contextRoot);
			beanDefinition.getPropertyValues().addPropertyValue("providerBeans", providerBeans);
			parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		}
		else if(ReferencesProxy.class.equals(beanClass)){
			String url = element.getAttribute("url");
			String user = element.getAttribute("user");
			String password = element.getAttribute("password");
			List<Element> references = DomUtils.getChildElementsByTagName(element, "reference"); 
			for(Element reference:references){
				String referenceBd_id = reference.getAttribute("id");
				RootBeanDefinition referenceBd = new RootBeanDefinition();
				referenceBd.setBeanClass(ReferencesProxy.class);
				referenceBd.setLazyInit(false);
				referenceBd.getPropertyValues().addPropertyValue("serviceUrl", url+referenceBd_id+"/");
				referenceBd.getPropertyValues().addPropertyValue("serviceInterface", reference.getAttribute("interface"));
				parserContext.getRegistry().registerBeanDefinition(referenceBd_id, referenceBd);
			}
		}
		return beanDefinition;
	}
}