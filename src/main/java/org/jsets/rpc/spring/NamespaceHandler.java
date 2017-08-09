package org.jsets.rpc.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class NamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("providers", new NhrpcBeanDefinitionParser(ProvidersBean.class,true)); 
		registerBeanDefinitionParser("references", new NhrpcBeanDefinitionParser(ReferencesProxy.class,true));
	}
}